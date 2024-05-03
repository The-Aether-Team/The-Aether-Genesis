package com.aetherteam.genesis.data.generators;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.data.resources.registries.GenesisBiomeModifiers;
import com.aetherteam.genesis.data.resources.registries.GenesisBiomes;
import com.aetherteam.genesis.data.resources.registries.GenesisConfiguredFeatures;
import com.aetherteam.genesis.data.resources.registries.GenesisPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class GenesisRegistrySets extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, GenesisConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, GenesisPlacedFeatures::bootstrap)
            .add(Registries.BIOME, GenesisBiomes::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, GenesisBiomeModifiers::bootstrap);

    public GenesisRegistrySets(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(Genesis.MODID));
    }
}
