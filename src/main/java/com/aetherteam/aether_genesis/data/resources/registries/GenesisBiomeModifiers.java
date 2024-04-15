package com.aetherteam.aether_genesis.data.resources.registries;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.world.biomemodifier.AddMobChargeBiomeModifier;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ForgeBiomeModifiers;
import net.neoforged.neoforge.registries.ForgeRegistries;

import java.util.List;

public class GenesisBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_GREEN_AERCLOUD_4 = createKey("add_green_aercloud_4");
    public static final ResourceKey<BiomeModifier> ADD_GREEN_AERCLOUD_8 = createKey("add_green_aercloud_8");
    public static final ResourceKey<BiomeModifier> ADD_PURPLE_AERCLOUD = createKey("add_purple_aercloud");
    public static final ResourceKey<BiomeModifier> ADD_STORM_AERCLOUD = createKey("add_storm_aercloud");
    public static final ResourceKey<BiomeModifier> ADD_ORANGE_TREES = createKey("add_orange_trees");
    public static final ResourceKey<BiomeModifier> ADD_GROVE_TREES = createKey("add_grove_trees");
    public static final ResourceKey<BiomeModifier> ADD_WOODLAND_TREES = createKey("add_woodland_trees");
    public static final ResourceKey<BiomeModifier> ADD_FOREST_TREES = createKey("add_forest_trees");
    public static final ResourceKey<BiomeModifier> ADD_CONTINUUM_ORE = createKey("add_continuum_ore");

    public static final ResourceKey<BiomeModifier> COST_CARRION_SPROUT = createKey("cost_carrion_sprout");
    public static final ResourceKey<BiomeModifier> COST_DARK_SWET = createKey("cost_dark_swet");
    public static final ResourceKey<BiomeModifier> COST_TEMPEST = createKey("cost_tempest");

    public static final ResourceKey<BiomeModifier> SPAWN_CARRION_SPROUT = createKey("spawn_carrion_sprout");
    public static final ResourceKey<BiomeModifier> SPAWN_DARK_SWET = createKey("spawn_dark_swet");
    public static final ResourceKey<BiomeModifier> SPAWN_TEMPEST = createKey("spawn_tempest");

    private static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Genesis.MODID, name));
    }

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        context.register(ADD_GREEN_AERCLOUD_4, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_GREEN_AERCLOUDS),
                HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.GREEN_AERCLOUD_4_PLACEMENT)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION
        ));
        context.register(ADD_GREEN_AERCLOUD_8, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_GREEN_AERCLOUDS),
                HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.GREEN_AERCLOUD_8_PLACEMENT)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION
        ));
        context.register(ADD_PURPLE_AERCLOUD, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_PURPLE_AERCLOUDS),
                HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.PURPLE_AERCLOUD_PLACEMENT)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION
        ));
        context.register(ADD_STORM_AERCLOUD, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_STORM_AERCLOUDS),
                HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.STORM_AERCLOUD_PLACEMENT)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION
        ));
        context.register(ADD_ORANGE_TREES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_ORANGE_TREES),
                HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.ORANGE_TREE_PATCH_PLACEMENT)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_GROVE_TREES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_GROVE_TREES),
                HolderSet.direct(
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.SKYROOT_GROVE_LARGE_GREEN_SKYROOT_TREES_PLACEMENT)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_WOODLAND_TREES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_WOODLAND_TREES),
                HolderSet.direct(
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.SKYROOT_WOODLAND_LARGE_GREEN_SKYROOT_TREES_PLACEMENT),
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.SKYROOT_WOODLAND_GREEN_SKYROOT_PINES_PLACEMENT),
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.SKYROOT_WOODLAND_GREEN_HOOKED_SKYROOTS_PLACEMENT)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_FOREST_TREES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_FOREST_TREES),
                HolderSet.direct(
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.SKYROOT_FOREST_LARGE_GREEN_SKYROOT_TREES_PLACEMENT),
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.SKYROOT_FOREST_GREEN_SKYROOT_PINES_PLACEMENT),
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.SKYROOT_FOREST_GREEN_HOOKED_SKYROOTS_PLACEMENT)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_CONTINUUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_CONTINUUM_ORE),
                HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(GenesisPlacedFeatures.ORE_CONTINUUM_PLACEMENT)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(COST_CARRION_SPROUT, new AddMobChargeBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_CARRION_SPROUT), GenesisEntityTypes.CARRION_SPROUT.get(), 0.4, 0.11));

        context.register(COST_DARK_SWET, new AddMobChargeBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_DARK_SWET), GenesisEntityTypes.DARK_SWET.get(), 0.5, 0.1));

        context.register(COST_TEMPEST, new AddMobChargeBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_TEMPEST), GenesisEntityTypes.TEMPEST.get(), 0.6, 0.16));

        context.register(SPAWN_CARRION_SPROUT, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_CARRION_SPROUT),
                List.of(new MobSpawnSettings.SpawnerData(GenesisEntityTypes.CARRION_SPROUT.get(), 6, 1, 1))));

        context.register(SPAWN_DARK_SWET, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_DARK_SWET),
                List.of(new MobSpawnSettings.SpawnerData(GenesisEntityTypes.DARK_SWET.get(), 5, 1, 1))));

        context.register(SPAWN_TEMPEST, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(GenesisTags.Biomes.HAS_TEMPEST),
                List.of(new MobSpawnSettings.SpawnerData(GenesisEntityTypes.TEMPEST.get(), 16, 1, 1))));
    }
}
