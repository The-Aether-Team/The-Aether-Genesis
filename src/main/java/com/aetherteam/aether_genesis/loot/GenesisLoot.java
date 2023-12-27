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
    public static final ResourceLocation CONTINUUM_ORB_STONE = register("selectors/continuum_orb/stone");
    public static final ResourceLocation CONTINUUM_ORB_ORE = register("selectors/continuum_orb/ore");
    public static final ResourceLocation CONTINUUM_ORB_NATURAL = register("selectors/continuum_orb/natural");
    public static final ResourceLocation CONTINUUM_ORB_FLOWER = register("selectors/continuum_orb/flower");
    public static final ResourceLocation CONTINUUM_ORB_PLANT = register("selectors/continuum_orb/plant");
    public static final ResourceLocation CONTINUUM_ORB_FARMABLE = register("selectors/continuum_orb/farmable");
    public static final ResourceLocation CONTINUUM_ORB_LEAVES = register("selectors/continuum_orb/leaves");
    public static final ResourceLocation CONTINUUM_ORB_CORAL = register("selectors/continuum_orb/coral");
    public static final ResourceLocation CONTINUUM_ORB_MATERIALS = register("selectors/continuum_orb/materials");
    public static final ResourceLocation CONTINUUM_ORB_DROPS = register("selectors/continuum_orb/drops");
    public static final ResourceLocation CONTINUUM_ORB_DYES = register("selectors/continuum_orb/dyes");
    public static final ResourceLocation CONTINUUM_ORB_DISCS = register("selectors/continuum_orb/discs");
    public static final ResourceLocation CONTINUUM_ORB_BOOKS = register("selectors/continuum_orb/books");
    public static final ResourceLocation CONTINUUM_ORB_TREASURE = register("selectors/continuum_orb/treasure");

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
