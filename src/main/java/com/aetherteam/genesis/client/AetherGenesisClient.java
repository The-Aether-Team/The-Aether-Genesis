package com.aetherteam.genesis.client;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.GenesisConfig;
import com.aetherteam.genesis.client.event.listeners.GenesisAudioListener;
import com.aetherteam.genesis.client.gui.screen.inventory.HolystoneFurnaceScreen;
import com.aetherteam.genesis.client.particle.GenesisParticleTypes;
import com.aetherteam.genesis.client.renderer.GenesisRenderers;
import com.aetherteam.genesis.inventory.menu.GenesisMenuTypes;
import com.aetherteam.cumulus.CumulusConfig;
import com.aetherteam.nitrogen.event.listeners.TooltipListeners;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = AetherGenesis.MODID, dist = Dist.CLIENT)
public class AetherGenesisClient {

    public AetherGenesisClient(ModContainer mod, IEventBus bus) {
        bus.addListener(AetherGenesisClient::clientSetup);
        bus.addListener(AetherGenesisClient::registerGuiFactories);

        bus.addListener(GenesisShaders::registerShaders);
        bus.addListener(GenesisParticleTypes::registerParticleFactories);
        bus.addListener(GenesisColorResolvers::registerItemColor);

        GenesisRenderers.listener(bus);
        GenesisAudioListener.listeners(NeoForge.EVENT_BUS);
    }

    public static void clientSetup(FMLClientSetupEvent event) {
        setupMenuButtons();
        GenesisRenderers.registerCuriosRenderers();
        event.enqueueWork(() -> {
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
        }
    }

    public static void registerGuiFactories(RegisterMenuScreensEvent event) {
        event.register(GenesisMenuTypes.HOLYSTONE_FURNACE.get(), HolystoneFurnaceScreen::new);
    }

    public static void registerTooltipOverrides() {
        TooltipListeners.PREDICATES.put(AetherItems.GOLDEN_PARACHUTE, (player, stack, components, ctx, component) -> {
            if (GenesisConfig.COMMON.gold_aercloud_ability.get() && component.getContents() instanceof TranslatableContents contents && contents.getKey().endsWith(".1")) {
                return Component.translatable(contents.getKey() + ".genesis");
            } else {
                return component;
            }
        });
    }
}
