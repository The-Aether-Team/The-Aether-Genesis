package com.aetherteam.aether_genesis.client.renderer.entity.layers;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.entity.model.SliderHostMimicModel;
import com.aetherteam.aether_genesis.entity.monster.boss.SliderHostMimic;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class LabyrinthEyeLayer<T extends SliderHostMimic, M extends SliderHostMimicModel<T>> extends EyesLayer<T, M> {
    private static final RenderType HOST_MIMIC_EYE_LIT = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/slider_host_mimic/slider_host_mimic_critical_glow.png"));

    public LabyrinthEyeLayer(RenderLayerParent<T, M> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public void render(@Nonnull PoseStack poseStack, MultiBufferSource buffer, int packedLight, @Nonnull SliderHostMimic hostMimic, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer consumer = buffer.getBuffer(this.renderType());
        if(hostMimic.isAwake())
            this.getParentModel().renderToBuffer(poseStack, consumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Nonnull
    @Override
    public RenderType renderType() {
        return HOST_MIMIC_EYE_LIT;
    }
}