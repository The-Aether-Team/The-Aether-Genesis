package com.aetherteam.genesis.client.renderer.entity.model;

import com.aetherteam.genesis.entity.passive.CarrionSprout;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;

import java.util.Map;

public class CarrionSproutModel extends EntityModel<CarrionSprout> {
    public ModelPart stemBottom;
    public ModelPart head;
    public ModelPart petal1;
    public ModelPart petal2;
    public ModelPart petal3;
    public ModelPart petal4;
    public ModelPart petal5;
    public ModelPart petal6;
    public ModelPart petal7;
    public ModelPart petal8;
    public ModelPart stemTop;
    public ModelPart headTop;
    public ModelPart teeth;
    public ModelPart jaw;
    private float maxSize;
    private float size;

    public CarrionSproutModel(ModelPart root) {
        this.stemBottom = root.getChild("stem_bottom");
        this.head = this.stemBottom.getChild("head");
        this.petal1 = this.head.getChild("petal_1");
        this.petal2 = this.head.getChild("petal_2");
        this.petal3 = this.head.getChild("petal_3");
        this.petal4 = this.head.getChild("petal_4");
        this.petal5 = this.head.getChild("petal_5");
        this.petal6 = this.head.getChild("petal_6");
        this.petal7 = this.head.getChild("petal_7");
        this.petal8 = this.head.getChild("petal_8");
        this.stemTop = this.stemBottom.getChild("stem_top");
        this.headTop = this.stemTop.getChild("head_top");
        this.teeth = this.headTop.getChild("teeth");
        this.jaw = this.headTop.getChild("jaw");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition stemBottom = partDefinition.addOrReplaceChild("stem_bottom", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, 0.0F, 0.0F, 2, 10, 2), PartPose.offset(-1.0F, 0.0F, -1.0F));
        PartDefinition head = stemBottom.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -3.0F, 6, 2, 6), PartPose.offset(1.0F, 4.0F, 1.0F));
        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 0) {
                head.addOrReplaceChild("petal_" + i, CubeListBuilder.create().texOffs(43, 49).addBox(-2.8F, 0.0F, -10.8F, 6, 0, 9), PartPose.ZERO);
            } else {
                head.addOrReplaceChild("petal_" + i, CubeListBuilder.create().texOffs(43, 49).addBox(-2.8F, 0.0F, -11.8F, 6, 0, 9), PartPose.ZERO);
            }
        }
        PartDefinition stemTop = stemBottom.addOrReplaceChild("stem_top", CubeListBuilder.create().texOffs(8, 25).addBox(0.0F, -6.0F, -1.0F, 2, 6, 2), PartPose.offset(0.0F, 0.25F, 1.0F));
        PartDefinition headTop = stemTop.addOrReplaceChild("head_top", CubeListBuilder.create().texOffs(20, 16).addBox(-5.5F, -5.0F, -11.0F, 11, 5, 11), PartPose.offset(1.0F, -6.0F, 3.0F));
        headTop.addOrReplaceChild("teeth", CubeListBuilder.create().texOffs(0, 33).addBox(-4.5F, -1.0F, -9.0F, 9, 1, 9), PartPose.offset(0.0F, 0.5F, -1.5F));
        headTop.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(24, 1).addBox(-5.0F, 0.0F, -10.0F, 10, 2, 10), PartPose.offset(0.0F, -0.5F, -0.5F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    @Override
    public void prepareMobModel( CarrionSprout carrionSprout, float limbSwing, float limbSwingAmount, float partialTicks) {
        super.prepareMobModel(carrionSprout, limbSwing, limbSwingAmount, partialTicks);
        this.maxSize = carrionSprout.getMaxSize();
        this.size = carrionSprout.getSize();
    }

    public Iterable<ModelPart> petalParts() {
        return ImmutableList.of(this.petal1, this.petal2, this.petal3, this.petal4, this.petal5, this.petal6, this.petal7, this.petal8);
    }

    @Override
    public void setupAnim( CarrionSprout carrionSprout, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float sinage1 = (float) Math.sin(ageInTicks);
        float sinage2 = 1.75F + (float) Math.sin(ageInTicks + 2.0F) * 1.5F;
        if (carrionSprout.hurtTime > 0) {
            sinage1 *= 0.45F;
            sinage1 -= 0.125F;
        } else {
            sinage1 *= 0.25F;
        }
        this.stemBottom.y = sinage2 - 4.5F + sinage1 * 2.0F;
        int i = 0;
        for (ModelPart modelPart : this.petalParts()) {
            modelPart.xRot = ((i % 2 == 0) ? -0.25F : -0.4125F);
            modelPart.xRot += sinage1;
            modelPart.yRot = 17.0F;
            modelPart.yRot += (float) (((Math.PI * 2.0F) / 8.0F) * i);
            modelPart.y = sinage2 - 3.5F - this.stemBottom.y;
            i++;
        }
        this.stemTop.xRot = 0.3490659F;
        this.headTop.xRot = -0.3490659F - this.stemTop.xRot;
        this.jaw.xRot = 0.08F + this.stemTop.xRot;
        this.jaw.xRot += sinage1;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        if (this.size < this.maxSize) {
            var red = Mth.clamp((FastColor.ARGB32.red(color) / 255f) * this.size, 0.0F, 1.0F);
            var blue = Mth.clamp((FastColor.ARGB32.blue(color) / 255f) * this.size, 0.6F, 1.0F);

            color = colorFloat(color, Map.of("red", red, "blue", blue));
        }
        this.stemBottom.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    public static int colorInt(int color, Map<String, Integer> colorComponents) {
        for (var entry : colorComponents.entrySet()) {
            color = switch (entry.getKey().toLowerCase()) {
                case "alpha", "a" -> color | entry.getValue() << 24;
                case "red", "r" -> color | entry.getValue() << 16;
                case "green", "g" -> color | entry.getValue() << 8;
                case "blue", "b" -> color | entry.getValue();
                default -> throw new IllegalStateException("Unexpected value: " + entry.getKey());
            };
        }

        return color;
    }

    public static int colorFloat(int color, Map<String, Float> colorComponents) {
        for (var entry : colorComponents.entrySet()) {
            var colorInt = Math.clamp(Math.round(entry.getValue() * 255), 0, 255);

            color = switch (entry.getKey().toLowerCase()) {
                case "alpha", "a" -> color | colorInt << 24;
                case "red", "r" -> color | colorInt << 16;
                case "green", "g" -> color | colorInt << 8;
                case "blue", "b" -> color | colorInt;
                default -> throw new IllegalStateException("Unexpected value: " + entry.getKey());
            };
        }

        return color;
    }
}
