package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.entity.ai.goal.CompanionFollowGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public interface Companion {
    UUID SLOW_FALLING_ID = UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA");
    AttributeModifier SLOW_FALLING = new AttributeModifier(SLOW_FALLING_ID, "Slow falling acceleration reduction", -0.07, AttributeModifier.Operation.ADDITION); // Add -0.07 to 0.08 so we get the vanilla default of 0.01

    default void registerGoals(PathfinderMob mob, GoalSelector goals) {
        goals.addGoal(0, new CompanionFollowGoal(mob, 1.0F));
        goals.addGoal(1, new LookAtPlayerGoal(mob, Player.class, 8.0F));
        goals.addGoal(2, new RandomLookAroundGoal(mob));
    }

    default void travel(LivingEntity livingEntity, Vec3 travelVector) {
        if (livingEntity.isControlledByLocalInstance()) {
            double gravityValue;
            AttributeInstance gravity = livingEntity.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
            boolean isFalling = livingEntity.getDeltaMovement().y() <= 0.0;
            if (isFalling && livingEntity.hasEffect(MobEffects.SLOW_FALLING)) {
                if (!gravity.hasModifier(SLOW_FALLING)) {
                    gravity.addTransientModifier(SLOW_FALLING);
                }
                livingEntity.resetFallDistance();
            } else if (gravity.hasModifier(SLOW_FALLING)) {
                gravity.removeModifier(SLOW_FALLING);
            }
            gravityValue = gravity.getValue();

            FluidState fluidState = livingEntity.getLevel().getFluidState(livingEntity.blockPosition());
            if ((livingEntity.isInWater() || (livingEntity.isInFluidType(fluidState) && fluidState.getFluidType() != ForgeMod.LAVA_TYPE.get())) && this.isAffectedByFluids() && !livingEntity.canStandOnFluid(fluidState)) {
                if (livingEntity.isInWater() || (livingEntity.isInFluidType(fluidState) && !livingEntity.moveInFluid(fluidState, travelVector, gravityValue))) {
                    double y = livingEntity.getY();
                    float speedModifier = livingEntity.isSprinting() ? 0.9F : this.getWaterSlowDown();
                    float speed = 0.02F;
                    float depthStrider = (float) EnchantmentHelper.getDepthStrider(livingEntity);
                    if (depthStrider > 3.0F) {
                        depthStrider = 3.0F;
                    }

                    if (!livingEntity.isOnGround()) {
                        depthStrider *= 0.5F;
                    }

                    if (depthStrider > 0.0F) {
                        speedModifier += (0.55F - speedModifier) * depthStrider / 3.0F;
                        speed += (livingEntity.getSpeed() - speed) * depthStrider / 3.0F;
                    }

                    if (livingEntity.hasEffect(MobEffects.DOLPHINS_GRACE)) {
                        speedModifier = 0.96F;
                    }

                    speed *= (float) livingEntity.getAttribute(ForgeMod.SWIM_SPEED.get()).getValue();
                    livingEntity.moveRelative(speed, travelVector);
                    livingEntity.move(MoverType.SELF, livingEntity.getDeltaMovement());
                    Vec3 movement = livingEntity.getDeltaMovement();
                    if (livingEntity.horizontalCollision && livingEntity.onClimbable()) {
                        movement = new Vec3(movement.x(), 0.2, movement.z());
                    }

                    livingEntity.setDeltaMovement(movement.multiply(speedModifier, 0.8F, speedModifier));
                    Vec3 fluidMovement = livingEntity.getFluidFallingAdjustedMovement(gravityValue, isFalling, livingEntity.getDeltaMovement());
                    livingEntity.setDeltaMovement(fluidMovement);
                    if (livingEntity.horizontalCollision && livingEntity.isFree(fluidMovement.x(), fluidMovement.y() + (double) 0.6F - livingEntity.getY() + y, fluidMovement.z())) {
                        livingEntity.setDeltaMovement(fluidMovement.x(), 0.3, fluidMovement.z());
                    }
                }
            } else if (livingEntity.isInLava() && this.isAffectedByFluids() && !livingEntity.canStandOnFluid(fluidState)) {
                double y = livingEntity.getY();
                livingEntity.moveRelative(0.02F, travelVector);
                livingEntity.move(MoverType.SELF, livingEntity.getDeltaMovement());
                if (livingEntity.getFluidHeight(FluidTags.LAVA) <= livingEntity.getFluidJumpThreshold()) {
                    livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().multiply(0.5, 0.8, 0.5));
                    Vec3 fluidMovement = livingEntity.getFluidFallingAdjustedMovement(gravityValue, isFalling, livingEntity.getDeltaMovement());
                    livingEntity.setDeltaMovement(fluidMovement);
                } else {
                    livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().scale(0.5));
                }

                if (!livingEntity.isNoGravity()) {
                    livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().add(0.0, -gravityValue / 4.0, 0.0));
                }

                Vec3 movement = livingEntity.getDeltaMovement();
                if (livingEntity.horizontalCollision && livingEntity.isFree(movement.x(), movement.y() + (double) 0.6F - livingEntity.getY() + y, movement.z())) {
                    livingEntity.setDeltaMovement(movement.x(), 0.3, movement.z());
                }
            } else {
                BlockPos belowPos = this.getMovementAffectingBlockPos();
                float friction = this.getFrictionModifier();
                float speed = livingEntity.isOnGround() ? friction * 0.91F : 0.91F;
                Vec3 frictionMovement = livingEntity.handleRelativeFrictionAndCalculateMovement(travelVector, friction);
                double deltaY = frictionMovement.y();
                if (livingEntity.hasEffect(MobEffects.LEVITATION)) {
                    deltaY += (0.05 * (double) (livingEntity.getEffect(MobEffects.LEVITATION).getAmplifier() + 1) - frictionMovement.y()) * 0.2;
                    livingEntity.resetFallDistance();
                } else if (livingEntity.getLevel().isClientSide() && !livingEntity.getLevel().hasChunkAt(belowPos)) {
                    if (livingEntity.getY() > (double) livingEntity.getLevel().getMinBuildHeight()) {
                        deltaY = -0.1;
                    } else {
                        deltaY = 0.0;
                    }
                } else if (!livingEntity.isNoGravity()) {
                    deltaY -= gravityValue;
                }

                if (livingEntity.shouldDiscardFriction()) {
                    livingEntity.setDeltaMovement(frictionMovement.x(), deltaY, frictionMovement.z());
                } else {
                    livingEntity.setDeltaMovement(frictionMovement.x() * (double) speed, deltaY * 0.98, frictionMovement.z() * (double) speed);
                }
            }
        }

        livingEntity.calculateEntityAnimation(false);
    }

    void setOwner(UUID owner);
    UUID getOwner();

    ItemStack getSummonItem();

    float getFrictionModifier();

    boolean isAffectedByFluids();
    float getWaterSlowDown();
    BlockPos getMovementAffectingBlockPos();
}
