package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.entity.companion.Companion;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.ForgeHooksClient;

import javax.annotation.Nullable;

public interface DisplayItemPlate<T extends Mob & Companion<T>> {
    default void renderDisplayItem(T entity, PoseStack poseStack, MultiBufferSource buffer, EntityRenderDispatcher entityRenderDispatcher, ItemRenderer itemRenderer) {
        double distance = entityRenderDispatcher.distanceToSqr(entity);
        if (ForgeHooksClient.isNameplateInRenderDistance(entity, distance)) {
            float height = entity.getBbHeight() + 0.5F;
            poseStack.pushPose();
            poseStack.translate(0.0F, height, 0.0F);
            poseStack.mulPose(entityRenderDispatcher.cameraOrientation());
            poseStack.translate(0.0F, 0.3, 0.0F);
            poseStack.scale(0.5F, 0.5F, 0.1F);
            this.renderItem(itemRenderer, entity.getSummonItem(), LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), entity.getId());
            poseStack.popPose();
        }
    }

    default void renderItem(ItemRenderer itemRenderer, ItemStack itemStack, int combinedLight, int combinedOverlay, PoseStack poseStack, MultiBufferSource buffer, @Nullable Level level, int seed) {
        if (!itemStack.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            for (BakedModel model : itemRenderer.getModel(itemStack, level, null, seed).getRenderPasses(itemStack, true)) {
                itemRenderer.renderModelLists(model, itemStack, combinedLight, combinedOverlay, poseStack, buffer.getBuffer(RenderType.cutout()));
            }
            poseStack.popPose();
        }
    }
}
