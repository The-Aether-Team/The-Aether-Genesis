package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Fangrin extends CompanionMob {
    public Fangrin(EntityType<Fangrin> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.FANGRIN_CAPSULE.get()), false);
    }
}
