package com.aetherteam.aether_genesis.item.accessories.cape;

import com.aetherteam.aether.item.accessories.cape.CapeItem;
import com.aetherteam.aether_genesis.Genesis;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class DexterityCapeItem extends CapeItem {
    public DexterityCapeItem(String capeLocation, Properties properties) {
        super(new ResourceLocation(Genesis.MODID, capeLocation), properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> map = LinkedHashMultimap.create();
        map.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Dexterity speed bonus", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return map;
    }
}
