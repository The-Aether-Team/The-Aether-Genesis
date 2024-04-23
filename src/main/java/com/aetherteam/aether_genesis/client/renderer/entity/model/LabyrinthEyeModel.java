package com.aetherteam.aether_genesis.client.renderer.entity.model;

import com.aetherteam.aether_genesis.entity.monster.dungeon.boss.LabyrinthEye;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class LabyrinthEyeModel extends EntityModel<LabyrinthEye> {
	private final ModelPart labyrinthEye;
	private final ModelPart cogGroup;
	private final ModelPart cog;
	private final ModelPart cog2;
	private final ModelPart cog3;
	private final ModelPart cog4;
	private final ModelPart cog5;
	private final ModelPart leftCog;
	private final ModelPart cog6;
	private final ModelPart cog7;
	private final ModelPart cog8;
	private final ModelPart cog9;
	private final ModelPart cog10;
	private final ModelPart rightCog;

	public LabyrinthEyeModel(ModelPart root) {
		this.labyrinthEye = root.getChild("labyrinth_eye");
		this.cogGroup = this.labyrinthEye.getChild("cog_group");
		ModelPart cogGroupAbove = this.cogGroup.getChild("cog_group_above");
		ModelPart cogGroupBelow = this.cogGroup.getChild("cog_group_below");
		this.cog = cogGroupAbove.getChild("cog");
		this.cog2 = cogGroupAbove.getChild("cog_2");
		this.cog3 = cogGroupAbove.getChild("cog_3");
		this.cog4 = cogGroupAbove.getChild("cog_4");
		this.cog5 = cogGroupAbove.getChild("cog_5");
		this.leftCog = this.cogGroup.getChild("left_cog");
		this.cog6 = cogGroupBelow.getChild("cog_6");
		this.cog7 = cogGroupBelow.getChild("cog_7");
		this.cog8 = cogGroupBelow.getChild("cog_8");
		this.cog9 = cogGroupBelow.getChild("cog_9");
		this.cog10 = cogGroupBelow.getChild("cog_10");
		this.rightCog = this.cogGroup.getChild("right_cog");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition labyrinthEye = partdefinition.addOrReplaceChild("labyrinth_eye", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		labyrinthEye.addOrReplaceChild("head", CubeListBuilder.create().texOffs(20, 16).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -25.0F, 0.0F, 0.0F, -1.6144F, 0.0F));

		PartDefinition cogGroup = labyrinthEye.addOrReplaceChild("cog_group", CubeListBuilder.create(), PartPose.offset(0.0F, -24.25F, 0.0F));



		PartDefinition cogGroupAbove = cogGroup.addOrReplaceChild("cog_group_above", CubeListBuilder.create(), PartPose.offset(0.0F, -1.75F, 0.0F));

		PartDefinition cog = cogGroupAbove.addOrReplaceChild("cog", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 3.1416F));

		PartDefinition halfCog = cog.addOrReplaceChild("half_cog", CubeListBuilder.create(), PartPose.offset(-4.0F, 15.0F, -25.0F));

		PartDefinition cogPart = halfCog.addOrReplaceChild("cog_part", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle = cogPart.addOrReplaceChild("cog_single", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, -6.0F, 11.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble = cogPart.addOrReplaceChild("cog_double", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, -4.8284F, 5.6569F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(2.0F, -4.8284F, 9.6569F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble.addOrReplaceChild("main_r2", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.1716F, -3.3431F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart2 = halfCog.addOrReplaceChild("cog_part2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle2 = cogPart2.addOrReplaceChild("cog_single2", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, -1.2426F, -17.7574F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle2.addOrReplaceChild("main_r3", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.7574F, -28.7574F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble2 = cogPart2.addOrReplaceChild("cog_double2", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 18.8701F, -11.3137F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(2.0F, 18.8701F, -7.3137F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble2.addOrReplaceChild("main_r4", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.8701F, -20.3137F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition halfCog2 = cog.addOrReplaceChild("half_cog2", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 15.0F, -25.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cogPart3 = halfCog2.addOrReplaceChild("cog_part3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle3 = cogPart3.addOrReplaceChild("cog_single3", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 27.5147F, -65.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle3.addOrReplaceChild("main_r5", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 33.5147F, -76.0F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble3 = cogPart3.addOrReplaceChild("cog_double3", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 72.6102F, -24.3848F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(2.0F, 72.6102F, -20.3848F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble3.addOrReplaceChild("main_r6", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 78.6102F, -33.3848F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart4 = halfCog2.addOrReplaceChild("cog_part4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle4 = cogPart4.addOrReplaceChild("cog_single4", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 74.7574F, 15.7574F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle4.addOrReplaceChild("main_r7", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 80.7574F, 4.7574F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble4 = cogPart4.addOrReplaceChild("cog_double4", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 48.9117F, 66.1249F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(2.0F, 48.9117F, 70.1249F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble4.addOrReplaceChild("main_r8", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 54.9117F, 57.1249F, 0.0F, 1.5708F, -1.5708F));


		PartDefinition cog2 = cogGroupAbove.addOrReplaceChild("cog_2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -2.618F));

		PartDefinition halfCog3 = cog2.addOrReplaceChild("half_cog3", CubeListBuilder.create(), PartPose.offset(6.808F, -3.6422F, -41.4853F));

		PartDefinition cogPart5 = halfCog3.addOrReplaceChild("cog_part5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle5 = cogPart5.addOrReplaceChild("cog_single5", CubeListBuilder.create().texOffs(16, 0).addBox(-9.6613F, 12.6422F, 29.7139F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle5.addOrReplaceChild("main_r9", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6613F, 18.6422F, 18.7139F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble5 = cogPart5.addOrReplaceChild("cog_double5", CubeListBuilder.create().texOffs(16, 0).addBox(-9.6613F, -4.8791F, 32.0717F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-9.6613F, -4.8791F, 36.0717F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble5.addOrReplaceChild("main_r10", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6613F, 1.1209F, 23.0717F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart6 = halfCog3.addOrReplaceChild("cog_part6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle6 = cogPart6.addOrReplaceChild("cog_single6", CubeListBuilder.create().texOffs(16, 0).addBox(-9.6613F, -19.9566F, 0.8849F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle6.addOrReplaceChild("main_r11", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6613F, -13.9566F, -10.1151F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble6 = cogPart6.addOrReplaceChild("cog_double6", CubeListBuilder.create().texOffs(16, 0).addBox(-9.6613F, -7.5447F, -11.3644F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-9.6613F, -7.5447F, -7.3644F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble6.addOrReplaceChild("main_r12", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6613F, -1.5447F, -20.3644F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition halfCog4 = cog2.addOrReplaceChild("half_cog4", CubeListBuilder.create(), PartPose.offsetAndRotation(6.808F, -3.6422F, -41.4853F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cogPart7 = halfCog4.addOrReplaceChild("cog_part7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle7 = cogPart7.addOrReplaceChild("cog_single7", CubeListBuilder.create().texOffs(16, 0).addBox(-9.6613F, 8.8725F, -83.7139F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle7.addOrReplaceChild("main_r13", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6613F, 14.8725F, -94.7139F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble7 = cogPart7.addOrReplaceChild("cog_double7", CubeListBuilder.create().texOffs(16, 0).addBox(-9.6613F, 72.6609F, -50.7996F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-9.6613F, 72.6609F, -46.7996F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble7.addOrReplaceChild("main_r14", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6613F, 78.6609F, -59.7996F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart8 = halfCog4.addOrReplaceChild("cog_part8", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle8 = cogPart8.addOrReplaceChild("cog_single8", CubeListBuilder.create().texOffs(16, 0).addBox(-9.6613F, 93.4713F, -2.8849F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle8.addOrReplaceChild("main_r15", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6613F, 99.4713F, -13.8849F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble8 = cogPart8.addOrReplaceChild("cog_double8", CubeListBuilder.create().texOffs(16, 0).addBox(-9.6613F, 75.3265F, 66.1756F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-9.6613F, 75.3265F, 70.1756F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble8.addOrReplaceChild("main_r16", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6613F, 81.3265F, 57.1756F, 0.0F, 1.5708F, -1.5708F));


		PartDefinition cog3 = cogGroupAbove.addOrReplaceChild("cog_3", CubeListBuilder.create(), PartPose.offsetAndRotation(-30.0F, 0.0F, -2.0F, -1.5708F, 0.0F, 2.618F));

		PartDefinition halfCog5 = cog3.addOrReplaceChild("half_cog5", CubeListBuilder.create(), PartPose.offset(6.808F, -3.6422F, -41.4853F));

		PartDefinition cogPart9 = halfCog5.addOrReplaceChild("cog_part9", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle9 = cogPart9.addOrReplaceChild("cog_single9", CubeListBuilder.create().texOffs(16, 0).addBox(-33.9353F, 12.6422F, 14.7139F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle9.addOrReplaceChild("main_r17", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.9353F, 18.6422F, 3.7139F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble9 = cogPart9.addOrReplaceChild("cog_double9", CubeListBuilder.create().texOffs(16, 0).addBox(-33.9353F, 5.7275F, 21.465F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-33.9353F, 5.7275F, 25.465F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble9.addOrReplaceChild("main_r18", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.9353F, 11.7275F, 12.465F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart10 = halfCog5.addOrReplaceChild("cog_part10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle10 = cogPart10.addOrReplaceChild("cog_single10", CubeListBuilder.create().texOffs(16, 0).addBox(-33.9353F, -4.9566F, 0.8849F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle10.addOrReplaceChild("main_r19", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.9353F, 1.0434F, -10.1151F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble10 = cogPart10.addOrReplaceChild("cog_double10", CubeListBuilder.create().texOffs(16, 0).addBox(-33.9353F, 3.0619F, -0.7578F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-33.9353F, 3.0619F, 3.2422F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble10.addOrReplaceChild("main_r20", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.9353F, 9.0619F, -9.7578F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition halfCog6 = cog3.addOrReplaceChild("half_cog6", CubeListBuilder.create(), PartPose.offsetAndRotation(6.808F, -3.6422F, -41.4853F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cogPart11 = halfCog6.addOrReplaceChild("cog_part11", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle11 = cogPart11.addOrReplaceChild("cog_single11", CubeListBuilder.create().texOffs(16, 0).addBox(-33.9353F, 8.8725F, -68.7139F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle11.addOrReplaceChild("main_r21", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.9353F, 14.8725F, -79.7139F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble11 = cogPart11.addOrReplaceChild("cog_double11", CubeListBuilder.create().texOffs(16, 0).addBox(-33.9353F, 62.0543F, -40.193F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-33.9353F, 62.0543F, -36.193F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble11.addOrReplaceChild("main_r22", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.9353F, 68.0543F, -49.193F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart12 = halfCog6.addOrReplaceChild("cog_part12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle12 = cogPart12.addOrReplaceChild("cog_single12", CubeListBuilder.create().texOffs(16, 0).addBox(-33.9353F, 78.4713F, -2.8849F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle12.addOrReplaceChild("main_r23", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.9353F, 84.4713F, -13.8849F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble12 = cogPart12.addOrReplaceChild("cog_double12", CubeListBuilder.create().texOffs(16, 0).addBox(-33.9353F, 64.7199F, 55.569F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-33.9353F, 64.7199F, 59.569F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble12.addOrReplaceChild("main_r24", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.9353F, 70.7199F, 46.569F, 0.0F, 1.5708F, -1.5708F));


		PartDefinition cog4 = cogGroupAbove.addOrReplaceChild("cog_4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 2.0944F));

		PartDefinition halfCog7 = cog4.addOrReplaceChild("half_cog7", CubeListBuilder.create(), PartPose.offset(-12.229F, 16.3578F, -20.979F));

		PartDefinition cogPart13 = halfCog7.addOrReplaceChild("cog_part13", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle13 = cogPart13.addOrReplaceChild("cog_single13", CubeListBuilder.create().texOffs(16, 0).addBox(13.4392F, -7.3578F, 8.8324F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle13.addOrReplaceChild("main_r25", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4392F, -1.3578F, -2.1676F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble13 = cogPart13.addOrReplaceChild("cog_double13", CubeListBuilder.create().texOffs(16, 0).addBox(13.4392F, -4.2558F, 3.164F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(13.4392F, -4.2558F, 7.164F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble13.addOrReplaceChild("main_r26", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4392F, 1.7442F, -5.836F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart14 = halfCog7.addOrReplaceChild("cog_part14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle14 = cogPart14.addOrReplaceChild("cog_single14", CubeListBuilder.create().texOffs(16, 0).addBox(13.4392F, 0.925F, -19.1151F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle14.addOrReplaceChild("main_r27", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4392F, 6.925F, -30.1151F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble14 = cogPart14.addOrReplaceChild("cog_double14", CubeListBuilder.create().texOffs(16, 0).addBox(13.4392F, 21.3629F, -10.7411F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(13.4392F, 21.3629F, -6.7411F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble14.addOrReplaceChild("main_r28", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4392F, 27.3629F, -19.7411F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition halfCog8 = cog4.addOrReplaceChild("half_cog8", CubeListBuilder.create(), PartPose.offsetAndRotation(-12.229F, 16.3578F, -20.979F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cogPart15 = halfCog8.addOrReplaceChild("cog_part15", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle15 = cogPart15.addOrReplaceChild("cog_single15", CubeListBuilder.create().texOffs(16, 0).addBox(13.4392F, 28.8725F, -62.8324F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle15.addOrReplaceChild("main_r29", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4392F, 34.8725F, -73.8324F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble15 = cogPart15.addOrReplaceChild("cog_double15", CubeListBuilder.create().texOffs(16, 0).addBox(13.4392F, 72.0375F, -21.8919F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(13.4392F, 72.0375F, -17.8919F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble15.addOrReplaceChild("main_r30", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4392F, 78.0375F, -30.8919F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart16 = halfCog8.addOrReplaceChild("cog_part16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle16 = cogPart16.addOrReplaceChild("cog_single16", CubeListBuilder.create().texOffs(16, 0).addBox(13.4392F, 72.5897F, 17.1151F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle16.addOrReplaceChild("main_r31", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4392F, 78.5897F, 6.1151F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble16 = cogPart16.addOrReplaceChild("cog_double16", CubeListBuilder.create().texOffs(16, 0).addBox(13.4392F, 46.4189F, 65.5523F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(13.4392F, 46.4189F, 69.5523F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble16.addOrReplaceChild("main_r32", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4392F, 52.4189F, 56.5523F, 0.0F, 1.5708F, -1.5708F));


		PartDefinition cog5 = cogGroupAbove.addOrReplaceChild("cog_5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, -2.0944F));

		PartDefinition halfCog9 = cog5.addOrReplaceChild("half_cog9", CubeListBuilder.create(), PartPose.offset(-12.229F, 16.3578F, -20.979F));

		PartDefinition cogPart17 = halfCog9.addOrReplaceChild("cog_part17", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle17 = cogPart17.addOrReplaceChild("cog_single17", CubeListBuilder.create().texOffs(16, 0).addBox(7.0189F, -7.3578F, 8.8324F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle17.addOrReplaceChild("main_r33", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0189F, -1.3578F, -2.1676F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble17 = cogPart17.addOrReplaceChild("cog_double17", CubeListBuilder.create().texOffs(16, 0).addBox(7.0189F, -4.2558F, 3.164F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(7.0189F, -4.2558F, 7.164F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble17.addOrReplaceChild("main_r34", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0189F, 1.7442F, -5.836F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart18 = halfCog9.addOrReplaceChild("cog_part18", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle18 = cogPart18.addOrReplaceChild("cog_single18", CubeListBuilder.create().texOffs(16, 0).addBox(7.0189F, 0.925F, -19.1151F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle18.addOrReplaceChild("main_r35", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0189F, 6.925F, -30.1151F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble18 = cogPart18.addOrReplaceChild("cog_double18", CubeListBuilder.create().texOffs(16, 0).addBox(7.0189F, 21.3629F, -10.7411F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(7.0189F, 21.3629F, -6.7411F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble18.addOrReplaceChild("main_r36", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0189F, 27.3629F, -19.7411F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition halfCog10 = cog5.addOrReplaceChild("half_cog10", CubeListBuilder.create(), PartPose.offsetAndRotation(-12.229F, 16.3578F, -20.979F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cogPart19 = halfCog10.addOrReplaceChild("cog_part19", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle19 = cogPart19.addOrReplaceChild("cog_single19", CubeListBuilder.create().texOffs(16, 0).addBox(7.0189F, 28.8725F, -62.8324F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle19.addOrReplaceChild("main_r37", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0189F, 34.8725F, -73.8324F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble19 = cogPart19.addOrReplaceChild("cog_double19", CubeListBuilder.create().texOffs(16, 0).addBox(7.0189F, 72.0375F, -21.8919F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(7.0189F, 72.0375F, -17.8919F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble19.addOrReplaceChild("main_r38", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0189F, 78.0375F, -30.8919F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart20 = halfCog10.addOrReplaceChild("cog_part20", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle20 = cogPart20.addOrReplaceChild("cog_single20", CubeListBuilder.create().texOffs(16, 0).addBox(7.0189F, 72.5897F, 17.1151F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle20.addOrReplaceChild("main_r39", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0189F, 78.5897F, 6.1151F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble20 = cogPart20.addOrReplaceChild("cog_double20", CubeListBuilder.create().texOffs(16, 0).addBox(7.0189F, 46.4189F, 65.5523F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(7.0189F, 46.4189F, 69.5523F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble20.addOrReplaceChild("main_r40", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0189F, 52.4189F, 56.5523F, 0.0F, 1.5708F, -1.5708F));


		PartDefinition leftCog = cogGroup.addOrReplaceChild("left_cog", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.75F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition halfLeftCog = leftCog.addOrReplaceChild("halfLeftCog", CubeListBuilder.create(), PartPose.offset(0.0F, 19.1754F, -8.0398F));

		PartDefinition part1 = halfLeftCog.addOrReplaceChild("part1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		part1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 22).addBox(47.0F, -17.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, -35.6819F, -16.0941F, -1.5708F, -0.7854F, 1.5708F));
		part1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 22).addBox(52.0F, 0.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, -18.4687F, -22.8392F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part2 = halfLeftCog.addOrReplaceChild("part2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
		part2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 22).addBox(47.0F, -17.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, -43.5455F, -56.8951F, -1.5708F, -0.7854F, 1.5708F));
		part2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 22).addBox(52.0F, 0.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, -26.3323F, -63.6403F, 0.0F, -1.5708F, 0.0F));

		PartDefinition halfLeftCog2 = leftCog.addOrReplaceChild("halfLeftCog2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 19.1754F, -8.0398F, 3.1416F, 0.0F, 0.0F));

		PartDefinition part3 = halfLeftCog2.addOrReplaceChild("part3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		part3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 22).addBox(47.0F, -17.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, -2.7445F, -64.7587F, -1.5708F, -0.7854F, 1.5708F));
		part3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 22).addBox(52.0F, 0.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, 14.4687F, -71.5039F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part4 = halfLeftCog2.addOrReplaceChild("part4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
		part4.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 22).addBox(47.0F, -17.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, 5.1191F, -23.9577F, -1.5708F, -0.7854F, 1.5708F));
		part4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 22).addBox(52.0F, 0.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, 22.3323F, -30.7029F, 0.0F, -1.5708F, 0.0F));

		PartDefinition outLayer = leftCog.addOrReplaceChild("outLayer", CubeListBuilder.create(), PartPose.offset(2.0F, 8.1754F, -71.0398F));

		PartDefinition lefCogOutLayer = outLayer.addOrReplaceChild("lefCogOutLayer", CubeListBuilder.create().texOffs(12, 8).addBox(-3.7574F, 3.3711F, -3.6222F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 101.0F, -0.3491F, 0.0F, 0.0F));
		lefCogOutLayer.addOrReplaceChild("part7_r1", CubeListBuilder.create().texOffs(12, 8).addBox(0.0F, -1.0F, 18.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, 14.5994F, -11.3041F, 1.7952F, 0.0F, 0.0F));
		lefCogOutLayer.addOrReplaceChild("part6_r1", CubeListBuilder.create().texOffs(12, 8).addBox(0.0F, -1.0F, 18.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, 14.1544F, -15.4152F, 1.3464F, 0.0F, 0.0F));
		lefCogOutLayer.addOrReplaceChild("part5_r1", CubeListBuilder.create().texOffs(12, 8).addBox(0.0F, -1.0F, 18.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, 11.9697F, -18.926F, 0.8976F, 0.0F, 0.0F));
		lefCogOutLayer.addOrReplaceChild("part4_r1", CubeListBuilder.create().texOffs(12, 8).addBox(0.0F, -1.0F, 18.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, 8.4781F, -21.1412F, 0.4488F, 0.0F, 0.0F));
		lefCogOutLayer.addOrReplaceChild("part3_r1", CubeListBuilder.create().texOffs(12, 8).addBox(0.0F, -1.0F, 18.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, -2.4745F, -17.3623F, -0.8976F, 0.0F, 0.0F));
		lefCogOutLayer.addOrReplaceChild("part1_r1", CubeListBuilder.create().texOffs(12, 8).addBox(0.0F, -1.0F, 18.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7574F, 0.4621F, -20.2735F, -0.4488F, 0.0F, 0.0F));



		PartDefinition cogGroupBelow = cogGroup.addOrReplaceChild("cog_group_below", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.25F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cog6 = cogGroupBelow.addOrReplaceChild("cog_6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 3.1416F));

		PartDefinition halfCog11 = cog6.addOrReplaceChild("half_cog11", CubeListBuilder.create(), PartPose.offset(-4.0F, 15.0F, -25.0F));

		PartDefinition cogPart21 = halfCog11.addOrReplaceChild("cog_part21", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle21 = cogPart21.addOrReplaceChild("cog_single21", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, -6.4853F, 10.4135F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle21.addOrReplaceChild("main_r41", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.4853F, -0.5865F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble21 = cogPart21.addOrReplaceChild("cog_double21", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, -4.7568F, 4.899F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(2.0F, -4.7568F, 8.899F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble21.addOrReplaceChild("main_r42", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.2432F, -4.101F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart22 = halfCog11.addOrReplaceChild("cog_part22", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle22 = cogPart22.addOrReplaceChild("cog_single22", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, -0.6561F, -18.2426F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle22.addOrReplaceChild("main_r43", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.3439F, -29.2426F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble22 = cogPart22.addOrReplaceChild("cog_double22", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 19.6279F, -11.2421F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(2.0F, 19.6279F, -7.2421F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble22.addOrReplaceChild("main_r44", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 25.6279F, -20.2421F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition halfCog12 = cog6.addOrReplaceChild("half_cog12", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 15.0F, -25.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cogPart23 = halfCog12.addOrReplaceChild("cog_part23", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle23 = cogPart23.addOrReplaceChild("cog_single23", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 28.0F, -64.4135F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle23.addOrReplaceChild("main_r45", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 34.0F, -75.4135F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble23 = cogPart23.addOrReplaceChild("cog_double23", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 72.5386F, -23.6269F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(2.0F, 72.5386F, -19.6269F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble23.addOrReplaceChild("main_r46", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 78.5386F, -32.6269F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart24 = halfCog12.addOrReplaceChild("cog_part24", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle24 = cogPart24.addOrReplaceChild("cog_single24", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 74.1708F, 16.2426F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle24.addOrReplaceChild("main_r47", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 80.1708F, 5.2426F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble24 = cogPart24.addOrReplaceChild("cog_double24", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 48.1538F, 66.0533F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(2.0F, 48.1538F, 70.0533F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble24.addOrReplaceChild("main_r48", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 54.1538F, 57.0533F, 0.0F, 1.5708F, -1.5708F));


		PartDefinition cog7 = cogGroupBelow.addOrReplaceChild("cog_7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -2.618F));

		PartDefinition halfCog13 = cog7.addOrReplaceChild("half_cog13", CubeListBuilder.create(), PartPose.offset(6.808F, -3.6422F, -41.4853F));

		PartDefinition cogPart25 = halfCog13.addOrReplaceChild("cog_part25", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle25 = cogPart25.addOrReplaceChild("cog_single25", CubeListBuilder.create().texOffs(16, 0).addBox(-9.9546F, 12.1569F, 29.206F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle25.addOrReplaceChild("main_r49", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.9546F, 18.1569F, 18.206F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble25 = cogPart25.addOrReplaceChild("cog_double25", CubeListBuilder.create().texOffs(16, 0).addBox(-9.9546F, -4.8631F, 31.3693F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-9.9546F, -4.8631F, 35.3693F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble25.addOrReplaceChild("main_r50", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.9546F, 1.1369F, 22.3693F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart26 = halfCog13.addOrReplaceChild("cog_part26", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle26 = cogPart26.addOrReplaceChild("cog_single26", CubeListBuilder.create().texOffs(16, 0).addBox(-9.9546F, -19.4486F, 0.3996F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle26.addOrReplaceChild("main_r51", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.9546F, -13.4486F, -10.6004F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble26 = cogPart26.addOrReplaceChild("cog_double26", CubeListBuilder.create().texOffs(16, 0).addBox(-9.9546F, -6.8424F, -11.3484F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-9.9546F, -6.8424F, -7.3484F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble26.addOrReplaceChild("main_r52", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.9546F, -0.8424F, -20.3484F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition halfCog14 = cog7.addOrReplaceChild("half_cog14", CubeListBuilder.create(), PartPose.offsetAndRotation(6.808F, -3.6422F, -41.4853F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cogPart27 = halfCog14.addOrReplaceChild("cog_part27", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle27 = cogPart27.addOrReplaceChild("cog_single27", CubeListBuilder.create().texOffs(16, 0).addBox(-9.9546F, 9.3578F, -83.206F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle27.addOrReplaceChild("main_r53", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.9546F, 15.3578F, -94.206F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble27 = cogPart27.addOrReplaceChild("cog_double27", CubeListBuilder.create().texOffs(16, 0).addBox(-9.9546F, 72.6449F, -50.0973F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-9.9546F, 72.6449F, -46.0973F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble27.addOrReplaceChild("main_r54", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.9546F, 78.6449F, -59.0973F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart28 = halfCog14.addOrReplaceChild("cog_part28", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle28 = cogPart28.addOrReplaceChild("cog_single28", CubeListBuilder.create().texOffs(16, 0).addBox(-9.9546F, 92.9634F, -2.3996F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle28.addOrReplaceChild("main_r55", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.9546F, 98.9634F, -13.3996F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble28 = cogPart28.addOrReplaceChild("cog_double28", CubeListBuilder.create().texOffs(16, 0).addBox(-9.9546F, 74.6242F, 66.1596F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-9.9546F, 74.6242F, 70.1596F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble28.addOrReplaceChild("main_r56", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.9546F, 80.6242F, 57.1596F, 0.0F, 1.5708F, -1.5708F));


		PartDefinition cog8 = cogGroupBelow.addOrReplaceChild("cog_8", CubeListBuilder.create(), PartPose.offsetAndRotation(-30.0F, 0.0F, -2.0F, -1.5708F, 0.0F, 2.618F));

		PartDefinition halfCog15 = cog8.addOrReplaceChild("half_cog15", CubeListBuilder.create(), PartPose.offset(6.808F, -3.6422F, -41.4853F));

		PartDefinition cogPart29 = halfCog15.addOrReplaceChild("cog_part29", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle29 = cogPart29.addOrReplaceChild("cog_single29", CubeListBuilder.create().texOffs(16, 0).addBox(-33.6421F, 12.1569F, 14.206F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle29.addOrReplaceChild("main_r57", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.6421F, 18.1569F, 3.206F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble29 = cogPart29.addOrReplaceChild("cog_double29", CubeListBuilder.create().texOffs(16, 0).addBox(-33.6421F, 5.7435F, 20.7627F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 0).addBox(-33.6421F, 5.7435F, 24.7627F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble29.addOrReplaceChild("main_r58", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.6421F, 11.7435F, 11.7627F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart30 = halfCog15.addOrReplaceChild("cog_part30", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle30 = cogPart30.addOrReplaceChild("cog_single30", CubeListBuilder.create().texOffs(16, 0).addBox(-33.6421F, -4.4486F, 0.3996F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle30.addOrReplaceChild("main_r59", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.6421F, 1.5514F, -10.6004F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble30 = cogPart30.addOrReplaceChild("cog_double30", CubeListBuilder.create().texOffs(16, 0).addBox(-33.6421F, 3.7642F, -0.7418F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-33.6421F, 3.7642F, 3.2582F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble30.addOrReplaceChild("main_r60", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.6421F, 9.7642F, -9.7418F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition halfCog16 = cog8.addOrReplaceChild("half_cog16", CubeListBuilder.create(), PartPose.offsetAndRotation(6.808F, -3.6422F, -41.4853F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cogPart31 = halfCog16.addOrReplaceChild("cog_part31", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle31 = cogPart31.addOrReplaceChild("cog_single31", CubeListBuilder.create().texOffs(16, 0).addBox(-33.6421F, 9.3578F, -68.206F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle31.addOrReplaceChild("main_r61", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.6421F, 15.3578F, -79.206F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble31 = cogPart31.addOrReplaceChild("cog_double31", CubeListBuilder.create().texOffs(16, 0).addBox(-33.6421F, 62.0383F, -39.4907F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-33.6421F, 62.0383F, -35.4907F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble31.addOrReplaceChild("main_r62", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.6421F, 68.0383F, -48.4907F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart32 = halfCog16.addOrReplaceChild("cog_part32", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle32 = cogPart32.addOrReplaceChild("cog_single32", CubeListBuilder.create().texOffs(16, 0).addBox(-33.6421F, 77.9634F, -2.3996F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle32.addOrReplaceChild("main_r63", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.6421F, 83.9634F, -13.3996F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble32 = cogPart32.addOrReplaceChild("cog_double32", CubeListBuilder.create().texOffs(16, 0).addBox(-33.6421F, 64.0176F, 55.553F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-33.6421F, 64.0176F, 59.553F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble32.addOrReplaceChild("main_r64", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.6421F, 70.0176F, 46.553F, 0.0F, 1.5708F, -1.5708F));


		PartDefinition cog9 = cogGroupBelow.addOrReplaceChild("cog_9", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 2.0944F));

		PartDefinition halfCog17 = cog9.addOrReplaceChild("half_cog17", CubeListBuilder.create(), PartPose.offset(-12.229F, 16.3578F, -20.979F));

		PartDefinition cogPart33 = halfCog17.addOrReplaceChild("cog_part33", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle33 = cogPart33.addOrReplaceChild("cog_single33", CubeListBuilder.create().texOffs(16, 0).addBox(13.9471F, -7.8431F, 8.5391F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle33.addOrReplaceChild("main_r65", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.9471F, -1.8431F, -2.4609F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble33 = cogPart33.addOrReplaceChild("cog_double33", CubeListBuilder.create().texOffs(16, 0).addBox(13.9471F, -4.3916F, 2.6135F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(13.9471F, -4.3916F, 6.6135F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble33.addOrReplaceChild("main_r66", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.9471F, 1.6084F, -6.3865F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart34 = halfCog17.addOrReplaceChild("cog_part34", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle34 = cogPart34.addOrReplaceChild("cog_single34", CubeListBuilder.create().texOffs(16, 0).addBox(13.9471F, 1.2182F, -19.6004F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle34.addOrReplaceChild("main_r67", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.9471F, 7.2182F, -30.6004F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble34 = cogPart34.addOrReplaceChild("cog_double34", CubeListBuilder.create().texOffs(16, 0).addBox(13.9471F, 21.9134F, -10.8769F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(13.9471F, 21.9134F, -6.8769F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble34.addOrReplaceChild("main_r68", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.9471F, 27.9134F, -19.8769F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition halfCog18 = cog9.addOrReplaceChild("half_cog18", CubeListBuilder.create(), PartPose.offsetAndRotation(-12.229F, 16.3578F, -20.979F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cogPart35 = halfCog18.addOrReplaceChild("cog_part35", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle35 = cogPart35.addOrReplaceChild("cog_single35", CubeListBuilder.create().texOffs(16, 0).addBox(13.9471F, 29.3578F, -62.5391F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle35.addOrReplaceChild("main_r69", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.9471F, 35.3578F, -73.5391F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble35 = cogPart35.addOrReplaceChild("cog_double35", CubeListBuilder.create().texOffs(16, 0).addBox(13.9471F, 72.1733F, -21.3414F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(13.9471F, 72.1733F, -17.3414F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble35.addOrReplaceChild("main_r70", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.9471F, 78.1733F, -30.3414F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart36 = halfCog18.addOrReplaceChild("cog_part36", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle36 = cogPart36.addOrReplaceChild("cog_single36", CubeListBuilder.create().texOffs(16, 0).addBox(13.9471F, 72.2965F, 17.6004F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle36.addOrReplaceChild("main_r71", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.9471F, 78.2965F, 6.6004F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble36 = cogPart36.addOrReplaceChild("cog_double36", CubeListBuilder.create().texOffs(16, 0).addBox(13.9471F, 45.8683F, 65.688F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(13.9471F, 45.8683F, 69.688F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble36.addOrReplaceChild("main_r72", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.9471F, 51.8683F, 56.688F, 0.0F, 1.5708F, -1.5708F));


		PartDefinition cog10 = cogGroupBelow.addOrReplaceChild("cog_10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, -2.0944F));

		PartDefinition halfCog19 = cog10.addOrReplaceChild("half_cog19", CubeListBuilder.create(), PartPose.offset(-12.229F, 16.3578F, -20.979F));

		PartDefinition cogPart37 = halfCog19.addOrReplaceChild("cog_part37", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle37 = cogPart37.addOrReplaceChild("cog_single37", CubeListBuilder.create().texOffs(16, 0).addBox(6.511F, -7.8431F, 8.5391F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle37.addOrReplaceChild("main_r73", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.511F, -1.8431F, -2.4609F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble37 = cogPart37.addOrReplaceChild("cog_double37", CubeListBuilder.create().texOffs(16, 0).addBox(6.511F, -4.3916F, 2.6135F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(6.511F, -4.3916F, 6.6135F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble37.addOrReplaceChild("main_r74", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.511F, 1.6084F, -6.3865F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart38 = halfCog19.addOrReplaceChild("cog_part38", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle38 = cogPart38.addOrReplaceChild("cog_single38", CubeListBuilder.create().texOffs(16, 0).addBox(6.511F, 1.2182F, -19.6004F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle38.addOrReplaceChild("main_r75", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.511F, 7.2182F, -30.6004F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble38 = cogPart38.addOrReplaceChild("cog_double38", CubeListBuilder.create().texOffs(16, 0).addBox(6.511F, 21.9134F, -10.8769F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(6.511F, 21.9134F, -6.8769F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble38.addOrReplaceChild("main_r76", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.511F, 27.9134F, -19.8769F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition halfCog20 = cog10.addOrReplaceChild("half_cog20", CubeListBuilder.create(), PartPose.offsetAndRotation(-12.229F, 16.3578F, -20.979F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cogPart39 = halfCog20.addOrReplaceChild("cog_part39", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 26.0F));
		PartDefinition cogSingle39 = cogPart39.addOrReplaceChild("cog_single39", CubeListBuilder.create().texOffs(16, 0).addBox(6.511F, 29.3578F, -62.5391F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle39.addOrReplaceChild("main_r77", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.511F, 35.3578F, -73.5391F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble39 = cogPart39.addOrReplaceChild("cog_double39", CubeListBuilder.create().texOffs(16, 0).addBox(6.511F, 72.1733F, -21.3414F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(6.511F, 72.1733F, -17.3414F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble39.addOrReplaceChild("main_r78", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.511F, 78.1733F, -30.3414F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition cogPart40 = halfCog20.addOrReplaceChild("cog_part40", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 26.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cogSingle40 = cogPart40.addOrReplaceChild("cog_single40", CubeListBuilder.create().texOffs(16, 0).addBox(6.511F, 72.2965F, 17.6004F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));
		cogSingle40.addOrReplaceChild("main_r79", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.511F, 78.2965F, 6.6004F, 0.0F, 1.5708F, -1.5708F));
		PartDefinition cogDouble40 = cogPart40.addOrReplaceChild("cog_double40", CubeListBuilder.create().texOffs(16, 0).addBox(6.511F, 45.8683F, 65.688F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(6.511F, 45.8683F, 69.688F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 9.0F, -0.7854F, 0.0F, 0.0F));
		cogDouble40.addOrReplaceChild("main_r80", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.511F, 51.8683F, 56.688F, 0.0F, 1.5708F, -1.5708F));


		PartDefinition rightCog = cogGroup.addOrReplaceChild("right_cog", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.75F, -2.0F, 1.5708F, 0.0F, 1.0472F));

		rightCog.addOrReplaceChild("part6_r2", CubeListBuilder.create().texOffs(0, 22).addBox(-15.0F, 0.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0027F, -1.2426F, -21.442F, 1.5708F, 1.309F, 0.0F));
		rightCog.addOrReplaceChild("part5_r2", CubeListBuilder.create().texOffs(0, 22).addBox(-15.0F, 0.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.486F, -1.2426F, -19.3079F, 1.5708F, 2.0944F, 0.0F));
		rightCog.addOrReplaceChild("part4_r2", CubeListBuilder.create().texOffs(0, 22).addBox(-15.0F, 0.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.1471F, -1.2426F, -14.6286F, 1.5708F, 2.8798F, 0.0F));
		rightCog.addOrReplaceChild("part3_r2", CubeListBuilder.create().texOffs(0, 22).addBox(-15.0F, 0.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3337F, -1.2426F, -5.0201F, 1.5708F, -1.8326F, 0.0F));
		rightCog.addOrReplaceChild("part2_r1", CubeListBuilder.create().texOffs(0, 22).addBox(-15.0F, 0.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1497F, -1.2426F, -7.1543F, 1.5708F, -1.0472F, 0.0F));
		rightCog.addOrReplaceChild("part1_r2", CubeListBuilder.create().texOffs(0, 22).addBox(-15.0F, 0.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8107F, -1.2426F, -11.8335F, 1.5708F, -0.2618F, 0.0F));

		PartDefinition rightCogOutLayer2 = rightCog.addOrReplaceChild("rightCogOutLayer2", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.5359F, -2.0F, -11.5167F, 0.0F, -0.1745F, 0.0F));

		rightCogOutLayer2.addOrReplaceChild("part6_r3", CubeListBuilder.create().texOffs(16, 0).addBox(-18.0F, -130.0F, -77.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(68.728F, 132.7574F, -14.889F, 0.0F, 1.5708F, 0.0F));
		rightCogOutLayer2.addOrReplaceChild("part5_r3", CubeListBuilder.create().texOffs(16, 0).addBox(-18.0F, -130.0F, -77.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(70.3614F, 132.7574F, 7.848F, 0.0F, 1.2217F, 0.0F));
		rightCogOutLayer2.addOrReplaceChild("part4_r3", CubeListBuilder.create().texOffs(16, 0).addBox(-18.0F, -130.0F, -77.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.7735F, 132.7574F, 66.5797F, 0.0F, -0.1745F, 0.0F));
		rightCogOutLayer2.addOrReplaceChild("part3_r3", CubeListBuilder.create().texOffs(16, 0).addBox(-18.0F, -130.0F, -77.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(31.8815F, 132.7574F, 61.0229F, 0.0F, 0.1745F, 0.0F));
		rightCogOutLayer2.addOrReplaceChild("part2_r2", CubeListBuilder.create().texOffs(16, 0).addBox(-18.0F, -130.0F, -77.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(64.1196F, 132.7574F, 29.7725F, 0.0F, 0.8727F, 0.0F));
		rightCogOutLayer2.addOrReplaceChild("part1_r3", CubeListBuilder.create().texOffs(16, 0).addBox(-18.0F, -130.0F, -77.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(50.7557F, 132.7574F, 48.2399F, 0.0F, 0.5236F, 0.0F));

		PartDefinition rightBigLayer3 = rightCog.addOrReplaceChild("rightBigLayer3", CubeListBuilder.create().texOffs(16, 0).addBox(-0.0612F, -1.2426F, -13.2549F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.134F, -6.0F, -1.5F, 0.0F, 0.5236F, 0.0F));
		rightBigLayer3.addOrReplaceChild("main_r81", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0612F, 4.7574F, -24.2549F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition rightBigLayer4 = rightCog.addOrReplaceChild("rightBigLayer4", CubeListBuilder.create().texOffs(16, 0).addBox(-0.0612F, -16.7574F, 11.2549F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.134F, -6.0F, -1.5F, 3.1416F, 0.5236F, 0.0F));
		rightBigLayer4.addOrReplaceChild("main_r82", CubeListBuilder.create().texOffs(0, 14).addBox(-15.0F, 2.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0612F, -10.7574F, 0.2549F, 0.0F, 1.5708F, -1.5708F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(LabyrinthEye eye, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.cogGroup.zRot = ageInTicks * 4000;
		this.cog.visible = eye.getHealth() >= 212.0F;
		this.cog2.visible = eye.getHealth() >= 174.0F;
		this.cog3.visible = eye.getHealth() >= 117.0F;
		this.cog4.visible = eye.getHealth() >= 79.0F;
		this.cog5.visible = eye.getHealth() >= 41.0F;
		this.leftCog.visible = eye.getHealth() >= 231.0F;
		this.cog6.visible = eye.getHealth() >= 60.0F;
		this.cog7.visible = eye.getHealth() >= 155.0F;
		this.cog8.visible = eye.getHealth() >= 193.0F;
		this.cog9.visible = eye.getHealth() >= 98.0F;
		this.cog10.visible = eye.getHealth() >= 22.0F;
		this.rightCog.visible = eye.getHealth() >= 136.0F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.labyrinthEye.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}