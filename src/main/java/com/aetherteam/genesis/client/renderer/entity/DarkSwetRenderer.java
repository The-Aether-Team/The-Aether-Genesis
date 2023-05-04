package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.aether.client.renderer.entity.SwetRenderer;
import com.aetherteam.aether.entity.monster.Swet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class DarkSwetRenderer extends SwetRenderer {
    private static final ResourceLocation DARK_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/swet/swet_dark.png");

    public DarkSwetRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull Swet swet) {
        return DARK_TEXTURE;
    }
}
