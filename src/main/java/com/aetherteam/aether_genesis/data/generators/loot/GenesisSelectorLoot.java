package com.aetherteam.aether_genesis.data.generators.loot;

import com.aetherteam.aether_genesis.loot.GenesisLoot;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

public class GenesisSelectorLoot implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        builder.accept(GenesisLoot.CONTINUUM_ORB, LootTable.lootTable()
                .withPool(continuumOrbLoot())
        );
    }

    private static LootPool.Builder continuumOrbLoot() {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1));
    }
}
