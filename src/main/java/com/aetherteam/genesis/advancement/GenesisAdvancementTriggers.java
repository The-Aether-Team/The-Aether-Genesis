package com.aetherteam.genesis.advancement;

import com.aetherteam.genesis.AetherGenesis;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisAdvancementTriggers {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, AetherGenesis.MODID);

    public static final DeferredHolder<CriterionTrigger<?>, ContinuumOrbLootTrigger> CONTINUUM_ORB = TRIGGERS.register("continuum_orb", ContinuumOrbLootTrigger::new);
}
