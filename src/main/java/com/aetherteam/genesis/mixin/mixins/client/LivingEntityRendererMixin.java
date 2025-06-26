package com.aetherteam.genesis.mixin.mixins.client;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {
    @ModifyArgs(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V"))
    private void renderToBuffer(Args args, @Local(ordinal = 0, argsOnly = true) T entity, @Local(ordinal = 1, argsOnly = true) float partialTicks) {
        if (entity.getType() == AetherEntityTypes.AERBUNNY.get()) {
            if (entity.hasCustomName() && entity.getName().getString().equalsIgnoreCase("potts")) {
                int j = 25;
                int k = entity.tickCount / 25 + entity.getId();
                int l = DyeColor.values().length;
                int i1 = k % l;
                int j1 = (k + 1) % l;
                float f = ((float)(entity.tickCount % 25) + partialTicks) / 25.0F;
                int k1 = Sheep.getColor(DyeColor.byId(i1));
                int l1 = Sheep.getColor(DyeColor.byId(j1));
                int i = FastColor.ARGB32.lerp(f, k1, l1);
                args.set(4, i);
            }
        }
    }
}
