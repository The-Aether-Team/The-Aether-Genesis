package com.aetherteam.genesis.data.resources.registries;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.resources.builders.AetherPlacedFeatureBuilders;
import com.aetherteam.aether.world.placementmodifier.ImprovedLayerPlacementModifier;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.data.resources.builders.GenesisPlacedFeatureBuilders;
import com.aetherteam.nitrogen.data.resources.builders.NitrogenPlacedFeatureBuilders;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class GenesisPlacedFeatures {
    public static final ResourceKey<PlacedFeature> GREEN_AERCLOUD_4_PLACEMENT = createKey("green_aercloud_4");
    public static final ResourceKey<PlacedFeature> GREEN_AERCLOUD_8_PLACEMENT = createKey("green_aercloud_8");
    public static final ResourceKey<PlacedFeature> PURPLE_AERCLOUD_PLACEMENT = createKey("purple_aercloud");
    public static final ResourceKey<PlacedFeature> STORM_AERCLOUD_PLACEMENT = createKey("storm_aercloud");

    public static final ResourceKey<PlacedFeature> ORANGE_TREE_PATCH_PLACEMENT = createKey("orange_tree_patch");
    public static final ResourceKey<PlacedFeature> SKYROOT_GROVE_LARGE_GREEN_SKYROOT_TREES_PLACEMENT = createKey("skyroot_grove_large_green_skyroot_trees"); // Rare Large Green Skyroot Tree

    public static final ResourceKey<PlacedFeature> SKYROOT_WOODLAND_LARGE_GREEN_SKYROOT_TREES_PLACEMENT = createKey("skyroot_woodland_large_green_skyroot_trees"); // Occasional Large Green Skyroot Tree
    public static final ResourceKey<PlacedFeature> SKYROOT_WOODLAND_GREEN_SKYROOT_PINES_PLACEMENT = createKey("skyroot_woodland_green_skyroot_pines"); // Occasional Green Skyroot Pine
    public static final ResourceKey<PlacedFeature> SKYROOT_WOODLAND_GREEN_HOOKED_SKYROOTS_PLACEMENT = createKey("skyroot_woodland_green_hooked_skyroots"); // Very Rare Green Hooked Skyroot

    public static final ResourceKey<PlacedFeature> SKYROOT_FOREST_LARGE_GREEN_SKYROOT_TREES_PLACEMENT = createKey("skyroot_forest_large_green_skyroot_trees"); // Occasional Large Green Skyroot Tree
    public static final ResourceKey<PlacedFeature> SKYROOT_FOREST_GREEN_SKYROOT_PINES_PLACEMENT = createKey("skyroot_forest_green_skyroot_pines"); // Occasional Green Skyroot Pine
    public static final ResourceKey<PlacedFeature> SKYROOT_FOREST_GREEN_HOOKED_SKYROOTS_PLACEMENT = createKey("skyroot_forest_green_hooked_skyroots"); // Very Rare Green Hooked Skyroot

    public static final ResourceKey<PlacedFeature> VIBRANT_MEADOW_TREES_PLACEMENT = createKey("vibrant_meadow_trees");
    public static final ResourceKey<PlacedFeature> VIBRANT_GROVE_TREES_PLACEMENT = createKey("vibrant_grove_trees");
    public static final ResourceKey<PlacedFeature> VIBRANT_WOODLAND_TREES_PLACEMENT = createKey("vibrant_woodland_trees");
    public static final ResourceKey<PlacedFeature> VIBRANT_FOREST_TREES_PLACEMENT = createKey("vibrant_forest_trees");

    public static final ResourceKey<PlacedFeature> ORE_CONTINUUM_PLACEMENT = createKey("ore_continuum");

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(AetherGenesis.MODID, name));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, GREEN_AERCLOUD_4_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_AERCLOUD_4_CONFIGURATION), AetherPlacedFeatureBuilders.aercloudPlacement(0, 32, 24));
        register(context, GREEN_AERCLOUD_8_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_AERCLOUD_8_CONFIGURATION), AetherPlacedFeatureBuilders.aercloudPlacement(32, 64, 7));
        register(context, PURPLE_AERCLOUD_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.PURPLE_AERCLOUD_CONFIGURATION), AetherPlacedFeatureBuilders.aercloudPlacement(0, 32, 24));
        register(context, STORM_AERCLOUD_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.STORM_AERCLOUD_CONFIGURATION), AetherPlacedFeatureBuilders.aercloudPlacement(0, 32, 24));

        register(context, ORANGE_TREE_PATCH_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.ORANGE_TREE_PATCH_CONFIGURATION),
                RarityFilter.onAverageOnceEvery(16),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                BiomeFilter.biome());

        register(context, SKYROOT_GROVE_LARGE_GREEN_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(13), AetherBlocks.SKYROOT_SAPLING.get()));

        register(context, SKYROOT_WOODLAND_LARGE_GREEN_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(10), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_WOODLAND_GREEN_SKYROOT_PINES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_SKYROOT_PINE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(8), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_WOODLAND_GREEN_HOOKED_SKYROOTS_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_HOOKED_SKYROOT_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(16), AetherBlocks.SKYROOT_SAPLING.get()));

        register(context, SKYROOT_FOREST_LARGE_GREEN_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(10), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_FOREST_GREEN_SKYROOT_PINES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_SKYROOT_PINE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(8), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_FOREST_GREEN_HOOKED_SKYROOTS_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_HOOKED_SKYROOT_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(16), AetherBlocks.SKYROOT_SAPLING.get()));

        register(context, VIBRANT_MEADOW_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.TREES_VIBRANT_MEADOW_CONFIGURATION), AetherPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(1)));
        register(context, VIBRANT_GROVE_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.TREES_VIBRANT_GROVE_CONFIGURATION), AetherPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(2, 0.1F, 1)));
        register(context, VIBRANT_WOODLAND_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.TREES_VIBRANT_WOODLAND_CONFIGURATION), AetherPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
        register(context, VIBRANT_FOREST_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.TREES_VIBRANT_FOREST_CONFIGURATION), AetherPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(6, 0.1F, 1)));

        register(context, ORE_CONTINUUM_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.ORE_CONTINUUM_CONFIGURATION),
                NitrogenPlacedFeatureBuilders.commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(128))));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
