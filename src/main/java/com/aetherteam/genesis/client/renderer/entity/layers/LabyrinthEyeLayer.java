package com.aetherteam.genesis.client.renderer.entity.layers;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.client.renderer.entity.model.LabyrinthEyeModel;
import com.aetherteam.genesis.entity.monster.dungeon.boss.LabyrinthEye;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class LabyrinthEyeLayer extends EyesLayer<LabyrinthEye, LabyrinthEyeModel> {
    private static final RenderType LABYRINTH_EYE_ASLEEP_GLOW = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/labyrinth_eye/labyrinth_eye_sleep_glow.png"));
    private static final RenderType LABYRINTH_EYE_AWAKE_GLOW = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/labyrinth_eye/labyrinth_eye_awake_glow.png"));

    public LabyrinthEyeLayer(RenderLayerParent<LabyrinthEye, LabyrinthEyeModel> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, LabyrinthEye eye, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer consumer = buffer.getBuffer(this.renderType(eye));
        this.getParentModel().renderToBuffer(poseStack, consumer, LightTexture.FULL_SKY, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    
    public RenderType renderType(LabyrinthEye eye) {
        if (eye.isAwake()) {
            return LABYRINTH_EYE_AWAKE_GLOW;
        }
        return this.renderType();
    }
    
    @Override
    public RenderType renderType() {
        return LABYRINTH_EYE_ASLEEP_GLOW;
    }
}