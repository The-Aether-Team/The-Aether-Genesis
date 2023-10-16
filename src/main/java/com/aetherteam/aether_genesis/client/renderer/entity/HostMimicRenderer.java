package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.layers.HostMimicLayer;
import com.aetherteam.aether_genesis.client.renderer.entity.model.SliderHostMimicModel;
import com.aetherteam.aether_genesis.entity.monster.boss.SliderHostMimic;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class HostMimicRenderer extends MobRenderer<SliderHostMimic, SliderHostMimicModel> {
    private static final ResourceLocation HOST_MIMIC_ASLEEP_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/slider_host_mimic/slider_host_mimic_asleep.png");
    private static final ResourceLocation HOST_MIMIC_AWAKE_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/slider_host_mimic/slider_host_mimic_critical.png");

    public HostMimicRenderer(EntityRendererProvider.Context context) {
        super(context, new SliderHostMimicModel(context.bakeLayer(GenesisModelLayers.SLIDER_HOST_MIMIC)), 0.5F);
        this.addLayer(new HostMimicLayer(this));
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull SliderHostMimic sentryGuardian) {
        return sentryGuardian.isAwake() ? HOST_MIMIC_AWAKE_TEXTURE : HOST_MIMIC_ASLEEP_TEXTURE;
    }
}
