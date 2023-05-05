package com.aetherteam.aether_genesis.data.providers;

import com.aetherteam.aether.data.providers.AetherBlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;

import java.util.Set;

public abstract class GenesisBlockLootSubProvider extends AetherBlockLootSubProvider {
    public GenesisBlockLootSubProvider(Set<Item> items, FeatureFlagSet flags) {
        super(items, flags);
    }
}
