package com.aetherteam.genesis.entity.monster.dungeon;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.client.AetherSoundEvents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class BattleSentry extends Slime {
    public static final EntityDataAccessor<Boolean> DATA_AWAKE_ID = SynchedEntityData.defineId(BattleSentry.class, EntityDataSerializers.BOOLEAN);

    public float timeSpotted = 0.0F;

    public BattleSentry(EntityType<? extends BattleSentry> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new BattleSentry.FloatGoal(this));
        this.goalSelector.addGoal(2, new BattleSentry.AttackGoal(this));
        this.goalSelector.addGoal(3, new BattleSentry.RandomDirectionGoal(this));
        this.goalSelector.addGoal(5, new BattleSentry.KeepOnJumpingGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (entity) -> Math.abs(entity.getY() - this.getY()) <= 4.0));
    }

    
    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.8)
                .add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_AWAKE_ID, false);
    }

    @Override
    public void setSize(int size, boolean resetHealth) {}

    @Override
    public void remove( Entity.RemovalReason reason) {
        this.setRemoved(reason);
        if (reason == Entity.RemovalReason.KILLED) {
            this.gameEvent(GameEvent.ENTITY_DIE);
        }
    }

    public boolean isAwake() {
        return this.entityData.get(DATA_AWAKE_ID);
    }

    public void setAwake(boolean awake) {
        this.entityData.set(DATA_AWAKE_ID, awake);
    }

    protected float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public void tick() {
        if (this.level().getNearestPlayer(this.getX(), this.getY(), this.getZ(), 8.0, EntitySelector.NO_SPECTATORS) != null) {
            if (!this.isAwake()) {
                if (this.timeSpotted >= 24) {
                    this.setAwake(true);
                }
                this.timeSpotted++;
            }
        } else {
            this.setAwake(false);
        }
        super.tick();
    }

    @Override
    public @Nullable
    SpawnGroupData finalizeSpawn( ServerLevelAccessor level,  DifficultyInstance difficulty,  MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        this.setSize(1, true);
        this.setLeftHanded(false);
        return spawnData;
    }

    
    @Override
    protected ResourceLocation getDefaultLootTable() {
        return this.getType().getDefaultLootTable();
    }

    @Override
    protected SoundEvent getHurtSound( DamageSource damageSource) {
        return AetherSoundEvents.ENTITY_SENTRY_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AetherSoundEvents.ENTITY_SENTRY_DEATH.get();
    }

    protected boolean isDealsDamage() {
        return this.isEffectiveAi();
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
    public EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(2*0.879F);
    }

    
    @Override
    protected ParticleOptions getParticleType() {
        return new BlockParticleOption(ParticleTypes.BLOCK, AetherBlocks.SENTRY_STONE.get().defaultBlockState());
    }

    @SuppressWarnings("unchecked")
    
    @Override
    public EntityType<? extends BattleSentry> getType() {
        return (EntityType<? extends BattleSentry>) super.getType();
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    protected void jumpFromGround() {
        if(isAwake()) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x, 0.1, vec3.z);
            this.hasImpulse = true;
        }
    }

    public static class AttackGoal extends SlimeAttackGoal {
        private final BattleSentry sentry;

        public AttackGoal(BattleSentry sentryIn) {
            super(sentryIn);
            this.sentry = sentryIn;
        }

        @Override
        public boolean canUse() {
            return this.sentry.isAwake() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.sentry.isAwake() && super.canContinueToUse();
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
            return this.sentry.isAwake() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.sentry.isAwake() && super.canContinueToUse();
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
            return this.sentry.isAwake() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.sentry.isAwake() && super.canContinueToUse();
        }
    }

    public static class RandomDirectionGoal extends SlimeRandomDirectionGoal {
        private final BattleSentry sentry;

        public RandomDirectionGoal(BattleSentry sentryIn) {
            super(sentryIn);
            this.sentry = sentryIn;
        }

        @Override
        public boolean canUse() {
            return this.sentry.isAwake() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.sentry.isAwake() && super.canContinueToUse();
        }
    }
}
