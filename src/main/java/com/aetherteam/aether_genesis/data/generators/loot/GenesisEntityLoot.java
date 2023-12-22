package com.aetherteam.aether_genesis.data.generators.loot;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.aetherteam.aether_genesis.loot.functions.CarrionSproutSize;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.stream.Stream;

public class GenesisEntityLoot extends EntityLootSubProvider {
    public GenesisEntityLoot() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(GenesisEntityTypes.CARRION_SPROUT.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GenesisItems.WYNDBERRY.get())
                                .apply(CarrionSproutSize.setAmount(ConstantValue.exactly(3), ConstantValue.exactly(1), ConstantValue.exactly(3)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
        );
        this.add(GenesisEntityTypes.ZEPHYROO.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool())
        );
        this.add(GenesisEntityTypes.DARK_SWET.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GenesisItems.DARK_SWET_BALL.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GenesisBlocks.PURPLE_AERCLOUD.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
        );
        this.add(GenesisEntityTypes.TEMPEST.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GenesisBlocks.STORM_AERCLOUD.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
        );
        this.add(GenesisEntityTypes.BATTLE_SENTRY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherBlocks.CARVED_STONE.get()).setWeight(4)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
        );
        this.add(GenesisEntityTypes.TRACKING_GOLEM.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherBlocks.CARVED_STONE.get()).setWeight(4)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                        .add(LootItem.lootTableItem(AetherBlocks.SENTRY_STONE.get()).setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
        );
        this.add(GenesisEntityTypes.SKYROOT_MIMIC.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GenesisBlocks.SKYROOT_CHEST.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_DART.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4))))
                        .add(LootItem.lootTableItem(AetherItems.ENCHANTED_DART.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4))))
                        .add(LootItem.lootTableItem(AetherItems.POISON_DART.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4))))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_PICKAXE.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(AetherItems.IRON_RING.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_AMBER.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_GEMSTONE.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(AetherItems.HOLYSTONE_PICKAXE.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(AetherBlocks.ICESTONE.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                )
        );

        this.add(GenesisEntityTypes.SENTRY_GUARDIAN.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GenesisItems.GUARDIAN_KEY.get())
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        )
                )
        );
        this.add(GenesisEntityTypes.SLIDER_HOST_MIMIC.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GenesisItems.HOST_KEY.get())
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        )
                )
        );
        this.add(GenesisEntityTypes.LABYRINTH_EYE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GenesisItems.COG_KEY.get())
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        )
                )
        );
    }

    @Override
    public Stream<EntityType<?>> getKnownEntityTypes() {
        return GenesisEntityTypes.ENTITY_TYPES.getEntries().stream().flatMap(entityType -> Stream.of(entityType.get()));
    }
}
