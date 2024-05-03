package com.aetherteam.genesis.world.trunkplacer;

import com.aetherteam.genesis.Genesis;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Genesis.MODID);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<SkinnyHookedTrunkPlacer>> SKINNY_HOOKED_TRUNK_PLACER = TRUNK_PLACERS.register("skinny_hooked_trunk_placer", () -> new TrunkPlacerType<>(SkinnyHookedTrunkPlacer.CODEC));
}
