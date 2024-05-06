package com.aetherteam.genesis.item.accessories.cape;

import com.aetherteam.aether.item.accessories.cape.CapeItem;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.item.accessories.abilities.SpeedAccessory;
import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class DexterityCapeItem extends CapeItem implements SpeedAccessory {
    /**
     * The unique identifier for the item's movement speed bonus.
     */
    private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("779AFB2C-54DC-4F43-8A0B-1A2DD1F77C09");

    public DexterityCapeItem(String capeLocation, Properties properties) {
        super(new ResourceLocation(AetherGenesis.MODID, capeLocation), properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        return this.addSpeedModifier(SPEED_MODIFIER_UUID);
    }
}
