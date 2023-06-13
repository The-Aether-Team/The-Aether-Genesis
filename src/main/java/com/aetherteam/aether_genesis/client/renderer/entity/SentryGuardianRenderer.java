package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.layers.SentryGuardianLayer;
import com.aetherteam.aether_genesis.client.renderer.entity.model.SentryGuardianModel;
import com.aetherteam.aether_genesis.entity.monster.boss.SentryGuardian;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SentryGuardianRenderer extends MobRenderer<SentryGuardian, SentryGuardianModel<SentryGuardian>> {
    private static final ResourceLocation SENTRY_GUARDIAN_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/sentry_guardian/sentry_guardian.png");
    private static final ResourceLocation SENTRY_GUARDIAN_TEXTURE_GLOW = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/sentry_guardian/sentry_guardian_critical.png");

    public SentryGuardianRenderer(EntityRendererProvider.Context context) {
        super(context, new SentryGuardianModel<>(context.bakeLayer(GenesisModelLayers.SENTRY_GUARDIAN)), 0.5F);
        this.addLayer(new SentryGuardianLayer<>(this));
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull SentryGuardian sentryGuardian) {
        return sentryGuardian.isAwake() ? SENTRY_GUARDIAN_TEXTURE_GLOW : SENTRY_GUARDIAN_TEXTURE;
    }
}
