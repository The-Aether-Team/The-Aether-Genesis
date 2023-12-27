package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.model.HostEyeProjectileModel;
import com.aetherteam.aether_genesis.entity.projectile.HostEyeProjectile;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HostEyeProjectileRenderer extends MobRenderer<HostEyeProjectile, HostEyeProjectileModel> {
    private static final ResourceLocation HOST_EYE_PROJECTILE_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/projectile/host_eye.png");

    public HostEyeProjectileRenderer(EntityRendererProvider.Context context) {
        super(context, new HostEyeProjectileModel(context.bakeLayer(GenesisModelLayers.HOST_EYE_PROJECTILE)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(HostEyeProjectile hostEye) {
        return HOST_EYE_PROJECTILE_TEXTURE;
    }
}
