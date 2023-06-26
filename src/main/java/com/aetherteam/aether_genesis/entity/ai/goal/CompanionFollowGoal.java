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
    private int calmDown;
    private boolean isRunning;

    public CompanionFollowGoal(PathfinderMob mob, double speedModifier) {
        super(mob, speedModifier, Ingredient.EMPTY, false);
        this.speedModifier = speedModifier;
        this.targetingConditions = TEMP_TARGETING.copy().selector((livingEntity) -> mob instanceof Companion companion && livingEntity.getUUID().equals(companion.getOwner().getUUID()));
    }

    @Override
    public boolean canUse() {
        if (this.calmDown > 0) {
            --this.calmDown;
            return false;
        } else {
            this.player = this.mob.level.getNearestPlayer(this.targetingConditions, this.mob);
            return this.player != null;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse();
    }

    @Override
    public void start() {
        this.isRunning = true;
    }

    @Override
    public void stop() {
        this.player = null;
        this.mob.getNavigation().stop();
        this.calmDown = reducedTickDelay(100);
        this.isRunning = false;
    }

    @Override
    public void tick() {
        this.mob.getLookControl().setLookAt(this.player, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
        if (this.mob.distanceToSqr(this.player) < 6.25D) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().moveTo(this.player, this.speedModifier);
        }
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }
}
