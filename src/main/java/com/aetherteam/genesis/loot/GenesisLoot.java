package com.aetherteam.genesis.loot;

import com.aetherteam.genesis.AetherGenesis;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GenesisLoot {
    private static final Set<ResourceKey<LootTable>> LOOT_TABLES = new HashSet<>();
    public static final Set<ResourceKey<LootTable>> IMMUTABLE_LOOT_TABLES = Collections.unmodifiableSet(LOOT_TABLES);

    public static final ResourceKey<LootTable> BRONZE_DUNGEON_REWARD = register("chests/dungeon/bronze/bronze_dungeon_reward");

    public static final ResourceKey<LootTable> CONTINUUM_ORB = register("selectors/continuum_orb");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_WOOD = register("selectors/continuum_orb/wood");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_STONE = register("selectors/continuum_orb/stone");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_ORE = register("selectors/continuum_orb/ore");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_NATURAL = register("selectors/continuum_orb/natural");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_FLOWER = register("selectors/continuum_orb/flower");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_PLANT = register("selectors/continuum_orb/plant");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_FARMABLE = register("selectors/continuum_orb/farmable");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_LEAVES = register("selectors/continuum_orb/leaves");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_CORAL = register("selectors/continuum_orb/coral");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_MATERIALS = register("selectors/continuum_orb/materials");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_DROPS = register("selectors/continuum_orb/drops");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_DYES = register("selectors/continuum_orb/dyes");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_DISCS = register("selectors/continuum_orb/discs");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_BOOKS = register("selectors/continuum_orb/books");
    public static final ResourceKey<LootTable> CONTINUUM_ORB_TREASURE = register("selectors/continuum_orb/treasure");

    private static ResourceKey<LootTable> register(String id) {
        return register(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, id));
    }

    private static ResourceKey<LootTable> register(ResourceLocation id) {
        var key = ResourceKey.create(Registries.LOOT_TABLE, id);

        if (LOOT_TABLES.add(key)) return key;

        throw new IllegalArgumentException(id + " is already a registered built-in loot table");
    }
}
