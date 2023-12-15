package com.aetherteam.aether_genesis.item.accessories;

import com.aetherteam.aether.capability.player.AetherPlayer;
import com.aetherteam.aether.client.AetherKeys;
import com.aetherteam.aether.mixin.mixins.common.accessor.LivingEntityAccessor;
import com.aetherteam.aether_genesis.entity.companion.EtherealWisp;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Supplier;

public class EtherealStoneItem extends CompanionItem<EtherealWisp> {
    public EtherealStoneItem(Supplier<EntityType<EtherealWisp>> companionType, Properties properties) {
        super(companionType, properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (livingEntity.level().isClientSide() && livingEntity instanceof Player player) {
            AetherPlayer.get(player).ifPresent((aetherPlayer) -> {
                if (AetherKeys.INVISIBILITY_TOGGLE.consumeClick()) {
                    aetherPlayer.setSynched(INBTSynchable.Direction.SERVER, "setInvisibilityEnabled", !aetherPlayer.isInvisibilityEnabled());
                }
            });
        }
        if (!livingEntity.level().isClientSide() && livingEntity instanceof Player player) {
            AetherPlayer.get(player).ifPresent((aetherPlayer) -> {
                if (aetherPlayer.isInvisibilityEnabled()) {
                    if (!aetherPlayer.isWearingInvisibilityCloak() && !aetherPlayer.attackedWithInvisibility()) {
                        aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setWearingInvisibilityCloak", true);
                    } else if (aetherPlayer.isWearingInvisibilityCloak() && aetherPlayer.attackedWithInvisibility()) {
                        aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setWearingInvisibilityCloak", false);
                    }
                } else {
                    aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setWearingInvisibilityCloak", false);
                }
            });
        }
        if (!livingEntity.isInvisible()) {
            if (livingEntity instanceof Player player) {
                AetherPlayer.get(player).ifPresent((aetherPlayer) -> {
                    if (aetherPlayer.isInvisibilityEnabled()) {
                        aetherPlayer.getPlayer().setInvisible(true);
                    }
                });
            } else {
                livingEntity.setInvisible(true);
            }
        } else {
            if (livingEntity instanceof Player player) {
                AetherPlayer.get(player).ifPresent((aetherPlayer) -> {
                    if (!aetherPlayer.isInvisibilityEnabled()) {
                        aetherPlayer.getPlayer().setInvisible(false);
                    }
                });
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (!livingEntity.level().isClientSide() && livingEntity instanceof Player player) {
            AetherPlayer.get(player).ifPresent((aetherPlayer) -> aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setWearingInvisibilityCloak", false));
        }
        livingEntity.setInvisible(false);
        ((LivingEntityAccessor) livingEntity).callUpdateEffectVisibility();
        super.onUnequip(slotContext, newStack, stack);
    }
}
