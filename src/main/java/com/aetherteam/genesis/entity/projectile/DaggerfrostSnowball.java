package com.aetherteam.genesis.entity.projectile;

import com.aetherteam.genesis.entity.GenesisEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class DaggerfrostSnowball extends Snowball {
    public DaggerfrostSnowball(Level level) {
        super(GenesisEntityTypes.DAGGERFROST_SNOWBALL.get(), level);
    }

    public DaggerfrostSnowball(EntityType<DaggerfrostSnowball> entityType, Level level) {
        super(entityType, level);
    }

    /**
     *
     */
    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        int i = entity instanceof Blaze ? 3 : 1;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)i);
    }
}
