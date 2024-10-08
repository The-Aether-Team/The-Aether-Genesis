package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.model.HostEyeProjectileModel;
import com.aetherteam.genesis.entity.projectile.HostEyeProjectile;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class HostEyeProjectileRenderer extends EntityRenderer<HostEyeProjectile> {
    private static final ResourceLocation HOST_EYE_PROJECTILE_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/projectile/host_eye.png");
    private final HostEyeProjectileModel hostEye;

        public HostEyeProjectileRenderer(EntityRendererProvider.Context context) {
            super(context);
            this.hostEye = new HostEyeProjectileModel(context.bakeLayer(GenesisModelLayers.HOST_EYE_PROJECTILE));
        }

    @Override
    public ResourceLocation getTextureLocation(HostEyeProjectile hostEye) {
        return HOST_EYE_PROJECTILE_TEXTURE;
    }
}
