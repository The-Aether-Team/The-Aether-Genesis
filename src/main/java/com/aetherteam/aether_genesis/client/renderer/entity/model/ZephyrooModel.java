package com.aetherteam.aether_genesis.client.renderer.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ZephyrooModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "zephyroo"), "main");

	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart rightArm;
	public final ModelPart leftArm;
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;

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
		this.head.yRot = netHeadYaw * Mth.PI / 180;
		this.head.xRot = headPitch * Mth.PI / 180;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}