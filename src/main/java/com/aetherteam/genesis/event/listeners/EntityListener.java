package com.aetherteam.genesis.event.listeners;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.blockentity.AltarBlockEntity;
import com.aetherteam.genesis.GenesisConfig;
import com.aetherteam.genesis.event.hooks.EntityHooks;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobDespawnEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.apache.commons.lang3.tuple.Pair;

public class EntityListener {

    public static void listen(IEventBus bus) {
        bus.addListener(EntityListener::onJoin);
        bus.addListener(EntityListener::onSize);
        bus.addListener(EntityListener::finalizeSpawn);
        bus.addListener(EntityListener::allowDespawn);
        bus.addListener(EntityListener::playerInteractBlock);
    }

    /**
     * @see EntityHooks#setZephyrColor(Entity)
     */
    public static void onJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        EntityHooks.setZephyrColor(entity);
    }

    /**
     * @see EntityHooks#determineZephyrSize(Entity)
     */
    public static void onSize(EntityEvent.Size event) {
        Entity entity = event.getEntity();
        EntityDimensions size = EntityHooks.determineZephyrSize(entity);
        if (size != null) {
            event.setNewSize(size);
        }
    }

    /**
     * @see EntityHooks#shouldStopZephyrSpawn(LivingEntity)
     */
    public static void finalizeSpawn(MobSpawnEvent.SpawnPlacementCheck event) {
        if (EntityHooks.shouldStopZephyrSpawn(event.getEntityType(), event.getLevel())) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }
    }

    /**
     * @see EntityHooks#shouldZephyrDespawn(LivingEntity)
     */
    public static void allowDespawn(MobDespawnEvent event) {
        LivingEntity zephyr = event.getEntity();
        if (EntityHooks.shouldZephyrDespawn(zephyr)) {
            event.setResult(MobDespawnEvent.Result.ALLOW);
        }
    }

    public static void playerInteractBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        BlockState blockState = event.getLevel().getBlockState(event.getPos());
        if (GenesisConfig.STARTUP.altar_redesign.get() && blockState.is(AetherBlocks.ALTAR)) {
            ItemStack stack = player.getItemInHand(hand);
            if (!stack.isEmpty()) {
                if (level.getBlockEntity(event.getPos()) instanceof AltarBlockEntity altarBlockEntity) {
                    int i = 1;
                    if (!altarBlockEntity.canPlaceItem(i, stack)) {
                        i = 0;
                    }
                    player.swing(hand);
                    if (altarBlockEntity.getItem(i).isEmpty()) {
                        altarBlockEntity.setItem(i, stack.copyWithCount(1));
                    } else if (altarBlockEntity.getItem(i).is(stack.getItem()) && altarBlockEntity.getItem(i).getCount() < altarBlockEntity.getItem(i).getMaxStackSize()) {
                        altarBlockEntity.getItem(i).setCount(altarBlockEntity.getItem(i).getCount() + 1);
                    }
                    stack.shrink(1);
                    event.setUseBlock(TriState.FALSE);
                    event.setUseItem(TriState.FALSE);
                }
            }
        }
    }
}