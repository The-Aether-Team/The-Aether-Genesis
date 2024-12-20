package com.aetherteam.genesis.event.listeners;

import com.aetherteam.genesis.event.hooks.EntityHooks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobDespawnEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import org.apache.commons.lang3.tuple.Pair;

public class EntityListener {

    public static void listen(IEventBus bus) {
        bus.addListener(EntityListener::onJoin);
        bus.addListener(EntityListener::onSize);
        bus.addListener(EntityListener::finalizeSpawn);
        bus.addListener(EntityListener::allowDespawn);
    }

    /**
     * @see EntityHooks#setZephyrColor(Entity)
     */
    @SubscribeEvent
    public static void onJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        EntityHooks.setZephyrColor(entity);
    }

    /**
     * @see EntityHooks#determineZephyrSize(Entity)
     */
    @SubscribeEvent
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
    @SubscribeEvent
    public static void finalizeSpawn(MobSpawnEvent.SpawnPlacementCheck event) {
        if (EntityHooks.shouldStopZephyrSpawn(event.getEntityType(), event.getLevel())) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }
    }

    /**
     * @see EntityHooks#shouldZephyrDespawn(LivingEntity)
     */
    @SubscribeEvent
    public static void allowDespawn(MobDespawnEvent event) {
        LivingEntity zephyr = event.getEntity();
        if (EntityHooks.shouldZephyrDespawn(zephyr)) {
            event.setResult(MobDespawnEvent.Result.ALLOW);
        }
    }
}