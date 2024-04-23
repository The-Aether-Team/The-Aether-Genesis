package com.aetherteam.aether_genesis.client.renderer.blockentity;

import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.block.dungeon.SkyrootChestMimicBlock;
import com.aetherteam.aether_genesis.block.utility.SkyrootChestBlock;
import com.aetherteam.aether_genesis.blockentity.SkyrootChestMimicBlockEntity;
import com.aetherteam.aether_genesis.client.GenesisAtlases;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Calendar;

/**
 * [CODE COPY] - {@link com.aetherteam.aether.client.renderer.blockentity.ChestMimicRenderer}
 */
public class SkyrootChestMimicRenderer implements BlockEntityRenderer<SkyrootChestMimicBlockEntity> {
	//private static final Material LOOTR_MATERIAL = new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation("lootr", "chest")); //todo
	private final ModelPart lid;
	private final ModelPart bottom;
	private final ModelPart lock;
	private boolean xmasTextures = false;

	public SkyrootChestMimicRenderer(BlockEntityRendererProvider.Context context) {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26) {
			this.xmasTextures = true;
		}
		ModelPart root = context.bakeLayer(GenesisModelLayers.SKYROOT_CHEST_MIMIC);
		this.bottom = root.getChild("bottom");
		this.lid = root.getChild("lid");
		this.lock = root.getChild("lock");
	}

	@Override
	public void render(SkyrootChestMimicBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		BlockState blockState = blockEntity.getLevel() != null ? blockEntity.getBlockState() : GenesisBlocks.SKYROOT_CHEST_MIMIC.get().defaultBlockState().setValue(SkyrootChestBlock.FACING, Direction.SOUTH);
		if (blockState.getBlock() instanceof SkyrootChestMimicBlock) {
			poseStack.pushPose();
			float f = blockState.getValue(SkyrootChestBlock.FACING).toYRot();
			poseStack.translate(0.5, 0.5, 0.5);
			poseStack.mulPose(Axis.YP.rotationDegrees(-f));
			poseStack.translate(-0.5, -0.5, -0.5);
			Material material = this.getMaterial();
			VertexConsumer vertexconsumer = material.buffer(buffer, RenderType::entityCutout);
			this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, packedLight, packedOverlay);
			poseStack.popPose();
		}
	}

	private void render(PoseStack poseStack, VertexConsumer consumer, ModelPart chestLid, ModelPart chestLatch, ModelPart chestBottom, int packedLight, int packedOverlay) {
		chestLid.render(poseStack, consumer, packedLight, packedOverlay);
		chestLatch.render(poseStack, consumer, packedLight, packedOverlay);
		chestBottom.render(poseStack, consumer, packedLight, packedOverlay);
	}

	private Material getMaterial() {
//		if (ModList.get().isLoaded("lootr")) {
//			if (!ConfigManager.isVanillaTextures()) {
//				return LOOTR_MATERIAL;
//			}
//		}
		if (this.xmasTextures) {
			return Sheets.CHEST_XMAS_LOCATION;
		} else {
			return GenesisAtlases.SKYROOT_CHEST_MATERIAL;
		}
	}
}