package com.aetherteam.genesis.client.event.hooks;

import com.aetherteam.genesis.GenesisConfig;
import com.aetherteam.genesis.client.GenesisMusicManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;

public class GenesisAudioHooks {
    /**
     * Stops other music from playing over Aether music.
     * @see com.aetherteam.genesis.client.event.listeners.GenesisAudioListener#onPlaySound(PlaySoundEvent)
     */
    public static boolean shouldCancelMusic(SoundInstance sound) {
        if (sound.getSource() == SoundSource.MUSIC && GenesisConfig.CLIENT.night_music_tracks.get()) {
            return ((GenesisMusicManager.getSituationalMusic() != null && !sound.getLocation().equals(SimpleSoundInstance.forMusic(GenesisMusicManager.getSituationalMusic().getEvent().value()).getLocation())) && (GenesisMusicManager.getSituationalOppositeDaytimeMusic() != null && !sound.getLocation().equals(SimpleSoundInstance.forMusic(GenesisMusicManager.getSituationalOppositeDaytimeMusic().getEvent().value()).getLocation())))
                    || GenesisMusicManager.getCurrentMusic() != null && !sound.getLocation().equals(GenesisMusicManager.getCurrentMusic().getLocation());
        } else {
            return false;
        }
    }

    /**
     * Ticks Aether Genesis's music manager.
     * @see com.aetherteam.genesis.client.event.listeners.GenesisAudioListener#onClientTick(TickEvent.ClientTickEvent)
     */
    public static void tick() {
        if (!Minecraft.getInstance().isPaused() && GenesisConfig.CLIENT.night_music_tracks.get()) {
            GenesisMusicManager.tick();
        }
    }

    /**
     * Resets the music on respawn.
     * @see com.aetherteam.genesis.client.event.listeners.GenesisAudioListener#onPlayerRespawn(ClientPlayerNetworkEvent.Clone)
     */
    public static void stop() {
        GenesisMusicManager.stopPlaying();
    }
}
