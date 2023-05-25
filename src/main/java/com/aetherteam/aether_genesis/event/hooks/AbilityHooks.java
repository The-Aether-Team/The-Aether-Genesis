package com.aetherteam.aether_genesis.event.hooks;

import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.capability.player.GenesisPlayer;
import com.aetherteam.aether_genesis.entity.PhoenixDart;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

    public static class WeaponHooks {
        public static void stickDart(LivingEntity entity, DamageSource source) {
            if (entity instanceof Player player && !player.getLevel().isClientSide()) {
                Entity sourceEntity = source.getDirectEntity();
                if (sourceEntity instanceof PhoenixDart) {
                    GenesisPlayer.get(player).ifPresent(genesisPlayer -> genesisPlayer.setPhoenixDartCount(genesisPlayer.getPhoenixDartCount() + 1));
                }
            }
        }
    }
}
