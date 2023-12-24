package com.aetherteam.aether_genesis.item.accessories.companion;

import com.aetherteam.aether.item.accessories.AccessoryItem;
import com.aetherteam.aether_genesis.entity.companion.CompanionMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Supplier;

public class CompanionItem<T extends CompanionMob> extends AccessoryItem implements Companion<T> {
    private final Supplier<EntityType<T>> companionType;

    public CompanionItem(Supplier<EntityType<T>> companionType, Properties properties) {
        super(properties);
        this.companionType = companionType;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Companion.super.equip(slotContext);
        super.onEquip(slotContext, prevStack, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Companion.super.unequip(slotContext);
        super.onUnequip(slotContext, newStack, stack);
    }

    public EntityType<T> getCompanionType() {
        return this.companionType.get();
    }
}
