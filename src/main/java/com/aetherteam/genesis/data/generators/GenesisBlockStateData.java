package com.aetherteam.genesis.data.generators;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.data.providers.GenesisBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class GenesisBlockStateData extends GenesisBlockStateProvider {
    public GenesisBlockStateData(PackOutput output, ExistingFileHelper helper) {
        super(output, AetherGenesis.MODID, helper);
    }

    @Override
    public void registerStatesAndModels() {
        this.translucentBlock(GenesisBlocks.GREEN_AERCLOUD.get(), "natural/");
        this.purpleAercloud(GenesisBlocks.PURPLE_AERCLOUD.get());
        this.translucentBlock(GenesisBlocks.STORM_AERCLOUD.get(), "natural/");
        this.block(GenesisBlocks.CONTINUUM_ORE.get(), "natural/");
        this.block(GenesisBlocks.BLUE_SKYROOT_LEAVES.get(), "natural/");
        this.block(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get(), "natural/");
        this.block(GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get(), "natural/");
        this.block(GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get(), "natural/");
        this.orangeTree(GenesisBlocks.ORANGE_TREE.get());
        this.pottedOrangeTree(GenesisBlocks.POTTED_ORANGE_TREE.get(), GenesisBlocks.ORANGE_TREE.get());
        this.saplingBlock(GenesisBlocks.BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.saplingBlock(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.saplingBlock(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), "natural/");
        this.pottedPlant(GenesisBlocks.POTTED_BLUE_SKYROOT_SAPLING.get(), GenesisBlocks.BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.pottedPlant(GenesisBlocks.POTTED_DARK_BLUE_SKYROOT_SAPLING.get(), GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.pottedPlant(GenesisBlocks.POTTED_PURPLE_CRYSTAL_TREE_SAPLING.get(), GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), "natural/");

        this.dungeonPillar(GenesisBlocks.CARVED_PILLAR.get());
        this.dungeonPillarTop(GenesisBlocks.CARVED_PILLAR_TOP.get());

        this.block(GenesisBlocks.DIVINE_CARVED_STONE.get(), "dungeon/");
        this.block(GenesisBlocks.DIVINE_SENTRY_STONE.get(), "dungeon/");
        this.dungeonBlock(GenesisBlocks.LOCKED_DIVINE_CARVED_STONE.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.dungeonBlock(GenesisBlocks.LOCKED_DIVINE_SENTRY_STONE.get(), GenesisBlocks.DIVINE_SENTRY_STONE.get());
        this.dungeonBlock(GenesisBlocks.TRAPPED_DIVINE_CARVED_STONE.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.dungeonBlock(GenesisBlocks.TRAPPED_DIVINE_SENTRY_STONE.get(), GenesisBlocks.DIVINE_SENTRY_STONE.get());
        this.invisibleBlock(GenesisBlocks.BOSS_DOORWAY_DIVINE_CARVED_STONE.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.invisibleBlock(GenesisBlocks.BOSS_DOORWAY_DIVINE_SENTRY_STONE.get(), GenesisBlocks.DIVINE_SENTRY_STONE.get());
        this.dungeonBlock(GenesisBlocks.TREASURE_DOORWAY_DIVINE_CARVED_STONE.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.dungeonBlock(GenesisBlocks.TREASURE_DOORWAY_DIVINE_SENTRY_STONE.get(), GenesisBlocks.DIVINE_SENTRY_STONE.get());

        this.wallBlock(GenesisBlocks.DIVINE_CARVED_WALL.get(), GenesisBlocks.DIVINE_CARVED_STONE.get(), "dungeon/");
        this.stairs(GenesisBlocks.DIVINE_CARVED_STAIRS.get(), GenesisBlocks.DIVINE_CARVED_STONE.get(), "dungeon/");
        this.slab(GenesisBlocks.DIVINE_CARVED_SLAB.get(), GenesisBlocks.DIVINE_CARVED_STONE.get(), "dungeon/");

        this.block(GenesisBlocks.BLOOD_MOSS_HOLYSTONE.get(), "dungeon/");

        this.coldFire(GenesisBlocks.COLD_FIRE.get());
    }
}
