package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.entity.miscellaneous.CogProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

//todo FIX THIS
public class CogArrowRenderer extends EntityRenderer<CogProjectile> {
    private static final ResourceLocation COG_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/projectile/cog.png");
    private final ModelPart cogArrow;

    public CogArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.cogArrow = context.bakeLayer(GenesisModelLayers.COG_ARROW);
    }

    @Override
    public void render(CogProjectile cog, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(1.0, 1.5, 1.0);
        if (!cog.isLarge())
            poseStack.scale(0.25F, 0.25F, 0.25F);
        VertexConsumer iVertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(cog)));
        this.cogArrow.render(poseStack, iVertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(cog, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    
    @Override
    public ResourceLocation getTextureLocation( CogProjectile cogArrow) {
        return COG_TEXTURE;
    }
}
