package com.aetherteam.aether_genesis.client.gui.screen.inventory;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.inventory.menu.HolystoneFurnaceMenu;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class HolystoneFurnaceScreen extends AbstractFurnaceScreen<HolystoneFurnaceMenu> {
	private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation(Genesis.MODID, "textures/gui/menu/holystone_furnace.png");

	public HolystoneFurnaceScreen(HolystoneFurnaceMenu container, Inventory inventory, Component title) {
		super(container, new SmeltingRecipeBookComponent(), inventory, title, FURNACE_GUI_TEXTURES);
	}
}