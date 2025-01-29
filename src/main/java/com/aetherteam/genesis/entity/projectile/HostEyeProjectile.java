package com.aetherteam.genesis.entity.projectile;

import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.monster.dungeon.boss.SliderHostMimic;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;
import java.util.List;

public class HostEyeProjectile extends Projectile {
    private SliderHostMimic projectileOwner;
    private Vec3 targetPoint;
    private float velocity;
    private Direction moveDirection = null;
    private int moveDelay = this.calculateMoveDelay();
    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private Vec3 targetDeltaMovement = Vec3.ZERO;

    public HostEyeProjectile(EntityType<? extends HostEyeProjectile> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public HostEyeProjectile(Level level, SliderHostMimic owner, Direction moveDirection) {
        this(GenesisEntityTypes.HOST_EYE.get(), level);
        this.projectileOwner = owner;
        this.moveDirection = moveDirection;
        this.setOwner(owner);
    }

    @Override
    protected void defineSynchedData() { }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            if (this.lerpSteps > 0) {
                this.lerpPositionAndRotationStep(this.lerpSteps, this.lerpX, this.lerpY, this.lerpZ, 0.0, 0.0);
                --this.lerpSteps;
            } else {
                this.reapplyPosition();
            }
        } else {
            if (this.moveDelay <= 0) {
                this.targetPoint = this.findTargetPoint();
                if (this.targetPoint != null) {
                    Direction moveDir = this.getMoveDirection(this.targetPoint);
                    if (!(this.axisDistance(this.targetPoint, this.position(), moveDir) <= 0.0)) {
                        if (this.velocity < this.getMaxVelocity()) {
                            this.velocity = Math.min(this.getMaxVelocity(), this.velocity + this.getVelocityIncrease());
                        }
                        this.setDeltaMovement((float) moveDir.getStepX() * this.velocity, (float) moveDir.getStepY() * this.velocity, (float) moveDir.getStepZ() * this.velocity);
                        this.hasImpulse = true;
                    } else {
                        this.stop();
                    }
                }
            } else {
                --this.moveDelay;
            }
            if (this.moveDirection == null) {
                this.setDeltaMovement(this.getDeltaMovement().multiply(0, 0, 0));
            }

            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult)) {
                this.onHit(hitresult);
            }

            List<Entity> entities = this.level().getEntities(this, this.getBoundingBox());
            for (Entity target : entities) {
                Mob owner = this.projectileOwner;
                if (target instanceof LivingEntity living && this.canHitEntity(living) && living.hurt(this.damageSources().mobAttack(owner), 4)) {
                    this.playSound(GenesisSoundEvents.ENTITY_HOST_EYE_COLLIDE.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                    living.knockback(1.0, this.getX() - living.getX(), this.getZ() - living.getZ());
                }
            }

            this.checkInsideBlocks();
            this.move(MoverType.SELF, this.getDeltaMovement());
        }
    }

    @Nullable
    public Vec3 findTargetPoint() {
        Vec3 pos = this.targetPoint;
        if (pos != null) {
            return pos;
        } else {
            if (this.projectileOwner != null) {
                LivingEntity target = this.projectileOwner.getTarget();
                return target == null ? null : target.getEyePosition();
            }
            return null;
        }
    }

    private Direction getMoveDirection(Vec3 targetPoint) {
        Direction moveDir = this.moveDirection;
        if (moveDir == null) {
            double x = targetPoint.x - this.getX();
            double y = targetPoint.y - this.getBoundingBox().minY;
            double z = targetPoint.z - this.getZ();
            moveDir = this.calculateDirection(x, y, z);
            this.moveDirection = moveDir;
        }

        return moveDir;
    }

    public Direction calculateDirection(double x, double y, double z) {
        double absX = Math.abs(x);
        double absY = Math.abs(y);
        double absZ = Math.abs(z);
        if (absY > absX && absY > absZ) {
            return y > 0.0 ? Direction.UP : Direction.DOWN;
        } else if (absX > absZ) {
            return x > 0.0 ? Direction.EAST : Direction.WEST;
        } else {
            return z > 0.0 ? Direction.SOUTH : Direction.NORTH;
        }
    }

    private double axisDistance(Vec3 target, Vec3 start, Direction direction) {
        double x = target.x() - start.x();
        double y = target.y() - start.y();
        double z = target.z() - start.z();
        return x * (double) direction.getStepX() + y * (double) direction.getStepY() + z * (double) direction.getStepZ();
    }

    public int calculateMoveDelay() {
        return 2 + this.random.nextInt(14);
    }

    public float getVelocityIncrease() {
        return 0.035F - 400.0F / 30000.0F;
    }

    public float getMaxVelocity() {
        return 5.0F;
    }

    @Override
    public void lerpTo(double x, double y, double z, float yRot, float xRot, int steps) {
        this.lerpX = x;
        this.lerpY = y;
        this.lerpZ = z;
        this.lerpSteps = steps + 2;
        this.setDeltaMovement(this.targetDeltaMovement);
    }

    @Override
    public double lerpTargetX() {
        return this.lerpSteps > 0 ? this.lerpX : this.getX();
    }

    @Override
    public double lerpTargetY() {
        return this.lerpSteps > 0 ? this.lerpY : this.getY();
    }

    @Override
    public double lerpTargetZ() {
        return this.lerpSteps > 0 ? this.lerpZ : this.getZ();
    }

    @Override
    public void lerpMotion(double x, double y, double z) {
        this.targetDeltaMovement = new Vec3(x, y, z);
        this.setDeltaMovement(this.targetDeltaMovement);
    }

    @Override
    public void checkDespawn() {
        if (this.level().getDifficulty() == Difficulty.PEACEFUL
                || this.projectileOwner == null
                || !this.projectileOwner.isAlive()
                || !this.projectileOwner.isAwake()) {
            if (this.projectileOwner != null) {
                this.projectileOwner.getEyeProjectiles().removeIf((projectile) -> projectile.getId() == this.getId());
            }
            this.discard();
        }
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return super.canHitEntity(entity) && !entity.equals(this.projectileOwner);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        this.stop();
    }

    private void stop() {
        this.moveDirection = null;
        this.moveDelay = this.calculateMoveDelay();
        this.targetPoint = null;
        this.velocity = 0.0F;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.projectileOwner != null) {
            tag.putInt("ProjectileOwner", this.projectileOwner.getId());
        }
        if (this.targetPoint != null) {
            tag.putDouble("xTarget", this.targetPoint.x());
            tag.putDouble("yTarget", this.targetPoint.y());
            tag.putDouble("zTarget", this.targetPoint.z());
        }
        tag.putFloat("Velocity", this.velocity);
        if (this.moveDirection != null) {
            tag.putInt("Direction", this.moveDirection.get3DDataValue());
        }
        tag.putInt("MoveDelay", this.moveDelay);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("ProjectileOwner")) {
            if (this.level().getEntity(tag.getInt("ProjectileOwner")) instanceof SliderHostMimic mob) {
                this.projectileOwner = mob;
            }
        }
        double targetX = 0.0;
        double targetY = 0.0;
        double targetZ = 0.0;
        if (tag.contains("xTarget")) {
            targetX = tag.getDouble("xTarget");
        }
        if (tag.contains("yTarget")) {
            targetY = tag.getDouble("yTarget");
        }
        if (tag.contains("zTarget")) {
            targetZ = tag.getDouble("zTarget");
        }
        this.targetPoint = new Vec3(targetX, targetY, targetZ);
        if (tag.contains("Direction")) {
            this.moveDirection = Direction.from3DDataValue(tag.getInt("Direction"));
        }
        if (tag.contains("MoveDelay")) {
            this.moveDelay = tag.getInt("MoveDelay");
        }
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