package com.aetherteam.aether_genesis.client.renderer.entity.layers;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.entity.model.KraisithModel;
import com.aetherteam.aether_genesis.entity.companion.Kraisith;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class KraisithEyesLayer extends EyesLayer<Kraisith, KraisithModel> {
    private static final RenderType KRAISITH_GLOW = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/companions/kraisith/kraisith_emissive.png"));

    public KraisithEyesLayer(RenderLayerParent<Kraisith, KraisithModel> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public RenderType renderType() {
        return KRAISITH_GLOW;
    }
}
