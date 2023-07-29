package com.aetherteam.aether_genesis.event.listeners;


import com.aetherteam.aether_genesis.event.hooks.LevelSoundHooks;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.PlayLevelSoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

@Mod.EventBusSubscriber
public class LevelSoundListener {

    @SubscribeEvent
    public static void alterSounds(PlayLevelSoundEvent event)
    {
        RegistryObject<SoundEvent> newSound = LevelSoundHooks.modify(event.getSound().get());
        if (newSound != null) {
            ForgeRegistries.SOUND_EVENTS.getHolder(newSound.get()).ifPresent(event::setSound);
        }

    }

}
