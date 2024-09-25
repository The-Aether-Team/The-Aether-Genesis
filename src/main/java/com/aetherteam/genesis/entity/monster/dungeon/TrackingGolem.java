package com.aetherteam.genesis.entity.monster.dungeon;

import com.aetherteam.aether.entity.ai.goal.ContinuousMeleeAttackGoal;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.network.packet.clientbound.TrackingGolemWarningPacket;
import com.aetherteam.nitrogen.network.PacketRelay;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
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
		this.entityData.define(DATA_CAN_SEE_ENEMY_ID, false);
	}

	public static AttributeSupplier.Builder createMobAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0)
				.add(Attributes.ATTACK_DAMAGE, 3.0)
				.add(Attributes.MOVEMENT_SPEED, 0.28)
				.add(Attributes.FOLLOW_RANGE, 8.0);
	}

	public void tick() {
		if (!this.level().isClientSide()) {
			if (this.getTarget() != null && this.getTarget().isAlive()) {
				if (!this.getSeenEnemy()) {
					this.setSeenEnemy(true);
				}
				int duration = 250;
				if (!this.getTarget().hasEffect(MobEffects.BLINDNESS)
						|| this.getTarget().getEffect(MobEffects.BLINDNESS).getAmplifier() < this.getTarget().getEffect(MobEffects.BLINDNESS).getAmplifier()
						|| this.getTarget().getEffect(MobEffects.BLINDNESS).endsWithin(duration - 1)) {
					if (!this.getTarget().hasEffect(MobEffects.BLINDNESS)) {
						if (this.getTarget() instanceof ServerPlayer serverPlayer) {
							PacketRelay.sendToPlayer(new TrackingGolemWarningPacket(serverPlayer.getId()), serverPlayer);
						}
					}
					this.getTarget().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 350), this);
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
}