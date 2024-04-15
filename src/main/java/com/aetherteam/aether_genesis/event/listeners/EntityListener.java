package com.aetherteam.aether_genesis.event.listeners;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.capability.zephyr.ZephyrColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

@Mod.EventBusSubscriber
public class EntityListener {
    @SubscribeEvent
    public static void onJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (GenesisConfig.COMMON.tan_zephyr_variation.get()) {
            if (entity.getType() == AetherEntityTypes.ZEPHYR.get() && entity instanceof Zephyr zephyr) {
                if (zephyr.getRandom().nextInt(10) == 0) {
                    ZephyrColor.get(zephyr).ifPresent((zephyrColor) -> zephyrColor.setTan(true));
                    zephyr.refreshDimensions();
                }
            }
        }
    }

    @SubscribeEvent
    public static void onSize(EntityEvent.Size event) {
        Entity entity = event.getEntity();
        if (entity.getType() == AetherEntityTypes.ZEPHYR.get() && entity instanceof Zephyr zephyr) {
            ZephyrColor.get(zephyr).ifPresent((zephyrColor) -> {
                if (zephyrColor.isTan()) {
                    event.setNewSize(EntityDimensions.fixed(3.5F, 2.25F), true);
                }
            });
        }
    }

    @SubscribeEvent
    public static void finalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        LivingEntity zephyr = event.getEntity();
        if (zephyr.getType() == AetherEntityTypes.ZEPHYR.get() && !zephyr.level().isClientSide()) {
            if (zephyr.level().isNight()) {
                event.setSpawnCancelled(true);
            }
        }
    }

    @SubscribeEvent
    public static void allowDespawn(MobSpawnEvent.AllowDespawn event) {
        LivingEntity zephyr = event.getEntity();
        if (zephyr.getType() == AetherEntityTypes.ZEPHYR.get() && !zephyr.level().isClientSide() && zephyr.level().isNight() && zephyr.getRandom().nextInt(100) == 0) {
            event.setResult(Event.Result.ALLOW);
        }
    }
}