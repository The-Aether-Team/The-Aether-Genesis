package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.FrostboundSprite;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FrostboundStoneItem extends CompanionItem<FrostboundSprite> {
    public FrostboundStoneItem(Properties properties) {
        super(GenesisEntityTypes.FROSTBOUND_SPRITE, properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        LivingEntity livingEntity = reference.entity();
        if (livingEntity instanceof Player player) {
            if (player.tickCount % 25 == 0) {
                int i = player.getInventory().findSlotMatchingItem(new ItemStack(Items.SNOWBALL));
                if (i == -1) {
                    player.getInventory().add(new ItemStack(Items.SNOWBALL));
                } else {
                    ItemStack snowball = player.getInventory().getItem(i);
                    if (snowball.getCount() < snowball.getMaxStackSize()) {
                        snowball.setCount(snowball.getCount() + 1); //todo why does this run immediately after when the snowball is added.
                    }
                }
            }
        }
    }
}
