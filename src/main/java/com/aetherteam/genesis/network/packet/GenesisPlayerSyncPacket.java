package com.aetherteam.genesis.network.packet;

import com.aetherteam.aether.network.packet.AetherPlayerSyncPacket;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.attachment.GenesisDataAttachments;
import com.aetherteam.genesis.attachment.GenesisPlayerAttachment;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import oshi.util.tuples.Quartet;

import java.util.function.Supplier;

/**
 * Sync packet for values in the {@link GenesisPlayerAttachment} class.
 */
public class GenesisPlayerSyncPacket extends SyncEntityPacket<GenesisPlayerAttachment> {
    public static final Type<GenesisPlayerSyncPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "sync_genesis_player_attachment"));

    public static final StreamCodec<RegistryFriendlyByteBuf, GenesisPlayerSyncPacket> STREAM_CODEC = CustomPacketPayload.codec(
            GenesisPlayerSyncPacket::write,
            GenesisPlayerSyncPacket::decode);

    public GenesisPlayerSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public GenesisPlayerSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static GenesisPlayerSyncPacket decode(RegistryFriendlyByteBuf buf) {
        return new GenesisPlayerSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public Supplier<AttachmentType<GenesisPlayerAttachment>> getAttachment() {
        return GenesisDataAttachments.GENESIS_PLAYER;
    }

    public static void execute(GenesisPlayerSyncPacket payload, IPayloadContext context) {
        SyncEntityPacket.execute(payload, context.player());
    }
}