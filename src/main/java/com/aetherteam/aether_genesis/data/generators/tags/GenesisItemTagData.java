package com.aetherteam.aether_genesis.data.generators.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.item.GenesisItems;
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
        this.tag(AetherTags.Items.AETHER_ACCESSORY).add(
                GenesisItems.CRYSTAL_EXP_BOTTLE.get());
        this.tag(AetherTags.Items.AETHER_RING).add(
                GenesisItems.BONE_RING.get(),
                GenesisItems.CANDY_RING.get());
        this.tag(GenesisTags.Items.SWET_JELLY).add(
                GenesisItems.BLUE_SWET_JELLY.get(),
                GenesisItems.DARK_SWET_JELLY.get(),
                GenesisItems.GOLDEN_SWET_JELLY.get());
        this.tag(GenesisTags.Items.DARTS).add(
                AetherItems.GOLDEN_DART.get(),
                AetherItems.POISON_DART.get(),
                AetherItems.ENCHANTED_DART.get());
    }
}
