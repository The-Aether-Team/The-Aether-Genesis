package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.aether.client.renderer.entity.SwetRenderer;
import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.entity.companion.BabyPinkSwet;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabyPinkSwetRenderer extends SwetRenderer implements DisplayItemPlate<BabyPinkSwet> {
    private static final ResourceLocation BABY_PINK_SWET_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/baby_pink_swet.png");
    protected final ItemRenderer itemRenderer;

    public BabyPinkSwetRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(Swet swet, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(swet, entityYaw, partialTicks, poseStack, buffer, packedLight);
        if (this.shouldShowName(swet) && swet instanceof BabyPinkSwet babyPinkSwet) {
            this.renderDisplayItem(babyPinkSwet, poseStack, buffer, this.entityRenderDispatcher, this.itemRenderer);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Swet swet) {
        return BABY_PINK_SWET_TEXTURE;
    }
}
