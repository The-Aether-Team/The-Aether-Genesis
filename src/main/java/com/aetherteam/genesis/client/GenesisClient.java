package com.aetherteam.genesis.client;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.GenesisConfig;
import com.aetherteam.genesis.client.gui.screen.inventory.HolystoneFurnaceScreen;
import com.aetherteam.genesis.client.renderer.GenesisRenderers;
import com.aetherteam.genesis.inventory.menu.GenesisMenuTypes;
import com.aetherteam.cumulus.CumulusConfig;
import com.aetherteam.nitrogen.event.listeners.TooltipListeners;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AetherGenesis.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        setupMenuButtons();
        GenesisRenderers.registerCuriosRenderers();
        event.enqueueWork(() -> {
            registerGuiFactories();
            GenesisAtlases.registerSkyrootChestAtlases();
            if (GenesisConfig.CLIENT.night_music_tracks.get()) {
                AetherConfig.CLIENT.disable_music_manager.set(true);
            }
            registerTooltipOverrides();
        });
    }

    public static void setupMenuButtons() {
        if (GenesisConfig.CLIENT.genesis_menu_layout.get()) {
            CumulusConfig.CLIENT.enable_menu_list_button.set(true);
            CumulusConfig.CLIENT.enable_menu_list_button.save();
            AetherConfig.CLIENT.enable_aether_menu_button.set(false);
            AetherConfig.CLIENT.enable_aether_menu_button.save();
        }
    }

    public static void registerGuiFactories() {
        MenuScreens.register(GenesisMenuTypes.HOLYSTONE_FURNACE.get(), HolystoneFurnaceScreen::new);
    }

    public static void registerTooltipOverrides() {
        TooltipListeners.PREDICATES.put(AetherItems.GOLDEN_PARACHUTE.getId(), (player, stack, components, component) -> {
            if (GenesisConfig.COMMON.gold_aercloud_ability.get() && component.getContents() instanceof TranslatableContents contents && contents.getKey().endsWith(".1")) {
                return Component.translatable(contents.getKey() + ".genesis");
            } else {
                return component;
            }
        });
    }
}
