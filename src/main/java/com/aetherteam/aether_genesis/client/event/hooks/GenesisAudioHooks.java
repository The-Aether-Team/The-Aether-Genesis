package com.aetherteam.aether_genesis.client.event.hooks;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.client.GenesisMusicManager;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.mixin.mixins.client.accessor.SoundEngineAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;

import java.util.List;

public class GenesisAudioHooks {
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

    public static boolean shouldReplacePortalHum(SoundInstance sound) {
        if (sound.getSource() == SoundSource.BLOCKS && GenesisConfig.COMMON.aether_ii_portal_sounds.get()) {
            return sound.getLocation().equals(AetherSoundEvents.BLOCK_AETHER_PORTAL_AMBIENT.get().getLocation());
        } else {
            return false;
        }
    }

    public static boolean shouldCancelPortalSound(SoundEngine soundEngine, SoundInstance sound) {
        if (sound != null) {
            if (sound.getSource() == SoundSource.BLOCKS) {
                if (sound.getLocation().equals(GenesisSoundEvents.PORTAL_HUM.get().getLocation())
                        || sound.getLocation().equals(GenesisSoundEvents.PORTAL_TRAVEL.get().getLocation())
                        || sound.getLocation().equals(GenesisSoundEvents.PORTAL_TRIGGER.get().getLocation())) {
                    List<ResourceLocation> activeSounds = ((SoundEngineAccessor) soundEngine).genesis$getInstanceToChannel().keySet().stream().map(SoundInstance::getLocation).toList();
                    return activeSounds.contains(sound.getLocation());
                }
            }
        }
        return false;
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
