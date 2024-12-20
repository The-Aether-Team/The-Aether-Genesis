package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.FrostpineTotem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class FrostpineTotemItem extends CompanionItem<FrostpineTotem> {
    private static final MobEffectInstance TOTEM_NIGHT_VISION_EFFECT = new MobEffectInstance(MobEffects.NIGHT_VISION, MobEffectInstance.INFINITE_DURATION, 0, false, false, false);

    public FrostpineTotemItem(Properties properties) {
        super(GenesisEntityTypes.FROSTPINE_TOTEM, properties);
    }

    /**
     * Applies a night vision effect to players if they do not already have it.
     *
     * @param reference The {@link SlotReference} of the Accessory.
     * @param stack     The Accessory {@link ItemStack}.
     */
    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        LivingEntity livingEntity = reference.entity();
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
     * @param reference The {@link SlotReference} of the Accessory.
     * @param stack     The {@link ItemStack} of the Accessory.
     */
    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        LivingEntity livingEntity = reference.entity();
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
        super.onUnequip(stack, reference);
    }

    /**
     * @return The night vision {@link MobEffectInstance}. It is infinite and does not display visible particles.
     */
    public static MobEffectInstance getTotemNightVisionEffect() {
        return TOTEM_NIGHT_VISION_EFFECT;
    }
}
