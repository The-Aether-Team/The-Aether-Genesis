package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.genesis.attachment.GenesisDataAttachments;
import com.aetherteam.genesis.entity.companion.Companion;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public interface CompanionAccessory<T extends Entity> {
    /**
     * Attaches a companion entity to the player for tracking using {@link com.aetherteam.genesis.attachment.GenesisPlayerAttachment}.
     *
     * @param slotContext The {@link SlotReference} of the Companion accessory.
     */
    default void equip(ItemStack stack, SlotReference slotContext) {
        LivingEntity wearer = slotContext.entity();
        if (wearer.level() instanceof ServerLevel serverLevel) {
            Entity entity = this.getCompanionType().create(serverLevel, t -> this.applyCompanionInfo(t, wearer.getUUID()), wearer.blockPosition(), MobSpawnType.MOB_SUMMONED, false, false);
            if (entity != null && wearer instanceof Player player) {
                if (entity instanceof Companion<?> companion) {
                    companion.onEquip(stack);
                }
                if (stack.has(DataComponents.CUSTOM_NAME)) {
                    entity.setCustomName(stack.getHoverName().plainCopy());
                }
                serverLevel.addFreshEntityWithPassengers(entity);
                player.getData(GenesisDataAttachments.GENESIS_PLAYER).addCompanion(player, entity);
            }
        }
    }

    /**
     * Removes a companion entity from being tracked with the player through {@link com.aetherteam.genesis.attachment.GenesisPlayerAttachment}.
     *
     * @param slotContext The {@link SlotReference} of the Companion accessory.
     */
    default void unequip(ItemStack itemStack, SlotReference slotContext) {
        LivingEntity wearer = slotContext.entity();
        if (wearer instanceof Player player) {
            player.getData(GenesisDataAttachments.GENESIS_PLAYER).removeCompanion((entity) -> {
                if (entity.getType() == this.getCompanionType()) {
                    if (entity instanceof Companion<?> companion) {
                        companion.onUnequip(itemStack);
                    }
                    return true;
                }
                return false;
            });
        }
    }

    /**
     * @return The companion {@link EntityType}.
     */
    EntityType<T> getCompanionType();

    default void applyCompanionInfo(T t, UUID owner) {
        if (t instanceof Companion<?> companion) {
            companion.setOwner(owner);
        }
    }
}
