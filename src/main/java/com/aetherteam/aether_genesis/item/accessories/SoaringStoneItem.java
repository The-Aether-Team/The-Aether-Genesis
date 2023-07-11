package com.aetherteam.aether_genesis.item.accessories;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class SoaringStoneItem extends CompanionItem {
    public SoaringStoneItem(Supplier<? extends EntityType<? extends Entity>> companionType, Properties properties) {
        super(companionType, properties);
    }
}
