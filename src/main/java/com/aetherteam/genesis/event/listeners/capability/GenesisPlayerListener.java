package com.aetherteam.genesis.event.listeners.capability;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.event.hooks.AttachmentHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class GenesisPlayerListener {
    /**
     * @see AttachmentHooks.GenesisPlayerHooks#login(Player)
     */
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        AttachmentHooks.GenesisPlayerHooks.login(player);
    }

    /**
     * @see AttachmentHooks.GenesisPlayerHooks#logout(Player)
     */
    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        AttachmentHooks.GenesisPlayerHooks.logout(player);
    }

    /**
     * @see AttachmentHooks.GenesisPlayerHooks#update(LivingEntity)
     */
    @SubscribeEvent
    public static void onPlayerUpdate(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        AttachmentHooks.GenesisPlayerHooks.update(livingEntity);
    }
}
