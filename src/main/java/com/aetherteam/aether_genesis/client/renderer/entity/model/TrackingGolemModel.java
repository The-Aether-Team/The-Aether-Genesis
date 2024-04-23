package com.aetherteam.aether_genesis.client.renderer.entity.model;

import com.aetherteam.aether_genesis.entity.monster.dungeon.TrackingGolem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class TrackingGolemModel extends EntityModel<TrackingGolem> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public TrackingGolemModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.leftLeg = root.getChild("left_leg_joint");
        this.rightLeg = root.getChild("right_leg_joint");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -8.0F, -4.5F, 11.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 50).addBox(-6.0F, -16.0F, -4.5F, 12.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(-8.0F, -25.0F, -5.5F, 16.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(54, 17).mirror().addBox(8.0F, 8.0F, -3.5F, 5.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(40, 3).mirror().addBox(8.0F, 0.0F, -3.0F, 4.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.0F, 0.0F));

        partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(54, 17).addBox(-13.0F, 7.0F, -3.5F, 5.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(40, 3).addBox(-12.0F, -1.0F, -3.0F, 4.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftLegJoint = partDefinition.addOrReplaceChild("left_leg_joint", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));
        leftLegJoint.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(42, 47).mirror().addBox(0.0F, -11.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition rightLegJoint = partDefinition.addOrReplaceChild("right_leg_joint", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));
        rightLegJoint.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(42, 47).addBox(-2.5F, -5.5F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 5.5F, 0.0F, 0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshDefinition, 128, 64);
    }

    @Override
    public void setupAnim(TrackingGolem golem, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.rightArm.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftArm.xRot = -Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}