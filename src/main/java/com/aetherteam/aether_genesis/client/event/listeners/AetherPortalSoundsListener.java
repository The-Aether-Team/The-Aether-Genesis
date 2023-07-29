package com.aetherteam.aether_genesis.client.event.listeners;


import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.util.RandomSource;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class AetherPortalSoundsListener {

    @SubscribeEvent
    public static void alterSounds(PlaySoundEvent event)
    {
        SoundInstance newSound = null;
        RandomSource random = RandomSource.create();
        SoundInstance oldSound = event.getOriginalSound();
        if (oldSound.getLocation() == AetherSoundEvents.BLOCK_AETHER_PORTAL_AMBIENT.get().getLocation()) {
            newSound = new SimpleSoundInstance(GenesisSoundEvents.PORTAL_HUM.get(), oldSound.getSource(), oldSound.getSound().getVolume().sample(random), oldSound.getSound().getPitch().sample(random), random, oldSound.getX(), oldSound.getY(), oldSound.getZ());
        }
        if (newSound != null) {
            event.setSound(newSound);
        }
    }

}
