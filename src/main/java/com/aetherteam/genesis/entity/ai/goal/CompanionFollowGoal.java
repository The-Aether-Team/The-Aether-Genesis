package com.aetherteam.genesis.entity.ai.goal;

import com.aetherteam.genesis.entity.companion.Companion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

import java.util.EnumSet;
import java.util.UUID;

/**
 * [CODE COPY] - {@link net.minecraft.world.entity.ai.goal.FollowOwnerGoal}.<br><br>
 * Adapted to Companions.
 */
public class CompanionFollowGoal<T extends Mob & Companion<T>> extends Goal {
    private final T companion;
    private LivingEntity owner;
    private final LevelReader level;
    private final double speedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private float oldWaterCost;
    private final boolean canFly;

    public CompanionFollowGoal(T companion, double speedModifier) {
        this.companion = companion ;
        this.level = companion.level();
        this.speedModifier = speedModifier;
        this.navigation = companion.getNavigation();
        this.canFly = false;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        if (!(companion.getNavigation() instanceof GroundPathNavigation) && !(companion.getNavigation() instanceof FlyingPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for CompanionFollowGoal");
        }
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        UUID uuid = this.companion.getOwner();
        if (uuid == null) {
            return false;
        } else {
            Player player = this.companion.level().getPlayerByUUID(uuid);
            if (player == null) {
                return false;
            } else if (player.isSpectator()) {
                return false;
            } else if (this.unableToMove()) {
                return false;
            } else if (this.companion.distanceToSqr(player) < 4.0) {
                return false;
            } else {
                this.owner = player;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        if (this.navigation.isDone()) {
            return false;
        } else if (this.unableToMove()) {
            return false;
        } else {
            return !(this.companion.distanceToSqr(this.owner) <= 4.0);
        }
    }

    private boolean unableToMove() {
        return this.companion.isPassenger() || this.companion.isLeashed();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.companion.getPathfindingMalus(BlockPathTypes.WATER);
        this.companion.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.owner = null;
        this.navigation.stop();
        this.companion.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.companion.getLookControl().setLookAt(this.owner, 10.0F, (float) this.companion.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            if (this.companion.distanceToSqr(this.owner) >= 100.0D) {
                this.teleportToOwner();
            } else {
                this.navigation.moveTo(this.owner, this.speedModifier);
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
            this.companion.moveTo((double) x + 0.5, y, (double) z + 0.5, this.companion.getYRot(), this.companion.getXRot());
            this.navigation.stop();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos pos) {
        BlockPathTypes blockpathtypes = WalkNodeEvaluator.getBlockPathTypeStatic(this.level, pos.mutable());
        if (blockpathtypes != BlockPathTypes.WALKABLE) {
            return false;
        } else {
            BlockState blockstate = this.level.getBlockState(pos.below());
            if (!this.canFly && blockstate.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos blockpos = pos.subtract(this.companion.blockPosition());
                return this.level.noCollision(this.companion, this.companion.getBoundingBox().move(blockpos));
            }
        }
    }

    private int randomIntInclusive(int min, int max) {
        return this.companion.getRandom().nextInt(max - min + 1) + min;
    }
}