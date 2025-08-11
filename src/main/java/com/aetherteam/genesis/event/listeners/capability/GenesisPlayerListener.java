package com.aetherteam.genesis.event.listeners.capability;

import com.aetherteam.genesis.event.hooks.AttachmentHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class GenesisPlayerListener {
    public static void listen(IEventBus bus) {
        bus.addListener(GenesisPlayerListener::onPlayerLogin);
        bus.addListener(GenesisPlayerListener::onPlayerLogout);
        bus.addListener(GenesisPlayerListener::onPlayerUpdate);
        bus.addListener(GenesisPlayerListener::onPlayerChangedDimension);
    }

    /**
     * @see AttachmentHooks.GenesisPlayerHooks#login(Player)
     */
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        AttachmentHooks.GenesisPlayerHooks.login(player);
    }

    /**
     * @see AttachmentHooks.GenesisPlayerHooks#logout(Player)
     */
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        AttachmentHooks.GenesisPlayerHooks.logout(player);
    }

    /**
     * @see AttachmentHooks.GenesisPlayerHooks#update(LivingEntity)
     */
    public static void onPlayerUpdate(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            AttachmentHooks.GenesisPlayerHooks.update(livingEntity);
        }
    }

    /**
     * @see AttachmentHooks.GenesisPlayerHooks#changeDimension(Player)
     */
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        Player player = event.getEntity();
        AttachmentHooks.GenesisPlayerHooks.changeDimension(player);
    }
}
