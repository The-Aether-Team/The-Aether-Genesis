package com.aetherteam.aether_genesis.inventory.menu;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GenesisMenuTypes {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Genesis.MODID);

	public static final RegistryObject<MenuType<HolystoneFurnaceMenu>> HOLYSTONE_FURNACE = register("holystone_furnace", HolystoneFurnaceMenu::new);

	private static<T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, MenuType.MenuSupplier<T> menu) {
		return MENU_TYPES.register(name, () -> new MenuType<>(menu, FeatureFlags.VANILLA_SET));
	}
}