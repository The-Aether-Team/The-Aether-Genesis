package com.aetherteam.aether_genesis.entity.ai.goal;

import com.aetherteam.aether_genesis.entity.companion.Companion;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;

public class CompanionFollowGoal extends TemptGoal {
    private static final TargetingConditions TEMP_TARGETING = TargetingConditions.forNonCombat().range(50.0D).ignoreLineOfSight();
    private final TargetingConditions targetingConditions;
    private final double speedModifier;
    @Nullable
    protected Player player;
    private boolean isRunning;
    private int timeToRecalcPath;

    public CompanionFollowGoal(PathfinderMob mob, double speedModifier) {
        super(mob, speedModifier, Ingredient.EMPTY, false);
        this.speedModifier = speedModifier;
        this.targetingConditions = TEMP_TARGETING.copy().selector((livingEntity) -> mob instanceof Companion companion && companion.getOwner() != null && livingEntity.getUUID().equals(companion.getOwner()));
    }

    @Override
    public boolean canUse() {
        this.player = this.mob.level.getNearestPlayer(this.targetingConditions, this.mob);
        return this.player != null && this.mob.distanceToSqr(this.player) >= 5.0D;
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone() && this.mob.distanceToSqr(this.player) >= 5.0D;
    }

    @Override
    public void start() {
        this.isRunning = true;
        this.timeToRecalcPath = 0;
    }

    @Override
    public void stop() {
        this.player = null;
        this.mob.getNavigation().stop();
        this.isRunning = false;
    }

    @Override
    public void tick() {
        this.mob.lookAt(this.player, 10.0F, this.mob.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            if (this.mob.distanceToSqr(this.player) >= 144.0D) {
                //this.teleportToOwner();
            } else if (this.mob.distanceToSqr(this.player) >= 5.0D) {
                this.mob.getNavigation().moveTo(this.player, this.speedModifier);
            }
        }
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }
}
