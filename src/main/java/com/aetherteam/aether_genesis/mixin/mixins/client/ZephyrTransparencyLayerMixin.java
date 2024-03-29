package com.aetherteam.aether_genesis.mixin.mixins.client;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.client.renderer.entity.layers.ZephyrTransparencyLayer;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.capability.zephyr.ZephyrColor;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ZephyrTransparencyLayer.class)
public class ZephyrTransparencyLayerMixin {
    @Unique
    private static final ResourceLocation ZEPHYR_TAN_TRANSPARENCY_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/zephyr/zephyr_tan_layer.png");

    @ModifyVariable(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILcom/aetherteam/aether/entity/monster/Zephyr;FFFFFF)V",
            at = @At(value = "STORE"), remap = false
    )
    private VertexConsumer injected(VertexConsumer consumer, @Local MultiBufferSource buffer, @Local Zephyr zephyr) {
        if (!AetherConfig.CLIENT.legacy_models.get()) {
            LazyOptional<ZephyrColor> zephyrColorLazy = ZephyrColor.get(zephyr);
            if (zephyrColorLazy.isPresent() && zephyrColorLazy.resolve().isPresent()) {
                ZephyrColor color = zephyrColorLazy.resolve().get();
                if (color.isTan()) {
                    return buffer.getBuffer(RenderType.entityTranslucent(ZEPHYR_TAN_TRANSPARENCY_TEXTURE));
                }
            }
        }
        return consumer;
    }
}
