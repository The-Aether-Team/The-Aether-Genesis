package com.aetherteam.genesis.client.event.hooks;

import com.aetherteam.genesis.Genesis;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class GenesisGuiHooks {
    public static void addAbilityTooltip(ItemStack stack, List<Component> components) {
        String string = stack.getDescriptionId() + "." + Genesis.MODID + ".ability.tooltip";
        if (I18n.exists(string)) {
            components.add(1, Component.translatable(string));
        }
    }
}
