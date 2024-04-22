package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NexSpirit extends CompanionMob {
    public static final EntityDataAccessor<Boolean> DATA_BROKEN_ID = SynchedEntityData.defineId(NexSpirit.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> DATA_COOLDOWN_ID = SynchedEntityData.defineId(NexSpirit.class, EntityDataSerializers.INT);

    public NexSpirit(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.DEATH_SEAL.get()), true);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_BROKEN_ID, false);
        this.getEntityData().define(DATA_COOLDOWN_ID, 0);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getCooldown() > 0) {
            if (!this.isBroken()) {
                this.setBroken(true);
            }
            if (this.tickCount % 10 == 0) {
                this.setCooldown(this.getCooldown() - 1);
            }
        } else {
            this.setBroken(false);
        }
    }

    @Override
    public void onEquip(ItemStack itemStack) {
        if (itemStack.getTag() != null && itemStack.getTag().contains("Cooldown")) {
            int cooldown = itemStack.getTag().getInt("Cooldown");
            if (cooldown > 0) {
                this.setCooldown(cooldown);
                this.setBroken(true);
            }
            itemStack.getTag().remove("Cooldown");
        }
        super.onEquip(itemStack);
    }

    @Override
    public void onUnequip(ItemStack itemStack) {
        this.setItemCooldown();
        super.onUnequip(itemStack);
    }

    @Override
    public void remove(RemovalReason reason) {
        this.setItemCooldown();
        super.remove(reason);
    }

    private void setItemCooldown() {
        if (this.getCooldown() > 0) {
            this.getItem().getOrCreateTag().putInt("Cooldown", this.getCooldown());
        }
    }

    public boolean isBroken() {
        return this.getEntityData().get(DATA_BROKEN_ID);
    }

    public void setBroken(boolean broken) {
        this.getEntityData().set(DATA_BROKEN_ID, broken);
    }

    public int getCooldown() {
        return this.getEntityData().get(DATA_COOLDOWN_ID);
    }

    public void setCooldown(int cooldown) {
        this.getEntityData().set(DATA_COOLDOWN_ID, cooldown);
    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundTag tag) {
//        super.addAdditionalSaveData(tag);
//        tag.putBoolean("Broken", this.isBroken());
//        tag.putInt("Cooldown", this.getCooldown());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundTag tag) {
//        super.readAdditionalSaveData(tag);
//        if (tag.contains("Broken")) {
//            this.setBroken(tag.getBoolean("Broken"));
//        }
//        if (tag.contains("Cooldown")) {
//            this.setCooldown(tag.getInt("Cooldown"));
//        }
//    }
}
