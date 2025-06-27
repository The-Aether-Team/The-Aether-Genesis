package com.aetherteam.genesis.item.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record HungerTracker(float exhaustionLevel, float saturationLevel) {
    public static final MapCodec<HungerTracker> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.FLOAT.fieldOf("exhaustionLevel").forGetter(HungerTracker::exhaustionLevel),
            Codec.FLOAT.fieldOf("saturationLevel").forGetter(HungerTracker::saturationLevel)
    ).apply(instance, HungerTracker::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, HungerTracker> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, HungerTracker::exhaustionLevel,
            ByteBufCodecs.FLOAT, HungerTracker::saturationLevel,
            HungerTracker::new
    );
}
