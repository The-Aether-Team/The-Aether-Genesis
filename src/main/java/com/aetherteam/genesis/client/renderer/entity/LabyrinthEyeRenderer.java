package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.layers.LabyrinthEyeLayer;
import com.aetherteam.genesis.client.renderer.entity.model.LabyrinthEyeModel;
import com.aetherteam.genesis.entity.monster.dungeon.boss.LabyrinthEye;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LabyrinthEyeRenderer extends MobRenderer<LabyrinthEye, LabyrinthEyeModel> {
    private static final ResourceLocation LABYRINTH_EYE_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/labyrinth_eye/labyrinth_eye_sleep.png");
    private static final ResourceLocation LABYRINTH_EYE_AWAKE_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/labyrinth_eye/labyrinth_eye_awake.png");

    public LabyrinthEyeRenderer(EntityRendererProvider.Context context) {
        super(context, new LabyrinthEyeModel(context.bakeLayer(GenesisModelLayers.LABYRINTH_EYE)), 0.5F);
        this.addLayer(new LabyrinthEyeLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(LabyrinthEye labyrinthEye) {
        return labyrinthEye.isAwake() ? LABYRINTH_EYE_AWAKE_TEXTURE : LABYRINTH_EYE_TEXTURE;
    }
}
