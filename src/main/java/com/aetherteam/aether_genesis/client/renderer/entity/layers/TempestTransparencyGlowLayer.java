package com.aetherteam.aether_genesis.client.renderer.entity.layers;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.entity.model.TempestModel;
import com.aetherteam.aether_genesis.entity.monster.Tempest;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TempestTransparencyGlowLayer extends EyesLayer<Tempest, TempestModel<Tempest>> {
    private static final RenderType LAYER_GLOW = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tempest/tempest_layer_glow.png"));

    private final TempestModel transparency;
    public TempestTransparencyGlowLayer(RenderLayerParent<Tempest, TempestModel<Tempest>> entityRenderer, TempestModel transparencyModel) {
        super(entityRenderer);
        this.transparency = transparencyModel;
    }

    @Override
    public RenderType renderType() {
        return LAYER_GLOW;
    }
}