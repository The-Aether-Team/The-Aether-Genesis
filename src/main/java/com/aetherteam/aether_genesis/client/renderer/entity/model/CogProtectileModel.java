package com.aetherteam.aether_genesis.client.renderer.entity.model;

import com.aetherteam.aether_genesis.entity.miscellaneous.CogProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CogProtectileModel<T extends CogProjectile> extends EntityModel<T> {
	public final ModelPart cog;

	public CogProtectileModel(ModelPart root) {
		this.cog = root.getChild("cog");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition cog = partDefinition.addOrReplaceChild("cog", CubeListBuilder.create(), PartPose.offset(0.5F, 18.5F, 0.0F));

		PartDefinition cogPart = cog.addOrReplaceChild("cog_part", CubeListBuilder.create(), PartPose.offset(-1.5F, 5.5F, 0.0F));
		cogPart.addOrReplaceChild("cog_part_double", CubeListBuilder.create().texOffs(0, 0).addBox(4.1213F, -7.5355F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.1213F, -7.5355F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 0).addBox(2.1213F, -6.5355F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));
		cogPart.addOrReplaceChild("cog_part_single", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -9.2426F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 0).addBox(-1.0F, -8.2426F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cogPart2 = cog.addOrReplaceChild("cog_part_2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5F, 5.5F, 0.0F, 0.0F, 0.0F, -1.5708F));
		cogPart2.addOrReplaceChild("cog_part_double_2", CubeListBuilder.create().texOffs(0, 0).addBox(3.4142F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.4142F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 0).addBox(1.4142F, 0.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));
		cogPart2.addOrReplaceChild("cog_part_single_2", CubeListBuilder.create().texOffs(0, 0).addBox(4.1213F, -4.1213F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 0).addBox(3.1213F, -3.1213F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cogPart3 = cog.addOrReplaceChild("cog_part_3", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5F, 5.5F, 0.0F, 0.0F, 0.0F, -3.1416F));
		cogPart3.addOrReplaceChild("cog_part_double_3", CubeListBuilder.create().texOffs(0, 0).addBox(-3.1213F, -1.7071F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.1213F, -1.7071F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 0).addBox(-5.1213F, -0.7071F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));
		cogPart3.addOrReplaceChild("cog_part_single_3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 0).addBox(-2.0F, 1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cogPart4 = cog.addOrReplaceChild("cog_part_4", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5F, 5.5F, 0.0F, 0.0F, 0.0F, 1.5708F));
		cogPart4.addOrReplaceChild("cog_part_double_4", CubeListBuilder.create().texOffs(0, 0).addBox(-2.4142F, -8.2426F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.4142F, -8.2426F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 0).addBox(-4.4142F, -7.2426F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));
		cogPart4.addOrReplaceChild("cog_part_single_4", CubeListBuilder.create().texOffs(0, 0).addBox(-5.1213F, -5.1213F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 0).addBox(-6.1213F, -4.1213F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 32, 16);
	}

	@Override
	public void setupAnim(CogProjectile cog, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.cog.xRot = -Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.cog.yRot = this.cog.xRot;
		this.cog.zRot = this.cog.xRot;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.cog.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}