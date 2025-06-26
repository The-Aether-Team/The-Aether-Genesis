package com.aetherteam.genesis.event.listeners;

import com.aetherteam.genesis.event.hooks.AbilityHooks;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class WeaponAbilityListener {

    public static void listen(IEventBus bus) {
        bus.addListener(WeaponAbilityListener::onDartHurt);
    }

    /**
     * @see AbilityHooks.WeaponHooks#stickDart(LivingEntity, DamageSource)
     */
    public static void onDartHurt(LivingDamageEvent.Pre event) {
        LivingEntity livingEntity = event.getEntity();
        DamageSource damageSource = event.getSource();
        AbilityHooks.WeaponHooks.stickDart(livingEntity, damageSource);
    }
}