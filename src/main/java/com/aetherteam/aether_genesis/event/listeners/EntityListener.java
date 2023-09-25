package com.aetherteam.aether_genesis.event.listeners;

import com.aetherteam.aether.entity.AetherEntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityListener {
    @SubscribeEvent
    public static void disableSpawn(MobSpawnEvent.FinalizeSpawn event){
        LivingEntity zephyr = event.getEntity();
        if (zephyr.getType() == AetherEntityTypes.ZEPHYR.get() && !zephyr.level().isClientSide() && zephyr.level().isNight()) {
            event.setSpawnCancelled(true);
        }
    }

    @SubscribeEvent
    public static void forceDespawn(MobSpawnEvent.AllowDespawn event) {
        LivingEntity zephyr = event.getEntity();
        if (zephyr.getType() == AetherEntityTypes.ZEPHYR.get() && !zephyr.level().isClientSide() && zephyr.level().isNight() && zephyr.getRandom().nextInt(100) == 0) {
            event.setResult(Event.Result.ALLOW);
        }
    }
}