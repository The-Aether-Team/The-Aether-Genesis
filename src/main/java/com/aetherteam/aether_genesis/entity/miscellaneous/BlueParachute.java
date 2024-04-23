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
            if (this.getY() >= this.level().getMaxBuildHeight()) { // The parachute breaks when it reaches max world height.
                this.ejectPassengers();
                this.die();
            }
        }
    }

    /**
     * Handles parachute and passenger movement.
     *
     * @param passenger The {@link LivingEntity} passenger.
     */
    @Override
    public void moveParachute(LivingEntity passenger) {
        if (this.isVehicle()) {
            this.setYRot(passenger.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(passenger.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            float f = passenger.xxa * 0.5F; // Side-to-side movement is slowed.
            float f1 = passenger.zza; // Forward movement is normal.
            if (f1 <= 0.0F) {
                f1 *= 0.25F; // Backwards movement is slowed.
            }

            Vec3 travelVec = new Vec3(f, passenger.yya, f1);
            Vec3 movement = this.calculateMovement(travelVec);

            this.setDeltaMovement(movement.x * 0.91F, 0.75F, movement.z * 0.91F); // Applies vertical motion to the passenger.
            if (passenger instanceof ServerPlayer serverPlayer) { // Prevents the player from being kicked for flying.
                ServerGamePacketListenerImplAccessor serverGamePacketListenerImplAccessor = (ServerGamePacketListenerImplAccessor) serverPlayer.connection;
                serverGamePacketListenerImplAccessor.aether$setAboveGroundTickCount(0);
                serverGamePacketListenerImplAccessor.aether$setAboveGroundVehicleTickCount(0);
            }
        }
    }
}
