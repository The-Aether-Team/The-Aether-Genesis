package com.aetherteam.genesis.data.providers;

import com.aetherteam.aether.data.providers.AetherLanguageProvider;
import net.minecraft.data.PackOutput;

public abstract class GenesisLanguageProvider extends AetherLanguageProvider {
    public GenesisLanguageProvider(PackOutput output, String id) {
        super(output, id);
    }
}
