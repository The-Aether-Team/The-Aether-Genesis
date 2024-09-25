package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.layers.BattleSentryLayer;
import com.aetherteam.genesis.entity.monster.dungeon.BattleSentry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BattleSentryRenderer extends MobRenderer<BattleSentry, SlimeModel<BattleSentry>> {
    private static final ResourceLocation BATTLE_SENTRY_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/battle_sentry/battle_sentry.png");
    private static final ResourceLocation BATTLE_SENTRY_LIT_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/mobs/battle_sentry/battle_sentry_lit.png");

    public BattleSentryRenderer(EntityRendererProvider.Context context) {
        super(context, new SlimeModel<>(context.bakeLayer(GenesisModelLayers.BATTLE_SENTRY)), 0.3F);
        this.addLayer(new BattleSentryLayer(this));
    }

    @Override
    protected void scale(BattleSentry sentry, PoseStack poseStack, float partialTickTime) {
        float f = 0.879F;
        poseStack.scale(f, f, f);
        float f1 = sentry.getSize() + 1.0F;
        poseStack.scale(f1, f1, f1);
        if (!sentry.isAwake() && sentry.isStalking()) {
            poseStack.translate(0.0F, 0.5F, 0.0F);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(BattleSentry sentry) {
        return sentry.isAwake() ? BATTLE_SENTRY_LIT_TEXTURE : BATTLE_SENTRY_TEXTURE;
    }
}
