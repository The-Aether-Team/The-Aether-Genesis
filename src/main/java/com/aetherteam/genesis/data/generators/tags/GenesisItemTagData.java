package com.aetherteam.genesis.data.generators.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.GenesisTags;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenesisItemTagData extends ItemTagsProvider {
    public GenesisItemTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, AetherGenesis.MODID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        // Genesis
        this.copy(GenesisTags.Blocks.LOG_WALLS, GenesisTags.Items.LOG_WALLS);

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

        // Aether
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
        this.tag(AetherTags.Items.BRONZE_DUNGEON_LOOT).add(
                GenesisItems.DARK_GUMMY_SWET.get(),
                GenesisItems.FROSTPINE_TOTEM.get(),
                GenesisItems.DEXTERITY_CAPE.get(),
                GenesisItems.FANGRIN_CAPSULE.get(),
                GenesisItems.KRAISITH_CAPSULE.get(),
                GenesisItems.SOARING_STONE.get(),
                GenesisItems.FLEETING_STONE.get(),
                GenesisItems.BABY_PINK_SWET.get(),
                GenesisItems.MOUSE_EAR_CAP.get(),
                GenesisItems.MUSIC_DISC_DEMISE.get()
        );
        this.tag(AetherTags.Items.SILVER_DUNGEON_LOOT).add(
                GenesisItems.DARK_GUMMY_SWET.get(),
                GenesisItems.PHOENIX_DART_SHOOTER.get(),
                GenesisItems.SKYROOT_RING.get(),
                GenesisItems.SWETTY_PENDANT.get(),
                GenesisItems.LUCKY_BELL.get(),
                GenesisItems.FROSTBOUND_STONE.get(),
                GenesisItems.ORB_OF_ARKENZUS.get(),
                GenesisItems.ETHEREAL_STONE.get(),
                GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE.get(),
                GenesisItems.MUSIC_DISC_APPROACHES.get()
        );
        this.tag(AetherTags.Items.GOLD_DUNGEON_LOOT).add(
                GenesisItems.BONE_RING.get(),
                GenesisItems.CANDY_RING.get(),
                GenesisItems.DAGGERFROST_LOCKET.get(),
                GenesisItems.DEATH_SEAL.get(),
                GenesisItems.RECORDING_892.get(),
                GenesisItems.CONTINUUM_BOMB.get()
        );
        this.tag(AetherTags.Items.PHYG_TEMPTATION_ITEMS).add(GenesisItems.ORANGE.get());
        this.tag(AetherTags.Items.FLYING_COW_TEMPTATION_ITEMS).add(GenesisItems.ORANGE.get());
        this.tag(AetherTags.Items.SHEEPUFF_TEMPTATION_ITEMS).add(GenesisItems.ORANGE.get());
        this.tag(AetherTags.Items.AERBUNNY_TEMPTATION_ITEMS).add(GenesisItems.ORANGE.get());

        this.tag(AetherTags.Items.ACCESSORIES_RINGS).add(
                GenesisItems.BONE_RING.get(),
                GenesisItems.CANDY_RING.get(),
                GenesisItems.SKYROOT_RING.get());
        this.tag(AetherTags.Items.ACCESSORIES_PENDANTS).add(
                GenesisItems.LUCKY_BELL.get(),
                GenesisItems.SWETTY_PENDANT.get(),
                GenesisItems.DAGGERFROST_LOCKET.get());
        this.tag(AetherTags.Items.ACCESSORIES_CAPES).add(
                GenesisItems.CAPE.get(),
                GenesisItems.DEXTERITY_CAPE.get(),
                GenesisItems.FANGRIN_CAPSULE.get(), //todo temporary
                GenesisItems.KRAISITH_CAPSULE.get(),
                GenesisItems.FLEETING_STONE.get(),
                GenesisItems.SOARING_STONE.get(),
                GenesisItems.ETHEREAL_STONE.get(),
                GenesisItems.ORB_OF_ARKENZUS.get(),
                GenesisItems.FROSTPINE_TOTEM.get(),
                GenesisItems.FROSTBOUND_STONE.get(),
                GenesisItems.DEATH_SEAL.get(),
                GenesisItems.BABY_PINK_SWET.get());
        this.tag(AetherTags.Items.ACCESSORIES_MISCELLANEOUS).add(GenesisItems.MOUSE_EAR_CAP.get());

        // Forge
        this.tag(Tags.Items.CHESTS_WOODEN).add(GenesisBlocks.SKYROOT_CHEST.get().asItem());
        this.tag(Tags.Items.ORE_RATES_SINGULAR).add(GenesisBlocks.CONTINUUM_ORE.get().asItem());
        this.tag(Tags.Items.ORES).add(GenesisBlocks.CONTINUUM_ORE.get().asItem());

        // Vanilla
        this.tag(ItemTags.SAPLINGS).add(
                GenesisBlocks.BLUE_SKYROOT_SAPLING.get().asItem(),
                GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get().asItem());
        this.tag(ItemTags.LOGS_THAT_BURN).addTag( // Charcoal Recipes
                GenesisTags.Items.LOG_WALLS);
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
        this.tag(ItemTags.LEAVES).add(
                GenesisBlocks.BLUE_SKYROOT_LEAVES.get().asItem(),
                GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get().asItem(),
                GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get().asItem());
        this.tag(ItemTags.MUSIC_DISCS).add(
                GenesisItems.MUSIC_DISC_AERWHALE.get(),
                GenesisItems.MUSIC_DISC_APPROACHES.get(),
                GenesisItems.MUSIC_DISC_DEMISE.get(),
                GenesisItems.RECORDING_892.get());
    }
}
