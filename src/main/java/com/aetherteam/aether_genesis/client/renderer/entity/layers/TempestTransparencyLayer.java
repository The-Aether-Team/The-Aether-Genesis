package com.aetherteam.aether_genesis.client.renderer.entity.layers;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.entity.model.TempestModel;
import com.aetherteam.aether_genesis.entity.monster.Tempest;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class TempestTransparencyLayer extends RenderLayer<Tempest, TempestModel<Tempest>> {
    private static final ResourceLocation TEMPEST_TRANSPARENCY_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tempest/tempest_layer.png");
    private final TempestModel transparency;

    public TempestTransparencyLayer(RenderLayerParent<Tempest, TempestModel<Tempest>> entityRenderer, TempestModel transparencyModel) {
        super(entityRenderer);
        this.transparency = transparencyModel;
    }

    public void render(@Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int packedLight, @Nonnull Tempest tempest, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (this.getParentModel() instanceof TempestModel && !tempest.isInvisible()) {
            this.getParentModel().copyPropertiesTo(this.transparency);
            this.transparency.prepareMobModel(tempest, limbSwing, limbSwingAmount, partialTicks);
            this.transparency.setupAnim(tempest, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(TEMPEST_TRANSPARENCY_TEXTURE));
            this.transparency.renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(tempest, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }

    }
}