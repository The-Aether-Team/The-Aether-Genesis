package com.aetherteam.aether_genesis.blockTileEntity;

import com.aetherteam.aether.blockentity.AbstractAetherFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;

public class HolyFurnaceBlockEntity extends AbstractAetherFurnaceBlockEntity {
    public HolyFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(GenesisBlockEntityTypes.HOLYSTONE_FURNACE.get(), pPos, pBlockState, RecipeType.SMELTING);
    }

    protected Component getDefaultName() {
        return Component.translatable("container.furnace");
    }

    protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
        return new FurnaceMenu(pId, pPlayer, this, this.dataAccess);
    }
}
