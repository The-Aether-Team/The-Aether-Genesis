package com.aetherteam.aether_genesis.entity.projectile;

import com.aetherteam.aether.attachment.AetherDataAttachments;
import com.aetherteam.aether.attachment.LightningTrackerAttachment;
import com.aetherteam.aether.capability.lightning.LightningTracker;
import com.aetherteam.aether_genesis.block.miscellaneous.ColdFireBlock;
import com.aetherteam.aether_genesis.client.particle.GenesisParticleTypes;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;


public class TempestThunderBall extends AbstractHurtingProjectile {
	private int ticksInAir;

	public TempestThunderBall(EntityType<? extends TempestThunderBall> entityType, Level level) {
		super(entityType, level);
		this.setNoGravity(true);
	}

	public TempestThunderBall(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(GenesisEntityTypes.TEMPEST_THUNDERBALL.get(), shooter, accelX, accelY, accelZ, level);
		this.setNoGravity(true);
	}

	@Override
	public void tick() {
		if (!this.onGround()) {
			++this.ticksInAir;
		}

		if (this.ticksInAir > 400 && !this.level().isClientSide()) {
			this.discard();
		}

		if (this.level().isClientSide() || (this.getOwner() == null || this.getOwner().isAlive()) && this.level().hasChunkAt(this.blockPosition())) {
			HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
			if (hitResult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitResult)) {
				this.onHit(hitResult);
			}

			this.checkInsideBlocks();
			Vec3 vec3 = this.getDeltaMovement();
			double d0 = this.getX() + vec3.x();
			double d1 = this.getY() + vec3.y();
			double d2 = this.getZ() + vec3.z();
			ProjectileUtil.rotateTowardsMovement(this, 0.2F);
			float f = this.getInertia();
			if (this.isInWater()) {
				for(int i = 0; i < 4; ++i) {
					this.level().addParticle(ParticleTypes.BUBBLE, d0 - vec3.x() * 0.25D, d1 - vec3.y() * 0.25D, d2 - vec3.z() * 0.25D, vec3.x(), vec3.y(), vec3.z());
				}

				f = 0.8F;
			}

			this.setDeltaMovement(vec3.add(this.xPower, this.yPower, this.zPower).scale((double)f));
			double xOffset = this.position().x() + (level().getRandom().nextDouble() * 1.5) - 0.75;
			double yOffset = this.position().y() + (level().getRandom().nextDouble() * 2) - 0.5;
			double zOffset = this.position().z() + (level().getRandom().nextDouble() * 1.5) - 0.75;
			if (level().isClientSide()) {
				level().addParticle(GenesisParticleTypes.TEMPEST_ELECTRICITY.get(), xOffset + 0.3, yOffset + 0.3, zOffset + 0.3, 0.0, 0.0, 0.0);
				level().addParticle(GenesisParticleTypes.TEMPEST_ELECTRICITY.get(), xOffset, yOffset, zOffset, 0.0, 0.0, 0.0);
			}
			this.setPos(d0, d1, d2);
		} else {
			this.discard();
		}
	}

	@Override
	protected void onHit(HitResult pResult) {
		super.onHit(pResult);
		if (!this.level().isClientSide()) {
			LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(this.level());
			if (lightningBolt != null) {
				LightningTrackerAttachment attachment = lightningBolt.getData(AetherDataAttachments.LIGHTNING_TRACKER);
				attachment.setOwner(this.getOwner());
				lightningBolt.setPos(this.getX(), this.getY(), this.getZ());
				lightningBolt.setVisualOnly(true);
				this.level().addFreshEntity(lightningBolt);
				this.spawnColdFire();
			}
			this.discard();
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		if (!this.level().isClientSide) {
			Entity entity = result.getEntity();
			Entity entity1 = this.getOwner();
			entity.hurt(this.damageSources().indirectMagic(this, entity1), 4.0F);
			if (entity1 instanceof LivingEntity) {
				this.doEnchantDamageEffects((LivingEntity)entity1, entity);
			}
		}
	}

	private void spawnColdFire() {
		if (!this.level().isClientSide() && this.level().getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
			BlockPos.MutableBlockPos mutablePos = this.blockPosition().mutable();
			mutablePos.setY(this.level().getHeight(Heightmap.Types.OCEAN_FLOOR, mutablePos.getX(), mutablePos.getZ()));
			BlockPos blockPos = mutablePos.immutable();
			BlockState blockState = ColdFireBlock.getState(this.level(), blockPos);

			this.level().setBlockAndUpdate(blockPos, blockState);

			for (int i = 0; i < 4; ++i) {
				BlockPos blockPos1 = blockPos.offset(this.random.nextInt(3) - 1, this.random.nextInt(3) - 1, this.random.nextInt(3) - 1);
				blockState = ColdFireBlock.getState(this.level(), blockPos1);
				if (this.level().getBlockState(blockPos1).isAir() && blockState.canSurvive(this.level(), blockPos1)) {
					this.level().setBlockAndUpdate(blockPos1, blockState);
				}
			}
		}
	}

	@Override
	public boolean isPickable() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return false;
	}

	@Override
	protected boolean shouldBurn() {
		return false;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}