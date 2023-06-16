package com.aetherteam.aether_genesis.entity.monster;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.entity.miscellaneous.TempestThunderBall;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import javax.annotation.Nonnull;

public class Tempest extends Zephyr {
    public static final EntityDataAccessor<Integer> DATA_ATTACK_CHARGE_ID = SynchedEntityData.defineId(Tempest.class, EntityDataSerializers.INT);
    public static final Vector3f TEMPEST_PARTICLE_COLOR = Vec3.fromRGB24(16777215).toVector3f();
    public static final DustParticleOptions TEMPEST_PARTICLES = new DustParticleOptions(TEMPEST_PARTICLE_COLOR, 1.0F);

    public Tempest(EntityType<? extends Tempest> type, Level level) {
        super(type, level);
        this.moveControl = new Zephyr.MoveHelperController(this);
        this.xpReward = 20;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new Zephyr.RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new Zephyr.LookAroundGoal(this));
        this.goalSelector.addGoal(5, new Tempest.ThunderballAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
    }

    @Nonnull
    public static AttributeSupplier.Builder createMobAttributes() {
        return FlyingMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25.0)
                .add(Attributes.FOLLOW_RANGE, 50.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ATTACK_CHARGE_ID, 0);
    }

    public static boolean checkTempestSpawnRules(EntityType<? extends Tempest> tempest, LevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && Mob.checkMobSpawnRules(tempest, level, reason, pos, random) && (reason != MobSpawnType.NATURAL || random.nextInt(11) == 0) && level.canSeeSky(pos) && isNight(level);
    }

    private static boolean isNight(LevelAccessor level){
        return !(level.getSkyDarken() < 4) && !level.dimensionType().hasFixedTime();
    }

    @Override
    public void tick() {
        if (!this.getLevel().isClientSide() && !isNight(this.level)) {
            this.hurt(this.level.damageSources().generic(), 1);
            for (int i = 0; i < 7; ++i) {
                if (this.getLevel() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.SMOKE, this.getRandomX(0.3), this.getRandomY() - 0.1, this.getRandomZ(0.3), 1, 0, 0, 0, this.random.nextGaussian() * 0.02);
                }
            }
        }
        for (int count = 0; count < 3; ++count) {
            double xOffset = this.position().x() + (level.getRandom().nextDouble() * 1.5) - 0.75;
            double yOffset = this.position().y() + (level.getRandom().nextDouble() * 2) - 0.5;
            double zOffset = this.position().z() + (level.getRandom().nextDouble() * 1.5) - 0.75;
            level.addParticle(TEMPEST_PARTICLES, xOffset, yOffset, zOffset, 0.0, 0.0, 0.0);
        }
        super.tick();
    }

    protected SoundEvent getAmbientSound() {
        return GenesisSoundEvents.ENTITY_TEMPEST_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return GenesisSoundEvents.ENTITY_TEMPEST_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return GenesisSoundEvents.ENTITY_TEMPEST_DEATH.get();
    }


    static class ThunderballAttackGoal extends Goal {
        private final Tempest parentEntity;
        public int attackTimer;

        public ThunderballAttackGoal(Tempest tempest) {
            this.parentEntity = tempest;
        }

        /**
         * Returns whether execution should begin. You can also read and cache
         * any state necessary for execution in this method as well.
         */
        @Override
        public boolean canUse() {
            return parentEntity.getTarget() != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void start() {
            this.attackTimer = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted
         * by another one
         */
        @Override
        public void stop() {
            this.parentEntity.setAttackCharge(0);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void tick() {
            LivingEntity target = this.parentEntity.getTarget();
            if (target.distanceToSqr(this.parentEntity) < 40 * 40 && this.parentEntity.hasLineOfSight(target)) {
                Level level = this.parentEntity.level;
                ++this.attackTimer;
                if (this.attackTimer == 10) {
                    this.parentEntity.playSound(this.parentEntity.getAmbientSound(), 3.0F, (level.random.nextFloat() - level.random.nextFloat()) * 0.2F + 1.0F);
                } else if (this.attackTimer == 20) {
                    Vec3 look = this.parentEntity.getViewVector(1.0F);
                    double accelX = target.getX() - (this.parentEntity.getX() + look.x * 4.0);
                    double accelY = target.getY(0.5) - (0.5 + this.parentEntity.getY(0.5));
                    double accelZ = target.getZ() - (this.parentEntity.getZ() + look.z * 4.0);
                    this.parentEntity.playSound(GenesisSoundEvents.ENTITY_TEMPEST_SHOOT.get(), 3.0F, (level.random.nextFloat() - level.random.nextFloat()) * 0.2F + 1.0F);
                    TempestThunderBall thunderBall = new TempestThunderBall(level);
                    thunderBall.setPos(this.parentEntity.getX() + look.x * 4.0, this.parentEntity.getY(0.5) + 0.5, this.parentEntity.getZ() + look.z * 4.0);
                    thunderBall.shoot(accelX, accelY, accelZ, 1.0F, 1.0F);
                    level.addFreshEntity(thunderBall);
                    this.attackTimer = -40;
                }
            } else if (this.attackTimer > 0) {
                this.attackTimer--;
            }
            this.parentEntity.setAttackCharge(this.attackTimer);
        }
    }
}
