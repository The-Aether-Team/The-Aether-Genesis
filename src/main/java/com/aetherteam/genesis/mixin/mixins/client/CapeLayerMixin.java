package com.aetherteam.genesis.mixin.mixins.client;

import com.aetherteam.aether.item.EquipmentUtil;
import com.aetherteam.genesis.item.GenesisItems;
import com.aetherteam.genesis.mixin.mixins.client.accessor.PlayerModelAccessor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.component.DyedItemColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {
    /**
     * Used to render a dyed cape.
     */
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/PlayerModel;renderCloak(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V", shift = At.Shift.AFTER), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V")
    private void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        CapeLayer capeLayer = (CapeLayer) (Object) this;
        SlotEntryReference slotResult = EquipmentUtil.getAccessory(livingEntity, GenesisItems.CAPE.get());
        if (slotResult != null && livingEntity.getSkin().capeTexture() != null) {
            int color = DyedItemColor.getOrDefault(slotResult.stack(), 16777215);
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entitySolid(livingEntity.getSkin().capeTexture()));
            ((PlayerModelAccessor) capeLayer.getParentModel()).aether_genesis$getCloak().render(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, color);
        }
    }
}
