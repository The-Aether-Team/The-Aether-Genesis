package com.aetherteam.aether_genesis.item.accessories;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.aether_genesis.item.ChanceDoubleDrop;
import net.minecraft.sounds.SoundEvents;

public class SkyrootRingItem extends RingItem implements ChanceDoubleDrop {
    public SkyrootRingItem(Properties properties) {
        super(() -> SoundEvents.WOOD_PLACE, properties);
    }
}
