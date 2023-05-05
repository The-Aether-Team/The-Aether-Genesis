package com.aetherteam.aether_genesis.data.resources;

import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether.block.AetherBlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;

public class GenesisFeatureStates {
    public static final BlockState GREEN_AERCLOUD = GenesisBlocks.GREEN_AERCLOUD.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState PURPLE_AERCLOUD = GenesisBlocks.PURPLE_AERCLOUD.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState STORM_AERCLOUD = GenesisBlocks.STORM_AERCLOUD.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);

    public static final BlockState SKYROOT_LOG_WALL = GenesisBlocks.SKYROOT_LOG_WALL.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);

    public static final BlockState BLUE_SKYROOT_LEAVES = GenesisBlocks.BLUE_SKYROOT_LEAVES.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState DARK_BLUE_SKYROOT_LEAVES = GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState PURPLE_CRYSTAL_LEAVES = GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState PURPLE_CRYSTAL_FRUIT_LEAVES = GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
}
