package com.aetherteam.genesis.client;

import com.aetherteam.genesis.AetherGenesis;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisSoundEvents { //todo improve ordering and naming of sound events
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, AetherGenesis.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_MUSIC_DISC_AERWHALE = register("music_disc.aerwhale");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_MUSIC_DISC_APPROACHES = register("music_disc.approaches");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_MUSIC_DISC_DEMISE = register("music_disc.demise");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_RECORDING_892 = register("music_disc.recording_892");

    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_BONE_RING = register("item.accessory.equip_bone_ring");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_CANDY_RING = register("item.accessory.equip_candy_ring");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_SKYROOT_RING = register("item.accessory.equip_skyroot_ring");

    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_LUCKY_BELL = register("item.accessory.equip_lucky_bell");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_SWETTY_PENDANT = register("item.accessory.equip_swetty_pendant");
    public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_ACCESSORY_EQUIP_DAGGERFROST_LOCKET = register("item.accessory.equip_daggerfrost_locket");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_CARRION_SPROUT_HURT = register("entity.carrion_sprout.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_CARRION_SPROUT_DEATH = register("entity.carrion_sprout.death");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZEPHYROO_AMBIENT = register("entity.zephyroo.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZEPHYROO_HURT = register("entity.zephyroo.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZEPHYROO_DEATH = register("entity.zephyroo.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZEPHYROO_JUMP = register("entity.zephyroo.jump");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TEMPEST_AMBIENT = register("entity.tempest.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TEMPEST_HURT = register("entity.tempest.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TEMPEST_DEATH = register("entity.tempest.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TEMPEST_SHOOT = register("entity.tempest.shoot");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BATTLE_SENTRY_DEATH = register("entity.battle_sentry.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BATTLE_SENTRY_HURT = register("entity.battle_sentry.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BATTLE_SENTRY_JUMP = register("entity.battle_sentry.jump");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BATTLE_SENTRY_SQUISH = register("entity.battle_sentry.squish");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BATTLE_SENTRY_AMBIENT = register("entity.battle_sentry.ambient");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GOLEM_HURT = register("entity.sentry_golem.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GOLEM_DEATH = register("entity.sentry_golem.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GOLEM_SAY = register("entity.sentry_golem.say");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GOLEM_THROW_BOMB = register("entity.sentry_golem.throw_bomb");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TRACKING_GOLEM_HURT = register("entity.tracking_golem.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TRACKING_GOLEM_DEATH = register("entity.tracking_golem.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TRACKING_GOLEM_SAY = register("entity.tracking_golem.say");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TRACKING_GOLEM_SEEN_ENEMY = register("entity.tracking_golem.seen_enemy");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_TRACKING_GOLEM_CREEPY_SEEN = register("entity.tracking_golem.creepy_seen"); //todo this isnt even used where is it used

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SLIDER_HOST_MIMIC_AWAKEN = register("entity.slider_host_mimic.awaken");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SLIDER_HOST_MIMIC_AMBIENT = register("entity.slider_host_mimic.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SLIDER_HOST_MIMIC_SHOOT = register("entity.slider_host_mimic.shoot");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SLIDER_HOST_MIMIC_SCARE = register("entity.slider_host_mimic.scare");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SLIDER_HOST_MIMIC_HURT = register("entity.slider_host_mimic.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SLIDER_HOST_MIMIC_DEATH = register("entity.slider_host_mimic.death");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GUARDIAN_DEATH = register("entity.sentry_guardian.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GUARDIAN_SUMMON = register("entity.sentry_guardian.summon");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GUARDIAN_HURT = register("entity.sentry_guardian.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GUARDIAN_AMBIENT = register("entity.sentry_guardian.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SENTRY_GUARDIAN_ATTACK = register("entity.sentry_guardian.attack");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LABYRINTH_EYE_MOVE = register("entity.labyrinth_eye.move");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LABYRINTH_EYE_DEATH = register("entity.labyrinth_eye.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LABYRINTH_EYE_BREAK = register("entity.labyrinth_eye.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LABYRINTH_EYE_COG_LOSS = register("entity.labyrinth_eye.cog_loss");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_KRAISITH_SHOOT = register("entity.kraisith.shoot");

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_CONTINUUM_BOMB_THROW = register("entity.continuum_bomb.throw");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_REWARD_ITEM_LAUNCH = register("entity.reward_item.launch");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HOST_EYE_COLLIDE = register("entity.host_eye.collide");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_COG_BREAK = register("entity.cog.break");

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_AETHER_NIGHT = register("music.aether_night");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_MINIBOSS = register("music.miniboss");

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, name)));
    }
}
