package com.aetherteam.aether_genesis.entity.ai.goal;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether_genesis.entity.passive.Zephyroo;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;

import java.util.EnumSet;
import java.util.function.Predicate;

public class ZephyrooGrazeGoal extends Goal {
    private static final Predicate<BlockState> IS_TALL_GRASS = BlockStatePredicate.forBlock(Blocks.SHORT_GRASS);
    private final Zephyroo mob;
    private int hungerTicks;
    private boolean eating;

    public ZephyrooGrazeGoal(Zephyroo zephyroo) {
        this.mob = zephyroo;
        this.hungerTicks = adjustedTickDelay(200);
        setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return hungerTicks-- <= 0;
    }

    @Override
    public void start() {
        ((Zephyroo.ZephyrooMoveControl) mob.getMoveControl()).hopping = false;
    }

    public void stop() {
        ((Zephyroo.ZephyrooMoveControl) mob.getMoveControl()).hopping = true;
        hungerTicks = adjustedTickDelay(200);
        eating = false;
    }

    @Override
    public void tick() {
        if (eating) {
            eatGrass();
        } else {
            Aether.LOGGER.info("Searching");
            searchForGrass();
            // End the search early if it takes too long.
            if (hungerTicks < adjustedTickDelay(-200)) {
                stop();
            }
        }
    }

    private void eatGrass() {
        int eatAnimationTick = Math.max(0, this.mob.getEatAnimationTick() - 1);
        this.mob.setEatAnimationTick(eatAnimationTick);
        if (eatAnimationTick == this.adjustedTickDelay(4)) {
            BlockPos blockpos = this.mob.blockPosition();
            if (IS_TALL_GRASS.test(this.mob.level().getBlockState(blockpos))) {
                if (net.neoforged.neoforge.event.EventHooks.getMobGriefingEvent(this.mob.level(), this.mob)) {
                    this.mob.level().destroyBlock(blockpos, false);
                }

                this.mob.ate();
            } else {
                BlockPos blockpos1 = blockpos.below();
                if (this.mob.level().getBlockState(blockpos1).is(AetherBlocks.AETHER_GRASS_BLOCK.get())) {
                    if (net.neoforged.neoforge.event.EventHooks.getMobGriefingEvent(this.mob.level(), this.mob)) {
                        this.mob.level().levelEvent(2001, blockpos1, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
                        this.mob.level().setBlock(blockpos1, AetherBlocks.AETHER_DIRT.get().defaultBlockState(), 2);
                    }

                    this.mob.ate();
                }
            }
        }
        if (eatAnimationTick <= 0) {
            stop();
        }
    }

    private void searchForGrass() {
        BlockPos pos = mob.blockPosition();
        if (IS_TALL_GRASS.test(mob.level().getBlockState(pos)) || mob.level().getBlockState(pos.below()).is(AetherBlocks.AETHER_GRASS_BLOCK.get())) {
            startEatingGrass();
        } else if (mob.getNavigation().isDone()) {
            RandomSource random = mob.getRandom();
            int x = random.nextInt(7) - 3;
            int y = random.nextInt(7) - 3;
            int z = random.nextInt(7) - 3;
            mob.getNavigation().moveTo(mob.getX() + x, mob.getY() + y, mob.getZ() + z, 1.0);
        }
    }

    private void startEatingGrass() {
        eating = true;
        mob.setEatAnimationTick(adjustedTickDelay(40));
        mob.level().broadcastEntityEvent(mob, (byte)10);
        mob.getNavigation().stop();
    }
}
