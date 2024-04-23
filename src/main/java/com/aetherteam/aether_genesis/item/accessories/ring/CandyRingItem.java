package com.aetherteam.aether_genesis.item.accessories.ring;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class CandyRingItem extends RingItem {
    public CandyRingItem() {
        super(GenesisSoundEvents.ITEM_CANDY_RING_EQUIP, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    /**
     * Saves the player's exhaustion and saturation levels to the Candy Ring's NBT data.
     *
     * @param slotContext  The {@link SlotContext} of the Curio.
     * @param prevStack The previous {@link ItemStack} in the slot.
     * @param stack The {@link ItemStack} being equipped.
     */
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            stack.getOrCreateTag().putFloat("ExhaustionLevel", player.getFoodData().getExhaustionLevel());
            stack.getOrCreateTag().putFloat("SaturationLevel", player.getFoodData().getSaturationLevel());
        }
    }

    /**
     * Keeps the player's exhaustion and saturation levels within the limits stored by the Candy Ring.
     *
     * @param slotContext  The {@link SlotContext} of the Curio.
     * @param stack The {@link ItemStack} correlating to the item.
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            if (slotContext.entity() instanceof Player player) {
                float exhaustionLevel = stack.getOrCreateTag().getFloat("ExhaustionLevel");
                float saturationLevel = stack.getOrCreateTag().getFloat("SaturationLevel");
                if (player.getFoodData().getExhaustionLevel() > exhaustionLevel) { // Reduce exhaustion to stored level if it goes up.
                    player.getFoodData().setExhaustion(exhaustionLevel);
                }
                if (player.getFoodData().getSaturationLevel() < saturationLevel) { // Increase saturation to stored level if it goes down.
                    player.getFoodData().setSaturation(saturationLevel);
                }
            }
        }
    }
}
