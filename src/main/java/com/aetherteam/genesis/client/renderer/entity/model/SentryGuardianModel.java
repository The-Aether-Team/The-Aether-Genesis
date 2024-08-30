package com.aetherteam.genesis.client.renderer.entity.model;

import com.aetherteam.genesis.entity.monster.dungeon.boss.SentryGuardian;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SentryGuardianModel extends EntityModel<SentryGuardian> {
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;

	public SentryGuardianModel(ModelPart root) {
		this.body = root.getChild("body");
		this.leftArm = root.getChild("left_arm_joint");
		this.rightArm = root.getChild("right_arm_joint");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 42).addBox(-7.0F, -19.0F, -7.0F, 14.0F, 6.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(32, 4).addBox(-9.0F, -39.0F, -9.0F, 18.0F, 20.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(42, 48).addBox(-7.0F, -47.0F, -7.0F, 14.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("left_upper_arm", CubeListBuilder.create().texOffs(0, 0).addBox(14.0F, -15.0F, -45.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0436F, 2.4607F, -18.0F, 0.0F, -1.5708F, -0.7418F));

		body.addOrReplaceChild("right_upper_arm", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-27.0F, -41.4607F, -35.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-11.2057F, 15.2137F, -23.0F, 0.0F, 1.5708F, 0.7418F));

		PartDefinition leftArmJoint = partDefinition.addOrReplaceChild("left_arm_joint", CubeListBuilder.create(), PartPose.offset(18.0F, -1.0F, 0.0F));
		leftArmJoint.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 90).addBox(9.0F, -15.5F, -23.0F, 10.0F, 18.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.0F, 25.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rightArmJoint = partDefinition.addOrReplaceChild("right_arm_joint", CubeListBuilder.create(), PartPose.offset(-18.0F, -1.0F, 0.0F));
		rightArmJoint.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 90).mirror().addBox(-19.0F, -15.5F, -23.0F, 10.0F, 18.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(18.0F, 25.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 128, 128);
	}

	@Override
	public void setupAnim(SentryGuardian guardian, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.leftArm.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}