package com.aetherteam.aether_genesis.entity.companion;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;

import java.util.function.Supplier;

public class Wisp extends CompanionMob {
    private float armRotO;
    private float armRot;

    public Wisp(EntityType<? extends Wisp> entityType, Level level, Supplier<ItemStack> summoningItem) {
        super(entityType, level, summoningItem, true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.FOLLOW_RANGE, 48.0);
    }

    @Override
    public void tick() {
        super.tick();
        AttributeInstance gravity = this.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
        if (gravity != null) {
            double fallSpeed = Math.max(gravity.getValue() * -1.25, -0.1); // Entity isn't allowed to fall too slowly from gravity.
            if (this.getDeltaMovement().y() < fallSpeed) {
                this.setDeltaMovement(this.getDeltaMovement().x(), fallSpeed, this.getDeltaMovement().z());
                this.hasImpulse = true;
            }
        }
        if (this.getLevel().isClientSide()) {
            this.armRotO = this.armRot;
            this.armRot += 0.5F;
        }
    }

    @Override
    protected void jumpFromGround() { }

    public float getArmRotO() {
        return this.armRotO;
    }

    public float getArmRot() {
        return this.armRot;
    }

    @Override
    public boolean onClimbable() {
        return this.horizontalCollision;
    }
}
