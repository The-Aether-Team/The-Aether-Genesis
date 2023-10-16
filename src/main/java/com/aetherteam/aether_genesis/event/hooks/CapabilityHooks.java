package com.aetherteam.aether_genesis.event.hooks;

import com.aetherteam.aether_genesis.capability.player.GenesisPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class CapabilityHooks {
    public static class GenesisPlayerHooks {
        public static void login(Player player) {
            GenesisPlayer.get(player).ifPresent(GenesisPlayer::onLogin);
        }

        public static void logout(Player player) {
            GenesisPlayer.get(player).ifPresent(GenesisPlayer::onLogout);
        }

        public static void update(LivingEntity entity) {
            if (entity instanceof Player player) {
                GenesisPlayer.get(player).ifPresent(GenesisPlayer::onUpdate);
            }
        }
    }
}
