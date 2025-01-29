package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.layers.SentryGuardianGlowLayer;
import com.aetherteam.genesis.client.renderer.entity.model.SentryGuardianModel;
import com.aetherteam.genesis.entity.monster.dungeon.boss.SentryGuardian;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SentryGuardianRenderer extends MobRenderer<SentryGuardian, SentryGuardianModel> {
    private static final ResourceLocation SENTRY_GUARDIAN_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/sentry_guardian/sentry_guardian.png");
    private static final ResourceLocation SENTRY_GUARDIAN_AWAKE_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/sentry_guardian/sentry_guardian_critical.png");

    public SentryGuardianRenderer(EntityRendererProvider.Context context) {
        super(context, new SentryGuardianModel(context.bakeLayer(GenesisModelLayers.SENTRY_GUARDIAN)), 0.5F);
        this.addLayer(new SentryGuardianGlowLayer(this));
    }
    
    @Override
    public ResourceLocation getTextureLocation(SentryGuardian sentryGuardian) {
        return sentryGuardian.isAwake() ? SENTRY_GUARDIAN_AWAKE_TEXTURE : SENTRY_GUARDIAN_TEXTURE;
    }
}
