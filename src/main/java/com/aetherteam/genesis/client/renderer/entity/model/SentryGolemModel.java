package com.aetherteam.genesis.client.renderer.entity.model;

import com.aetherteam.genesis.entity.monster.dungeon.SentryGolem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SentryGolemModel extends EntityModel<SentryGolem> {
    protected final ModelPart body;
    protected final ModelPart head;
    protected final ModelPart leftArm;
    protected final ModelPart rightArm;
    protected final ModelPart leftLeg;
    protected final ModelPart rightLeg;

    public byte armState = 2;
    float[] armsAngles = new float[] { 1.0F, 1.0F, 0.5F, 0.5F };

    public SentryGolemModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.leftArm = this.body.getChild("left_arm");
        this.rightArm = this.body.getChild("right_arm");
        this.leftLeg = this.body.getChild("left_leg");
        this.rightLeg = this.body.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 50).addBox(-6.0F, 1.25F, -4.5F, 12.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(-8.0F, -8.75F, -5.5F, 16.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.75F, 0.0F));

        body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -6.0F, -4.5F, 11.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.75F, -0.5F));

        body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(54, 17).addBox(-5.0F, 5.0F, -3.5F, 5.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(40, 3).addBox(-4.0F, -3.0F, -3.0F, 4.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -4.75F, 0.0F));

        body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(54, 17).mirror().addBox(0.0F, 5.0F, -3.5F, 5.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(40, 3).mirror().addBox(0.0F, -3.0F, -3.0F, 4.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, -4.75F, 0.0F));

        body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(42, 47).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 6.25F, 0.0F));

        body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(42, 47).mirror().addBox(-2.5F, 0.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.5F, 6.25F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void setupAnim(SentryGolem golem, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * limbSwingAmount;

        this.rightArm.resetPose();
        this.leftArm.resetPose();

        this.armState = golem.getHandState();
        if (golem.progress < this.armsAngles[this.armState]) {
            golem.progress += 0.06F;
        }

        if (golem.progress > this.armsAngles[this.armState]) {
            golem.progress -= 0.06F;
        }

        this.rightArm.xRot = -3.0F * golem.progress;
        this.leftArm.xRot = -3.0F * golem.progress;
        ModelPart part = this.rightArm;
        part.yRot -= 0.3F * golem.progress;
        part = this.leftArm;
        part.yRot += 0.3F * golem.progress;
        part = this.rightArm;
        part.zRot += 0.3F * golem.progress;
        part = this.leftArm;
        part.zRot -= 0.3F * golem.progress;

        AnimationUtils.bobModelPart(this.rightArm, ageInTicks, 1.0F);
        AnimationUtils.bobModelPart(this.leftArm, ageInTicks, -1.0F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
