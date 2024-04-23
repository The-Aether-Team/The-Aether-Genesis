package com.aetherteam.aether_genesis.client.renderer.entity.model;

import com.aetherteam.aether_genesis.entity.companion.Fangrin;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class FangrinModel extends EntityModel<Fangrin> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftBackLeg;
	private final ModelPart rightBackLeg;
	private final ModelPart tail;
	private final ModelPart tailEnd;

	public FangrinModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.leftFrontLeg = this.body.getChild("leg_front_left");
		this.rightFrontLeg = this.body.getChild("leg_front_right");
		this.leftBackLeg = this.body.getChild("leg_back_left");
		this.rightBackLeg = this.body.getChild("leg_back_right");
		this.tail = this.body.getChild("tail");
		this.tailEnd = this.tail.getChild("tail_r1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -10.0F, -3.0F, 9.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(0, 18).addBox(-6.0F, -11.0F, -3.5F, 12.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(29, 0).addBox(-5.0F, -10.5F, 3.5F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0F, -2.0F, -5.0F, 8.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -4.0F, 0.1745F, 0.0F, 0.0F));
		head.addOrReplaceChild("ear_right_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -1.25F, -1.0F, 0.0F, 0.0F, -0.8378F));
		head.addOrReplaceChild("ear_left_r1", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, 0.0F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.25F, -1.0F, 0.0F, 0.0F, 0.8378F));
		body.addOrReplaceChild("leg_front_left", CubeListBuilder.create().texOffs(42, 18).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -4.0F, -0.5F));
		body.addOrReplaceChild("leg_front_right", CubeListBuilder.create().texOffs(42, 9).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -4.0F, -0.5F));
		body.addOrReplaceChild("leg_back_left", CubeListBuilder.create().texOffs(42, 35).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -4.0F, 6.5F));
		body.addOrReplaceChild("leg_back_right", CubeListBuilder.create().texOffs(29, 35).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -4.0F, 6.5F));
		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 7.0F));
		tail.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(30, 22).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 0.0F, -0.4363F, 0.0F, 0.0F));
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public void setupAnim(Fangrin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * (float) (Math.PI / 180.0);
		this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0);
		this.body.zRot = Mth.cos(limbSwing * 0.6662F) * 0.15F * limbSwingAmount;
		this.rightBackLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftBackLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.tailEnd.yRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}