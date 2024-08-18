package com.aetherteam.genesis.entity.projectile;

import com.aetherteam.genesis.entity.monster.dungeon.SentryGolem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class DetonationProjectile extends ThrowableProjectile {
    public DetonationProjectile(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DetonationProjectile(EntityType<? extends ThrowableProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public DetonationProjectile(EntityType<? extends ThrowableProjectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 0.5F, Level.ExplosionInteraction.NONE);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!(result.getEntity() instanceof SentryGolem)) {
            result.getEntity().hurt(this.level().damageSources().explosion(result.getEntity(), result.getEntity()), 3);
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 0.5F, Level.ExplosionInteraction.NONE);
        }
    }
}
