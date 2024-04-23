package com.aetherteam.aether_genesis.item.accessories.ring;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.item.accessories.ChanceDoubleDrop;
import net.minecraft.world.item.Item;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class SkyrootRingItem extends RingItem implements ChanceDoubleDrop {
    public SkyrootRingItem() {
        super(GenesisSoundEvents.ITEM_SKYROOT_RING_EQUIP, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }
}
