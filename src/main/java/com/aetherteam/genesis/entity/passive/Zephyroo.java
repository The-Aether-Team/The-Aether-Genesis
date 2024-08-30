package com.aetherteam.genesis.entity.passive;

import com.aetherteam.aether.entity.ai.goal.EatAetherGrassGoal;
import com.aetherteam.aether.entity.passive.AetherAnimal;
import com.aetherteam.genesis.GenesisTags;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class Zephyroo extends AetherAnimal {
    private EatAetherGrassGoal eatBlockGoal;
    private int eatAnimationTick = 0;
    private int jumpTicks;
    private int jumpDuration;
    private int jumpDelayTicks;
    private boolean wasOnGround;

    public Zephyroo(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.jumpControl = new ZephyrooJumpControl(this);
        this.moveControl = new ZephyrooMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        this.eatBlockGoal = new EatAetherGrassGoal(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ZephyrooPanicGoal(this, 1.25));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1, Ingredient.of(GenesisTags.Items.ZEPHYROO_TEMPTATION_ITEMS), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(5, this.eatBlockGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createZephyrooAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 40).add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 1) {
            this.spawnSprintParticle();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        } else if (id == 10) {
            this.eatAnimationTick = 40;
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    public void aiStep() {
        if (this.level().isClientSide()) {
            this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        }
        super.aiStep();
        if (this.jumpTicks != this.jumpDuration) {
            ++this.jumpTicks;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
    }

    @Override
    public void customServerAiStep() {
        this.eatAnimationTick = this.eatBlockGoal.getEatAnimationTick();

        if (this.jumpDelayTicks > 0) {
            --this.jumpDelayTicks;
        }
        if (this.onGround()) {
            if (!this.wasOnGround) {
                this.setJumping(false);
                this.checkLandingDelay();
            }
            ZephyrooJumpControl jumpControl = (ZephyrooJumpControl) this.jumpControl;
            if (!jumpControl.wantJump()) {
                if (this.moveControl.hasWanted() && this.jumpDelayTicks == 0) {
                    Path path = this.navigation.getPath();
                    Vec3 vec3 = new Vec3(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
                    if (path != null && !path.isDone()) {
                        vec3 = path.getNextEntityPos(this);
                    }
                    this.facePoint(vec3.x, vec3.z);
                    this.startJumping();
                }
            } else if (!jumpControl.canJump()) {
                this.enableJumpControl();
            }
        }
        this.wasOnGround = this.onGround();
    }

    private void facePoint(double x, double z) {
        this.setYRot((float) (Mth.atan2(z - this.getZ(), x - this.getX()) * 180.0 / Mth.PI) - 90.0F);
    }

    @Override
    protected void jumpFromGround() {
        super.jumpFromGround();
        double speed = this.moveControl.getSpeedModifier();
        if (speed > 0.0) {
            double horizontalDistance = this.getDeltaMovement().horizontalDistanceSqr();
            if (horizontalDistance < 0.01) {
                this.moveRelative(0.25F, new Vec3(0.0, 0.0, 1.0));
            }
        }
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte) 1);
        }
    }

    @Override
    protected float getJumpPower() {
        float basePower = 0.3F;
        if (this.horizontalCollision || this.moveControl.hasWanted() && this.moveControl.getWantedY() > this.getY() + 0.5) {
            basePower = 0.5F;
        }
        Path path = this.navigation.getPath();
        if (path != null && !path.isDone()) {
            Vec3 vec3 = path.getNextEntityPos(this);
            if (vec3.y > this.getY() + 0.5) {
                basePower = 0.5F;
            }
        }
        if (this.moveControl.getSpeedModifier() <= 0.6) {
            basePower = 0.3F;
        }

        return (basePower * 1.5F) + this.getJumpBoostPower();
    }

    @Override
    public void setJumping(boolean jumping) {
        super.setJumping(jumping);
        if (jumping) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }
    }

    public void startJumping() {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    public float getJumpCompletion(float partialTick) {
        return this.jumpDuration == 0 ? 0.0F : ((float) this.jumpTicks + partialTick) / (float) this.jumpDuration;
    }

    private void setLandingDelay() {
        if (this.moveControl.getSpeedModifier() < 2.2) {
            this.jumpDelayTicks = 10;
        } else {
            this.jumpDelayTicks = 1;
        }
    }

    private void checkLandingDelay() {
        this.setLandingDelay();
        this.disableJumpControl();
    }

    private void enableJumpControl() {
        ((ZephyrooJumpControl) this.jumpControl).setCanJump(true);
    }

    private void disableJumpControl() {
        ((ZephyrooJumpControl) this.jumpControl).setCanJump(false);
    }

    public void setSpeedModifier(double speedModifier) {
        this.getNavigation().setSpeedModifier(speedModifier);
        this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), speedModifier);
    }

    public float getHeadEatAngleScale(float partialTick) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float f = ((float) (this.eatAnimationTick - 4) - partialTick) / 32.0F;
            return (float) (Math.PI / 5) + 0.21991149F * Mth.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? (float) (Math.PI / 5) : this.getXRot() * (float) (Math.PI / 180.0);
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return this.isBaby() ? size.height * 0.8F : 1.1F;
    }

    @Override
    public int getMaxFallDistance() {
        return 12;
    }

    @Override
    public boolean canSpawnSprintParticle() {
        return false;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(GenesisTags.Items.ZEPHYROO_TEMPTATION_ITEMS);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return GenesisEntityTypes.ZEPHYROO.get().create(level);
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.RABBIT_JUMP;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return GenesisSoundEvents.ENTITY_ZEPHYROO_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return GenesisSoundEvents.ENTITY_ZEPHYROO_DEATH.get();
    }

    public static class ZephyrooJumpControl extends JumpControl {
        private final Zephyroo zephyroo;
        private boolean canJump;

        public ZephyrooJumpControl(Zephyroo zephyroo) {
            super(zephyroo);
            this.zephyroo = zephyroo;
        }

        public boolean wantJump() {
            return this.jump;
        }

        public boolean canJump() {
            return this.canJump;
        }

        public void setCanJump(boolean canJump) {
            this.canJump = canJump;
        }

        public void tick() {
            if (this.jump) {
                this.zephyroo.startJumping();
                this.jump = false;
            }
        }
    }

    public static class ZephyrooMoveControl extends MoveControl {
        private final Zephyroo zephyroo;
        private double nextJumpSpeed;

        public ZephyrooMoveControl(Zephyroo zephyroo) {
            super(zephyroo);
            this.zephyroo = zephyroo;
        }

        public void tick() {
            if (this.zephyroo.onGround() && !this.zephyroo.jumping && !((ZephyrooJumpControl) this.zephyroo.jumpControl).wantJump()) {
                this.zephyroo.setSpeedModifier(0.0);
            } else if (this.hasWanted()) {
                this.zephyroo.setSpeedModifier(this.nextJumpSpeed);
            }

            super.tick();
        }

        public void setWantedPosition(double x, double y, double z, double speed) {
            if (this.zephyroo.isInWater()) {
                speed = 1.5;
            }

            super.setWantedPosition(x, y, z, speed);
            if (speed > 0.0) {
                this.nextJumpSpeed = speed;
            }
        }
    }

    static class ZephyrooPanicGoal extends PanicGoal {
        private final Zephyroo zephyroo;

        public ZephyrooPanicGoal(Zephyroo zephyroo, double speedModifier) {
            super(zephyroo, speedModifier);
            this.zephyroo = zephyroo;
        }

        public void tick() {
            super.tick();
            this.zephyroo.setSpeedModifier(this.speedModifier);
        }
    }
}
