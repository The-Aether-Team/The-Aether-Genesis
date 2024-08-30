package com.aetherteam.genesis.mixin.mixins.client;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.passive.Sheepuff;
import com.aetherteam.genesis.AetherGenesis;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {
    @ModifyArgs(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V"))
    private void renderToBuffer(Args args, @Local(ordinal = 0, argsOnly = true) T entity, @Local(ordinal = 1, argsOnly = true) float partialTicks) {
        if (entity.getType() == AetherEntityTypes.AERBUNNY.get()) {
            if (entity.hasCustomName() && entity.getName().getString().equalsIgnoreCase("potts")) {
                float red;
                float green;
                float blue;
                int i1 = 25;
                int i = entity.tickCount / i1 + entity.getId();
                int j = DyeColor.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f3 = ((float) (entity.tickCount % i1) + partialTicks) / (float) i1;
                float[] afloat1 = Sheepuff.getColorArray(DyeColor.byId(k));
                float[] afloat2 = Sheepuff.getColorArray(DyeColor.byId(l));
                red = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
                green = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
                blue = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
                args.set(4, red);
                args.set(5, green);
                args.set(6, blue);
            }
        }
    }
}
