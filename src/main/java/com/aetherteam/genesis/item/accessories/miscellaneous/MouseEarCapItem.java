package com.aetherteam.genesis.item.accessories.miscellaneous;

import com.aetherteam.aether.item.accessories.AccessoryItem;
import com.aetherteam.genesis.AetherGenesis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

// TODO: CONVERT INTO DATA COMPONENT???
public class MouseEarCapItem extends AccessoryItem {
    private final ResourceLocation EARS_LOCATION = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "textures/models/accessory/ears/mouse_ear_cap_accessory.png");

    public MouseEarCapItem() {
        super(new Item.Properties().stacksTo(1));
    }

    public ResourceLocation getEarsTexture() {
        return this.EARS_LOCATION;
    }
}
