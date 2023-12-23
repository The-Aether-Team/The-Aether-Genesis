package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.model.ZephyrooModel;
import com.aetherteam.aether_genesis.entity.passive.Zephyroo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ZephyrooRenderer extends MobRenderer<Zephyroo, ZephyrooModel<Zephyroo>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/zephyroo/zephyroo.png");
    public ZephyrooRenderer(EntityRendererProvider.Context context) {
        super(context, new ZephyrooModel<>(context.bakeLayer(GenesisModelLayers.ZEPHYROO)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Zephyroo entity) {
        return TEXTURE;
    }
}
