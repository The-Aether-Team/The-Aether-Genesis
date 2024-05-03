package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.entity.projectile.DaggerfrostSnowball;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class DaggerfrostSnowballRenderer extends EntityRenderer<DaggerfrostSnowball> {
    private static final ResourceLocation DAGGERFROST_SNOWBALL_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/projectile/daggerfrost_snowball.png");

    public DaggerfrostSnowballRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * [CODE COPY] - {@link net.minecraft.client.renderer.entity.DragonFireballRenderer}
     */
    @Override
    public void render(DaggerfrostSnowball snowball, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (snowball.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(snowball) < 12.25)) {
            poseStack.pushPose();
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            PoseStack.Pose pose = poseStack.last();
            Matrix4f matrix4f = pose.pose();
            Matrix3f matrix3f = pose.normal();
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(snowball)));
            vertex(vertexconsumer, matrix4f, matrix3f, packedLight, 0.0F, 0, 0, 1);
            vertex(vertexconsumer, matrix4f, matrix3f, packedLight, 1.0F, 0, 1, 1);
            vertex(vertexconsumer, matrix4f, matrix3f, packedLight, 1.0F, 1, 1, 0);
            vertex(vertexconsumer, matrix4f, matrix3f, packedLight, 0.0F, 1, 0, 0);
            poseStack.popPose();
            super.render(snowball, entityYaw, partialTicks, poseStack, buffer, packedLight);
        }
    }

    private static void vertex(VertexConsumer consumer, Matrix4f matrix4f, Matrix3f matrix3f, int uv, float x, int y, int u, int v) {
        consumer.vertex(matrix4f, x - 0.5F, (float) y - 0.25F, 0.0F)
                .color(255, 255, 255, 255)
                .uv((float) u, (float) v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(uv)
                .normal(matrix3f, 0.0F, 1.0F, 0.0F)
                .endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(DaggerfrostSnowball snowball) {
        return DAGGERFROST_SNOWBALL_TEXTURE;
    }
}