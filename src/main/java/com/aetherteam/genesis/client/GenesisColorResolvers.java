package com.aetherteam.genesis.client;

import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public class GenesisColorResolvers {
    static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        event.register((color, itemProvider) -> itemProvider > 0 ? -1 : DyedItemColor.getOrDefault(color, 0xFFFFFFFF), GenesisItems.CAPE.get());
        event.register((color, itemProvider) -> itemProvider > 0 ? -1 : DyedItemColor.getOrDefault(color, 0xFF9D3333), GenesisItems.MOUSE_EAR_CAP.get());
    }
}
