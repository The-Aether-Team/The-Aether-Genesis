package com.aetherteam.aether_genesis.entity.passive;

import com.aetherteam.aether.entity.passive.AetherAnimal;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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
    public Zephyroo(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this)); // TODO This shouldn't be needed
        goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0));
        goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 16));

    }

    public static AttributeSupplier.Builder createZephyrooAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 40).add(Attributes.MOVEMENT_SPEED, 0.2);
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

    static class ZephyrooMoveControl extends MoveControl {
        private final Zephyroo zephyroo;
        public ZephyrooMoveControl(Zephyroo mob) {
            super(mob);
            this.zephyroo = mob;
        }

        // Three modes - grazing, normal (hopping), combat (goofy easter egg)
        // Should be able to jump 3.3 blocks high onto a ledge
        // Mid-air jumps might be nice, too
        @Override
        public void tick() {
            super.tick();
        }
    }
}
