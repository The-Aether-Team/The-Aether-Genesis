package com.aetherteam.genesis.entity.projectile;

import com.aetherteam.aether.data.resources.registries.AetherDamageTypes;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.monster.dungeon.boss.LabyrinthEye;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

public class CogProjectile extends Projectile {
    public static final EntityDataAccessor<Boolean> SIZE = SynchedEntityData.defineId(CogProjectile.class, EntityDataSerializers.BOOLEAN);
    public double xPower;
    public double yPower;
    public double zPower;
    protected int ticksInAir = 0;

    public CogProjectile(EntityType<? extends CogProjectile> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    /**
     * @param shooter - The entity that created this projectile
     */
    public CogProjectile(Level level, Entity shooter, Boolean large) {
        this(GenesisEntityTypes.FLYING_COG.get(), level);
        this.setLarge(large);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getY() + 1, shooter.getZ());
        // Randomizes motion on spawn.
        float rotation = this.random.nextFloat() * 360;
        this.xPower = Mth.sin(rotation) * 0.35;
        this.zPower = -Mth.cos(rotation) * 0.35;
        this.yPower = Mth.sin(this.random.nextFloat() * 360) * 0.35;
        double verticalOffset = 1 - Math.abs(this.yPower);
        this.xPower *= verticalOffset;
        this.zPower *= verticalOffset;
        this.setDeltaMovement(this.xPower, this.yPower, this.zPower);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(SIZE, false);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.onGround()) {
            ++this.ticksInAir;
        }
        if (this.ticksInAir > this.getLifeSpan()) {
            if (!this.level().isClientSide()) {
                this.discard();
            }
        }
        HitResult result = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        boolean flag = false;
        if (result.getType() != HitResult.Type.MISS && !flag && !EventHooks.onProjectileImpact(this, result)) {
            this.onHit(result);
        }
        this.checkInsideBlocks();
        this.tickMovement();
    }

    @Override
    public void remove(RemovalReason reason) {
        this.playSound(this.getImpactExplosionSoundEvent(), 2.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.2F);
        super.remove(reason);
    }

    protected void tickMovement() {
        if (!this.level().isClientSide()) {
            if (this.getOwner() == null || !this.getOwner().isAlive() || (this.getOwner() instanceof LabyrinthEye labyrinthEye && labyrinthEye.getDungeon() != null && labyrinthEye.getDungeon().dungeonPlayers().isEmpty())) {
                if (this.getImpactExplosionSoundEvent() != null) {
                    this.playSound(this.getImpactExplosionSoundEvent(), 1.0F, 1.0F);
                }
                this.discard();
            }
        }
        Vec3 vector3d = this.getDeltaMovement();
        double d2 = this.getX() + vector3d.x();
        double d0 = this.getY() + vector3d.y();
        double d1 = this.getZ() + vector3d.z();
        this.updateRotation();
        this.setPos(d2, d0, d1);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity livingEntity && livingEntity != this.getOwner()) {
            if (livingEntity.hurt(AetherDamageTypes.indirectEntityDamageSource(this.level(), AetherDamageTypes.FLOATING_BLOCK, this, this.getOwner()), 5.0F + this.random.nextInt(2))) {
                if (this.getImpactExplosionSoundEvent() != null) {
                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(), this.getImpactExplosionSoundEvent(), SoundSource.HOSTILE, 2.0F, this.random.nextFloat() - this.random.nextFloat() * 0.2F + 1.2F);
                }
                if (!this.level().isClientSide()) {
                    this.discard();
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.markHurt();
        switch (result.getDirection()) {
            case NORTH, SOUTH -> this.zPower = -this.zPower;
            case UP, DOWN -> this.yPower = -this.yPower;
            case WEST, EAST -> this.xPower = -this.xPower;
        }
        this.setDeltaMovement(this.xPower, this.yPower, this.zPower);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            this.markHurt();
            Entity entity = source.getEntity();
            if (entity != null) {
                if (!this.level().isClientSide()) {
                    Vec3 vec3 = entity.getLookAngle();
                    this.setDeltaMovement(vec3);
                    this.xPower = vec3.x() * 0.25;
                    this.yPower = vec3.y() * 0.15;
                    this.zPower = vec3.z() * 0.25;
                }
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isLarge() {
        return this.entityData.get(SIZE);
    }

    public void setLarge(boolean large) {
        this.entityData.set(SIZE, large);
    }

    protected SoundEvent getImpactExplosionSoundEvent() {
        return GenesisSoundEvents.ENTITY_COG_BREAK.get();
    }

    public int getLifeSpan() {
        return 500;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("TicksInAir", this.ticksInAir);
        tag.putDouble("XSpeed", this.xPower);
        tag.putDouble("YSpeed", this.yPower);
        tag.putDouble("ZSpeed", this.zPower);
        tag.putBoolean("Large", this.isLarge());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("TicksInAir")) {
            this.ticksInAir = tag.getInt("TicksInAir");
        }
        this.xPower = tag.getDouble("XSpeed");
        this.yPower = tag.getDouble("YSpeed");
        this.zPower = tag.getDouble("ZSpeed");
        if (tag.contains("Large")) {
            this.setLarge(tag.getBoolean("Large"));
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
