package com.aetherteam.aether_genesis.entity.miscellaneous;

import com.aetherteam.aether.capability.lightning.LightningTracker;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import com.aetherteam.aether_genesis.block.miscellaneous.ColdFireBlock;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

import static com.aetherteam.aether_genesis.entity.monster.Tempest.TEMPEST_PARTICLE_COLOR;


public class TempestThunderBall extends AbstractCrystal {
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
		if(level().isClientSide()) {
			level().addParticle(new DustParticleOptions(TEMPEST_PARTICLE_COLOR, 1.0F), xOffset + 0.3, yOffset + 0.3, zOffset + 0.3, 0.0, 0.0, 0.0);
			level().addParticle(new DustParticleOptions(TEMPEST_PARTICLE_COLOR, 1.0F), xOffset, yOffset, zOffset, 0.0, 0.0, 0.0);
		}
		super.tick();
	}

	@Override
	protected void tickMovement() {
		super.tickMovement();
		this.setDeltaMovement(this.getDeltaMovement().scale(0.99F));
	}

	@Override
	protected ParticleOptions getExplosionParticle() {
		return ParticleTypes.SMOKE;
	}

	protected void onHit(HitResult pResult) {
		super.onHit(pResult);
		if (!this.level().isClientSide) {
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

	private void spawnColdFire() {
		if (!this.level().isClientSide && this.level().getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
			BlockPos blockPos = this.blockPosition();
			BlockState blockState = ColdFireBlock.getState(this.level(), blockPos);
			if (this.level().getBlockState(blockPos).isAir() && blockState.canSurvive(this.level(), blockPos)) {
				this.level().setBlockAndUpdate(blockPos, blockState);
			}

			for (int i = 0; i < 4; ++i) {
				BlockPos blockPos1 = blockPos.offset(this.random.nextInt(3) - 1, this.random.nextInt(3) - 1, this.random.nextInt(3) - 1);
				blockState = ColdFireBlock.getState(this.level(), blockPos1);
				if (this.level().getBlockState(blockPos1).isAir() && blockState.canSurvive(this.level(), blockPos1)) {
					this.level().setBlockAndUpdate(blockPos1, blockState);
				}
			}
		}
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}