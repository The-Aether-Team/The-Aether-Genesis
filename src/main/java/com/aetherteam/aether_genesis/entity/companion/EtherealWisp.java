package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EtherealWisp extends Wisp {
    public EtherealWisp(EntityType<EtherealWisp> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.ETHEREAL_STONE.get()));
    }
}
