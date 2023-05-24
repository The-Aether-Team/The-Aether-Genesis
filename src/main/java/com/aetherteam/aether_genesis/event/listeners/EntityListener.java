package com.aetherteam.aether_genesis.event.listeners;

import com.aetherteam.aether.entity.AetherEntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityListener {
    @SubscribeEvent
    public static void DisabledNightSpawn(MobSpawnEvent.FinalizeSpawn event){
        LivingEntity zephyr = event.getEntity();
        if(zephyr.getType() == AetherEntityTypes.ZEPHYR.get() && !zephyr.getLevel().isClientSide && zephyr.getLevel().isNight())
            event.setSpawnCancelled(true);
    }

    @SubscribeEvent
    public static void setNightDead(LivingEvent.LivingTickEvent event){
        LivingEntity zephyr = event.getEntity();
        if(zephyr.getType() == AetherEntityTypes.ZEPHYR.get() && zephyr.getLevel().isNight() && zephyr.isAlive() && !zephyr.getLevel().isClientSide)
            event.getEntity().setHealth(0);
    }
}