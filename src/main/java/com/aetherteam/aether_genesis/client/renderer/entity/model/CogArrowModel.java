package com.aetherteam.aether_genesis.client.renderer.entity.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.aetherteam.aether_genesis.entity.miscellaneous.CogArrow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CogArrowModel<T extends CogArrow> extends EntityModel<T> {
	public final ModelPart Cog;

	public CogArrowModel(ModelPart root) {
		this.Cog = root.getChild("Cog");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Cog = partdefinition.addOrReplaceChild("Cog", CubeListBuilder.create(), PartPose.offset(0.5F, 18.5F, 0.0F));

		PartDefinition cogPart = Cog.addOrReplaceChild("cogPart", CubeListBuilder.create(), PartPose.offset(-1.5F, 5.5F, 0.0F));

		PartDefinition cogPartDouble = cogPart.addOrReplaceChild("cogPartDouble", CubeListBuilder.create().texOffs(0, 0).addBox(4.1213F, -7.5355F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.1213F, -7.5355F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(2.1213F, -6.5355F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cogPartSingle = cogPart.addOrReplaceChild("cogPartSingle", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -9.2426F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-1.0F, -8.2426F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cogPart2 = Cog.addOrReplaceChild("cogPart2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5F, 5.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cogPartDouble2 = cogPart2.addOrReplaceChild("cogPartDouble2", CubeListBuilder.create().texOffs(0, 0).addBox(3.4142F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.4142F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(1.4142F, 0.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cogPartSingle2 = cogPart2.addOrReplaceChild("cogPartSingle2", CubeListBuilder.create().texOffs(0, 0).addBox(4.1213F, -4.1213F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(3.1213F, -3.1213F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cogPart3 = Cog.addOrReplaceChild("cogPart3", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5F, 5.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cogPartDouble3 = cogPart3.addOrReplaceChild("cogPartDouble3", CubeListBuilder.create().texOffs(0, 0).addBox(-3.1213F, -1.7071F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.1213F, -1.7071F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-5.1213F, -0.7071F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cogPartSingle3 = cogPart3.addOrReplaceChild("cogPartSingle3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-2.0F, 1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cogPart4 = Cog.addOrReplaceChild("cogPart4", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5F, 5.5F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cogPartDouble4 = cogPart4.addOrReplaceChild("cogPartDouble4", CubeListBuilder.create().texOffs(0, 0).addBox(-2.4142F, -8.2426F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.4142F, -8.2426F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-4.4142F, -7.2426F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cogPartSingle4 = cogPart4.addOrReplaceChild("cogPartSingle4", CubeListBuilder.create().texOffs(0, 0).addBox(-5.1213F, -5.1213F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-6.1213F, -4.1213F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(CogArrow entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Cog.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.Cog.yRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.Cog.zRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Cog.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}