package com.aetherteam.genesis.item.accessories.ring;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class BoneRingItem extends RingItem {
    /**
     * The unique identifier for the item's attack damage bonus.
     */
    private static final ResourceLocation ATTACK_DAMAGE_MODIFIER_LOCATION = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "bone_ring_damage_bonus");

    public BoneRingItem() {
        super(GenesisSoundEvents.ITEM_ACCESSORY_EQUIP_BONE_RING, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    /**
     * Sets up an attack damage modifier when the Bone Ring is equipped.
     *
     * @param reference  The {@link SlotReference} of the Accessory.
     * @param builder    An attribute builder for the given Accessory slot.
     * @param stack      The {@link ItemStack} correlating to the item.
     */
    @Override
    public void getDynamicModifiers(ItemStack stack, SlotReference reference, AccessoryAttributeBuilder builder) {
        builder.addExclusive(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER_LOCATION, 1, AttributeModifier.Operation.ADD_VALUE));
    }
}
