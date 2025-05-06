package com.aetherteam.genesis.client.renderer.blockentity;

import com.aetherteam.aether.blockentity.AltarBlockEntity;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.GenesisConfig;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AltarRenderer implements BlockEntityRenderer<AltarBlockEntity> {
    public static final ResourceLocation ALTAR_LOCATION = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "textures/entity/tiles/altar/altar.png");
    private final ItemRenderer itemRenderer;
    private final ModelPart altar;
    private final ModelPart ambrosiumCorner1;
    private final ModelPart ambrosiumCorner2;
    private final ModelPart ambrosiumCorner3;
    private final ModelPart ambrosiumCorner4;
    private final ModelPart top;
    private float ambRotation = 0.0F;
    private float ambSpinningSpeed = 0.0F;
    private float ambrosiumFinalRotation = 0.0F;
    private float bobOffs = -1.0F;
    private float inputItemRotation = 0.0F;

    public AltarRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
        ModelPart modelPart = context.bakeLayer(GenesisModelLayers.ALTAR);
        this.altar = modelPart.getChild("altar");
        this.ambrosiumCorner1 = this.altar.getChild("ambrosium_corner_1");
        this.ambrosiumCorner2 = this.altar.getChild("ambrosium_corner_2");
        this.ambrosiumCorner3 = this.altar.getChild("ambrosium_corner_3");
        this.ambrosiumCorner4 = this.altar.getChild("ambrosium_corner_4");
        this.top = this.altar.getChild("top");
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
                .texOffs(48, 6).addBox(4.0F, -16.0F, 4.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        altar.addOrReplaceChild("ambrosium_corner_1", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -17.0F, -6.0F));
        altar.addOrReplaceChild("ambrosium_corner_2", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -17.0F, 6.0F));
        altar.addOrReplaceChild("ambrosium_corner_3", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -17.0F, 6.0F));
        altar.addOrReplaceChild("ambrosium_corner_4", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -17.0F, -6.0F));
        altar.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 56).addBox(-4.0F, -1.0F, -3.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    @Override
    public void render(AltarBlockEntity altarBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        if (GenesisConfig.STARTUP.altar_redesign.get()) {
            BlockState state = altarBlockEntity.getBlockState();
            Direction direction = state.getValue(AbstractFurnaceBlock.FACING);
            poseStack.pushPose();
            poseStack.translate(0.5F, 1.5F, 0.5F);
            poseStack.mulPose(Axis.XN.rotationDegrees(180));
            poseStack.mulPose(Axis.YP.rotationDegrees(direction.toYRot() - 90));
            VertexConsumer consumer = multiBufferSource.getBuffer(RenderType.entityCutout(ALTAR_LOCATION));
            this.altar.render(poseStack, consumer, packedLight, packedOverlay);
            this.ambrosiumCorner1.yRot = this.ambRotation;
            this.ambrosiumCorner2.yRot = this.ambRotation;
            this.ambrosiumCorner3.yRot = this.ambRotation;
            this.ambrosiumCorner4.yRot = this.ambRotation;
            poseStack.popPose();

            if (altarBlockEntity.getLevel() != null) {
                ItemStack itemStack = !altarBlockEntity.getItem(0).isEmpty() ? altarBlockEntity.getItem(0) : altarBlockEntity.getItem(2);
                if (this.bobOffs < 0) {
                    this.bobOffs = altarBlockEntity.getLevel().getRandom().nextFloat() * Mth.PI * 2.0F;
                }
                if (!itemStack.isEmpty()) {
                    poseStack.pushPose();
                    poseStack.translate(0.5, 1.0, 0.5);
                    altarBlockEntity.getLevel().getRandom().setSeed(ItemEntityRenderer.getSeedForItemStack(itemStack));
                    BakedModel bakedModel = this.itemRenderer.getModel(itemStack, altarBlockEntity.getLevel(), null, 0);
                    boolean flag = bakedModel.isGui3d();
                    float f1 = Mth.sin(this.inputItemRotation / 10.0F + this.bobOffs) * 0.1F + 0.1F;
                    float f2 = bakedModel.getTransforms().getTransform(ItemDisplayContext.GROUND).scale.y();
                    poseStack.translate(0.0F, f1 + 0.25F * f2, 0.0F);
                    float f3 = this.inputItemRotation / 20.0F + this.bobOffs;
                    poseStack.mulPose(Axis.YP.rotation(f3));
                    ItemEntityRenderer.renderMultipleFromCount(this.itemRenderer, poseStack, multiBufferSource, packedLight, itemStack, bakedModel, flag, altarBlockEntity.getLevel().getRandom());
                    this.inputItemRotation += 1.0F / 10.0F;
                    poseStack.popPose();
                }

                ItemStack fuelStack = altarBlockEntity.getItem(1);
                this.spin(fuelStack);
                if (!fuelStack.isEmpty()) {
                    int amount = fuelStack.getCount();
                    for (int i = 0; i < amount; i++) {
                        poseStack.pushPose();
                        float radius = 2.0F;
                        float theta = 5.0F;

                        float dist = Mth.PI * i / amount * 2.0F;
                        float x = radius * Mth.cos(theta + dist);
                        float y = 0.0F;
                        float z = radius * Mth.sin(theta + dist);
                        float deltaX = z * Mth.cos(this.ambrosiumFinalRotation) - x * Mth.sin(this.ambrosiumFinalRotation);
                        float deltaZ = x * Mth.cos(this.ambrosiumFinalRotation) + z * Mth.sin(this.ambrosiumFinalRotation);
                        poseStack.translate(0.5, 1.25, 0.5);
                        poseStack.scale(0.2F, 0.2F, 0.2F);
                        poseStack.translate(deltaX, y, deltaZ);

                        this.ambrosiumFinalRotation += this.ambSpinningSpeed / 100.0F;

                        Minecraft.getInstance().getItemRenderer().renderStatic(fuelStack, ItemDisplayContext.FIXED, packedLight, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, altarBlockEntity.getLevel(), 0);

                        poseStack.popPose();
                    }
                }
            }
        }
    }

    public void spin(ItemStack stack) {
        if (!stack.isEmpty()) {
            float rotationSpeed = 0.05F * stack.getCount() * 0.5F;
            this.ambRotation += rotationSpeed / 20.0F;
            float spinningSpeed;
            if (stack.getCount() < 4) {
                spinningSpeed = 0.2F * stack.getCount() * 0.5F;
            } else {
                spinningSpeed = 0.35F;
            }
            this.ambSpinningSpeed = spinningSpeed / 20.0F;
        } else {
            this.ambRotation = 0.0F;
            this.ambSpinningSpeed = 0.0F;
        }
    }
}
