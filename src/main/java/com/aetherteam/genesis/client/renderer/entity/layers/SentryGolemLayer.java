package com.aetherteam.genesis.client.renderer.entity.layers;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.entity.model.SentryGolemModel;
import com.aetherteam.genesis.entity.monster.dungeon.SentryGolem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class SentryGolemLayer extends EyesLayer<SentryGolem, SentryGolemModel> {
    private static final RenderType SENTRY_GOLEM_GLOW = RenderType.eyes(new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/sentry_golem/sentry_golem_glow.png"));

    public SentryGolemLayer(RenderLayerParent<SentryGolem, SentryGolemModel> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public RenderType renderType() {
        return SENTRY_GOLEM_GLOW;
    }
}
