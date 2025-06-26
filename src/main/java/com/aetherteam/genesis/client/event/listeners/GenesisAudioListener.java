package com.aetherteam.genesis.client.event.listeners;

import com.aetherteam.genesis.client.event.hooks.GenesisAudioHooks;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.sound.PlaySoundEvent;

public class GenesisAudioListener {

    public static void listeners(IEventBus eventBus) {
        eventBus.addListener(GenesisAudioListener::onPlaySound);
        eventBus.addListener(GenesisAudioListener::onClientTick);
        eventBus.addListener(GenesisAudioListener::onPlayerRespawn);
    }

    /**
     * @see GenesisAudioHooks#shouldCancelMusic(SoundInstance)
     */
    public static void onPlaySound(PlaySoundEvent event) {
        SoundInstance sound = event.getOriginalSound();

        if (GenesisAudioHooks.shouldCancelMusic(sound)) {
            event.setSound(null);
        }
    }

    /**
     * @see GenesisAudioHooks#tick()
     */
    public static void onClientTick(ClientTickEvent.Post event) {
        GenesisAudioHooks.tick();
    }

    /**
     * @see GenesisAudioHooks#stop()
     */
    public static void onPlayerRespawn(ClientPlayerNetworkEvent.Clone event) {
        GenesisAudioHooks.stop();
    }
}
