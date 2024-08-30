package com.aetherteam.genesis.loot.functions;

import com.aetherteam.genesis.AetherGenesis;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisLootFunctions {
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, AetherGenesis.MODID);

    public static final DeferredHolder<LootItemFunctionType, LootItemFunctionType> CARRION_SPROUT_SIZE = LOOT_FUNCTION_TYPES.register("carrion_sprout_size", () -> new LootItemFunctionType(CarrionSproutSize.CODEC));
}
