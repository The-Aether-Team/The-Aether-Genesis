package com.aetherteam.genesis.client.renderer.entity.model;

import com.aetherteam.genesis.client.renderer.entity.AbstractGolemModel;
import com.aetherteam.genesis.entity.monster.dungeon.SentryGolem;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class SentryGolemModel extends AbstractGolemModel<SentryGolem> {
    public byte armState = 2;
    float[] armsAngles = new float[] { 1.0F, 1.0F, 0.5F, 0.5F };

    public SentryGolemModel(ModelPart root) {
        super(root);
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
}
