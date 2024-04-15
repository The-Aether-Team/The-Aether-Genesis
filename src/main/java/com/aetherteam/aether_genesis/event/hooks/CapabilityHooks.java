package com.aetherteam.aether_genesis.event.hooks;

import com.aetherteam.aether_genesis.capability.GenesisDataAttachments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class CapabilityHooks {
    public static class GenesisPlayerHooks {
        public static void login(Player player) {
            player.getData(GenesisDataAttachments.GENESIS_PLAYER).onLogin();
        }

        public static void logout(Player player) {
            player.getData(GenesisDataAttachments.GENESIS_PLAYER).onLogout();
        }

        public static void update(LivingEntity entity) {
            if (entity instanceof Player player) {
                player.getData(GenesisDataAttachments.GENESIS_PLAYER).onUpdate(player);
            }
        }
    }
}
