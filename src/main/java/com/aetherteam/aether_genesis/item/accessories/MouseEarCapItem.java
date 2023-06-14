package com.aetherteam.aether_genesis.item.accessories;

import com.aetherteam.aether.item.accessories.AccessoryItem;
import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.resources.ResourceLocation;

public class MouseEarCapItem extends AccessoryItem implements DyeableEars {
    protected ResourceLocation EARS_LOCATION;

    public MouseEarCapItem(Properties properties) {
        super(properties);
        this.setRenderTexture(Genesis.MODID, "mouse_ear_cap");
    }

    public void setRenderTexture(String modId, String registryName) {
        this.EARS_LOCATION = new ResourceLocation(modId, "textures/models/accessory/ears/" + registryName + "_accessory.png");
    }

    public ResourceLocation getEarsTexture() {
        return this.EARS_LOCATION;
    }
}
