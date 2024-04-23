package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrostpineTotem extends FloatingCompanion {
    public FrostpineTotem(EntityType<FrostpineTotem> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.FROSTPINE_TOTEM.get()));
    }
}
