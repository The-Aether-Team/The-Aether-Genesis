package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

/**
 * [CODE COPY] - {@link Swet}<br>
 * [CODE COPY] - {@link CompanionMob}<br><br>
 * A Swet that acts as a companion and is always passive.
 */
public class BabyPinkSwet extends Swet implements Companion<BabyPinkSwet> {
    private static final EntityDataAccessor<Optional<UUID>> DATA_OWNER_ID = SynchedEntityData.defineId(BabyPinkSwet.class, EntityDataSerializers.OPTIONAL_UUID);

    public BabyPinkSwet(EntityType<? extends Swet> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwetFollowPlayerGoal(this));
        this.goalSelector.addGoal(1, new SwetKeepOnJumpingGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0).add(Attributes.MOVEMENT_SPEED, 0.4).add(Attributes.FOLLOW_RANGE, 48.0);
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
        this.tickCompanion(this);
    }

    @Override
    public void onEquip(ItemStack itemStack) {

    }

    @Override
    public void onUnequip(ItemStack itemStack) {

    }

    @Override
    public void consumePassenger(LivingEntity livingEntity) { }

    @Override
    public void spawnDissolveParticles() { }

    @Override
    public float getWaterDamageScale() {
        return 0.0F;
    }

    @Override
    public void setWaterDamageScale(float scale) {

    }

    public UUID getOwner() {
        return this.getEntityData().get(DATA_OWNER_ID).orElse(null);
    }

    public void setOwner(UUID owner) {
        this.getEntityData().set(DATA_OWNER_ID, Optional.ofNullable(owner));
    }

    @Override
    public ItemStack getSummonItem() {
        return new ItemStack(GenesisItems.BABY_PINK_SWET.get());
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

    @Override
    public boolean canSpawnSplashParticles() {
        return false;
    }

    @Override
    public boolean isFriendlyTowardEntity(LivingEntity entity) {
        return true;
    }

    @Override
    public boolean shouldShowName() {
        return true;
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Nullable
    @Override
    public ItemStack getPickResult() {
        return this.getSummonItem();
    }

    public static class SwetFollowPlayerGoal extends Goal {
        private final BabyPinkSwet swet;
        private LivingEntity owner;
        private final LevelReader level;

        public SwetFollowPlayerGoal(BabyPinkSwet swet) {
            this.swet = swet;
            this.level = swet.level();
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            Player player = this.swet.level().getPlayerByUUID(this.swet.getOwner());
            if (player == null) {
                return false;
            } else if (player.isSpectator()) {
                return false;
            } else if (this.unableToMove()) {
                return false;
            } else if (this.swet.distanceToSqr(player) < 4.0) {
                return false;
            } else {
                this.owner = player;
                return this.swet.getMoveControl() instanceof SwetMoveControl;
            }
        }

        @Override
        public boolean canContinueToUse() {
            if (this.unableToMove()) {
                return false;
            } else {
                return !(this.swet.distanceToSqr(this.owner) <= 4.0);
            }
        }

        private boolean unableToMove() {
            return this.swet.isPassenger() || this.swet.isLeashed();
        }

        @Override
        public void start() {
            if (this.swet.getMoveControl() instanceof SwetMoveControl swetMoveControl) {
                swetMoveControl.setCanJump(true);
            }
        }

        @Override
        public void stop() {
            this.owner = null;
            if (this.swet.getMoveControl() instanceof SwetMoveControl swetMoveControl) {
                swetMoveControl.setCanJump(false);
            }
        }

        @Override
        public void tick() {
            if (this.swet.getMoveControl() instanceof SwetMoveControl swetMoveControl) {
                this.swet.lookAt(this.owner, 10.0F, 10.0F);
                swetMoveControl.setDirection(this.swet.getYRot(), true);
                if (this.swet.distanceToSqr(this.owner) >= 100.0D) {
                    this.teleportToOwner();
                }
            }
        }

        private void teleportToOwner() {
            BlockPos blockpos = this.owner.blockPosition();

            for (int i = 0; i < 10; ++i) {
                int j = this.randomIntInclusive(-3, 3);
                int k = this.randomIntInclusive(-1, 1);
                int l = this.randomIntInclusive(-3, 3);
                boolean flag = this.maybeTeleportTo(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);
                if (flag) {
                    return;
                }
            }
        }

        private boolean maybeTeleportTo(int x, int y, int z) {
            if (Math.abs((double) x - this.owner.getX()) < 2.0 && Math.abs((double) z - this.owner.getZ()) < 2.0) {
                return false;
            } else if (!this.canTeleportTo(new BlockPos(x, y, z))) {
                return false;
            } else {
                this.swet.moveTo((double) x + 0.5, y, (double) z + 0.5, this.swet.getYRot(), this.swet.getXRot());
                return true;
            }
        }

        private boolean canTeleportTo(BlockPos pos) {
            BlockPathTypes blockpathtypes = WalkNodeEvaluator.getBlockPathTypeStatic(this.level, pos.mutable());
            if (blockpathtypes != BlockPathTypes.WALKABLE) {
                return false;
            } else {
                BlockState blockstate = this.level.getBlockState(pos.below());
                if (blockstate.getBlock() instanceof LeavesBlock) {
                    return false;
                } else {
                    BlockPos blockpos = pos.subtract(this.swet.blockPosition());
                    return this.level.noCollision(this.swet, this.swet.getBoundingBox().move(blockpos));
                }
            }
        }

        private int randomIntInclusive(int min, int max) {
            return this.swet.getRandom().nextInt(max - min + 1) + min;
        }
    }
}
