package com.aetherteam.genesis.client.renderer.entity.layers;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.entity.model.KraisithModel;
import com.aetherteam.genesis.entity.companion.Kraisith;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class KraisithEyesLayer extends EyesLayer<Kraisith, KraisithModel> {
    private static final RenderType KRAISITH_GLOW = RenderType.eyes(new ResourceLocation(AetherGenesis.MODID, "textures/entity/companions/kraisith/kraisith_emissive.png"));

    public KraisithEyesLayer(RenderLayerParent<Kraisith, KraisithModel> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public RenderType renderType() {
        return KRAISITH_GLOW;
    }
}
