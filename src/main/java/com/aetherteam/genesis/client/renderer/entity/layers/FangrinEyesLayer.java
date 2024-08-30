package com.aetherteam.genesis.client.renderer.entity.layers;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.entity.model.FangrinModel;
import com.aetherteam.genesis.entity.companion.Fangrin;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class FangrinEyesLayer extends EyesLayer<Fangrin, FangrinModel> {
    private static final RenderType FANGRIN_GLOW = RenderType.eyes(new ResourceLocation(AetherGenesis.MODID, "textures/entity/companions/fangrin/fangrin_emissive.png"));

    public FangrinEyesLayer(RenderLayerParent<Fangrin, FangrinModel> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public RenderType renderType() {
        return FANGRIN_GLOW;
    }
}
