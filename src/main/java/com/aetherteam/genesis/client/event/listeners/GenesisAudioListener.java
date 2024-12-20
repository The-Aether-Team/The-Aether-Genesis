package com.aetherteam.genesis.client.event.listeners;

import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.client.event.hooks.GenesisAudioHooks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
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
     * @see GenesisAudioHooks#shouldReplacePortalHum(SoundInstance)
     * @see GenesisAudioHooks#shouldCancelPortalSound(SoundEngine, SoundInstance)
     */
    public static void onPlaySound(PlaySoundEvent event) {
        SoundEngine soundEngine = event.getEngine();
        SoundInstance sound = event.getOriginalSound();

        if (GenesisAudioHooks.shouldCancelMusic(sound)) {
            event.setSound(null);
        }

        Level level = Minecraft.getInstance().level;
        if (level != null) {
            RandomSource random = level.getRandom();
            if (GenesisAudioHooks.shouldReplacePortalHum(sound)) { // Parameters based on Nether Portal hum.
                event.setSound(new SimpleSoundInstance(GenesisSoundEvents.BLOCK_AETHER_PORTAL_HUM.get(), SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, RandomSource.create(random.nextLong()), sound.getX(), sound.getY(), sound.getZ()));
            }
        }

        SoundInstance newSound = event.getSound();
        if (GenesisAudioHooks.shouldCancelPortalSound(soundEngine, newSound)) {
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
