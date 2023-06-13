package com.aetherteam.aether_genesis.client.renderer.accessory.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class MouseEarCapModel extends HumanoidModel<LivingEntity> {
    public final ModelPart cap;
    public final ModelPart earLeft;
    public final ModelPart earRight;

    public MouseEarCapModel(ModelPart root) {
        super(root);
        this.cap = root.getChild("cap");
        this.earLeft = this.cap.getChild("ear_left");
        this.earRight = this.cap.getChild("ear_right");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshDefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition cap = partDefinition.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, CubeDeformation.NONE.extend(0.55F)), PartPose.ZERO);
        cap.addOrReplaceChild("ear_left", CubeListBuilder.create().texOffs(24, 0).addBox(3.0F, -13.0F, -1.0F, 6.0F, 6.0F, 1.0F, CubeDeformation.NONE.extend(1.0F, 1.0F, 0.15F)), PartPose.ZERO);
        cap.addOrReplaceChild("ear_right", CubeListBuilder.create().texOffs(24, 0).addBox(-9.0F, -13.0F, -1.0F, 6.0F, 6.0F, 1.0F, CubeDeformation.NONE.extend(1.0F, 1.0F, 0.15F)), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.cap.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
