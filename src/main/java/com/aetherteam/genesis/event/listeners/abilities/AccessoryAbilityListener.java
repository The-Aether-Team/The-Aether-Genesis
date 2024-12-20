package com.aetherteam.genesis.event.listeners.abilities;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.event.hooks.AbilityHooks;
import com.aetherteam.genesis.event.listeners.capability.GenesisPlayerListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

public class AccessoryAbilityListener {

    public static void listen(IEventBus bus) {
        bus.addListener(AccessoryAbilityListener::entityJoinLevel);
        bus.addListener(AccessoryAbilityListener::entityDie);
    }

    /**
     * @see AbilityHooks.AccessoryHooks#convertSnowball(Entity, Level)
     */
    public static void entityJoinLevel(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();
        if (AbilityHooks.AccessoryHooks.convertSnowball(entity, level)) {
            event.setCanceled(true);
        }
    }

    public static void entityDie(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (AbilityHooks.AccessoryHooks.saveFromDeath(entity)) {
            event.setCanceled(true);
        }
    }
}
