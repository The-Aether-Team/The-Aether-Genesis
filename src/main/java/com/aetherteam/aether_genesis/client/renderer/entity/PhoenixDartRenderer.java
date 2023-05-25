package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.entity.PhoenixDart;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class PhoenixDartRenderer extends ArrowRenderer<PhoenixDart> {
    public static final ResourceLocation PHOENIX_DART_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/projectile/phoenix_dart.png");

    public PhoenixDartRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull PhoenixDart dart) {
        return PHOENIX_DART_TEXTURE;
    }
}
