package com.aetherteam.aether_genesis.item.accessories.ring;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class BoneRingItem extends RingItem { //todo sound effects
    public BoneRingItem() {
        super(() -> SoundEvents.SKELETON_AMBIENT, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
        attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Bone Ring Damage Bonus", 3, AttributeModifier.Operation.ADDITION)); //todo balanace
        return attributes;
    }
}
