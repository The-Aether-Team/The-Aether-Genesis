package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SoaringWisp extends Wisp {
    public SoaringWisp(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.SOARING_STONE.get()));
    }
}
