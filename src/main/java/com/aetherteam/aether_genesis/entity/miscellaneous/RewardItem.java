package com.aetherteam.aether_genesis.entity.miscellaneous;

import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RewardItem extends ItemEntity {
    public RewardItem(EntityType<? extends RewardItem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public RewardItem(Level pLevel, double pPosX, double pPosY, double pPosZ, ItemStack pItemStack) {
        this(pLevel, pPosX, pPosY, pPosZ, pItemStack, pLevel.random.nextDouble() * 0.2D - 0.1D, 0.2D, pLevel.random.nextDouble() * 0.2D - 0.1D);
    }

    public RewardItem(Level pLevel, double pPosX, double pPosY, double pPosZ, ItemStack pItemStack, double pDeltaX, double pDeltaY, double pDeltaZ) {
        this(GenesisEntityTypes.REWARD_ITEM.get(), pLevel);
        this.setPos(pPosX, pPosY, pPosZ);
        this.setDeltaMovement(pDeltaX, pDeltaY, pDeltaZ);
        this.setItem(pItemStack);
        this.setDefaultPickUpDelay();
    }

    public void tick() {
        if (this.getAge() == 0)
            this.level().playSound(this,this.getOnPos() , SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.AMBIENT, 3.0F, 0.75F + this.random.nextFloat());
        if (!this.onGround()) {
            if (this.level().isClientSide && this.getAge() % 2 < 2)
                this.level().addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY() - 0.3D, this.getZ(), this.random.nextGaussian() * 0.05D, - this.getMotionDirection().getStepY() * 0.5D, this.random.nextGaussian() * 0.05D);
            int sparkCount;
            for (sparkCount = 1; sparkCount <= 5; sparkCount++) {
                double motX = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                double motY = this.random.nextDouble();
                double motZ = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getX(), this.getY(), this.getZ(), motX, motY, motZ);
            }
            if (this.tickCount % 5 == 0)
                for (sparkCount = 1; sparkCount <= 10; sparkCount++) {
                    double motX = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                    double motY = this.random.nextDouble();
                    double motZ = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                    this.level().addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), motX / 2.0D, motY / 2.0D, motZ / 2.0D);
                }
        }
        super.tick();
    }
}
