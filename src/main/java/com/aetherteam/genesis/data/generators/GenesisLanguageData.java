package com.aetherteam.genesis.data.generators;

import com.aetherteam.aether.data.providers.AetherLanguageProvider;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.inventory.menu.GenesisMenuTypes;
import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.data.PackOutput;

public class GenesisLanguageData extends AetherLanguageProvider {
    public GenesisLanguageData(PackOutput output) {
        super(output, AetherGenesis.MODID);
    }

    @Override
    protected void addTranslations() {
        this.addBlock(GenesisBlocks.ENCHANTED_GRASS_BLOCK, "Enchanted Grass Block");
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

        this.addItem(GenesisItems.CORNSTARCH_BOWL, "Cornstarch Bowl");
        this.addItem(GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE, "Crystal Experience Bottle");
        this.addItem(GenesisItems.BONE_RING, "Bone Ring");
        this.addItem(GenesisItems.CANDY_RING, "Candy Ring");
        this.addItem(GenesisItems.SKYROOT_RING, "Skyroot Ring");
        this.addItem(GenesisItems.LUCKY_BELL, "Lucky Bell");
        this.addItem(GenesisItems.SWETTY_PENDANT, "Swetty Pendant");
        this.addItem(GenesisItems.DAGGERFROST_LOCKET, "Daggerfrost Locket");
        this.addItem(GenesisItems.CAPE, "Cape");
        this.addItem(GenesisItems.DEXTERITY_CAPE, "Dexterity Cape");
        this.addItem(GenesisItems.MOUSE_EAR_CAP, "Mouse Ear Cap");
        this.addItem(GenesisItems.FANGRIN_CAPSULE, "Fangrin Capsule");
        this.addItem(GenesisItems.KRAISITH_CAPSULE, "Kraisith Capsule");
        this.addItem(GenesisItems.FLEETING_STONE, "Fleeting Stone");
        this.addItem(GenesisItems.SOARING_STONE, "Soaring Stone");
        this.addItem(GenesisItems.ETHEREAL_STONE, "Ethereal Stone");
        this.addItem(GenesisItems.ORB_OF_ARKENZUS, "Orb of Arkenzus");
        this.addItem(GenesisItems.FROSTPINE_TOTEM, "Frostpine Totem");
        this.addItem(GenesisItems.FROSTBOUND_STONE, "Frostbound Stone");
        this.addItem(GenesisItems.DEATH_SEAL, "Death Seal");
        this.addItem(GenesisItems.BABY_PINK_SWET, "Baby Pink Swet");

        this.addItem(GenesisItems.PHOENIX_DART_SHOOTER, "Phoenix Dart Shooter");
        this.addItem(GenesisItems.CONTINUUM_BOMB, "Continuum Bomb");

        this.addItem(GenesisItems.CARRION_SPROUT_SPAWN_EGG, "Carrion Sprout Spawn Egg");
        this.addItem(GenesisItems.ZEPHYROO_SPAWN_EGG, "Zephyroo Spawn Egg");
        this.addItem(GenesisItems.DARK_SWET_SPAWN_EGG, "Dark Swet Spawn Egg");
        this.addItem(GenesisItems.TEMPEST_SPAWN_EGG, "Tempest Spawn Egg");
        this.addItem(GenesisItems.SKYROOT_CHEST_MIMIC_SPAWN_EGG, "Skyroot Chest Mimic Spawn Egg");
        this.addItem(GenesisItems.BATTLE_SENTRY_SPAWN_EGG, "Battle Sentry Spawn Egg");
        this.addItem(GenesisItems.SENTRY_GOLEM_SPAWN_EGG, "Sentry Golem Spawn Egg");
        this.addItem(GenesisItems.TRACKING_GOLEM_SPAWN_EGG, "Tracking Golem Spawn Egg");

        this.addItem(GenesisItems.BLUE_PARACHUTE, "Blue Parachute");
        this.addItem(GenesisItems.GREEN_PARACHUTE, "Green Parachute");
        this.addItem(GenesisItems.PURPLE_PARACHUTE, "Purple Parachute");

        this.addItem(GenesisItems.GUARDIAN_KEY, "Guardian Key");
        this.addItem(GenesisItems.HOST_KEY, "Host Key");
        this.addItem(GenesisItems.COG_KEY, "Cog Key");

        this.addItem(GenesisItems.MUSIC_DISC_AERWHALE, "Aerwhale Music Disc");
        this.addDiscDesc(GenesisItems.MUSIC_DISC_AERWHALE, "Aether UK - Aerwhale");
        this.addItem(GenesisItems.MUSIC_DISC_APPROACHES, "Moa Music Disc");
        this.addDiscDesc(GenesisItems.MUSIC_DISC_APPROACHES, "Emile van Krieken - Approaches");
        this.addItem(GenesisItems.MUSIC_DISC_DEMISE, "Labyrinth Music Disc");
        this.addDiscDesc(GenesisItems.MUSIC_DISC_DEMISE, "Moorziey - Demise");
        this.addItem(GenesisItems.RECORDING_892, "Recording #892");
        this.addDiscDesc(GenesisItems.RECORDING_892, "Emile van Krieken - ???");

        this.addGeneric("experience.desc", "Experience (%s inside)");
        this.addGeneric("death_seal.desc", "Repairing Seal: %s%");

        this.addPerItemAbilityTooltip(GenesisItems.PHOENIX_DART_SHOOTER.get(), 1,"\u00A77Shoots:\u00A7r All Dart Types");

        this.addPerItemAbilityTooltip(GenesisItems.MOUSE_EAR_CAP.get(), 1,"\u00A7eCosmetic");
        this.addPerItemAbilityTooltip(GenesisItems.MOUSE_EAR_CAP.get(), 2,"\u00A76Dyable");
        this.addPerItemAbilityTooltip(GenesisItems.CAPE.get(), 1,"\u00A7eCosmetic");
        this.addPerItemAbilityTooltip(GenesisItems.CAPE.get(), 2,"\u00A76Dyable");
        this.addPerItemAbilityTooltip(GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE.get(), 1,"\u00A79Ability:\u00A7r Stores Experience");
        this.addPerItemAbilityTooltip(GenesisItems.DAGGERFROST_LOCKET.get(), 1,"\u00A79Ability:\u00A7r Snowballs Cause Damage");
        this.addPerItemAbilityTooltip(GenesisItems.LUCKY_BELL.get(), 1,"\u00A79Ability:\u00A7r Mobs Drop Presents");
        this.addPerItemAbilityTooltip(GenesisItems.CANDY_RING.get(), 1,"\u00A79Ability:\u00A7r Removes All Hunger");
        this.addPerItemAbilityTooltip(GenesisItems.SKYROOT_RING.get(), 1,"\u00A79Ability:\u00A7r 15% Chance Double Drops");
        this.addPerItemAbilityTooltip(GenesisItems.SWETTY_PENDANT.get(), 1,"\u00A79Ability:\u00A7r Gives Wall Sliding");
        this.addPerItemAbilityTooltip(GenesisItems.DEXTERITY_CAPE.get(), 1,"\u00A79Ability:\u00A7r Gives Move Speed");
        this.addPerItemAbilityTooltip(GenesisItems.ORB_OF_ARKENZUS.get(), 1,"\u00A79Ability:\u00A7r Gives Slowfall");
        this.addPerItemAbilityTooltip(GenesisItems.BABY_PINK_SWET.get(), 1,"\u00A79Ability:\u00A7r Allows Swet Riding");
        this.addPerItemAbilityTooltip(GenesisItems.ETHEREAL_STONE.get(), 1,"\u00A79Ability:\u00A7r Gives Invisibility");
        this.addPerItemAbilityTooltip(GenesisItems.FLEETING_STONE.get(), 1,"\u00A79Ability:\u00A7r Gives High Steps");
        this.addPerItemAbilityTooltip(GenesisItems.FANGRIN_CAPSULE.get(), 1,"\u00A79Ability:\u00A7r Melee Combat");
        this.addPerItemAbilityTooltip(GenesisItems.DEATH_SEAL.get(), 1,"\u00A79Ability:\u00A7r Gives Resurrection");
        this.addPerItemAbilityTooltip(GenesisItems.KRAISITH_CAPSULE.get(), 1,"\u00A79Ability:\u00A7r Ranged Combat");
        this.addPerItemAbilityTooltip(GenesisItems.SOARING_STONE.get(), 1,"\u00A79Ability:\u00A7r Gives Move Speed");
        this.addPerItemAbilityTooltip(GenesisItems.FROSTPINE_TOTEM.get(), 1,"\u00A79Ability:\u00A7r Gives Night Vision");
        this.addPerItemAbilityTooltip(GenesisItems.FROSTBOUND_STONE.get(), 1,"\u00A79Ability:\u00A7r Generates Snowballs");

        this.addPerItemAbilityTooltip(GenesisItems.GOLDEN_SWET_BALL.get(), 1,"\u00A79Ability:\u00A7r Grows Grass");
        this.addPerItemAbilityTooltip(GenesisItems.GOLDEN_SWET_BALL.get(), 2,"\u00A73Use:\u00A7r Right-Click Block");
        this.addPerItemAbilityTooltip(GenesisItems.DARK_SWET_BALL.get(), 1,"\u00A79Ability:\u00A7r Grows Grass");
        this.addPerItemAbilityTooltip(GenesisItems.DARK_SWET_BALL.get(), 2,"\u00A73Use:\u00A7r Right-Click Block");
        this.addPerItemAbilityTooltip(GenesisItems.CONTINUUM_ORB.get(), 1,"\u00A79Ability:\u00A7r Grants Random Item");
        this.addPerItemAbilityTooltip(GenesisItems.CONTINUUM_ORB.get(), 2,"\u00A73Use:\u00A7r Right-Click");
        this.addPerItemAbilityTooltip(GenesisItems.DARK_GUMMY_SWET.get(), 1,"\u00A7aBuff:\u00A7r Fills Hunger");
        this.addPerItemAbilityTooltip(GenesisItems.RAINBOW_STRAWBERRY.get(), 1,"\u00A7aBuff:\u00A7r Increases Mining Speed & Resistance");

        this.addPerItemAbilityTooltip(AetherItems.GOLDEN_PARACHUTE.get(), 1,"\u00A79Ability:\u00A7r Fast Descent"); //todo move to overrides pack
        this.addPerItemAbilityTooltip(AetherItems.GOLDEN_PARACHUTE.get(), 2,"\u00A73Use:\u00A7r Right-Click");
        this.addPerItemAbilityTooltip(GenesisItems.PURPLE_PARACHUTE.get(), 1,"\u00A79Ability:\u00A7r Directional Descent");
        this.addPerItemAbilityTooltip(GenesisItems.PURPLE_PARACHUTE.get(), 2,"\u00A73Use:\u00A7r Right-Click");
        this.addPerItemAbilityTooltip(GenesisItems.GREEN_PARACHUTE.get(), 1,"\u00A79Ability:\u00A7r Forward Descent");
        this.addPerItemAbilityTooltip(GenesisItems.GREEN_PARACHUTE.get(), 2,"\u00A73Use:\u00A7r Right-Click");
        this.addPerItemAbilityTooltip(GenesisItems.BLUE_PARACHUTE.get(), 1,"\u00A79Ability:\u00A7r Fast Elevation");
        this.addPerItemAbilityTooltip(GenesisItems.BLUE_PARACHUTE.get(), 2,"\u00A73Use:\u00A7r Right-Click");
        this.addPerItemAbilityTooltip(GenesisItems.CONTINUUM_BOMB.get(), 1,"\u00A79Ability:\u00A7r Creates Festive Party!");
        this.addPerItemAbilityTooltip(GenesisItems.CONTINUUM_BOMB.get(), 2,"\u00A73Use:\u00A7r Right-Click");

        this.addEntityType(GenesisEntityTypes.CARRION_SPROUT, "Carrion Sprout");
        this.addEntityType(GenesisEntityTypes.ZEPHYROO, "Zephyroo");
        this.addEntityType(GenesisEntityTypes.DARK_SWET, "Dark Swet");
        this.addEntityType(GenesisEntityTypes.TEMPEST, "Tempest");
        this.addEntityType(GenesisEntityTypes.BATTLE_SENTRY, "Battle Sentry");
        this.addEntityType(GenesisEntityTypes.SENTRY_GOLEM, "Sentry Golem");
        this.addEntityType(GenesisEntityTypes.TRACKING_GOLEM, "Tracking Golem");
        this.addEntityType(GenesisEntityTypes.SKYROOT_MIMIC, "Skyroot Mimic");

        this.addEntityType(GenesisEntityTypes.FANGRIN, "Fangrin");
        this.addEntityType(GenesisEntityTypes.KRAISITH, "Kraisith");
        this.addEntityType(GenesisEntityTypes.FLEETING_WISP, "Fleeting Wisp");
        this.addEntityType(GenesisEntityTypes.SOARING_WISP, "Soaring Wisp");
        this.addEntityType(GenesisEntityTypes.ETHEREAL_WISP, "Ethereal Wisp");
        this.addEntityType(GenesisEntityTypes.SHADE_OF_ARKENZUS, "Shade of Arkenzus");
        this.addEntityType(GenesisEntityTypes.FROSTPINE_TOTEM, "Frostpine Totem");
        this.addEntityType(GenesisEntityTypes.FROSTBOUND_SPRITE, "Frostbound Sprite");
        this.addEntityType(GenesisEntityTypes.NEX_SPIRIT, "Nex Spirit");
        this.addEntityType(GenesisEntityTypes.BABY_PINK_SWET, "Baby Pink Swet");

        this.addEntityType(GenesisEntityTypes.SENTRY_GUARDIAN, "Sentry Guardian");
        this.addEntityType(GenesisEntityTypes.SLIDER_HOST_MIMIC, "Slider Host Mimic");
        this.addEntityType(GenesisEntityTypes.HOST_EYE, "Host Eye");

        this.addBlock(GenesisBlocks.CARVED_PILLAR_TOP, "Carved Pillar Top");
        this.addBlock(GenesisBlocks.CARVED_PILLAR, "Carved Pillar");
        this.addBlock(GenesisBlocks.SKYROOT_CHEST_MIMIC, "Skyroot Chest Mimic");
        this.addBlock(GenesisBlocks.DIVINE_CARVED_STONE, "Divine Carved Stone");
        this.addBlock(GenesisBlocks.DIVINE_SENTRY_STONE, "Divine Sentry Stone");
        this.addBlock(GenesisBlocks.LOCKED_DIVINE_CARVED_STONE, "Locked Divine Carved Stone");
        this.addBlock(GenesisBlocks.LOCKED_DIVINE_SENTRY_STONE, "Locked Divine Sentry Stone");
        this.addBlock(GenesisBlocks.TRAPPED_DIVINE_CARVED_STONE, "Trapped Divine Carved Stone");
        this.addBlock(GenesisBlocks.TRAPPED_DIVINE_SENTRY_STONE, "Trapped Divine Sentry Stone");
        this.addBlock(GenesisBlocks.BOSS_DOORWAY_DIVINE_CARVED_STONE, "Boss Doorway Divine Carved Stone");
        this.addBlock(GenesisBlocks.BOSS_DOORWAY_DIVINE_SENTRY_STONE, "Boss Doorway Divine Sentry Stone");
        this.addBlock(GenesisBlocks.TREASURE_DOORWAY_DIVINE_CARVED_STONE, "Treasure Doorway Divine Carved Stone");
        this.addBlock(GenesisBlocks.TREASURE_DOORWAY_DIVINE_SENTRY_STONE, "Treasure Doorway Divine Sentry Stone");
        this.addBlock(GenesisBlocks.DIVINE_CARVED_WALL, "Divine Carved Wall");
        this.addBlock(GenesisBlocks.DIVINE_CARVED_STAIRS, "Divine Carved Stairs");
        this.addBlock(GenesisBlocks.DIVINE_CARVED_SLAB, "Divine Carved Slab");

        this.addBlock(GenesisBlocks.BLOOD_MOSS_HOLYSTONE, "Blood Moss Holystone");

        this.addBlock(GenesisBlocks.COLD_FIRE, "Cold Fire");

        //todo missing subtitles
        this.addSubtitle("block", "portal.hum", "Aether Portal hums");
        this.addSubtitle("block", "portal.trigger", "Aether Portal hum intensifies");
        this.addSubtitle("entity", "carrion_sprout.hurt", "Carrion Sprout hurts");
        this.addSubtitle("entity", "carrion_sprout.death", "Carrion Sprout dies");
        this.addSubtitle("block", "aercloud.blue_aercloud_bounce", "Blue Aercloud bounces");
        this.addSubtitle("entity", "tempest.shoot", "Tempest spits");
        this.addSubtitle("entity", "tempest.ambient", "Tempest blows");
        this.addSubtitle("entity", "tempest.death", "Tempest dies");
        this.addSubtitle("entity", "tempest.hurt", "Tempest hurts");

        this.addContainerType(GenesisMenuTypes.HOLYSTONE_FURNACE, "Holystone Furnace");

        this.addGuiText("slider.message.attack.invalid_item", "Hmm. It's a rock-solid block. My %1$s wouldn't work on this.");
        this.addGuiText("slider.message.attack.invalid_fist", "Hmm. It's a rock-solid block. My fist wouldn't work on this.");

        this.addCommonConfig("gameplay", "gold_aercloud_ability", "Changes Gold Aercloud and respective parachute behavior to launch entities downwards");
        this.addCommonConfig("gameplay", "improved_slider_message", "Changes the message sent on attacking the Slider with an incorrect item to an alternate version which more subtly implies that you need a pickaxe");
        this.addClientConfig("gui", "genesis_menu_layout", "Replaces the menu toggle buttons with the Cumulus' menu switcher");
        this.addClientConfig("audio", "blue_aercloud_bounce_sfx", "Makes Blue Aerclouds have their wobbly sounds that play when bouncing on them");
        this.addClientConfig("audio", "night_music_tracks", "Adds some nice night tracks to the Aether's music selection. Also disables the default music manager for the Aether, to prevent overlap");

        this.addPackTitle("classic", "Genesis of the Void Textures");

        this.addPackDescription("mod", "The Aether: Genesis Resources");
        this.addPackDescription("classic", "The classic look from Genesis of the Void");

        this.addGuiText("host.title", "the Slider Host Mimic");
        this.addGuiText("sentry_guardian.title", "the Sentry Guardian");
        this.addGuiText("labyrinth_eye.title", "the Labyrinth Eye");

        this.addGuiText("boss.message.projectile", "Hmm... Better switch to a sword");
    }
}
