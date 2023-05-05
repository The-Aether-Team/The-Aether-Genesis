package com.aetherteam.aether_genesis.data.generators;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.data.providers.GenesisItemModelProvider;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GenesisItemModelData extends GenesisItemModelProvider {
    public GenesisItemModelData(PackOutput output, ExistingFileHelper helper) {
        super(output, Genesis.MODID, helper);
    }

    @Override
    protected void registerModels() {
        this.item(GenesisItems.GOLDEN_SWET_BALL.get(), "materials/");
        this.item(GenesisItems.DARK_SWET_BALL.get(), "materials/");

        this.item(GenesisItems.DARK_GUMMY_SWET.get(), "food/");
        this.item(GenesisItems.BLUE_SWET_JELLY.get(), "food/");
        this.item(GenesisItems.GOLDEN_SWET_JELLY.get(), "food/");
        this.item(GenesisItems.DARK_SWET_JELLY.get(), "food/");

        this.item(GenesisItems.BLUE_PARACHUTE.get(), "miscellaneous/");
        this.item(GenesisItems.GREEN_PARACHUTE.get(), "miscellaneous/");
        this.item(GenesisItems.PURPLE_PARACHUTE.get(), "miscellaneous/");

        this.eggItem(GenesisItems.DARK_SWET_SPAWN_EGG.get());

        this.itemBlock(GenesisBlocks.GREEN_AERCLOUD.get());
        this.itemBlock(GenesisBlocks.PURPLE_AERCLOUD.get());
        this.itemBlock(GenesisBlocks.STORM_AERCLOUD.get());
        this.itemBlock(GenesisBlocks.BLUE_SKYROOT_LEAVES.get());
        this.itemBlock(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get());
        this.itemBlock(GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get());
        this.itemBlock(GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get());
        this.itemBlockFlat(GenesisBlocks.BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.itemBlockFlat(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.itemBlockFlat(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), "natural/");

        this.itemLogWallBlock(GenesisBlocks.SKYROOT_LOG_WALL.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/", Aether.MODID);
        this.itemLogWallBlock(GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(), AetherBlocks.STRIPPED_SKYROOT_LOG.get(), "natural/", Aether.MODID);
        this.itemWoodWallBlock(GenesisBlocks.SKYROOT_WOOD_WALL.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/", Aether.MODID);
        this.itemWoodWallBlock(GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get(), AetherBlocks.STRIPPED_SKYROOT_LOG.get(), "natural/", Aether.MODID);
    }
}
