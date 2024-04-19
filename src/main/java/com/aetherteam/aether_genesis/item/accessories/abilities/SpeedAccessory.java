package com.aetherteam.aether_genesis.item.accessories.abilities;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public interface SpeedAccessory {
    /**
     * Sets up a speed modifier for an accessory when equipped.<br><br>
     *
     * @param uuid        A unique {@link UUID} for the attribute.
     * @return The ({@link Multimap Multimap&lt;Attribute, AttributeModifier&gt;}) with the speed attribute.
     */
    default Multimap<Attribute, AttributeModifier> addSpeedModifier(UUID uuid) {
        Multimap<Attribute, AttributeModifier> map = LinkedHashMultimap.create();
        map.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Speed bonus", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return map;
    }
}
