package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.model.FrostboundSpriteModel;
import com.aetherteam.aether_genesis.entity.companion.FrostboundSprite;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FrostboundSpriteRenderer extends CompanionRenderer<FrostboundSprite, FrostboundSpriteModel> {
    private static final ResourceLocation FROSTBOUND_SPRITE_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/frostbound_sprite.png");

    public FrostboundSpriteRenderer(EntityRendererProvider.Context context) {
        super(context, new FrostboundSpriteModel(context.bakeLayer(GenesisModelLayers.FROSTBOUND_SPRITE)), 0.45F);
    }

    @Override
    protected void scale(FrostboundSprite frostboundSprite, PoseStack poseStack, float partialTickTime) {
        poseStack.translate(0.0, -0.6, 0.0);
        float sin = Mth.sin((frostboundSprite.tickCount + partialTickTime) / 6);
        poseStack.translate(0.0, sin / 15, 0.0);
    }

    @Override
    protected float getBob(FrostboundSprite frostboundSprite, float partialTick) {
        return (frostboundSprite.tickCount + partialTick) / 6;
    }

    @Override
    public ResourceLocation getTextureLocation(FrostboundSprite frostboundSprite) {
        return FROSTBOUND_SPRITE_TEXTURE;
    }
}
