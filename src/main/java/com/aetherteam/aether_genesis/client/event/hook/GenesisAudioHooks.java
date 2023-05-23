package com.aetherteam.aether_genesis.client.event.hook;

import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.client.GenesisMusicManager;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;

public class GenesisAudioHooks {
    public GenesisAudioHooks() {
    }

    public static boolean shouldCancelMusic(SoundInstance sound) {
        if (sound.getSource() == SoundSource.MUSIC && GenesisConfig.CLIENT.night_music_tracks.get()) {
            return ((GenesisMusicManager.getSituationalMusic() != null && !sound.getLocation().equals(SimpleSoundInstance.forMusic(GenesisMusicManager.getSituationalMusic().getEvent().get()).getLocation())) && (GenesisMusicManager.getSituationalOppositeDaytimeMusic() != null && !sound.getLocation().equals(SimpleSoundInstance.forMusic(GenesisMusicManager.getSituationalOppositeDaytimeMusic().getEvent().get()).getLocation()))) || GenesisMusicManager.getCurrentMusic() != null && !sound.getLocation().equals(GenesisMusicManager.getCurrentMusic().getLocation());
        } else {
            return false;
        }
    }

    public static boolean shouldCancelAercloudBounceSound(SoundInstance sound) {
        if (sound.getSource() == SoundSource.BLOCKS && !GenesisConfig.CLIENT.blue_aercloud_bounce_sfx.get()) {
            return (sound.getLocation().equals(GenesisSoundEvents.BLUE_AERCLOUD_BOUNCE.get().getLocation()));
        } else {
            return false;
        }
    }

    public static void tick() {
        if (!Minecraft.getInstance().isPaused() && GenesisConfig.CLIENT.night_music_tracks.get()) {
            GenesisMusicManager.tick();
        }
    }
    public static void stop() {
        GenesisMusicManager.stopPlaying();

    }
}
