package com.aetherteam.aether_genesis.item;

import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.item.food.GenesisFoods;
import com.aetherteam.aether.item.food.GummySwetItem;
import com.aetherteam.aether.item.materials.SwetBallItem;
import com.aetherteam.aether.item.miscellaneous.ParachuteItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GenesisItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Genesis.MODID);

    public static final RegistryObject<Item> GOLDEN_SWET_BALL = ITEMS.register("golden_swet_ball", () -> new SwetBallItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_SWET_BALL = ITEMS.register("dark_swet_ball", () -> new SwetBallItem(new Item.Properties()));
    public static final RegistryObject<Item> SKYROOT_BOWL = ITEMS.register("skyroot_bowl", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CORNSTARCH_BOWL = ITEMS.register("cornstarch_bowl", () -> new Item(new Item.Properties().craftRemainder(SKYROOT_BOWL.get()).stacksTo(1)));

    public static final RegistryObject<Item> BLUE_SWET_JELLY = ITEMS.register("blue_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final RegistryObject<Item> GOLDEN_SWET_JELLY = ITEMS.register("golden_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final RegistryObject<Item> DARK_SWET_JELLY = ITEMS.register("dark_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final RegistryObject<Item> DARK_GUMMY_SWET = ITEMS.register("dark_gummy_swet", GummySwetItem::new);
    public static final RegistryObject<Item> ICESTONE_POPROCKS = ITEMS.register("icestone_poprocks", () -> new Item(new Item.Properties().food(GenesisFoods.ICESTONE_POPROCKS)));
    public static final RegistryObject<Item> COCOATRICE = ITEMS.register("cocoatrice", () -> new Item(new Item.Properties().food(GenesisFoods.COCOATRICE)));
    public static final RegistryObject<Item> WARPED_CHOCOLATES = ITEMS.register("warped_chocolates", () -> new Item(new Item.Properties().food(GenesisFoods.WARPED_CHOCOLATES)));
    public static final RegistryObject<Item> BLUEBERRY_LOLLIPOP = ITEMS.register("blueberry_lollipop", () -> new Item(new Item.Properties().food(GenesisFoods.BLUEBERRY_LOLLIPOP)));
    public static final RegistryObject<Item> ORANGE_LOLLIPOP = ITEMS.register("orange_lollipop", () -> new Item(new Item.Properties().food(GenesisFoods.ORANGE_LOLLIPOP)));
    public static final RegistryObject<Item> PINK_SWET_LOLLIPOP  = ITEMS.register("stomper_pop", () -> new Item(new Item.Properties().food(GenesisFoods.PINK_SWET_LOLLIPOP)));
    public static final RegistryObject<Item> ORANGE = ITEMS.register("orange", () -> new Item(new Item.Properties().food(GenesisFoods.ORANGE)));
    public static final RegistryObject<Item> WYNDBERRY = ITEMS.register("wyndberry", () -> new Item(new Item.Properties().food(GenesisFoods.WYNDBERRY)));
    public static final RegistryObject<Item> JELLY_PUMPKIN = ITEMS.register("jelly_pumpkin", () -> new Item(new Item.Properties().food(GenesisFoods.JELLY_PUMPKIN)));
    public static final RegistryObject<Item> CANDY_CORN = ITEMS.register("candy_corn", () -> new Item(new Item.Properties().food(GenesisFoods.CANDY_CORN)));
    public static final RegistryObject<Item> RAINBOW_STRAWBERRY = ITEMS.register("rainbow_strawberry", () -> new Item(new Item.Properties().food(GenesisFoods.RAINBOW_STRAWBERRY)));


    public static final RegistryObject<Item> BLUE_PARACHUTE = ITEMS.register("blue_parachute", () -> new ParachuteItem(GenesisEntityTypes.BLUE_PARACHUTE, new Item.Properties().durability(1)));
    public static final RegistryObject<Item> GREEN_PARACHUTE = ITEMS.register("green_parachute", () -> new ParachuteItem(GenesisEntityTypes.GREEN_PARACHUTE, new Item.Properties().durability(1)));
    public static final RegistryObject<Item> PURPLE_PARACHUTE = ITEMS.register("purple_parachute", () -> new ParachuteItem(GenesisEntityTypes.PURPLE_PARACHUTE, new Item.Properties().durability(1)));

    public static final RegistryObject<SpawnEggItem> DARK_SWET_SPAWN_EGG = ITEMS.register("dark_swet_spawn_egg", () -> new ForgeSpawnEggItem(GenesisEntityTypes.DARK_SWET, 0x947DC4, 0x4FB1DA, new Item.Properties()));
}
