package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.FrostboundSprite;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import top.theillusivec4.curios.api.SlotContext;

public class FrostboundStoneItem extends CompanionItem<FrostboundSprite> {
    public FrostboundStoneItem(Properties properties) {
        super(GenesisEntityTypes.FROSTBOUND_SPRITE, properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
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
