package com.aetherteam.aether_genesis.world.biomemodifier;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record AddMobChargeBiomeModifier(HolderSet<Biome> biomes, EntityType<?> entityType, double charge, double energyBudget) implements BiomeModifier {
    @Override
    public void modify(Holder<Biome> biome, BiomeModifier.Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == BiomeModifier.Phase.ADD && this.biomes.contains(biome)) {
            MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
            spawns.addMobCharge(this.entityType(), this.charge(), this.energyBudget());
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return GenesisBiomeModifierSerializers.ADD_MOB_CHARGE_BIOME_MODIFIER_TYPE.get();
    }
}
