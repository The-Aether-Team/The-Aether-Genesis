package com.aetherteam.genesis.entity.projectile;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.entity.monster.dungeon.Sentry;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.monster.dungeon.BattleSentry;
import com.aetherteam.genesis.entity.monster.dungeon.TrackingGolem;
import com.aetherteam.genesis.entity.monster.dungeon.boss.SentryGuardian;
import com.aetherteam.genesis.entity.monster.dungeon.boss.SliderHostMimic;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HostEyeProjectile extends PathfinderMob {
    public static final EntityDataAccessor<Boolean> MOVEMENT = SynchedEntityData.defineId(HostEyeProjectile.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> TIMER = SynchedEntityData.defineId(HostEyeProjectile.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> DIRECTION = SynchedEntityData.defineId(HostEyeProjectile.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Float> SPEEDY = SynchedEntityData.defineId(HostEyeProjectile.class, EntityDataSerializers.FLOAT);

    private final SliderHostMimic host;
    private boolean movement;
    private int timer;
    private int direction;
    public float speedy;
    public float harvey;

    public HostEyeProjectile(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        host = new SliderHostMimic(GenesisEntityTypes.SLIDER_HOST_MIMIC.get(), this.level());
    }


    protected float getJumpPower() {
        return 0.0F;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return this.isRemoved() || !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

    protected void dealDamage(LivingEntity pLivingEntity) {
        if (this.isAlive()) {
            if (this.hasLineOfSight(pLivingEntity) && pLivingEntity.hurt(this.damageSources().mobAttack(this), 4)) {
                this.playSound(AetherSoundEvents.ENTITY_SLIDER_COLLIDE.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.doEnchantDamageEffects(this, pLivingEntity);
            }
        }
    }

    public void playerTouch(Player pEntity) {
        this.dealDamage(pEntity);
    }

    public void tick() {
        super.tick();
        this.jumping = false;
        this.setRot(0, 0);
        if (this.getTarget() != null && this.getTarget() instanceof LivingEntity) {
            LivingEntity e1 = this.getTarget();
            if (e1.getHealth() <= 0.0F || !canAttack(this.getTarget())) {
                this.setTarget(null);
                stop();
                this.timer = 0;
                return;
            }
        } else {
            if (this.getTarget() != null && this.getTarget().isDeadOrDying()) {
                this.setTarget(null);
                stop();
                this.timer = 0;
                return;
            }
            if (this.getTarget() == null) {
                this.setTarget(level().getNearestPlayer(this, -1.0));
                if (this.getTarget() == null) {
                    this.setTarget(null);
                    stop();
                    this.timer = 0;
                    if (!this.level().isClientSide)
                        discard();
                    return;
                }
            }
        }
        if (this.host == null || this.host.isDeadOrDying()) {
            this.setTarget(null);
            stop();
            this.timer = 0;
            if (!this.level().isClientSide)
                discard();
            return;
        }
        if (!this.host.canAttack(this.getTarget())) {
            this.setTarget(null);
            stop();
            this.timer = 0;
            if (!this.level().isClientSide)
                discard();
            return;
        }
        this.fallDistance = 0.0F;
        if (this.movement) {
            if (this.isPushable()) {
                this.level().playLocalSound(this.position().x, this.position().y, this.position().z, SoundEvents.GENERIC_EXPLODE, SoundSource.AMBIENT, 3.0F, (0.625F + (this.level().random.nextFloat() - this.level().random.nextFloat()) * 0.2F) * 0.7F, false);
                this.level().playSound(this, this.blockPosition(), AetherSoundEvents.ENTITY_SLIDER_COLLIDE.get(), SoundSource.AMBIENT, 2.5F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
                stop();
            } else {
                if (this.speedy < 2.0F)
                    this.speedy += 0.035F;
                this.setDeltaMovement(0, 0, 0);
                if (this.direction == 0) {
                    this.addDeltaMovement(new Vec3(0, this.speedy, 0));
                    if (this.getBoundingBox().minY > this.getTarget().getBoundingBox().minY + 0.35D) {
                        stop();
                        this.timer = 8;
                    }
                } else if (this.direction == 1) {
                    this.addDeltaMovement(new Vec3(0, this.getDeltaMovement().y -this.speedy, 0));
                    if (this.getBoundingBox().minY < this.getTarget().getBoundingBox().minY - 0.25D) {
                        stop();
                        this.timer = 8;
                    }
                } else if (this.direction == 2) {
                    this.addDeltaMovement(new Vec3(this.speedy, 0, 0));
                    if (this.position().x > this.getTarget().position().x + 0.125D) {
                        stop();
                        this.timer = 8;
                    }
                } else if (this.direction == 3) {
                    this.addDeltaMovement(new Vec3(this.getDeltaMovement().x -this.speedy, 0, 0));
                    if (this.position().x < this.getTarget().position().x - 0.125D) {
                        stop();
                        this.timer = 8;
                    }
                } else if (this.direction == 4) {
                    this.addDeltaMovement(new Vec3(0, 0, this.speedy));
                    if (this.position().z > this.getTarget().position().z + 0.125D) {
                        stop();
                        this.timer = 8;
                    }
                } else if (this.direction == 5) {
                    this.addDeltaMovement(new Vec3(0, 0, this.getDeltaMovement().z -this.speedy));
                    if (this.position().z < this.getTarget().position().z - 0.125D) {
                        stop();
                        this.timer = 8;
                    }
                }
            }
        } else {
            this.setDeltaMovement(this.getDeltaMovement().x, 0, this.getDeltaMovement().z);
            if (this.timer > 0) {
                this.timer--;
                this.setDeltaMovement(0, 0, 0);
            } else {
                double a = Math.abs(this.position().x - this.getTarget().position().x);
                double b = Math.abs(this.getBoundingBox().minY - this.getTarget().getBoundingBox().minY);
                double c = Math.abs(this.position().z - this.getTarget().position().z);
                if (a > c) {
                    this.direction = 2;
                    if (this.position().x > this.getTarget().position().x)
                        this.direction = 3;
                } else {
                    this.direction = 4;
                    if (this.position().z > this.getTarget().position().z)
                        this.direction = 5;
                }
                if ((b > a && b > c) || (b > 0.25D && this.random.nextInt(5) == 0)) {
                    this.direction = 0;
                    if (this.position().y > this.getTarget().position().y)
                        this.direction = 1;
                }
                this.movement = true;
            }
        }
        if (this.harvey > 0.01F)
            this.harvey *= 0.8F;
    }

    public void push(Entity entity) {
        if (this.movement) {
            if (entity instanceof Sentry || entity instanceof  TrackingGolem || entity instanceof  SliderHostMimic || entity instanceof SentryGuardian || entity instanceof BattleSentry)
                return;
            boolean flag = entity.hurt(this.damageSources().thrown(this, this.host), 6.0F);
            if (flag && entity instanceof LivingEntity) {
                this.level().playSound(this, this.blockPosition(), AetherSoundEvents.ENTITY_SLIDER_COLLIDE.get(), SoundSource.AMBIENT, 2.5F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
                LivingEntity ek = (LivingEntity)entity;
                ek.setDeltaMovement(ek.getDeltaMovement().x + 0.35D,ek.getDeltaMovement().y +  2.0D,ek.getDeltaMovement().z +  2.0D);
                stop();
            }
        }
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOVEMENT, movement);
        this.entityData.define(TIMER, timer);
        this.entityData.define(DIRECTION, direction);
        this.entityData.define(SPEEDY, speedy);
    }

    public void stop() {
        this.movement = false;
        this.timer = 12;
        this.direction = 0;
        this.speedy = 0.0F;
        this.setDeltaMovement(0, 0, 0);
    }

    @Override
    public void knockback(double pStrength, double pX, double pZ) {

    }

    @Override
    public void push(double x, double y, double z) {

    }

    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        return true;
    }

    @Override
    public float getYRot() {
        return 0;
    }

    @Override
    protected boolean canRide( Entity vehicle) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean shouldDiscardFriction() {
        return true;
    }
}