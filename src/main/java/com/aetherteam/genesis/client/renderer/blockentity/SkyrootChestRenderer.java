package com.aetherteam.genesis.client.renderer.blockentity;

import com.aetherteam.genesis.client.GenesisAtlases;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;

public class SkyrootChestRenderer<T extends BlockEntity & LidBlockEntity> extends ChestRenderer<T> {
	public SkyrootChestRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected Material getMaterial(T blockEntity, ChestType chestType) {
		return switch (chestType) {
			case LEFT -> GenesisAtlases.SKYROOT_CHEST_LEFT_MATERIAL;
			case RIGHT -> GenesisAtlases.SKYROOT_CHEST_RIGHT_MATERIAL;
			case SINGLE -> GenesisAtlases.SKYROOT_CHEST_MATERIAL;
		};
	}
}