package com.aetherteam.genesis.entity.companion;

import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.entity.ai.goal.AvoidEnemyGoal;
import com.aetherteam.genesis.entity.ai.goal.CompanionHurtByTargetGoal;
import com.aetherteam.genesis.entity.ai.goal.CompanionHurtTargetGoal;
import com.aetherteam.genesis.entity.projectile.EnchantedNeedle;
import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Kraisith extends CompanionMob implements Combative, RangedAttackMob {
    public Kraisith(EntityType<Kraisith> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.KRAISITH_CAPSULE.get()), false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AvoidEnemyGoal(this, 1.2));
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
        this.playSound(GenesisSoundEvents.ENTITY_KRAISITH_SHOOT.get(), 2.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F)); //todo sound
        this.level().addFreshEntity(needle);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return this.isRemoved() || source.is(DamageTypeTags.IS_FALL) || (this.getOwner() != null && source.getEntity() instanceof Player player && this.getOwner().equals(player.getUUID()));
    }

    //todo regeneration
}
