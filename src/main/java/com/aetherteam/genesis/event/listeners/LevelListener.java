package com.aetherteam.genesis.event.listeners;

import com.aetherteam.genesis.event.hooks.LevelHooks;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LevelListener {
    @SubscribeEvent
    public static void onPlayerTraveling(SaplingGrowTreeEvent event) {
        LevelAccessor level = event.getLevel();
        RandomSource random = event.getRandomSource();
        Holder<ConfiguredFeature<?, ?>> feature = event.getFeature();
        ResourceKey<ConfiguredFeature<?, ?>> newFeature = LevelHooks.modifyGrownSapling(level, random, feature);
        if (newFeature != null) {
            event.setFeature(newFeature);
        }
    }
}
