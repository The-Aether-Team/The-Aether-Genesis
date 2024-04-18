package com.aetherteam.aether_genesis.client.renderer.player.layer;

import com.aetherteam.aether.client.renderer.player.layer.DartLayer;
import com.aetherteam.aether.entity.projectile.dart.AbstractDart;
import com.aetherteam.aether_genesis.attachment.GenesisDataAttachments;
import com.aetherteam.aether_genesis.attachment.GenesisPlayerAttachment;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Function;

public class PhoenixDartLayer<T extends LivingEntity, M extends PlayerModel<T>> extends DartLayer<T, M> {
    private final Function<GenesisPlayerAttachment, Integer> dartCount;

    public PhoenixDartLayer(EntityRenderDispatcher renderDispatcher, LivingEntityRenderer<T, M> renderer, Function<Entity, AbstractDart> dart, Function<GenesisPlayerAttachment, Integer> dartCount, float offset) {
        super(renderDispatcher, renderer, dart, null, offset);
        this.dartCount = dartCount;
    }

    @Override
    protected int numStuck(T entity) {
        if (entity instanceof Player player) {
            return this.dartCount.apply(player.getData(GenesisDataAttachments.GENESIS_PLAYER));
        }
        return 0;
    }
}
