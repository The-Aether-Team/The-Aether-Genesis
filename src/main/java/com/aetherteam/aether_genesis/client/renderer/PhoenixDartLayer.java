package com.aetherteam.aether_genesis.client.renderer;

import com.aetherteam.aether.client.renderer.player.layer.GoldenDartLayer;
import com.aetherteam.aether.entity.projectile.dart.AbstractDart;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.entity.PhoenixDart;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;

public class PhoenixDartLayer<T extends LivingEntity, M extends PlayerModel<T>> extends GoldenDartLayer<T, M> {
    public PhoenixDartLayer(EntityRenderDispatcher renderDispatcher, LivingEntityRenderer<T, M> renderer) {
        super(renderDispatcher, renderer);
    }

    @Override
    protected void renderStuckItem(@Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int packedLight, @Nonnull Entity entity, float f, float f1, float f2, float partialTicks) {
        AbstractDart dart = new PhoenixDart(GenesisEntityTypes.PHOENIX_DART.get(), entity.level);
        dart.setPos(entity.getX(), entity.getY(), entity.getZ());
        this.renderStuckDart(dart, poseStack, buffer, packedLight, entity, f, f1, f2, partialTicks);
    }
}