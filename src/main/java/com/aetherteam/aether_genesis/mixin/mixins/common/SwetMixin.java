package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether.item.EquipmentUtil;
import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Swet.class)
public class SwetMixin {
    @Inject(at = @At(value = "HEAD"), method = "isFriendlyTowardEntity", cancellable = true, remap = false)
    private void getDefaultCollisionShape(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (EquipmentUtil.hasCurio(entity, GenesisItems.BABY_PINK_SWET.get())) {
            cir.setReturnValue(true);
        }
    }
}
