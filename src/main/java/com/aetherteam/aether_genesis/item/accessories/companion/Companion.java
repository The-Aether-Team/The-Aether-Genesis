package com.aetherteam.aether_genesis.item.accessories.companion;

import com.aetherteam.aether_genesis.attachment.GenesisDataAttachments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import top.theillusivec4.curios.api.SlotContext;

public interface Companion<T extends Entity> {
    default void equip(SlotContext slotContext) {
        LivingEntity wearer = slotContext.entity();
        if (wearer.level() instanceof ServerLevel serverLevel) {
            CompoundTag tag = new CompoundTag();
            tag.putUUID("Owner", wearer.getUUID());
            Entity entity = this.getCompanionType().spawn(serverLevel, tag, null, wearer.blockPosition(), MobSpawnType.MOB_SUMMONED, false, false);
            if (entity != null && wearer instanceof Player player) {
                player.getData(GenesisDataAttachments.GENESIS_PLAYER).addCompanion(player, entity);
            }
        }
    }

    default void unequip(SlotContext slotContext) {
        LivingEntity wearer = slotContext.entity();
        if (wearer instanceof Player player) {
            player.getData(GenesisDataAttachments.GENESIS_PLAYER).removeCompanion((entity) -> entity.getType() == this.getCompanionType());
        }
    }

    EntityType<T> getCompanionType();
}
