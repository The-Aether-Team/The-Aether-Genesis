package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.model.KraisithModel;
import com.aetherteam.aether_genesis.entity.companion.Kraisith;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class KraisithRenderer extends CompanionRenderer<Kraisith, KraisithModel> {
    private static final ResourceLocation KRAISITH_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/kraisith/kraisith.png");

    public KraisithRenderer(EntityRendererProvider.Context context) {
        super(context, new KraisithModel(context.bakeLayer(GenesisModelLayers.KRAISITH)), 0.45F);
    }

    @Override
    public ResourceLocation getTextureLocation(Kraisith kraisith) {
        return KRAISITH_TEXTURE;
    }
}
