package com.aetherteam.aether_genesis.client.renderer.accessory;

import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.accessory.model.PendantModel;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.accessory.model.MouseEarCapModel;
import com.aetherteam.aether_genesis.item.accessories.MouseEarCapItem;
import com.aetherteam.aether_genesis.mixin.mixins.client.accessor.PlayerModelAccessor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class MouseEarCapRenderer implements ICurioRenderer {
    private final MouseEarCapModel mouseEarCap;

    public MouseEarCapRenderer() {
        this.mouseEarCap = new MouseEarCapModel(Minecraft.getInstance().getEntityModels().bakeLayer(GenesisModelLayers.MOUSE_EAR_CAP));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        MouseEarCapItem mouseEarCapItem = (MouseEarCapItem) stack.getItem();
        int i = mouseEarCapItem.getColor(stack);
        float red = (float) (i >> 16 & 255) / 255.0F;
        float green = (float) (i >> 8 & 255) / 255.0F;
        float blue = (float) (i & 255) / 255.0F;
        ICurioRenderer.followHeadRotations(slotContext.entity(), this.mouseEarCap.cap);
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(mouseEarCapItem.getEarsTexture()));
        this.mouseEarCap.renderToBuffer(poseStack, consumer, light, LivingEntityRenderer.getOverlayCoords(slotContext.entity(), 0.0F), red, green, blue, 1.0F);
    }
}

