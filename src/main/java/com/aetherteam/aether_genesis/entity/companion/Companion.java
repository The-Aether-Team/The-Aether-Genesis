package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.entity.ai.goal.CompanionFollowGoal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.player.Player;

public interface Companion {
    default void registerGoals(PathfinderMob mob, GoalSelector goals) {
        goals.addGoal(0, new CompanionFollowGoal(mob, 1.0F));
    }

    void setOwner(Player owner);
    Player getOwner();
}
