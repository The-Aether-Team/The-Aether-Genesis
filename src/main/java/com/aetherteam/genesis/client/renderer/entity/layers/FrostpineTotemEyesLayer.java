package com.aetherteam.genesis.client.renderer.entity.layers;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.entity.model.FrostpineTotemModel;
import com.aetherteam.genesis.entity.companion.FrostpineTotem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class FrostpineTotemEyesLayer extends EyesLayer<FrostpineTotem, FrostpineTotemModel> {
    private static final RenderType FROSTPINE_TOTEM_GLOW = RenderType.eyes(new ResourceLocation(AetherGenesis.MODID, "textures/entity/companions/frostpine_totem/frostpine_totem_emissive.png"));

    public FrostpineTotemEyesLayer(RenderLayerParent<FrostpineTotem, FrostpineTotemModel> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public RenderType renderType() {
        return FROSTPINE_TOTEM_GLOW;
    }
}
