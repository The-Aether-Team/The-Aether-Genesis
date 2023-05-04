package com.aetherteam.genesis.world.treegrower;

import com.aetherteam.genesis.data.resources.registries.GenesisConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class DarkBlueSkyrootTree extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        return GenesisConfiguredFeatures.DARK_BLUE_HOOKED_SKYROOT_CONFIGURATION;
    }
}
