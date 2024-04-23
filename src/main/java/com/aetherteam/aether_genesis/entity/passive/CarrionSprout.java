package com.aetherteam.aether_genesis.entity.passive;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.entity.monster.AechorPlant;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;

public class CarrionSprout extends Mob {
    public static final EntityDataAccessor<Float> DATA_MAX_SIZE_ID = SynchedEntityData.defineId(CarrionSprout.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> DATA_SIZE_ID = SynchedEntityData.defineId(CarrionSprout.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<String> DATA_COLOR_ID = SynchedEntityData.defineId(CarrionSprout.class, EntityDataSerializers.STRING);

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

    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_MAX_SIZE_ID, 0.0F);
        this.getEntityData().define(DATA_SIZE_ID, 0.0F);
        this.getEntityData().define(DATA_COLOR_ID, "blue");
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> dataAccessor) {
        if (DATA_SIZE_ID.equals(dataAccessor)) {
            this.setBoundingBox(this.makeBoundingBox());
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(dataAccessor);
    }

    /**
     * Centers the mob to a block on spawn, and determines the current size and the max size it will be able to grow to.<br><br>
     * Warning for "deprecation" is suppressed because this is fine to override.
     *
     * @param level      The {@link ServerLevelAccessor} where the entity is spawned.
     * @param difficulty The {@link DifficultyInstance} of the game.
     * @param reason     The {@link MobSpawnType} reason.
     * @param spawnData  The {@link SpawnGroupData}.
     * @param tag        The {@link CompoundTag} to apply to this entity.
     * @return The {@link SpawnGroupData} to return.
     */
    @Override
    @SuppressWarnings("deprecation")
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        this.setPos(Math.floor(this.getX()) + 0.5, this.getY(), Math.floor(this.getZ()) + 0.5);
        this.setMaxSize(Mth.clamp(this.getRandom().nextFloat() * this.getRandom().nextInt(3), 1.0F, 3.0F));
        this.setSize(Mth.clamp(this.getRandom().nextFloat() * this.getRandom().nextInt(2) - 0.3F, Mth.clamp(this.getMaxSize() - this.getRandom().nextFloat(), 0.3F, 0.6F), 0.3F));
        this.sinage = this.getRandom().nextFloat() * 6.0F;
        return spawnData;
    }

    /**
     * Carrion Sprouts can spawn if the block at the spawn location is in the {@link GenesisTags.Blocks#CARRION_SPROUT_SPAWNABLE_ON} tag, if they are spawning at a light level above 8,
     * if the difficulty isn't peaceful, and they spawn with a random chance of 1/10.
     *
     * @param carrionSprout The {@link AechorPlant} {@link EntityType}.
     * @param level       The {@link LevelAccessor}.
     * @param reason      The {@link MobSpawnType} reason.
     * @param pos         The spawn {@link BlockPos}.
     * @param random      The {@link RandomSource}.
     * @return Whether this entity can spawn, as a {@link Boolean}.
     */
    public static boolean checkCarrionSproutSpawnRules(EntityType<? extends CarrionSprout> carrionSprout, LevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(GenesisTags.Blocks.CARRION_SPROUT_SPAWNABLE_ON)
                && level.getRawBrightness(pos, 0) > 8
                && (reason != MobSpawnType.NATURAL || random.nextInt(10) == 0);
    }

    /**
     * Kills the Carrion Sprout if it is not on a valid block or on a vehicle, and also handles size growth.
     */
    @Override
    public void tick() {
        super.tick();
        if (!this.level().getBlockState(this.blockPosition().below()).is(AetherTags.Blocks.AECHOR_PLANT_SPAWNABLE_ON) && !this.isPassenger()) {
            this.kill();
        }
        if (!this.level().isClientSide()) {
            if (this.getSize() < this.getMaxSize()) {
                this.setSize(this.getSize() + 0.0001F);
            }
        }
    }

    /**
     * Handles the petal animation.
     */
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

    /**
     * Easter egg behavior for Carrion Sprouts.
     *
     * @param player The interacting {@link Player}.
     * @param hand   The {@link InteractionHand}.
     * @return The {@link InteractionResult}.
     */
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.PINK_DYE) && !this.getColor().equals("pink")) {
            this.setColor("pink");
            return InteractionResult.SUCCESS;
        } else if (itemStack.is(Items.WHITE_DYE) && !this.getColor().equals("white")) {
            this.setColor("white");
            return InteractionResult.SUCCESS;
        } else if (itemStack.is(Items.LIGHT_BLUE_DYE) && !this.getColor().equals("blue")) {
            this.setColor("blue");
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    /**
     * Disallows Carrion Sprout from being pushed.
     *
     * @param x The {@link Double} for x-motion.
     * @param y The {@link Double} for y-motion.
     * @param z The {@link Double} for z-motion.
     */
    @Override
    public void push(double x, double y, double z) { }

    /**
     * Disallows Carrion Sprout from jumping.
     */
    @Override
    protected void jumpFromGround() { }

    /**
     * Disallows Aechor Plants from being leashed.
     */
    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    /**
     * @return The {@link Integer} for the max possible size of the Carrion Sprout.
     */
    public float getMaxSize() {
        return this.getEntityData().get(DATA_MAX_SIZE_ID);
    }

    /**
     * Sets the max possible size of the Carrion Sprout.
     *
     * @param maxSize The {@link Integer} value.
     */
    public void setMaxSize(float maxSize) {
        this.getEntityData().set(DATA_MAX_SIZE_ID, maxSize);
    }

    /**
     * @return The {@link Integer} for the size of the Carrion Sprout.
     */
    public float getSize() {
        return this.getEntityData().get(DATA_SIZE_ID);
    }

    /**
     * Sets the size of the Carrion Sprout.
     *
     * @param size The {@link Integer} value.
     */
    public void setSize(float size) {
        this.getEntityData().set(DATA_SIZE_ID, size);
    }

    /**
     * @return The {@link String} for the name of the Carrion Sprout's color.
     */
    public String getColor() {
        return this.getEntityData().get(DATA_COLOR_ID);
    }

    /**
     * Sets the color of the Carrion Sprout.
     *
     * @param color The {@link String} color name.
     */
    public void setColor(String color) {
        this.getEntityData().set(DATA_COLOR_ID, color);
    }

    /**
     * @return The adjusted bounding box of the Carrion Sprout according to size.
     */
    @Override
    protected AABB makeBoundingBox() {
        return this.createDimensions().makeBoundingBox(this.position());
    }

    /**
     * Handles the hitbox for the randomized sizing of Carrion Sprouts.
     *
     * @param pose The {@link Pose} to get dimensions for.
     * @return The {@link EntityDimensions}.
     */
    @Override
    public EntityDimensions getDimensions(Pose pose) {
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

    /**
     * [CODE COPY] - {@link Animal#getExperienceReward()}.
     */
    @Override
    public int getExperienceReward() {
        return 1 + this.level().getRandom().nextInt(3);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("Size", this.getSize());
        tag.putString("Color", this.getColor());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Size")) {
            this.setSize(tag.getFloat("Size"));
        }
        if (tag.contains("Color")) {
            this.setColor(tag.getString("Color"));
        }
    }
}


