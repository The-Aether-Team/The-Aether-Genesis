package com.aetherteam.genesis.loot.modifiers;

import com.aetherteam.genesis.AetherGenesis;
import com.mojang.serialization.Codec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class GenesisLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AetherGenesis.MODID);

    public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<ChanceDoubleDropsModifier>> CHANCE_DOUBLE_DROPS = GLOBAL_LOOT_MODIFIERS.register("chance_double_drops", () -> ChanceDoubleDropsModifier.CODEC);
    public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<PresentDropsModifier>> PRESENT_DROPS = GLOBAL_LOOT_MODIFIERS.register("present_drops", () -> PresentDropsModifier.CODEC);
}
