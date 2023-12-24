package com.aetherteam.aether_genesis.item.accessories.companion;

import com.aetherteam.aether_genesis.entity.companion.SoaringWisp;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;
import java.util.function.Supplier;

public class SoaringStoneItem extends CompanionItem<SoaringWisp> {
    public SoaringStoneItem(Supplier<EntityType<SoaringWisp>> companionType, Properties properties) {
        super(companionType, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> map = LinkedHashMultimap.create();
        map.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Soaring Stone speed bonus", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return map;
    }
}
