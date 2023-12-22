package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.layers.TrackingGolemLayer;
import com.aetherteam.aether_genesis.client.renderer.entity.model.TrackingGolemModel;
import com.aetherteam.aether_genesis.entity.monster.TrackingGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TrackingGolemRenderer extends MobRenderer<TrackingGolem, TrackingGolemModel> {
    private static final ResourceLocation TRACKING_GOLEM_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tracking_golem/sentry_golem.png");
    private static final ResourceLocation TRACKING_GOLEM_TEXTURE_GLOW = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tracking_golem/sentry_golem_hostile.png");

    public TrackingGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new TrackingGolemModel(context.bakeLayer(GenesisModelLayers.TRACKING_GOLEM)), 0.5F);
        this.addLayer(new TrackingGolemLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation( TrackingGolem trackingGolem) {
        return trackingGolem.getSeenEnemy() ? TRACKING_GOLEM_TEXTURE_GLOW : TRACKING_GOLEM_TEXTURE;
    }
}
