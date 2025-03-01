package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.entity.projectile.EnchantedNeedle;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EnchantedNeedleRenderer extends ArrowRenderer<EnchantedNeedle> {
    private static final ResourceLocation ENCHANTED_NEEDLE_TEXTURE = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "textures/entity/projectile/dart/enchanted_needle.png");

    public EnchantedNeedleRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(EnchantedNeedle dart) {
        return ENCHANTED_NEEDLE_TEXTURE;
    }
}

