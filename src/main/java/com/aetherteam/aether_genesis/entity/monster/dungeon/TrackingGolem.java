package com.aetherteam.aether_genesis.entity.monster.dungeon;

import com.aetherteam.aether.entity.ai.goal.ContinuousMeleeAttackGoal;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TrackingGolem extends Monster {
	public static final EntityDataAccessor<Integer> CAN_SEE_ENEMY = SynchedEntityData.defineId(TrackingGolem.class, EntityDataSerializers.INT);

	public TrackingGolem(EntityType<? extends TrackingGolem> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new ContinuousMeleeAttackGoal(this, 1.0, false));
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CAN_SEE_ENEMY, 16);
	}

	
	public static AttributeSupplier.Builder createMobAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0)
				.add(Attributes.ATTACK_DAMAGE, 3.0)
				.add(Attributes.MOVEMENT_SPEED, 0.28)
				.add(Attributes.FOLLOW_RANGE, 8.0);
	}

	@Override
	protected SoundEvent getHurtSound( DamageSource damageSource) {
		return GenesisSoundEvents.ENTITY_TRACKING_GOLEM_HIT.get();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return GenesisSoundEvents.ENTITY_TRACKING_GOLEM_SAY.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return GenesisSoundEvents.ENTITY_TRACKING_GOLEM_DEATH.get();
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return true;
	}

	public boolean getSeenEnemy() {
		return this.entityData.get(CAN_SEE_ENEMY) == 1;
	}

	public void setSeenEnemy(boolean seen) {
		if (seen) {
			this.level().playSound(this, this.getOnPos(), GenesisSoundEvents.ENTITY_TRACKING_GOLEM_SEEN_ENEMY.get(), SoundSource.AMBIENT, 5.0F, this.random.nextFloat() * 0.4F + 0.8F);
			this.entityData.set(CAN_SEE_ENEMY, 1);
		} else {
			this.entityData.set(CAN_SEE_ENEMY, 0);
		}
	}

	public void tick() {
		super.tick();
		Player entityPlayer = this.level().getNearestPlayer(this, 8.0D);
		if (this.getTarget() == null)
			if (entityPlayer != null && canBeSeenAsEnemy() && entityPlayer.isAlive() && !entityPlayer.isCreative() && !entityPlayer.isSpectator())
				this.setTarget(entityPlayer);
		if (this.getTarget() != null && canBeSeenAsEnemy() && this.getTarget().isAlive()) {
			if (this.getTarget() instanceof Player player && !player.isCreative() && !player.isSpectator())
				faceEntity((Player) this.getTarget(), this, 3.5F, 40.0F);
			lookAt(this, 10F, 10F);
			if (!getSeenEnemy())
				setSeenEnemy(true);
			if (!this.level().isClientSide) {
				(this.getTarget()).addEffect(new MobEffectInstance(MobEffects.CONFUSION, 10, 3));
				(this.getTarget()).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 3));
			}
		} else {
			this.setTarget(null);
			setSeenEnemy(false);
		}
	}

	public void faceEntity(Player player, Entity par1Entity, float par2, float par3) {
		double d2, d0 = par1Entity.position().x - player.position().x;
		double d1 = par1Entity.position().z - player.position().z;
		if (par1Entity instanceof LivingEntity livingEntity) {
			d2 = livingEntity.position().y + livingEntity.getEyeHeight() - (player.position().y + player.getEyeHeight());
		} else {
			d2 = (par1Entity.getBoundingBox().minY + par1Entity.getBoundingBox().maxY) / 2.0D - (player.position().y + player.getEyeHeight());
		}
		double d3 = Mth.invSqrt(d0 * d0 + d1 * d1);
		float f2 = (float) (Math.atan2(d1, d0) * 180.0D / Math.PI) - 90.0F;
		float f3 = (float) -(Math.atan2(d2, d3) * 180.0D / Math.PI);
		player.setXRot(updateRotation(player.getXRot(), f3, par3));
		player.setYRot(updateRotation(player.getYRot(), f2, par2));
	}

	private float updateRotation(float par1, float par2, float par3) {
		float f3 = Mth.wrapDegrees(par2 - par1);
		if (f3 > par3)
			f3 = par3;
		if (f3 < -par3)
			f3 = -par3;
		return par1 + f3;
	}
}