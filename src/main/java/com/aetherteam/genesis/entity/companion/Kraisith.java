package com.aetherteam.genesis.entity.companion;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.genesis.entity.ai.goal.CompanionHurtByTargetGoal;
import com.aetherteam.genesis.entity.ai.goal.CompanionHurtTargetGoal;
import com.aetherteam.genesis.entity.monster.dungeon.boss.SliderHostMimic;
import com.aetherteam.genesis.entity.projectile.EnchantedNeedle;
import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.UUID;

public class Kraisith extends CompanionMob implements Combative, RangedAttackMob {
    public Kraisith(EntityType<Kraisith> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.KRAISITH_CAPSULE.get()), false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AvoidEnemyGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0, 60, 10.0F));
        this.targetSelector.addGoal(1, new CompanionHurtByTargetGoal<>(this));
        this.targetSelector.addGoal(2, new CompanionHurtTargetGoal<>(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 50.0).add(Attributes.MOVEMENT_SPEED, 0.3).add(Attributes.FOLLOW_RANGE, 48.0);
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity owner) { //todo
        if (target instanceof Creeper || target instanceof Ghast) {
            return false;
        } else if (target instanceof Wolf wolf) {
            return !wolf.isTame() || wolf.getOwner() != owner;
        } else if (target instanceof Player && owner instanceof Player && !((Player)owner).canHarmPlayer((Player)target)) {
            return false;
        } else if (target instanceof AbstractHorse && ((AbstractHorse)target).isTamed()) {
            return false;
        } else {
            return !(target instanceof TamableAnimal) || !((TamableAnimal)target).isTame();
        }
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        EnchantedNeedle needle = new EnchantedNeedle(this.level(), this);
        double d0 = target.getX() - this.getX();
        double d1 = target.getY(0.3333333333333333) - needle.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        needle.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
        this.playSound(AetherSoundEvents.ENTITY_AECHOR_PLANT_SHOOT.get(), 2.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F)); //todo sound
        this.level().addFreshEntity(needle);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return this.isRemoved() || source.is(DamageTypeTags.IS_FALL) || (this.getOwner() != null && source.getEntity() instanceof Player player && this.getOwner().equals(player.getUUID()));
    }

    //todo regeneration

    public static class AvoidEnemyGoal extends Goal {
        private final Kraisith kraisith;
        protected double posX;
        protected double posY;
        protected double posZ;

        public AvoidEnemyGoal(Kraisith kraisith) {
            super();
            this.kraisith = kraisith;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.kraisith.getTarget();
            if (target != null && target.distanceToSqr(this.kraisith) < 16) {
                Vec3 vec3 = this.findRandomPosition(target);
                if (vec3 != null) {
                    if (target.distanceToSqr(vec3.x, vec3.y, vec3.z) < target.distanceToSqr(this.kraisith)) {
                        return false;
                    } else {
                        this.posX = vec3.x;
                        this.posY = vec3.y;
                        this.posZ = vec3.z;
                        return true;
                    }
                }
            }
            return false;
        }

        protected Vec3 findRandomPosition(LivingEntity entity) {
            return DefaultRandomPos.getPosAway(this.kraisith, 6, 2, entity.position());
        }

        @Override
        public boolean canContinueToUse() {
            return !this.kraisith.getBoundingBox().contains(new Vec3(this.posX, this.posY, this.posZ))
                    && !this.kraisith.getNavigation().isDone()
                    && !this.kraisith.getNavigation().isStuck()
                    && this.kraisith.getTarget() != null;
        }

        @Override
        public void start() {
            this.kraisith.getNavigation().moveTo(this.posX, this.posY, this.posZ, 1.5);
        }

        @Override
        public void stop() {
            this.kraisith.getNavigation().stop();
        }

        @Override
        public void tick() {
            super.tick();
            LivingEntity avoid = null;
            if (this.kraisith.getTarget() != null && this.kraisith.getTarget().distanceToSqr(this.kraisith) < 16) {
                avoid = this.kraisith.getTarget();
            }
            if (avoid != null) {
                Vec3 vec3 = this.findRandomPosition(avoid);
                if (vec3 != null) {
                    if (!(avoid.distanceToSqr(vec3.x, vec3.y, vec3.z) < avoid.distanceToSqr(this.kraisith))) {
                        this.posX = vec3.x;
                        this.posY = vec3.y;
                        this.posZ = vec3.z;
                        this.kraisith.getNavigation().moveTo(this.posX, this.posY, this.posZ, 1.5);
                    }
                }
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
