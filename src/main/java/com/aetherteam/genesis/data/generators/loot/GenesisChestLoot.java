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
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public record GenesisChestLoot(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder) {
        builder.accept(GenesisLoot.BRONZE_DUNGEON_REWARD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 5.0F))
                        .add(LootItem.lootTableItem(GenesisItems.FROSTPINE_TOTEM.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.DEXTERITY_CAPE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.FANGRIN_CAPSULE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.SOARING_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.FLEETING_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.BABY_PINK_SWET.get()).setWeight(1))
                        .add(LootItem.lootTableItem(GenesisItems.MOUSE_EAR_CAP.get()).setWeight(1))
                )
        );
    }
}
