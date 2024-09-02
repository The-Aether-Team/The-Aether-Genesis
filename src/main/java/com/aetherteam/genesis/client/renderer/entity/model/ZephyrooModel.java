package com.aetherteam.genesis.client.renderer.entity.model;

import com.aetherteam.genesis.entity.passive.Zephyroo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ZephyrooModel<T extends Zephyroo> extends EntityModel<T> {
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart rightArm;
	public final ModelPart leftArm;
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;
	private float jumpRotation;

	private float headXRot = 0;

	public ZephyrooModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = body.getChild("head");
		this.rightArm = body.getChild("right_arm");
		this.leftArm = body.getChild("left_arm");
		this.rightLeg = body.getChild("right_leg");
		this.leftLeg = body.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -10.0F, 8.0F, 9.0F, 18.0F, new CubeDeformation(0.0F))
		// Waist
		.texOffs(0, 27).addBox(-7.0F, -7.5F, -2.0F, 14.0F, 9.0F, 9.0F, new CubeDeformation(0.0F))
		// Tail
		.texOffs(32, 31).addBox(0.0F, -7.0F, 7.0F, 1.0F, 15.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.5F, 1.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(10, 8).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -9.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -9.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(34, 0).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, 0.5F, -7.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -11.0F));

		PartDefinition ear_left = head.addOrReplaceChild("ear_left", CubeListBuilder.create().texOffs(0, 45).mirror().addBox(-1.0F, -5.0F, 0.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, -4.0F, -0.5F, 0.0F, 0.0F, 0.3054F));

		PartDefinition ear_right = head.addOrReplaceChild("ear_right", CubeListBuilder.create().texOffs(0, 45).addBox(-2.0F, -5.0F, 0.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -4.0F, -0.5F, 0.0F, 0.0F, -0.3054F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(22, 45).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 45).addBox(-1.5F, 6.0F, -8.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 1.5F, 2.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(44, 19).addBox(-1.5F, 6.0F, -8.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 1.5F, 2.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T zephyroo, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = ageInTicks - (float) zephyroo.tickCount;
		this.head.yRot = netHeadYaw * (Mth.PI / 180);
		this.head.xRot = headPitch * (Mth.PI / 180) * 0.2F;
		this.head.xRot += this.headXRot;
		this.jumpRotation = Mth.sin(zephyroo.getJumpCompletion(f) * 3.1415927F);
		this.leftLeg.xRot = (this.jumpRotation * 50.0F) * Mth.PI / 180;
		this.rightLeg.xRot = (this.jumpRotation * 50.0F) * Mth.PI / 180;
		this.leftArm.xRot = (this.jumpRotation * 40.0F + 5.0F) * (Mth.PI / 180) * 0.5F;
		this.rightArm.xRot = (this.jumpRotation * 40.0F + 5.0F) * (Mth.PI / 180) * 0.5F;
	}

	@Override
	public void prepareMobModel(T zephyroo, float pLimbSwing, float pLimbSwingAmount, float pPartialTick) {
		this.headXRot = zephyroo.getHeadEatAngleScale(pPartialTick);
		this.jumpRotation = Mth.sin(zephyroo.getJumpCompletion(pPartialTick) * 3.1415927F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (this.young) {
			poseStack.pushPose();
			poseStack.scale(0.65F, 0.65F, 0.65F);
			poseStack.translate(0.0F, 0.8F, 0.0F);
			this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
			poseStack.popPose();
		} else {
			poseStack.pushPose();
			poseStack.scale(1.15F, 1.15F, 1.15F);
			poseStack.translate(0.0F, -0.2F, 0.0F);
			this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
			poseStack.popPose();
		}
	}
}