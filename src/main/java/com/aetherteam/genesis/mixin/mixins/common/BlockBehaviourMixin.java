package com.aetherteam.genesis.mixin.mixins.common;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.genesis.GenesisConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {
    @Inject(at = @At(value = "HEAD"), method = "getOcclusionShape(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/shapes/VoxelShape;", cancellable = true)
    private void getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos, CallbackInfoReturnable<VoxelShape> cir) {
        try {
            if (GenesisConfig.STARTUP.altar_redesign.get() && AetherBlocks.ALTAR.isBound() && state.is(AetherBlocks.ALTAR)) {
                cir.setReturnValue(Shapes.empty());
            }
        } catch (Exception ignored) { }
    }
}
