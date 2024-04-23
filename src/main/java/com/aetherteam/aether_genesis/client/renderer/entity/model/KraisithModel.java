package com.aetherteam.aether_genesis.client.renderer.entity.model;

import com.aetherteam.aether_genesis.entity.companion.Kraisith;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class KraisithModel extends EntityModel<Kraisith> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftBackLeg;
	private final ModelPart rightBackLeg;

	public KraisithModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.leftFrontLeg = this.body.getChild("leg_front_left");
		this.rightFrontLeg = this.body.getChild("leg_front_right");
		this.leftBackLeg = this.body.getChild("leg_back_left");
		this.rightBackLeg = this.body.getChild("leg_back_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(34, 0).addBox(-4.0F, -10.0F, -1.0F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(34, 16).addBox(-5.0F, -1.0F, -2.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 1.5F));
		arms.addOrReplaceChild("arm_left_r1", CubeListBuilder.create().texOffs(17, 13).addBox(0.0F, -7.0F, -6.5F, 2.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, -1.0F, 0.0F, -0.0873F, 0.0F));
		arms.addOrReplaceChild("arm_right_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -7.0F, -6.5F, 2.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, -1.0F, 0.0F, 0.0873F, 0.0F));
		body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 33).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -1.0F));
		body.addOrReplaceChild("leg_front_right", CubeListBuilder.create().texOffs(28, 38).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -5.0F, -0.5F));
		body.addOrReplaceChild("leg_front_left", CubeListBuilder.create().texOffs(19, 38).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -5.0F, -0.5F));
		body.addOrReplaceChild("leg_back_left", CubeListBuilder.create().texOffs(17, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -5.0F, 5.5F));
		body.addOrReplaceChild("leg_back_right", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -5.0F, 5.5F));
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public void setupAnim(Kraisith entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * (float) (Math.PI / 180.0);
		this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0);
		this.rightBackLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftBackLeg.xRot = Mth.cos(limbSwing * 0.6662F + (Mth.PI * 0.3F)) * 1.4F * limbSwingAmount;
		this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (Mth.PI * 0.6F)) * 1.4F * limbSwingAmount;
		this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (Mth.PI * 0.9F)) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}