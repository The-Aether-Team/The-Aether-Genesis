package com.aetherteam.genesis.client.renderer.entity.model;

import com.aetherteam.genesis.client.renderer.entity.AbstractGolemModel;
import com.aetherteam.genesis.entity.monster.dungeon.TrackingGolem;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class TrackingGolemModel extends AbstractGolemModel<TrackingGolem> {
    public TrackingGolemModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setupAnim(TrackingGolem golem, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.rightArm.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftArm.xRot = -Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * limbSwingAmount;
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * limbSwingAmount;
    }
}
