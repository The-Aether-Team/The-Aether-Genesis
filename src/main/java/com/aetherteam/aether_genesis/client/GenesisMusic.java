package com.aetherteam.aether_genesis.client;

import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.Biome;

public class GenesisMusic {
    public static final Music AETHER_NIGHT = Musics.createGameMusic(GenesisSoundEvents.MUSIC_AETHER_NIGHT.getHolder().get());

    public static Music getNightMusicForBiome(Holder<Biome> biomeHolder) {
        return AETHER_NIGHT;
    }
}
