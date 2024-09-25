package com.aetherteam.genesis.client.particle;

import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.TrackingGolemRenderer;
import com.aetherteam.genesis.client.renderer.entity.model.TrackingGolemModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class TrackingGolemWarningParticle extends Particle {
    private final TrackingGolemModel model;
    private final RenderType renderType = RenderType.entityTranslucent(TrackingGolemRenderer.TRACKING_GOLEM_HOSTILE_TEXTURE);

    public TrackingGolemWarningParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
        this.model = new TrackingGolemModel(Minecraft.getInstance().getEntityModels().bakeLayer(GenesisModelLayers.TRACKING_GOLEM));
        this.gravity = 0.0F;
        this.lifetime = 30;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        float f = ((float)this.age + pPartialTicks) / (float)this.lifetime;
        float f1 = 0.05F + 0.5F * Mth.sin(f * (float) Math.PI);
        PoseStack posestack = new PoseStack();
        posestack.mulPose(pRenderInfo.rotation());
        posestack.mulPose(Axis.XP.rotationDegrees(150.0F * f - 20.0F));
        posestack.scale(-1.0F, -1.0F, 1.0F);
        posestack.translate(0.0F, -0.5F, 1.9F);
        posestack.mulPose(Axis.XP.rotationDegrees(45));
        posestack.scale(1.5F, 1.5F, 1.5F);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexconsumer = multibuffersource$buffersource.getBuffer(this.renderType);
        this.model.head.render(posestack, vertexconsumer, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, f1);
        multibuffersource$buffersource.endBatch();
    }

    @Override
    public boolean shouldCull() {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(
                SimpleParticleType pType,
                ClientLevel pLevel,
                double pX,
                double pY,
                double pZ,
                double pXSpeed,
                double pYSpeed,
                double pZSpeed
        ) {
            return new TrackingGolemWarningParticle(pLevel, pX, pY, pZ);
        }
    }
}