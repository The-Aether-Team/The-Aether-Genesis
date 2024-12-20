package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.aether.item.accessories.abilities.SlowFallAccessory;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.ShadeOfArkenzus;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class OrbOfArkenzusItem extends CompanionItem<ShadeOfArkenzus> implements SlowFallAccessory {
    public OrbOfArkenzusItem(Properties properties) {
        super(GenesisEntityTypes.SHADE_OF_ARKENZUS, properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        this.handleSlowFall(reference.entity());
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return true;
    }
}
