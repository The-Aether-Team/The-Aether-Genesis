package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.entity.miscellaneous.CogArrow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

//todo FIX THIS
public class CogArrowRenderer extends EntityRenderer<CogArrow> {
    private static final ResourceLocation COG_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/projectile/cog.png");
    private final ModelPart cogArrow;

    public CogArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.cogArrow = context.bakeLayer(GenesisModelLayers.COG_ARROW);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull CogArrow cogArrow) {
        return COG_TEXTURE;
    }
}
