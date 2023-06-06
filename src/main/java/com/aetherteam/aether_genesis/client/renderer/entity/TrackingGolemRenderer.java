package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.TrackingGolemLayer;
import com.aetherteam.aether_genesis.client.renderer.model.TrackingGolemModel;
import com.aetherteam.aether_genesis.entity.monster.TrackingGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class TrackingGolemRenderer extends MobRenderer<TrackingGolem, TrackingGolemModel<TrackingGolem>> {
    private static final ResourceLocation TRACKING_GOLEM_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tracking_golem/sentry_golem.png");
    private static final ResourceLocation TRACKING_GOLEM_TEXTURE_GLOW = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tracking_golem/sentry_golem_hostile.png");

    public TrackingGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new TrackingGolemModel<>(context.bakeLayer(GenesisModelLayers.TRACKING_GOLEM)), 0.5F);
        this.addLayer(new TrackingGolemLayer<>(this));
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull TrackingGolem trackingGolem) {
        return trackingGolem.getSeenEnemy() ? TRACKING_GOLEM_TEXTURE_GLOW : TRACKING_GOLEM_TEXTURE;
    }
}
