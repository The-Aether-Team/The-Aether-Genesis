package com.aetherteam.aether_genesis.data.generators.loot;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether_genesis.loot.GenesisLoot;
import com.aetherteam.aether_genesis.loot.entries.RandomEntry;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
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
                                        LootItem.lootTableItem(Blocks.OAK_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.SPRUCE_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.BIRCH_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.JUNGLE_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.ACACIA_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.DARK_OAK_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.MANGROVE_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.CHERRY_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.CRIMSON_STEM).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.WARPED_STEM).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.MUSHROOM_STEM).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(AetherBlocks.SKYROOT_LOG.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F))),
                                        LootItem.lootTableItem(AetherBlocks.GOLDEN_OAK_LOG.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F)))
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
                                        LootItem.lootTableItem(Blocks.GRANITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.DEEPSLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.CALCITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.TUFF).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.DRIPSTONE_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.SANDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.RED_SANDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.PRISMARINE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.NETHERRACK).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.BASALT).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.BLACKSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.END_STONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.MAGMA_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.OBSIDIAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.GLOWSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_ORE,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.NETHERITE_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))), //todo
                                        LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.LAPIS_LAZULI).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.QUARTZ).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_NATURAL,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.DIRT).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.MUD).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.CLAY).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.GRAVEL).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.SAND).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.RED_SAND).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.ICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.SNOW).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.MOSS_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.SCULK).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.SOUL_SOIL).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.TERRACOTTA).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.GLASS).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.SPONGE).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_FLOWER,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.FLOWERING_AZALEA).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F))),
                                        LootItem.lootTableItem(Blocks.FLOWERING_AZALEA).apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F)))
                                )
                        )
                )
        );

    }
}
