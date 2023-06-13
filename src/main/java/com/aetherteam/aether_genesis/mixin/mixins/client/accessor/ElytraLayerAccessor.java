package com.aetherteam.aether_genesis.mixin.mixins.client.accessor;

import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ElytraLayer.class)
public interface ElytraLayerAccessor<T extends LivingEntity> {
    @Accessor("elytraModel")
    ElytraModel<T> aether$getElytraModel();
}
