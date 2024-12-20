package com.aetherteam.genesis.item.accessories.cape;

import com.aetherteam.aether.item.accessories.cape.CapeItem;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.item.accessories.abilities.SpeedAccessory;
import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class DexterityCapeItem extends CapeItem implements SpeedAccessory {
    /**
     * The unique identifier for the item's movement speed bonus.
     */
    private static final ResourceLocation SPEED_MODIFIER_LOCATION = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "dexterity_cape_speed_increases");

    public DexterityCapeItem(String capeLocation, Properties properties) {
        super(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, capeLocation), properties);
    }

    @Override
    public void getDynamicModifiers(ItemStack stack, SlotReference reference, AccessoryAttributeBuilder builder) {
        this.addSpeedModifier(builder, SPEED_MODIFIER_LOCATION);
    }
}
