package com.aetherteam.aether_genesis.entity.companion;

import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NexSpirit extends FloatingCompanion {
    public static final EntityDataAccessor<Boolean> DATA_BROKEN_ID = SynchedEntityData.defineId(NexSpirit.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> DATA_COOLDOWN_ID = SynchedEntityData.defineId(NexSpirit.class, EntityDataSerializers.INT);

    public NexSpirit(EntityType<NexSpirit> entityType, Level level) {
        super(entityType, level, () -> new ItemStack(GenesisItems.DEATH_SEAL.get()));
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
        if (this.getCooldown() > 0) { // Slowly tick down cooldown.
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
                this.setCooldown(cooldown); // Set cooldown tag that was stored with the Death Seal to the Nex Spirit
                this.setBroken(true);
            }
            itemStack.getTag().remove("Cooldown"); // Remove cooldown tag from Death Seal after transfer.
        }
        super.onEquip(itemStack);
    }

    @Override
    public void onUnequip(ItemStack itemStack) {
        this.setItemCooldown(); // Set item cooldown if it is unequipped.
        super.onUnequip(itemStack);
    }

    @Override
    public void remove(RemovalReason reason) {
        this.setItemCooldown(); // Set item cooldown when the Nex Spirit is removed.
        super.remove(reason);
    }

    private void setItemCooldown() {
        if (this.getCooldown() > 0) { // Apply the current cooldown value to the Death Seal.
            this.getItem().getOrCreateTag().putInt("Cooldown", this.getCooldown());
        }
    }

    /**
     * @return The {@link Boolean} for if the Nex Spirit is currently broken.
     */
    public boolean isBroken() {
        return this.getEntityData().get(DATA_BROKEN_ID);
    }

    /**
     * Sets whether the Nex Spirit is currently broken and can't use its ability.
     *
     * @param broken The {@link Boolean} for if the Nex Spirit is currently broken.
     */
    public void setBroken(boolean broken) {
        this.getEntityData().set(DATA_BROKEN_ID, broken);
    }

    /**
     * @return The current cooldown {@link Integer} value.
     */
    public int getCooldown() {
        return this.getEntityData().get(DATA_COOLDOWN_ID);
    }

    /**
     * Sets the cooldown {@link Integer} for how long until the Nex Spirit is no longer broken.
     *
     * @param cooldown The current cooldown {@link Integer} value.
     */
    public void setCooldown(int cooldown) {
        this.getEntityData().set(DATA_COOLDOWN_ID, cooldown);
    }
}
