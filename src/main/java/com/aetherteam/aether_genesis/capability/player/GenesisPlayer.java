package com.aetherteam.aether_genesis.capability.player;

import com.aetherteam.aether.capability.player.AetherPlayer;
import com.aetherteam.aether_genesis.capability.GenesisCapabilities;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

public interface GenesisPlayer extends AetherPlayer {
    static LazyOptional<GenesisPlayer> get(Player player) {
        return player.getCapability(GenesisCapabilities.GENESIS_PLAYER_CAPABILITY);
    }

    void setPhoenixDartCount(int count);
    int getPhoenixDartCount();
}