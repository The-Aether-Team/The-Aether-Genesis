package com.aetherteam.genesis.mixin.mixins.client;

import com.aetherteam.aether.item.EquipmentUtil;
import com.aetherteam.genesis.item.GenesisItems;
import com.aetherteam.genesis.mixin.mixins.client.accessor.ElytraLayerAccessor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.component.DyedItemColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ElytraLayer.class)
public class ElytraLayerMixin<T extends LivingEntity, M extends EntityModel<T>> {
    /**
     * Used to render a colored Elytra for dyed capes.
     */
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ElytraModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V", shift = At.Shift.BEFORE), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", cancellable = true)
    private void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (livingEntity instanceof AbstractClientPlayer abstractClientPlayer) {
            ElytraLayer<T, M> elytraLayer = (ElytraLayer<T, M>) (Object) this;
            SlotEntryReference slotResult = EquipmentUtil.getAccessory(livingEntity, GenesisItems.CAPE.get());
            if (slotResult != null && abstractClientPlayer.isModelPartShown(PlayerModelPart.CAPE) && abstractClientPlayer.getSkin().capeTexture() != null) {
                ResourceLocation location = abstractClientPlayer.getSkin().capeTexture();
                int color = DyedItemColor.getOrDefault(slotResult.stack(), 16777215);
                VertexConsumer consumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(location), slotResult.stack().hasFoil());
                ((ElytraLayerAccessor<T>) elytraLayer).aether_genesis$getElytraModel().renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, color);
                poseStack.popPose();
                ci.cancel();
            }
        }
    }
}
