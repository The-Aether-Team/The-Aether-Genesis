package com.aetherteam.aether_genesis.client.renderer.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.aetherteam.aether_genesis.entity.monster.boss.SentryGuardian;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SentryGuardianModel<T extends SentryGuardian> extends EntityModel<T> {

	private final ModelPart leftarm;
	private final ModelPart rightarm;
	private final ModelPart body;

	public SentryGuardianModel(ModelPart root) {
		this.leftarm = root.getChild("leftarm");
		this.rightarm = root.getChild("rightarm");
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create(), PartPose.offset(18.0F, -1.0F, 0.0F));

		PartDefinition cube_r1 = leftarm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 90).addBox(9.0F, -15.5F, -23.0F, 10.0F, 18.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.0F, 25.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create(), PartPose.offset(-18.0F, -1.0F, 0.0F));

		PartDefinition cube_r2 = rightarm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 90).mirror().addBox(-19.0F, -15.5F, -23.0F, 10.0F, 18.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(18.0F, 25.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 42).addBox(-7.0F, -19.0F, -7.0F, 14.0F, 6.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(32, 4).addBox(-9.0F, -39.0F, -9.0F, 18.0F, 20.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(42, 48).addBox(-7.0F, -47.0F, -7.0F, 14.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(14.0F, -15.0F, -45.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0436F, 2.4607F, -18.0F, 0.0F, -1.5708F, -0.7418F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-27.0F, -41.4607F, -35.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-11.2057F, 15.2137F, -23.0F, 0.0F, 1.5708F, 0.7418F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.leftarm.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightarm.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leftarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}