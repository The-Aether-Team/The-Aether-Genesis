package com.aetherteam.aether_genesis.data.generators;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.data.resources.registries.GenesisBiomeModifiers;
import com.aetherteam.aether_genesis.data.resources.registries.GenesisBiomes;
import com.aetherteam.aether_genesis.data.resources.registries.GenesisConfiguredFeatures;
import com.aetherteam.aether_genesis.data.resources.registries.GenesisPlacedFeatures;
import com.aetherteam.aether_genesis.mixin.mixins.common.accessor.RegistrySetBuilderAccessor;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class GenesisRegistrySets extends DatapackBuiltinEntriesProvider {
    /**
     * This anonymous class of {@link RegistrySetBuilder} prevents the missing {@link ResourceKey} error during data generation.
     */
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder() {
        public HolderLookup.Provider buildPatch(RegistryAccess registries, HolderLookup.Provider lookup) {
            RegistrySetBuilder setBuilder = this;
            RegistrySetBuilder.BuildState state = ((RegistrySetBuilderAccessor) setBuilder).callCreateState(registries);
            Map<ResourceKey<? extends Registry<?>>, RegistryContents<?>> map = new HashMap<>();
            state.collectReferencedRegistries().forEach((element) -> map.put(element.key(), element));
            ((RegistrySetBuilderAccessor) setBuilder).aether_genesis$getEntries().stream().map((RegistryStub<?> stub) -> stub.collectChanges(state)).forEach((contents) -> map.put(contents.key(), contents));
            Stream<HolderLookup.RegistryLookup<?>> stream = registries.registries().map((entry) -> entry.value().asLookup());
            HolderLookup.Provider provider = HolderLookup.Provider.create(Stream.concat(stream, map.values().stream().map(RegistrySetBuilder.RegistryContents::buildAsLookup).peek(state::addOwner)));
            state.fillMissingHolders(lookup);
            state.throwOnError();
            return provider;
        }
    }
            .add(Registries.CONFIGURED_FEATURE, GenesisConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, GenesisPlacedFeatures::bootstrap)
            .add(Registries.BIOME, GenesisBiomes::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, GenesisBiomeModifiers::bootstrap);

    public GenesisRegistrySets(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(Genesis.MODID));
    }

    public static HolderLookup.Provider createLookup() {
        return BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup());
    }
}
