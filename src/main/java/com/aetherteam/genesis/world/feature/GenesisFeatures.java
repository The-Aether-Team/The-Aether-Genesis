package com.aetherteam.genesis.world.feature;

import com.aetherteam.genesis.AetherGenesis;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, AetherGenesis.MODID);

    public static DeferredHolder<Feature<?>, Feature<SimpleBlockConfiguration>> ORANGE_TREE = FEATURES.register("orange_tree", () -> new OrangeTreeFeature(SimpleBlockConfiguration.CODEC));
}
