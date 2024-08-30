package com.aetherteam.genesis.world.treegrower;

import com.aetherteam.aether.data.resources.registries.AetherConfiguredFeatures;
import com.aetherteam.genesis.data.resources.registries.GenesisConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class GenesisTreeGrowers {
    public static final TreeGrower BLUE_SKYROOT = new TreeGrower(
            "blue_skyroot",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(GenesisConfiguredFeatures.BLUE_SKYROOT_TREE_CONFIGURATION),
            Optional.of(GenesisConfiguredFeatures.BLUE_SKYROOT_PINE_CONFIGURATION),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower DARK_BLUE_SKYROOT = new TreeGrower(
            "dark_blue_skyroot",
            Optional.empty(),
            Optional.of(GenesisConfiguredFeatures.DARK_BLUE_HOOKED_SKYROOT_CONFIGURATION),
            Optional.empty()
    );

    public static final TreeGrower CRYSTAL_TREE = new TreeGrower(
            "crystal_tree",
            Optional.empty(),
            Optional.of(AetherConfiguredFeatures.CRYSTAL_TREE_CONFIGURATION),
            Optional.empty()
    );

    public static final TreeGrower PURPLE_CRYSTAL_TREE = new TreeGrower(
            "purple_crystal_tree",
            Optional.empty(),
            Optional.of(GenesisConfiguredFeatures.FRUITLESS_PURPLE_CRYSTAL_TREE_CONFIGURATION),
            Optional.empty()
    );
}
