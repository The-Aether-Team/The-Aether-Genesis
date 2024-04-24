package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.entity.ai.goal.CompanionHurtByTargetGoal;
import com.aetherteam.aether_genesis.entity.ai.goal.CompanionHurtTargetGoal;
import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Fangrin extends CompanionMob implements Combative { //todo melee?
    public Fangrin(EntityType<Fangrin> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.FANGRIN_CAPSULE.get()), false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
        this.targetSelector.addGoal(1, new CompanionHurtByTargetGoal<>(this));
        this.targetSelector.addGoal(2, new CompanionHurtTargetGoal<>(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 50.0).add(Attributes.MOVEMENT_SPEED, 0.3).add(Attributes.ATTACK_DAMAGE, 4.0).add(Attributes.FOLLOW_RANGE, 48.0);
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
    public boolean isInvulnerableTo(DamageSource source) {
        return this.isRemoved() || source.is(DamageTypeTags.IS_FALL) || (this.getOwner() != null && source.getEntity() instanceof Player player && this.getOwner().equals(player.getUUID()));
    }

    //todo regeneration
}
