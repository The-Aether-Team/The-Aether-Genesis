package com.aetherteam.aether_genesis.client.event.listeners;

import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.client.event.hooks.GenesisAudioHooks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class GenesisAudioListener {
    /**
     * @see GenesisAudioHooks#shouldCancelMusic(SoundInstance)
     * @see GenesisAudioHooks#shouldReplacePortalHum(SoundInstance)
     * @see GenesisAudioHooks#shouldCancelPortalSound(SoundEngine, SoundInstance)
     */
    @SubscribeEvent
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
                event.setSound(new SimpleSoundInstance(GenesisSoundEvents.PORTAL_HUM.get(), SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, RandomSource.create(random.nextLong()), sound.getX(), sound.getY(), sound.getZ()));
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
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            GenesisAudioHooks.tick();
        }
    }

    /**
     * @see GenesisAudioHooks#stop()
     */
    @SubscribeEvent
    public static void onPlayerRespawn(ClientPlayerNetworkEvent.Clone event) {
        GenesisAudioHooks.stop();
    }
}
