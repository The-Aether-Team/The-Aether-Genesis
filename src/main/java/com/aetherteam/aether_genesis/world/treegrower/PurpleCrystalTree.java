package com.aetherteam.aether_genesis.world.treegrower;

import com.aetherteam.aether_genesis.data.resources.registries.GenesisConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class PurpleCrystalTree extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        return GenesisConfiguredFeatures.FRUITLESS_PURPLE_CRYSTAL_TREE_CONFIGURATION;
    }
}