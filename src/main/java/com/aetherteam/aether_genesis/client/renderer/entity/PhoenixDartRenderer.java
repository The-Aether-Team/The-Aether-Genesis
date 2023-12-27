package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.entity.projectile.PhoenixDart;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PhoenixDartRenderer extends ArrowRenderer<PhoenixDart> {
    public static final ResourceLocation PHOENIX_DART_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/projectile/phoenix_dart.png");

    public PhoenixDartRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(PhoenixDart dart) {
        return PHOENIX_DART_TEXTURE;
    }
}
