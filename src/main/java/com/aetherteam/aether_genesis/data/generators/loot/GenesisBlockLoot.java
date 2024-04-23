package com.aetherteam.aether_genesis.data.generators.loot;

import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.mixin.mixins.common.accessor.BlockLootAccessor;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.data.providers.GenesisBlockLootSubProvider;
import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GenesisBlockLoot extends GenesisBlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = new HashSet<>();

    public GenesisBlockLoot() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(GenesisBlocks.ENCHANTED_GRASS_BLOCK.get(), (block) -> this.createSingleItemTableWithSilkTouch(block, Blocks.DIRT));
        this.dropSelfDouble(GenesisBlocks.GREEN_AERCLOUD.get());
        this.dropSelfDouble(GenesisBlocks.PURPLE_AERCLOUD.get());
        this.dropSelfDouble(GenesisBlocks.STORM_AERCLOUD.get());
        this.add(GenesisBlocks.CONTINUUM_ORE.get(), (block) -> this.createOreDrop(block, GenesisItems.CONTINUUM_ORB.get()));
        this.dropSelf(GenesisBlocks.HOLYSTONE_HEADSTONE.get());
        this.dropSelf(GenesisBlocks.HOLYSTONE_KEYSTONE.get());
        this.dropSelf(GenesisBlocks.HOLYSTONE_HIGHLIGHT.get());
        this.add(GenesisBlocks.BLUE_SKYROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, GenesisBlocks.BLUE_SKYROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.add(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.add(GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.add(GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndFruitAndSkyrootSticks(leaves, GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), AetherItems.WHITE_APPLE.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.add(GenesisBlocks.ORANGE_TREE.get(), (tree) -> this.droppingOrangeTree(tree, GenesisItems.ORANGE.get()));
        this.dropPottedContents(GenesisBlocks.POTTED_ORANGE_TREE.get());
        this.dropSelf(GenesisBlocks.BLUE_SKYROOT_SAPLING.get());
        this.dropSelf(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get());
        this.dropSelf(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get());
        this.dropPottedContents(GenesisBlocks.POTTED_BLUE_SKYROOT_SAPLING.get());
        this.dropPottedContents(GenesisBlocks.POTTED_DARK_BLUE_SKYROOT_SAPLING.get());
        this.dropPottedContents(GenesisBlocks.POTTED_PURPLE_CRYSTAL_TREE_SAPLING.get());
        this.dropSelfDouble(GenesisBlocks.SKYROOT_LOG_WALL.get());
        this.dropSelf(GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get());
        this.dropSelfDouble(GenesisBlocks.SKYROOT_WOOD_WALL.get());
        this.dropSelf(GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get());
        this.dropSelf(GenesisBlocks.SKYROOT_CRAFTING_TABLE.get());
        this.dropSelf(GenesisBlocks.HOLYSTONE_FURNACE.get());
        this.dropSelf(GenesisBlocks.SKYROOT_CHEST.get());
        this.dropSelf(GenesisBlocks.SKYROOT_LADDER.get());

        this.dropSelf(GenesisBlocks.CARVED_PILLAR_TOP.get());
        this.dropSelf(GenesisBlocks.CARVED_PILLAR.get());
        this.dropSelf(GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.dropSelf(GenesisBlocks.DIVINE_SENTRY_STONE.get());
        this.dropNone(GenesisBlocks.LOCKED_DIVINE_CARVED_STONE.get());
        this.dropNone(GenesisBlocks.LOCKED_DIVINE_SENTRY_STONE.get());
        this.dropNone(GenesisBlocks.TRAPPED_DIVINE_CARVED_STONE.get());
        this.dropNone(GenesisBlocks.TRAPPED_DIVINE_SENTRY_STONE.get());
        this.dropNone(GenesisBlocks.BOSS_DOORWAY_DIVINE_CARVED_STONE.get());
        this.dropNone(GenesisBlocks.BOSS_DOORWAY_DIVINE_SENTRY_STONE.get());
        this.dropNone(GenesisBlocks.TREASURE_DOORWAY_DIVINE_CARVED_STONE.get());
        this.dropNone(GenesisBlocks.TREASURE_DOORWAY_DIVINE_SENTRY_STONE.get());
        this.dropSelf(GenesisBlocks.DIVINE_CARVED_WALL.get());
        this.dropSelf(GenesisBlocks.DIVINE_CARVED_STAIRS.get());
        this.dropSelf(GenesisBlocks.DIVINE_CARVED_SLAB.get());

        this.dropNone(GenesisBlocks.COLD_FIRE.get());
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return GenesisBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
    }
}
