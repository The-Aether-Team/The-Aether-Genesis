package com.aetherteam.genesis.client.renderer.blockentity;

import com.aetherteam.aether.blockentity.AltarBlockEntity;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.GenesisConfig;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AltarRenderer implements BlockEntityRenderer<AltarBlockEntity> {
    public static final ResourceLocation ALTAR_LOCATION = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "textures/entity/tiles/altar/altar.png");
    private final ModelPart altar;

    public AltarRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart modelPart = context.bakeLayer(GenesisModelLayers.ALTAR);
        this.altar = modelPart.getChild("altar");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        PartDefinition altar = partDefinition.addOrReplaceChild("altar", CubeListBuilder.create().texOffs(8, 32).addBox(-7.0F, -2.0F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(16, 5).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.0F, -12.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(8, 15).addBox(-7.0F, -15.0F, -7.0F, 14.0F, 3.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(48, 6).addBox(-8.0F, -16.0F, -8.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 6).addBox(-8.0F, -16.0F, 4.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 6).addBox(4.0F, -16.0F, -8.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 6).addBox(4.0F, -16.0F, 4.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(5.0F, -17.0F, -7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(5.0F, -17.0F, 5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-7.0F, -17.0F, 5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-7.0F, -17.0F, -7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        altar.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 56).addBox(-4.0F, -1.0F, -3.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    @Override
    public void render(AltarBlockEntity altarBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        if (GenesisConfig.STARTUP.altar_redesign.get()) {
            BlockState state = altarBlockEntity.getBlockState();
            Direction direction = state.getValue(AbstractFurnaceBlock.FACING);
            poseStack.pushPose();
            poseStack.translate(0.5F, 1.5F, 0.5F);
            poseStack.mulPose(Axis.XN.rotationDegrees(180));
            poseStack.mulPose(Axis.YP.rotationDegrees(direction.toYRot() - 90));
            VertexConsumer consumer = multiBufferSource.getBuffer(RenderType.entityCutout(ALTAR_LOCATION));
            this.altar.render(poseStack, consumer, i, i1);
            poseStack.popPose();
        }
    }
}
