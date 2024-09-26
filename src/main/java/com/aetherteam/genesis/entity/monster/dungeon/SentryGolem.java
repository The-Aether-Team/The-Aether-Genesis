package com.aetherteam.genesis.entity.monster.dungeon;

import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.projectile.DetonationProjectile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class SentryGolem extends Monster implements RangedAttackMob {
    public static final EntityDataAccessor<Byte> DATA_HAND_STATE_ID = SynchedEntityData.defineId(SentryGolem.class, EntityDataSerializers.BYTE);
    public static final EntityDataAccessor<Integer> DATA_FIRE_TIME_ID = SynchedEntityData.defineId(SentryGolem.class, EntityDataSerializers.INT);
    public int timeTilToss = 50;
    public float progress = 0.0F;

    public SentryGolem(EntityType<? extends SentryGolem> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(2, new ThrowExplosiveAttackGoal(this, 60, 0.08F, 49.0F, 100.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createMobAttributes() { //todo
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.28)
                .add(Attributes.FOLLOW_RANGE, 50.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HAND_STATE_ID, (byte) 2);
        this.entityData.define(DATA_FIRE_TIME_ID, 0);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            if (this.getTarget() != null) {
                if (this.timeTilToss != 0) {
                    --this.timeTilToss;
                } else {
                    this.timeTilToss = 50;
                }
            }
        }
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distance) {
        DetonationProjectile bomb = new DetonationProjectile(GenesisEntityTypes.DETONATION_PROJECTILE.get(), this, this.level());
        double x = target.getX() - this.getX();
        double y = target.getEyeY() - this.getY() - 1.0;
        double z = target.getZ() - this.getZ();
        double length = Math.sqrt(x * x + z * z);
        bomb.shoot(x, y + (length * 0.2F), z, 0.5F, 8.0F);
        bomb.setYRot(this.yBodyRot);
//        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(bomb);
    }

    public byte getHandState() {
        return this.entityData.get(DATA_HAND_STATE_ID);
    }

    public void setHandState(byte state) {
        this.entityData.set(DATA_HAND_STATE_ID, state);
    }

    public int getFireTime() {
        return this.entityData.get(DATA_FIRE_TIME_ID);
    }

    public void setFireTime(int time) {
        this.entityData.set(DATA_FIRE_TIME_ID, time);
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        tag.putByte("arm_state", this.getHandState());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (tag.contains("arm_state")) {
            this.setHandState(tag.getByte("arm_state"));
        }
    }

    public static class ThrowExplosiveAttackGoal extends Goal {
        private final SentryGolem golem;
        @Nullable
        private LivingEntity target;
        private final int maxRangedAttackTime;
        private int attackTime = -1;
        private final double speedModifier;
        private int seeTime;
        private final float maxAttackRange;
        private final float minAttackRange;

        public ThrowExplosiveAttackGoal(SentryGolem golem, int maxRangedAttackTime, double speedModifier, float minAttackRange, float maxAttackRange) {
            this.golem = golem;
            this.maxRangedAttackTime = maxRangedAttackTime;
            this.speedModifier = speedModifier;
            this.minAttackRange = minAttackRange;
            this.maxAttackRange = maxAttackRange;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity targetEntity = this.golem.getTarget();
            if (targetEntity != null && targetEntity.isAlive()) {
                this.target = targetEntity;
                return true;
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return this.canUse() || this.target.isAlive() && !this.golem.getNavigation().isDone();
        }

        public void stop() {
            this.target = null;
            this.seeTime = 0;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            double distance = this.golem.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
            boolean canSee = this.golem.getSensing().hasLineOfSight(this.target);
            if (canSee) {
                ++this.seeTime;
            } else {
                this.seeTime = 0;
            }

            if (distance <= (double) this.minAttackRange && this.seeTime >= 20) {
                Vec3 randomPos = LandRandomPos.getPos(this.golem, 16, 7);
                if (randomPos != null) {
                    this.golem.getNavigation().moveTo(randomPos.x(), randomPos.y(), randomPos.z(), this.speedModifier);
                } else {
                    this.golem.getNavigation().moveTo(this.target, this.speedModifier);
                }
            } else if (distance <= (double) this.maxAttackRange && this.seeTime >= 20) {
                this.golem.getNavigation().stop();
            } else {
                this.golem.getNavigation().moveTo(this.target, this.speedModifier);
            }

            this.golem.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

            this.attackTime = Math.max(this.attackTime - 1, 0);
            this.golem.setFireTime(this.attackTime);
            if (this.attackTime <= 30) {
                this.golem.setHandState((byte) 1);
            }

            if (this.attackTime <= 0 && distance <= (double) this.maxAttackRange && canSee) {
                this.golem.performRangedAttack(this.target, 1.0F);
                this.attackTime = this.maxRangedAttackTime;
                this.golem.setHandState((byte) 2);
            }
        }
    }
}
