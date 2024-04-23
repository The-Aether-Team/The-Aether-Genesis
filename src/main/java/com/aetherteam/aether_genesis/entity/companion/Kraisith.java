package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Kraisith extends CompanionMob {
    public Kraisith(EntityType<Kraisith> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.KRAISITH_CAPSULE.get()), false);
    }
}
