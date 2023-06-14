package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.model.LabyrinthEyeModel;
import com.aetherteam.aether_genesis.entity.monster.boss.LabyrinthEye;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class LabyrinthEyeRenderer extends MobRenderer<LabyrinthEye, LabyrinthEyeModel<LabyrinthEye>> {
    private static final ResourceLocation LABYRINTH_EYE_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/labyrinth_eye/labyrinth_eye_sleep.png");
    private static final ResourceLocation LABYRINTH_EYE_TEXTURE_LIT = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/labyrinth_eye/labyrinth_eye_awake.png");

    public LabyrinthEyeRenderer(EntityRendererProvider.Context context) {
        super(context, new LabyrinthEyeModel<>(context.bakeLayer(GenesisModelLayers.LABYRINTH_EYE)), 0.5F);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull LabyrinthEye labyrinthEye) {
        return labyrinthEye.isAwake() ? LABYRINTH_EYE_TEXTURE_LIT : LABYRINTH_EYE_TEXTURE;
    }
}
