package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrostboundSprite extends CompanionMob {
    public FrostboundSprite(EntityType<FrostboundSprite> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.FROSTBOUND_STONE.get()), true);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            boolean isMoving = this.getDeltaMovement().x() > 0.01D || this.getDeltaMovement().y() > 0.1D || this.getDeltaMovement().z() > 0.01D || this.getDeltaMovement().x() < -0.01D || this.getDeltaMovement().y() < -0.1D || this.getDeltaMovement().z() < -0.01D;
            if (this.tickCount % 40 == 0 || isMoving && this.tickCount % 3 == 0) {
                double radius = 0.2D;

                for (double y = this.position().y(); y <= this.position().y() + 1.5D; y += 0.1D) {
                    double x = (radius *= 1.003D) * Math.cos(Math.pow(y, 2.0D)) + 0.0D;
                    double z = radius * Math.sin(Math.pow(y, 2.0D) * 10.0D) - 0.05D;
                    double motX = (double) (this.getRandom().nextBoolean() ? -1 : 1) * this.getRandom().nextDouble();
                    double motY = (double) (this.getRandom().nextBoolean() ? -1 : 1) * this.getRandom().nextDouble();
                    double motZ = (double) (this.getRandom().nextBoolean() ? -1 : 1) * this.getRandom().nextDouble();

                    this.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.position().x() + x, y, this.position().z() + z, motX / 55.0D, motY / 55.0D, motZ / 55.0D);
                }
            }
        }
    }
}
