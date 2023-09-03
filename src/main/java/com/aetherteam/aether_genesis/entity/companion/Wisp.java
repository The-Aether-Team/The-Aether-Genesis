package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class Wisp extends PathfinderMob implements Companion {
    private static final EntityDataAccessor<Optional<UUID>> DATA_OWNER_ID = SynchedEntityData.defineId(Wisp.class, EntityDataSerializers.OPTIONAL_UUID);
    private float armRotO;
    private float armRot;

    public Wisp(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        Companion.super.registerGoals(this, this.goalSelector);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 5.0D).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_OWNER_ID, Optional.empty());
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        if (tag != null) {
            if (tag.contains("Owner")) {
                this.setOwner(tag.getUUID("Owner"));
            }
        }
        return spawnData;
    }

    @Override
    public void tick() {
        super.tick();
        AttributeInstance gravity = this.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
        if (gravity != null) {
            double fallSpeed = Math.max(gravity.getValue() * -1.25, -0.1); // Entity isn't allowed to fall too slowly from gravity.
            if (this.getDeltaMovement().y() < fallSpeed) {
                this.setDeltaMovement(this.getDeltaMovement().x(), fallSpeed, this.getDeltaMovement().z());
                this.hasImpulse = true;
            }
        }
        if (this.getLevel().isClientSide()) {
            this.armRotO = this.armRot;
            this.armRot += 0.5F;
        }
        if (this.getOwner() != null) {
            Player player = this.getLevel().getPlayerByUUID(this.getOwner());
            if (player == null || !player.isAlive()) {
                this.discard();
            }
        } else {
            this.discard();
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        Companion.super.travel(this, travelVector);
    }

    @Override
    protected void jumpFromGround() { }

    @Override
    public UUID getOwner() {
        return this.getEntityData().get(DATA_OWNER_ID).orElse(null);
    }

    @Override
    public ItemStack getSummonItem() {
        return new ItemStack(GenesisItems.SOARING_STONE.get());
    }

    @Override
    public void setOwner(UUID owner) {
        this.getEntityData().set(DATA_OWNER_ID, Optional.ofNullable(owner));
    }

    public float getArmRotO() {
        return this.armRotO;
    }

    public float getArmRot() {
        return this.armRot;
    }

    @Override
    public float getFrictionModifier() {
        if (Math.abs(this.getDeltaMovement().x() * this.getDeltaMovement().z()) < 0.00075) {
            return 0.95F;
        } else {
            return 0.6F;
        }
    }

    @Override
    public boolean isAffectedByFluids() {
        return super.isAffectedByFluids();
    }

    @Override
    public float getWaterSlowDown() {
        return super.getWaterSlowDown();
    }

    @Override
    public BlockPos getMovementAffectingBlockPos() {
        return super.getBlockPosBelowThatAffectsMyMovement();
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

    @Override
    public boolean onClimbable() {
        return this.horizontalCollision;
    }

    @Override
    public boolean shouldShowName() {
        return true;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
