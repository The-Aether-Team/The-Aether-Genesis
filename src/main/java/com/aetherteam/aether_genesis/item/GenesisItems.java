package com.aetherteam.aether_genesis.item;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import com.aetherteam.aether.item.food.GummySwetItem;
import com.aetherteam.aether.item.materials.SwetBallItem;
import com.aetherteam.aether.item.miscellaneous.DungeonKeyItem;
import com.aetherteam.aether.item.miscellaneous.ParachuteItem;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.item.accessories.cape.DexterityCapeItem;
import com.aetherteam.aether_genesis.item.accessories.cape.DyeableCapeItem;
import com.aetherteam.aether_genesis.item.accessories.companion.*;
import com.aetherteam.aether_genesis.item.accessories.miscellaneous.MouseEarCapItem;
import com.aetherteam.aether_genesis.item.accessories.pendant.SwettyPendantItem;
import com.aetherteam.aether_genesis.item.accessories.ring.BoneRingItem;
import com.aetherteam.aether_genesis.item.accessories.ring.CandyRingItem;
import com.aetherteam.aether_genesis.item.accessories.ring.SkyrootRingItem;
import com.aetherteam.aether_genesis.item.combat.PhoenixDartShooterItem;
import com.aetherteam.aether_genesis.item.food.GenesisFoods;
import com.aetherteam.aether_genesis.item.materials.ContinuumOrbItem;
import com.aetherteam.aether_genesis.item.miscellaneous.ContinuumBombItem;
import com.aetherteam.aether_genesis.item.miscellaneous.CrystalBottleItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class GenesisItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Genesis.MODID);

    public static final DeferredItem<Item> GOLDEN_SWET_BALL = ITEMS.register("golden_swet_ball", () -> new SwetBallItem(new Item.Properties()));
    public static final DeferredItem<Item> DARK_SWET_BALL = ITEMS.register("dark_swet_ball", () -> new SwetBallItem(new Item.Properties()));
    public static final DeferredItem<Item> CORNSTARCH_BOWL = ITEMS.register("cornstarch_bowl", () -> new Item(new Item.Properties().craftRemainder(Items.BOWL).stacksTo(1)));
    public static final DeferredItem<Item> CONTINUUM_ORB = ITEMS.register("continuum_orb", () -> new ContinuumOrbItem(new Item.Properties()));

    public static final DeferredItem<Item> BLUE_SWET_JELLY = ITEMS.register("blue_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final DeferredItem<Item> GOLDEN_SWET_JELLY = ITEMS.register("golden_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final DeferredItem<Item> DARK_SWET_JELLY = ITEMS.register("dark_swet_jelly", () -> new Item(new Item.Properties().food(GenesisFoods.SWET_JELLY)));
    public static final DeferredItem<Item> DARK_GUMMY_SWET = ITEMS.register("dark_gummy_swet", GummySwetItem::new);
    public static final DeferredItem<Item> ICESTONE_POPROCKS = ITEMS.register("icestone_poprocks", () -> new Item(new Item.Properties().food(GenesisFoods.ICESTONE_POPROCKS)));
    public static final DeferredItem<Item> COCOATRICE = ITEMS.register("cocoatrice", () -> new Item(new Item.Properties().food(GenesisFoods.COCOATRICE)));
    public static final DeferredItem<Item> WRAPPED_CHOCOLATES = ITEMS.register("wrapped_chocolates", () -> new Item(new Item.Properties().food(GenesisFoods.WRAPPED_CHOCOLATES)));
    public static final DeferredItem<Item> BLUEBERRY_LOLLIPOP = ITEMS.register("blueberry_lollipop", () -> new Item(new Item.Properties().food(GenesisFoods.BLUEBERRY_LOLLIPOP)));
    public static final DeferredItem<Item> ORANGE_LOLLIPOP = ITEMS.register("orange_lollipop", () -> new Item(new Item.Properties().food(GenesisFoods.ORANGE_LOLLIPOP)));
    public static final DeferredItem<Item> STOMPER_POP  = ITEMS.register("stomper_pop", () -> new Item(new Item.Properties().food(GenesisFoods.STOMPER_POP)));
    public static final DeferredItem<Item> ORANGE = ITEMS.register("orange", () -> new Item(new Item.Properties().food(GenesisFoods.ORANGE)));
    public static final DeferredItem<Item> WYNDBERRY = ITEMS.register("wyndberry", () -> new Item(new Item.Properties().food(GenesisFoods.WYNDBERRY)));
    public static final DeferredItem<Item> JELLY_PUMPKIN = ITEMS.register("jelly_pumpkin", () -> new Item(new Item.Properties().food(GenesisFoods.JELLY_PUMPKIN)));
    public static final DeferredItem<Item> CANDY_CORN = ITEMS.register("candy_corn", () -> new Item(new Item.Properties().food(GenesisFoods.CANDY_CORN)));
    public static final DeferredItem<Item> RAINBOW_STRAWBERRY = ITEMS.register("rainbow_strawberry", () -> new Item(new Item.Properties().food(GenesisFoods.RAINBOW_STRAWBERRY)));

    public static final DeferredItem<Item> BONE_RING = ITEMS.register("bone_ring", BoneRingItem::new);
    public static final DeferredItem<Item> CANDY_RING = ITEMS.register("candy_ring", CandyRingItem::new);
    public static final DeferredItem<Item> SKYROOT_RING = ITEMS.register("skyroot_ring", SkyrootRingItem::new);

    public static final DeferredItem<Item> LUCKY_BELL = ITEMS.register("lucky_bell", () -> new PendantItem(new ResourceLocation(Genesis.MODID, "lucky_bell"), AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ICE_PENDANT, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT)));
    public static final DeferredItem<Item> SWETTY_PENDANT = ITEMS.register("swetty_pendant", SwettyPendantItem::new);
    public static final DeferredItem<Item> DAGGERFROST_LOCKET = ITEMS.register("daggerfrost_locket", () -> new PendantItem(new ResourceLocation(Genesis.MODID, "daggerfrost_locket"), AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ICE_PENDANT, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT)));

    public static final DeferredItem<Item> CAPE = ITEMS.register("cape", () -> new DyeableCapeItem("white_cape", new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> DEXTERITY_CAPE = ITEMS.register("dexterity_cape", () -> new DexterityCapeItem("dexterity_cape", new Item.Properties().stacksTo(1).rarity(AETHER_LOOT)));

    public static final DeferredItem<Item> MOUSE_EAR_CAP = ITEMS.register("mouse_ear_cap", MouseEarCapItem::new);

    public static final DeferredItem<Item> FANGRIN_CAPSULE = ITEMS.register("fangrin_capsule", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> KRAISITH_CAPSULE = ITEMS.register("kraisith_capsule", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> FLEETING_STONE = ITEMS.register("fleeting_stone", () -> new FleetingStoneItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SOARING_STONE = ITEMS.register("soaring_stone", () -> new SoaringStoneItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ETHEREAL_STONE = ITEMS.register("ethereal_stone", () -> new EtherealStoneItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ORB_OF_ARKENZUS = ITEMS.register("orb_of_arkenzus", () -> new OrbOfArkenzusItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> FROSTPINE_TOTEM = ITEMS.register("frostpine_totem", () -> new FrostpineTotemItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> FROSTBOUND_STONE = ITEMS.register("frostbound_stone", () -> new FrostboundStoneItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> DEATH_SEAL = ITEMS.register("death_seal", () -> new DeathSealItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BABY_PINK_SWET = ITEMS.register("baby_pink_swet", BabyPinkSwetItem::new);

    public static final DeferredItem<Item> PHOENIX_DART_SHOOTER = ITEMS.register("phoenix_dart_shooter", PhoenixDartShooterItem::new);
    public static final DeferredItem<Item> CONTINUUM_BOMB = ITEMS.register("continuum_bomb", ContinuumBombItem::new);
    public static final DeferredItem<Item> CRYSTAL_EXPERIENCE_BOTTLE = ITEMS.register("crystal_experience_bottle", CrystalBottleItem::new);

    public static final DeferredItem<Item> MUSIC_DISC_AERWHALE = ITEMS.register("music_disc_aerwhale", () -> new RecordItem(5, GenesisSoundEvents.ITEM_MUSIC_DISC_AERWHALE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3560));
    public static final DeferredItem<Item> MUSIC_DISC_APPROACHES = ITEMS.register("music_disc_approaches", () -> new RecordItem(6, GenesisSoundEvents.ITEM_MUSIC_DISC_APPROACHES, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 5480));
    public static final DeferredItem<Item> MUSIC_DISC_DEMISE = ITEMS.register("music_disc_demise", () -> new RecordItem(7, GenesisSoundEvents.ITEM_MUSIC_DISC_DEMISE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 6000));
    public static final DeferredItem<Item> RECORDING_892 = ITEMS.register("recording_892", () -> new RecordItem(8, GenesisSoundEvents.ITEM_RECORDING_892, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 1940));

    public static final DeferredItem<Item> BLUE_PARACHUTE = ITEMS.register("blue_parachute", () -> new ParachuteItem(GenesisEntityTypes.BLUE_PARACHUTE, new Item.Properties().durability(1)));
    public static final DeferredItem<Item> GREEN_PARACHUTE = ITEMS.register("green_parachute", () -> new ParachuteItem(GenesisEntityTypes.GREEN_PARACHUTE, new Item.Properties().durability(1)));
    public static final DeferredItem<Item> PURPLE_PARACHUTE = ITEMS.register("purple_parachute", () -> new ParachuteItem(GenesisEntityTypes.PURPLE_PARACHUTE, new Item.Properties().durability(1)));

    public static final DeferredItem<Item> GUARDIAN_KEY = ITEMS.register("guardian_key", () -> new DungeonKeyItem(new ResourceLocation(Genesis.MODID, "guardian"), new Item.Properties().stacksTo(1).rarity(AETHER_LOOT).fireResistant()));
    public static final DeferredItem<Item> HOST_KEY = ITEMS.register("host_key", () -> new DungeonKeyItem(new ResourceLocation(Genesis.MODID, "host"), new Item.Properties().stacksTo(1).rarity(AETHER_LOOT).fireResistant()));
    public static final DeferredItem<Item> COG_KEY = ITEMS.register("cog_key", () -> new DungeonKeyItem(new ResourceLocation(Genesis.MODID, "cog"), new Item.Properties().stacksTo(1).rarity(AETHER_LOOT).fireResistant()));

    public static final DeferredItem<SpawnEggItem> CARRION_SPROUT_SPAWN_EGG = ITEMS.register("carrion_sprout_spawn_egg", () -> new DeferredSpawnEggItem(GenesisEntityTypes.CARRION_SPROUT, 0xC9D8E9, 0x597898, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> DARK_SWET_SPAWN_EGG = ITEMS.register("dark_swet_spawn_egg", () -> new DeferredSpawnEggItem(GenesisEntityTypes.DARK_SWET, 0x947DC4, 0x4FB1DA, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> TEMPEST_SPAWN_EGG = ITEMS.register("tempest_spawn_egg", () -> new DeferredSpawnEggItem(GenesisEntityTypes.TEMPEST, 0x3C464C, 0xC3E6F0, new Item.Properties()));

    public static final DeferredItem<SpawnEggItem> SKYROOT_CHEST_MIMIC_SPAWN_EGG = ITEMS.register("skyroot_chest_mimic_spawn_egg", () -> new DeferredSpawnEggItem(GenesisEntityTypes.SKYROOT_MIMIC, 0x696B51,0x47443A, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> BATTLE_SENTRY_SPAWN_EGG = ITEMS.register("battle_sentry_spawn_egg", () -> new DeferredSpawnEggItem(GenesisEntityTypes.BATTLE_SENTRY, 0x808080,0x3A8AEC, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> TRACKING_GOLEM_SPAWN_EGG = ITEMS.register("tracking_golem_spawn_egg", () -> new DeferredSpawnEggItem(GenesisEntityTypes.TRACKING_GOLEM, 0x808080,0x3A8AEC, new Item.Properties()));
}
