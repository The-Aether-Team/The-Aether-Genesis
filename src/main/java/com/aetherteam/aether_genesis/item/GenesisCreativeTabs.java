package com.aetherteam.aether_genesis.item;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Genesis.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisCreativeTabs {
    @SubscribeEvent
    public static void buildCreativeModeTabs(CreativeModeTabEvent.BuildContents event) {
        CreativeModeTab tab = event.getTab();
        if (tab == AetherCreativeTabs.AETHER_BUILDING_BLOCKS) {
            event.getEntries().putAfter(new ItemStack(AetherBlocks.SKYROOT_LOG.get()), new ItemStack(GenesisBlocks.SKYROOT_LOG_WALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.STRIPPED_SKYROOT_LOG.get()), new ItemStack(GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.SKYROOT_WOOD.get()), new ItemStack(GenesisBlocks.SKYROOT_WOOD_WALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherBlocks.STRIPPED_SKYROOT_WOOD.get()), new ItemStack(GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_NATURAL_BLOCKS) {
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
            event.getEntries().putAfter(new ItemStack(AetherBlocks.GOLDEN_OAK_SAPLING.get()), new ItemStack(GenesisBlocks.CRYSTAL_TREE_SAPLING.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisBlocks.CRYSTAL_TREE_SAPLING.get()), new ItemStack(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES) {
            event.getEntries().putAfter(new ItemStack(AetherItems.GOLDEN_PARACHUTE.get()), new ItemStack(GenesisItems.BLUE_PARACHUTE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.BLUE_PARACHUTE.get()), new ItemStack(GenesisItems.GREEN_PARACHUTE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.GREEN_PARACHUTE.get()), new ItemStack(GenesisItems.PURPLE_PARACHUTE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_FOOD_AND_DRINKS) {
            event.getEntries().putAfter(new ItemStack(AetherItems.ENCHANTED_BERRY.get()), new ItemStack(GenesisItems.BLUE_SWET_JELLY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.BLUE_SWET_JELLY.get()), new ItemStack(GenesisItems.GOLDEN_SWET_JELLY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.GOLDEN_SWET_JELLY.get()), new ItemStack(GenesisItems.DARK_SWET_JELLY.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(AetherItems.GOLDEN_GUMMY_SWET.get()), new ItemStack(GenesisItems.DARK_GUMMY_SWET.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_INGREDIENTS) {
            event.getEntries().putAfter(new ItemStack(AetherItems.SWET_BALL.get()), new ItemStack(GenesisItems.GOLDEN_SWET_BALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(GenesisItems.GOLDEN_SWET_BALL.get()), new ItemStack(GenesisItems.DARK_SWET_BALL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == AetherCreativeTabs.AETHER_SPAWN_EGGS) {
            event.getEntries().putAfter(new ItemStack(AetherItems.COCKATRICE_SPAWN_EGG.get()), new ItemStack(GenesisItems.DARK_SWET_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}