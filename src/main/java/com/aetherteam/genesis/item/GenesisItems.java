package com.aetherteam.genesis.item;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.item.food.GenesisFoods;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.item.food.GummySwetItem;
import com.aetherteam.aether.item.materials.SwetBallItem;
import com.aetherteam.aether.item.miscellaneous.ParachuteItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GenesisItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Genesis.MODID);

    public static final RegistryObject<Item> GOLDEN_SWET_BALL = ITEMS.register("golden_swet_ball", () -> new SwetBallItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_SWET_BALL = ITEMS.register("dark_swet_ball", () -> new SwetBallItem(new Item.Properties()));

    public static final RegistryObject<Item> BLUE_SWET_JELLY = ITEMS.register("blue_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final RegistryObject<Item> GOLDEN_SWET_JELLY = ITEMS.register("golden_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final RegistryObject<Item> DARK_SWET_JELLY = ITEMS.register("dark_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final RegistryObject<Item> DARK_GUMMY_SWET = ITEMS.register("dark_gummy_swet", GummySwetItem::new);

    public static final RegistryObject<Item> BLUE_PARACHUTE = ITEMS.register("blue_parachute", () -> new ParachuteItem(GenesisEntityTypes.BLUE_PARACHUTE, new Item.Properties().durability(1)));
    public static final RegistryObject<Item> GREEN_PARACHUTE = ITEMS.register("green_parachute", () -> new ParachuteItem(GenesisEntityTypes.GREEN_PARACHUTE, new Item.Properties().durability(1)));
    public static final RegistryObject<Item> PURPLE_PARACHUTE = ITEMS.register("purple_parachute", () -> new ParachuteItem(GenesisEntityTypes.PURPLE_PARACHUTE, new Item.Properties().durability(1)));

    public static final RegistryObject<SpawnEggItem> DARK_SWET_SPAWN_EGG = ITEMS.register("dark_swet_spawn_egg", () -> new ForgeSpawnEggItem(GenesisEntityTypes.DARK_SWET, 0x947DC4, 0x4FB1DA, new Item.Properties()));
}
