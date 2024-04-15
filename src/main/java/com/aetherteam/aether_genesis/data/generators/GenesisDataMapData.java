package com.aetherteam.aether_genesis.data.generators;

import com.aetherteam.aether_genesis.block.GenesisBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class GenesisDataMapData extends DataMapProvider {
    public GenesisDataMapData(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void gather() {
        var compostables = this.builder(NeoForgeDataMaps.COMPOSTABLES);
        this.addCompost(compostables, GenesisBlocks.BLUE_SKYROOT_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, GenesisBlocks.BLUE_SKYROOT_SAPLING.get(), 0.3F);
        this.addCompost(compostables, GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(), 0.3F);
        this.addCompost(compostables, GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get(), 0.3F);
        this.addCompost(compostables, GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), 0.3F);
    }

    private void addCompost(DataMapProvider.Builder<Compostable, Item> map, ItemLike item, float chance) {
        map.add(item.asItem().builtInRegistryHolder(), new Compostable(chance), false);
    }
}
