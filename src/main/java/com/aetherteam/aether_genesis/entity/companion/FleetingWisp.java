package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FleetingWisp extends Wisp {
    public FleetingWisp(EntityType<FleetingWisp> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.FLEETING_STONE.get()));
    }
}
