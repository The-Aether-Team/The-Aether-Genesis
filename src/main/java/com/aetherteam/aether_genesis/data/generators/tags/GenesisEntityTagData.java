package com.aetherteam.aether_genesis.data.generators.tags;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenesisEntityTagData extends EntityTypeTagsProvider {
    public GenesisEntityTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, Genesis.MODID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(AetherTags.Entities.SWETS).add(
                GenesisEntityTypes.DARK_SWET.get()
        );
    }
}
