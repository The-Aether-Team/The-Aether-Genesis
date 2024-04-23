package com.aetherteam.aether_genesis.event.listeners;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.event.hooks.EntityHooks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class EntityListener {
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
        Pair<EntityDimensions, Boolean> size = EntityHooks.determineZephyrSize(entity);
        if (size != null) {
            event.setNewSize(size.getLeft(), size.getRight());
        }
    }

    /**
     * @see EntityHooks#shouldStopZephyrSpawn(LivingEntity)
     */
    @SubscribeEvent
    public static void finalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        LivingEntity zephyr = event.getEntity();
        if (EntityHooks.shouldStopZephyrSpawn(zephyr)) {
            event.setSpawnCancelled(true);
        }
    }

    /**
     * @see EntityHooks#shouldZephyrDespawn(LivingEntity)
     */
    @SubscribeEvent
    public static void allowDespawn(MobSpawnEvent.AllowDespawn event) {
        LivingEntity zephyr = event.getEntity();
        if (EntityHooks.shouldZephyrDespawn(zephyr)) {
            event.setResult(Event.Result.ALLOW);
        }
    }
}