package com.aetherteam.aether_genesis.mixin.mixins.common.accessor;

import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(WallBlock.class)
public interface WallBlockAccessor { //todo rename
    @Accessor
    Map<BlockState, VoxelShape> getShapeByIndex();

    @Mutable
    @Accessor
    void setShapeByIndex(Map<BlockState, VoxelShape> shapeByIndex);

    @Accessor
    Map<BlockState, VoxelShape> getCollisionShapeByIndex();

    @Mutable
    @Accessor
    void setCollisionShapeByIndex(Map<BlockState, VoxelShape> collisionShapeByIndex);
}
