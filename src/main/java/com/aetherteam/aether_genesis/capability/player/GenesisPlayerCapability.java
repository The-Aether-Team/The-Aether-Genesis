package com.aetherteam.aether_genesis.capability.player;

import com.aetherteam.aether_genesis.entity.companion.Companion;
import com.aetherteam.aether_genesis.network.GenesisPacketHandler;
import com.aetherteam.aether_genesis.network.packet.GenesisPlayerSyncPacket;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import com.aetherteam.nitrogen.network.BasePacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GenesisPlayerCapability implements GenesisPlayer {
    private final Player player;

    private List<Entity> companions = new ArrayList<>();
    private int phoenixDartCount;

    /**
     * Stores the following methods as able to be synced between client and server and vice-versa.
     */
    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions = Map.ofEntries(
            Map.entry("setPhoenixDartCount", Triple.of(Type.INT, (object) -> this.setPhoenixDartCount((int) object), this::getPhoenixDartCount))
    );
    private boolean shouldSyncAfterJoin;

    public GenesisPlayerCapability(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Saves data on world close.
     */
    @Override
    public CompoundTag serializeNBT() {
        return new CompoundTag();
    }

    /**
     * Restores data from world on open.
     */
    @Override
    public void deserializeNBT(CompoundTag nbt) { }

    @Override
    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    /**
     * Handles functions when the player logs out of a world from {@link net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent}.
     */
    @Override
    public void onLogout() {
        this.clearCompanions();
    }

    /**
     * Handles functions when the player logs in to a world from {@link net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent}.
     */
    @Override
    public void onLogin() {
        this.shouldSyncAfterJoin = true;
    }

    /**
     * Handles functions when the player ticks from {@link net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent}
     */
    @Override
    public void onUpdate() {
        this.syncAfterJoin();
    }

    private void syncAfterJoin() {
        if (this.shouldSyncAfterJoin) {
            this.forceSync(INBTSynchable.Direction.CLIENT);
            this.shouldSyncAfterJoin = false;
        }
    }

    @Override
    public void setCompanions(List<Entity> companions) {
        companions.stream().filter((entity) -> entity instanceof Companion<?>).forEach((entity) -> ((Companion<?>) entity).setOwner(this.getPlayer().getUUID()));
        this.companions = companions;
    }

    @Override
    public void addCompanion(Entity companion) {
        if (companion instanceof Companion companionEntity) {
            companionEntity.setOwner(this.getPlayer().getUUID());
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

    /**
     * @return The {@link List} of companion {@link Entity Entities} that this player has active.
     */
    @Override
    public List<Entity> getCompanions() {
        return this.companions;
    }

    @Override
    public void setPhoenixDartCount(int count) {
        this.phoenixDartCount = count;
    }

    /**
     * @return An {@link Integer} for how many Phoenix Darts are stuck in the player.
     */
    @Override
    public int getPhoenixDartCount() {
        return this.phoenixDartCount;
    }

    @Override
    public BasePacket getSyncPacket(String key, Type type, Object value) {
        return new GenesisPlayerSyncPacket(this.getPlayer().getId(), key, type, value);
    }

    @Override
    public SimpleChannel getPacketChannel() {
        return GenesisPacketHandler.INSTANCE;
    }
}