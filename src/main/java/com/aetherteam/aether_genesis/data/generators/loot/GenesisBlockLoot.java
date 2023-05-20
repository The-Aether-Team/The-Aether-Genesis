package com.aetherteam.aether_genesis.data.generators.loot;

import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.data.providers.GenesisBlockLootSubProvider;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.mixin.mixins.common.accessor.BlockLootAccessor;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

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
        this.dropSelfDouble(GenesisBlocks.GREEN_AERCLOUD.get());
        this.dropSelfDouble(GenesisBlocks.PURPLE_AERCLOUD.get());
        this.dropSelfDouble(GenesisBlocks.STORM_AERCLOUD.get());
        this.add(GenesisBlocks.BLUE_SKYROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, GenesisBlocks.BLUE_SKYROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.add(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.add(GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.add(GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndFruitAndSkyrootSticks(leaves, GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), AetherItems.WHITE_APPLE.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
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
        this.dropSelf(GenesisBlocks.AETHER_CRAFTING_TABLE.get());
        this.dropSelf(GenesisBlocks.HOLYSTONE_FURNACE.get());
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return GenesisBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
    }
}
