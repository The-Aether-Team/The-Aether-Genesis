package com.aetherteam.genesis.network.clientbound;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.particle.GenesisParticleTypes;
import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record NexResurrectionEffectPacket() implements CustomPacketPayload {
    public static final Type<NexResurrectionEffectPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "nex_resurrection_effect"));

    public static final StreamCodec<RegistryFriendlyByteBuf, NexResurrectionEffectPacket> STREAM_CODEC = CustomPacketPayload.codec(
            NexResurrectionEffectPacket::write,
            NexResurrectionEffectPacket::decode);

    public void write(RegistryFriendlyByteBuf buf) {
    }

    public static NexResurrectionEffectPacket decode(RegistryFriendlyByteBuf buf) {
        return new NexResurrectionEffectPacket();
    }

    @Override
    public Type<NexResurrectionEffectPacket> type() {
        return TYPE;
    }

    public static void execute(NexResurrectionEffectPacket payload, IPayloadContext context) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            Minecraft minecraft = Minecraft.getInstance();
            minecraft.particleEngine.createTrackingEmitter(Minecraft.getInstance().player, GenesisParticleTypes.NEX_SPIRIT_RESURRECTION.get(), 30);
            Minecraft.getInstance().player.level().playLocalSound(
                    Minecraft.getInstance().player.getX(),
                    Minecraft.getInstance().player.getY(),
                    Minecraft.getInstance().player.getZ(),
                    SoundEvents.TOTEM_USE, Minecraft.getInstance().player.getSoundSource(), 1.0F, 1.0F, false);
            minecraft.gameRenderer.displayItemActivation(new ItemStack(GenesisItems.DEATH_SEAL.get()));
        }
    }
}
