package com.aetherteam.genesis.client;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.item.GenesisItems;
import com.aetherteam.genesis.item.accessories.cape.DyeableCape;
import com.aetherteam.genesis.item.accessories.miscellaneous.DyeableEars;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Genesis.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisColorResolvers {
    @SubscribeEvent
    static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        event.register((color, itemProvider) -> itemProvider > 0 ? -1 : ((DyeableCape) color.getItem()).getColor(color), GenesisItems.CAPE.get());
        event.register((color, itemProvider) -> itemProvider > 0 ? -1 : ((DyeableEars) color.getItem()).getColor(color), GenesisItems.MOUSE_EAR_CAP.get());
    }
}
