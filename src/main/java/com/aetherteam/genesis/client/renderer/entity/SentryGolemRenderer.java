package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.layers.SentryGolemLayer;
import com.aetherteam.genesis.client.renderer.entity.model.SentryGolemModel;
import com.aetherteam.genesis.entity.monster.dungeon.SentryGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SentryGolemRenderer extends MobRenderer<SentryGolem, SentryGolemModel> {
    private static final ResourceLocation SENTRY_GOLEM_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/sentry_golem/sentry_golem.png");

    public SentryGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SentryGolemModel(context.bakeLayer(GenesisModelLayers.SENTRY_GOLEM)), 0.7F);
        this.addLayer(new SentryGolemLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SentryGolem golem) {
        return SENTRY_GOLEM_TEXTURE;
    }
}
