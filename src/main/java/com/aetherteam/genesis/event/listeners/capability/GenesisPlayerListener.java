package com.aetherteam.genesis.event.listeners.capability;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.event.hooks.AttachmentHooks;
import com.aetherteam.genesis.event.listeners.EntityListener;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class GenesisPlayerListener {

    public static void listen(IEventBus bus) {
        bus.addListener(GenesisPlayerListener::onPlayerLogin);
        bus.addListener(GenesisPlayerListener::onPlayerLogout);
        bus.addListener(GenesisPlayerListener::onPlayerUpdate);
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
}
