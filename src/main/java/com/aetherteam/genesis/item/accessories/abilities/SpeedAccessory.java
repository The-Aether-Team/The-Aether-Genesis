package com.aetherteam.genesis.item.accessories.abilities;

import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public interface SpeedAccessory {
    /**
     * Sets up a speed modifier for an accessory when equipped.<br><br>
     *
     * @param location        A unique {@link UUID} for the attribute.
     */
    default void addSpeedModifier(AccessoryAttributeBuilder builder, ResourceLocation location) {
        builder.addExclusive(Attributes.MOVEMENT_SPEED, new AttributeModifier(location, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }
}
