package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether.entity.monster.dungeon.boss.slider.Slider;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.model.SentryGuardianModel;
import com.aetherteam.aether_genesis.entity.monster.boss.SentryGuardian;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SentryGuardianLayer<T extends SentryGuardian, M extends SentryGuardianModel<T>> extends EyesLayer<T, M> {
	private static final RenderType GUARDIAN_EYE = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/sentry_guardian/sentry_guardian_glow.png"));
	private static final RenderType GUARDIAN_EYE_LIT = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/sentry_guardian/sentry_guardian_critical_glow.png"));

	public SentryGuardianLayer(RenderLayerParent<T, M> entityRenderer) {
		super(entityRenderer);
	}

	@Override
	public void render(@Nonnull PoseStack poseStack, MultiBufferSource buffer, int packedLight, @Nonnull SentryGuardian guardian, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		VertexConsumer consumer = buffer.getBuffer(this.renderType(guardian));
		this.getParentModel().renderToBuffer(poseStack, consumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Nonnull
	public RenderType renderType(SentryGuardian guardian) {
		if (guardian.isAwake()) {
			return GUARDIAN_EYE_LIT;
		}
		return this.renderType();
	}

	@Nonnull
	@Override
	public RenderType renderType() {
		return GUARDIAN_EYE;
	}
}