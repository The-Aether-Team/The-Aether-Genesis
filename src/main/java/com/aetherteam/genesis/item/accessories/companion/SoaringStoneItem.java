package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.SoaringWisp;
import com.aetherteam.genesis.item.accessories.abilities.SpeedAccessory;
import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SoaringStoneItem extends CompanionItem<SoaringWisp> implements SpeedAccessory {
    /**
     * The unique identifier for the item's movement speed bonus.
     */
    private static final ResourceLocation SPEED_MODIFIER_LOCATION = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "soaring_stone_speed_increases");

    public SoaringStoneItem(Properties properties) {
        super(GenesisEntityTypes.SOARING_WISP, properties);
    }

    @Override
    public void getDynamicModifiers(ItemStack stack, SlotReference reference, AccessoryAttributeBuilder builder) {
        this.addSpeedModifier(builder, SPEED_MODIFIER_LOCATION);
    }

}
