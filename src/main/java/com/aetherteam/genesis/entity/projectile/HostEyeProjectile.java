package com.aetherteam.genesis.entity.projectile;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

public class HostEyeProjectile extends Projectile {
    @Nullable
    private Entity finalTarget;
    @Nullable
    private Direction currentMoveDirection;
    private int flightSteps;
    private double targetDeltaX;
    private double targetDeltaY;
    private double targetDeltaZ;
    @Nullable
    private UUID targetId;

    public HostEyeProjectile(EntityType<? extends HostEyeProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noPhysics = true;
    }

    public HostEyeProjectile(Level level, LivingEntity shooter, Entity finalTarget, Direction.Axis axis) {
        this(GenesisEntityTypes.HOST_EYE.get(), level);
        this.setOwner(shooter);
        BlockPos pos = shooter.blockPosition();
        double d0 = pos.getX() + 0.5;
        double d1 = pos.getY() + 0.5;
        double d2 = pos.getZ() + 0.5;
        this.moveTo(d0, d1, d2, this.getYRot(), this.getXRot());
        this.finalTarget = finalTarget;
        this.currentMoveDirection = Direction.UP;
        this.selectNextMoveDirection(axis);
    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.finalTarget != null) {
            tag.putUUID("Target", this.finalTarget.getUUID());
        }

        if (this.currentMoveDirection != null) {
            tag.putInt("Dir", this.currentMoveDirection.get3DDataValue());
        }

        tag.putInt("Steps", this.flightSteps);
        tag.putDouble("TXD", this.targetDeltaX);
        tag.putDouble("TYD", this.targetDeltaY);
        tag.putDouble("TZD", this.targetDeltaZ);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.flightSteps = tag.getInt("Steps");
        this.targetDeltaX = tag.getDouble("TXD");
        this.targetDeltaY = tag.getDouble("TYD");
        this.targetDeltaZ = tag.getDouble("TZD");
        if (tag.contains("Dir", 99)) {
            this.currentMoveDirection = Direction.from3DDataValue(tag.getInt("Dir"));
        }

