package com.aetherteam.aether_genesis.loot.functions;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GenesisLootFunctions {
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, Genesis.MODID);

    public static final RegistryObject<LootItemFunctionType> CARRION_SPROUT_SIZE = LOOT_FUNCTION_TYPES.register("carrion_sprout_size", () -> new LootItemFunctionType(new CarrionSproutSize.Serializer()));
}
