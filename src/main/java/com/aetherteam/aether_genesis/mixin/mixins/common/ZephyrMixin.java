package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.entity.monster.Zephyr;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zephyr.class)
public class ZephyrMixin extends FlyingMob {
    protected ZephyrMixin(EntityType<? extends FlyingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    /**
     * @author keletu
     * @reason Zephyr Will not spawn in night
     */
    @Overwrite(remap=false)
    public static boolean checkZephyrSpawnRules(EntityType<? extends Zephyr> zephyr, LevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && Mob.checkMobSpawnRules(zephyr, level, reason, pos, random) && level.getSkyDarken() < 4 && isDay(level);
    }

    private static boolean isDay(LevelAccessor level){
        return (level.getSkyDarken() < 4 && !level.dimensionType().hasFixedTime());
    }

    @Inject(at = @At(value = "HEAD"), method = "aiStep")
    private void nightTimeDead(CallbackInfo ci) {
        if(this.isAlive() && this.level.isNight() && !this.level.isClientSide)
            this.setHealth(0);
    }
}
