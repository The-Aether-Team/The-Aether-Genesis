package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.block.natural.BlueAercloudBlock;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlueAercloudBlock.class)
public class BlueAercloudBlockMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;resetFallDistance()V", shift = At.Shift.AFTER), method = "entityInside", cancellable = true)
    private void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (!entity.isShiftKeyDown()) {
            level.playSound((entity instanceof Player player ? player : (Player) null), pos, GenesisSoundEvents.BLUE_AERCLOUD_BOUNCE.get(), SoundSource.BLOCKS, 0.8f,
                    0.5f + (level.random.nextFloat() * 0.4f));
        }
    }
}