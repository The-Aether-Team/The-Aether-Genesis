package com.aetherteam.genesis.mixin.mixins.common;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.utility.AltarBlock;
import com.aetherteam.genesis.GenesisConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {
    @Unique
    private static final VoxelShape SHAPE_BASE_1 = Block.box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);
    @Unique
    private static final VoxelShape SHAPE_BASE_2 = Block.box(4.0, 2.0, 4.0, 12.0, 4.0, 12.0);
    @Unique
    private static final VoxelShape SHAPE_COLUMN = Block.box(7.0, 4.0, 7.0, 9.0, 9.0, 9.0);
    @Unique
    private static final VoxelShape SHAPE_TOP_1 = Block.box(5.0, 9.0, 5.0, 11.0, 12.0, 11.0);
    @Unique
    private static final VoxelShape SHAPE_TOP_2 = Block.box(1.0, 12.0, 1.0, 15.0, 15.0, 15.0);
    @Unique
    private static final VoxelShape SHAPE_CORNER_1 = Block.box(0.0, 11.0, 0.0, 4.0, 16.0, 4.0);
    @Unique
    private static final VoxelShape SHAPE_CORNER_2 = Block.box(12.0, 11.0, 0.0, 16.0, 16.0, 4.0);
    @Unique
    private static final VoxelShape SHAPE_CORNER_3 = Block.box(0.0, 11.0, 12.0, 4.0, 16.0, 16.0);
    @Unique
    private static final VoxelShape SHAPE_CORNER_4 = Block.box(12.0, 11.0, 12.0, 16.0, 16.0, 16.0);
    @Unique
    private static final VoxelShape SHAPE_SCROLL_X = Block.box(4.0, 15.0, 5.0, 12.0, 16.0, 11.0);
    @Unique
    private static final VoxelShape SHAPE_SCROLL_Z = Block.box(5.0, 15.0, 4.0, 11.0, 16.0, 12.0);
    @Unique
    private static final VoxelShape SHAPE_X = Shapes.or(SHAPE_BASE_1, SHAPE_BASE_2, SHAPE_COLUMN, SHAPE_TOP_1, SHAPE_TOP_2, SHAPE_CORNER_1, SHAPE_CORNER_2, SHAPE_CORNER_3, SHAPE_CORNER_4, SHAPE_SCROLL_X);
    @Unique
    private static final VoxelShape SHAPE_Z = Shapes.or(SHAPE_BASE_1, SHAPE_BASE_2, SHAPE_COLUMN, SHAPE_TOP_1, SHAPE_TOP_2, SHAPE_CORNER_1, SHAPE_CORNER_2, SHAPE_CORNER_3, SHAPE_CORNER_4, SHAPE_SCROLL_Z);

    @Inject(at = @At(value = "HEAD"), method = "getShape(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;", cancellable = true)
    private void getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        try {
            if (GenesisConfig.STARTUP.altar_redesign.get() && AetherBlocks.ALTAR.isBound() && state.is(AetherBlocks.ALTAR)) {
                if (state.getValue(AltarBlock.FACING).getAxis() == Direction.Axis.X) {
                    cir.setReturnValue(SHAPE_X);
                } else {
                    cir.setReturnValue(SHAPE_Z);
                }
            }
        } catch (Exception ignored) { }
    }
}
