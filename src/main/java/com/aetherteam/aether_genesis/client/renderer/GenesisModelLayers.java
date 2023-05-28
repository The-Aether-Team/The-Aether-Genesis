package com.aetherteam.aether_genesis.client.renderer;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class GenesisModelLayers {
    public static final ModelLayerLocation CARRION_SPROUT = register("carrion_sprout");
    public static final ModelLayerLocation TEMPEST = register("tempest");
    public static final ModelLayerLocation TEMPEST_TRANSPARENCY = register("tempest", "transparency");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String type) {
        return register(new ResourceLocation(Genesis.MODID, name), type);
    }

    private static ModelLayerLocation register(ResourceLocation identifier, String type) {
        return new ModelLayerLocation(identifier, type);
    }
}
