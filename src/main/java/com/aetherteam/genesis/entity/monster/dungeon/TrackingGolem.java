package com.aetherteam.genesis.entity.monster.dungeon;

import com.aetherteam.aether.entity.ai.goal.ContinuousMeleeAttackGoal;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.client.particle.GenesisParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
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
	public static final EntityDataAccessor<Boolean> DATA_CAN_SEE_ENEMY_ID = SynchedEntityData.defineId(TrackingGolem.class, EntityDataSerializers.BOOLEAN);

	public TrackingGolem(EntityType<? extends TrackingGolem> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder createMobAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0)
				.add(Attributes.ATTACK_DAMAGE, 3.0)
				.add(Attributes.MOVEMENT_SPEED, 0.28)
				.add(Attributes.FOLLOW_RANGE, 8.0);
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
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_CAN_SEE_ENEMY_ID, false);
	}

	/**
	 * getTarget() on client side will never find target.
	 * */
	/*@Override
	public void handleEntityEvent(byte id) {
		if (id == 100) {
			if (this.getTarget() instanceof Player player) {
				player.level().addParticle(GenesisParticleTypes.TRACKING_GOLEM_WARNING.get(), player.getX(), player.getY(), player.getZ(), 0.0, 0.0, 0.0);
				player.level().playSound(player, player.getX(), player.getY(), player.getZ(), GenesisSoundEvents.ENTITY_TRACKING_GOLEM_SEEN_ENEMY.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
			}
		} else {
			super.handleEntityEvent(id);
		}
	}*/

	public void tick() {
		if (!this.level().isClientSide()) {
			if (this.getTarget() != null && this.getTarget().isAlive()) {
				if (!this.getSeenEnemy()) {
					this.setSeenEnemy(true);
				}
				int duration = 25;
				boolean shouldRefreshBlindness = !this.getTarget().hasEffect(MobEffects.BLINDNESS) || this.getTarget().getEffect(MobEffects.BLINDNESS).endsWithin(duration - 1);
				if (shouldRefreshBlindness) {
					if (!this.getTarget().hasEffect(MobEffects.BLINDNESS)) {
						if (this.getTarget() instanceof ServerPlayer player && this.level() instanceof ServerLevel serverLevel) {
							serverLevel.sendParticles(player, GenesisParticleTypes.TRACKING_GOLEM_WARNING.get(), true, player.getX(), player.getY(), player.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
							player.playNotifySound(GenesisSoundEvents.ENTITY_TRACKING_GOLEM_SEEN_ENEMY.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
						}
					}
					this.getTarget().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30), this);
				}
			} else {
				if (this.getSeenEnemy()) {
					this.setSeenEnemy(false);
				}
			}
		}
		super.tick();
	}

	public boolean getSeenEnemy() {
		return this.entityData.get(DATA_CAN_SEE_ENEMY_ID);
	}

	public void setSeenEnemy(boolean seen) {
		this.entityData.set(DATA_CAN_SEE_ENEMY_ID, seen);
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return true;
	}

	@Override
	protected SoundEvent getHurtSound( DamageSource damageSource) {
		return GenesisSoundEvents.ENTITY_TRACKING_GOLEM_HURT.get();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return GenesisSoundEvents.ENTITY_TRACKING_GOLEM_SAY.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return GenesisSoundEvents.ENTITY_TRACKING_GOLEM_DEATH.get();
	}
}