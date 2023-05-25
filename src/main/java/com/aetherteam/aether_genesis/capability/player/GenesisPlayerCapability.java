package com.aetherteam.aether_genesis.capability.player;

import com.aetherteam.aether.capability.CapabilitySyncing;
import com.aetherteam.aether.network.AetherPacket;
import com.aetherteam.aether.network.packet.AetherPlayerSyncPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class GenesisPlayerCapability extends CapabilitySyncing implements GenesisPlayer {
    private final Player player;
    private int phoenixDartCount;

    public GenesisPlayerCapability(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return this.player;
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

    @Override
    public AetherPacket getSyncPacket(CompoundTag tag) {
        return new AetherPlayerSyncPacket(this.getPlayer().getId(), tag);
    }

    @Override
    public CompoundTag serializeNBT() {
        return new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {}
}