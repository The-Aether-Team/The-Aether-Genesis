package com.aetherteam.aether_genesis.block.natural;

import com.aetherteam.aether.mixin.mixins.common.accessor.SpreadingSnowyDirtBlockAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class EnchantedGrassBlock extends GrassBlock {
    public EnchantedGrassBlock(Properties properties) {
        super(properties);
    }

    /**
     * Based on part of {@link net.minecraft.world.level.block.SpreadingSnowyDirtBlock#randomTick(BlockState, ServerLevel, BlockPos, RandomSource)}.<br><br>
     * Warning for "deprecation" is suppressed due to being copied from what Forge does.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!SpreadingSnowyDirtBlockAccessor.callCanBeGrass(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
        }
    }
}