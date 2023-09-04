package com.aetherteam.aether_genesis.mixin.mixins.client;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.item.accessories.FrostpineTotemItem;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Mixin(EffectRenderingInventoryScreen.class)
public class EffectRenderingInventoryScreenMixin {
    @ModifyVariable(method = "renderEffects", at = @At(value = "STORE"), index = 10)
    private Iterable<MobEffectInstance> injected(Iterable<MobEffectInstance> effects) {
        return StreamSupport.stream(effects.spliterator(), false).filter((effectInstance) -> !(effectInstance.toString().equals(FrostpineTotemItem.getTotemNightVisionEffect().toString()))).sorted().collect(Collectors.toList());
    }
}
