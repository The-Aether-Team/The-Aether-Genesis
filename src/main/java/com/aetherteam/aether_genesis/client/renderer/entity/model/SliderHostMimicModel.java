package com.aetherteam.aether_genesis.client.renderer.entity.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.aetherteam.aether_genesis.entity.monster.boss.SliderHostMimic;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SliderHostMimicModel<T extends SliderHostMimic> extends EntityModel<T> {
	private final ModelPart left_leg1;
	private final ModelPart right_leg1;
	private final ModelPart body;
	private final ModelPart left_leg2;
	private final ModelPart right_leg2;
	private final ModelPart fake_body;

	public SliderHostMimicModel(ModelPart root) {
		this.left_leg1 = root.getChild("left_leg1");
		this.right_leg1 = root.getChild("right_leg1");
		this.body = root.getChild("body");
		this.left_leg2 = root.getChild("left_leg2");
		this.right_leg2 = root.getChild("right_leg2");
		this.fake_body = root.getChild("fake_body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("left_leg1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, 13.0F, -13.0F));
		partdefinition.addOrReplaceChild("right_leg1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -1.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-13.0F, 13.0F, -13.0F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -44.0F, -16.0F, 32.0F, 32.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		partdefinition.addOrReplaceChild("fake_body", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -32.0F, -16.0F, 32.0F, 32.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -1.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(13.0F, 13.0F, 13.0F));
		partdefinition.addOrReplaceChild("right_leg2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, 13.0F, 13.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(SliderHostMimic entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.body.visible = entity.isAwake();
		this.fake_body.visible = !entity.isAwake();
		this.left_leg1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg1.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg2.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_leg2.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		left_leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		fake_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}