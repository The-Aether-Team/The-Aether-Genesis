package com.aetherteam.aether_genesis.world.biomemodifier;

import com.aetherteam.aether_genesis.Genesis;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class GenesisBiomeModifierSerializers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Genesis.MODID);

    public static final DeferredHolder<Codec<? extends BiomeModifier>, Codec<AddMobChargeBiomeModifier>> ADD_MOB_CHARGE_BIOME_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("add_mob_charge", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddMobChargeBiomeModifier::biomes),
                    BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_type").forGetter(AddMobChargeBiomeModifier::entityType),
                    Codec.DOUBLE.fieldOf("charge").forGetter(AddMobChargeBiomeModifier::charge),
                    Codec.DOUBLE.fieldOf("energy_budget").forGetter(AddMobChargeBiomeModifier::energyBudget)
            ).apply(builder, AddMobChargeBiomeModifier::new))
    );
}
