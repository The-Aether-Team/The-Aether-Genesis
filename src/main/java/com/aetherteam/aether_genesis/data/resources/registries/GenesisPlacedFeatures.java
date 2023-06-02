package com.aetherteam.aether_genesis.data.resources.registries;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.resources.builders.AetherPlacedFeatureBuilders;
import com.aetherteam.aether.world.placementmodifier.ImprovedLayerPlacementModifier;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.data.resources.builders.GenesisPlacedFeatureBuilders;
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

    public static final ResourceKey<PlacedFeature> SKYROOT_MEADOW_BLUE_SKYROOT_TREES_PLACEMENT = createKey("skyroot_meadow_blue_skyroot_trees");
    public static final ResourceKey<PlacedFeature> SKYROOT_MEADOW_LARGE_GREEN_SKYROOT_TREES_PLACEMENT = createKey("skyroot_meadow_large_green_skyroot_trees");

    public static final ResourceKey<PlacedFeature> SKYROOT_GROVE_BLUE_SKYROOT_TREES_PLACEMENT = createKey("skyroot_grove_blue_skyroot_trees");
    public static final ResourceKey<PlacedFeature> SKYROOT_GROVE_LARGE_GREEN_SKYROOT_TREES_PLACEMENT = createKey("skyroot_grove_large_green_skyroot_trees");
    public static final ResourceKey<PlacedFeature> SKYROOT_GROVE_BLUE_SKYROOT_PINES_PLACEMENT = createKey("skyroot_grove_blue_skyroot_pines");
    public static final ResourceKey<PlacedFeature> SKYROOT_GROVE_GREEN_SKYROOT_PINES_PLACEMENT = createKey("skyroot_grove_green_skyroot_pines");

    public static final ResourceKey<PlacedFeature> SKYROOT_WOODLAND_BLUE_SKYROOT_TREES_PLACEMENT = createKey("skyroot_woodland_blue_skyroot_trees");
    public static final ResourceKey<PlacedFeature> SKYROOT_WOODLAND_LARGE_GREEN_SKYROOT_TREES_PLACEMENT = createKey("skyroot_woodland_large_green_skyroot_trees");
    public static final ResourceKey<PlacedFeature> SKYROOT_WOODLAND_BLUE_SKYROOT_PINES_PLACEMENT = createKey("skyroot_woodland_blue_skyroot_pines");
    public static final ResourceKey<PlacedFeature> SKYROOT_WOODLAND_GREEN_SKYROOT_PINES_PLACEMENT = createKey("skyroot_woodland_green_skyroot_pines");
    public static final ResourceKey<PlacedFeature> SKYROOT_WOODLAND_DARK_BLUE_HOOKED_SKYROOTS_PLACEMENT = createKey("skyroot_woodland_dark_blue_hooked_skyroots");
    public static final ResourceKey<PlacedFeature> SKYROOT_WOODLAND_GREEN_HOOKED_SKYROOTS_PLACEMENT = createKey("skyroot_woodland_green_hooked_skyroots");
    public static final ResourceKey<PlacedFeature> SKYROOT_WOODLAND_PURPLE_CRYSTAL_TREES_PLACEMENT = createKey("skyroot_woodland_purple_crystal_trees");

    public static final ResourceKey<PlacedFeature> SKYROOT_FOREST_BLUE_SKYROOT_TREES_PLACEMENT = createKey("skyroot_forest_blue_skyroot_trees");
    public static final ResourceKey<PlacedFeature> SKYROOT_FOREST_LARGE_GREEN_SKYROOT_TREES_PLACEMENT = createKey("skyroot_forest_large_green_skyroot_trees");
    public static final ResourceKey<PlacedFeature> SKYROOT_FOREST_BLUE_SKYROOT_PINES_PLACEMENT = createKey("skyroot_forest_blue_skyroot_pines");
    public static final ResourceKey<PlacedFeature> SKYROOT_FOREST_GREEN_SKYROOT_PINES_PLACEMENT = createKey("skyroot_forest_green_skyroot_pines");
    public static final ResourceKey<PlacedFeature> SKYROOT_FOREST_DARK_BLUE_HOOKED_SKYROOTS_PLACEMENT = createKey("skyroot_forest_dark_blue_hooked_skyroots");
    public static final ResourceKey<PlacedFeature> SKYROOT_FOREST_GREEN_HOOKED_SKYROOTS_PLACEMENT = createKey("skyroot_forest_green_hooked_skyroots");
    public static final ResourceKey<PlacedFeature> SKYROOT_FOREST_PURPLE_CRYSTAL_TREES_PLACEMENT = createKey("skyroot_forest_purple_crystal_trees");

    public static final ResourceKey<PlacedFeature> ORE_CONTINUUM_PLACEMENT = createKey("ore_continuum");

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Genesis.MODID, name));
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

        register(context, SKYROOT_MEADOW_BLUE_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(2), GenesisBlocks.BLUE_SKYROOT_SAPLING.get()));
        register(context, SKYROOT_MEADOW_LARGE_GREEN_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(4), AetherBlocks.SKYROOT_SAPLING.get()));

        register(context, SKYROOT_GROVE_BLUE_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1), GenesisBlocks.BLUE_SKYROOT_SAPLING.get()));
        register(context, SKYROOT_GROVE_LARGE_GREEN_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(3), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_GROVE_BLUE_SKYROOT_PINES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_PINE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(2), GenesisBlocks.BLUE_SKYROOT_SAPLING.get()));
        register(context, SKYROOT_GROVE_GREEN_SKYROOT_PINES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_SKYROOT_PINE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(1), AetherBlocks.SKYROOT_SAPLING.get()));

        register(context, SKYROOT_WOODLAND_BLUE_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(3, 0.1F, 1), GenesisBlocks.BLUE_SKYROOT_SAPLING.get()));
        register(context, SKYROOT_WOODLAND_LARGE_GREEN_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(3), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_WOODLAND_BLUE_SKYROOT_PINES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_PINE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1), GenesisBlocks.BLUE_SKYROOT_SAPLING.get()));
        register(context, SKYROOT_WOODLAND_GREEN_SKYROOT_PINES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_SKYROOT_PINE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(2, 0.1F, 1), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_WOODLAND_DARK_BLUE_HOOKED_SKYROOTS_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.DARK_BLUE_HOOKED_SKYROOT_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(4), GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get()));
        register(context, SKYROOT_WOODLAND_GREEN_HOOKED_SKYROOTS_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_HOOKED_SKYROOT_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(3), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_WOODLAND_PURPLE_CRYSTAL_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.PURPLE_CRYSTAL_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(5), AetherBlocks.SKYROOT_SAPLING.get()));

        register(context, SKYROOT_FOREST_BLUE_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(4, 0.1F, 1), GenesisBlocks.BLUE_SKYROOT_SAPLING.get()));
        register(context, SKYROOT_FOREST_LARGE_GREEN_SKYROOT_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(3), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_FOREST_BLUE_SKYROOT_PINES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_PINE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(2, 0.1F, 1), GenesisBlocks.BLUE_SKYROOT_SAPLING.get()));
        register(context, SKYROOT_FOREST_GREEN_SKYROOT_PINES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_SKYROOT_PINE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(3, 0.1F, 1), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_FOREST_DARK_BLUE_HOOKED_SKYROOTS_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.DARK_BLUE_HOOKED_SKYROOT_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(4), GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get()));
        register(context, SKYROOT_FOREST_GREEN_HOOKED_SKYROOTS_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_HOOKED_SKYROOT_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(3), AetherBlocks.SKYROOT_SAPLING.get()));
        register(context, SKYROOT_FOREST_PURPLE_CRYSTAL_TREES_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.PURPLE_CRYSTAL_TREE_CONFIGURATION),
                GenesisPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(5), AetherBlocks.SKYROOT_SAPLING.get()));

        register(context, ORE_CONTINUUM_PLACEMENT, configuredFeatures.getOrThrow(GenesisConfiguredFeatures.ORE_CONTINUUM_CONFIGURATION),
                AetherPlacedFeatureBuilders.commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(128))));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
