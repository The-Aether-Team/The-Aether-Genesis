package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NexSpirit extends CompanionMob {
    public static final EntityDataAccessor<Boolean> DATA_BROKEN_ID = SynchedEntityData.defineId(NexSpirit.class, EntityDataSerializers.BOOLEAN);

    public NexSpirit(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.DEATH_SEAL.get()), true);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_BROKEN_ID, false);
    }

    public boolean isBroken() {
        return this.getEntityData().get(DATA_BROKEN_ID);
    }

    public void setBroken(boolean broken) {
        this.getEntityData().set(DATA_BROKEN_ID, broken);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Broken", this.isBroken());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Broken")) {
            this.setBroken(tag.getBoolean("Broken"));
        }
    }
}
