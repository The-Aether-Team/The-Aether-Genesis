package com.aetherteam.genesis.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class AvoidEnemyGoal extends Goal {
    private final PathfinderMob mob;
    private final double speedModifier;
    protected double posX;
    protected double posY;
    protected double posZ;

    public AvoidEnemyGoal(PathfinderMob mob, double speedModifier) {
        super();
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        if (target != null && target.distanceToSqr(this.mob) < 16) {
            Vec3 vec3 = this.findRandomPosition(target);
            if (vec3 != null) {
                if (target.distanceToSqr(vec3.x, vec3.y, vec3.z) < target.distanceToSqr(this.mob)) {
                    return false;
                } else {
                    this.posX = vec3.x;
                    this.posY = vec3.y;
                    this.posZ = vec3.z;
                    return true;
                }
            }
        }
        return false;
    }

    protected Vec3 findRandomPosition(LivingEntity entity) {
        return DefaultRandomPos.getPosAway(this.mob, 6, 2, entity.position());
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.getBoundingBox().contains(new Vec3(this.posX, this.posY, this.posZ))
                && !this.mob.getNavigation().isDone()
                && !this.mob.getNavigation().isStuck()
                && this.mob.getTarget() != null;
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.posX, this.posY, this.posZ, this.speedModifier);
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity avoid = null;
        if (this.mob.getTarget() != null && this.mob.getTarget().distanceToSqr(this.mob) < 16) {
            avoid = this.mob.getTarget();
        }
        if (avoid != null) {
            Vec3 vec3 = this.findRandomPosition(avoid);
            if (vec3 != null) {
                if (!(avoid.distanceToSqr(vec3.x, vec3.y, vec3.z) < avoid.distanceToSqr(this.mob))) {
                    this.posX = vec3.x;
                    this.posY = vec3.y;
                    this.posZ = vec3.z;
                    this.mob.getNavigation().moveTo(this.posX, this.posY, this.posZ, this.speedModifier);
                }
            }
        }
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
