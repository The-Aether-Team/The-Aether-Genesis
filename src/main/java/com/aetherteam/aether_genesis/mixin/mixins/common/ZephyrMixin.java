package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.entity.monster.Zephyr;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zephyr.class)
public class ZephyrMixin extends FlyingMob {
    protected ZephyrMixin(EntityType<? extends FlyingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(at = @At(value = "HEAD"), method = "aiStep")
    private void nightTimeDead(CallbackInfo ci) {
        if(this.isAlive() && this.level.isNight() && !this.level.isClientSide)
            this.setHealth(0);
    }
}
