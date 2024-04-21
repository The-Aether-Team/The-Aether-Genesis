package com.aetherteam.aether_genesis.data.providers;

import com.aetherteam.aether.data.providers.AetherLanguageProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public abstract class GenesisLanguageProvider extends AetherLanguageProvider {
    public GenesisLanguageProvider(PackOutput output, String id) {
        super(output, id);
    }

    public void addAbilityTooltip(Item item, String name) {
        this.add(item.getDescriptionId() + "." + this.id + ".ability.tooltip", name);
    }
}
