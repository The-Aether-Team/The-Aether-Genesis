package com.aetherteam.aether_genesis.data.generators.loot;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.item.GenesisItems;
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
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
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
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_STONE),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_ORE),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_NATURAL),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_FLOWER),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_PLANT),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_FARMABLE),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_LEAVES),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_CORAL),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_MATERIALS),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_DROPS),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_DYES),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_DISCS),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_TEMPLATES),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_BOOKS),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_PATTERNS),
                                        LootTableReference.lootTableReference(GenesisLoot.CONTINUUM_ORB_SHERDS)
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_WOOD,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.OAK_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.SPRUCE_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.BIRCH_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.JUNGLE_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.ACACIA_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.DARK_OAK_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.MANGROVE_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.CHERRY_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.CRIMSON_STEM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.WARPED_STEM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.MUSHROOM_STEM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(AetherBlocks.SKYROOT_LOG.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(AetherBlocks.GOLDEN_OAK_LOG.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_STONE,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.STONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.ANDESITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.DIORITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.GRANITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.DEEPSLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.CALCITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.TUFF).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.DRIPSTONE_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.SANDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.RED_SANDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.PRISMARINE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))),
                                        LootItem.lootTableItem(Blocks.NETHERRACK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.BASALT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.BLACKSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.END_STONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))),
                                        LootItem.lootTableItem(Blocks.MAGMA_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Blocks.OBSIDIAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Blocks.GLOWSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(AetherBlocks.HOLYSTONE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(AetherBlocks.ICESTONE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(AetherBlocks.CARVED_STONE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(AetherBlocks.ANGELIC_STONE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(AetherBlocks.HELLFIRE_STONE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_ORE,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Items.COAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.NETHERITE_SCRAP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Items.LAPIS_LAZULI).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Items.QUARTZ).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))),
                                        LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(AetherItems.ZANITE_GEMSTONE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(AetherBlocks.ENCHANTED_GRAVITITE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(AetherItems.GOLDEN_AMBER.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_NATURAL,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.GRASS_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.MUD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.CLAY).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.GRAVEL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.SAND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.RED_SAND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.ICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.SNOW).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.MOSS_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Blocks.SCULK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Blocks.CRIMSON_NYLIUM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Blocks.WARPED_NYLIUM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Blocks.SOUL_SOIL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Blocks.BONE_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Blocks.TERRACOTTA).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Blocks.GLASS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.SPONGE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(AetherBlocks.AETHER_GRASS_BLOCK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(AetherBlocks.QUICKSOIL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(AetherBlocks.COLD_AERCLOUD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(AetherBlocks.BLUE_AERCLOUD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))),
                                        LootItem.lootTableItem(AetherBlocks.GOLDEN_AERCLOUD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(GenesisBlocks.GREEN_AERCLOUD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))),
                                        LootItem.lootTableItem(GenesisBlocks.PURPLE_AERCLOUD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))),
                                        LootItem.lootTableItem(GenesisBlocks.STORM_AERCLOUD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))),
                                        LootItem.lootTableItem(AetherBlocks.AEROGEL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(AetherBlocks.PRESENT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Blocks.MANGROVE_ROOTS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.COBWEB).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_FLOWER,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.AZALEA).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.FLOWERING_AZALEA).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.DANDELION).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.POPPY).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.BLUE_ORCHID).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.ALLIUM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.AZURE_BLUET).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.RED_TULIP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.ORANGE_TULIP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.WHITE_TULIP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.PINK_TULIP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.OXEYE_DAISY).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.CORNFLOWER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.LILY_OF_THE_VALLEY).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.TORCHFLOWER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.WITHER_ROSE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.PINK_PETALS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.SPORE_BLOSSOM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.BROWN_MUSHROOM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.RED_MUSHROOM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.CRIMSON_FUNGUS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.WARPED_FUNGUS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.SUNFLOWER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.LILAC).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.ROSE_BUSH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.PEONY).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.PITCHER_PLANT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(AetherBlocks.PURPLE_FLOWER.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(AetherBlocks.WHITE_FLOWER.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_PLANT,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.FERN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.DEAD_BUSH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.VINE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.GLOW_LICHEN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.HANGING_ROOTS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.LILY_PAD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.SEAGRASS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.SEA_PICKLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.SMALL_DRIPLEAF).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.BIG_DRIPLEAF).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.MOSS_CARPET).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.SCULK_VEIN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.CRIMSON_ROOTS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.WARPED_ROOTS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.NETHER_SPROUTS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.WEEPING_VINES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.TWISTING_VINES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.SHROOMLIGHT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.SCULK_SENSOR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_FARMABLE,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.OAK_SAPLING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.SPRUCE_SAPLING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.BIRCH_SAPLING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.JUNGLE_SAPLING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.ACACIA_SAPLING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.DARK_OAK_SAPLING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.MANGROVE_PROPAGULE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.CHERRY_SAPLING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.BAMBOO).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Blocks.SUGAR_CANE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.CACTUS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.CHORUS_FLOWER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.COCOA_BEANS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.PUMPKIN_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.MELON_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.BEETROOT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.TORCHFLOWER_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.PITCHER_POD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.GLOW_BERRIES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.SWEET_BERRIES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Items.KELP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Items.NETHER_WART).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(AetherBlocks.SKYROOT_SAPLING.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(AetherBlocks.GOLDEN_OAK_SAPLING.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(GenesisBlocks.BLUE_SKYROOT_SAPLING.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(AetherBlocks.BERRY_BUSH_STEM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(GenesisBlocks.ORANGE_TREE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_LEAVES,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.OAK_LEAVES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.SPRUCE_LEAVES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.BIRCH_LEAVES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.JUNGLE_LEAVES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.ACACIA_LEAVES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.DARK_OAK_LEAVES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.MANGROVE_LEAVES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.CHERRY_LEAVES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.AZALEA_LEAVES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.FLOWERING_AZALEA_LEAVES).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Blocks.BROWN_MUSHROOM_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.RED_MUSHROOM_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Blocks.NETHER_WART_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.WARPED_WART_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(AetherBlocks.SKYROOT_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(AetherBlocks.GOLDEN_OAK_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(AetherBlocks.CRYSTAL_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(AetherBlocks.HOLIDAY_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(AetherBlocks.DECORATED_HOLIDAY_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(GenesisBlocks.BLUE_SKYROOT_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_CORAL,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.TUBE_CORAL_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.BRAIN_CORAL_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.BUBBLE_CORAL_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.FIRE_CORAL_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.HORN_CORAL_BLOCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.TUBE_CORAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.BRAIN_CORAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.BUBBLE_CORAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.FIRE_CORAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.HORN_CORAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.TUBE_CORAL_FAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.BRAIN_CORAL_FAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.BUBBLE_CORAL_FAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.FIRE_CORAL_FAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Blocks.HORN_CORAL_FAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_MATERIALS,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.FLINT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Items.EGG).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Items.HONEYCOMB).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Items.PRISMARINE_SHARD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.PRISMARINE_CRYSTALS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.NAUTILUS_SHELL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.HEART_OF_THE_SEA).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.ECHO_SHARD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.BRICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.NETHER_BRICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.POPPED_CHORUS_FRUIT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))),
                                        LootItem.lootTableItem(Items.PUFFERFISH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Items.DRAGON_BREATH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(AetherItems.SKYROOT_STICK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(GenesisItems.CORNSTARCH_BOWL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(AetherItems.SKYROOT_POISON_BUCKET.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_DROPS,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Blocks.WHITE_WOOL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.FEATHER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.LEATHER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))),
                                        LootItem.lootTableItem(Items.RABBIT_HIDE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Items.GLOW_INK_SAC).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Items.SCUTE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.SLIME_BALL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Items.BLAZE_ROD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.ENDER_PEARL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.SHULKER_SHELL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F))),
                                        LootItem.lootTableItem(Items.BLAZE_POWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.RABBIT_FOOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Items.SPIDER_EYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(Items.MAGMA_CREAM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.GHAST_TEAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(Items.PHANTOM_MEMBRANE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(AetherItems.AECHOR_PETAL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))),
                                        LootItem.lootTableItem(AetherItems.SWET_BALL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(GenesisItems.GOLDEN_SWET_BALL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))),
                                        LootItem.lootTableItem(GenesisItems.DARK_SWET_BALL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_DYES,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Items.WHITE_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.LIGHT_GRAY_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.GRAY_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.BLACK_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.BROWN_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.RED_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.ORANGE_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.YELLOW_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.LIME_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.GREEN_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.CYAN_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.LIGHT_BLUE_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.BLUE_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.PURPLE_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.MAGENTA_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))),
                                        LootItem.lootTableItem(Items.PINK_DYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_DISCS,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Items.MUSIC_DISC_13).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_CAT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_BLOCKS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_CHIRP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_FAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_MALL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_MELLOHI).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_STAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_STRAD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_WARD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_11).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_WAIT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_OTHERSIDE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_PIGSTEP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MUSIC_DISC_RELIC).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(AetherItems.MUSIC_DISC_AETHER_TUNE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(AetherItems.MUSIC_DISC_ASCENDING_DAWN.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(GenesisItems.MUSIC_DISC_DEMISE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(GenesisItems.MUSIC_DISC_APPROACHES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(GenesisItems.RECORDING_892.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(GenesisItems.MUSIC_DISC_AERWHALE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_TEMPLATES,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
                                        LootItem.lootTableItem(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_BOOKS,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(EnchantRandomlyFunction.randomApplicableEnchantment())
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_PATTERNS,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Items.FLOWER_BANNER_PATTERN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.CREEPER_BANNER_PATTERN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.SKULL_BANNER_PATTERN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.MOJANG_BANNER_PATTERN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.GLOBE_BANNER_PATTERN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))),
                                        LootItem.lootTableItem(Items.PIGLIN_BANNER_PATTERN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                )
                        )
                )
        );
        builder.accept(GenesisLoot.CONTINUUM_ORB_SHERDS,
                LootTable.lootTable().withPool(
                        LootPool.lootPool().add(
                                RandomEntry.random(
                                        LootItem.lootTableItem(Items.ANGLER_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.ARCHER_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.ARMS_UP_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.BLADE_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.BREWER_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.BURN_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.DANGER_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.EXPLORER_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.FRIEND_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.HEART_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.HEARTBREAK_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.HOWL_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.MINER_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.MOURNER_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.PLENTY_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.PRIZE_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.SHEAF_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.SHELTER_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.SKULL_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))),
                                        LootItem.lootTableItem(Items.SNORT_POTTERY_SHERD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                )
                        )
                )
        );
    }
}
