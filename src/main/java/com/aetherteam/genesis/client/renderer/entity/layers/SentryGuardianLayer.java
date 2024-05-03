package com.aetherteam.genesis.client.renderer.entity.layers;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.client.renderer.entity.model.SentryGuardianModel;
import com.aetherteam.genesis.entity.monster.dungeon.boss.SentryGuardian;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SentryGuardianLayer extends EyesLayer<SentryGuardian, SentryGuardianModel> {
	private static final RenderType SENTRY_GUARDIAN_GLOW = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/sentry_guardian/sentry_guardian_glow.png"));
	private static final RenderType SENTRY_GUARDIAN_CRITICAL_GLOW = RenderType.eyes(new ResourceLocation(Genesis.MODID, "textures/entity/mobs/sentry_guardian/sentry_guardian_critical_glow.png"));

	public SentryGuardianLayer(RenderLayerParent<SentryGuardian, SentryGuardianModel> entityRenderer) {
		super(entityRenderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, SentryGuardian guardian, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		VertexConsumer consumer = buffer.getBuffer(this.renderType(guardian));
		this.getParentModel().renderToBuffer(poseStack, consumer, LightTexture.FULL_SKY, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	public RenderType renderType(SentryGuardian guardian) {
		if (guardian.isAwake()) {
			return SENTRY_GUARDIAN_CRITICAL_GLOW;
		}
		return this.renderType();
	}

	@Override
	public RenderType renderType() {
		return SENTRY_GUARDIAN_GLOW;
	}
}