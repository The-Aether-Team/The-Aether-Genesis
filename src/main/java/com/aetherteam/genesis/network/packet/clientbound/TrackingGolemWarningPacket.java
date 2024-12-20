package com.aetherteam.genesis.network.packet.clientbound;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.client.particle.GenesisParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record TrackingGolemWarningPacket(int playerID) implements CustomPacketPayload {
    public static final Type<TrackingGolemWarningPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "tracking_golem_warning"));

    public static final StreamCodec<RegistryFriendlyByteBuf, TrackingGolemWarningPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            TrackingGolemWarningPacket::playerID,
            TrackingGolemWarningPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @OnlyIn(Dist.CLIENT)
    public static void execute(TrackingGolemWarningPacket payload, IPayloadContext context) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            if (Minecraft.getInstance().level.getEntity(payload.playerID()) instanceof Player player) {
                Minecraft.getInstance().level.addParticle(GenesisParticleTypes.TRACKING_GOLEM_WARNING.get(), player.getX(), player.getY(), player.getZ(), 0.0, 0.0, 0.0);
                Minecraft.getInstance().level.playSound(player, player.getX(), player.getY(), player.getZ(), GenesisSoundEvents.ENTITY_TRACKING_GOLEM_SEEN_ENEMY.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
            }
        }
    }
}