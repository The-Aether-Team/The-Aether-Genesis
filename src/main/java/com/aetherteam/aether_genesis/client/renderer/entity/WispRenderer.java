package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.client.renderer.entity.model.WispModel;
import com.aetherteam.aether_genesis.entity.companion.Wisp;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class WispRenderer extends MobRenderer<Wisp, WispModel> implements DisplayItemPlate {
    private final ResourceLocation texture;
    private final ItemRenderer itemRenderer;

    public WispRenderer(EntityRendererProvider.Context renderer, ModelLayerLocation modelLocation, ResourceLocation textureLocation) {
        super(renderer, new WispModel(renderer.bakeLayer(modelLocation)), 0.25F);
        this.texture = textureLocation;
        this.itemRenderer = renderer.getItemRenderer();
    }

    @Override
    public void render(Wisp wisp, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        super.render(wisp, entityYaw, partialTicks, matrixStack, buffer, packedLight);
        if (this.shouldShowName(wisp)) {
            this.renderDisplayItem(wisp, matrixStack, buffer, this.entityRenderDispatcher, this.itemRenderer);
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
        return texture;
    }
}
