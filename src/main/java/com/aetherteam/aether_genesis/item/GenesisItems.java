package com.aetherteam.aether_genesis.item;

import com.aetherteam.aether.item.food.GummySwetItem;
import com.aetherteam.aether.item.materials.SwetBallItem;
import com.aetherteam.aether.item.miscellaneous.AetherRecordItem;
import com.aetherteam.aether.item.miscellaneous.DungeonKeyItem;
import com.aetherteam.aether.item.miscellaneous.ParachuteItem;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.item.accessories.*;
import com.aetherteam.aether_genesis.item.combat.PhoenixDartShooterItem;
import com.aetherteam.aether_genesis.item.food.GenesisFoods;
import com.aetherteam.aether_genesis.item.materials.ContinuumOrbItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;
import static com.aetherteam.aether.item.AetherItems.GOLDEN_DART;

public class GenesisItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Genesis.MODID);

    public static final RegistryObject<Item> GOLDEN_SWET_BALL = ITEMS.register("golden_swet_ball", () -> new SwetBallItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_SWET_BALL = ITEMS.register("dark_swet_ball", () -> new SwetBallItem(new Item.Properties()));
    public static final RegistryObject<Item> SKYROOT_BOWL = ITEMS.register("skyroot_bowl", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CORNSTARCH_BOWL = ITEMS.register("cornstarch_bowl", () -> new Item(new Item.Properties().craftRemainder(SKYROOT_BOWL.get()).stacksTo(1)));
    public static final RegistryObject<Item> CONTINUUM_ORB = ITEMS.register("continuum_orb", () -> new ContinuumOrbItem(new Item.Properties()));

    public static final RegistryObject<Item> BLUE_SWET_JELLY = ITEMS.register("blue_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final RegistryObject<Item> GOLDEN_SWET_JELLY = ITEMS.register("golden_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final RegistryObject<Item> DARK_SWET_JELLY = ITEMS.register("dark_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final RegistryObject<Item> DARK_GUMMY_SWET = ITEMS.register("dark_gummy_swet", GummySwetItem::new);
    public static final RegistryObject<Item> ICESTONE_POPROCKS = ITEMS.register("icestone_poprocks", () -> new Item(new Item.Properties().food(GenesisFoods.ICESTONE_POPROCKS)));
    public static final RegistryObject<Item> COCOATRICE = ITEMS.register("cocoatrice", () -> new Item(new Item.Properties().food(GenesisFoods.COCOATRICE)));
    public static final RegistryObject<Item> WRAPPED_CHOCOLATES = ITEMS.register("wrapped_chocolates", () -> new Item(new Item.Properties().food(GenesisFoods.WRAPPED_CHOCOLATES)));
    public static final RegistryObject<Item> BLUEBERRY_LOLLIPOP = ITEMS.register("blueberry_lollipop", () -> new Item(new Item.Properties().food(GenesisFoods.BLUEBERRY_LOLLIPOP)));
    public static final RegistryObject<Item> ORANGE_LOLLIPOP = ITEMS.register("orange_lollipop", () -> new Item(new Item.Properties().food(GenesisFoods.ORANGE_LOLLIPOP)));
    public static final RegistryObject<Item> STOMPER_POP  = ITEMS.register("stomper_pop", () -> new Item(new Item.Properties().food(GenesisFoods.STOMPER_POP)));
    public static final RegistryObject<Item> ORANGE = ITEMS.register("orange", () -> new Item(new Item.Properties().food(GenesisFoods.ORANGE)));
    public static final RegistryObject<Item> WYNDBERRY = ITEMS.register("wyndberry", () -> new Item(new Item.Properties().food(GenesisFoods.WYNDBERRY)));
    public static final RegistryObject<Item> JELLY_PUMPKIN = ITEMS.register("jelly_pumpkin", () -> new Item(new Item.Properties().food(GenesisFoods.JELLY_PUMPKIN)));
    public static final RegistryObject<Item> CANDY_CORN = ITEMS.register("candy_corn", () -> new Item(new Item.Properties().food(GenesisFoods.CANDY_CORN)));
    public static final RegistryObject<Item> RAINBOW_STRAWBERRY = ITEMS.register("rainbow_strawberry", () -> new Item(new Item.Properties().food(GenesisFoods.RAINBOW_STRAWBERRY)));

    public static final RegistryObject<Item> CRYSTAL_EXP_BOTTLE = ITEMS.register("crystal_exp_bottle", () -> new CrystalBottleItem(new Item.Properties().stacksTo(1).rarity(AETHER_LOOT)));
    public static final RegistryObject<Item> BONE_RING = ITEMS.register("bone_ring", () -> new BoneRingItem(new Item.Properties().stacksTo(1).rarity(AETHER_LOOT)));
    public static final RegistryObject<Item> CANDY_RING = ITEMS.register("candy_ring", () -> new CandyRingItem(new Item.Properties().stacksTo(1).rarity(AETHER_LOOT)));
    public static final RegistryObject<Item> SKYROOT_RING = ITEMS.register("skyroot_ring", () -> new SkyrootRingItem(new Item.Properties().stacksTo(1).rarity(AETHER_LOOT)));

    public static final RegistryObject<Item> CAPE = ITEMS.register("cape", () -> new DyeableCapeItem("white_cape", new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DEXTERITY_CAPE = ITEMS.register("dexterity_cape", () -> new DexterityCapeItem("dexterity_cape", new Item.Properties().stacksTo(1).rarity(AETHER_LOOT)));

    public static final RegistryObject<Item> PHOENIX_DART_SHOOTER = ITEMS.register("phoenix_dart_shooter", () -> new PhoenixDartShooterItem(GOLDEN_DART, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT)));
    public static final RegistryObject<Item> CONTINUUM_BOMB = ITEMS.register("continuum_bomb", () -> new ContinuumBombItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MUSIC_DISC_AERWHALE = ITEMS.register("music_disc_aerwhale", () -> new AetherRecordItem(5, GenesisSoundEvents.ITEM_MUSIC_DISC_AERWHALE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3560));
    public static final RegistryObject<Item> MUSIC_DISC_APPROACHES = ITEMS.register("music_disc_approaches", () -> new AetherRecordItem(6, GenesisSoundEvents.ITEM_MUSIC_DISC_APPROACHES, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 5480));
    public static final RegistryObject<Item> MUSIC_DISC_DEMISE = ITEMS.register("music_disc_demise", () -> new AetherRecordItem(7, GenesisSoundEvents.ITEM_MUSIC_DISC_DEMISE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 6000));
    public static final RegistryObject<Item> RECORDING_892 = ITEMS.register("recording_892", () -> new AetherRecordItem(8, GenesisSoundEvents.ITEM_RECORDING_892, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 1940));

    public static final RegistryObject<Item> BLUE_PARACHUTE = ITEMS.register("blue_parachute", () -> new ParachuteItem(GenesisEntityTypes.BLUE_PARACHUTE, new Item.Properties().durability(1)));
    public static final RegistryObject<Item> GREEN_PARACHUTE = ITEMS.register("green_parachute", () -> new ParachuteItem(GenesisEntityTypes.GREEN_PARACHUTE, new Item.Properties().durability(1)));
    public static final RegistryObject<Item> PURPLE_PARACHUTE = ITEMS.register("purple_parachute", () -> new ParachuteItem(GenesisEntityTypes.PURPLE_PARACHUTE, new Item.Properties().durability(1)));

    public static final RegistryObject<Item> GUARDIAN_KEY = ITEMS.register("guardian_key", () -> new DungeonKeyItem(new ResourceLocation(Genesis.MODID, "guardian"), new Item.Properties().stacksTo(1).rarity(AETHER_LOOT).fireResistant()));
    public static final RegistryObject<Item> HOST_KEY = ITEMS.register("host_key", () -> new DungeonKeyItem(new ResourceLocation(Genesis.MODID, "host"), new Item.Properties().stacksTo(1).rarity(AETHER_LOOT).fireResistant()));

    public static final RegistryObject<SpawnEggItem> CARRION_SPROUT_SPAWN_EGG = ITEMS.register("carrion_sprout_spawn_egg", () -> new ForgeSpawnEggItem(GenesisEntityTypes.CARRION_SPROUT, 0xC9D8E9, 0x597898, new Item.Properties()));
    public static final RegistryObject<SpawnEggItem> DARK_SWET_SPAWN_EGG = ITEMS.register("dark_swet_spawn_egg", () -> new ForgeSpawnEggItem(GenesisEntityTypes.DARK_SWET, 0x947DC4, 0x4FB1DA, new Item.Properties()));
    public static final RegistryObject<SpawnEggItem> TEMPEST_SPAWN_EGG = ITEMS.register("tempest_spawn_egg", () -> new ForgeSpawnEggItem(GenesisEntityTypes.TEMPEST, 0x3C464C, 0xC3E6F0, new Item.Properties()));

    public static final RegistryObject<SpawnEggItem> BATTLE_SENTRY_SPAWN_EGG = ITEMS.register("battle_sentry_spawn_egg", () -> new ForgeSpawnEggItem(GenesisEntityTypes.BATTLE_SENTRY, 0x808080,0x3A8AEC, new Item.Properties()));
    public static final RegistryObject<SpawnEggItem> TRACKING_GOLEM_SPAWN_EGG = ITEMS.register("tracking_golem_spawn_egg", () -> new ForgeSpawnEggItem(GenesisEntityTypes.TRACKING_GOLEM, 0x808080,0x3A8AEC, new Item.Properties()));
}
