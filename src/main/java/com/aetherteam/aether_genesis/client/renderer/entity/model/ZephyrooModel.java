package com.aetherteam.aether_genesis.client.renderer.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ZephyrooModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "zephyroo"), "main");
	private final ModelPart body;

	public ZephyrooModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -10.0F, 8.0F, 9.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 27).addBox(-7.0F, -7.5F, -2.0F, 14.0F, 9.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(32, 31).addBox(0.0F, -7.0F, 7.0F, 1.0F, 15.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.5F, 1.0F));

		PartDefinition right_arm_r1 = body.addOrReplaceChild("right_arm_r1", CubeListBuilder.create().texOffs(10, 8).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -9.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition left_arm_r1 = body.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -9.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(34, 0).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, 0.5F, -7.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -11.0F));

		PartDefinition ear_left_r1 = head.addOrReplaceChild("ear_left_r1", CubeListBuilder.create().texOffs(0, 45).mirror().addBox(-1.0F, -5.0F, 0.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, -4.0F, -0.5F, 0.0F, 0.0F, 0.3054F));

		PartDefinition ear_right_r1 = head.addOrReplaceChild("ear_right_r1", CubeListBuilder.create().texOffs(0, 45).addBox(-2.0F, -5.0F, 0.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -4.0F, -0.5F, 0.0F, 0.0F, -0.3054F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(22, 45).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 45).addBox(-1.5F, 6.0F, -8.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 1.5F, 2.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(44, 19).addBox(-1.5F, 6.0F, -8.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 1.5F, 2.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}