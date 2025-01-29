package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.model.HostEyeProjectileModel;
import com.aetherteam.genesis.entity.projectile.HostEyeProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class HostEyeProjectileRenderer extends EntityRenderer<HostEyeProjectile> {
    private static final ResourceLocation HOST_EYE_PROJECTILE_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/projectile/host_eye.png");
    private final HostEyeProjectileModel eyeModel;

    public HostEyeProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.eyeModel = new HostEyeProjectileModel(context.bakeLayer(GenesisModelLayers.HOST_EYE_PROJECTILE));
    }

    @Override
    public void render(HostEyeProjectile hostEye, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(hostEye)));
        this.eyeModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        super.render(hostEye, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(HostEyeProjectile hostEye) {
        return HOST_EYE_PROJECTILE_TEXTURE;
    }
}
