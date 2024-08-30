package com.aetherteam.genesis.client.renderer.entity.model;

import com.aetherteam.genesis.entity.companion.FrostpineTotem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class FrostpineTotemModel extends EntityModel<FrostpineTotem> {
	private final ModelPart totem;

	public FrostpineTotemModel(ModelPart root) {
		this.totem = root.getChild("totem");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition totem = partDefinition.addOrReplaceChild("totem", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-3.0F, -16.0F, -3.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 24).addBox(-5.0F, -12.0F, -2.0F, 10.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(38, 26).addBox(2.0F, -12.0F, -4.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(28, 26).addBox(-5.0F, -12.0F, -4.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 16).addBox(-4.0F, -14.0F, 1.5F, 8.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 8).addBox(-3.0F, -20.0F, -2.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-8.0F, -24.0F, 0.0F, 16.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 33).addBox(-4.0F, -7.0F, -3.0F, 8.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(30, 39).addBox(-1.0F, -6.0F, 4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 44).addBox(-3.5F, -4.0F, -4.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 44).addBox(1.5F, -4.0F, -4.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 49).addBox(-2.0F, -3.0F, -2.5F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		totem.addOrReplaceChild("top_head", CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, -3.0F, -5.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.0F, -1.0F, 0.7854F, 0.0F, 0.0F));
		totem.addOrReplaceChild("top_tail", CubeListBuilder.create().texOffs(34, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -17.0F, 2.0F, -0.6109F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public void setupAnim(FrostpineTotem totem, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) { }

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.totem.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}