package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.aether.item.accessories.AccessoryItem;
import com.aetherteam.genesis.entity.companion.CompanionMob;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class CompanionItem<T extends CompanionMob> extends AccessoryItem implements CompanionAccessory<T> {
    private final Supplier<EntityType<T>> companionType;

    public CompanionItem(Supplier<EntityType<T>> companionType, Properties properties) {
        super(properties);
        this.companionType = companionType;
    }

    /**
     * @see CompanionAccessory#equip(ItemStack, SlotReference)
     */
    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        CompanionAccessory.super.equip(stack, reference);
    }

    /**
     * @see CompanionAccessory#unequip(ItemStack, SlotReference)
     */
    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        CompanionAccessory.super.unequip(stack, reference);
    }

    /**
     * @return The companion {@link EntityType}.
     */
    public EntityType<T> getCompanionType() {
        return this.companionType.get();
    }
}
