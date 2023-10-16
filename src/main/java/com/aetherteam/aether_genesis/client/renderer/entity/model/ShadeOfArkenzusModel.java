package com.aetherteam.aether_genesis.client.renderer.entity.model;

import com.aetherteam.aether_genesis.entity.companion.ShadeOfArkenzus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ShadeOfArkenzusModel extends EntityModel<ShadeOfArkenzus> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightWing;
	private final ModelPart leftWing;

	public ShadeOfArkenzusModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.rightArm = this.body.getChild("arm_right");
		this.leftArm = this.body.getChild("arm_left");
		this.rightLeg = this.body.getChild("leg_right");
		this.leftLeg = this.body.getChild("leg_left");
		this.rightWing = this.body.getChild("wing_right_offset");
		this.leftWing = this.body.getChild("wing_left_offset");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, 0.0F, 0.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-3.0F, 0.0F, 0.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offset(1.0F, 12.0F, -1.0F));

		body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 24).addBox(-2.0F, -3.9F, -1.9F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(-1.0F, 0.0F, 1.0F));

		body.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(16, 16).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, 1.0F));
		body.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(24, 16).addBox(0.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, 1.0F));
		body.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 6.0F, 1.0F));
		body.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(8, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 1.0F));

		PartDefinition wingRightOffset = body.addOrReplaceChild("wing_right_offset", CubeListBuilder.create(), PartPose.offset(-2.0F, 2.0F, 2.0F));
		wingRightOffset.addOrReplaceChild("wing_right", CubeListBuilder.create().texOffs(16, 20).addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition wingLeftOffset = body.addOrReplaceChild("wing_left_offset", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 2.0F));
		wingLeftOffset.addOrReplaceChild("wing_left", CubeListBuilder.create().texOffs(16, 20).mirror().addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshDefinition, 32, 32);
	}

	@Override
	public void setupAnim(ShadeOfArkenzus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		float f = 1.0F;

		this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		this.rightArm.zRot = 0.0F;
		this.leftArm.zRot = 0.0F;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
		this.rightLeg.yRot = 0.005F;
		this.leftLeg.yRot = -0.005F;
		this.rightLeg.zRot = 0.005F;
		this.leftLeg.zRot = -0.005F;

		this.rightArm.yRot = 0.0F;
		this.leftArm.yRot = 0.0F;

		float sinage = ageInTicks;
		if (!entity.isOnGround()) {
			sinage *= 0.75F;
		} else {
			sinage *= 0.15F;
		}
		float targetYRot = Mth.sin(sinage) / 6.0F + 0.25F;
		float targetZRot = Mth.cos(sinage) / (entity.isOnGround() ? 8.0F : 3.0F) - 0.125F;
		this.leftWing.yRot = targetYRot;
		this.leftWing.zRot = targetZRot;
		this.rightWing.yRot = -targetYRot;
		this.rightWing.zRot = -targetZRot;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}