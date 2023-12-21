package com.aetherteam.aether_genesis.data.resources.registries;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.data.resources.builders.GenesisBiomeBuilders;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class GenesisBiomes {
    public static final ResourceKey<Biome> VIBRANT_MEADOW = createKey("vibrant_meadow");
    public static final ResourceKey<Biome> VIBRANT_GROVE = createKey("vibrant_grove");
    public static final ResourceKey<Biome> VIBRANT_WOODLAND = createKey("vibrant_woodland");
    public static final ResourceKey<Biome> VIBRANT_FOREST = createKey("vibrant_forest");

    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Genesis.MODID, name));
    }

    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> vanillaConfiguredCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(VIBRANT_MEADOW, GenesisBiomeBuilders.vibrantMeadowBiome(placedFeatures, vanillaConfiguredCarvers));
        context.register(VIBRANT_GROVE, GenesisBiomeBuilders.vibrantGroveBiome(placedFeatures, vanillaConfiguredCarvers));
        context.register(VIBRANT_WOODLAND, GenesisBiomeBuilders.vibrantWoodlandBiome(placedFeatures, vanillaConfiguredCarvers));
        context.register(VIBRANT_FOREST, GenesisBiomeBuilders.vibrantForestBiome(placedFeatures, vanillaConfiguredCarvers));
    }
}
