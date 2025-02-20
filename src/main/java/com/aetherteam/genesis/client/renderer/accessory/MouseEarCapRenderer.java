package com.aetherteam.genesis.client.renderer.accessory;

import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.accessory.model.MouseEarCapModel;
import com.aetherteam.genesis.item.accessories.miscellaneous.MouseEarCapItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class MouseEarCapRenderer implements AccessoryRenderer {
    private final MouseEarCapModel mouseEarCap;

    public MouseEarCapRenderer() {
        this.mouseEarCap = new MouseEarCapModel(Minecraft.getInstance().getEntityModels().bakeLayer(GenesisModelLayers.MOUSE_EAR_CAP));
    }

    @Override
    public <M extends LivingEntity> void render(ItemStack stack, SlotReference reference, PoseStack matrices, EntityModel<M> model, MultiBufferSource buffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        MouseEarCapItem mouseEarCapItem = (MouseEarCapItem) stack.getItem();
        int color = DyedItemColor.getOrDefault(stack, 10302259);
        if (model instanceof HumanoidModel<M> humanoidModel) {
            AccessoryRenderer.transformToModelPart(matrices, humanoidModel.head);
            matrices.mulPose(Axis.XP.rotationDegrees(180));
            matrices.translate(0, 0.5, 0);
            matrices.scale(2, 2, 2);
        }
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(mouseEarCapItem.getEarsTexture()));
        this.mouseEarCap.renderToBuffer(matrices, consumer, light, LivingEntityRenderer.getOverlayCoords(reference.entity(), 0.0F), color);
    }
}

