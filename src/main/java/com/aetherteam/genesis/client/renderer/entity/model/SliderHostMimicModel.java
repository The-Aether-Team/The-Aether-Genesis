package com.aetherteam.genesis.client.renderer.entity.model;

import com.aetherteam.genesis.entity.monster.dungeon.boss.SliderHostMimic;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SliderHostMimicModel extends EntityModel<SliderHostMimic> {
	private final ModelPart mimic;
	private final ModelPart body;
	private final ModelPart frontLeftLeg;
	private final ModelPart frontRightLeg;
	private final ModelPart backRightLeg;
	private final ModelPart backLeftLeg;

	public SliderHostMimicModel(ModelPart root) {
		this.mimic = root.getChild("mimic");
		this.body = root.getChild("body");
		this.frontLeftLeg = this.body.getChild("leg_front_left");
		this.frontRightLeg = this.body.getChild("leg_front_right");
		this.backRightLeg = this.body.getChild("leg_back_right");
		this.backLeftLeg = this.body.getChild("leg_back_left");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		partDefinition.addOrReplaceChild("mimic", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -32.0F, -16.0F, 32.0F, 32.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 160).addBox(-16.0F, -16.0F, -17.0F, 32.0F, 32.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(0, 118).addBox(-16.0F, 6.0F, -17.0F, 32.0F, 10.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(0, 42).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 12.0F, 20.0F, new CubeDeformation(0.0F))
				.texOffs(0, 74).addBox(-16.0F, -6.0F, -17.0F, 10.0F, 12.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(84, 74).addBox(6.0F, -6.0F, -17.0F, 10.0F, 12.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-16.0F, -16.0F, -17.0F, 32.0F, 10.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 1.0F));

		body.addOrReplaceChild("leg_front_left", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, 16.0F, -14.0F));
		body.addOrReplaceChild("leg_front_right", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, 16.0F, -14.0F));
		body.addOrReplaceChild("leg_back_right", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, 16.0F, 12.0F));
		body.addOrReplaceChild("leg_back_left", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, 16.0F, 12.0F));

		return LayerDefinition.create(meshDefinition, 256, 256);
	}

	@Override
	public void setupAnim(SliderHostMimic hostMimic, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (hostMimic.isAwake()) {
			this.frontRightLeg.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 0.6F * limbSwingAmount;
			this.backRightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount;
			this.frontLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount;
			this.backLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 0.6F * limbSwingAmount;
			this.body.visible = true;
			this.mimic.visible = false;
		} else {
			this.body.visible = false;
			this.mimic.visible = true;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.mimic.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}