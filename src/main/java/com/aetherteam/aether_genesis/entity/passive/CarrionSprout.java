package com.aetherteam.aether_genesis.entity.passive;

import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class CarrionSprout extends Mob {
    public static final EntityDataAccessor<Float> DATA_MAX_SIZE_ID = SynchedEntityData.defineId(CarrionSprout.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> DATA_SIZE_ID = SynchedEntityData.defineId(CarrionSprout.class, EntityDataSerializers.FLOAT);

    public float sinage;
    public float sinageAdd;

    public CarrionSprout(EntityType<? extends CarrionSprout> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
    }

    @Nonnull
    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MAX_SIZE_ID, 0.0F);
        this.entityData.define(DATA_SIZE_ID, 0.0F);
    }

    @Override
    public void onSyncedDataUpdated(@Nonnull EntityDataAccessor<?> dataAccessor) {
        if (DATA_SIZE_ID.equals(dataAccessor)) {
            this.setBoundingBox(this.makeBoundingBox());
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(dataAccessor);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor level, @Nonnull DifficultyInstance difficulty, @Nonnull MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        this.setPos(Math.floor(this.getX()) + 0.5, this.getY(), Math.floor(this.getZ()) + 0.5);
        this.setMaxSize(Mth.clamp(this.getRandom().nextFloat() * this.getRandom().nextInt(3), 1.0F, 3.0F));
        this.setSize(Mth.clamp(this.getRandom().nextFloat() * this.getRandom().nextInt(2) - 0.3F, Mth.clamp(this.getMaxSize() - this.getRandom().nextFloat(), 0.3F, 0.6F), 0.3F));
        this.sinage = this.random.nextFloat() * 6.0F;
        return super.finalizeSpawn(level, difficulty, reason, spawnData, tag);
    }

    public static boolean checkCarrionSproutSpawnRules(EntityType<? extends CarrionSprout> carrionSprout, LevelAccessor level, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(GenesisTags.Blocks.CARRION_SPROUT_SPAWNABLE_ON)
                && level.getRawBrightness(pos, 0) > 8
                && (spawnReason != MobSpawnType.NATURAL || random.nextInt(10) == 0);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getLevel().getBlockState(this.blockPosition().below()).is(GenesisTags.Blocks.CARRION_SPROUT_SPAWNABLE_ON)) {
            this.kill();
        }
        if (!this.getLevel().isClientSide()) {
            if (this.getSize() < this.getMaxSize()) {
                this.setSize(this.getSize() + 0.0001F);
            }
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.sinage += this.sinageAdd;
        if (this.hurtTime > 0) {
            this.sinageAdd = 0.9F;
        } else {
            this.sinageAdd = 0.15F;
        }
        if (this.sinage >= Mth.TWO_PI) {
            this.sinage -= Mth.TWO_PI;
        }
    }

    @Override
    protected void jumpFromGround() { }

    @Override
    protected void doPush(@Nonnull Entity entity) {
        if (!this.isPassengerOfSameVehicle(entity)) {
            if (!entity.noPhysics && !this.noPhysics) {
                double d0 = entity.getX() - this.getX();
                double d1 = entity.getZ() - this.getZ();
                double d2 = Mth.absMax(d0, d1);
                if (d2 >= (double) 0.01F) {
                    d2 = Math.sqrt(d2);
                    d0 /= d2;
                    d1 /= d2;
                    double d3 = 1.0 / d2;
                    if (d3 > 1.0) {
                        d3 = 1.0;
                    }

                    d0 *= d3;
                    d1 *= d3;
                    d0 *= 0.05F;
                    d1 *= 0.05F;

                    if (!entity.isVehicle()) {
                        entity.push(d0, 0.0, d1);
                    }
                }
            }
        }
    }

    public float getMaxSize() {
        return this.entityData.get(DATA_MAX_SIZE_ID);
    }

    public void setMaxSize(float maxSize) {
        this.entityData.set(DATA_MAX_SIZE_ID, maxSize);
    }

    public float getSize() {
        return this.entityData.get(DATA_SIZE_ID);
    }

    public void setSize(float size) {
        this.entityData.set(DATA_SIZE_ID, size);
    }

    @Nonnull
    @Override
    protected AABB makeBoundingBox() {
        return this.createDimensions().makeBoundingBox(this.position());
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return this.createDimensions();
    }

    private EntityDimensions createDimensions() {
        float width = this.getSize();
        float height = this.getSize() + (this.getSize() * 0.4F);
        return EntityDimensions.fixed(width, height);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return GenesisSoundEvents.ENTITY_CARRION_SPROUT_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return GenesisSoundEvents.ENTITY_CARRION_SPROUT_DEATH.get();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public int getExperienceReward() {
        return 1 + this.getLevel().getRandom().nextInt(3);
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("Size", this.getSize());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Size")) {
            this.setSize(tag.getFloat("Size"));
        }
    }
}


