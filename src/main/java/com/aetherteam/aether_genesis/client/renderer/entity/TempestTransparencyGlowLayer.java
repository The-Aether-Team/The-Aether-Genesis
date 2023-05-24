package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.model.TempestModel;
import com.aetherteam.aether_genesis.entity.monster.Tempest;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class TempestTransparencyGlowLayer extends EyesLayer<Tempest, TempestModel<Tempest>> {
    private static final RenderType LAYER_GLOW = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tempest/tempest_layer_glow.png"));

    private final TempestModel transparency;
    public TempestTransparencyGlowLayer(RenderLayerParent<Tempest, TempestModel<Tempest>> entityRenderer, TempestModel transparencyModel) {
        super(entityRenderer);
        this.transparency = transparencyModel;
    }

    @Override
    public RenderType renderType() {
        return LAYER_GLOW;
    }

    /*    @Override
    public void render(@Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int packedLight, @Nonnull Tempest tempest, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (this.getParentModel() instanceof TempestModel && !tempest.isInvisible()) {
            this.getParentModel().copyPropertiesTo(this.transparency);
            this.transparency.prepareMobModel(tempest, limbSwing, limbSwingAmount, partialTicks);
            this.transparency.setupAnim(tempest, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            VertexConsumer consumer = buffer.getBuffer(LAYER_GLOW);
            this.transparency.renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(tempest, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }*/
}