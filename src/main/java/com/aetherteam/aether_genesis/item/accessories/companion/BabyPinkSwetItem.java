package com.aetherteam.aether_genesis.item.accessories.companion;

import com.aetherteam.aether.item.accessories.AccessoryItem;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.entity.companion.BabyPinkSwet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

/**
 * Behavior is handled in {@link com.aetherteam.aether_genesis.mixin.mixins.common.SwetMixin}.
 */
public class BabyPinkSwetItem extends AccessoryItem implements CompanionAccessory<BabyPinkSwet> {
    public BabyPinkSwetItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        CompanionAccessory.super.equip(slotContext, stack);
        super.onEquip(slotContext, prevStack, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        CompanionAccessory.super.unequip(slotContext);
        super.onUnequip(slotContext, newStack, stack);
    }

    public EntityType<BabyPinkSwet> getCompanionType() {
        return GenesisEntityTypes.BABY_PINK_SWET.get();
    }
}
