package com.aetherteam.genesis.client.renderer.entity.layers;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.client.renderer.entity.model.TempestModel;
import com.aetherteam.genesis.entity.monster.Tempest;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class TempestTransparencyGlowLayer extends EyesLayer<Tempest, TempestModel> {
    private static final RenderType TEMPEST_GLOW = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tempest/tempest_layer_glow.png"));

    public TempestTransparencyGlowLayer(RenderLayerParent<Tempest, TempestModel> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public RenderType renderType() {
        return TEMPEST_GLOW;
    }
}