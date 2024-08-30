package com.aetherteam.genesis.client.renderer.entity.layers;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.entity.model.SliderHostMimicModel;
import com.aetherteam.genesis.entity.monster.dungeon.boss.SliderHostMimic;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class HostMimicLayer extends EyesLayer<SliderHostMimic, SliderHostMimicModel> {
	private static final RenderType HOST_MIMIC_CRITICAL_GLOW = RenderType.eyes(new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/slider_host_mimic/slider_host_mimic_critical_glow.png"));

	public HostMimicLayer(RenderLayerParent<SliderHostMimic, SliderHostMimicModel> entityRenderer) {
		super(entityRenderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, SliderHostMimic hostMimic, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		VertexConsumer consumer = buffer.getBuffer(this.renderType());
		if (hostMimic.isAwake()) {
			this.getParentModel().renderToBuffer(poseStack, consumer, LightTexture.FULL_SKY, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public RenderType renderType() {
		return HOST_MIMIC_CRITICAL_GLOW;
	}
}