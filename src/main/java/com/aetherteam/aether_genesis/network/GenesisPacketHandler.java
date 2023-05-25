package com.aetherteam.aether_genesis.network;

import com.aetherteam.aether.network.AetherPacket;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.network.packet.GenesisPlayerSyncPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Function;

public class GenesisPacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Genesis.MODID, "main"),
			() -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
	);

	private static int index;

	public static synchronized void register() {
		register(GenesisPlayerSyncPacket.class, GenesisPlayerSyncPacket::decode);
	}

	private static <MSG extends AetherPacket> void register(final Class<MSG> packet, Function<FriendlyByteBuf, MSG> decoder) {
		INSTANCE.messageBuilder(packet, index++).encoder(AetherPacket::encode).decoder(decoder).consumerMainThread(AetherPacket::handle).add();
	}
}