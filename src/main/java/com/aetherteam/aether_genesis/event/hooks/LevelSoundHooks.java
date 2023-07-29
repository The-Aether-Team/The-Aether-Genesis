package com.aetherteam.aether_genesis.event.hooks;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

public class LevelSoundHooks {


    public static RegistryObject<SoundEvent> modify(SoundEvent sound) {
        if (GenesisConfig.COMMON.aether_ii_portal_sounds.get()) {
            if (sound == AetherSoundEvents.BLOCK_AETHER_PORTAL_AMBIENT.get()) {
                return GenesisSoundEvents.PORTAL_HUM;
            }
            if (sound == AetherSoundEvents.BLOCK_AETHER_PORTAL_TRAVEL.get()) {
                return GenesisSoundEvents.PORTAL_TRAVEL;
            }
            if (sound == AetherSoundEvents.BLOCK_AETHER_PORTAL_TRIGGER.get()) {
                return GenesisSoundEvents.PORTAL_TRIGGER;
            }
        }
        return null;
    }
}
