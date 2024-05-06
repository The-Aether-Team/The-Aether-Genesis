package com.aetherteam.genesis.data.generators;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.data.providers.GenesisItemModelProvider;
import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class GenesisItemModelData extends GenesisItemModelProvider {
    public GenesisItemModelData(PackOutput output, ExistingFileHelper helper) {
        super(output, AetherGenesis.MODID, helper);
    }

    @Override
    protected void registerModels() {
        this.item(GenesisItems.GOLDEN_SWET_BALL.get(), "materials/");
        this.item(GenesisItems.DARK_SWET_BALL.get(), "materials/");
        this.item(GenesisItems.CORNSTARCH_BOWL.get(), "materials/");
        this.item(GenesisItems.CONTINUUM_ORB.get(), "materials/");

        this.item(GenesisItems.DARK_GUMMY_SWET.get(), "food/");
        this.item(GenesisItems.BLUE_SWET_JELLY.get(), "food/");
        this.item(GenesisItems.GOLDEN_SWET_JELLY.get(), "food/");
        this.item(GenesisItems.DARK_SWET_JELLY.get(), "food/");

        this.item(GenesisItems.BLUE_PARACHUTE.get(), "miscellaneous/");
        this.item(GenesisItems.GREEN_PARACHUTE.get(), "miscellaneous/");
        this.item(GenesisItems.PURPLE_PARACHUTE.get(), "miscellaneous/");

        this.rotatedItem(GenesisItems.GUARDIAN_KEY.get(), "miscellaneous/");
        this.rotatedItem(GenesisItems.HOST_KEY.get(), "miscellaneous/");
        this.rotatedItem(GenesisItems.COG_KEY.get(), "miscellaneous/");

        this.item(GenesisItems.ICESTONE_POPROCKS.get(), "food/");
        this.item(GenesisItems.COCOATRICE.get(), "food/");
        this.item(GenesisItems.WRAPPED_CHOCOLATES.get(), "food/");
        this.item(GenesisItems.BLUEBERRY_LOLLIPOP.get(), "food/");
        this.item(GenesisItems.ORANGE_LOLLIPOP.get(), "food/");
        this.item(GenesisItems.STOMPER_POP.get(), "food/");
        this.item(GenesisItems.ORANGE .get(), "food/");
        this.item(GenesisItems.WYNDBERRY.get(), "food/");
        this.item(GenesisItems.JELLY_PUMPKIN.get(), "food/");
        this.item(GenesisItems.CANDY_CORN.get(), "food/");
        this.item(GenesisItems.RAINBOW_STRAWBERRY.get(), "food/");

        this.item(GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE.get(), "accessories/");
        this.item(GenesisItems.BONE_RING.get(), "accessories/");
        this.item(GenesisItems.CANDY_RING.get(), "accessories/");
        this.item(GenesisItems.SKYROOT_RING.get(), "accessories/");
        this.item(GenesisItems.LUCKY_BELL.get(), "accessories/");
        this.item(GenesisItems.SWETTY_PENDANT.get(), "accessories/");
        this.item(GenesisItems.DAGGERFROST_LOCKET.get(), "accessories/");
        this.dyeableCape(GenesisItems.CAPE.get());
        this.item(GenesisItems.DEXTERITY_CAPE.get(), "accessories/");
        this.item(GenesisItems.MOUSE_EAR_CAP.get(), "accessories/");
        this.item(GenesisItems.FANGRIN_CAPSULE.get(), "accessories/");
        this.item(GenesisItems.KRAISITH_CAPSULE.get(), "accessories/");
        this.item(GenesisItems.FLEETING_STONE.get(), "accessories/");
        this.item(GenesisItems.SOARING_STONE.get(), "accessories/");
        this.item(GenesisItems.ETHEREAL_STONE.get(), "accessories/");
        this.item(GenesisItems.ORB_OF_ARKENZUS.get(), "accessories/");
        this.item(GenesisItems.FROSTPINE_TOTEM.get(), "accessories/");
        this.item(GenesisItems.FROSTBOUND_STONE.get(), "accessories/");
        this.item(GenesisItems.DEATH_SEAL.get(), "accessories/");
        this.item(GenesisItems.BABY_PINK_SWET.get(), "accessories/");

        this.item(GenesisItems.MUSIC_DISC_AERWHALE.get(), "miscellaneous/");
        this.item(GenesisItems.MUSIC_DISC_APPROACHES.get(), "miscellaneous/");
        this.item(GenesisItems.MUSIC_DISC_DEMISE.get(), "miscellaneous/");
        this.item(GenesisItems.RECORDING_892.get(), "miscellaneous/");

        this.dartShooterItem(GenesisItems.PHOENIX_DART_SHOOTER.get(), "combat/");
        this.item(GenesisItems.CONTINUUM_BOMB.get(), "miscellaneous/");

        this.eggItem(GenesisItems.CARRION_SPROUT_SPAWN_EGG.get());
        this.eggItem(GenesisItems.DARK_SWET_SPAWN_EGG.get());
        this.eggItem(GenesisItems.TEMPEST_SPAWN_EGG.get());
        this.eggItem(GenesisItems.SKYROOT_CHEST_MIMIC_SPAWN_EGG.get());
        this.eggItem(GenesisItems.BATTLE_SENTRY_SPAWN_EGG.get());
        this.eggItem(GenesisItems.TRACKING_GOLEM_SPAWN_EGG.get());

        this.itemBlock(GenesisBlocks.ENCHANTED_GRASS_BLOCK.get());
        this.itemBlock(GenesisBlocks.GREEN_AERCLOUD.get());
        this.itemBlock(GenesisBlocks.PURPLE_AERCLOUD.get());
        this.itemBlock(GenesisBlocks.STORM_AERCLOUD.get());
        this.itemBlock(GenesisBlocks.CONTINUUM_ORE.get());
        this.itemBlock(GenesisBlocks.BLUE_SKYROOT_LEAVES.get());
        this.itemBlock(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get());
        this.itemBlock(GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get());
        this.itemBlock(GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get());
        this.orangeTree(GenesisBlocks.ORANGE_TREE.get());
        this.itemBlockFlat(GenesisBlocks.BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.itemBlockFlat(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get(), "natural/");
        this.itemBlockFlat(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get(), "natural/");

        this.itemBlock(GenesisBlocks.HOLYSTONE_HEADSTONE.get());
        this.itemBlock(GenesisBlocks.HOLYSTONE_KEYSTONE.get());
        this.itemBlock(GenesisBlocks.HOLYSTONE_HIGHLIGHT.get());

        this.itemLogWallBlock(GenesisBlocks.SKYROOT_LOG_WALL.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/", Aether.MODID);
        this.itemLogWallBlock(GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(), AetherBlocks.STRIPPED_SKYROOT_LOG.get(), "natural/", Aether.MODID);
        this.itemWoodWallBlock(GenesisBlocks.SKYROOT_WOOD_WALL.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/", Aether.MODID);
        this.itemWoodWallBlock(GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get(), AetherBlocks.STRIPPED_SKYROOT_LOG.get(), "natural/", Aether.MODID);

        this.itemBlock(GenesisBlocks.SKYROOT_CRAFTING_TABLE.get());
        this.itemBlock(GenesisBlocks.HOLYSTONE_FURNACE.get());
        this.lookalikeBlock(GenesisBlocks.SKYROOT_CHEST.get(), this.mcLoc("item/chest"));
        this.itemBlockFlat(GenesisBlocks.SKYROOT_LADDER.get(), "construction/");

        this.itemBlock(GenesisBlocks.CARVED_PILLAR_TOP.get());
        this.itemBlock(GenesisBlocks.CARVED_PILLAR.get());
        this.lookalikeBlock(GenesisBlocks.SKYROOT_CHEST_MIMIC.get(), this.mcLoc("item/chest"));
        this.itemBlock(GenesisBlocks.DIVINE_SENTRY_STONE.get());
        this.itemBlock(GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.itemLockedDungeonBlock(GenesisBlocks.LOCKED_DIVINE_CARVED_STONE.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.itemLockedDungeonBlock(GenesisBlocks.LOCKED_DIVINE_SENTRY_STONE.get(), GenesisBlocks.DIVINE_SENTRY_STONE.get());
        this.itemTrappedDungeonBlock(GenesisBlocks.TRAPPED_DIVINE_CARVED_STONE.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.itemTrappedDungeonBlock(GenesisBlocks.TRAPPED_DIVINE_SENTRY_STONE.get(), GenesisBlocks.DIVINE_SENTRY_STONE.get());
        this.itemBossDoorwayDungeonBlock(GenesisBlocks.BOSS_DOORWAY_DIVINE_CARVED_STONE.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.itemBossDoorwayDungeonBlock(GenesisBlocks.BOSS_DOORWAY_DIVINE_SENTRY_STONE.get(), GenesisBlocks.DIVINE_SENTRY_STONE.get());
        this.itemTreasureDoorwayDungeonBlock(GenesisBlocks.TREASURE_DOORWAY_DIVINE_CARVED_STONE.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.itemTreasureDoorwayDungeonBlock(GenesisBlocks.TREASURE_DOORWAY_DIVINE_SENTRY_STONE.get(), GenesisBlocks.DIVINE_SENTRY_STONE.get());

        this.itemWallBlock(GenesisBlocks.DIVINE_CARVED_WALL.get(), GenesisBlocks.DIVINE_CARVED_STONE.get(), "dungeon/");
        this.itemBlock(GenesisBlocks.DIVINE_CARVED_STAIRS.get());
        this.itemBlock(GenesisBlocks.DIVINE_CARVED_SLAB.get());

        this.itemBlock(GenesisBlocks.BLOOD_MOSS_HOLYSTONE.get());
    }
}
