package com.aetherteam.aether_genesis.data.generators.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenesisEntityTagData extends EntityTypeTagsProvider {
    public GenesisEntityTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, Genesis.MODID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(GenesisTags.Entities.COMPANIONS).add(
                GenesisEntityTypes.FLEETING_WISP.get(),
                GenesisEntityTypes.SOARING_WISP.get(),
                GenesisEntityTypes.ETHEREAL_WISP.get());
        this.tag(GenesisTags.Entities.NO_PRESENT_DROPS);

        this.tag(AetherTags.Entities.WHIRLWIND_UNAFFECTED).add(GenesisEntityTypes.CARRION_SPROUT.get());
        this.tag(AetherTags.Entities.UNLAUNCHABLE).add(GenesisEntityTypes.CARRION_SPROUT.get());
        this.tag(AetherTags.Entities.DEFLECTABLE_PROJECTILES)
                .addTag(EntityTypeTags.ARROWS)
                .add(GenesisEntityTypes.TEMPEST_THUNDERBALL.get(),
                        GenesisEntityTypes.PHOENIX_DART.get());
        this.tag(AetherTags.Entities.SWETS).add(
                GenesisEntityTypes.DARK_SWET.get());
        this.tag(AetherTags.Entities.TREATED_AS_AETHER_ENTITY).add(
                GenesisEntityTypes.CARRION_SPROUT.get(),
                GenesisEntityTypes.DARK_SWET.get(),
                GenesisEntityTypes.TEMPEST.get(),
                GenesisEntityTypes.BATTLE_SENTRY.get());
        this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
                GenesisEntityTypes.TEMPEST.get(),
                GenesisEntityTypes.SENTRY_GUARDIAN.get(),
                GenesisEntityTypes.LABYRINTH_EYE.get(),
                GenesisEntityTypes.SLIDER_HOST_MIMIC.get());
        this.tag(AetherTags.Entities.UNHOOKABLE).add(GenesisEntityTypes.CARRION_SPROUT.get());

        this.tag(EntityTypeTags.IMPACT_PROJECTILES)
                .add(GenesisEntityTypes.PHOENIX_DART.get());
        this.tag(Tags.EntityTypes.BOSSES).add(
                GenesisEntityTypes.LABYRINTH_EYE.get(),
                GenesisEntityTypes.SENTRY_GUARDIAN.get(),
                GenesisEntityTypes.SLIDER_HOST_MIMIC.get());
    }
}
