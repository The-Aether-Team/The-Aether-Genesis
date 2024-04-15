package com.aetherteam.aether_genesis.mixin.mixins.client;

//todo
//@Mixin(ElytraLayer.class)
//public class ElytraLayerMixin<T extends LivingEntity, M extends EntityModel<T>> {
//    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ElytraModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V", shift = At.Shift.BEFORE), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", cancellable = true)
//    private void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
//        if (livingEntity instanceof AbstractClientPlayer abstractClientPlayer) {
//            ElytraLayer<T, M> elytraLayer = (ElytraLayer<T, M>) (Object) this;
//            SlotResult slotResult = EquipmentUtil.getCurio(livingEntity, GenesisItems.CAPE.get());
//            if (slotResult != null && abstractClientPlayer.isCapeLoaded() && abstractClientPlayer.getCloakTextureLocation() != null && abstractClientPlayer.isModelPartShown(PlayerModelPart.CAPE)) {
//                ResourceLocation location = abstractClientPlayer.getCloakTextureLocation();
//                DyeableCape dyeableItem = (DyeableCape) slotResult.stack().getItem();
//                int i = ((net.minecraft.world.item.DyeableLeatherItem) dyeableItem).getColor(slotResult.stack());
//                float red = (float) (i >> 16 & 255) / 255.0F;
//                float green = (float) (i >> 8 & 255) / 255.0F;
//                float blue = (float) (i & 255) / 255.0F;
//                VertexConsumer consumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(location), false, slotResult.stack().hasFoil());
//                ((ElytraLayerAccessor<T>) elytraLayer).aether_genesis$getElytraModel().renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
//                poseStack.popPose();
//                ci.cancel();
//            }
//        }
//    }
//}
