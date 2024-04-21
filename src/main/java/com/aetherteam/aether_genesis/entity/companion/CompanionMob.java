package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.entity.ai.goal.CompanionFollowGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public abstract class CompanionMob extends PathfinderMob implements Companion<CompanionMob> {
    private static final UUID SLOW_FALLING_ID = UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA");
    private static final AttributeModifier SLOW_FALLING = new AttributeModifier(SLOW_FALLING_ID, "Slow falling acceleration reduction", -0.07, AttributeModifier.Operation.ADDITION); // Add -0.07 to 0.08 so we get the vanilla default of 0.01
    private static final EntityDataAccessor<Optional<UUID>> DATA_OWNER_ID = SynchedEntityData.defineId(CompanionMob.class, EntityDataSerializers.OPTIONAL_UUID);

    private final Supplier<ItemStack> summoningItem;
    private final boolean isFloating;

    protected CompanionMob(EntityType<? extends PathfinderMob> entityType, Level level, Supplier<ItemStack> summoningItem, boolean isFloating) {
        super(entityType, level);
        this.summoningItem = summoningItem;
        this.isFloating = isFloating;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.FOLLOW_RANGE, 48.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new CompanionFollowGoal<>(this, 1.0D));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_OWNER_ID, Optional.empty());
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        if (tag != null) {
            if (tag.contains("Owner")) {
                this.setOwner(tag.getUUID("Owner"));
            }
        }
        return spawnData;
    }

    @Override
    public void tick() {
        super.tick();
        this.tickCompanion(this);
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isControlledByLocalInstance()) {
            double gravityValue;
            AttributeInstance gravity = this.getAttribute(NeoForgeMod.ENTITY_GRAVITY.value());
            boolean isFalling = this.getDeltaMovement().y() <= 0.0;
            if (isFalling && this.hasEffect(MobEffects.SLOW_FALLING)) {
                if (!gravity.hasModifier(SLOW_FALLING)) {
                    gravity.addTransientModifier(SLOW_FALLING);
                }
                this.resetFallDistance();
            } else if (gravity.hasModifier(SLOW_FALLING)) {
                gravity.removeModifier(SLOW_FALLING.getId());
            }
            gravityValue = gravity.getValue();

            FluidState fluidState = this.level().getFluidState(this.blockPosition());
            if ((this.isInWater() || (this.isInFluidType(fluidState) && fluidState.getFluidType() != NeoForgeMod.LAVA_TYPE.value())) && this.isAffectedByFluids() && !this.canStandOnFluid(fluidState)) {
                if (this.isInWater() || (this.isInFluidType(fluidState) && !this.moveInFluid(fluidState, travelVector, gravityValue))) {
                    double y = this.getY();
                    float speedModifier = this.isSprinting() ? 0.9F : this.getWaterSlowDown();
                    float speed = 0.02F;
                    float depthStrider = (float) EnchantmentHelper.getDepthStrider(this);
                    if (depthStrider > 3.0F) {
                        depthStrider = 3.0F;
                    }

                    if (!this.onGround()) {
                        depthStrider *= 0.5F;
                    }

                    if (depthStrider > 0.0F) {
                        speedModifier += (0.55F - speedModifier) * depthStrider / 3.0F;
                        speed += (this.getSpeed() - speed) * depthStrider / 3.0F;
                    }

                    if (this.hasEffect(MobEffects.DOLPHINS_GRACE)) {
                        speedModifier = 0.96F;
                    }

                    speed *= (float) this.getAttribute(NeoForgeMod.SWIM_SPEED.value()).getValue();
                    this.moveRelative(speed, travelVector);
                    this.move(MoverType.SELF, this.getDeltaMovement());
                    Vec3 movement = this.getDeltaMovement();
                    if (this.horizontalCollision && this.onClimbable()) {
                        movement = new Vec3(movement.x(), 0.2, movement.z());
                    }

                    this.setDeltaMovement(movement.multiply(speedModifier, 0.8F, speedModifier));
                    Vec3 fluidMovement = this.getFluidFallingAdjustedMovement(gravityValue, isFalling, this.getDeltaMovement());
                    this.setDeltaMovement(fluidMovement);
                    if (this.horizontalCollision && this.isFree(fluidMovement.x(), fluidMovement.y() + (double) 0.6F - this.getY() + y, fluidMovement.z())) {
                        this.setDeltaMovement(fluidMovement.x(), 0.3, fluidMovement.z());
                    }
                }
            } else if (this.isInLava() && this.isAffectedByFluids() && !this.canStandOnFluid(fluidState)) {
                double y = this.getY();
                this.moveRelative(0.02F, travelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                if (this.getFluidHeight(FluidTags.LAVA) <= this.getFluidJumpThreshold()) {
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.5, 0.8, 0.5));
                    Vec3 fluidMovement = this.getFluidFallingAdjustedMovement(gravityValue, isFalling, this.getDeltaMovement());
                    this.setDeltaMovement(fluidMovement);
                } else {
                    this.setDeltaMovement(this.getDeltaMovement().scale(0.5));
                }

                if (!this.isNoGravity()) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0, -gravityValue / 4.0, 0.0));
                }

                Vec3 movement = this.getDeltaMovement();
                if (this.horizontalCollision && this.isFree(movement.x(), movement.y() + (double) 0.6F - this.getY() + y, movement.z())) {
                    this.setDeltaMovement(movement.x(), 0.3, movement.z());
                }
            } else {
                BlockPos belowPos = this.getBlockPosBelowThatAffectsMyMovement();
                float friction = this.getFrictionModifier();
                float speed = this.onGround() ? friction * 0.91F : 0.91F;
                Vec3 frictionMovement = this.handleRelativeFrictionAndCalculateMovement(travelVector, friction);
                double deltaY = frictionMovement.y();
                if (this.hasEffect(MobEffects.LEVITATION)) {
                    deltaY += (0.05 * (double) (this.getEffect(MobEffects.LEVITATION).getAmplifier() + 1) - frictionMovement.y()) * 0.2;
                    this.resetFallDistance();
                } else if (this.level().isClientSide() && !this.level().hasChunkAt(belowPos)) {
                    if (this.getY() > (double) this.level().getMinBuildHeight()) {
                        deltaY = -0.1;
                    } else {
                        deltaY = 0.0;
                    }
                } else if (!this.isNoGravity()) {
                    deltaY -= gravityValue;
                }

                if (this.shouldDiscardFriction()) {
                    this.setDeltaMovement(frictionMovement.x(), deltaY, frictionMovement.z());
                } else {
                    this.setDeltaMovement(frictionMovement.x() * (double) speed, deltaY * 0.98, frictionMovement.z() * (double) speed);
                }
            }
        }

        this.calculateEntityAnimation(false);
    }

    public UUID getOwner() {
        return this.getEntityData().get(DATA_OWNER_ID).orElse(null);
    }

    public void setOwner(UUID owner) {
        this.getEntityData().set(DATA_OWNER_ID, Optional.ofNullable(owner));
    }

    public ItemStack getSummonItem() {
        return this.summoningItem.get();
    }

    public boolean isFloating() {
        return this.isFloating;
    }

    public float getFrictionModifier() {
        if (this.isFloating() && Math.abs(this.getDeltaMovement().x() * this.getDeltaMovement().z()) < 0.00075) {
            return 0.95F;
        } else {
            return 0.6F;
        }
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

    @Override
    public boolean shouldShowName() {
        return true;
    }

    @Nullable
    @Override
    public ItemStack getPickResult() {
        return this.getSummonItem();
    }
}
