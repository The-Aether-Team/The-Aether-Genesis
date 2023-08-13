package com.aetherteam.aether_genesis.mixin.mixins.client;

import com.aetherteam.aether.item.EquipmentUtil;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.aetherteam.aether_genesis.item.accessories.DyeableCape;
import com.aetherteam.aether_genesis.mixin.mixins.client.accessor.PlayerModelAccessor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.DyeableLeatherItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotResult;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/PlayerModel;renderCloak(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V", shift = At.Shift.AFTER), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V")
    private void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        CapeLayer capeLayer = (CapeLayer) (Object) this;
        SlotResult slotResult = EquipmentUtil.getCurio(livingEntity, GenesisItems.CAPE.get());
        if (slotResult != null) {
            DyeableCape dyeableItem = (DyeableCape) slotResult.stack().getItem();
            int i = ((DyeableLeatherItem) dyeableItem).getColor(slotResult.stack());
            float red = (float) (i >> 16 & 255) / 255.0F;
            float green = (float) (i >> 8 & 255) / 255.0F;
            float blue = (float) (i & 255) / 255.0F;
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entitySolid(livingEntity.getCloakTextureLocation()));
            ((PlayerModelAccessor) capeLayer.getParentModel()).aether$getCloak().render(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
        }
    }
}
