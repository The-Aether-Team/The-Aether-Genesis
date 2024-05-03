package com.aetherteam.genesis.event.listeners;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.event.hooks.LevelHooks;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.SaplingGrowTreeEvent;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class LevelListener {
    /**
     * @see LevelHooks#modifyGrownSapling(LevelAccessor, RandomSource, Holder)
     */
    @SubscribeEvent
    public static void onSaplingGrow(SaplingGrowTreeEvent event) {
        LevelAccessor level = event.getLevel();
        RandomSource random = event.getRandomSource();
        Holder<ConfiguredFeature<?, ?>> feature = event.getFeature();
        ResourceKey<ConfiguredFeature<?, ?>> newFeature = LevelHooks.modifyGrownSapling(level, random, feature);
        if (newFeature != null) {
            event.setFeature(newFeature);
        }
    }
}
