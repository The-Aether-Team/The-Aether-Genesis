package com.aetherteam.aether_genesis.client;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GenesisSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Genesis.MODID);

    public static final RegistryObject<SoundEvent> ENTITY_CARRION_SPROUT_HURT = register("entity.carrion_sprout.hurt");
    public static final RegistryObject<SoundEvent> ENTITY_CARRION_SPROUT_DEATH = register("entity.carrion_sprout.death");

    public static final RegistryObject<SoundEvent> MUSIC_AETHER_NIGHT = register("music.aether_night");
    public static final RegistryObject<SoundEvent> BLUE_AERCLOUD_BOUNCE = register("block.aercloud.blue_aercloud_bounce");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Genesis.MODID, name)));
    }
}
