package com.aetherteam.genesis.client;

import com.aetherteam.genesis.AetherGenesis;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.JukeboxSong;

public class GenesisJukeboxSongs {
    public static final ResourceKey<JukeboxSong> AERWHALE = create("aerwhale");
    public static final ResourceKey<JukeboxSong> APPROACHES = create("approaches");
    public static final ResourceKey<JukeboxSong> DEMISE = create("demise");
    public static final ResourceKey<JukeboxSong> RECORDING_892 = create("recording_892");

    private static ResourceKey<JukeboxSong> create(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, name));
    }
}
