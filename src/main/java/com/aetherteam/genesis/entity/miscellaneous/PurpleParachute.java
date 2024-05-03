package com.aetherteam.genesis.entity.miscellaneous;

import com.aetherteam.aether.entity.miscellaneous.Parachute;
import com.aetherteam.aether.mixin.mixins.common.accessor.ServerGamePacketListenerImplAccessor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;

public class PurpleParachute extends Parachute {
    public PurpleParachute(EntityType<? extends Parachute> type, Level level) {
        super(type, level);
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
            float f1 = 1.25F; // Forward movement is forced.

            Vec3 travelVec = new Vec3(f, passenger.yya, f1);
            AttributeInstance gravity = passenger.getAttribute(NeoForgeMod.ENTITY_GRAVITY.value());
            double d0 = gravity != null ? gravity.getValue() : 0.08;
            Vec3 movement = this.calculateMovement(travelVec);
            double d2 = movement.y;
            if (!this.isNoGravity()) {
                d2 -= d0;
            }

            d2 *= 0.98;
            double fallSpeed = Math.max(d0 * -3.125, -0.25); // Slows fall speed and slows the parachute from falling too slow and getting stuck midair.
            this.setDeltaMovement(movement.x * 0.91F, Math.max(d2, fallSpeed), movement.z * 0.91F);
            if (passenger instanceof ServerPlayer serverPlayer) { // Prevents the player from being kicked for flying.
                ServerGamePacketListenerImplAccessor serverGamePacketListenerImplAccessor = (ServerGamePacketListenerImplAccessor)serverPlayer.connection;
                serverGamePacketListenerImplAccessor.aether$setAboveGroundTickCount(0);
                serverGamePacketListenerImplAccessor.aether$setAboveGroundVehicleTickCount(0);
            }
        }
    }
}
