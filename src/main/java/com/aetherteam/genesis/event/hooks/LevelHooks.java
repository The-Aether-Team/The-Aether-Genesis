package com.aetherteam.genesis.event.hooks;

import com.aetherteam.genesis.data.resources.registries.GenesisConfiguredFeatures;
import com.aetherteam.aether.data.resources.registries.AetherConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class LevelHooks {
    public static ResourceKey<ConfiguredFeature<?, ?>> modifyGrownSapling(LevelAccessor levelAccessor, RandomSource random, Holder<ConfiguredFeature<?, ?>> feature) {
        if (feature != null) {
            int chance = random.nextInt(10);
            if (feature.is(AetherConfiguredFeatures.SKYROOT_TREE_CONFIGURATION)) {
                if (chance == 0) {
                    return GenesisConfiguredFeatures.LARGE_GREEN_SKYROOT_TREE_CONFIGURATION;
                } else if (chance == 1) {
                    return GenesisConfiguredFeatures.GREEN_SKYROOT_PINE_CONFIGURATION;
                } else if (chance == 2) {
                    return GenesisConfiguredFeatures.GREEN_HOOKED_SKYROOT_CONFIGURATION;
                }
            }
        }
        return null;
    }
}
