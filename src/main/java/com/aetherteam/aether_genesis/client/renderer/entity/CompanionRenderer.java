package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.entity.companion.CompanionMob;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;

public abstract class CompanionRenderer<T extends CompanionMob, S extends EntityModel<T>> extends MobRenderer<T, S> implements DisplayItemPlate<CompanionMob> {
    protected final ItemRenderer itemRenderer;

    public CompanionRenderer(EntityRendererProvider.Context context, S model, float shadowRadius) {
        super(context, model, shadowRadius);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(T companion, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        super.render(companion, entityYaw, partialTicks, matrixStack, buffer, packedLight);
        if (this.shouldShowName(companion)) {
            this.renderDisplayItem(companion, matrixStack, buffer, this.entityRenderDispatcher, this.itemRenderer);
        }
    }
}
