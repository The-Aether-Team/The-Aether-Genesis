package com.aetherteam.aether_genesis.data.generators;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.data.providers.GenesisBlockStateProvider;
import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GenesisBlockStateData extends GenesisBlockStateProvider {
    public GenesisBlockStateData(PackOutput output, ExistingFileHelper helper) {
        super(output, Genesis.MODID, helper);
    }

    @Override
    public void registerStatesAndModels() {
        this.translucentBlock(GenesisBlocks.GREEN_AERCLOUD.get(), "natural/");
        this.purpleAercloud(GenesisBlocks.PURPLE_AERCLOUD.get());
        this.translucentBlock(GenesisBlocks.STORM_AERCLOUD.get(), "natural/");
        this.block(GenesisBlocks.BLUE_SKYROOT_LEAVES.get(), "natural/");
        this.block(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get(), "natural/");
        this.block(GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get(), "natural/");
        this.block(GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get(), "natural/");
        this.saplingBlock(GenesisBlocks.BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.saplingBlock(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.saplingBlock(GenesisBlocks.CRYSTAL_TREE_SAPLING.get(), "natural/");
        this.saplingBlock(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), "natural/");
        this.pottedPlant(GenesisBlocks.POTTED_BLUE_SKYROOT_SAPLING.get(), GenesisBlocks.BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.pottedPlant(GenesisBlocks.POTTED_DARK_BLUE_SKYROOT_SAPLING.get(), GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.logWallBlock(GenesisBlocks.SKYROOT_LOG_WALL.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/", Aether.MODID);
        this.logWallBlock(GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(), AetherBlocks.STRIPPED_SKYROOT_LOG.get(), "natural/", Aether.MODID);
        this.woodWallBlock(GenesisBlocks.SKYROOT_WOOD_WALL.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/", Aether.MODID);
        this.woodWallBlock(GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get(), AetherBlocks.STRIPPED_SKYROOT_LOG.get(), "natural/", Aether.MODID);
    }
}
