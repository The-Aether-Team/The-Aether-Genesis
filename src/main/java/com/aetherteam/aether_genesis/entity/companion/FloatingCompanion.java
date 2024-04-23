package com.aetherteam.aether_genesis.entity.companion;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.function.Supplier;

public class FloatingCompanion extends CompanionMob {
    public FloatingCompanion(EntityType<? extends FloatingCompanion> entityType, Level level, Supplier<ItemStack> summoningItem) {
        super(entityType, level, summoningItem, true);
    }

    @Override
    public void tick() {
        super.tick();
        AttributeInstance gravity = this.getAttribute(NeoForgeMod.ENTITY_GRAVITY.value());
        if (gravity != null) {
            double fallSpeed = Math.max(gravity.getValue() * -1.25, -0.1); // Entity isn't allowed to fall too slowly from gravity.
            if (this.getDeltaMovement().y() < fallSpeed) {
                this.setDeltaMovement(this.getDeltaMovement().x(), fallSpeed, this.getDeltaMovement().z());
                this.hasImpulse = true;
            }
        }
    }

    @Override
    protected void jumpFromGround() { }

    @Override
    public boolean onClimbable() {
        return this.horizontalCollision;
    }
}
