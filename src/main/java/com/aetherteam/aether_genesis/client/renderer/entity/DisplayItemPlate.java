package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.entity.companion.Wisp;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.ForgeHooksClient;

public interface DisplayItemPlate {
    default void renderDisplayItem(Wisp entity, PoseStack poseStack, MultiBufferSource buffer, EntityRenderDispatcher entityRenderDispatcher, ItemRenderer itemRenderer) {
        double distance = entityRenderDispatcher.distanceToSqr(entity);
        if (ForgeHooksClient.isNameplateInRenderDistance(entity, distance)) {
            float height = entity.getBbHeight() + 0.5F;
            poseStack.pushPose();
            poseStack.translate(0.0F, height, 0.0F);
            poseStack.mulPose(entityRenderDispatcher.cameraOrientation());
            poseStack.translate(0.0F, -0.1, 0.0F);
            poseStack.scale(0.5F, 0.5F, 0.1F);
            itemRenderer.renderStatic(entity.getSummonItem(), ItemDisplayContext.HEAD, LightTexture.pack(15, 15), OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.getLevel(), entity.getId()); //todo its still affected by light.
            poseStack.popPose();
        }
    }
}
