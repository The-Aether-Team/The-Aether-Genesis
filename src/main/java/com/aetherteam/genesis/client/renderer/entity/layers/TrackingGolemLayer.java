package com.aetherteam.genesis.client.renderer.entity.layers;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.entity.model.TrackingGolemModel;
import com.aetherteam.genesis.entity.monster.dungeon.TrackingGolem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class TrackingGolemLayer extends EyesLayer<TrackingGolem, TrackingGolemModel> {
	private static final RenderType TRACKING_GOLEM_GLOW = RenderType.eyes(new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/tracking_golem/sentry_golem_glow.png"));
	private static final RenderType TRACKING_GOLEM_HOSTILE_GLOW = RenderType.eyes(new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/tracking_golem/sentry_golem_hostile_glow.png"));

	public TrackingGolemLayer(RenderLayerParent<TrackingGolem, TrackingGolemModel> entityRenderer) {
		super(entityRenderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, TrackingGolem golem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		VertexConsumer consumer = buffer.getBuffer(this.renderType(golem));
		this.getParentModel().renderToBuffer(poseStack, consumer, LightTexture.FULL_SKY, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public RenderType renderType(TrackingGolem golem) {
		if (golem.getSeenEnemy()) {
			return TRACKING_GOLEM_HOSTILE_GLOW;
		}
		return this.renderType();
	}
	
	@Override
	public RenderType renderType() {
		return TRACKING_GOLEM_GLOW;
	}
}