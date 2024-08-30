package com.aetherteam.genesis.client.renderer.entity.layers;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.entity.model.ShadeOfArkenzusModel;
import com.aetherteam.genesis.entity.companion.ShadeOfArkenzus;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class ShadeOfArkenzusEyesLayer extends EyesLayer<ShadeOfArkenzus, ShadeOfArkenzusModel> {
    private static final RenderType SHADE_OF_ARKENZUS_GLOW = RenderType.eyes(new ResourceLocation(AetherGenesis.MODID, "textures/entity/companions/shade_of_arkenzus/shade_of_arkenzus_emissive.png"));

    public ShadeOfArkenzusEyesLayer(RenderLayerParent<ShadeOfArkenzus, ShadeOfArkenzusModel> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public RenderType renderType() {
        return SHADE_OF_ARKENZUS_GLOW;
    }
}
