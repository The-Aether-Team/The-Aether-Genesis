package com.aetherteam.genesis.event.hooks;

import com.aetherteam.genesis.attachment.GenesisDataAttachments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class AttachmentHooks {
    public static class GenesisPlayerHooks {
        /**
         * @see com.aetherteam.genesis.event.listeners.capability.GenesisPlayerListener#onPlayerLogin(PlayerEvent.PlayerLoggedInEvent)
         */
        public static void login(Player player) {
            player.getData(GenesisDataAttachments.GENESIS_PLAYER).onLogin();
        }

        /**
         * @see com.aetherteam.genesis.event.listeners.capability.GenesisPlayerListener#onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent)
         */
        public static void logout(Player player) {
            player.getData(GenesisDataAttachments.GENESIS_PLAYER).onLogout();
        }

        /**
         * @see com.aetherteam.genesis.event.listeners.capability.GenesisPlayerListener#onPlayerUpdate(LivingEvent.LivingTickEvent)
         */
        public static void update(LivingEntity entity) {
            if (entity instanceof Player player) {
                player.getData(GenesisDataAttachments.GENESIS_PLAYER).onUpdate(player);
            }
        }
    }
}
