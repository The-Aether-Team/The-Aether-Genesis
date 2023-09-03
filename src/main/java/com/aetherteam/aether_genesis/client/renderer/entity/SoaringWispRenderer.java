package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.model.WispModel;
import com.aetherteam.aether_genesis.entity.companion.Wisp;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;

import javax.annotation.Nonnull;

public class SoaringWispRenderer extends MobRenderer<Wisp, WispModel> { //todo abstract
    private static final ResourceLocation SOARING_WISP_LOCATION = new ResourceLocation(Genesis.MODID, "textures/entity/companions/soaring_wisp.png");
    private final ItemRenderer itemRenderer;

    public SoaringWispRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new WispModel(renderer.bakeLayer(GenesisModelLayers.SOARING_WISP)), 0.25F);
        this.itemRenderer = renderer.getItemRenderer();
    }

    @Override
    public void render(Wisp wisp, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        super.render(wisp, entityYaw, partialTicks, matrixStack, buffer, packedLight);
        if (this.shouldShowName(wisp)) {
            this.renderDisplayItem(wisp, matrixStack, buffer);
        }
    }

    @Override
    protected void scale(Wisp wisp, @Nonnull PoseStack poseStack, float partialTickTime) {
        poseStack.translate(0.0D, -0.75D, 0.0D);
        float sin = Mth.sin((wisp.tickCount + partialTickTime) / 6);
        poseStack.translate(0.0D, sin / 15, 0.0D);
    }

    @Override
    protected void setupRotations(Wisp entityLiving, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) { }

    @Override
    protected float getBob(Wisp wisp, float partialTick) {
        return partialTick;
    }

    @Override
    public ResourceLocation getTextureLocation(@Nonnull Wisp wisp) {
        return SOARING_WISP_LOCATION;
    }

    protected void renderDisplayItem(Wisp entity, PoseStack matrixStack, MultiBufferSource buffer) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(entity);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(entity, d0)) {
            float f = entity.getBbHeight() + 0.5F;
            matrixStack.pushPose();
            matrixStack.translate(0.0F, f, 0.0F);
            matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            matrixStack.translate(0.0F, 0.15, 0.0F);
            matrixStack.scale(1.0F, 1.0F, 0.1F);
            this.itemRenderer.renderStatic(entity.getSummonItem(), ItemDisplayContext.GROUND, LightTexture.pack(15, 15), OverlayTexture.NO_OVERLAY, matrixStack, buffer, entity.getLevel(), entity.getId());
            matrixStack.popPose();
        }
    }
}
