package com.aetherteam.aether_genesis.entity.miscellaneous;

import com.aetherteam.aether.capability.lightning.LightningTracker;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

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
		if (!this.level.isClientSide) {
			LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(this.level);
			if (lightningBolt != null) {
				LightningTracker.get(lightningBolt).ifPresent(lightningTracker -> lightningTracker.setOwner(this.getOwner()));
				lightningBolt.setPos(this.getX(), this.getY(), this.getZ());
				this.level.addFreshEntity(lightningBolt);
			}
			this.discard();
		}

	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}