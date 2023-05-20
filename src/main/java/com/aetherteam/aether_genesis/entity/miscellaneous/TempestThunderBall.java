package com.aetherteam.aether_genesis.entity.miscellaneous;

import com.aetherteam.aether.capability.lightning.LightningTracker;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

public class TempestThunderBall extends Fireball implements ItemSupplier {
	private int ticksInAir;

	public TempestThunderBall(EntityType<? extends TempestThunderBall> type, Level level) {
		super(type, level);
		this.setNoGravity(true);
	}

	public TempestThunderBall(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(GenesisEntityTypes.TEMPEST_THUNDERBALL.get(), shooter, accelX, accelY, accelZ, level);
		this.setNoGravity(true);
	}

	/**
	 * Warning for "deprecation" is suppressed because vanilla calls {@link Level#hasChunkAt(BlockPos)} just fine.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		if (!this.onGround) {
			++this.ticksInAir;
		}
		if (this.ticksInAir > 400) {
			this.discard();
		}
		if (this.level.isClientSide || (this.getOwner() == null || this.getOwner().isAlive()) && this.level.hasChunkAt(this.blockPosition())) {
			HitResult hitResult = ProjectileUtil.getHitResult(this, this::canHitEntity);
			if (hitResult.getType() != HitResult.Type.MISS && !ForgeEventFactory.onProjectileImpact(this, hitResult)) {
				this.onHit(hitResult);
			}

			this.checkInsideBlocks();
			Vec3 vec3 = this.getDeltaMovement();
			double d0 = this.getX() + vec3.x;
			double d1 = this.getY() + vec3.y;
			double d2 = this.getZ() + vec3.z;
			ProjectileUtil.rotateTowardsMovement(this, 0.2F);
			float f = this.getInertia();
			if (this.isInWater()) {
				for (int i = 0; i < 4; ++i) {
					this.level.addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * 0.25, d1 - vec3.y * 0.25, d2 - vec3.z * 0.25, vec3.x, vec3.y, vec3.z);
				}
				f = 0.8F;
			}

			this.setDeltaMovement(vec3.add(this.xPower, this.yPower, this.zPower).scale(f));
			this.level.addParticle(this.getTrailParticle(), d0, d1 + 0.5, d2, 0.0, 0.0, 0.0);
			this.setPos(d0, d1, d2);
		} else {
			this.discard();
		}
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

	protected void onHitEntity(EntityHitResult pResult) {
		super.onHitEntity(pResult);
		if (!this.level.isClientSide) {
			Entity entity = pResult.getEntity();
			Entity entity1 = this.getOwner();
			entity.hurt(this.damageSources().fireball(this, entity1), 6.0F);
			if (entity1 instanceof LivingEntity) {
				this.doEnchantDamageEffects((LivingEntity)entity1, entity);
			}

		}
	}

	@Override
	protected boolean shouldBurn() {
		return false;
	}

	@Nonnull
	@Override
	protected ParticleOptions getTrailParticle() {
		return ParticleTypes.SMOKE;
	}

	@Nonnull
	@Override
	public ItemStack getItem() {
		return new ItemStack(Blocks.ICE);
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}