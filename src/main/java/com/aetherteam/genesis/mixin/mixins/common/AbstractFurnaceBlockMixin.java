package com.aetherteam.genesis.mixin.mixins.common;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.genesis.GenesisConfig;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlock.class)
public class AbstractFurnaceBlockMixin {
    @Inject(at = @At(value = "HEAD"), method = "getRenderShape(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/RenderShape;", cancellable = true)
    private void getRenderShape(BlockState state, CallbackInfoReturnable<RenderShape> cir) {
        try {
            if (GenesisConfig.STARTUP.altar_redesign.get() && AetherBlocks.ALTAR.isBound() && state.is(AetherBlocks.ALTAR)) {
                cir.setReturnValue(RenderShape.INVISIBLE);
            }
        } catch (Exception ignored) { }
    }
}
