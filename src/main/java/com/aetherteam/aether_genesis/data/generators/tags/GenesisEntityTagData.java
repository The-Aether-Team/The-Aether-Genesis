package com.aetherteam.aether_genesis.data.generators.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenesisEntityTagData extends EntityTypeTagsProvider {
    public GenesisEntityTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, Genesis.MODID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        // Genesis
        this.tag(GenesisTags.Entities.COMPANIONS).add(
                GenesisEntityTypes.FLEETING_WISP.get(),
                GenesisEntityTypes.SOARING_WISP.get(),
                GenesisEntityTypes.ETHEREAL_WISP.get(),
                GenesisEntityTypes.SHADE_OF_ARKENZUS.get(),
                GenesisEntityTypes.FROSTPINE_TOTEM.get(),
                GenesisEntityTypes.BABY_PINK_SWET.get());
        this.tag(GenesisTags.Entities.NO_PRESENT_DROPS);

        // Aether
        this.tag(AetherTags.Entities.WHIRLWIND_UNAFFECTED).add(GenesisEntityTypes.CARRION_SPROUT.get());
        this.tag(AetherTags.Entities.UNLAUNCHABLE).add(GenesisEntityTypes.CARRION_SPROUT.get());
        this.tag(AetherTags.Entities.DEFLECTABLE_PROJECTILES)
                .addTag(EntityTypeTags.ARROWS)
                .add(GenesisEntityTypes.TEMPEST_THUNDERBALL.get(),
                        GenesisEntityTypes.PHOENIX_DART.get());
        this.tag(AetherTags.Entities.SWETS).add(
                GenesisEntityTypes.DARK_SWET.get());
        this.tag(AetherTags.Entities.UNHOOKABLE).add(GenesisEntityTypes.CARRION_SPROUT.get());
        this.tag(AetherTags.Entities.TREATED_AS_AETHER_ENTITY).add(
                GenesisEntityTypes.CARRION_SPROUT.get(),
                GenesisEntityTypes.DARK_SWET.get(),
                GenesisEntityTypes.TEMPEST.get(),
                GenesisEntityTypes.BATTLE_SENTRY.get(),
                GenesisEntityTypes.TRACKING_GOLEM.get(),
                GenesisEntityTypes.SKYROOT_MIMIC.get(),
                GenesisEntityTypes.SENTRY_GUARDIAN.get(),
                GenesisEntityTypes.SLIDER_HOST_MIMIC.get(),
                GenesisEntityTypes.LABYRINTH_EYE.get());
        this.tag(AetherTags.Entities.DUNGEON_ENTITIES).add(
                GenesisEntityTypes.BATTLE_SENTRY.get(),
                GenesisEntityTypes.TRACKING_GOLEM.get(),
                GenesisEntityTypes.SKYROOT_MIMIC.get(),
                GenesisEntityTypes.SENTRY_GUARDIAN.get(),
                GenesisEntityTypes.SLIDER_HOST_MIMIC.get(),
                GenesisEntityTypes.LABYRINTH_EYE.get());

        // Forge
        this.tag(Tags.EntityTypes.BOSSES).add(
                GenesisEntityTypes.LABYRINTH_EYE.get(),
                GenesisEntityTypes.SENTRY_GUARDIAN.get(),
                GenesisEntityTypes.SLIDER_HOST_MIMIC.get());

        // Vanilla
        this.tag(EntityTypeTags.IMPACT_PROJECTILES)
                .add(GenesisEntityTypes.PHOENIX_DART.get());
        this.tag(EntityTypeTags.FROG_FOOD)
                .add(GenesisEntityTypes.DARK_SWET.get());
        this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
                GenesisEntityTypes.TEMPEST.get(),
                GenesisEntityTypes.SENTRY_GUARDIAN.get(),
                GenesisEntityTypes.LABYRINTH_EYE.get(),
                GenesisEntityTypes.SLIDER_HOST_MIMIC.get());
    }
}
