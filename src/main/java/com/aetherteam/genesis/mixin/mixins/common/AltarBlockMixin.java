package com.aetherteam.genesis.mixin.mixins.common;

import com.aetherteam.aether.block.utility.AltarBlock;
import com.aetherteam.genesis.GenesisConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AltarBlock.class)
public class AltarBlockMixin {
    @Inject(at = @At(value = "HEAD"), method = "animateTick(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V", cancellable = true)
    private void getOcclusionShape(BlockState state, Level level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (GenesisConfig.STARTUP.altar_redesign.get()) {
            ci.cancel();
        }
    }
}
