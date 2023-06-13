package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.entity.layers.BattleSentryLayer;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.entity.monster.BattleSentry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class BattleSentryRenderer extends MobRenderer<BattleSentry, SlimeModel<BattleSentry>> {
    private static final ResourceLocation BATTLE_SENTRY_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/battle_sentry/battle_sentry.png");
    private static final ResourceLocation BATTLE_SENTRY_LIT_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/battle_sentry/battle_sentry_lit.png");

    public BattleSentryRenderer(EntityRendererProvider.Context context) {
        super(context, new SlimeModel<>(context.bakeLayer(GenesisModelLayers.BATTLE_SENTRY)), 0.3F);
        this.addLayer(new BattleSentryLayer<>(this));
    }

    @Override
    protected void scale(BattleSentry sentry, PoseStack poseStack, float partialTickTime) {
        float f = 0.879F;
        poseStack.scale(f, f, f);
        float f1 = sentry.getSize() + 1.0F;
        float f2 = 0.0F;
        float f3 = 1.0F / (f2 + 1.0F);
        poseStack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    public void render(BattleSentry pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isAwake() && !pEntity.hurtMarked)
            super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
        if (!pEntity.isAwake() || pEntity.hurtMarked) {
            pMatrixStack.pushPose();
            pMatrixStack.translate(0.0F, -0.75F, 0.0F);
            super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
            pMatrixStack.popPose();
        }
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(BattleSentry sentry) {
        return sentry.isAwake() ? BATTLE_SENTRY_LIT_TEXTURE : BATTLE_SENTRY_TEXTURE;
    }
}
