package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.aether.item.accessories.AccessoryItem;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.BabyPinkSwet;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class BabyPinkSwetItem extends AccessoryItem implements CompanionAccessory<BabyPinkSwet> {
    public BabyPinkSwetItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        CompanionAccessory.super.equip(stack, reference);
        super.onEquip(stack, reference);
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        CompanionAccessory.super.unequip(stack, reference);
        super.onUnequip(stack, reference);
    }

    public EntityType<BabyPinkSwet> getCompanionType() {
        return GenesisEntityTypes.BABY_PINK_SWET.get();
    }
}
