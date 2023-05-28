package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.model.CarrionSproutModel;
import com.aetherteam.aether_genesis.entity.passive.CarrionSprout;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class CarrionSproutRenderer extends MobRenderer<CarrionSprout, CarrionSproutModel> {
    private static final ResourceLocation CARRION_SPROUT_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/carrion_sprout/carrion_sprout.png");

    public CarrionSproutRenderer(EntityRendererProvider.Context context) {
        super(context, new CarrionSproutModel(context.bakeLayer(GenesisModelLayers.CARRION_SPROUT)), 0.5F);
    }

    @Override
    protected void scale(CarrionSprout carrionSprout, @Nonnull PoseStack poseStack, float partialTickTime) {
        float f2 = 0.625F + carrionSprout.getSize() / 6.0F;
        RenderSystem.setShaderColor(f2, 1.0F, f2, 1.0F); //todo
        poseStack.scale(f2, f2, f2);
        poseStack.translate(0.0, 1.2, 0.0);
        this.shadowRadius = f2 - 0.25F;
    }

    @Override
    protected float getBob(@Nonnull CarrionSprout carrionSprout, float partialTicks) {
        return Mth.lerp(partialTicks, carrionSprout.sinage, carrionSprout.sinage + carrionSprout.sinageAdd);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull CarrionSprout carrionSprout) {
        return CARRION_SPROUT_TEXTURE;
    }
}
