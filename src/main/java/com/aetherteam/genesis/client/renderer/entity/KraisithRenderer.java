package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.layers.KraisithEyesLayer;
import com.aetherteam.genesis.client.renderer.entity.model.KraisithModel;
import com.aetherteam.genesis.entity.companion.Kraisith;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class KraisithRenderer extends CompanionRenderer<Kraisith, KraisithModel> {
    private static final ResourceLocation KRAISITH_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/companions/kraisith/kraisith.png");

    public KraisithRenderer(EntityRendererProvider.Context context) {
        super(context, new KraisithModel(context.bakeLayer(GenesisModelLayers.KRAISITH)), 0.45F);
        this.addLayer(new KraisithEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Kraisith kraisith) {
        return KRAISITH_TEXTURE;
    }
}
