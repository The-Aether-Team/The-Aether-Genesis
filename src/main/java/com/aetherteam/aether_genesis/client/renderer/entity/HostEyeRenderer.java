package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.model.HostEyeModel;
import com.aetherteam.aether_genesis.entity.miscellaneous.HostEye;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class HostEyeRenderer extends MobRenderer<HostEye, HostEyeModel<HostEye>> {
    private static final ResourceLocation HOST_EYE_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/projectile/host_eye.png");

    public HostEyeRenderer(EntityRendererProvider.Context context) {
        super(context, new HostEyeModel<>(context.bakeLayer(GenesisModelLayers.HOST_EYE)), 0.5F);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull HostEye sentryGuardian) {
        return HOST_EYE_TEXTURE;
    }
}
