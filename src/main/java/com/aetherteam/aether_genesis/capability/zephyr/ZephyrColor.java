package com.aetherteam.aether_genesis.capability.zephyr;

import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether_genesis.capability.GenesisCapabilities;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.LazyOptional;

public interface ZephyrColor extends INBTSynchable<CompoundTag> {
    Zephyr getZephyr();

    static LazyOptional<ZephyrColor> get(Zephyr zephyr) {
        return zephyr.getCapability(GenesisCapabilities.ZEPHYR_COLOR_CAPABILITY);
    }

    void setTan(boolean tan);
    boolean isTan();
}
