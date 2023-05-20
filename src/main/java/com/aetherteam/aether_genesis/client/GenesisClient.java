package com.aetherteam.aether_genesis.client;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.blockTileEntity.GenesisMenuTypes;
import com.aetherteam.aether_genesis.blockTileEntity.HolystoneFurnaceScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Genesis.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(GenesisClient::registerGuiFactories);
    }

    public static void registerGuiFactories() {
        MenuScreens.register(GenesisMenuTypes.HOLYSTONE_FURNACE.get(), HolystoneFurnaceScreen::new);
    }
}
