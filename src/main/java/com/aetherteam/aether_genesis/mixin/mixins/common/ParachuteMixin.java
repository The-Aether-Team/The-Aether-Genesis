package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.miscellaneous.Parachute;
import com.aetherteam.aether.mixin.mixins.common.accessor.ServerGamePacketListenerImplAccessor;
import com.aetherteam.aether_genesis.GenesisConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Parachute.class)
public class ParachuteMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lcom/aetherteam/aether/entity/miscellaneous/Parachute;setYRot(F)V", shift = At.Shift.BEFORE), method = "moveParachute(Lnet/minecraft/world/entity/LivingEntity;)V", cancellable = true)
    private void moveParachute(LivingEntity passenger, CallbackInfo ci) {
        Parachute parachute = (Parachute) (Object) this;
        if (parachute.getType() == AetherEntityTypes.GOLDEN_PARACHUTE.get() && GenesisConfig.COMMON.gold_aercloud_ability.get()) {
            parachute.setYRot(passenger.getYRot());
            parachute.yRotO = parachute.getYRot();
            parachute.setXRot(passenger.getXRot() * 0.5F);
            parachute.setYRot(parachute.getYRot() % 360.0F);
            parachute.setXRot(parachute.getXRot() % 360.0F);
            float f = passenger.xxa * 0.5F;
            float f1 = passenger.zza;
            if (f1 <= 0.0F) {
                f1 *= 0.25F;
            }

            Vec3 travelVec = new Vec3(f, passenger.yya, f1);
            Vec3 movement = parachute.calculateMovement(travelVec);

            double descendSpeed = -0.75D;
            parachute.setDeltaMovement(movement.x * 0.9100000262260437D, descendSpeed, movement.z * 0.9100000262260437D);
            if (passenger instanceof ServerPlayer serverPlayer) {
                ServerGamePacketListenerImplAccessor serverGamePacketListenerImplAccessor = (ServerGamePacketListenerImplAccessor) serverPlayer.connection;
                serverGamePacketListenerImplAccessor.aether$setAboveGroundTickCount(0);
                serverGamePacketListenerImplAccessor.aether$setAboveGroundVehicleTickCount(0);
            }
            ci.cancel();
        }
    }
}
