package com.aetherteam.genesis.data.generators;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class GenesisSoundData extends SoundDefinitionsProvider {
    public GenesisSoundData(PackOutput output, ExistingFileHelper helper) {
        super(output,   Genesis.MODID, helper);
    }

    @Override
    public void registerSounds() {
        // Blocks
        this.add(GenesisSoundEvents.BLOCK_AETHER_PORTAL_HUM,
                definition().with(sound("aether_genesis:block/portal/hum"))
                        .subtitle("subtitles.aether_genesis.block.portal.hum")
        );
        this.add(GenesisSoundEvents.BLOCK_AETHER_PORTAL_TRAVEL,
                definition().with(sound("aether_genesis:block/portal/travel"))
        );
        this.add(GenesisSoundEvents.BLOCK_AETHER_PORTAL_TRIGGER,
                definition().with(sound("aether_genesis:block/portal/trigger"))
                        .subtitle("subtitles.aether_genesis.block.portal.trigger")
        );
        this.add(GenesisSoundEvents.BLOCK_BLUE_AERCLOUD_BOUNCE,
                definition().with(sound("aether_genesis:block/aercloud/blue_aercloud_bounce"))
                        .subtitle("subtitles.aether_genesis.block.aercloud.blue_aercloud_bounce")
        );


        // Items
        this.add(GenesisSoundEvents.ITEM_BONE_RING_EQUIP,
                definition().with(
                        sound("mob/skeleton/say1"),
                        sound( "mob/skeleton/say2"),
                        sound("mob/skeleton/say3")
                ).subtitle("subtitles.aether_genesis.item.accessory.equip_bone_ring")
        );
        this.add(GenesisSoundEvents.ITEM_CANDY_RING_EQUIP,
                definition().with(
                        sound("minecraft:item/armor/equip_chain1"),
                        sound("minecraft:item/armor/equip_chain2"),
                        sound("minecraft:item/armor/equip_chain3"),
                        sound("minecraft:item/armor/equip_chain4"),
                        sound("minecraft:item/armor/equip_chain5"),
                        sound("minecraft:item/armor/equip_chain6")
                ).subtitle("subtitles.aether_genesis.item.accessory.equip_candy_ring")
        );
        this.add(GenesisSoundEvents.ITEM_SKYROOT_RING_EQUIP,
                definition().with(
                        sound("dig/wood1"),
                        sound("dig/wood2"),
                        sound("dig/wood3"),
                        sound("dig/wood4")
                ).subtitle("subtitles.aether_genesis.item.accessory.equip_skyroot_ring")
        );

        this.add(GenesisSoundEvents.ITEM_MUSIC_DISC_AERWHALE,
                definition().with(sound("aether_genesis:item/records/aerwhale").stream())
        );
        this.add(GenesisSoundEvents.ITEM_MUSIC_DISC_APPROACHES,
                definition().with(sound("aether_genesis:item/records/approaches").stream())
        );
        this.add(GenesisSoundEvents.ITEM_MUSIC_DISC_DEMISE,
                definition().with(sound("aether_genesis:item/records/demise").stream())
        );
        this.add(GenesisSoundEvents.ITEM_RECORDING_892,
                definition().with(sound("aether_genesis:item/records/chase").stream())
        );


        // Entities
        this.add(GenesisSoundEvents.ENTITY_CARRION_SPROUT_HURT,
                definition().with(
                        sound("minecraft:damage/hit1"),
                        sound("minecraft:damage/hit2"),
                        sound("minecraft:damage/hit3")
                ).subtitle("subtitles.aether_genesis.entity.carrion_sprout.hurt")
        );
        this.add(GenesisSoundEvents.ENTITY_CARRION_SPROUT_DEATH,
                definition().with(sound("minecraft:damage/fallbig"))
                        .subtitle("subtitles.aether_genesis.entity.carrion_sprout.death")
        );
        this.add(GenesisSoundEvents.ENTITY_TEMPEST_SHOOT,
                definition().with(sound("aether:entity/zephyr/shoot"))
                        .subtitle("subtitles.aether_genesis.entity.tempest.shoot"));
        this.add(GenesisSoundEvents.ENTITY_TEMPEST_AMBIENT,
                definition().with(sound("aether:entity/zephyr/call"))
                        .subtitle("subtitles.aether_genesis.entity.tempest.ambient")
        );
        this.add(GenesisSoundEvents.ENTITY_TEMPEST_DEATH,
                definition().with(sound("aether:entity/zephyr/call"))
                        .subtitle("subtitles.aether_genesis.entity.tempest.death")
        );
        this.add(GenesisSoundEvents.ENTITY_TEMPEST_HURT,
                definition().with(sound("aether:entity/zephyr/call"))
                        .subtitle("subtitles.aether_genesis.entity.tempest.hurt")
        );

        this.add(GenesisSoundEvents.ENTITY_TRACKING_GOLEM_DEATH,
                definition().with(sound("aether_genesis:entity/tracking_golem/death"))
                        .subtitle("subtitles.aether_genesis.entity.tracking_golem.death")
        );
        this.add(GenesisSoundEvents.ENTITY_TRACKING_GOLEM_CREEPY_SEEN,
                definition().with(sound("aether_genesis:entity/tracking_golem/creepy_seen"))
                        .subtitle("subtitles.aether_genesis.entity.tracking_golem.creepy_seen")
        );
        this.add(GenesisSoundEvents.ENTITY_TRACKING_GOLEM_HIT,
                definition().with(sound("aether_genesis:entity/tracking_golem/hit"))
                        .subtitle("subtitles.aether_genesis.entity.tracking_golem.hit")
        );
        this.add(GenesisSoundEvents.ENTITY_TRACKING_GOLEM_SAY,
                definition().with(sound("aether_genesis:entity/tracking_golem/say"))
                        .subtitle("subtitles.aether_genesis.entity.tracking_golem.say")
        );
        this.add(GenesisSoundEvents.ENTITY_TRACKING_GOLEM_SEEN_ENEMY,
                definition().with(sound("aether_genesis:entity/tracking_golem/seen_enemy"))
                        .subtitle("subtitles.aether_genesis.entity.tracking_golem.seen_enemy")
        );

        this.add(GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_DEATH,
                definition().with(sound("aether_genesis:entity/sentry_guardian/death"))
                        .subtitle("subtitles.aether_genesis.entity.tracking_golem.creepy_seen")
        );
        this.add(GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_SUMMON,
                definition().with(sound("aether_genesis:entity/sentry_guardian/spawn"))
                        .subtitle("subtitles.aether_genesis.entity.sentry_guardian.spawn")
        );
        this.add(GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_LIVING,
                definition().with(sound("aether_genesis:entity/sentry_guardian/living"))
                        .subtitle("subtitles.aether_genesis.entity.sentry_guardian.living")
        );
        this.add(GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_HIT,
                definition().with(sound("aether_genesis:entity/sentry_guardian/hit"))
                        .subtitle("subtitles.aether_genesis.entity.sentry_guardian.hit")
        );

        this.add(GenesisSoundEvents.ENTITY_LABYRINTH_EYE_COGLOSS,
                definition().with(sound("aether_genesis:entity/labyrinth_eye/cogloss"))
                        .subtitle("subtitles.aether_genesis.entity.labyrinth_eye.cogloss")
        );
        this.add(GenesisSoundEvents.ENTITY_LABYRINTH_EYE_DEATH,
                definition().with(sound("aether_genesis:entity/labyrinth_eye/eyedeath"))
                        .subtitle("subtitles.aether_genesis.entity.labyrinth_eye.eyedeath")
        );
        this.add(GenesisSoundEvents.ENTITY_LABYRINTH_EYE_MOVE,
                definition().with(sound("aether_genesis:entity/labyrinth_eye/move_1"),
                                sound("aether_genesis:entity/labyrinth_eye/move_2"))
                        .subtitle("subtitles.aether_genesis.entity.labyrinth_eye.move")
        );
        this.add(GenesisSoundEvents.ENTITY_COG_BREAK,
                definition().with(sound("aether_genesis:entity/cog/wall_final"))
                        .subtitle("subtitles.aether_genesis.entity.cog.break")
        );


        // Music
        this.add(GenesisSoundEvents.MUSIC_AETHER_NIGHT,
                definition().with(
                        sound("aether_genesis:music/aether_night1").stream(),
                        sound("aether_genesis:music/aether_night2").stream()
                )
        );
    }
}