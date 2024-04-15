package com.aetherteam.aether_genesis.world;

//import com.aetherteam.aether_genesis.data.resources.registries.GenesisBiomes;
//import com.mojang.datafixers.util.Pair;
//import net.minecraft.core.Registry;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.biome.Climate;
//import teamrazor.aeroblender.aether.AetherRegionType;
//import terrablender.api.Region;
//
//import java.util.function.Consumer;

//public class GenesisRegion extends Region {
//    public GenesisRegion(ResourceLocation name, int weight) {
//        super(name, AetherRegionType.THE_AETHER, weight);
//    }
//
//    @Override
//    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
//        Climate.Parameter fullRange = Climate.Parameter.span(-1.0F, 1.0F);
//        Climate.Parameter temps1 = Climate.Parameter.span(-1.0F, -0.8F);
//        Climate.Parameter temps2 = Climate.Parameter.span(-0.8F, 0.0F);
//        Climate.Parameter temps3 = Climate.Parameter.span(0.0F, 0.4F);
//        Climate.Parameter temps4 = Climate.Parameter.span(0.4F, 0.93F);
//        Climate.Parameter temps5 = Climate.Parameter.span(0.93F, 0.94F);
//        Climate.Parameter temps6 = Climate.Parameter.span(0.94F, 1.0F);
//
//        // Row 1
//        this.addBiome(mapper, new Climate.ParameterPoint(temps1, fullRange, fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_MEADOW);
//
//        // Row 2
//        this.addBiome(mapper, new Climate.ParameterPoint(temps2, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_MEADOW);
//        this.addBiome(mapper, new Climate.ParameterPoint(temps2, Climate.Parameter.span(0.0F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_FOREST);
//
//        // Row 3
//        this.addBiome(mapper, new Climate.ParameterPoint(temps3, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_GROVE);
//        this.addBiome(mapper, new Climate.ParameterPoint(temps3, Climate.Parameter.span(0.0F, 0.8F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_FOREST);
//        this.addBiome(mapper, new Climate.ParameterPoint(temps3, Climate.Parameter.span(0.8F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_GROVE);
//
//        // Row 4
//        this.addBiome(mapper, new Climate.ParameterPoint(temps4, Climate.Parameter.span(-1.0F, -0.1F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_GROVE);
//        this.addBiome(mapper, new Climate.ParameterPoint(temps4, Climate.Parameter.span(-0.1F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_FOREST);
//
//        // Row 5
//        this.addBiome(mapper, new Climate.ParameterPoint(temps5, Climate.Parameter.span(-1.0F, -0.6F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_MEADOW);
//        this.addBiome(mapper, new Climate.ParameterPoint(temps5, Climate.Parameter.span(-0.6F, -0.3F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_GROVE);
//        this.addBiome(mapper, new Climate.ParameterPoint(temps5, Climate.Parameter.span(-0.3F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_FOREST);
//
//        // Row 6
//        this.addBiome(mapper, new Climate.ParameterPoint(temps6, Climate.Parameter.span(-1.0F, -0.1F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_MEADOW);
//        this.addBiome(mapper, new Climate.ParameterPoint(temps6, Climate.Parameter.span(-0.1F, 0.8F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_WOODLAND);
//        this.addBiome(mapper, new Climate.ParameterPoint(temps5, Climate.Parameter.span(0.8F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0), GenesisBiomes.VIBRANT_FOREST);
//    }
//}
