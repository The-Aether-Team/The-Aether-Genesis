package com.aetherteam.genesis.entity.monster.dungeon;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.mixin.mixins.common.accessor.RangedAttackGoalAccessor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class SentryGolem extends Monster implements RangedAttackMob {
    public static final EntityDataAccessor<Integer> DATA_BOMB_CHARGE_ID = SynchedEntityData.defineId(SentryGolem.class, EntityDataSerializers.INT);

    public SentryGolem(EntityType<? extends SentryGolem> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(2, new ThrowExplosiveAttackGoal(this, 1.0, 10.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createMobAttributes() { //todo
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.28)
                .add(Attributes.FOLLOW_RANGE, 8.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BOMB_CHARGE_ID, 0);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTarget() != null) {
            if (this.getBombCharge() <= 0) {
                this.setBombCharge(1);
            }
        }
        if (this.getBombCharge() > 0 && this.getBombCharge() < 120) {
            this.setBombCharge(Math.min(120, this.getBombCharge() + 1));
        }
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distance) {
        Snowball snowball = new Snowball(this.level(), this);
        double d0 = target.getEyeY() - 1.100000023841858;
        double d1 = target.getX() - this.getX();
        double d2 = d0 - snowball.getY();
        double d3 = target.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224;
        snowball.shoot(d1, d2 + d4, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(snowball);
    }

    public int getBombCharge() {
        return this.entityData.get(DATA_BOMB_CHARGE_ID);
    }

    public void setBombCharge(int bombCharge) {
        this.entityData.set(DATA_BOMB_CHARGE_ID, bombCharge);
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    public static class ThrowExplosiveAttackGoal extends Goal {
        private final SentryGolem golem;
        @Nullable
        private LivingEntity target;
        private final double speedModifier;
        private int seeTime;
        private final float attackRadius;
        private final float attackRadiusSqr;

        public ThrowExplosiveAttackGoal(SentryGolem golem, double speedModifier, float attackRadius) {
            this.golem = golem;
            this.speedModifier = speedModifier;
            this.attackRadius = attackRadius;
            this.attackRadiusSqr = attackRadius * attackRadius;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity targetEntity = this.golem.getTarget();
            if (targetEntity != null && targetEntity.isAlive() && this.golem.getBombCharge() > 0) {
                this.target = targetEntity;
                return true;
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return this.canUse() || this.target.isAlive() && !this.golem.getNavigation().isDone() && this.golem.getBombCharge() > 0;
        }

        public void stop() {
            this.target = null;
            this.seeTime = 0;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            double d0 = this.golem.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
            boolean flag = this.golem.getSensing().hasLineOfSight(this.target);
            if (flag) {
                ++this.seeTime;
            } else {
                this.seeTime = 0;
            }

            if (!(d0 > (double) this.attackRadiusSqr) && this.seeTime >= 5) {
                this.golem.getNavigation().stop();
            } else {
                this.golem.getNavigation().moveTo(this.target, this.speedModifier);
            }

            this.golem.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

            if (flag && this.golem.getBombCharge() >= 120) {
                float f = (float) Math.sqrt(d0) / this.attackRadius;
                float f1 = Mth.clamp(f, 0.1F, 1.0F);
                if (this.golem.getBombCharge() >= 100) {
                    this.golem.performRangedAttack(this.target, f1);
                    this.golem.setBombCharge(0);
                }
            }
        }
    }
}
