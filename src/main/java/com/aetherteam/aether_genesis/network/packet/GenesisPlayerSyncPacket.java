package com.aetherteam.aether_genesis.network.packet;

import com.aetherteam.aether_genesis.capability.player.GenesisPlayer;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import oshi.util.tuples.Quartet;

public class GenesisPlayerSyncPacket extends SyncEntityPacket<GenesisPlayer> {
    public GenesisPlayerSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public GenesisPlayerSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    public static GenesisPlayerSyncPacket decode(FriendlyByteBuf buf) {
        return new GenesisPlayerSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public LazyOptional<GenesisPlayer> getCapability(Entity entity) {
        return GenesisPlayer.get((Player) entity);
    }
}