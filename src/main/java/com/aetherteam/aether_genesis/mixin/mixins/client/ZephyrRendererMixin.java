package com.aetherteam.aether_genesis.mixin.mixins.client;

import com.aetherteam.aether.client.renderer.entity.ZephyrRenderer;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether_genesis.capability.zephyr.ZephyrColor;
import com.mojang.blaze3d.vertex.PoseStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZephyrRenderer.class)
public class ZephyrRendererMixin {
    @Inject(at = @At(value = "TAIL"), method = "scale(Lcom/aetherteam/aether/entity/monster/Zephyr;Lcom/mojang/blaze3d/vertex/PoseStack;F)V", remap = false)
    private void scale(Zephyr zephyr, PoseStack poseStack, float partialTicks, CallbackInfo ci) {
        ZephyrColor.get(zephyr).ifPresent((zephyrColor) -> {
            if (zephyrColor.isTan()) {
                poseStack.scale(0.75F, 0.75F, 0.75F);
            }
        });
    }
}
