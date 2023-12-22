package com.aetherteam.aether_genesis.client;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.inventory.menu.GenesisMenuTypes;
import com.aetherteam.aether_genesis.client.gui.screen.inventory.HolystoneFurnaceScreen;
import com.aetherteam.aether_genesis.client.renderer.GenesisRenderers;
import com.aetherteam.cumulus.CumulusConfig;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Genesis.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        setupMenuButtons();
        GenesisRenderers.registerCuriosRenderers();
        event.enqueueWork(() -> {
            registerGuiFactories();
            GenesisAtlases.registerSkyrootChestAtlases();
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
}
