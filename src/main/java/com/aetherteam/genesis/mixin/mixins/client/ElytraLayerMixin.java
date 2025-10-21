package com.aetherteam.genesis.mixin.mixins.client;

import com.aetherteam.aether.item.EquipmentUtil;
import com.aetherteam.aether.item.accessories.cape.CapeItem;
import com.aetherteam.genesis.item.GenesisItems;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ElytraLayer.class)
public abstract class ElytraLayerMixin<T extends LivingEntity, M extends EntityModel<T>> {
    @Shadow
    @Final
    private ElytraModel<T> elytraModel;

    @WrapOperation(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ElytraModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V"))
    private void render(ElytraModel<T> instance, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int overlay, Operation<Void> original, @Local(argsOnly = true) T livingEntity, @Local ResourceLocation location) {
        SlotEntryReference slotResult = EquipmentUtil.getAccessory(livingEntity, GenesisItems.CAPE.get());
        if (slotResult != null && slotResult.stack().getItem() instanceof CapeItem capeItem && this.getElytraTexture(livingEntity.getItemBySlot(EquipmentSlot.CHEST), livingEntity).equals(capeItem.getCapeTexture())) {
            int color = DyedItemColor.getOrDefault(slotResult.stack(), 16777215);
            this.elytraModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, color);
        } else {
            original.call(instance, poseStack, vertexConsumer, packedLight, overlay);
        }
    }

    @Shadow
    public abstract ResourceLocation getElytraTexture(ItemStack stack, T entity);
}
