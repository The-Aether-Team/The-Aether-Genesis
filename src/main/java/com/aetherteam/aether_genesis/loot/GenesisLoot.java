package com.aetherteam.aether_genesis.loot;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GenesisLoot {
    private static final Set<ResourceLocation> LOOT_TABLES = new HashSet<>();
    public static final Set<ResourceLocation> IMMUTABLE_LOOT_TABLES = Collections.unmodifiableSet(LOOT_TABLES);

    public static final ResourceLocation CONTINUUM_ORB = register("selectors/continuum_orb");
    public static final ResourceLocation CONTINUUM_ORB_WOOD = register("selectors/continuum_orb/wood");
    public static final ResourceLocation CONTINUUM_ORB_WOOD_LOGS = register("selectors/continuum_orb/wood/logs");
    public static final ResourceLocation CONTINUUM_ORB_WOOD_PLANKS = register("selectors/continuum_orb/wood/planks");
    public static final ResourceLocation CONTINUUM_ORB_STONE = register("selectors/continuum_orb/stone");

    private static ResourceLocation register(String id) {
        return register(new ResourceLocation(Genesis.MODID, id));
    }

    private static ResourceLocation register(ResourceLocation id) {
        if (LOOT_TABLES.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }
}
