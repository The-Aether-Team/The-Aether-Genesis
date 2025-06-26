package com.aetherteam.genesis.network.packet;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.attachment.GenesisDataAttachments;
import com.aetherteam.genesis.attachment.ZephyrColorAttachment;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import oshi.util.tuples.Quartet;

import java.util.function.Supplier;

/**
 * Sync packet for values in the {@link ZephyrColorAttachment} class.
 */
public class ZephyrColorSyncPacket extends SyncEntityPacket<ZephyrColorAttachment> {
    public static final Type<ZephyrColorSyncPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "sync_zephyr_color_attachment"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ZephyrColorSyncPacket> STREAM_CODEC = CustomPacketPayload.codec(
            ZephyrColorSyncPacket::write,
            ZephyrColorSyncPacket::decode);

    public ZephyrColorSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public ZephyrColorSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static ZephyrColorSyncPacket decode(RegistryFriendlyByteBuf buf) {
        return new ZephyrColorSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public Supplier<AttachmentType<ZephyrColorAttachment>> getAttachment() {
        return GenesisDataAttachments.ZEPHYR_COLOR;
    }

    public static void execute(ZephyrColorSyncPacket payload, IPayloadContext context) {
        SyncEntityPacket.execute(payload, context.player());
    }
}