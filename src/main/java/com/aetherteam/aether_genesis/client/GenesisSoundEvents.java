package com.aetherteam.aether_genesis.client;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Genesis.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_CARRION_SPROUT_HURT = register("entity.carrion_sprout.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_CARRION_SPROUT_DEATH = register("entity.carrion_sprout.death");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TRACKING_GOLEM_HIT = register("entity.tracking_golem.hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TRACKING_GOLEM_DEATH = register("entity.tracking_golem.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TRACKING_GOLEM_SEEN_ENEMY = register("entity.tracking_golem.seen_enemy");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TRACKING_GOLEM_SAY = register("entity.tracking_golem.say");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TRACKING_GOLEM_CREEPY_SEEN = register("entity.tracking_golem.creepy_seen");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GUARDIAN_DEATH = register("entity.sentry_guardian.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GUARDIAN_SUMMON = register("entity.sentry_guardian.summon");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GUARDIAN_HIT = register("entity.sentry_guardian.hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GUARDIAN_LIVING = register("entity.sentry_guardian.living");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LABYRINTH_EYE_DEATH = register("entity.labyrinth_eye.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LABYRINTH_EYE_COGLOSS = register("entity.labyrinth_eye.cogloss");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LABYRINTH_EYE_MOVE = register("entity.labyrinth_eye.move");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_COG_BREAK = register("entity.cog.break");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TEMPEST_AMBIENT = register("entity.tempest.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TEMPEST_HURT = register("entity.tempest.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TEMPEST_DEATH = register("entity.tempest.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TEMPEST_SHOOT = register("entity.tempest.shoot");
    
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_MUSIC_DISC_AERWHALE = register("item.music_disc.aerwhale");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_MUSIC_DISC_APPROACHES = register("item.music_disc.approaches");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_MUSIC_DISC_DEMISE = register("item.music_disc.demise");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_RECORDING_892 = register("item.music_disc.recording_892");

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_AETHER_NIGHT = register("music.aether_night");
    public static final DeferredHolder<SoundEvent, SoundEvent> BLUE_AERCLOUD_BOUNCE = register("block.aercloud.blue_aercloud_bounce");
    public static final DeferredHolder<SoundEvent, SoundEvent> PORTAL_HUM = register("block.portal.hum");
    public static final DeferredHolder<SoundEvent, SoundEvent> PORTAL_TRAVEL = register("block.portal.travel");
    public static final DeferredHolder<SoundEvent, SoundEvent> PORTAL_TRIGGER = register("block.portal.trigger");

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Genesis.MODID, name)));
    }
}
