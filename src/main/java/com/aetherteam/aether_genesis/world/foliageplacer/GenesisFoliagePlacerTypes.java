package com.aetherteam.aether_genesis.world.foliageplacer;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisFoliagePlacerTypes {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Genesis.MODID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<AetherPineFoliagePlacer>> AETHER_PINE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("aether_pine_foliage_placer", () -> new FoliagePlacerType<>(AetherPineFoliagePlacer.CODEC));
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<HookedFoliagePlacer>> HOOKED_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("hooked_foliage_placer", () -> new FoliagePlacerType<>(HookedFoliagePlacer.CODEC));
}
