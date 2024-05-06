package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.model.CogProtectileModel;
import com.aetherteam.genesis.entity.projectile.CogProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CogProjectileRenderer extends EntityRenderer<CogProjectile> {
    private static final ResourceLocation COG_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/projectile/cog.png");
    private final CogProtectileModel cog;

    public CogProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.cog = new CogProtectileModel(context.bakeLayer(GenesisModelLayers.COG_PROJECTILE));
    }

    @Override
    public void render(CogProjectile cog, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float yRot = Mth.rotLerp(partialTicks, cog.yRotO, cog.getYRot());
        float xRot = Mth.lerp(partialTicks, cog.xRotO, cog.getXRot());
        poseStack.translate(1.0, 1.5, 1.0);
        if (!cog.isLarge()) {
            poseStack.scale(0.25F, 0.25F, 0.25F);
        }
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(cog)));
        this.cog.setupAnim(cog, 0.0F, 0.0F, cog.tickCount, yRot, xRot);
        this.cog.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(cog, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(CogProjectile cog) {
        return COG_TEXTURE;
    }
}
