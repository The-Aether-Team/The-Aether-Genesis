package com.aetherteam.aether_genesis.capability.player;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.capability.CapabilitySyncing;
import com.aetherteam.aether.network.AetherPacket;
import com.aetherteam.aether_genesis.entity.companion.Companion;
import com.aetherteam.aether_genesis.network.packet.GenesisPlayerSyncPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GenesisPlayerCapability extends CapabilitySyncing implements GenesisPlayer {
    private final Player player;

    private List<Entity> companions = new ArrayList<>();
    private int phoenixDartCount;

    public GenesisPlayerCapability(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public CompoundTag serializeNBT() {
        return new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) { }

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
    public void onLogout() {
        this.clearCompanions();
    }

    @Override
    public void onLogin() {

    }

    @Override
    public void onUpdate() {
        this.updateSyncableNBTFromServer(this.getPlayer().getLevel());
        Aether.LOGGER.info(String.valueOf(this.getCompanions()));
    }

    @Override
    public void setCompanions(List<Entity> companions) {
        companions.forEach((entity) -> {
            if (entity instanceof Companion companionEntity) {
                companionEntity.setOwner(this.getPlayer());
            }
        });
        this.companions = companions;
    }

    @Override
    public void addCompanion(Entity companion) {
        if (companion instanceof Companion companionEntity) {
            companionEntity.setOwner(this.getPlayer());
        }
        this.companions.add(companion);
    }

    @Override
    public void removeCompanion(Predicate<Entity> companionCheck) {
        this.companions.stream().filter(companionCheck).findFirst().ifPresent(Entity::discard);
        this.companions.removeIf(companionCheck);
    }

    @Override
    public void clearCompanions() {
        this.companions.forEach(Entity::discard);
        this.companions.clear();
    }

    @Override
    public List<Entity> getCompanions() {
        return this.companions;
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
    public AetherPacket getSyncPacket(CompoundTag tag) {
        return new GenesisPlayerSyncPacket(this.getPlayer().getId(), tag);
    }
}