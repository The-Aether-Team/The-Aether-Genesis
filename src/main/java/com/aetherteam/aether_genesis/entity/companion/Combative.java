package com.aetherteam.aether_genesis.entity.companion;

import net.minecraft.world.entity.LivingEntity;

public interface Combative {
    boolean wantsToAttack(LivingEntity target, LivingEntity owner);
}
