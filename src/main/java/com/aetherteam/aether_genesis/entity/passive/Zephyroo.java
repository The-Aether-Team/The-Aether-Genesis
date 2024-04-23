package com.aetherteam.aether_genesis.entity.passive;

import com.aetherteam.aether.entity.passive.AetherAnimal;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.entity.ai.goal.ZephyrooGrazeGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class Zephyroo extends AetherAnimal {
    private int eatAnimationTick = 0;
    public Zephyroo(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.moveControl = new ZephyrooMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new ZephyrooGrazeGoal(this));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 16));

    }

    public static AttributeSupplier.Builder createZephyrooAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 40).add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.eatAnimationTick > 0) {
            this.eatAnimationTick--;
        }
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 10) {
            this.eatAnimationTick = 40;
        } else {
            super.handleEntityEvent(pId);
        }
    }

    public float getHeadEatAngleScale(float pPartialTick) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float f = ((float)(this.eatAnimationTick - 4) - pPartialTick) / 32.0F;
            return (float) (Math.PI / 5) + 0.21991149F * Mth.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? (float) (Math.PI / 5) : this.getXRot() * (float) (Math.PI / 180.0);
        }
    }

    public int getEatAnimationTick() {
        return eatAnimationTick;
    }

    public void setEatAnimationTick(int eatAnimationTick) {
        this.eatAnimationTick = eatAnimationTick;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return GenesisEntityTypes.ZEPHYROO.get().create(level);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return GenesisSoundEvents.ENTITY_ZEPHYROO_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return GenesisSoundEvents.ENTITY_ZEPHYROO_DEATH.get();
    }

    public static class ZephyrooMoveControl extends MoveControl {
        private final Zephyroo zephyroo;
        public boolean hopping = true;
        public ZephyrooMoveControl(Zephyroo mob) {
            super(mob);
            this.zephyroo = mob;
        }

        // Two modes - grazing, normal (hopping)
        // Should be able to jump 3.3 blocks high onto a ledge
        // Mid-air jumps might be nice, too
        @Override
        public void tick() {
            if (hopping) {
                if (zephyroo.onGround() && zephyroo.zza > 0.0) {
                    zephyroo.jumpControl.jump();
                }
            } else {
                super.tick();
            }
        }
    }
}
