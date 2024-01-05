package com.aetherteam.aether_genesis.capability.zephyr;

import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether_genesis.network.GenesisPacketHandler;
import com.aetherteam.aether_genesis.network.packet.ZephyrColorSyncPacket;
import com.aetherteam.nitrogen.network.BasePacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ZephyrColorCapability implements ZephyrColor {
    private final Zephyr zephyr;

    private boolean isTan = false;

    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions = Map.ofEntries(
            Map.entry("setTan", Triple.of(Type.BOOLEAN, (object) -> this.setTan((boolean) object), this::isTan))
    );

    public ZephyrColorCapability(Zephyr zephyr) {
        this.zephyr = zephyr;
    }

    @Override
    public Zephyr getZephyr() {
        return this.zephyr;
    }
    /**
     * Saves data on world close.
     */
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("Tan", this.isTan());
        return tag;
    }

    /**
     * Restores data from world on open.
     */
    @Override
    public void deserializeNBT(CompoundTag tag) {
        if (tag.contains("Tan")) {
            this.setTan(tag.getBoolean("Tan"));
        }
    }

    @Override
    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    @Override
    public void setTan(boolean tan) {
        this.isTan = tan;
    }

    @Override
    public boolean isTan() {
        return this.isTan;
    }

    @Override
    public BasePacket getSyncPacket(String key, Type type, Object value) {
        return new ZephyrColorSyncPacket(this.getZephyr().getId(), key, type, value);
    }

    @Override
    public SimpleChannel getPacketChannel() {
        return GenesisPacketHandler.INSTANCE;
    }
}
