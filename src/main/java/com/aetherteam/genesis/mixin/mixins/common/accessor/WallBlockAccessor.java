package com.aetherteam.genesis.mixin.mixins.common.accessor;

import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(WallBlock.class)
public interface WallBlockAccessor {
    @Accessor("shapeByIndex")
    Map<BlockState, VoxelShape> aether_genesis$getShapeByIndex();

    @Mutable
    @Accessor("shapeByIndex")
    void aether_genesis$setShapeByIndex(Map<BlockState, VoxelShape> shapeByIndex);

    @Accessor("collisionShapeByIndex")
    Map<BlockState, VoxelShape> aether_genesis$getCollisionShapeByIndex();

    @Mutable
    @Accessor("collisionShapeByIndex")
    void aether_genesis$setCollisionShapeByIndex(Map<BlockState, VoxelShape> collisionShapeByIndex);
}
