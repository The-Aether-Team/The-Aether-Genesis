package com.aetherteam.aether_genesis.world.trunkplacer;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GenesisTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Genesis.MODID);

    public static final RegistryObject<TrunkPlacerType<HookedTrunkPlacer>> HOOKED_TRUNK_PLACER = TRUNK_PLACERS.register("hooked_trunk_placer", () -> new TrunkPlacerType<>(HookedTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<SkinnyHookedTrunkPlacer>> SKINNY_HOOKED_TRUNK_PLACER = TRUNK_PLACERS.register("skinny_hooked_trunk_placer", () -> new TrunkPlacerType<>(SkinnyHookedTrunkPlacer.CODEC));
}
