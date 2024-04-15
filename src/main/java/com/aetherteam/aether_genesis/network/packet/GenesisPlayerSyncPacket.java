package com.aetherteam.aether_genesis.network.packet;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether_genesis.capability.GenesisDataAttachments;
import com.aetherteam.aether_genesis.capability.GenesisPlayerAttachment;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import oshi.util.tuples.Quartet;

import java.util.function.Supplier;

/**
 * Sync packet for values in the {@link GenesisPlayerAttachment} class.
 */
public class GenesisPlayerSyncPacket extends SyncEntityPacket<GenesisPlayerAttachment> {
    public static final ResourceLocation ID = new ResourceLocation(Aether.MODID, "sync_genesis_player_attachment");

    public GenesisPlayerSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public GenesisPlayerSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static GenesisPlayerSyncPacket decode(FriendlyByteBuf buf) {
        return new GenesisPlayerSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public Supplier<AttachmentType<GenesisPlayerAttachment>> getAttachment() {
        return GenesisDataAttachments.GENESIS_PLAYER;
    }
}