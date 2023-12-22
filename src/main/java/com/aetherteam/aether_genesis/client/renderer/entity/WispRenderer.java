package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.client.renderer.entity.model.WispModel;
import com.aetherteam.aether_genesis.entity.companion.Wisp;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class WispRenderer extends CompanionRenderer<Wisp, WispModel> {
    private final ResourceLocation texture;

    public WispRenderer(EntityRendererProvider.Context renderer, ModelLayerLocation modelLocation, ResourceLocation textureLocation) {
        super(renderer, new WispModel(renderer.bakeLayer(modelLocation)), 0.3F);
        this.texture = textureLocation;
    }

    @Override
    protected void scale(Wisp wisp,  PoseStack poseStack, float partialTickTime) {
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
    public ResourceLocation getTextureLocation( Wisp wisp) {
        return this.texture;
    }
}
