package com.aetherteam.genesis.item.accessories.ring;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class BoneRingItem extends RingItem {
    /**
     * The unique identifier for the item's attack damage bonus.
     */
    private static final UUID ATTACK_DAMAGE_MODIFIER_UUID = UUID.fromString("00C0CF09-E29C-4BC1-87F9-B54B696F8A59");

    public BoneRingItem() {
        super(GenesisSoundEvents.ITEM_BONE_RING_EQUIP, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    /**
     * Sets up an attack damage modifier when the Bone Ring is equipped.
     *
     * @param slotContext  The {@link SlotContext} of the Curio.
     * @param uuid A unique {@link UUID} for the Curio slot.
     * @param stack The {@link ItemStack} correlating to the item.
     * @return The ({@link Multimap Multimap&lt;Attribute, AttributeModifier&gt;}) with the speed attribute.
     */
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
        attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER_UUID, "Bone Ring Damage Bonus", 1, AttributeModifier.Operation.ADDITION));
        return attributes;
    }
}
