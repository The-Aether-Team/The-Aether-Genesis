package com.aetherteam.genesis.entity.companion;

import com.aetherteam.genesis.item.GenesisDataComponents;
import com.aetherteam.genesis.item.GenesisItems;
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
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_BROKEN_ID, false);
        builder.define(DATA_COOLDOWN_ID, 0);
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
        if (!this.getItem().isEmpty()) {
            Integer itemCooldown = this.getItem().get(GenesisDataComponents.NEX_SPIRIT_COOLDOWN);
            if (itemCooldown == null || itemCooldown != this.getCooldown()) {
                this.getItem().set(GenesisDataComponents.NEX_SPIRIT_COOLDOWN, this.getCooldown());
            }
        }
    }

    @Override
    public void onEquip(ItemStack itemStack) {
        Integer itemCooldown = this.getItem().get(GenesisDataComponents.NEX_SPIRIT_COOLDOWN);
        if (itemCooldown != null) {
            if (itemCooldown > 0) {
                this.setCooldown(itemCooldown); // Set cooldown tag that was stored with the Death Seal to the Nex Spirit
                this.setBroken(true);
            }
        }
        super.onEquip(itemStack);
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
