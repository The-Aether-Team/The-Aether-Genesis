package com.aetherteam.aether_genesis.entity.miscellaneous;

import com.aetherteam.aether_genesis.advancement.ContinuumOrbLootTrigger;
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

    private int partyTimeNeeded = 100, partyTime, presentCount = 9;

    public ContinuumBomb(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ContinuumBomb(Level pLevel, LivingEntity pShooter) {
        super(GenesisEntityTypes.CONTINUUM_BOMB.get(), pShooter, pLevel);
    }
    
    @Override
    public void tick() {
        if (this.hasHit) {
            setPos(new Vec3(this.hitX, this.hitY, this.hitZ));
            if (this.partyTime < this.partyTimeNeeded) {
                if (this.partyTime % 10 == 0) {
                    double motX = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                    double motY = 1.0D;
                    double motZ = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                    if (!this.level().isClientSide) {
                        List<ItemStack> loot = this.createLoot();
                        for (ItemStack reward : loot) {
                            if (this.getOwner() instanceof ServerPlayer serverPlayer) {
                                ContinuumOrbLootTrigger.INSTANCE.trigger(serverPlayer, reward);
                            }
                            RewardItem entity = new RewardItem(this.level(), this.getX(), this.getY(), this.getZ(), reward);
                            entity.setDeltaMovement(motX, motY, motZ);
                            this.level().addFreshEntity(entity);
                        }
                    }
                    for (int sparkCount = 1; sparkCount <= 10; sparkCount++) {
                        motX = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                        motY = this.random.nextDouble();
                        motZ = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                        this.level().addParticle(ParticleTypes.AMBIENT_ENTITY_EFFECT, this.getX(), this.getY(), this.getZ(), motX, motY, motZ);
                        this.level().addParticle(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), motX / 6.0D, motY / 6.0D, motZ / 6.0D);
                    }
                }
                this.partyTime++;
            } else {
                for (int sparkCount = 1; sparkCount <= 10; sparkCount++) {
                    double motX = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                    double motY = this.random.nextDouble();
                    double motZ = (this.random.nextBoolean() ? -1 : 1) * this.random.nextDouble();
                    this.level().addParticle(ParticleTypes.AMBIENT_ENTITY_EFFECT, this.getX(), this.getY(), this.getZ(), motX, motY, motZ);
                    this.level().addParticle(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), motX / 6.0D, motY / 6.0D, motZ / 6.0D);
                }
                discard();
            }
        } else {
            super.tick();
        }
    }

    protected List<ItemStack> createLoot() {
        LootParams parameters = new LootParams.Builder((ServerLevel) this.level()).withParameter(LootContextParams.ORIGIN, this.position()).withParameter(LootContextParams.THIS_ENTITY, this).create(LootContextParamSets.SELECTOR);
        LootTable lootTable = ((ServerLevel) this.level()).getServer().getLootData().getLootTable(GenesisLoot.CONTINUUM_ORB);
        List<ItemStack> list = lootTable.getRandomItems(parameters);
        return new ArrayList<>(list);
    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
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