        if (tag.hasUUID("Target")) {
            this.targetId = tag.getUUID("Target");
        }
    }

    @Override
    protected void defineSynchedData() {
    }

    private void setMoveDirection(@Nullable Direction pDirection) {
        this.currentMoveDirection = pDirection;
    }

    private void selectNextMoveDirection(@Nullable Direction.Axis axis) {
        double d0 = 0.5;
        BlockPos posBelow;
        if (this.finalTarget == null) {
            posBelow = this.blockPosition().below();
        } else {
            d0 = (double)this.finalTarget.getBbHeight() * 0.5;
            posBelow = BlockPos.containing(this.finalTarget.getX(), this.finalTarget.getY() + d0, this.finalTarget.getZ());
        }

        double d1 = (double)posBelow.getX() + 0.5;
        double d2 = (double)posBelow.getY() + d0;
        double d3 = (double)posBelow.getZ() + 0.5;
        Direction direction = null;
        if (!posBelow.closerToCenterThan(this.position(), 2.0)) {
            BlockPos pos = this.blockPosition();
            List<Direction> list = Lists.newArrayList();
            if (axis != Direction.Axis.X) {
                if (pos.getX() < posBelow.getX() && this.level().isEmptyBlock(pos.east())) {
                    list.add(Direction.EAST);
                } else if (pos.getX() > posBelow.getX() && this.level().isEmptyBlock(pos.west())) {
                    list.add(Direction.WEST);
                }
            }

            if (axis != Direction.Axis.Y) {
                if (pos.getY() < posBelow.getY() && this.level().isEmptyBlock(pos.above())) {
                    list.add(Direction.UP);
                } else if (pos.getY() > posBelow.getY() && this.level().isEmptyBlock(pos.below())) {
                    list.add(Direction.DOWN);
                }
            }

            if (axis != Direction.Axis.Z) {
                if (pos.getZ() < posBelow.getZ() && this.level().isEmptyBlock(pos.south())) {
                    list.add(Direction.SOUTH);
                } else if (pos.getZ() > posBelow.getZ() && this.level().isEmptyBlock(pos.north())) {
                    list.add(Direction.NORTH);
                }
            }

            direction = Direction.getRandom(this.random);
            if (list.isEmpty()) {
                for(int i = 5; !this.level().isEmptyBlock(pos.relative(direction)) && i > 0; --i) {
                    direction = Direction.getRandom(this.random);
                }
            } else {
                direction = list.get(this.random.nextInt(list.size()));
            }

            d1 = this.getX() + (double)direction.getStepX();
            d2 = this.getY() + (double)direction.getStepY();
            d3 = this.getZ() + (double)direction.getStepZ();
        }

        this.setMoveDirection(direction);
        double d6 = d1 - this.getX();
        double d7 = d2 - this.getY();
        double d4 = d3 - this.getZ();
        double d5 = Math.sqrt(d6 * d6 + d7 * d7 + d4 * d4);
        if (d5 == 0.0) {
            this.targetDeltaX = 0.0;
            this.targetDeltaY = 0.0;
            this.targetDeltaZ = 0.0;
        } else {
            this.targetDeltaX = d6 / d5 * 0.15;
            this.targetDeltaY = d7 / d5 * 0.15;
            this.targetDeltaZ = d4 / d5 * 0.15;
        }

        this.hasImpulse = true;
        this.flightSteps = 10 + this.random.nextInt(5) * 10;
    }

    /**
     * Makes the entity despawn if requirements are reached
     */
    @Override
    public void checkDespawn() {
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            this.discard();
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if (this.finalTarget == null && this.targetId != null) {
                this.finalTarget = ((ServerLevel)this.level()).getEntity(this.targetId);
                if (this.finalTarget == null) {
                    this.targetId = null;
                }
            }

            if (this.finalTarget == null || !this.finalTarget.isAlive() || this.finalTarget instanceof Player && this.finalTarget.isSpectator()) {
                if (!this.isNoGravity()) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
                }
            } else {
                this.targetDeltaX = Mth.clamp(this.targetDeltaX * 1.025, -1.0, 1.0);
                this.targetDeltaY = Mth.clamp(this.targetDeltaY * 1.025, -1.0, 1.0);
                this.targetDeltaZ = Mth.clamp(this.targetDeltaZ * 1.025, -1.0, 1.0);
                Vec3 vec3 = this.getDeltaMovement();
                this.setDeltaMovement(vec3.add((this.targetDeltaX - vec3.x) * 0.2, (this.targetDeltaY - vec3.y) * 0.2, (this.targetDeltaZ - vec3.z) * 0.2));
            }

            HitResult result = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (result.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, result)) {
                this.onHit(result);
            }
        }

        this.checkInsideBlocks();
        Vec3 vec3 = this.getDeltaMovement();
        this.setPos(this.getX() + vec3.x, this.getY() + vec3.y, this.getZ() + vec3.z);
        ProjectileUtil.rotateTowardsMovement(this, 0.5F);

        if (this.finalTarget != null && !this.finalTarget.isRemoved()) {
            if (this.flightSteps > 0) {
                --this.flightSteps;
                if (this.flightSteps == 0) {
                    this.selectNextMoveDirection(this.currentMoveDirection == null ? null : this.currentMoveDirection.getAxis());
                }
            }

            if (this.currentMoveDirection != null) {
                BlockPos pos = this.blockPosition();
                Direction.Axis axis = this.currentMoveDirection.getAxis();
                if (this.level().loadedAndEntityCanStandOn(pos.relative(this.currentMoveDirection), this)) {
                    this.selectNextMoveDirection(axis);
                } else {
                    BlockPos posFinal = this.finalTarget.blockPosition();
                    if (axis == Direction.Axis.X && pos.getX() == posFinal.getX()
                            || axis == Direction.Axis.Z && pos.getZ() == posFinal.getZ()
                            || axis == Direction.Axis.Y && pos.getY() == posFinal.getY()) {
                        this.selectNextMoveDirection(axis);
                    }
                }
            }
        }
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return super.canHitEntity(entity) && !entity.noPhysics;
    }

    /**
     * Returns {@code true} if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    @Override
    public boolean isOnFire() {
        return false;
    }

    /**
     * Checks if the entity is in range to render.
     */
    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return distance < 16384.0;
    }

    @Override
    public float getLightLevelDependentMagicValue() {
        return 1.0F;
    }

    protected void dealDamage(LivingEntity living) {
        if (this.isAlive()) {
            if (living.hurt(this.damageSources().mobProjectile(this, living), 4)) {
                this.playSound(AetherSoundEvents.ENTITY_SLIDER_COLLIDE.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.doEnchantDamageEffects(Objects.requireNonNull(this.getControllingPassenger()), living);
            }
        }
    }

    public void playerTouch(Player pEntity) {
        this.dealDamage(pEntity);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.destroy();
    }

    private void destroy() {
        this.discard();
        this.level().gameEvent(GameEvent.ENTITY_DAMAGE, this.position(), GameEvent.Context.of(this));
    }

    /**
     * Returns {@code true} if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        double d0 = packet.getXa();
        double d1 = packet.getYa();
        double d2 = packet.getZa();
        this.setDeltaMovement(d0, d1, d2);
    }
}