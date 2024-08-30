package com.aetherteam.genesis.attachment;

import com.aetherteam.genesis.entity.companion.Companion;
import com.aetherteam.genesis.network.packet.GenesisPlayerSyncPacket;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.BasePacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GenesisPlayerAttachment implements INBTSynchable {
    private List<Entity> companions = new ArrayList<>();
    private int phoenixDartCount;
    private int removePhoenixDartTime;

    /**
     * Stores the following methods as able to be synced between client and server and vice-versa.
     */
    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions = Map.ofEntries(
            Map.entry("setPhoenixDartCount", Triple.of(Type.INT, (object) -> this.setPhoenixDartCount((int) object), this::getPhoenixDartCount))
    );
    private boolean shouldSyncAfterJoin;

    public GenesisPlayerAttachment() {

    }

    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    /**
     * Handles functions when the player logs out of a world from {@link net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent}.
     */
    public void onLogout() {
        this.clearCompanions();
    }

    /**
     * Handles functions when the player logs in to a world from {@link net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent}.
     */
    public void onLogin() {
        this.shouldSyncAfterJoin = true;
    }

    /**
     * Handles functions when the player ticks from {@link net.neoforged.neoforge.event.entity.living.LivingEvent.LivingTickEvent}
     */
    public void onUpdate(Player player) {
        this.syncAfterJoin(player);
        this.handleRemoveDarts(player);
    }

    private void syncAfterJoin(Player player) {
        if (this.shouldSyncAfterJoin) {
            this.forceSync(player.getId(), INBTSynchable.Direction.CLIENT);
            this.shouldSyncAfterJoin = false;
        }
    }


    /**
     * Slowly removes darts that are rendered as stuck on the player by {@link com.aetherteam.aether.client.renderer.player.layer.DartLayer}.
     */
    private void handleRemoveDarts(Player player) {
        if (!player.level().isClientSide()) {
            if (this.getPhoenixDartCount() > 0) {
                if (this.removePhoenixDartTime <= 0) {
                    this.removePhoenixDartTime = 20 * (30 - this.getPhoenixDartCount());
                }

                --this.removePhoenixDartTime;
                if (this.removePhoenixDartTime <= 0) {
                    this.setSynched(player.getId(), INBTSynchable.Direction.CLIENT, "setPhoenixDartCount", this.getPhoenixDartCount() - 1);
                }
            }
        }
    }

    public void setCompanions(Player player, List<Entity> companions) {
        companions.stream().filter((entity) -> entity instanceof Companion<?>).forEach((entity) -> ((Companion<?>) entity).setOwner(player.getUUID()));
        this.companions = companions;
    }

    public void addCompanion(Player player, Entity companion) {
        if (companion instanceof Companion companionEntity) {
            companionEntity.setOwner(player.getUUID());
        }
        this.companions.add(companion);
    }

    public void removeCompanion(Predicate<Entity> companionCheck) {
        this.companions.stream().filter(companionCheck).findFirst().ifPresent(Entity::discard);
        this.companions.removeIf(companionCheck);
    }

    public void clearCompanions() {
        this.companions.forEach(Entity::discard);
        this.companions.clear();
    }

    /**
     * @return The {@link List} of companion {@link Entity Entities} that this player has active.
     */
    public List<Entity> getCompanions() {
        return this.companions;
    }

    public void setPhoenixDartCount(int count) {
        this.phoenixDartCount = count;
    }

    /**
     * @return An {@link Integer} for how many Phoenix Darts are stuck in the player.
     */
    public int getPhoenixDartCount() {
        return this.phoenixDartCount;
    }

    @Override
    public BasePacket getSyncPacket(int entityID, String key, Type type, Object value) {
        return new GenesisPlayerSyncPacket(entityID, key, type, value);
    }
}