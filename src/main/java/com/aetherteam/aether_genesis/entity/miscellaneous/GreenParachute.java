package com.aetherteam.aether_genesis.entity.miscellaneous;

import com.aetherteam.aether.entity.miscellaneous.Parachute;
import com.aetherteam.aether.mixin.mixins.common.accessor.ServerGamePacketListenerImplAccessor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

public class GreenParachute extends Parachute {
    public GreenParachute(EntityType<? extends Parachute> type, Level level) {
        super(type, level);
    }

    @Override
    public void moveParachute(LivingEntity passenger) {
        if (this.isVehicle()) {
            this.setYRot(passenger.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(passenger.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            float f = passenger.xxa;
            float f1 = passenger.zza;
            if (f1 <= 0.0F) {
                f1 *= 0.25F;
            }

            Vec3 travelVec = new Vec3(f, passenger.yya, f1);
            AttributeInstance gravity = passenger.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
            double d0 = gravity != null ? gravity.getValue() : 0.08D;
            Vec3 movement = this.calculateMovement(travelVec);
            double d2 = movement.y;
            if (!this.isNoGravity()) {
                d2 -= d0;
            }

            d2 *= 0.98D;
            double fallSpeed = Math.max(d0 * -3.125D, -0.25D);
            this.setDeltaMovement(movement.x * 0.9100000262260437D, Math.max(d2, fallSpeed), movement.z * 0.9100000262260437D);
            if (passenger instanceof ServerPlayer serverPlayer) {
                ServerGamePacketListenerImplAccessor serverGamePacketListenerImplAccessor = (ServerGamePacketListenerImplAccessor)serverPlayer.connection;
                serverGamePacketListenerImplAccessor.aether$setAboveGroundTickCount(0);
                serverGamePacketListenerImplAccessor.aether$setAboveGroundVehicleTickCount(0);
            }
        }
    }

    public Vec3 calculateMovement(Vec3 vec3) {
        float speed = 0.045F;
        this.moveRelative(speed, vec3);
        this.move(MoverType.SELF, this.getDeltaMovement());
        return this.getDeltaMovement();
    }
}
