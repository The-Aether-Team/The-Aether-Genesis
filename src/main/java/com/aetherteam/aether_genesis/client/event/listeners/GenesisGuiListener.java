package com.aetherteam.aether_genesis.client.event.listeners;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.event.hooks.GenesisGuiHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = Genesis.MODID, value = Dist.CLIENT)
public class GenesisGuiListener {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onTooltipRender(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        List<Component> components = event.getToolTip();
        GenesisGuiHooks.addAbilityTooltip(itemStack, components);
    }
}
