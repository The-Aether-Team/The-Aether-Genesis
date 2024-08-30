package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.model.ZephyrooModel;
import com.aetherteam.genesis.entity.passive.Zephyroo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ZephyrooRenderer extends MobRenderer<Zephyroo, ZephyrooModel<Zephyroo>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/zephyroo/zephyroo.png");
    public ZephyrooRenderer(EntityRendererProvider.Context context) {
        super(context, new ZephyrooModel<>(context.bakeLayer(GenesisModelLayers.ZEPHYROO)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Zephyroo entity) {
        return TEXTURE;
    }
}
