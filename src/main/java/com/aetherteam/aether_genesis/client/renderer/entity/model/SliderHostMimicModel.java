package com.aetherteam.aether_genesis.client.renderer.entity.model;

import com.aetherteam.aether_genesis.entity.monster.dungeon.boss.SliderHostMimic;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SliderHostMimicModel extends EntityModel<SliderHostMimic> {
	private final ModelPart mimic;
	private final ModelPart body;

	public SliderHostMimicModel(ModelPart root) {
		this.mimic = root.getChild("mimic");
		this.body = root.getChild("body");
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