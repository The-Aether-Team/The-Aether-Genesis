package com.aetherteam.genesis.mixin.mixins.common.accessor;

import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SpawnerBlockEntity.class)
public interface SpawnerBlockEntityAccessor {
    @Accessor("spawner")
    BaseSpawner aether_genesis$getSpawner();
}
