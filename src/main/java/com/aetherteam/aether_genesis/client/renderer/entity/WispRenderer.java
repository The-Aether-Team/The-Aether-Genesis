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

    public WispRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLocation, ResourceLocation textureLocation) {
        super(context, new WispModel(context.bakeLayer(modelLocation)), 0.3F);
        this.texture = textureLocation;
    }

    @Override
    protected void scale(Wisp wisp, PoseStack poseStack, float partialTickTime) {
        poseStack.translate(0.0, -0.75, 0.0);
        float sin = Mth.sin((wisp.tickCount + partialTickTime) / 6);
        poseStack.translate(0.0, sin / 15, 0.0);
    }

    @Override
    protected void setupRotations(Wisp wisp, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) { }

    @Override
    protected float getBob(Wisp wisp, float partialTick) {
        return partialTick;
    }

    @Override
    public ResourceLocation getTextureLocation(Wisp wisp) {
        return this.texture;
    }
}
