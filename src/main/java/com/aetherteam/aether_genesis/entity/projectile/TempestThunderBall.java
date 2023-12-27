package com.aetherteam.aether_genesis.entity.projectile;

import com.aetherteam.aether.capability.lightning.LightningTracker;
import com.aetherteam.aether_genesis.block.miscellaneous.ColdFireBlock;
import com.aetherteam.aether_genesis.client.particle.GenesisParticleTypes;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;


public class TempestThunderBall extends AbstractHurtingProjectile {
	private int ticksInAir;

	public TempestThunderBall(EntityType<? extends TempestThunderBall> entityType, Level level) {
		super(entityType, level);
	}

	public TempestThunderBall(Level level) {
		super(GenesisEntityTypes.TEMPEST_THUNDERBALL.get(), level);
		this.setNoGravity(true);
	}

	@Override
	public void tick() {
		double xOffset = this.position().x() + (level().getRandom().nextDouble() * 1.5) - 0.75;
		double yOffset = this.position().y() + (level().getRandom().nextDouble() * 2) - 0.5;
		double zOffset = this.position().z() + (level().getRandom().nextDouble() * 1.5) - 0.75;
		if (level().isClientSide()) {
			level().addParticle(GenesisParticleTypes.TEMPEST_ELECTRICITY.get(), xOffset + 0.3, yOffset + 0.3, zOffset + 0.3, 0.0, 0.0, 0.0);
			level().addParticle(GenesisParticleTypes.TEMPEST_ELECTRICITY.get(), xOffset, yOffset, zOffset, 0.0, 0.0, 0.0);
		}
		super.tick();
		Vec3 vector3d = this.getDeltaMovement();
		double d2 = this.getX() + vector3d.x();
		double d0 = this.getY() + vector3d.y();
		double d1 = this.getZ() + vector3d.z();
		this.updateRotation();
		this.setPos(d2, d0, d1);
	}

	@Override
	protected void onHit(HitResult pResult) {
		super.onHit(pResult);
		if (!this.level().isClientSide()) {
			LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(this.level());
			if (lightningBolt != null) {
				LightningTracker.get(lightningBolt).ifPresent(lightningTracker -> lightningTracker.setOwner(this.getOwner()));
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