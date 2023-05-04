package com.aetherteam.genesis.world.foliageplacer;

import com.aetherteam.genesis.Genesis;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GenesisFoliagePlacerTypes {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, Genesis.MODID);

    public static final RegistryObject<FoliagePlacerType<AetherPineFoliagePlacer>> AETHER_PINE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("aether_pine_foliage_placer", () -> new FoliagePlacerType<>(AetherPineFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<HookedFoliagePlacer>> HOOKED_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("hooked_foliage_placer", () -> new FoliagePlacerType<>(HookedFoliagePlacer.CODEC));
}
