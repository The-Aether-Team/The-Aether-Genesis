package com.aetherteam.aether_genesis.entity.projectile;

import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class DaggerfrostSnowball extends Snowball {
    public DaggerfrostSnowball(Level pLevel) {
        super(GenesisEntityTypes.DAGGERFROST_SNOWBALL.get(), pLevel);
    }

    public DaggerfrostSnowball(EntityType<DaggerfrostSnowball> daggerfrostSnowballEntityType, Level level) {
        super(daggerfrostSnowballEntityType, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int i = entity instanceof Blaze ? 3 : 1;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)i);
    }
}
