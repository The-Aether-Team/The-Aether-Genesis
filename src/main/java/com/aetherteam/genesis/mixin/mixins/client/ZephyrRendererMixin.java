package com.aetherteam.genesis.mixin.mixins.client;

import com.aetherteam.aether.client.renderer.entity.ZephyrRenderer;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.genesis.attachment.GenesisDataAttachments;
import com.aetherteam.genesis.attachment.ZephyrColorAttachment;
import com.mojang.blaze3d.vertex.PoseStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZephyrRenderer.class)
public class ZephyrRendererMixin {
    /**
     * Shrinks a Zephyr's scale if it is tan according to the {@link ZephyrColorAttachment}.
     */
    @Inject(at = @At(value = "TAIL"), method = "scale(Lcom/aetherteam/aether/entity/monster/Zephyr;Lcom/mojang/blaze3d/vertex/PoseStack;F)V", remap = false)
    private void scale(Zephyr zephyr, PoseStack poseStack, float partialTicks, CallbackInfo ci) {
        ZephyrColorAttachment attachment = zephyr.getData(GenesisDataAttachments.ZEPHYR_COLOR);
        if (attachment.isTan()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }
}
