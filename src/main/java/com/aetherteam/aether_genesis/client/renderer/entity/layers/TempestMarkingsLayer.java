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
public class TempestMarkingsLayer<T extends Tempest> extends EyesLayer<T, TempestModel<T>> {
    private static final RenderType TEMPEST_EYES = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tempest/tempest_emissive.png"));

    public TempestMarkingsLayer(RenderLayerParent<T, TempestModel<T>> p_117342_) {
        super(p_117342_);
    }

    public RenderType renderType() {
        return TEMPEST_EYES;
    }
}