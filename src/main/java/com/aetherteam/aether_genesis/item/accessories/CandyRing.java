package com.aetherteam.aether_genesis.item.accessories;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class CandyRing extends RingItem {
    private float foodLevel;
    private float foodSaturation;

    public CandyRing(Properties properties) {
        super(AetherSoundEvents.ITEM_ACCESSORY_EQUIP_GOLD_RING, properties);
    }

    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            this.foodLevel = player.getFoodData().getExhaustionLevel();
            this.foodSaturation = player.getFoodData().getSaturationLevel();
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level.isClientSide) {
            if (slotContext.entity() instanceof Player player) {
                if (player.getFoodData().getExhaustionLevel() > this.foodLevel) {
                    player.getFoodData().setExhaustion(foodLevel);
                }
                if (player.getFoodData().getSaturationLevel() < this.foodSaturation) {
                    player.getFoodData().setSaturation(foodSaturation);
                }
            }
        }
    }
}
