package com.aetherteam.genesis.network.packet.clientbound;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.client.particle.GenesisParticleTypes;
import com.aetherteam.nitrogen.network.BasePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public record TrackingGolemWarningPacket(int playerID) implements BasePacket {
    public static final ResourceLocation ID = new ResourceLocation(AetherGenesis.MODID, "tracking_golem_warning");

    @Override
    public ResourceLocation id() {
        return ID;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.playerID());
    }

    public static TrackingGolemWarningPacket decode(FriendlyByteBuf buf) {
        int playerID = buf.readInt();
        return new TrackingGolemWarningPacket(playerID);
    }

    @Override
    public void execute(Player playerEntity) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            if (Minecraft.getInstance().level.getEntity(this.playerID()) instanceof Player player) {
                Minecraft.getInstance().level.addParticle(GenesisParticleTypes.TRACKING_GOLEM_WARNING.get(), player.getX(), player.getY(), player.getZ(), 0.0, 0.0, 0.0);
                Minecraft.getInstance().level.playSound(player, player.getX(), player.getY(), player.getZ(), GenesisSoundEvents.ENTITY_TRACKING_GOLEM_SEEN_ENEMY.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
            }
        }
    }
}