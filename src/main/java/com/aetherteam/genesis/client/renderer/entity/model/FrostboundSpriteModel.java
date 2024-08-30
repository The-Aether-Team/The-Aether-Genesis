package com.aetherteam.genesis.client.renderer.entity.model;

import com.aetherteam.genesis.entity.companion.FrostboundSprite;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class FrostboundSpriteModel extends EntityModel<FrostboundSprite> {
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;

	public FrostboundSpriteModel(ModelPart root) {
		this.body = root.getChild("body");
		this.leftArm = this.body.getChild("arm_left");
		this.rightArm = this.body.getChild("arm_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 31).addBox(-3.0F, -7.0F, -2.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 20).addBox(-5.0F, -12.0F, -3.0F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(32, 21).addBox(-7.0F, -11.5F, 0.25F, 14.0F, 9.0F, 1.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 22.0F, 0.0F));
		body.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(16, 40).addBox(1.0F, 0.5F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -10.0F, 0.0F));
		body.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(0, 40).addBox(-5.0F, 0.5F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -10.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -3.5F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(30, 0).addBox(-4.0F, -6.75F, -3.5F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("head_detail_right_r1", CubeListBuilder.create().texOffs(14, 14).addBox(-3.0F, -3.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -3.0F, -0.5F, 0.0F, 0.3491F, 0.0F));
		head.addOrReplaceChild("head_detail_left_r1", CubeListBuilder.create().texOffs(22, 14).addBox(0.0F, -3.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.0F, -0.5F, 0.0F, -0.3491F, 0.0F));
		head.addOrReplaceChild("head_detail_top_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.4363F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(FrostboundSprite frostboundSprite, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float leftArmRotX = Mth.cos(limbSwing * 0.6662F + 3.141593F) * 0.5F * limbSwingAmount;
		float rightArmRotX = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
		this.leftArm.yRot = (float)(Math.sin((double)(ageInTicks * 20.0F) / 25.2957795) * 3.5) / 20.0F;
		this.rightArm.yRot = (float)(Math.cos((double)(ageInTicks * 20.0F) / 25.2957795) * 3.5) / 20.0F;
		this.leftArm.xRot = leftArmRotX;
		this.rightArm.xRot = rightArmRotX;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}