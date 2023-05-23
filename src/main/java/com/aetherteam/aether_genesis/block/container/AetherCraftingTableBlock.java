package com.aetherteam.aether_genesis.block.container;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class AetherCraftingTableBlock extends CraftingTableBlock {
	private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

	public AetherCraftingTableBlock(BlockBehaviour.Properties pProperties) {
		super(pProperties);
	}

	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return new SimpleMenuProvider((id, inventory, playerEntity) -> new CraftingMenu(id, inventory, ContainerLevelAccess.create(level, pos)) {
			@Override
			public boolean stillValid(@Nonnull Player player) {
				return true;
			}
		}, CONTAINER_TITLE);
	}
}