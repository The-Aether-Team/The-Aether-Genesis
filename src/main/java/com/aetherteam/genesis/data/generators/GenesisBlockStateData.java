package com.aetherteam.genesis.data.generators;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.data.providers.GenesisBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class GenesisBlockStateData extends GenesisBlockStateProvider {
    public GenesisBlockStateData(PackOutput output, ExistingFileHelper helper) {
        super(output, AetherGenesis.MODID, helper);
    }

    @Override
    public void registerStatesAndModels() {
        this.enchantedVanillaGrass(GenesisBlocks.ENCHANTED_GRASS_BLOCK.get(), Blocks.GRASS_BLOCK, Blocks.DIRT);
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
        this.holystonePillar(GenesisBlocks.HOLYSTONE_HEADSTONE.get());
        this.holystonePillar(GenesisBlocks.HOLYSTONE_KEYSTONE.get());
        this.holystonePillar(GenesisBlocks.HOLYSTONE_HIGHLIGHT.get());
        this.pottedPlant(GenesisBlocks.POTTED_BLUE_SKYROOT_SAPLING.get(), GenesisBlocks.BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.pottedPlant(GenesisBlocks.POTTED_DARK_BLUE_SKYROOT_SAPLING.get(), GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.pottedPlant(GenesisBlocks.POTTED_PURPLE_CRYSTAL_TREE_SAPLING.get(), GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), "natural/");
        this.makeLogWalls();
        this.skyrootCraftingTable(GenesisBlocks.SKYROOT_CRAFTING_TABLE.get(), AetherBlocks.SKYROOT_PLANKS.get(), "construction/", Aether.MODID);
        this.furnace(GenesisBlocks.HOLYSTONE_FURNACE.get());
        this.skyrootChest(GenesisBlocks.SKYROOT_CHEST.get());
        this.skyrootLadder(GenesisBlocks.SKYROOT_LADDER.get());

        this.dungeonPillar(GenesisBlocks.CARVED_PILLAR.get());
        this.dungeonPillarTop(GenesisBlocks.CARVED_PILLAR_TOP.get());
        this.skyrootChest(GenesisBlocks.SKYROOT_CHEST_MIMIC.get());

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

    private void makeLogWalls() {
        ModelFile postBig = this.makeWallPostModel(4, 16, "wooden_post_big");
        ModelFile postShort = this.makeWallPostModel(3, 14, "wooden_post_short");
        ModelFile postTall = this.makeWallPostModel(3, 16, "wooden_post_tall");

        ModelFile side = this.makeWallSideModel(5, 14, "wooden_side", ModelBuilder.FaceRotation.CLOCKWISE_90, 0, 5);
        ModelFile sideAlt = this.makeWallSideModel(5, 14, "wooden_side_alt", ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90, 11, 16);
        ModelFile sideTall = this.makeWallSideModel(5, 16, "wooden_side_tall", ModelBuilder.FaceRotation.CLOCKWISE_90, 0, 5);
        ModelFile sideTallAlt = this.makeWallSideModel(5, 16, "wooden_side_tall_alt", ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90, 11, 16);

        ModelFile sideShort = this.makeWallSideModel(4, 14, "wooden_side_short", ModelBuilder.FaceRotation.CLOCKWISE_90, 0, 4);
        ModelFile sideAltShort = this.makeWallSideModel(4, 14, "wooden_side_alt_short", ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90, 12, 16);
        ModelFile sideTallShort = this.makeWallSideModel(4, 16, "wooden_side_tall_short", ModelBuilder.FaceRotation.CLOCKWISE_90, 0, 4);
        ModelFile sideTallAltShort = this.makeWallSideModel(4, 16, "wooden_side_tall_alt_short", ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90, 12, 16);

        this.logWallBlock(GenesisBlocks.SKYROOT_LOG_WALL.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/", Aether.MODID, true, postBig, postShort, postTall, side, sideAlt, sideTall, sideTallAlt, sideShort, sideAltShort, sideTallShort, sideTallAltShort);
        this.logWallBlock(GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(), AetherBlocks.STRIPPED_SKYROOT_LOG.get(), "natural/", Aether.MODID, true, postBig, postShort, postTall, side, sideAlt, sideTall, sideTallAlt, sideShort, sideAltShort, sideTallShort, sideTallAltShort);
        this.logWallBlock(GenesisBlocks.SKYROOT_WOOD_WALL.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/", Aether.MODID, false, postBig, postShort, postTall, side, sideAlt, sideTall, sideTallAlt, sideShort, sideAltShort, sideTallShort, sideTallAltShort);
        this.logWallBlock(GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get(), AetherBlocks.STRIPPED_SKYROOT_LOG.get(), "natural/", Aether.MODID, false, postBig, postShort, postTall, side, sideAlt, sideTall, sideTallAlt, sideShort, sideAltShort, sideTallShort, sideTallAltShort);
    }
}
