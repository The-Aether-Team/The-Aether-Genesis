package com.aetherteam.genesis.entity.companion;

import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrostboundSprite extends FloatingCompanion {
    public FrostboundSprite(EntityType<FrostboundSprite> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.FROSTBOUND_STONE.get()));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            boolean isMoving = this.getDeltaMovement().x() > 0.01 || this.getDeltaMovement().y() > 0.1 || this.getDeltaMovement().z() > 0.01 || this.getDeltaMovement().x() < -0.01 || this.getDeltaMovement().y() < -0.1 || this.getDeltaMovement().z() < -0.01;
            if (this.tickCount % 40 == 0 || isMoving && this.tickCount % 8 == 0) { // Spawns particles around the base of the Frostbound Sprite for every 40 ticks or every 8 ticks if it's moving.
                double radius = 0.2;
                for (double y = this.position().y(); y <= this.position().y() + 1.5; y += 0.1) {
                    double x = (radius *= 1.003) * Math.cos(Math.pow(y, 2.0)) + 0.0;
                    double z = radius * Math.sin(Math.pow(y, 2.0) * 10.0) - 0.05;
                    double motX = (double) (this.getRandom().nextBoolean() ? -1 : 1) * this.getRandom().nextDouble();
                    double motY = (double) (this.getRandom().nextBoolean() ? -1 : 1) * this.getRandom().nextDouble();
                    double motZ = (double) (this.getRandom().nextBoolean() ? -1 : 1) * this.getRandom().nextDouble();
                    this.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.position().x() + x, y, this.position().z() + z, motX / 55.0, motY / 55.0, motZ / 55.0);
                }
            }
        }
    }
}
