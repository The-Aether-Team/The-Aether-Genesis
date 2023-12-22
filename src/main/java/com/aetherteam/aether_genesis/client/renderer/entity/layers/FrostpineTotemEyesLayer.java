package com.aetherteam.aether_genesis.client.renderer.entity.layers;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.entity.model.FrostpineTotemModel;
import com.aetherteam.aether_genesis.entity.companion.FrostpineTotem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class FrostpineTotemEyesLayer extends EyesLayer<FrostpineTotem, FrostpineTotemModel> {
    private static final RenderType FROSTPINE_TOTEM_EYES = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/companions/frostpine_totem/frostpine_totem_emissive.png"));

    public FrostpineTotemEyesLayer(RenderLayerParent<FrostpineTotem, FrostpineTotemModel> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public RenderType renderType() {
        return FROSTPINE_TOTEM_EYES;
    }
}
