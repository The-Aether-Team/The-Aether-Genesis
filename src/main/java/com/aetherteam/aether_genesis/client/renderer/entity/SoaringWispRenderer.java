package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.model.WispModel;
import com.aetherteam.aether_genesis.entity.companion.Wisp;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class SoaringWispRenderer extends MobRenderer<Wisp, WispModel> {
    private static final ResourceLocation SOARING_WISP_LOCATION = new ResourceLocation(Genesis.MODID, "textures/entity/companions/soaring_wisp.png");

    public SoaringWispRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new WispModel(renderer.bakeLayer(GenesisModelLayers.SOARING_WISP)), 0.25F);
    }

    @Override
    protected void scale(Wisp wisp, @Nonnull PoseStack poseStack, float partialTickTime) {
        poseStack.translate(0.0D, -0.75D, 0.0D);
        float sin = Mth.sin((wisp.tickCount + partialTickTime) / 6);
        poseStack.translate(0.0D, sin / 15, 0.0D);
    }

    @Override
    public ResourceLocation getTextureLocation(@Nonnull Wisp wisp) {
        return SOARING_WISP_LOCATION;
    }
}
