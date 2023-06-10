package com.aetherteam.aether_genesis.client.renderer;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.model.TrackingGolemModel;
import com.aetherteam.aether_genesis.entity.monster.TrackingGolem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class TrackingGolemLayer<T extends TrackingGolem, M extends TrackingGolemModel<T>> extends EyesLayer<T, M> {
	private static final RenderType SENTRY_EYE = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tracking_golem/sentry_golem_glow.png"));
	private static final RenderType SENTRY_EYE_GLOW = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tracking_golem/sentry_golem_hostile_glow.png"));

	public TrackingGolemLayer(RenderLayerParent<T, M> entityRenderer) {
		super(entityRenderer);
	}

	@Override
	public void render(@Nonnull PoseStack poseStack, MultiBufferSource buffer, int packedLight, @Nonnull TrackingGolem golem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		VertexConsumer consumer = buffer.getBuffer(this.renderType(golem));
		this.getParentModel().renderToBuffer(poseStack, consumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Nonnull
	public RenderType renderType(TrackingGolem golem) {
		if (golem.getSeenEnemy()) {
			return SENTRY_EYE_GLOW;
		}
		return this.renderType();
	}

	@Nonnull
	@Override
	public RenderType renderType() {
		return SENTRY_EYE ;
	}
}