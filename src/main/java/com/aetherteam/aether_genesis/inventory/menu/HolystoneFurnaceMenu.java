package com.aetherteam.aether_genesis.inventory.menu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class HolystoneFurnaceMenu extends AbstractFurnaceMenu {
   public HolystoneFurnaceMenu(int pContainerId, Inventory pPlayerInventory) {
      super(GenesisMenuTypes.HOLYSTONE_FURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, pContainerId, pPlayerInventory);
   }

   public HolystoneFurnaceMenu(int pContainerId, Inventory pPlayerInventory, Container pFurnaceContainer, ContainerData pFurnaceData) {
      super(GenesisMenuTypes.HOLYSTONE_FURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, pContainerId, pPlayerInventory, pFurnaceContainer, pFurnaceData);
   }
}