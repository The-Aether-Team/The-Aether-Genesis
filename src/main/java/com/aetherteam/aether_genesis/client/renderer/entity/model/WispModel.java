package com.aetherteam.aether_genesis.client.renderer.entity.model;

import com.aetherteam.aether_genesis.entity.companion.Wisp;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class WispModel extends EntityModel<Wisp> {
	private final ModelPart head;
	private final ModelPart arms;

	public WispModel(ModelPart root) {
		this.head = root.getChild("head");
		this.arms = root.getChild("arms");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		partDefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.5F, -6.0F, -2.5F, 5.0F, 5.0F, 5.0F, CubeDeformation.NONE)
				.texOffs(20, 6).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 3.0F, 1.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 15.0F, 0.0F));
		PartDefinition arms = partDefinition.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));
		arms.addOrReplaceChild("arm_left_bottom_r1", CubeListBuilder.create().texOffs(8, 21).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 10.0F, 1.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));
		arms.addOrReplaceChild("arm_left_top_r1", CubeListBuilder.create().texOffs(8, 10).addBox(-2.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));
		arms.addOrReplaceChild("arm_right_bottom_r1", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, 0.0F, -0.5F, 2.0F, 10.0F, 1.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));
		arms.addOrReplaceChild("arm_right_top_r1", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));
		return LayerDefinition.create(meshDefinition, 32, 32);
	}

	@Override
	public void setupAnim(Wisp wisp, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = (180.0F + Mth.rotLerp(ageInTicks, wisp.yBodyRotO, wisp.yBodyRot)) * Mth.DEG_TO_RAD;
		this.arms.yRot = Mth.rotLerp(ageInTicks, wisp.getArmRotO(), wisp.getArmRot());
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.arms.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}