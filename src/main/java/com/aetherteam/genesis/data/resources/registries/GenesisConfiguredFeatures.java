package com.aetherteam.genesis.data.resources.registries;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.resources.AetherFeatureRules;
import com.aetherteam.aether.data.resources.AetherFeatureStates;
import com.aetherteam.aether.data.resources.builders.AetherConfiguredFeatureBuilders;
import com.aetherteam.aether.data.resources.registries.AetherConfiguredFeatures;
import com.aetherteam.aether.world.configuration.AercloudConfiguration;
import com.aetherteam.aether.world.feature.AetherFeatures;
import com.aetherteam.aether.world.foliageplacer.GoldenOakFoliagePlacer;
import com.aetherteam.aether.world.trunkplacer.GoldenOakTrunkPlacer;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.block.natural.OrangeTreeBlock;
import com.aetherteam.genesis.block.natural.PurpleAercloudBlock;
import com.aetherteam.genesis.data.resources.GenesisFeatureStates;
import com.aetherteam.genesis.world.feature.GenesisFeatures;
import com.aetherteam.genesis.world.treedecorator.TrunkDecorator;
import com.aetherteam.genesis.world.trunkplacer.SkinnyHookedTrunkPlacer;
import com.aetherteam.nitrogen.world.foliageplacer.AetherPineFoliagePlacer;
import com.aetherteam.nitrogen.world.foliageplacer.HookedFoliagePlacer;
import com.aetherteam.nitrogen.world.trunkplacer.HookedTrunkPlacer;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class GenesisConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_AERCLOUD_4_CONFIGURATION = createKey("green_aercloud_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_AERCLOUD_8_CONFIGURATION = createKey("green_aercloud_8");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_AERCLOUD_CONFIGURATION = createKey("purple_aercloud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STORM_AERCLOUD_CONFIGURATION = createKey("storm_aercloud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_TREE_PATCH_CONFIGURATION = createKey("orange_tree_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_GREEN_SKYROOT_TREE_CONFIGURATION = createKey("large_green_skyroot_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SKYROOT_TREE_CONFIGURATION = createKey("blue_skyroot_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_SKYROOT_PINE_CONFIGURATION = createKey("green_skyroot_pine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_SKYROOT_PINE_CONFIGURATION = createKey("blue_skyroot_pine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_BLUE_HOOKED_SKYROOT_CONFIGURATION = createKey("dark_blue_hooked_skyroot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_HOOKED_SKYROOT_CONFIGURATION = createKey("green_hooked_skyroot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_CRYSTAL_TREE_CONFIGURATION = createKey("purple_crystal_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FRUITLESS_PURPLE_CRYSTAL_TREE_CONFIGURATION = createKey("fruitless_purple_crystal_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_VIBRANT_MEADOW_CONFIGURATION = createKey("trees_vibrant_meadow");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_VIBRANT_GROVE_CONFIGURATION = createKey("trees_vibrant_grove");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_VIBRANT_WOODLAND_CONFIGURATION = createKey("trees_vibrant_woodland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_VIBRANT_FOREST_CONFIGURATION = createKey("trees_vibrant_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CONTINUUM_CONFIGURATION = createKey("ore_continuum");

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(AetherGenesis.MODID, name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        SimpleWeightedRandomList.Builder<BlockState> purpleAercloudDirections = new SimpleWeightedRandomList.Builder<>();
        for (Direction direction : PurpleAercloudBlock.DIRECTIONS) {
            purpleAercloudDirections.add(GenesisFeatureStates.PURPLE_AERCLOUD.setValue(PurpleAercloudBlock.FACING, direction), 1);
        }
        SimpleWeightedRandomList.Builder<BlockState> orangeTrees = new SimpleWeightedRandomList.Builder<>();
        orangeTrees.add(GenesisFeatureStates.ORANGE_TREE.setValue(OrangeTreeBlock.AGE, 3), 1);
        orangeTrees.add(GenesisFeatureStates.ORANGE_TREE.setValue(OrangeTreeBlock.AGE, 4), 1);

        register(context, GREEN_AERCLOUD_4_CONFIGURATION, AetherFeatures.AERCLOUD.get(), AetherConfiguredFeatureBuilders.aercloud(4, GenesisFeatureStates.GREEN_AERCLOUD));
        register(context, GREEN_AERCLOUD_8_CONFIGURATION, AetherFeatures.AERCLOUD.get(), AetherConfiguredFeatureBuilders.aercloud(8, GenesisFeatureStates.GREEN_AERCLOUD));
        register(context, PURPLE_AERCLOUD_CONFIGURATION, AetherFeatures.AERCLOUD.get(), new AercloudConfiguration(4, new WeightedStateProvider(purpleAercloudDirections)));
        register(context, STORM_AERCLOUD_CONFIGURATION, AetherFeatures.AERCLOUD.get(), AetherConfiguredFeatureBuilders.aercloud(4, GenesisFeatureStates.STORM_AERCLOUD));
        register(context, ORANGE_TREE_PATCH_CONFIGURATION, Feature.FLOWER,
                FeatureUtils.simpleRandomPatchConfiguration(16, PlacementUtils.onlyWhenEmpty(GenesisFeatures.ORANGE_TREE.get(), new SimpleBlockConfiguration(new WeightedStateProvider(orangeTrees)))));
        register(context, LARGE_GREEN_SKYROOT_TREE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LOG),
                        new GoldenOakTrunkPlacer(10, 0, 0),
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LEAVES),
                        new GoldenOakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(7)),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
                ).ignoreVines().build());
        register(context, BLUE_SKYROOT_TREE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LOG),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.simple(GenesisFeatureStates.BLUE_SKYROOT_LEAVES),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());
        register(context, GREEN_SKYROOT_PINE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LOG),
                        new StraightTrunkPlacer(5, 5, 0),
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LEAVES),
                        new AetherPineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(2, 0, 2)
                ).ignoreVines().build());
        register(context, BLUE_SKYROOT_PINE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LOG),
                        new StraightTrunkPlacer(5, 5, 0),
                        BlockStateProvider.simple(GenesisFeatureStates.BLUE_SKYROOT_LEAVES),
                        new AetherPineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(2, 0, 2)
                ).ignoreVines().build());
        register(context, DARK_BLUE_HOOKED_SKYROOT_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LOG),
                        new HookedTrunkPlacer(8, 14, 14),
                        BlockStateProvider.simple(GenesisFeatureStates.DARK_BLUE_SKYROOT_LEAVES),
                        new HookedFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(2, 1, 4)
                ).decorators(List.of(new TrunkDecorator(BlockStateProvider.simple(GenesisFeatureStates.SKYROOT_LOG_WALL)))).ignoreVines().build());
        register(context, GREEN_HOOKED_SKYROOT_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LOG),
                        new HookedTrunkPlacer(8, 14, 14),
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LEAVES),
                        new HookedFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(2, 1, 4)
                ).decorators(List.of(new TrunkDecorator(BlockStateProvider.simple(GenesisFeatureStates.SKYROOT_LOG_WALL)))).ignoreVines().build());
        register(context, PURPLE_CRYSTAL_TREE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LOG),
                        new SkinnyHookedTrunkPlacer(8, 5, 0, BlockStateProvider.simple(GenesisFeatureStates.SKYROOT_LOG_WALL)),
                        new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(GenesisFeatureStates.PURPLE_CRYSTAL_LEAVES, 4).add(GenesisFeatureStates.PURPLE_CRYSTAL_FRUIT_LEAVES, 1).build()),
                        new HookedFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(2, 1, 4)
                ).ignoreVines().build());
        register(context, FRUITLESS_PURPLE_CRYSTAL_TREE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LOG),
                        new SkinnyHookedTrunkPlacer(8, 5, 0, BlockStateProvider.simple(GenesisFeatureStates.SKYROOT_LOG_WALL)),
                        BlockStateProvider.simple(GenesisFeatureStates.PURPLE_CRYSTAL_LEAVES),
                        new HookedFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), ConstantInt.of(2)),
                        new TwoLayersFeatureSize(2, 1, 4)
                ).ignoreVines().build());
        register(context, TREES_VIBRANT_MEADOW_CONFIGURATION, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AetherConfiguredFeatures.GOLDEN_OAK_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.GOLDEN_OAK_SAPLING.get())), 0.01F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.BLUE_SKYROOT_SAPLING.get())), 0.5F)
                ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AetherConfiguredFeatures.SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get()))));
        register(context, TREES_VIBRANT_GROVE_CONFIGURATION, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AetherConfiguredFeatures.GOLDEN_OAK_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.GOLDEN_OAK_SAPLING.get())), 0.01F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get())), 0.01F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_SKYROOT_PINE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get())), 0.2F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_PINE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.BLUE_SKYROOT_SAPLING.get())), 0.2F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.BLUE_SKYROOT_SAPLING.get())), 0.5F)
                ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AetherConfiguredFeatures.SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get()))));
        register(context, TREES_VIBRANT_WOODLAND_CONFIGURATION, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AetherConfiguredFeatures.GOLDEN_OAK_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.GOLDEN_OAK_SAPLING.get())), 0.01F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.PURPLE_CRYSTAL_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get())), 0.01F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_HOOKED_SKYROOT_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get())), 0.025F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.DARK_BLUE_HOOKED_SKYROOT_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get())), 0.025F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.FRUITLESS_PURPLE_CRYSTAL_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get())), 0.025F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get())), 0.05F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_SKYROOT_PINE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get())), 0.2F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_PINE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.BLUE_SKYROOT_SAPLING.get())), 0.2F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.BLUE_SKYROOT_SAPLING.get())), 0.5F)
                ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AetherConfiguredFeatures.SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get()))));
        register(context, TREES_VIBRANT_FOREST_CONFIGURATION, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AetherConfiguredFeatures.GOLDEN_OAK_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.GOLDEN_OAK_SAPLING.get())), 0.01F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.PURPLE_CRYSTAL_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get())), 0.01F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_HOOKED_SKYROOT_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get())), 0.025F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.DARK_BLUE_HOOKED_SKYROOT_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get())), 0.025F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.FRUITLESS_PURPLE_CRYSTAL_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get())), 0.025F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get())), 0.05F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.GREEN_SKYROOT_PINE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get())), 0.2F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_PINE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.BLUE_SKYROOT_SAPLING.get())), 0.2F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GenesisConfiguredFeatures.BLUE_SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(GenesisBlocks.BLUE_SKYROOT_SAPLING.get())), 0.5F)
                ), PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AetherConfiguredFeatures.SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get()))));
        register(context, ORE_CONTINUUM_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, GenesisFeatureStates.CONTINUUM_ORE, 4));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
