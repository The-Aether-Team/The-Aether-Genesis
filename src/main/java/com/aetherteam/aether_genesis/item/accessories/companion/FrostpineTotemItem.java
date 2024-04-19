package com.aetherteam.aether_genesis.item.accessories.companion;

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

    /**
     * Applies a night vision effect to players if they do not already have it.
     *
     * @param slotContext The {@link SlotContext} of the Curio.
     * @param stack       The Curio {@link ItemStack}.
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (!livingEntity.level().isClientSide()) {
            boolean noEffect = true;
            for (MobEffectInstance effect : livingEntity.getActiveEffects()) {
                if (effect.toString().equals(TOTEM_NIGHT_VISION_EFFECT.toString())) { // Checks if the player already has the effect.
                    noEffect = false;
                    break;
                }
            }
            if (noEffect) { // Applies the effect.
                MobEffectInstance nightVisionEffect = new MobEffectInstance(TOTEM_NIGHT_VISION_EFFECT);
                livingEntity.addEffect(nightVisionEffect, livingEntity);
            }
        }
    }

    /**
     * Removes the night vision effect when the Frostpine Totem is unequipped.
     *
     * @param slotContext The {@link SlotContext} of the Curio.
     * @param newStack    The new {@link ItemStack} in the slot.
     * @param stack       The {@link ItemStack} of the Curio.
     */
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

    /**
     * @return The night vision {@link MobEffectInstance}. It is infinite and does not display visible particles.
     */
    public static MobEffectInstance getTotemNightVisionEffect() {
        return TOTEM_NIGHT_VISION_EFFECT;
    }
}
