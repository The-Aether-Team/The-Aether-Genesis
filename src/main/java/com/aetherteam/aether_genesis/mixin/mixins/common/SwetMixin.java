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
public class SwetMixin { //todo: add a tag for valid items in the base aether mod for swet friendly accessories
    /**
     * Makes Baby Pink Swets into a valid accessory for making Swets friendly.
     */
    @Inject(at = @At(value = "HEAD"), method = "isFriendlyTowardEntity(Lnet/minecraft/world/entity/LivingEntity;)Z", cancellable = true, remap = false)
    private void isFriendlyTowardEntity(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (EquipmentUtil.hasCurio(entity, GenesisItems.BABY_PINK_SWET.get())) {
            cir.setReturnValue(true);
        }
    }
}
