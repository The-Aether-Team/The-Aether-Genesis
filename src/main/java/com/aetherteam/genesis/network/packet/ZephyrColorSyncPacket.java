package com.aetherteam.genesis.network.packet;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.attachment.GenesisDataAttachments;
import com.aetherteam.genesis.attachment.ZephyrColorAttachment;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import oshi.util.tuples.Quartet;

import java.util.function.Supplier;

/**
 * Sync packet for values in the {@link ZephyrColorAttachment} class.
 */
public class ZephyrColorSyncPacket extends SyncEntityPacket<ZephyrColorAttachment> {
    public static final ResourceLocation ID = new ResourceLocation(AetherGenesis.MODID, "sync_zephyr_color_attachment");

    public ZephyrColorSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public ZephyrColorSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static ZephyrColorSyncPacket decode(FriendlyByteBuf buf) {
        return new ZephyrColorSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public Supplier<AttachmentType<ZephyrColorAttachment>> getAttachment() {
        return GenesisDataAttachments.ZEPHYR_COLOR;
    }
}