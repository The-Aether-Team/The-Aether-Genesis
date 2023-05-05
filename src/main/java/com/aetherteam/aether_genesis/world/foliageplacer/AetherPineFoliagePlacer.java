package com.aetherteam.aether_genesis.world.foliageplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class AetherPineFoliagePlacer extends FoliagePlacer {
    public static final Codec<AetherPineFoliagePlacer> CODEC = RecordCodecBuilder.create((codec) -> foliagePlacerParts(codec)
            .and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((placer) -> placer.trunkHeight))
            .apply(codec, AetherPineFoliagePlacer::new));
    private final IntProvider trunkHeight;

    public AetherPineFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.trunkHeight = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return GenesisFoliagePlacerTypes.AETHER_PINE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliagePlacer.FoliageSetter setter, RandomSource random, TreeConfiguration configuration, int maxTreeHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockpos = attachment.pos();
        int i = 0;
        int j = 2;
        int k = 1;

        if (foliageHeight % 2 != 0) {
            j -= 1;
        }

        for (int l = offset; l >= -foliageHeight; --l) {
            this.placeLeavesRow(level, setter, random, configuration, blockpos, i, l, attachment.doubleTrunk());
            if (i >= j) {
                i = k;
                j = Math.min(j + 1, foliageRadius + attachment.radiusOffset());
            } else {
                ++i;
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration configuration) {
        int value = height - this.trunkHeight.sample(random);
        return Math.max(3, value);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return localX + localX + localZ + localZ > range + 2;
    }
}
