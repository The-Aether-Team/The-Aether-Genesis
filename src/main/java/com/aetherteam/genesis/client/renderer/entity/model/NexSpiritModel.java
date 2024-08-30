package com.aetherteam.genesis.client.renderer.entity.model;

import com.aetherteam.genesis.entity.companion.NexSpirit;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class NexSpiritModel extends EntityModel<NexSpirit> {
	private final ModelPart body;
	private final ModelPart tails;
	private final ModelPart tails_r1;

	public NexSpiritModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tails = this.body.getChild("tails");
		this.tails_r1 = this.tails.getChild("tails_r1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -8.0F, -0.5F, 12.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -2.0F));
		body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -6.5F, -5.0F, 8.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 51).addBox(-3.5F, -6.0F, -6.0F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 3.0F));
		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(29, 46).addBox(-5.0F, -4.0F, -7.0F, 10.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 4.0F));
		arms.addOrReplaceChild("arm_right_r1", CubeListBuilder.create().texOffs(31, 7).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.0873F, 0.0873F, 0.0F));
		arms.addOrReplaceChild("arm_left_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0873F, -0.0873F, 0.0F));
		PartDefinition tails = body.addOrReplaceChild("tails", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, -2.0F));
		tails.addOrReplaceChild("tails_r1", CubeListBuilder.create().texOffs(29, 29).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 2.0F, 0.1745F, 0.0F, 0.0F));
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public void setupAnim(NexSpirit entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float scale = Mth.TWO_PI;
		float offset = -3.45835F;
		this.tails_r1.xRot = ((scale * Mth.sin(ageInTicks - (Mth.cos(ageInTicks + offset) / 3) + Mth.HALF_PI + offset)) + scale) * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}