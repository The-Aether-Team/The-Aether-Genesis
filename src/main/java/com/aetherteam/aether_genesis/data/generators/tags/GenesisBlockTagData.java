package com.aetherteam.aether_genesis.data.generators.tags;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenesisBlockTagData extends BlockTagsProvider {
    public GenesisBlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, Genesis.MODID, helper);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(GenesisTags.Blocks.LOG_WALLS).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get());

        this.tag(AetherTags.Blocks.AETHER_PORTAL_BLACKLIST).add(
                GenesisBlocks.GREEN_AERCLOUD.get(),
                GenesisBlocks.PURPLE_AERCLOUD.get());
        this.tag(AetherTags.Blocks.AERCLOUDS).add(
                GenesisBlocks.GREEN_AERCLOUD.get(),
                GenesisBlocks.PURPLE_AERCLOUD.get(),
                GenesisBlocks.STORM_AERCLOUD.get());

        this.tag(BlockTags.LOGS).addTag(GenesisTags.Blocks.LOG_WALLS);
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(GenesisTags.Blocks.LOG_WALLS);
        this.tag(BlockTags.SAPLINGS).add(
                GenesisBlocks.BLUE_SKYROOT_SAPLING.get(),
                GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(),
                GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get());
        this.tag(BlockTags.LEAVES).add(
                GenesisBlocks.BLUE_SKYROOT_LEAVES.get(),
                GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get(),
                GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get(),
                GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get());
        this.tag(BlockTags.FLOWER_POTS).add(
                GenesisBlocks.POTTED_BLUE_SKYROOT_SAPLING.get(),
                GenesisBlocks.POTTED_DARK_BLUE_SKYROOT_SAPLING.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                GenesisBlocks.GREEN_AERCLOUD.get(),
                GenesisBlocks.PURPLE_AERCLOUD.get(),
                GenesisBlocks.STORM_AERCLOUD.get(),
                GenesisBlocks.BLUE_SKYROOT_LEAVES.get(),
                GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get(),
                GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get(),
                GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get());
        this.tag(BlockTags.WALLS).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get());
        this.tag(BlockTags.SNAPS_GOAT_HORN).addTag(GenesisTags.Blocks.LOG_WALLS);
    }
}
