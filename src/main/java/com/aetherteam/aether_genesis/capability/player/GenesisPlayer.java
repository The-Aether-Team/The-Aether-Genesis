package com.aetherteam.aether_genesis.capability.player;

import com.aetherteam.aether_genesis.capability.GenesisCapabilities;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

import java.util.List;
import java.util.function.Predicate;

public interface GenesisPlayer extends INBTSynchable<CompoundTag> {
    Player getPlayer();

    static LazyOptional<GenesisPlayer> get(Player player) {
        return player.getCapability(GenesisCapabilities.GENESIS_PLAYER_CAPABILITY);
    }

    void onLogout();
    void onLogin();

    void onUpdate();

    void setCompanions(List<Entity> companions);
    void addCompanion(Entity companion);
    void removeCompanion(Predicate<Entity> companionCheck);
    void clearCompanions();
    List<Entity> getCompanions();

    void setPhoenixDartCount(int count);
    int getPhoenixDartCount();
}