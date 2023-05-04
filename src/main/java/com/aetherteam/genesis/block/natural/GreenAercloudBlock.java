package com.aetherteam.genesis.block.natural;

import com.aetherteam.aether.block.natural.AercloudBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GreenAercloudBlock extends AercloudBlock {
    protected static final VoxelShape COLLISION_SHAPE = Shapes.empty();

    public GreenAercloudBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!entity.isShiftKeyDown()) {
            entity.resetFallDistance();
            int direction = level.getRandom().nextInt(4);
            switch (direction) {
                case 0 -> entity.setDeltaMovement(2.0, entity.getDeltaMovement().y(), 0);
                case 1 -> entity.setDeltaMovement(-2.0, entity.getDeltaMovement().y(), 0);
                case 2 -> entity.setDeltaMovement(0, entity.getDeltaMovement().y(), 2.0);
                case 3 -> entity.setDeltaMovement(0, entity.getDeltaMovement().y(), -2.0);
            }
        } else {
            super.entityInside(state, level, pos, entity);
        }
    }

    @Override
    public VoxelShape getDefaultCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPE;
    }
}
