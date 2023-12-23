package com.aetherteam.aether_genesis.data.generators.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenesisBlockTagData extends BlockTagsProvider {
    public GenesisBlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, Genesis.MODID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(GenesisTags.Blocks.LOG_WALLS).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get());
        this.tag(GenesisTags.Blocks.CARRION_SPROUT_SPAWNABLE_ON).add(AetherBlocks.AETHER_GRASS_BLOCK.get());

        this.tag(AetherTags.Blocks.AETHER_PORTAL_BLACKLIST).add(
                GenesisBlocks.GREEN_AERCLOUD.get(),
                GenesisBlocks.PURPLE_AERCLOUD.get());
        this.tag(AetherTags.Blocks.AERCLOUDS).add(
                GenesisBlocks.GREEN_AERCLOUD.get(),
                GenesisBlocks.PURPLE_AERCLOUD.get(),
                GenesisBlocks.STORM_AERCLOUD.get());
        this.tag(AetherTags.Blocks.ENCHANTED_GRASS).add(
                GenesisBlocks.ENCHANTED_GRASS_BLOCK.get());
        this.tag(AetherTags.Blocks.NON_BRONZE_DUNGEON_REPLACEABLE).add(
                GenesisBlocks.SKYROOT_CHEST_MIMIC.get()
        );
        this.tag(AetherTags.Blocks.DUNGEON_BLOCKS).add(
                GenesisBlocks.DIVINE_CARVED_STONE.get(),
                GenesisBlocks.DIVINE_SENTRY_STONE.get(),
                GenesisBlocks.CARVED_PILLAR.get(),
                GenesisBlocks.CARVED_PILLAR_SIDE.get()
        );
        this.tag(AetherTags.Blocks.LOCKED_DUNGEON_BLOCKS).add(
                GenesisBlocks.LOCKED_DIVINE_CARVED_STONE.get(),
                GenesisBlocks.LOCKED_DIVINE_SENTRY_STONE.get()
        );
        this.tag(AetherTags.Blocks.TRAPPED_DUNGEON_BLOCKS).add(
                GenesisBlocks.TRAPPED_DIVINE_CARVED_STONE.get(),
                GenesisBlocks.TRAPPED_DIVINE_SENTRY_STONE.get()
        );
        this.tag(AetherTags.Blocks.BOSS_DOORWAY_DUNGEON_BLOCKS).add(
                GenesisBlocks.BOSS_DOORWAY_DIVINE_CARVED_STONE.get(),
                GenesisBlocks.BOSS_DOORWAY_DIVINE_SENTRY_STONE.get()
        );
        this.tag(AetherTags.Blocks.TREASURE_DOORWAY_DUNGEON_BLOCKS).add(
                GenesisBlocks.TREASURE_DOORWAY_DIVINE_CARVED_STONE.get(),
                GenesisBlocks.TREASURE_DOORWAY_DIVINE_SENTRY_STONE.get()
        );
        this.tag(AetherTags.Blocks.SENTRY_BLOCKS).add(
                GenesisBlocks.DIVINE_CARVED_STONE.get(),
                GenesisBlocks.DIVINE_SENTRY_STONE.get(),
                GenesisBlocks.LOCKED_DIVINE_CARVED_STONE.get(),
                GenesisBlocks.LOCKED_DIVINE_SENTRY_STONE.get(),
                GenesisBlocks.TRAPPED_DIVINE_CARVED_STONE.get(),
                GenesisBlocks.TRAPPED_DIVINE_SENTRY_STONE.get(),
                GenesisBlocks.BOSS_DOORWAY_DIVINE_CARVED_STONE.get(),
                GenesisBlocks.BOSS_DOORWAY_DIVINE_SENTRY_STONE.get(),
                GenesisBlocks.TREASURE_DOORWAY_DIVINE_CARVED_STONE.get(),
                GenesisBlocks.TREASURE_DOORWAY_DIVINE_SENTRY_STONE.get(),
                GenesisBlocks.CARVED_PILLAR.get(),
                GenesisBlocks.CARVED_PILLAR_SIDE.get(),
                GenesisBlocks.DIVINE_CARVED_WALL.get(),
                GenesisBlocks.DIVINE_CARVED_STAIRS.get(),
                GenesisBlocks.DIVINE_CARVED_SLAB.get()
        );

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
        this.tag(BlockTags.DIRT).add(
                GenesisBlocks.ENCHANTED_GRASS_BLOCK.get());
        this.tag(BlockTags.FLOWER_POTS).add(
                GenesisBlocks.POTTED_BLUE_SKYROOT_SAPLING.get(),
                GenesisBlocks.POTTED_DARK_BLUE_SKYROOT_SAPLING.get(),
                GenesisBlocks.POTTED_PURPLE_CRYSTAL_TREE_SAPLING.get(),
                GenesisBlocks.POTTED_ORANGE_TREE.get());
        this.tag(BlockTags.ENDERMAN_HOLDABLE).add(
                GenesisBlocks.ENCHANTED_GRASS_BLOCK.get());
        this.tag(BlockTags.VALID_SPAWN).add(
                GenesisBlocks.ENCHANTED_GRASS_BLOCK.get());
        this.tag(BlockTags.BAMBOO_PLANTABLE_ON).add(
                GenesisBlocks.ENCHANTED_GRASS_BLOCK.get());
        this.tag(BlockTags.FIRE).add(
                GenesisBlocks.COLD_FIRE.get());
        this.tag(BlockTags.CLIMBABLE).add(
                GenesisBlocks.SKYROOT_LADDER.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                GenesisBlocks.CONTINUUM_ORE.get(),
                GenesisBlocks.HOLYSTONE_FURNACE.get(),
                GenesisBlocks.HOLYSTONE_HEADSTONE.get(),
                GenesisBlocks.HOLYSTONE_KEYSTONE.get(),
                GenesisBlocks.HOLYSTONE_HIGHLIGHT.get()
        );
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get(),
                GenesisBlocks.SKYROOT_CHEST.get(),
                GenesisBlocks.SKYROOT_CRAFTING_TABLE.get(),
                GenesisBlocks.SKYROOT_LADDER.get(),
                GenesisBlocks.SKYROOT_CHEST_MIMIC.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                GenesisBlocks.ENCHANTED_GRASS_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                GenesisBlocks.GREEN_AERCLOUD.get(),
                GenesisBlocks.PURPLE_AERCLOUD.get(),
                GenesisBlocks.STORM_AERCLOUD.get(),
                GenesisBlocks.BLUE_SKYROOT_LEAVES.get(),
                GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get(),
                GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get(),
                GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get(),
                GenesisBlocks.ORANGE_TREE.get());
        this.tag(BlockTags.STAIRS).add(
                GenesisBlocks.DIVINE_CARVED_STAIRS.get());
        this.tag(BlockTags.SLABS).add(
                GenesisBlocks.DIVINE_CARVED_SLAB.get());
        this.tag(BlockTags.WALLS).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get(),
                GenesisBlocks.DIVINE_CARVED_WALL.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(GenesisBlocks.CONTINUUM_ORE.get());
        this.tag(BlockTags.SNAPS_GOAT_HORN).addTag(GenesisTags.Blocks.LOG_WALLS);
        this.tag(BlockTags.CONVERTABLE_TO_MUD).add(
                GenesisBlocks.ENCHANTED_GRASS_BLOCK.get());
        this.tag(BlockTags.SCULK_REPLACEABLE).add(
                GenesisBlocks.ENCHANTED_GRASS_BLOCK.get());

        // Forge
        this.tag(Tags.Blocks.CHESTS_WOODEN).add(GenesisBlocks.SKYROOT_CHEST.get());
    }
}
