package com.aetherteam.aether_genesis.data.generators;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class GenesisSoundData extends SoundDefinitionsProvider {
    public GenesisSoundData(PackOutput output, ExistingFileHelper helper) {
        super(output,   Genesis.MODID, helper);
    }

    @Override
    public void registerSounds() {
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

        this.add(GenesisSoundEvents.MUSIC_AETHER_NIGHT,
                definition().with(
                        sound("aether_genesis:music/aether_night1").stream(),
                        sound("aether_genesis:music/aether_night2").stream()
                )
        );
        this.add(GenesisSoundEvents.BLUE_AERCLOUD_BOUNCE,
                definition().with(sound("aether_genesis:block/aercloud/blue_aercloud_bounce"))
                        .subtitle("subtitles.aether_genesis.block.aercloud.blue_aercloud_bounce")
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
    }
}