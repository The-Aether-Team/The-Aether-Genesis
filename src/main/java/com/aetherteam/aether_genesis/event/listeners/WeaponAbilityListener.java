package com.aetherteam.aether_genesis.event.listeners;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether_genesis.event.hooks.AbilityHooks;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;

@Mod.EventBusSubscriber(modid = Aether.MODID)
public class WeaponAbilityListener {
    /**
     * @see AbilityHooks.WeaponHooks#stickDart(LivingEntity, DamageSource)
     */
    @SubscribeEvent
    public static void onDartHurt(LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntity();
        DamageSource damageSource = event.getSource();
        if (!event.isCanceled()) {
            AbilityHooks.WeaponHooks.stickDart(livingEntity, damageSource);
        }
    }
}