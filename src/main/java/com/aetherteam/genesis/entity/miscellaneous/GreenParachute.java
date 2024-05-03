package com.aetherteam.genesis.entity.miscellaneous;

import com.aetherteam.aether.entity.miscellaneous.Parachute;
import com.aetherteam.aether.mixin.mixins.common.accessor.ServerGamePacketListenerImplAccessor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;

public class GreenParachute extends Parachute {
    public GreenParachute(EntityType<? extends Parachute> type, Level level) {
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
            float f = passenger.xxa; // Side-to-side movement is normal.
            float f1 = passenger.zza; // Forward movement is normal.
            if (f1 <= 0.0F) {
                f1 *= 0.25F; // Backwards movement is slowed.
            }

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

    /**
     * Calculates the movement vector for the parachute from the passenger.
     *
     * @param vec3 The passenger {@link Vec3}.
     * @return The modified {@link Vec3}.
     */
    public Vec3 calculateMovement(Vec3 vec3) {
        float speed = 0.045F; // Faster speed than normal cold parachutes.
        this.moveRelative(speed, vec3);
        this.move(MoverType.SELF, this.getDeltaMovement());
        return this.getDeltaMovement();
    }
}
