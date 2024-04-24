package com.aetherteam.aether_genesis.entity.ai.goal;

import com.aetherteam.aether_genesis.entity.companion.Combative;
import com.aetherteam.aether_genesis.entity.companion.CompanionMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;
import java.util.UUID;

public class CompanionHurtTargetGoal<T extends CompanionMob & Combative> extends TargetGoal {
    private final T companion;
    private LivingEntity ownerLastHurt;
    private int timestamp;

    public CompanionHurtTargetGoal(T companion) {
        super(companion, false);
        this.companion = companion;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this method as well.
     */
    @Override
    public boolean canUse() {
        UUID uuid = this.companion.getOwner();
        if (uuid == null) {
            return false;
        } else {
            Player player = this.companion.level().getPlayerByUUID(uuid);
            if (player == null) {
                return false;
            } else {
                this.ownerLastHurt = player.getLastHurtMob();
                int i = player.getLastHurtMobTimestamp();
                return i != this.timestamp
                        && this.canAttack(this.ownerLastHurt, TargetingConditions.DEFAULT)
                        && this.companion.wantsToAttack(this.ownerLastHurt, player);
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void start() {
        this.mob.setTarget(this.ownerLastHurt);
        UUID uuid = this.companion.getOwner();
        if (uuid != null) {
            Player player = this.companion.level().getPlayerByUUID(uuid);
            if (player != null) {
                this.timestamp = player.getLastHurtByMobTimestamp();
            }
        }
        super.start();
    }
}
