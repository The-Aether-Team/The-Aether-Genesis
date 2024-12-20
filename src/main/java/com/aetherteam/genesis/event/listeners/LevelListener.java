package com.aetherteam.genesis.event.listeners;

import com.aetherteam.genesis.event.hooks.LevelHooks;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;

public class LevelListener {

    public static void listen(IEventBus bus) {
        bus.addListener(LevelListener::onSaplingGrow);
    }

    /**
     * @see LevelHooks#modifyGrownSapling(LevelAccessor, RandomSource, Holder)
     */
    @SubscribeEvent
    public static void onSaplingGrow(BlockGrowFeatureEvent event) {
        LevelAccessor level = event.getLevel();
        RandomSource random = event.getRandom();
        Holder<ConfiguredFeature<?, ?>> feature = event.getFeature();
        ResourceKey<ConfiguredFeature<?, ?>> newFeature = LevelHooks.modifyGrownSapling(level, random, feature);
        if (newFeature != null) {
            event.setFeature(newFeature);
        }
    }
}
