package com.aetherteam.aether_genesis.item.accessories.companion;

import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.entity.companion.SoaringWisp;
import com.aetherteam.aether_genesis.item.accessories.abilities.SpeedAccessory;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class SoaringStoneItem extends CompanionItem<SoaringWisp> implements SpeedAccessory {
    /**
     * The unique identifier for the item's movement speed bonus.
     */
    private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("D0AF3952-D70E-4A7E-B686-90CFA5C465ED");

    public SoaringStoneItem(Properties properties) {
        super(GenesisEntityTypes.SOARING_WISP, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        return this.addSpeedModifier(SPEED_MODIFIER_UUID);
    }
}
