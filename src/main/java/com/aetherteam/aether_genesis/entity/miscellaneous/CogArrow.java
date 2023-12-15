package com.aetherteam.aether_genesis.entity.miscellaneous;

import com.aetherteam.aether.data.resources.registries.AetherDamageTypes;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

public class CogArrow extends Projectile {
    public static final EntityDataAccessor<Boolean> SIZE = SynchedEntityData.defineId(CogArrow.class, EntityDataSerializers.BOOLEAN);

    public double xPower;
    public double yPower;
    public double zPower;
    protected int ticksInAir = 0;

    public CogArrow(EntityType<? extends CogArrow> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    @Override
    public void remove(RemovalReason pReason) {
        this.playSound(GenesisSoundEvents.ENTITY_COG_BREAK.get(), 2.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.2F);
        super.remove(pReason);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(SIZE, false);
    }

    public boolean isLarge() {
        return this.entityData.get(SIZE);
    }

    public void setLarge(boolean large) {
        this.entityData.set(SIZE, large);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.onGround) {
            ++this.ticksInAir;
        }
        if (this.ticksInAir > this.getLifeSpan()) {
            this.discard();
        }
        HitResult result = ProjectileUtil.getHitResult(this, this::canHitEntity);
        boolean flag = false;
        if (result.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = ((BlockHitResult) result).getBlockPos();
            BlockState blockState = this.level.getBlockState(blockPos);
            if (blockState.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(blockPos);
                flag = true;
            } else if (blockState.is(Blocks.END_GATEWAY)) {
                BlockEntity blockEntity = this.level.getBlockEntity(blockPos);
                if (blockEntity instanceof TheEndGatewayBlockEntity endGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level, blockPos, blockState, this, endGatewayBlockEntity);
                }
                flag = true;
            }
        }
        if (result.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, result)) {
            this.onHit(result);
        }
        this.checkInsideBlocks();
        this.tickMovement();
        if(this.getOwner() != null && !this.getOwner().isAlive())
            this.discard();
    }

    protected void tickMovement() {
        Vec3 vector3d = this.getDeltaMovement();
        double d2 = this.getX() + vector3d.x;
        double d0 = this.getY() + vector3d.y;
        double d1 = this.getZ() + vector3d.z;
        this.updateRotation();
        this.setPos(d2, d0, d1);
    }

    public int getLifeSpan() {
        return 300;
    }

    @Nonnull
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    /**
     * @param shooter - The entity that created this projectile
     */
    public CogArrow(Level level, Entity shooter, Boolean large) {
        this(GenesisEntityTypes.COG_ARROW.get(), level);
        this.setLarge(large);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getY() + 1, shooter.getZ());
        float rotation = this.random.nextFloat() * 360;
        this.xPower = Mth.sin(rotation) * 0.5;
        this.zPower = -Mth.cos(rotation) * 0.5;
        this.yPower = Mth.sin(this.random.nextFloat() * 360) * 0.45;
        double verticalOffset = 1 - Math.abs(this.yPower);
        this.xPower *= verticalOffset;
        this.zPower *= verticalOffset;
        this.setDeltaMovement(this.xPower, this.yPower, this.zPower);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity livingEntity && livingEntity != this.getOwner()) {
            if (livingEntity.hurt(AetherDamageTypes.indirectEntityDamageSource(this.level, AetherDamageTypes.FLOATING_BLOCK, this, this.getOwner()), 6.0F)) {
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), this.getImpactExplosionSoundEvent(), SoundSource.HOSTILE, 2.0F, this.random.nextFloat() - this.random.nextFloat() * 0.2F + 1.2F);
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

    protected SoundEvent getImpactExplosionSoundEvent() {
        return SoundEvents.ARMOR_STAND_BREAK;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            this.markHurt();
            Entity entity = source.getEntity();
            if (entity != null) {
                if (!this.level.isClientSide) {
                    Vec3 vec3 = entity.getLookAngle();
                    this.setDeltaMovement(vec3);
                    this.xPower = vec3.x * 0.25;
                    this.yPower = vec3.y * 0.15;
                    this.zPower = vec3.z * 0.25;
                }

                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("TicksInAir", this.ticksInAir);
        tag.putDouble("XSpeed", this.xPower);
        tag.putDouble("YSpeed", this.yPower);
        tag.putDouble("ZSpeed", this.zPower);
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("TicksInAir")) {
            this.ticksInAir = tag.getInt("TicksInAir");
        }
        this.xPower = tag.getDouble("XSpeed");
        this.yPower = tag.getDouble("YSpeed");
        this.zPower = tag.getDouble("ZSpeed");
    }
}
