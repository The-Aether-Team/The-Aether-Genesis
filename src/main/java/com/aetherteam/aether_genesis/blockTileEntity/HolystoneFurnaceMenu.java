package com.aetherteam.aether_genesis.blockTileEntity;

import com.aetherteam.aether.inventory.menu.AbstractAetherFurnaceMenu;
import com.aetherteam.aether.inventory.menu.AetherMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class HolystoneFurnaceMenu extends AbstractAetherFurnaceMenu {
   public HolystoneFurnaceMenu(int pContainerId, Inventory pPlayerInventory) {
      super(GenesisMenuTypes.HOLYSTONE_FURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, pContainerId, pPlayerInventory);
   }

   public HolystoneFurnaceMenu(int pContainerId, Inventory pPlayerInventory, Container pFurnaceContainer, ContainerData pFurnaceData) {
      super(GenesisMenuTypes.HOLYSTONE_FURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, pContainerId, pPlayerInventory, pFurnaceContainer, pFurnaceData);
   }

   public boolean isFuel(ItemStack stack) {
      return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, null) > 0;
   }
}