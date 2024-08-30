package com.aetherteam.genesis.entity.companion;

import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SoaringWisp extends Wisp {
    public SoaringWisp(EntityType<SoaringWisp> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.SOARING_STONE.get()));
    }
}
