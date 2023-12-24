package com.aetherteam.aether_genesis.item.accessories.ring;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class CandyRingItem extends RingItem {
    private float foodLevel; //todo these needs to be moved to a capability, i dont think items can hold instanced values like this
    private float foodSaturation;

    public CandyRingItem() {
        super(AetherSoundEvents.ITEM_ACCESSORY_EQUIP_GOLD_RING, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            this.foodLevel = player.getFoodData().getExhaustionLevel();
            this.foodSaturation = player.getFoodData().getSaturationLevel();
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
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
