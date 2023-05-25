package com.aetherteam.aether_genesis.loot.modifiers;

import com.aetherteam.aether_genesis.Genesis;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GenesisLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Genesis.MODID);

    public static final RegistryObject<Codec<ChanceDoubleDropsModifier>> CHANCE_DOUBLE_DROPS = GLOBAL_LOOT_MODIFIERS.register("chance_double_drops", () -> ChanceDoubleDropsModifier.CODEC);
    public static final RegistryObject<Codec<AddEntityDropsModifier>> ADD_ENTITY_DROPS = GLOBAL_LOOT_MODIFIERS.register("add_entity_drops", () -> AddEntityDropsModifier.CODEC);
}
