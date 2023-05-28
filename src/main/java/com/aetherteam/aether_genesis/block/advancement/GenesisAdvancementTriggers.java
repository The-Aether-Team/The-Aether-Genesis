package com.aetherteam.aether_genesis.block.advancement;

import net.minecraft.advancements.CriteriaTriggers;

public class GenesisAdvancementTriggers {
    public static void init() {
        CriteriaTriggers.register(ContinuumOrbLootTrigger.INSTANCE);
    }
}
