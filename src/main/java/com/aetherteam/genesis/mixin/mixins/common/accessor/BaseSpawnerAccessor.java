package com.aetherteam.genesis.mixin.mixins.common.accessor;

import net.minecraft.world.level.BaseSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BaseSpawner.class)
public interface BaseSpawnerAccessor {
    @Accessor("spawnCount")
    void aether_genesis$setSpawnCount(int spawnCount);

    @Accessor("maxNearbyEntities")
    void aether_genesis$setMaxNearbyEntities(int maxNearbyEntities);
}
