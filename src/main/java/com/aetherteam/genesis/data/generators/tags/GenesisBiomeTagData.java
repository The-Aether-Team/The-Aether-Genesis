package com.aetherteam.genesis.data.generators.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.data.resources.registries.AetherBiomes;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.GenesisTags;
import com.aetherteam.genesis.data.resources.registries.GenesisBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenesisBiomeTagData extends BiomeTagsProvider {
    public GenesisBiomeTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, AetherGenesis.MODID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(GenesisTags.Biomes.HAS_GREEN_AERCLOUDS).addTag(AetherTags.Biomes.IS_AETHER);
        this.tag(GenesisTags.Biomes.HAS_PURPLE_AERCLOUDS).addTag(AetherTags.Biomes.IS_AETHER);
        this.tag(GenesisTags.Biomes.HAS_STORM_AERCLOUDS).addTag(AetherTags.Biomes.IS_AETHER);
        this.tag(GenesisTags.Biomes.HAS_ORANGE_TREES).addTag(AetherTags.Biomes.IS_AETHER);
        this.tag(GenesisTags.Biomes.HAS_GROVE_TREES)
                .addOptional(AetherBiomes.SKYROOT_GROVE.location());
        this.tag(GenesisTags.Biomes.HAS_WOODLAND_TREES)
                .addOptional(AetherBiomes.SKYROOT_WOODLAND.location());
        this.tag(GenesisTags.Biomes.HAS_FOREST_TREES)
                .addOptional(AetherBiomes.SKYROOT_FOREST.location());
        this.tag(GenesisTags.Biomes.HAS_CONTINUUM_ORE).addTag(AetherTags.Biomes.IS_AETHER);
        this.tag(GenesisTags.Biomes.HAS_CARRION_SPROUT).addTag(AetherTags.Biomes.IS_AETHER);
        this.tag(GenesisTags.Biomes.HAS_ZEPHYROO).addTag(AetherTags.Biomes.IS_AETHER);
        this.tag(GenesisTags.Biomes.HAS_DARK_SWET).addTag(AetherTags.Biomes.IS_AETHER);
        this.tag(GenesisTags.Biomes.HAS_TEMPEST).addTag(AetherTags.Biomes.IS_AETHER);

        this.tag(AetherTags.Biomes.IS_AETHER)
                .add(GenesisBiomes.VIBRANT_MEADOW)
                .add(GenesisBiomes.VIBRANT_GROVE)
                .add(GenesisBiomes.VIBRANT_WOODLAND)
                .add(GenesisBiomes.VIBRANT_FOREST);
    }
}
