package com.aetherteam.aether_genesis.item.accessories;

import com.aetherteam.aether_genesis.entity.companion.SoaringWisp;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class SoaringStoneItem extends CompanionItem<SoaringWisp> {
    public SoaringStoneItem(Supplier<EntityType<SoaringWisp>> companionType, Properties properties) {
        super(companionType, properties);
    }
}
