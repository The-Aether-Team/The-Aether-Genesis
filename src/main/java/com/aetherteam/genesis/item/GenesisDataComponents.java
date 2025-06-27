package com.aetherteam.genesis.item;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.item.components.HungerTracker;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, AetherGenesis.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<HungerTracker>> STORED_HUNGER_VALUES = DATA_COMPONENTS.register("stored_hunger_values", () -> DataComponentType.<HungerTracker>builder().persistent(HungerTracker.CODEC.codec()).networkSynchronized(HungerTracker.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> NEX_SPIRIT_COOLDOWN = DATA_COMPONENTS.register("nex_spirit_cooldown", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> STORED_EXPERIENCE_AMOUNT = DATA_COMPONENTS.register("stored_experience_amount", () -> DataComponentType.<Float>builder().persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT).build());
}
