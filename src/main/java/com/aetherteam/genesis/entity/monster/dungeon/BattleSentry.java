package com.aetherteam.genesis.entity.monster.dungeon;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.client.AetherSoundEvents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BattleSentry extends Slime {
    public static final EntityDataAccessor<Boolean> DATA_AWAKE_ID = SynchedEntityData.defineId(BattleSentry.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> DATA_STALKING_ID = SynchedEntityData.defineId(BattleSentry.class, EntityDataSerializers.BOOLEAN);

    private int ticksUntilNextAttack;

    public BattleSentry(EntityType<? extends BattleSentry> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new BattleSentry.FloatGoal(this));
        this.goalSelector.addGoal(2, new BattleSentry.AttackGoal(this));
        this.goalSelector.addGoal(5, new BattleSentry.KeepOnJumpingGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (entity) -> Math.abs(entity.getY() - this.getY()) <= 4.0));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 1.5)
                .add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_AWAKE_ID, false);
        this.entityData.define(DATA_STALKING_ID, true);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            if (this.getTarget() != null) {
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
                if (this.getTarget() instanceof Player player) {
                    if (this.isStalking()) {
                        if (!this.isLookingAtMe(player)) {
                            if (!this.isAwake()) {
                                this.setAwake(true);
                            }
                        } else {
                            if (this.isAwake()) {
                                this.setAwake(false);
                            }
                        }
                    } else {
                        this.setAwake(true);
                    }
                } else {
                    this.setAwake(true);
                }
            } else {
                if (this.isAwake()) {
                    this.setAwake(false);
                }
                if (!this.isStalking()) {
                    this.setStalking(true);
                }
            }
        }
    }

    protected void jumpFromGround() {
        if (this.canAttack(true)) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x * 1.25, 0.25, vec3.z * 1.25);
            this.hasImpulse = true;
        }
    }

    public void push(Entity entity) {
        super.push(entity);
        if (entity instanceof LivingEntity livingEntity) {
            if (!(entity instanceof BattleSentry)) {
                this.checkAndPerformAttack(livingEntity);
            }
        }
    }

    public void playerTouch(Player player) {
        if (EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(player)) {
            this.checkAndPerformAttack(player);
        }
    }

    protected void checkAndPerformAttack(LivingEntity target) {
        if (this.ticksUntilNextAttack <= 0 && this.isWithinMeleeAttackRange(target) && this.getSensing().hasLineOfSight(target)) {
            this.ticksUntilNextAttack = 20;
            this.doHurtTarget(target);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (super.hurt(source, amount)) {
            if (this.isStalking()) {
                this.setStalking(false);
            }
            return true;
        }
        return false;
    }

    @Override
    public void setSize(int size, boolean resetHealth) { }

    private boolean canAttack(boolean canUse) {
        if (this.isAwake()) {
            if (this.isStalking()) {
                return canUse && (!(this.getTarget() instanceof Player player) || !this.isLookingAtMe(player));
            }
            return canUse;
        }
        return false;
    }

    private boolean isLookingAtMe(Player player) {
        Vec3 vec3 = player.getViewVector(1.0F).normalize();
        Vec3 vec31 = new Vec3(this.getX() - player.getX(), this.getEyeY() - player.getEyeY(), this.getZ() - player.getZ());
        vec31 = vec31.normalize();
        double d1 = vec3.dot(vec31);
        return d1 > 0.4 && player.hasLineOfSight(this);
    }

    public boolean isAwake() {
        return this.entityData.get(DATA_AWAKE_ID);
    }

    public void setAwake(boolean awake) {
        this.entityData.set(DATA_AWAKE_ID, awake);
    }

    public boolean isStalking() {
        return this.entityData.get(DATA_STALKING_ID);
    }

    public void setStalking(boolean stalking) {
        this.entityData.set(DATA_STALKING_ID, stalking);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return AetherSoundEvents.ENTITY_SENTRY_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AetherSoundEvents.ENTITY_SENTRY_DEATH.get();
    }
    
    @Override
    protected SoundEvent getSquishSound() {
        return AetherSoundEvents.ENTITY_SENTRY_JUMP.get();
    }

    @Override
    protected SoundEvent getJumpSound() {
        return AetherSoundEvents.ENTITY_SENTRY_JUMP.get();
    }

    @Override
    protected boolean isDealsDamage() {
        return this.isEffectiveAi();
    }
    
    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(1.76F);
    }
    
    @Override
    protected ParticleOptions getParticleType() {
        return new BlockParticleOption(ParticleTypes.BLOCK, AetherBlocks.CARVED_STONE.get().defaultBlockState());
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    public static class AttackGoal extends SlimeAttackGoal {
        private final BattleSentry sentry;

        public AttackGoal(BattleSentry sentryIn) {
            super(sentryIn);
            this.sentry = sentryIn;
        }

        @Override
        public boolean canUse() {
            return this.sentry.canAttack(super.canUse());
        }

        @Override
        public boolean canContinueToUse() {
            return this.sentry.canAttack(super.canContinueToUse());
        }
    }

    public static class FloatGoal extends SlimeFloatGoal {
        private final BattleSentry sentry;

        public FloatGoal(BattleSentry sentryIn) {
            super(sentryIn);
            this.sentry = sentryIn;
        }

        @Override
        public boolean canUse() {
            return this.sentry.canAttack(super.canUse());
        }

        @Override
        public boolean canContinueToUse() {
            return this.sentry.canAttack(super.canContinueToUse());
        }
    }

    public static class KeepOnJumpingGoal extends SlimeKeepOnJumpingGoal {
        private final BattleSentry sentry;

        public KeepOnJumpingGoal(BattleSentry sentryIn) {
            super(sentryIn);
            this.sentry = sentryIn;
        }

        @Override
        public boolean canUse() {
            return this.sentry.canAttack(super.canUse());
        }

        @Override
        public boolean canContinueToUse() {
            return this.sentry.canAttack(super.canContinueToUse());
        }
    }
}
