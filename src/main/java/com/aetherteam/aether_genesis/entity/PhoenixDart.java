package com.aetherteam.aether_genesis.entity;

import com.aetherteam.aether.entity.projectile.dart.GoldenDart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class PhoenixDart extends GoldenDart {
    public PhoenixDart(Level level) {
        super(GenesisEntityTypes.PHOENIX_DART.get(), level);
        this.setBaseDamage(4);
    }

    public PhoenixDart(EntityType<? extends GoldenDart> type, Level level) {
        super(type, level);
    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level.isClientSide) {
            Entity entity = pResult.getEntity();
            entity.setSecondsOnFire(5);
        }
    }

    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!this.level.isClientSide) {
            Entity entity = this.getOwner();
            if (!(entity instanceof Mob) || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, entity)) {
                BlockPos blockpos = pResult.getBlockPos().relative(pResult.getDirection());
                if (this.level.isEmptyBlock(blockpos)) {
                    this.level.setBlockAndUpdate(blockpos, BaseFireBlock.getState(this.level, blockpos));
                }
            }

        }
    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level.isClientSide) {
            this.discard();
        }

    }

    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            for (int i = 0; i < 2; i++)
                this.spawnParticles(this);
        }else
            this.setSecondsOnFire(1);
    }

    private void spawnParticles(AbstractArrow arrow) {
        if (arrow.level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.FLAME,
                    arrow.getX() + (serverLevel.getRandom().nextGaussian() / 5.0D),
                    arrow.getY() + (serverLevel.getRandom().nextGaussian() / 3.0D),
                    arrow.getZ() + (serverLevel.getRandom().nextGaussian() / 5.0D),
                    1, 0.0D, 0.0D, 0.0D, 0.0F);
        }
    }
}
