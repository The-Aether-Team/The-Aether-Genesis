package com.aetherteam.genesis.item.accessories.ring;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.item.GenesisDataComponents;
import com.aetherteam.genesis.item.components.CandyFoodStuff;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class CandyRingItem extends RingItem {
    public CandyRingItem() {
        super(GenesisSoundEvents.ITEM_ACCESSORY_EQUIP_CANDY_RING, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    /**
     * Saves the player's exhaustion and saturation levels to the Candy Ring's NBT data.
     *
     * @param reference  The {@link SlotReference} of the Accessory.
     * @param stack      The {@link ItemStack} being equipped.
     */
    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (reference.entity() instanceof Player player) {
            stack.set(GenesisDataComponents.CANDY_RING_FOODSTUFF, new CandyFoodStuff(player.getFoodData().getExhaustionLevel(), player.getFoodData().getSaturationLevel()));
        }
    }

    /**
     * Keeps the player's exhaustion and saturation levels within the limits stored by the Candy Ring.
     *
     * @param reference  The {@link SlotReference} of the Curio.
     * @param stack      The {@link ItemStack} correlating to the item.
     */
    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!reference.entity().level().isClientSide()) {
            if (reference.entity() instanceof Player player && stack.has(GenesisDataComponents.CANDY_RING_FOODSTUFF)) {
                CandyFoodStuff foodStuff = stack.get(GenesisDataComponents.CANDY_RING_FOODSTUFF);
                float exhaustionLevel = foodStuff.exhaustionLevel();
                float saturationLevel = foodStuff.saturationLevel();
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
