package com.aetherteam.aether_genesis.item.accessories.companion;

import com.aetherteam.aether_genesis.attachment.GenesisDataAttachments;
import com.aetherteam.aether_genesis.entity.companion.Companion;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public interface CompanionAccessory<T extends Entity> {
    /**
     * Attaches a companion entity to the player for tracking using {@link com.aetherteam.aether_genesis.attachment.GenesisPlayerAttachment}.
     *
     * @param slotContext The {@link SlotContext} of the Companion accessory.
     */
    default void equip(SlotContext slotContext, ItemStack stack) {
        LivingEntity wearer = slotContext.entity();
        if (wearer.level() instanceof ServerLevel serverLevel) {
            CompoundTag tag = new CompoundTag();
            tag.putUUID("Owner", wearer.getUUID());
            Entity entity = this.getCompanionType().create(serverLevel, tag, null, wearer.blockPosition(), MobSpawnType.MOB_SUMMONED, false, false);
            if (entity != null && wearer instanceof Player player) {
                if (entity instanceof Companion<?> companion) {
                    companion.onEquip(stack);
                }
                if (stack.hasCustomHoverName()) {
                    entity.setCustomName(stack.getHoverName().plainCopy());
                }
                serverLevel.addFreshEntityWithPassengers(entity);
                player.getData(GenesisDataAttachments.GENESIS_PLAYER).addCompanion(player, entity);
            }
        }
    }

    /**
     * Removes a companion entity from being tracked with the player through {@link com.aetherteam.aether_genesis.attachment.GenesisPlayerAttachment}.
     *
     * @param slotContext The {@link SlotContext} of the Companion accessory.
     */
    default void unequip(SlotContext slotContext, ItemStack itemStack) {
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
}
