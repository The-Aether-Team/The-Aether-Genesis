package com.aetherteam.genesis.data.generators.loot;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.genesis.item.GenesisItems;
import com.aetherteam.genesis.loot.GenesisLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public record GenesisChestLoot(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder) {
        builder.accept(GenesisLoot.BRONZE_DUNGEON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 5.0F))
                        .add(LootItem.lootTableItem(GenesisItems.DARK_GUMMY_SWET.get()).setWeight(5))
                        .add(LootItem.lootTableItem(GenesisItems.CORNSTARCH_BOWL.get()).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(GenesisItems.CANDY_CORN.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(GenesisItems.DARK_GUMMY_SWET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(GenesisItems.CORNSTARCH_BOWL.get()).setWeight(2))
                        .add(LootItem.lootTableItem(GenesisItems.CANDY_CORN.get()).setWeight(2))
                        .add(LootItem.lootTableItem(GenesisItems.BABY_PINK_SWET.get()).setWeight(3))
                )
        );
        builder.accept(GenesisLoot.BRONZE_DUNGEON_REWARD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(GenesisItems.DARK_GUMMY_SWET.get()).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 4.0F))
                        .add(LootItem.lootTableItem(GenesisItems.DARK_GUMMY_SWET.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.CONTINUUM_ORB.get()).setWeight(2))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 5.0F))
                        .add(LootItem.lootTableItem(GenesisItems.FROSTPINE_TOTEM.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.DEXTERITY_CAPE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.FANGRIN_CAPSULE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.KRAISITH_CAPSULE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.SOARING_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.FLEETING_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.MOUSE_EAR_CAP.get()).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(GenesisItems.FROSTPINE_TOTEM.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.DEXTERITY_CAPE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.FANGRIN_CAPSULE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.KRAISITH_CAPSULE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.SOARING_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.FLEETING_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.MOUSE_EAR_CAP.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.MUSIC_DISC_DEMISE.get()).setWeight(9))
                )
        );
        builder.accept(GenesisLoot.SILVER_DUNGEON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 6.0F))
                        .add(LootItem.lootTableItem(GenesisItems.DARK_GUMMY_SWET.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(GenesisItems.CORNSTARCH_BOWL.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(GenesisItems.CANDY_CORN.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                )
        );
        builder.accept(GenesisLoot.SILVER_DUNGEON_REWARD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(GenesisItems.DARK_GUMMY_SWET.get()).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 5.0F))
                        .add(LootItem.lootTableItem(GenesisItems.DARK_GUMMY_SWET.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.CONTINUUM_ORB.get()).setWeight(4))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(GenesisItems.PHOENIX_DART_SHOOTER.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.SKYROOT_RING.get()).setWeight(2))
                        .add(LootItem.lootTableItem(GenesisItems.SWETTY_PENDANT.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.LUCKY_BELL.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.FROSTBOUND_STONE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.ORB_OF_ARKENZUS.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.ETHEREAL_STONE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE.get()).setWeight(2))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(GenesisItems.PHOENIX_DART_SHOOTER.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.SKYROOT_RING.get()).setWeight(2))
                        .add(LootItem.lootTableItem(GenesisItems.SWETTY_PENDANT.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.LUCKY_BELL.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.FROSTBOUND_STONE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.ORB_OF_ARKENZUS.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.ETHEREAL_STONE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(GenesisItems.MUSIC_DISC_APPROACHES.get()).setWeight(10))
                )
        );
        builder.accept(GenesisLoot.GOLD_DUNGEON_REWARD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(GenesisItems.DARK_GUMMY_SWET.get()).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 7.0F))
                        .add(LootItem.lootTableItem(GenesisItems.DARK_GUMMY_SWET.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.CONTINUUM_ORB.get()).setWeight(5))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(GenesisItems.BONE_RING.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.CANDY_RING.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.DAGGERFROST_LOCKET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(GenesisItems.DEATH_SEAL.get()).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(GenesisItems.BONE_RING.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.CANDY_RING.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.DAGGERFROST_LOCKET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(GenesisItems.DEATH_SEAL.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.RECORDING_892.get()).setWeight(7))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(GenesisItems.BONE_RING.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.CANDY_RING.get()).setWeight(3))
                        .add(LootItem.lootTableItem(GenesisItems.DAGGERFROST_LOCKET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(GenesisItems.DEATH_SEAL.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.CONTINUUM_BOMB.get()).setWeight(4))
                )
        );
    }
}
