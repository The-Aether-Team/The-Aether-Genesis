package com.aetherteam.aether_genesis.data.generators;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.blockentity.GenesisMenuTypes;
import com.aetherteam.aether_genesis.data.providers.GenesisLanguageProvider;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.data.PackOutput;

public class GenesisLanguageData extends GenesisLanguageProvider {
    public GenesisLanguageData(PackOutput output) {
        super(output, Genesis.MODID);
    }

    @Override
    protected void addTranslations() {
        this.addBlock(GenesisBlocks.GREEN_AERCLOUD, "Green Aercloud");
        this.addBlock(GenesisBlocks.PURPLE_AERCLOUD, "Purple Aercloud");
        this.addBlock(GenesisBlocks.STORM_AERCLOUD, "Storm Aercloud");
        this.addBlock(GenesisBlocks.CONTINUUM_ORE, "Continuum Ore");
        this.addBlock(GenesisBlocks.ORANGE_TREE, "Orange Tree");
        this.addBlock(GenesisBlocks.BLUE_SKYROOT_LEAVES, "Blue Skyroot Leaves");
        this.addBlock(GenesisBlocks.BLUE_SKYROOT_SAPLING, "Blue Skyroot Sapling");
        this.addBlock(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES, "Dark Blue Skyroot Leaves");
        this.addBlock(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING, "Dark Blue Skyroot Sapling");
        this.addBlock(GenesisBlocks.PURPLE_CRYSTAL_LEAVES, "Purple Crystal Leaves");
        this.addBlock(GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES, "Purple Crystal Fruit Leaves");
        this.addBlock(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING, "Purple Crystal Tree Sapling");
        this.addBlock(GenesisBlocks.SKYROOT_LOG_WALL, "Skyroot Log Wall");
        this.addBlock(GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL, "Stripped Skyroot Log Wall");
        this.addBlock(GenesisBlocks.SKYROOT_WOOD_WALL, "Skyroot Wood Wall");
        this.addBlock(GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL, "Stripped Skyroot Wood Wall");

        this.addBlock(GenesisBlocks.HOLYSTONE_HEADSTONE, "Holystone Headstone");
        this.addBlock(GenesisBlocks.HOLYSTONE_KEYSTONE, "Holystone Keystone");
        this.addBlock(GenesisBlocks.HOLYSTONE_HIGHLIGHT, "Holystone Highlight");

        this.addBlock(GenesisBlocks.SKYROOT_CRAFTING_TABLE, "Skyroot Crafting Table");
        this.addBlock(GenesisBlocks.HOLYSTONE_FURNACE, "Holystone Furnace");
        this.addBlock(GenesisBlocks.SKYROOT_CHEST, "Skyroot Chest");
        this.addBlock(GenesisBlocks.SKYROOT_LADDER, "Skyroot Ladder");

        this.addItem(GenesisItems.CONTINUUM_ORB, "Continuum Orb");
        this.addItem(GenesisItems.GOLDEN_SWET_BALL, "Golden Swet Ball");
        this.addItem(GenesisItems.DARK_SWET_BALL, "Dark Swet Ball");
        this.addItem(GenesisItems.DARK_GUMMY_SWET, "Dark Gummy Swet");
        this.addItem(GenesisItems.BLUE_SWET_JELLY, "Blue Swet Jelly");
        this.addItem(GenesisItems.GOLDEN_SWET_JELLY, "Golden Swet Jelly");
        this.addItem(GenesisItems.DARK_SWET_JELLY, "Dark Swet Jelly");
        this.addItem(GenesisItems.ICESTONE_POPROCKS, "Icestone Poprocks");
        this.addItem(GenesisItems.COCOATRICE, "Cocoatrice");
        this.addItem(GenesisItems.WRAPPED_CHOCOLATES, "Wrapped Chocolates");
        this.addItem(GenesisItems.BLUEBERRY_LOLLIPOP, "Blueberry Lollipop");
        this.addItem(GenesisItems.ORANGE_LOLLIPOP, "Orange Lollipop");
        this.addItem(GenesisItems.STOMPER_POP, "Stomper Pop");
        this.addItem(GenesisItems.ORANGE, "Orange");
        this.addItem(GenesisItems.WYNDBERRY, "Wyndberry");
        this.addItem(GenesisItems.JELLY_PUMPKIN, "Jelly Pumpkin");
        this.addItem(GenesisItems.CANDY_CORN, "Candy Corn");
        this.addItem(GenesisItems.RAINBOW_STRAWBERRY, "Rainbow Strawberry");

        this.addItem(GenesisItems.SKYROOT_BOWL, "Skyroot Bowl");
        this.addItem(GenesisItems.CORNSTARCH_BOWL, "Cornstarch Bowl");
        this.addItem(GenesisItems.CRYSTAL_EXP_BOTTLE, "Crystal Exp Bottle");
        this.addItem(GenesisItems.BONE_RING, "Bone Ring");
        this.addItem(GenesisItems.CANDY_RING, "Candy Ring");
        this.addItem(GenesisItems.SKYROOT_RING, "Skyroot Ring");
        this.addItem(GenesisItems.CAPE, "Cape");
        this.addItem(GenesisItems.DEXTERITY_CAPE, "Dexterity Cape");
        this.addItem(GenesisItems.MOUSE_EAR_CAP, "Mouse Ear Cap");

        this.addItem(GenesisItems.PHOENIX_DART_SHOOTER, "Phoenix Dart Shooter");
        this.addItem(GenesisItems.CONTINUUM_BOMB, "Continuum Bomb");

        this.addItem(GenesisItems.CARRION_SPROUT_SPAWN_EGG, "Carrion Sprout Spawn Egg");
        this.addItem(GenesisItems.DARK_SWET_SPAWN_EGG, "Dark Swet Spawn Egg");
        this.addItem(GenesisItems.TEMPEST_SPAWN_EGG, "Tempest Spawn Egg");
        this.addItem(GenesisItems.BATTLE_SENTRY_SPAWN_EGG, "Battle Sentry Spawn Egg");
        this.addItem(GenesisItems.TRACKING_GOLEM_SPAWN_EGG, "Tracking Golem Spawn Egg");

        this.addItem(GenesisItems.BLUE_PARACHUTE, "Blue Parachute");
        this.addItem(GenesisItems.GREEN_PARACHUTE, "Green Parachute");
        this.addItem(GenesisItems.PURPLE_PARACHUTE, "Purple Parachute");

        this.addItem(GenesisItems.GUARDIAN_KEY, "Guardian Key");
        this.addItem(GenesisItems.HOST_KEY, "Host Key");

        this.addItem(GenesisItems.MUSIC_DISC_AERWHALE, "Aerwhale Music Disc");
        this.addDiscDesc(GenesisItems.MUSIC_DISC_AERWHALE, "Aether UK - Aerwhale");
        this.addItem(GenesisItems.MUSIC_DISC_APPROACHES, "Moa Music Disc");
        this.addDiscDesc(GenesisItems.MUSIC_DISC_APPROACHES, "Emile van Krieken - Approaches");
        this.addItem(GenesisItems.MUSIC_DISC_DEMISE, "Labyrinth Music Disc");
        this.addDiscDesc(GenesisItems.MUSIC_DISC_DEMISE, "Moorziey - Demise");
        this.addItem(GenesisItems.RECORDING_892, "Recording #892");
        this.addDiscDesc(GenesisItems.RECORDING_892, "Emile van Krieken - ???");

        this.addEntityType(GenesisEntityTypes.CARRION_SPROUT, "Carrion Sprout");
        this.addEntityType(GenesisEntityTypes.DARK_SWET, "Dark Swet");
        this.addEntityType(GenesisEntityTypes.TEMPEST, "Tempest");
        this.addEntityType(GenesisEntityTypes.BATTLE_SENTRY, "Battle Sentry");

        this.addBlock(GenesisBlocks.CARVED_PILLAR, "Carved Pillar");
        this.addBlock(GenesisBlocks.CARVED_PILLAR_SIDE, "Carved Pillar Side");
        this.addBlock(GenesisBlocks.DIVINE_CRAVED_STONE, "Divine Carved Stone");
        this.addBlock(GenesisBlocks.DIVINE_SENTRY_STONE, "Divine Sentry Stone");
        this.addBlock(GenesisBlocks.BLOOD_MOSS_HOLYSTONE, "Blood Moss Holystone");

        this.addBlock(GenesisBlocks.COLD_FIRE, "Cold Fire");

        this.addSubtitle("entity", "carrion_sprout.hurt", "Carrion Sprout hurts");
        this.addSubtitle("entity", "carrion_sprout.death", "Carrion Sprout dies");
        this.addSubtitle("block", "aercloud.blue_aercloud_bounce", "Blue Aercloud bounces");
        
        this.addContainerType(GenesisMenuTypes.HOLYSTONE_FURNACE, "Holystone Furnace");

        this.addGuiText("slider.message.attack.invalid_item", "Hmm. It's a rock-solid block. My %1$s wouldn't work on this.");
        this.addGuiText("slider.message.attack.invalid_fist", "Hmm. It's a rock-solid block. My fist wouldn't work on this.");

        this.addCommonConfig("gameplay", "gold_aercloud_ability", "Changes Gold Aercloud and respective parachute behavior to launch entities downwards");
        this.addCommonConfig("gameplay", "improved_slider_message", "Changes the message sent on attacking the Slider with an incorrect item to an alternate version which more subtly implies that you need a pickaxe");
        this.addClientConfig("audio", "blue_aercloud_bounce_sfx", "Makes Blue Aerclouds have their wobbly sounds that play when bouncing on them");
        this.addClientConfig("audio", "night_music_tracks", "Adds some nice night tracks to the Aether's music selection. Also disables the default music manager for the Aether, to prevent overlap");

        this.addPackTitle("classic", "Genesis of the Void Textures");

        this.addPackDescription("mod", "The Aether: Genesis Resources");
        this.addPackDescription("classic", "The classic look from Genesis of the Void");
        //todo: lore
    }
}
