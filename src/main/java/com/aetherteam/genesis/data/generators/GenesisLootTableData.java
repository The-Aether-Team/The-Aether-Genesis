package com.aetherteam.genesis.data.generators;

import com.aetherteam.genesis.data.generators.loot.GenesisBlockLoot;
import com.aetherteam.genesis.data.generators.loot.GenesisChestLoot;
import com.aetherteam.genesis.data.generators.loot.GenesisEntityLoot;
import com.aetherteam.genesis.data.generators.loot.GenesisSelectorLoot;
import com.aetherteam.genesis.loot.GenesisLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GenesisLootTableData {
    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        return new LootTableProvider(output, GenesisLoot.IMMUTABLE_LOOT_TABLES, List.of(
                new LootTableProvider.SubProviderEntry(GenesisChestLoot::new, LootContextParamSets.CHEST),
                new LootTableProvider.SubProviderEntry(GenesisEntityLoot::new, LootContextParamSets.ENTITY),
                new LootTableProvider.SubProviderEntry(GenesisBlockLoot::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(provider -> new GenesisSelectorLoot(), LootContextParamSets.SELECTOR)
        ), registries);
    }
}
