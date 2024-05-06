package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.model.CarrionSproutModel;
import com.aetherteam.genesis.entity.passive.CarrionSprout;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CarrionSproutRenderer extends MobRenderer<CarrionSprout, CarrionSproutModel> {
    private static final ResourceLocation CARRION_SPROUT_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/carrion_sprout/carrion_sprout.png");
    private static final ResourceLocation CARRION_SPROUT_PINK_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/carrion_sprout/carrion_sprout_pink.png");
    private static final ResourceLocation CARRION_SPROUT_WHITE_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/carrion_sprout/carrion_sprout_white.png");

    public CarrionSproutRenderer(EntityRendererProvider.Context context) {
        super(context, new CarrionSproutModel(context.bakeLayer(GenesisModelLayers.CARRION_SPROUT)), 0.5F);
    }

    @Override
    protected void scale(CarrionSprout carrionSprout, PoseStack poseStack, float partialTickTime) {
        float size = carrionSprout.getSize();
        poseStack.scale(size, size, size);
        poseStack.translate(0.0, 1.2, 0.0);
        this.shadowRadius = size - 0.25F;
    }

    @Override
    protected float getBob(CarrionSprout carrionSprout, float partialTicks) {
        return Mth.lerp(partialTicks, carrionSprout.sinage, carrionSprout.sinage + carrionSprout.sinageAdd);
    }

    @Override
    public ResourceLocation getTextureLocation(CarrionSprout carrionSprout) {
        if (carrionSprout.getColor().equals("pink")) {
            return CARRION_SPROUT_PINK_TEXTURE;
        } else if (carrionSprout.getColor().equals("white")) {
            return CARRION_SPROUT_WHITE_TEXTURE;
        }
        return CARRION_SPROUT_TEXTURE;
    }
}
