package com.aetherteam.aether_genesis.capability.player;

import com.aetherteam.aether.capability.player.AetherPlayerCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class GenesisPlayerCapability extends AetherPlayerCapability implements GenesisPlayer {

    private int phoenixDartCount;

    public GenesisPlayerCapability(Player player) {
        super(player);
    }

    @Override
    public void setPhoenixDartCount(int count) {
        this.markDirty(true);
        this.phoenixDartCount = count;
    }

    @Override
    public int getPhoenixDartCount() {
        return this.phoenixDartCount;
    }

    @Override
    public CompoundTag serializeSynchableNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("PhoenixDartCount_Syncing", this.getPhoenixDartCount());
        return tag;
    }

    @Override
    public void deserializeSynchableNBT(CompoundTag tag) {
        if (tag.contains("PhoenixDartCount_Syncing")) {
            this.setPhoenixDartCount(tag.getInt("PhoenixDartCount_Syncing"));
        }
    }
}