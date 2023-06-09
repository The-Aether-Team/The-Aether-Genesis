package com.aetherteam.aether_genesis.data.generators.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenesisItemTagData extends ItemTagsProvider {
    public GenesisItemTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, Genesis.MODID, helper);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.copy(GenesisTags.Blocks.LOG_WALLS, GenesisTags.Items.LOG_WALLS);

        this.tag(AetherTags.Items.CRAFTS_SKYROOT_PLANKS).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get().asItem(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get().asItem(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get().asItem(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get().asItem());
        this.tag(AetherTags.Items.SWET_BALLS).add(
                GenesisItems.GOLDEN_SWET_BALL.get(),
                GenesisItems.DARK_SWET_BALL.get());
        this.tag(AetherTags.Items.DEPLOYABLE_PARACHUTES).add(
                GenesisItems.GREEN_PARACHUTE.get(),
                GenesisItems.PURPLE_PARACHUTE.get());
        this.tag(AetherTags.Items.DUNGEON_KEYS).add(
                GenesisItems.COG_KEY.get(),
                GenesisItems.GUARDIAN_KEY.get(),
                GenesisItems.HOST_KEY.get());

        this.tag(ItemTags.SAPLINGS).add(
                GenesisBlocks.BLUE_SKYROOT_SAPLING.get().asItem(),
                GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get().asItem());
        this.tag(ItemTags.LEAVES).add(
                GenesisBlocks.BLUE_SKYROOT_LEAVES.get().asItem(),
                GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get().asItem());
        this.tag(ItemTags.WALLS).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get().asItem(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get().asItem(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get().asItem(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get().asItem());
        this.tag(ItemTags.MUSIC_DISCS).add(
                GenesisItems.MUSIC_DISC_AERWHALE.get(),
                GenesisItems.MUSIC_DISC_APPROACHES.get(),
                GenesisItems.MUSIC_DISC_DEMISE.get(),
                GenesisItems.RECORDING_892.get());

        //todo move to new aether accessory tags eventually
        this.tag(AetherTags.Items.AETHER_ACCESSORY).add(
                GenesisItems.CRYSTAL_EXP_BOTTLE.get(),
                GenesisItems.MOUSE_EAR_CAP.get());
        this.tag(AetherTags.Items.AETHER_RING).add(
                GenesisItems.BONE_RING.get(),
                GenesisItems.CANDY_RING.get(),
                GenesisItems.SKYROOT_RING.get());
        this.tag(AetherTags.Items.AETHER_PENDANT).add(
                GenesisItems.LUCKY_BELL.get(),
                GenesisItems.SWETTY_PENDANT.get(),
                GenesisItems.DAGGERFROST_LOCKET.get());
        this.tag(AetherTags.Items.AETHER_CAPE).add(
                GenesisItems.CAPE.get(),
                GenesisItems.DEXTERITY_CAPE.get(),
                GenesisItems.SOARING_STONE.get()); //todo temporary
        this.tag(GenesisTags.Items.SWET_JELLY).add(
                GenesisItems.BLUE_SWET_JELLY.get(),
                GenesisItems.DARK_SWET_JELLY.get(),
                GenesisItems.GOLDEN_SWET_JELLY.get());
        this.tag(GenesisTags.Items.DARTS).add(
                AetherItems.GOLDEN_DART.get(),
                AetherItems.POISON_DART.get(),
                AetherItems.ENCHANTED_DART.get());

        // Forge
        this.tag(Tags.Items.CHESTS_WOODEN).add(GenesisBlocks.SKYROOT_CHEST.get().asItem());
    }
}
