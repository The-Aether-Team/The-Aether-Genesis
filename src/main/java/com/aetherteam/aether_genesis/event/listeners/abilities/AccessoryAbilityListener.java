package com.aetherteam.aether_genesis.event.listeners.abilities;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.event.hooks.AbilityHooks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class AccessoryAbilityListener {
    /**
     * @see AbilityHooks.AccessoryHooks#convertSnowball(Entity, Level)
     */
    @SubscribeEvent
    public static void entityJoinLevel(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();
        if (AbilityHooks.AccessoryHooks.convertSnowball(entity, level)) {
            event.setCanceled(true);
        }
    }
}
