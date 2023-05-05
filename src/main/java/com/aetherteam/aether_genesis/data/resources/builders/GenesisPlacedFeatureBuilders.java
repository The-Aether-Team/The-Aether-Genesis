package com.aetherteam.aether_genesis.data.resources.builders;

import com.aetherteam.aether.world.placementmodifier.DungeonBlacklistFilter;
import com.aetherteam.aether.world.placementmodifier.ImprovedLayerPlacementModifier;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

import java.util.List;

public class GenesisPlacedFeatureBuilders {
    public static List<PlacementModifier> treePlacement(PlacementModifier count, Block filter) {
        return treePlacementBase(count, filter).build();
    }

    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier count, Block filter) {
        return ImmutableList.<PlacementModifier>builder()
                .add(count)
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, UniformInt.of(0, 1), 4))
                .add(BiomeFilter.biome())
                .add(new DungeonBlacklistFilter())
                .add(PlacementUtils.filteredByBlockSurvival(filter));
    }
}
