package com.aetherteam.genesis.block.natural;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.genesis.mixin.mixins.common.accessor.WallBlockAccessor;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class GenesisDoubleDropsWall extends WallBlock {
    public GenesisDoubleDropsWall(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, false));
        this.fixShapeMaps();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AetherBlockStateProperties.DOUBLE_DROPS);
    }

    /**
     * Based on the Framed Blocks mod's shape map fix for implementing a wall with additional block properties.
     */
    private void fixShapeMaps() {
        WallBlockAccessor wallBlockAccessor = (WallBlockAccessor) this;
        Map<BlockState, VoxelShape> shapeByIndex = wallBlockAccessor.aether_genesis$getShapeByIndex();
        shapeByIndex = fixShapeMap(shapeByIndex);
        wallBlockAccessor.aether_genesis$setShapeByIndex(shapeByIndex);

        Map<BlockState, VoxelShape> collisionShapeByIndex = wallBlockAccessor.aether_genesis$getCollisionShapeByIndex();
        collisionShapeByIndex = fixShapeMap(collisionShapeByIndex);
        wallBlockAccessor.aether_genesis$setCollisionShapeByIndex(collisionShapeByIndex);
    }

    private static Map<BlockState, VoxelShape> fixShapeMap(Map<BlockState, VoxelShape> map) {
        ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();
        builder.putAll(map);
        for (BlockState state : map.keySet()) {
            builder.put(state.cycle(AetherBlockStateProperties.DOUBLE_DROPS), map.get(state));
        }
        return builder.build();
    }
}
