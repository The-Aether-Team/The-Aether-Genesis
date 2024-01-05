package com.aetherteam.aether_genesis.network.packet;

import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether_genesis.capability.zephyr.ZephyrColor;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.LazyOptional;
import oshi.util.tuples.Quartet;

/**
 * Sync packet for values in the {@link com.aetherteam.aether_genesis.capability.zephyr.ZephyrColorCapability} class.
 */
public class ZephyrColorSyncPacket extends SyncEntityPacket<ZephyrColor> {
    public ZephyrColorSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public ZephyrColorSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    public static ZephyrColorSyncPacket decode(FriendlyByteBuf buf) {
        return new ZephyrColorSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public LazyOptional<ZephyrColor> getCapability(Entity entity) {
        return ZephyrColor.get((Zephyr) entity);
    }
}