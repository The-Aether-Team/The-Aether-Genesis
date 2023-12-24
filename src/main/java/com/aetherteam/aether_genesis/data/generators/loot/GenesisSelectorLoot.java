package com.aetherteam.aether_genesis.data.generators.loot;

import com.aetherteam.aether_genesis.loot.GenesisLoot;
import com.aetherteam.aether_genesis.loot.entries.RandomEntry;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class GenesisSelectorLoot implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        builder.accept(GenesisLoot.CONTINUUM_ORB,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_WOOD),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_STONE)
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_WOOD,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_WOOD_PLANKS),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_WOOD_LOGS)
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_WOOD_PLANKS,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.OAK_PLANKS).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 24.0F))),
                                        LootItem.lootTableItem(Blocks.SPRUCE_PLANKS).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 24.0F))),
                                        LootItem.lootTableItem(Blocks.BIRCH_PLANKS).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 24.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_WOOD_LOGS,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.OAK_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.SPRUCE_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.BIRCH_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_STONE,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.STONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.ANDESITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.DIORITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.GRANITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F)))
                                )
                        )
                )
        );

    }
}
