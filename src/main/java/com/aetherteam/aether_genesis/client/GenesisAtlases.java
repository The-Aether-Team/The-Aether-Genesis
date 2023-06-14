package com.aetherteam.aether_genesis.client;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Genesis.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class GenesisAtlases {
	public static Material SKYROOT_CHEST_MATERIAL;
	public static Material SKYROOT_CHEST_LEFT_MATERIAL;
	public static Material SKYROOT_CHEST_RIGHT_MATERIAL;

	public static void registerSkyrootChestAtlases() {
		SKYROOT_CHEST_MATERIAL = getChestMaterial("skyroot_chest");
		SKYROOT_CHEST_LEFT_MATERIAL = getChestMaterial("skyroot_chest_left");
		SKYROOT_CHEST_RIGHT_MATERIAL = getChestMaterial("skyroot_chest_right");
	}

	public static Material getChestMaterial(String chestName) {
		return new Material(Sheets.CHEST_SHEET, new ResourceLocation(Genesis.MODID, "entity/tiles/chest/" + chestName));
	}
}