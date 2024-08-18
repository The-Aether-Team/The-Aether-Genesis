package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.layers.TrackingGolemLayer;
import com.aetherteam.genesis.client.renderer.entity.model.TrackingGolemModel;
import com.aetherteam.genesis.entity.monster.dungeon.TrackingGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TrackingGolemRenderer extends MobRenderer<TrackingGolem, TrackingGolemModel> {
    private static final ResourceLocation TRACKING_GOLEM_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/tracking_golem/tracking_golem.png");
    private static final ResourceLocation TRACKING_GOLEM_HOSTILE_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/tracking_golem/tracking_golem_hostile.png");

    public TrackingGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new TrackingGolemModel(context.bakeLayer(GenesisModelLayers.TRACKING_GOLEM)), 0.7F);
        this.addLayer(new TrackingGolemLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(TrackingGolem golem) {
        return golem.getSeenEnemy() ? TRACKING_GOLEM_HOSTILE_TEXTURE : TRACKING_GOLEM_TEXTURE;
    }
}
