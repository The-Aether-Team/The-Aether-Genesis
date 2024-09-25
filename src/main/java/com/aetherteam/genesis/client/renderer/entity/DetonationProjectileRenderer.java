package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.aether.Aether;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.model.DetonationProjectileModel;
import com.aetherteam.genesis.entity.projectile.DetonationProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class DetonationProjectileRenderer extends EntityRenderer<DetonationProjectile> {
    private static final ResourceLocation SENTRY_LIT_TEXTURE = new ResourceLocation(Aether.MODID, "textures/entity/mobs/sentry/sentry_lit.png");
    private static final RenderType SENTRY_EYE = RenderType.eyes(new ResourceLocation(Aether.MODID, "textures/entity/mobs/sentry/eye.png"));
    private final DetonationProjectileModel projectile;

    public DetonationProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.projectile = new DetonationProjectileModel(context.bakeLayer(GenesisModelLayers.DETONATION_PROJECTILE));
    }

    @Override
    public void render(DetonationProjectile projectile, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float yRot = Mth.rotLerp(partialTicks, projectile.yRotO, projectile.getYRot());
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
        this.projectile.setupAnim(projectile, 0.0F, 0.0F, projectile.tickCount, yRot, 180.0F);
        this.projectile.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(projectile))), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        this.projectile.renderToBuffer(poseStack, buffer.getBuffer(SENTRY_EYE), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(projectile, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(DetonationProjectile detonationProjectile) {
        return SENTRY_LIT_TEXTURE;
    }
}
