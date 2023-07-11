package com.aetherteam.aether_genesis.client.renderer.entity.layers;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.entity.model.LabyrinthEyeModel;
import com.aetherteam.aether_genesis.client.renderer.entity.model.SliderHostMimicModel;
import com.aetherteam.aether_genesis.entity.monster.boss.LabyrinthEye;
import com.aetherteam.aether_genesis.entity.monster.boss.SliderHostMimic;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class LabyrinthEyeLayer<T extends LabyrinthEye, M extends LabyrinthEyeModel<T>> extends EyesLayer<T, M> {
    private static final RenderType COG_EYE = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/labyrinth_eye/labyrinth_eye_sleep_glow.png"));
    private static final RenderType COG_EYE_LIT = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/labyrinth_eye/labyrinth_eye_awake_glow.png"));

    public LabyrinthEyeLayer(RenderLayerParent<T, M> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public void render(@Nonnull PoseStack poseStack, MultiBufferSource buffer, int packedLight, @Nonnull LabyrinthEye guardian, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer consumer = buffer.getBuffer(this.renderType(guardian));
        this.getParentModel().renderToBuffer(poseStack, consumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Nonnull
    public RenderType renderType(LabyrinthEye guardian) {
        if (guardian.isAwake()) {
            return COG_EYE_LIT;
        }
        return this.renderType();
    }

    @Nonnull
    @Override
    public RenderType renderType() {
        return COG_EYE;
    }
}