package com.aetherteam.aether_genesis.entity.companion;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public interface Companion<T extends Mob & Companion<T>> {
    default void tickCompanion(T companion) {
        if (this.getOwner() != null) {
            Player player = companion.level().getPlayerByUUID(this.getOwner());
            if (player == null || !player.isAlive()) {
                companion.discard();
            }
        } else {
            companion.discard();
        }
    }

    UUID getOwner();
    void setOwner(UUID owner);

    ItemStack getSummonItem();
}
