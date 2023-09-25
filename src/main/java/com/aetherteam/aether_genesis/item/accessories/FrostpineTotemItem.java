package com.aetherteam.aether_genesis.item.accessories;

import com.aetherteam.aether_genesis.entity.companion.FrostpineTotem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Supplier;

public class FrostpineTotemItem extends CompanionItem<FrostpineTotem> {
    private static final MobEffectInstance TOTEM_NIGHT_VISION_EFFECT = new MobEffectInstance(MobEffects.NIGHT_VISION, MobEffectInstance.INFINITE_DURATION, 0, false, false, false);

    public FrostpineTotemItem(Supplier<EntityType<FrostpineTotem>> companionType, Properties properties) {
        super(companionType, properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (!livingEntity.level().isClientSide()) {
            boolean noEffect = true;
            for (MobEffectInstance effect : livingEntity.getActiveEffects()) {
                if (effect.toString().equals(TOTEM_NIGHT_VISION_EFFECT.toString())) {
                    noEffect = false;
                    break;
                }
            }
            if (noEffect) {
                MobEffectInstance nightVisionEffect = new MobEffectInstance(TOTEM_NIGHT_VISION_EFFECT);
                livingEntity.addEffect(nightVisionEffect, livingEntity);
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (!livingEntity.level().isClientSide()) {
            boolean hasEffect = false;
            for (MobEffectInstance effect : livingEntity.getActiveEffects()) {
                if (effect.toString().equals(TOTEM_NIGHT_VISION_EFFECT.toString())) {
                    hasEffect = true;
                    break;
                }
            }
            if (hasEffect) {
                livingEntity.removeEffect(TOTEM_NIGHT_VISION_EFFECT.getEffect());
            }
        }
        super.onUnequip(slotContext, newStack, stack);
    }

    public static MobEffectInstance getTotemNightVisionEffect() {
        return TOTEM_NIGHT_VISION_EFFECT;
    }
}
