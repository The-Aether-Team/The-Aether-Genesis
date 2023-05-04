package com.aetherteam.genesis.data.generators.tags;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.GenesisTags;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.item.GenesisItems;
import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenesisItemTagData extends ItemTagsProvider {
    public GenesisItemTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, Genesis.MODID, helper);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.copy(GenesisTags.Blocks.LOG_WALLS, GenesisTags.Items.LOG_WALLS);

        this.tag(AetherTags.Items.CRAFTS_SKYROOT_PLANKS).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get().asItem(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get().asItem(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get().asItem(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get().asItem());
        this.tag(AetherTags.Items.SWET_BALLS).add(
                GenesisItems.GOLDEN_SWET_BALL.get(),
                GenesisItems.DARK_SWET_BALL.get());
        this.tag(AetherTags.Items.DEPLOYABLE_PARACHUTES).add(
                GenesisItems.GREEN_PARACHUTE.get(),
                GenesisItems.PURPLE_PARACHUTE.get());

        this.tag(ItemTags.SAPLINGS).add(
                GenesisBlocks.BLUE_SKYROOT_SAPLING.get().asItem(),
                GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get().asItem(),
                GenesisBlocks.CRYSTAL_TREE_SAPLING.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get().asItem());
        this.tag(ItemTags.LEAVES).add(
                GenesisBlocks.BLUE_SKYROOT_LEAVES.get().asItem(),
                GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get().asItem());
        this.tag(ItemTags.WALLS).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get().asItem(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get().asItem(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get().asItem(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get().asItem());
    }
}
