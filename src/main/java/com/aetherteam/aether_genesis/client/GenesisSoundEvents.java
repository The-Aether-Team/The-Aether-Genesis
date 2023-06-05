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

    public static final RegistryObject<SoundEvent> ENTITY_TRACKING_GOLEM_HIT = register("entity.tracking_golem.hit");
    public static final RegistryObject<SoundEvent> ENTITY_TRACKING_GOLEM_DEATH = register("entity.tracking_golem.death");
    public static final RegistryObject<SoundEvent> ENTITY_TRACKING_GOLEM_SEEN_ENEMY = register("entity.tracking_golem.seen_enemy");
    public static final RegistryObject<SoundEvent> ENTITY_TRACKING_GOLEM_SAY = register("entity.tracking_golem.say");
    public static final RegistryObject<SoundEvent> ENTITY_TRACKING_GOLEM_CREEPY_SEEN = register("entity.tracking_golem.creepy_seen");

    public static final RegistryObject<SoundEvent> ENTITY_SENTRY_GUARDIAN_DEATH = register("entity.sentry_guardian.death");
    public static final RegistryObject<SoundEvent> ENTITY_SENTRY_GUARDIAN_SUMMON = register("entity.sentry_guardian.summon");
    public static final RegistryObject<SoundEvent> ENTITY_SENTRY_GUARDIAN_HIT = register("entity.sentry_guardian.hit");
    public static final RegistryObject<SoundEvent> ENTITY_SENTRY_GUARDIAN_LIVING = register("entity.sentry_guardian.living");

    public static final RegistryObject<SoundEvent> ITEM_MUSIC_DISC_AERWHALE = register("item.music_disc.aerwhale");
    public static final RegistryObject<SoundEvent> ITEM_MUSIC_DISC_APPROACHES = register("item.music_disc.approaches");
    public static final RegistryObject<SoundEvent> ITEM_MUSIC_DISC_DEMISE = register("item.music_disc.demise");
    public static final RegistryObject<SoundEvent> ITEM_RECORDING_892 = register("item.music_disc.recording_892");

    public static final RegistryObject<SoundEvent> MUSIC_AETHER_NIGHT = register("music.aether_night");
    public static final RegistryObject<SoundEvent> BLUE_AERCLOUD_BOUNCE = register("block.aercloud.blue_aercloud_bounce");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Genesis.MODID, name)));
    }
}
