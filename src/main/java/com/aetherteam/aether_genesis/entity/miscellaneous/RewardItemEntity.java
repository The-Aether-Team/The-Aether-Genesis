package com.aetherteam.aether_genesis.entity.miscellaneous;

import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RewardItemEntity extends ItemEntity {
    private boolean hitGround = false;

    public RewardItemEntity(EntityType<RewardItemEntity> entityType, Level level) {
        super(entityType, level);
    }

    public RewardItemEntity(Level level, double x, double y, double z, ItemStack item) {
        this(level, x, y, z, item, level.getRandom().nextDouble() * 0.2 - 0.1, 0.2, level.getRandom().nextDouble() * 0.2 - 0.1);
    }

    public RewardItemEntity(Level level, double x, double y, double z, ItemStack item, double motX, double motY, double motZ) {
        this(GenesisEntityTypes.REWARD_ITEM.get(), level);
        this.setPos(x, y, z);
        this.setDeltaMovement(motX, motY, motZ);
        this.setItem(item);
        this.setDefaultPickUpDelay();
    }

    /**
     * Plays sounds and spawns particles that follow the item while it's flying in the air.
     */
    public void tick() {
        if (this.getAge() == 0) {
            this.level().playSound(this, this.getOnPos(), SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.AMBIENT, 3.0F, 0.75F + this.random.nextFloat()); //todo custom sound event
        }
        if (!this.hitGround) {
            if (this.level().isClientSide()) {
                this.level().addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY() - 0.3, this.getZ(), this.random.nextGaussian() * 0.05, - this.getMotionDirection().getStepY() * 0.5, this.random.nextGaussian() * 0.05);
            }
            for (int sparkCount = 1; sparkCount <= 5; sparkCount++) {
                double motX = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                double motY = this.random.nextDouble();
                double motZ = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getX(), this.getY(), this.getZ(), motX, motY, motZ);
            }
            if (this.tickCount % 5 == 0) {
                for (int sparkCount = 1; sparkCount <= 10; sparkCount++) {
                    double motX = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                    double motY = this.random.nextDouble();
                    double motZ = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                    this.level().addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), motX / 2.0, motY / 2.0, motZ / 2.0);
                }
            }
        }
        if (this.onGround()) {
            this.hitGround = true;
        }
        super.tick();
    }
}
