package com.aetherteam.aether_genesis.item.accessories;

import com.aetherteam.aether_genesis.entity.companion.Wisp;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class SoaringStoneItem extends CompanionItem<Wisp, EntityType<Wisp>> {
    public SoaringStoneItem(Supplier<EntityType<Wisp>> companionType, Properties properties) {
        super(companionType, properties);
    }
}
