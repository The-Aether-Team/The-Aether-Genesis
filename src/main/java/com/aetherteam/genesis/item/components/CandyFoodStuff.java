package com.aetherteam.genesis.item.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record CandyFoodStuff(float exhaustionLevel, float saturationLevel) {
    public static final MapCodec<CandyFoodStuff> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(
                Codec.FLOAT.fieldOf("exhaustionLevel").forGetter(CandyFoodStuff::exhaustionLevel),
                Codec.FLOAT.fieldOf("saturationLevel").forGetter(CandyFoodStuff::saturationLevel)
        ).apply(instance, CandyFoodStuff::new);
    });

    public static final StreamCodec<RegistryFriendlyByteBuf, CandyFoodStuff> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, CandyFoodStuff::exhaustionLevel,
            ByteBufCodecs.FLOAT, CandyFoodStuff::saturationLevel,
            CandyFoodStuff::new
    );
}
