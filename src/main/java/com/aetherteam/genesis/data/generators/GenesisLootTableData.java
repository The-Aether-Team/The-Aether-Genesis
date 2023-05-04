package com.aetherteam.genesis.data.generators;

import com.aetherteam.genesis.data.generators.loot.GenesisBlockLoot;
import com.aetherteam.genesis.data.generators.loot.GenesisEntityLoot;
import com.aetherteam.genesis.loot.GenesisLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class GenesisLootTableData {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, GenesisLoot.IMMUTABLE_LOOT_TABLES, List.of(
                new LootTableProvider.SubProviderEntry(GenesisEntityLoot::new, LootContextParamSets.ENTITY),
                new LootTableProvider.SubProviderEntry(GenesisBlockLoot::new, LootContextParamSets.BLOCK)
        ));
    }
}
