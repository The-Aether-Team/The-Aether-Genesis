package com.aetherteam.aether_genesis.data.providers;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.providers.AetherBlockLootSubProvider;
import com.aetherteam.aether.loot.functions.DoubleDrops;
import com.aetherteam.aether.mixin.mixins.common.accessor.BlockLootAccessor;
import com.aetherteam.aether_genesis.block.natural.OrangeTreeBlock;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public abstract class GenesisBlockLootSubProvider extends AetherBlockLootSubProvider {
    public GenesisBlockLootSubProvider(Set<Item> items, FeatureFlagSet flags) {
        super(items, flags);
    }

    public LootTable.Builder droppingWithChancesAndFruitAndSkyrootSticks(Block block, Block sapling, Item fruit, float... chances) {
        return droppingWithChancesAndSkyrootSticks(block, sapling, chances)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(BlockLootAccessor.aether$hasShearsOrSilkTouch().invert())
                        .add(this.applyExplosionCondition(block, LootItem.lootTableItem(fruit))));
    }

    public LootTable.Builder droppingOrangeTree(Block block, Item drop) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(this.applyExplosionDecay(block, LootItem.lootTableItem(drop))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)).when(LocationCheck.checkLocation(
                                        LocationPredicate.Builder.location().setBlock(
                                                BlockPredicate.Builder.block().of(AetherBlocks.ENCHANTED_AETHER_GRASS_BLOCK.get())),
                                        new BlockPos(0, -1, 0)).invert()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)).when(LocationCheck.checkLocation(
                                        LocationPredicate.Builder.location().setBlock(
                                                BlockPredicate.Builder.block().of(AetherBlocks.ENCHANTED_AETHER_GRASS_BLOCK.get())),
                                        new BlockPos(0, -1, 0))))
                                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrangeTreeBlock.AGE, 4)))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                        .apply(DoubleDrops.builder())
        ).withPool(LootPool.lootPool()
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrangeTreeBlock.AGE, 4)).invert())
                .add(LootItem.lootTableItem(block))
        );
    }
}
