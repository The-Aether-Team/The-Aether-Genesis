package com.aetherteam.aether_genesis.entity.miscellaneous;

import com.aetherteam.aether_genesis.advancement.GenesisAdvancementTriggers;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.aetherteam.aether_genesis.loot.GenesisLoot;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class ContinuumBomb extends ThrowableItemProjectile {
    private boolean hasHit;
    private double hitX;
    private double hitY;
    private double hitZ;
    private int activeTime;

    public ContinuumBomb(EntityType<ContinuumBomb> entityType, Level level) {
        super(entityType, level);
    }

    public ContinuumBomb(Level level, LivingEntity owner) {
        super(GenesisEntityTypes.CONTINUUM_BOMB.get(), owner, level);
    }
    
    @Override
    public void tick() {
        if (this.hasHit) {
            this.setPos(new Vec3(this.hitX, this.hitY, this.hitZ)); // Sticks the Continuum Bomb to the spot it landed at.
            if (this.activeTime < 100) {
                if (this.activeTime % 10 == 0) { // The Continuum Bomb spawns items over 99 ticks every 10 ticks; so new loot is queried for spawning 9 times.
                    if (!this.level().isClientSide() && this.level() instanceof ServerLevel serverLevel) {
                        double motX = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                        double motY = 1.0;
                        double motZ = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                        List<ItemStack> loot = this.createLoot(serverLevel);
                        for (ItemStack reward : loot) {
                            if (this.getOwner() instanceof ServerPlayer serverPlayer) {
                                GenesisAdvancementTriggers.CONTINUUM_ORB.get().trigger(serverPlayer, reward); // Advancement trigger for if a player gets a specified reward through Continuum.
                            }
                            RewardItemEntity entity = new RewardItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), reward); // Creates the loot reward items.
                            entity.setDeltaMovement(motX, motY, motZ);
                            this.level().addFreshEntity(entity);
                        }
                    }
                    this.spawnParticles();
                }
                this.activeTime++;
            } else {
                this.spawnParticles();
                this.discard();
            }
        } else {
            super.tick();
        }
    }

    /**
     * Spawns particle effects with loot creation and at the end of loot creation.
     */
    private void spawnParticles() {
        for (int sparkCount = 1; sparkCount <= 10; sparkCount++) {
            double motX = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
            double motY = this.random.nextDouble();
            double motZ = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
            this.level().addParticle(ParticleTypes.AMBIENT_ENTITY_EFFECT, this.getX(), this.getY(), this.getZ(), motX, motY, motZ);
            this.level().addParticle(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), motX / 6.0, motY / 6.0, motZ / 6.0);
        }
    }

    /**
     * This method creates the list of loot items that can be spawned by this Continuum Bomb.
     * @param serverLevel The {@link ServerLevel} provider.
     * @return The {@link ItemStack} {@link List}
     */
    private List<ItemStack> createLoot(ServerLevel serverLevel) {
        LootParams parameters = new LootParams.Builder(serverLevel).withParameter(LootContextParams.ORIGIN, this.position()).withParameter(LootContextParams.THIS_ENTITY, this).create(LootContextParamSets.SELECTOR);
        LootTable lootTable = serverLevel.getServer().getLootData().getLootTable(GenesisLoot.CONTINUUM_ORB);
        List<ItemStack> list = lootTable.getRandomItems(parameters);
        return new ArrayList<>(list);
    }

    /**
     * Determines the position that the Continuum Bomb will stick at.
     *
     * @param result The {@link HitResult} of the projectile.
     */
    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.hasHit) {
            this.hasHit = true;
            this.hitX = this.getX();
            this.hitY = this.getY();
            this.hitZ = this.getZ();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return GenesisItems.CONTINUUM_BOMB.get();
    }
}
