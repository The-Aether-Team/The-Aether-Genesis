package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Fangrin extends CompanionMob { //todo melee?
    public Fangrin(EntityType<Fangrin> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.FANGRIN_CAPSULE.get()), false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 50.0).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.FOLLOW_RANGE, 48.0);
    }
}
