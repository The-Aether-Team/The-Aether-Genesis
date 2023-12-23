package com.aetherteam.aether_genesis.data.generators.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
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
        this.tag(AetherTags.Items.DUNGEON_BLOCKS).add(
                GenesisBlocks.DIVINE_CARVED_STONE.get().asItem(),
                GenesisBlocks.DIVINE_SENTRY_STONE.get().asItem(),
                GenesisBlocks.CARVED_PILLAR_TOP.get().asItem(),
                GenesisBlocks.CARVED_PILLAR.get().asItem()
        );
        this.tag(AetherTags.Items.LOCKED_DUNGEON_BLOCKS).add(
                GenesisBlocks.LOCKED_DIVINE_CARVED_STONE.get().asItem(),
                GenesisBlocks.LOCKED_DIVINE_SENTRY_STONE.get().asItem()
        );
        this.tag(AetherTags.Items.TRAPPED_DUNGEON_BLOCKS).add(
                GenesisBlocks.TRAPPED_DIVINE_CARVED_STONE.get().asItem(),
                GenesisBlocks.TRAPPED_DIVINE_SENTRY_STONE.get().asItem()
        );
        this.tag(AetherTags.Items.BOSS_DOORWAY_DUNGEON_BLOCKS).add(
                GenesisBlocks.BOSS_DOORWAY_DIVINE_CARVED_STONE.get().asItem(),
                GenesisBlocks.BOSS_DOORWAY_DIVINE_SENTRY_STONE.get().asItem()
        );
        this.tag(AetherTags.Items.TREASURE_DOORWAY_DUNGEON_BLOCKS).add(
                GenesisBlocks.TREASURE_DOORWAY_DIVINE_CARVED_STONE.get().asItem(),
                GenesisBlocks.TREASURE_DOORWAY_DIVINE_SENTRY_STONE.get().asItem()
        );
        this.tag(AetherTags.Items.SENTRY_BLOCKS).add(
                GenesisBlocks.DIVINE_CARVED_STONE.get().asItem(),
                GenesisBlocks.DIVINE_SENTRY_STONE.get().asItem(),
                GenesisBlocks.LOCKED_DIVINE_CARVED_STONE.get().asItem(),
                GenesisBlocks.LOCKED_DIVINE_SENTRY_STONE.get().asItem(),
                GenesisBlocks.TRAPPED_DIVINE_CARVED_STONE.get().asItem(),
                GenesisBlocks.TRAPPED_DIVINE_SENTRY_STONE.get().asItem(),
                GenesisBlocks.BOSS_DOORWAY_DIVINE_CARVED_STONE.get().asItem(),
                GenesisBlocks.BOSS_DOORWAY_DIVINE_SENTRY_STONE.get().asItem(),
                GenesisBlocks.TREASURE_DOORWAY_DIVINE_CARVED_STONE.get().asItem(),
                GenesisBlocks.TREASURE_DOORWAY_DIVINE_SENTRY_STONE.get().asItem(),
                GenesisBlocks.CARVED_PILLAR_TOP.get().asItem(),
                GenesisBlocks.CARVED_PILLAR.get().asItem(),
                GenesisBlocks.DIVINE_CARVED_WALL.get().asItem(),
                GenesisBlocks.DIVINE_CARVED_STAIRS.get().asItem(),
                GenesisBlocks.DIVINE_CARVED_SLAB.get().asItem()
        );

        this.tag(ItemTags.SAPLINGS).add(
                GenesisBlocks.BLUE_SKYROOT_SAPLING.get().asItem(),
                GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get().asItem());
        this.tag(ItemTags.LEAVES).add(
                GenesisBlocks.BLUE_SKYROOT_LEAVES.get().asItem(),
                GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get().asItem());
        this.tag(ItemTags.STAIRS).add(
                GenesisBlocks.DIVINE_CARVED_STAIRS.get().asItem());
        this.tag(ItemTags.SLABS).add(
                GenesisBlocks.DIVINE_CARVED_SLAB.get().asItem());
        this.tag(ItemTags.WALLS).add(
                GenesisBlocks.SKYROOT_LOG_WALL.get().asItem(),
                GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get().asItem(),
                GenesisBlocks.SKYROOT_WOOD_WALL.get().asItem(),
                GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get().asItem(),
                GenesisBlocks.DIVINE_CARVED_WALL.get().asItem());
        this.tag(ItemTags.MUSIC_DISCS).add(
                GenesisItems.MUSIC_DISC_AERWHALE.get(),
                GenesisItems.MUSIC_DISC_APPROACHES.get(),
                GenesisItems.MUSIC_DISC_DEMISE.get(),
                GenesisItems.RECORDING_892.get());

        //todo move to new aether accessory tags eventually
        this.tag(AetherTags.Items.AETHER_ACCESSORY).add(GenesisItems.MOUSE_EAR_CAP.get());
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
                GenesisItems.FLEETING_STONE.get(),
                GenesisItems.SOARING_STONE.get(),
                GenesisItems.ETHEREAL_STONE.get(),
                GenesisItems.ORB_OF_ARKENZUS.get(),
                GenesisItems.FROSTPINE_TOTEM.get(),
                GenesisItems.BABY_PINK_SWET.get()); //todo temporary

        this.tag(GenesisTags.Items.SKYROOT_LADDER_CRAFTING).addTag(
                AetherTags.Items.SKYROOT_STICKS);
        this.tag(GenesisTags.Items.HOLYSTONE_FURNACE_CRAFTING).add(
                AetherBlocks.HOLYSTONE.get().asItem());
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
