package com.aetherteam.aether_genesis.entity.miscellaneous;

import com.aetherteam.aether.entity.miscellaneous.Parachute;
import com.aetherteam.aether.mixin.mixins.common.accessor.ServerGamePacketListenerImplAccessor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BlueParachute extends Parachute {
    public BlueParachute(EntityType<? extends Parachute> type, Level level) {
        super(type, level);
    }

    public void tick() {
        super.tick();
        LivingEntity passenger = this.getControllingPassenger();
        if (passenger != null) {
            if (this.getY() >= this.getLevel().getMaxBuildHeight()) {
                this.ejectPassengers();
                this.die();
            }
        }
    }

    @Override
    public void moveParachute(LivingEntity passenger) {
        if (this.isVehicle()) {
            this.setYRot(passenger.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(passenger.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            float f = passenger.xxa * 0.5F;
            float f1 = passenger.zza;
            if (f1 <= 0.0F) {
                f1 *= 0.25F;
            }

            Vec3 travelVec = new Vec3(f, passenger.yya, f1);
            Vec3 movement = this.calculateMovement(travelVec);

            double ascendSpeed = 0.75D;
            this.setDeltaMovement(movement.x * 0.9100000262260437D, ascendSpeed, movement.z * 0.9100000262260437D);
            if (passenger instanceof ServerPlayer serverPlayer) {
                ServerGamePacketListenerImplAccessor serverGamePacketListenerImplAccessor = (ServerGamePacketListenerImplAccessor) serverPlayer.connection;
                serverGamePacketListenerImplAccessor.aether$setAboveGroundTickCount(0);
                serverGamePacketListenerImplAccessor.aether$setAboveGroundVehicleTickCount(0);
            }
        }
    }
}
