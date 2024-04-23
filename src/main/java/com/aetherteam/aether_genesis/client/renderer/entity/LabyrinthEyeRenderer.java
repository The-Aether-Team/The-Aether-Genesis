package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.layers.LabyrinthEyeLayer;
import com.aetherteam.aether_genesis.client.renderer.entity.model.LabyrinthEyeModel;
import com.aetherteam.aether_genesis.entity.monster.dungeon.boss.LabyrinthEye;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LabyrinthEyeRenderer extends MobRenderer<LabyrinthEye, LabyrinthEyeModel> {
    private static final ResourceLocation LABYRINTH_EYE_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/labyrinth_eye/labyrinth_eye_sleep.png");
    private static final ResourceLocation LABYRINTH_EYE_AWAKE_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/labyrinth_eye/labyrinth_eye_awake.png");

    public LabyrinthEyeRenderer(EntityRendererProvider.Context context) {
        super(context, new LabyrinthEyeModel(context.bakeLayer(GenesisModelLayers.LABYRINTH_EYE)), 0.5F);
        this.addLayer(new LabyrinthEyeLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(LabyrinthEye labyrinthEye) {
        return labyrinthEye.isAwake() ? LABYRINTH_EYE_AWAKE_TEXTURE : LABYRINTH_EYE_TEXTURE;
    }
}
