package com.aetherteam.genesis.item;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.item.components.CandyFoodStuff;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, AetherGenesis.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> NEX_SPIRIT_COOLDOWN = DATA_COMPONENTS.register("nex_spirit_cooldown", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CandyFoodStuff>> CANDY_RING_FOODSTUFF = DATA_COMPONENTS.register("candy_ring_foodstuff", () -> DataComponentType.<CandyFoodStuff>builder().persistent(CandyFoodStuff.CODEC.codec()).networkSynchronized(CandyFoodStuff.STREAM_CODEC).build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> CRYSTAL_BOTTLE_AMOUNT = DATA_COMPONENTS.register("crystal_bottle_amount", () -> DataComponentType.<Float>builder().persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT).build());

}
