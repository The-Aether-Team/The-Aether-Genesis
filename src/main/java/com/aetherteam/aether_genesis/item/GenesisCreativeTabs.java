package com.aetherteam.aether_genesis.item;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Genesis.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisCreativeTabs {
    @SubscribeEvent
    public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if (tab == AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey()) {
            event.getEntries().putAfter(new ItemStack(AetherBlocks.SKYROOT_LOG.get()), new ItemStack(GenesisBlocks.SKYROOT_LOG_WALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.STRIPPED_SKYROOT_LOG.get()), new ItemStack(GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.SKYROOT_WOOD.get()), new ItemStack(GenesisBlocks.SKYROOT_WOOD_WALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.STRIPPED_SKYROOT_WOOD.get()), new ItemStack(GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.HOLYSTONE_HEADSTONE.get()), new ItemStack(GenesisBlocks.HOLYSTONE_HEADSTONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.HOLYSTONE_KEYSTONE.get()), new ItemStack(GenesisBlocks.HOLYSTONE_KEYSTONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.HOLYSTONE_HIGHLIGHT.get()), new ItemStack(GenesisBlocks.HOLYSTONE_HIGHLIGHT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.SKYROOT_LADDER.get()), new ItemStack(GenesisBlocks.SKYROOT_LADDER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        }
        if (tab == AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey()) {
            event.getEntries().putAfter(new ItemStack(AetherBlocks.ENCHANTED_AETHER_GRASS_BLOCK.get()), new ItemStack(GenesisBlocks.ENCHANTED_GRASS_BLOCK.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.SKYROOT_LOG.get()), new ItemStack(GenesisBlocks.SKYROOT_LOG_WALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.GOLDEN_AERCLOUD.get()), new ItemStack(GenesisBlocks.GREEN_AERCLOUD.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.GREEN_AERCLOUD.get()), new ItemStack(GenesisBlocks.PURPLE_AERCLOUD.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.PURPLE_AERCLOUD.get()), new ItemStack(GenesisBlocks.STORM_AERCLOUD.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.SKYROOT_LEAVES.get()), new ItemStack(GenesisBlocks.BLUE_SKYROOT_LEAVES.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.SKYROOT_SAPLING.get()), new ItemStack(GenesisBlocks.BLUE_SKYROOT_SAPLING.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.BLUE_SKYROOT_LEAVES.get()), new ItemStack(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.BLUE_SKYROOT_SAPLING.get()), new ItemStack(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.CRYSTAL_FRUIT_LEAVES.get()), new ItemStack(GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get()), new ItemStack(GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.GOLDEN_OAK_SAPLING.get()), new ItemStack(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.ORANGE_TREE.get()), new ItemStack(GenesisBlocks.ORANGE_TREE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.CONTINUUM_ORE.get()), new ItemStack(GenesisBlocks.CONTINUUM_ORE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES.getKey()) {
            event.getEntries().putAfter(new ItemStack(AetherItems.GOLDEN_PARACHUTE.get()), new ItemStack(GenesisItems.BLUE_PARACHUTE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.BLUE_PARACHUTE.get()), new ItemStack(GenesisItems.GREEN_PARACHUTE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.GREEN_PARACHUTE.get()), new ItemStack(GenesisItems.PURPLE_PARACHUTE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.PHOENIX_DART_SHOOTER.get()), new ItemStack(GenesisItems.PHOENIX_DART_SHOOTER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.CONTINUUM_BOMB.get()), new ItemStack(GenesisItems.CONTINUUM_BOMB.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.MUSIC_DISC_AERWHALE.get()), new ItemStack(GenesisItems.MUSIC_DISC_AERWHALE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.MUSIC_DISC_APPROACHES.get()), new ItemStack(GenesisItems.MUSIC_DISC_APPROACHES.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.MUSIC_DISC_DEMISE.get()), new ItemStack(GenesisItems.MUSIC_DISC_DEMISE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.RECORDING_892.get()), new ItemStack(GenesisItems.RECORDING_892.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.BRONZE_DUNGEON_KEY.get()), new ItemStack(GenesisItems.GUARDIAN_KEY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.BRONZE_DUNGEON_KEY.get()), new ItemStack(GenesisItems.HOST_KEY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.BRONZE_DUNGEON_KEY.get()), new ItemStack(GenesisItems.COG_KEY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES.getKey()) {
            event.getEntries().putAfter(new ItemStack(GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE.get()), new ItemStack(GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.BONE_RING.get()), new ItemStack(GenesisItems.BONE_RING.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.CANDY_RING.get()), new ItemStack(GenesisItems.CANDY_RING.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.SKYROOT_RING.get()), new ItemStack(GenesisItems.SKYROOT_RING.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.CAPE.get()), new ItemStack(GenesisItems.CAPE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.DEXTERITY_CAPE.get()), new ItemStack(GenesisItems.DEXTERITY_CAPE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.SWETTY_PENDANT.get()), new ItemStack(GenesisItems.SWETTY_PENDANT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.LUCKY_BELL.get()), new ItemStack(GenesisItems.LUCKY_BELL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.DAGGERFROST_LOCKET.get()), new ItemStack(GenesisItems.DAGGERFROST_LOCKET.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.MOUSE_EAR_CAP.get()), new ItemStack(GenesisItems.MOUSE_EAR_CAP.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.FLEETING_STONE.get()), new ItemStack(GenesisItems.FLEETING_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.SOARING_STONE.get()), new ItemStack(GenesisItems.SOARING_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.ETHEREAL_STONE.get()), new ItemStack(GenesisItems.ETHEREAL_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.ORB_OF_ARKENZUS.get()), new ItemStack(GenesisItems.ORB_OF_ARKENZUS.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.FROSTPINE_TOTEM.get()), new ItemStack(GenesisItems.FROSTPINE_TOTEM.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.BABY_PINK_SWET.get()), new ItemStack(GenesisItems.BABY_PINK_SWET.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_FOOD_AND_DRINKS.getKey()) {
            event.getEntries().putAfter(new ItemStack(AetherItems.ENCHANTED_BERRY.get()), new ItemStack(GenesisItems.BLUE_SWET_JELLY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.BLUE_SWET_JELLY.get()), new ItemStack(GenesisItems.GOLDEN_SWET_JELLY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.GOLDEN_SWET_JELLY.get()), new ItemStack(GenesisItems.DARK_SWET_JELLY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.GOLDEN_GUMMY_SWET.get()), new ItemStack(GenesisItems.DARK_GUMMY_SWET.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.ICESTONE_POPROCKS.get()), new ItemStack(GenesisItems.ICESTONE_POPROCKS.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.COCOATRICE.get()), new ItemStack(GenesisItems.COCOATRICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.WRAPPED_CHOCOLATES.get()), new ItemStack(GenesisItems.WRAPPED_CHOCOLATES.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.BLUEBERRY_LOLLIPOP.get()), new ItemStack(GenesisItems.BLUEBERRY_LOLLIPOP.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.ORANGE_LOLLIPOP.get()), new ItemStack(GenesisItems.ORANGE_LOLLIPOP.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.STOMPER_POP.get()), new ItemStack(GenesisItems.STOMPER_POP.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.ORANGE.get()), new ItemStack(GenesisItems.ORANGE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.WYNDBERRY.get()), new ItemStack(GenesisItems.WYNDBERRY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.JELLY_PUMPKIN.get()), new ItemStack(GenesisItems.JELLY_PUMPKIN.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.CANDY_CORN.get()), new ItemStack(GenesisItems.CANDY_CORN.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.RAINBOW_STRAWBERRY.get()), new ItemStack(GenesisItems.RAINBOW_STRAWBERRY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_INGREDIENTS.getKey()) {
            event.getEntries().putAfter(new ItemStack(GenesisItems.GOLDEN_SWET_BALL.get()), new ItemStack(GenesisItems.GOLDEN_SWET_BALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.DARK_SWET_BALL.get()), new ItemStack(GenesisItems.DARK_SWET_BALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.CORNSTARCH_BOWL.get()), new ItemStack(GenesisItems.CORNSTARCH_BOWL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.CONTINUUM_ORB.get()), new ItemStack(GenesisItems.CONTINUUM_ORB.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        if (tab == AetherCreativeTabs.AETHER_SPAWN_EGGS.getKey()) {
            event.getEntries().putAfter(new ItemStack(AetherItems.COCKATRICE_SPAWN_EGG.get()), new ItemStack(GenesisItems.CARRION_SPROUT_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.COCKATRICE_SPAWN_EGG.get()), new ItemStack(GenesisItems.ZEPHYROO_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.COCKATRICE_SPAWN_EGG.get()), new ItemStack(GenesisItems.DARK_SWET_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.COCKATRICE_SPAWN_EGG.get()), new ItemStack(GenesisItems.TEMPEST_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.COCKATRICE_SPAWN_EGG.get()), new ItemStack(GenesisItems.SKYROOT_CHEST_MIMIC_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.COCKATRICE_SPAWN_EGG.get()), new ItemStack(GenesisItems.BATTLE_SENTRY_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.COCKATRICE_SPAWN_EGG.get()), new ItemStack(GenesisItems.TRACKING_GOLEM_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS.getKey()) {
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.SKYROOT_CRAFTING_TABLE.get()), new ItemStack(GenesisBlocks.SKYROOT_CRAFTING_TABLE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.HOLYSTONE_FURNACE.get()), new ItemStack(GenesisBlocks.HOLYSTONE_FURNACE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.SKYROOT_CHEST.get()), new ItemStack(GenesisBlocks.SKYROOT_CHEST.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_DUNGEON_BLOCKS.getKey()) {
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.CARVED_PILLAR_SIDE.get()), new ItemStack(GenesisBlocks.CARVED_PILLAR_SIDE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.CARVED_PILLAR.get()), new ItemStack(GenesisBlocks.CARVED_PILLAR.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.CHEST_MIMIC.get()), new ItemStack(GenesisBlocks.SKYROOT_CHEST_MIMIC.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.DIVINE_CARVED_STONE.get()), new ItemStack(GenesisBlocks.DIVINE_CARVED_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.LOCKED_DIVINE_CARVED_STONE.get()), new ItemStack(GenesisBlocks.LOCKED_DIVINE_CARVED_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.TRAPPED_DIVINE_CARVED_STONE.get()), new ItemStack(GenesisBlocks.TRAPPED_DIVINE_CARVED_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.BOSS_DOORWAY_DIVINE_CARVED_STONE.get()), new ItemStack(GenesisBlocks.BOSS_DOORWAY_DIVINE_CARVED_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.TREASURE_DOORWAY_DIVINE_CARVED_STONE.get()), new ItemStack(GenesisBlocks.TREASURE_DOORWAY_DIVINE_CARVED_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.DIVINE_CARVED_STAIRS.get()), new ItemStack(GenesisBlocks.DIVINE_CARVED_STAIRS.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.DIVINE_CARVED_SLAB.get()), new ItemStack(GenesisBlocks.DIVINE_CARVED_SLAB.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.DIVINE_CARVED_WALL.get()), new ItemStack(GenesisBlocks.DIVINE_CARVED_WALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.DIVINE_SENTRY_STONE.get()), new ItemStack(GenesisBlocks.DIVINE_SENTRY_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.LOCKED_DIVINE_SENTRY_STONE.get()), new ItemStack(GenesisBlocks.LOCKED_DIVINE_SENTRY_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.TRAPPED_DIVINE_SENTRY_STONE.get()), new ItemStack(GenesisBlocks.TRAPPED_DIVINE_SENTRY_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.BOSS_DOORWAY_DIVINE_SENTRY_STONE.get()), new ItemStack(GenesisBlocks.BOSS_DOORWAY_DIVINE_SENTRY_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.TREASURE_DOORWAY_DIVINE_SENTRY_STONE.get()), new ItemStack(GenesisBlocks.TREASURE_DOORWAY_DIVINE_SENTRY_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.BLOOD_MOSS_HOLYSTONE.get()), new ItemStack(GenesisBlocks.BLOOD_MOSS_HOLYSTONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        event.getEntries().remove(new ItemStack(AetherItems.RED_CAPE.get()));
        event.getEntries().remove(new ItemStack(AetherItems.BLUE_CAPE.get()));
        event.getEntries().remove(new ItemStack(AetherItems.YELLOW_CAPE.get()));
        event.getEntries().remove(new ItemStack(AetherItems.WHITE_CAPE.get()));
    }
}
