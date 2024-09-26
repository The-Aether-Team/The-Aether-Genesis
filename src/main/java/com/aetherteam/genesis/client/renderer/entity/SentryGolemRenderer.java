package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.aether.Aether;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.layers.SentryGolemLayer;
import com.aetherteam.genesis.client.renderer.entity.model.DetonationProjectileModel;
import com.aetherteam.genesis.client.renderer.entity.model.SentryGolemModel;
import com.aetherteam.genesis.entity.monster.dungeon.SentryGolem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SentryGolemRenderer extends MobRenderer<SentryGolem, SentryGolemModel> {
    private static final ResourceLocation SENTRY_GOLEM_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/sentry_golem/sentry_golem.png");
    private static final ResourceLocation SENTRY_LIT_TEXTURE = new ResourceLocation(Aether.MODID, "textures/entity/mobs/sentry/sentry_lit.png");
    private static final RenderType SENTRY_EYE = RenderType.eyes(new ResourceLocation(Aether.MODID, "textures/entity/mobs/sentry/eye.png"));
    private final DetonationProjectileModel projectile;

    public SentryGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SentryGolemModel(context.bakeLayer(GenesisModelLayers.SENTRY_GOLEM)), 0.7F);
        this.addLayer(new SentryGolemLayer(this));
        this.projectile = new DetonationProjectileModel(context.bakeLayer(GenesisModelLayers.DETONATION_PROJECTILE));
    }

    @Override
    public void render(SentryGolem golem, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(golem, entityYaw, partialTicks, poseStack, buffer, packedLight);
        if (golem.isAlive()) {
            this.renderBomb(golem, entityYaw, partialTicks, poseStack, buffer, packedLight);
        }
    }

    public void renderBomb(SentryGolem golem, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        float progress = golem.progress;
        float scale = 1.0F;
        if (golem.getHandState() == 2) {
            if (!((double) progress < 0.5)) {
                return;
            }

            scale = Math.min(1.0F - (float) (golem.getFireTime() - 30) / 30.0F, 0.9F);
            scale *= 1.1F;
        }

        poseStack.pushPose();
        poseStack.translate(0.0F, 4.2F, 0.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.rotLerp(partialTicks, golem.yBodyRotO, golem.yBodyRot)));
        poseStack.translate(0.0, Math.sin(1.0F - progress) * 2.4 + 1.65, Math.sin(1.0F - progress) * -1.4);
        poseStack.scale(scale, scale, scale);
        this.projectile.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutoutNoCull(SENTRY_LIT_TEXTURE)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        this.projectile.renderToBuffer(poseStack, buffer.getBuffer(SENTRY_EYE), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(SentryGolem golem) {
        return SENTRY_GOLEM_TEXTURE;
    }
}
