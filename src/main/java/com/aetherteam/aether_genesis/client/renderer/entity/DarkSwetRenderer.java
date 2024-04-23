package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether.client.renderer.entity.SwetRenderer;
import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DarkSwetRenderer extends SwetRenderer {
    private static final ResourceLocation DARK_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/swet/swet_dark.png");

    public DarkSwetRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Swet swet) {
        return DARK_TEXTURE;
    }
}
