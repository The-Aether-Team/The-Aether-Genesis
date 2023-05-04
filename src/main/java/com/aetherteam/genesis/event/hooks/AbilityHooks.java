package com.aetherteam.genesis.event.hooks;

import com.aetherteam.genesis.block.GenesisBlocks;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.Map;

public class AbilityHooks {
    public static class ToolHooks {
        public static final Map<Block, Block> STRIPPABLES = (new ImmutableMap.Builder<Block, Block>())
                .put(GenesisBlocks.SKYROOT_LOG_WALL.get(), GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get())
                .put(GenesisBlocks.SKYROOT_WOOD_WALL.get(), GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get())
                .build();

        public static BlockState setupToolActions(BlockState old, ToolAction action) {
            Block oldBlock = old.getBlock();
            if (action == ToolActions.AXE_STRIP) {
                if (STRIPPABLES.containsKey(oldBlock)) {
                    return STRIPPABLES.get(oldBlock).withPropertiesOf(old);
                }
            }
            return old;
        }
    }
}
