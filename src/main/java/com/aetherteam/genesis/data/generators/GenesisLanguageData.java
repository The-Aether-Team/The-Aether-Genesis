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
        this.addItem(GenesisItems.SLIDER_HOST_MIMIC_SPAWN_EGG, "Sentry Host Mimic Spawn Egg");
        this.addItem(GenesisItems.SENTRY_GUARDIAN_SPAWN_EGG, "Sentry Guardian Spawn Egg");
        this.addItem(GenesisItems.LABYRINTH_EYE_SPAWN_EGG, "Labyrinth's Eye Spawn Egg");

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

        this.addPerItemAbilityTooltip(GenesisItems.PURPLE_PARACHUTE.get(), 1,"\u00A79Ability:\u00A7r Forward Descent");
        this.addPerItemAbilityTooltip(GenesisItems.PURPLE_PARACHUTE.get(), 2,"\u00A73Use:\u00A7r Right-Click");
        this.addPerItemAbilityTooltip(GenesisItems.GREEN_PARACHUTE.get(), 1,"\u00A79Ability:\u00A7r Directional Descent");
        this.addPerItemAbilityTooltip(GenesisItems.GREEN_PARACHUTE.get(), 2,"\u00A73Use:\u00A7r Right-Click");
        this.addPerItemAbilityTooltip(GenesisItems.BLUE_PARACHUTE.get(), 1,"\u00A79Ability:\u00A7r Fast Elevation");
        this.addPerItemAbilityTooltip(GenesisItems.BLUE_PARACHUTE.get(), 2,"\u00A73Use:\u00A7r Right-Click");
        this.addPerItemAbilityTooltip(GenesisItems.CONTINUUM_BOMB.get(), 1,"\u00A79Ability:\u00A7r Creates Festive Party!");
        this.addPerItemAbilityTooltip(GenesisItems.CONTINUUM_BOMB.get(), 2,"\u00A73Use:\u00A7r Right-Click");

        this.addPerItemAbilityTooltip(AetherItems.GOLDEN_PARACHUTE.get(), 1, "genesis", "\u00A79Ability:\u00A7r Fast Descent");

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
        this.addEntityType(GenesisEntityTypes.LABYRINTH_EYE, "Labyrinth's Eye");
        this.addEntityType(GenesisEntityTypes.HOST_EYE, "Host Eye");
        this.addEntityType(GenesisEntityTypes.FLYING_COG, "Flying Cog");

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

        this.addAdvancement("kill_zephyroo", "The Pain");
        this.addAdvancement("kill_tempest", "Cold Blooded");
        this.addAdvancement("continuum_orb", "Time Warp");
        this.addAdvancement("continuum_bomb", "Space Time Dance Party");
        this.addAdvancement("sentry_guardian", "Clobberin' Time");
        this.addAdvancement("slider_host_mimic", "Fool Me Once");
        this.addAdvancement("labyrinth_eye", "Always Watching");
        this.addAdvancement("mouse_ears", "Legally Distinct");
        this.addAdvancement("candies", "Trick or Treat");
        this.addAdvancement("companion", "Man's Best Friend");
        this.addAdvancement("nex_spirit", "Postmortem");

        this.addAdvancementDesc("kill_zephyroo", "Vanquish a great evil");
        this.addAdvancementDesc("kill_tempest", "Kill a Tempest with its own projectile");
        this.addAdvancementDesc("continuum_orb", "Use a Continuum Orb");
        this.addAdvancementDesc("continuum_bomb", "Use a Continuum Bomb");
        this.addAdvancementDesc("sentry_guardian", "Defeat the guardian boss");
        this.addAdvancementDesc("slider_host_mimic", "Defeat the mimic boss");
        this.addAdvancementDesc("labyrinth_eye", "Defeat the cog boss");
        this.addAdvancementDesc("mouse_ears", "Obtain a Mouse Ear Cap");
        this.addAdvancementDesc("candies", "Collect all the sweets and candies");
        this.addAdvancementDesc("companion", "Obtain a companion");
        this.addAdvancementDesc("nex_spirit", "Be resurrected by a Nex Spirit");

        //todo missing subtitles
        this.addSubtitle("block", "portal.hum", "Aether Portal hums");
        this.addSubtitle("block", "portal.trigger", "Aether Portal hum intensifies");
        this.addSubtitle("entity", "carrion_sprout.hurt", "Carrion Sprout hurts");
        this.addSubtitle("entity", "carrion_sprout.death", "Carrion Sprout dies");
        this.addSubtitle("entity", "tempest.shoot", "Tempest spits");
        this.addSubtitle("entity", "tempest.ambient", "Tempest blows");
        this.addSubtitle("entity", "tempest.death", "Tempest dies");
        this.addSubtitle("entity", "tempest.hurt", "Tempest hurts");

        this.addContainerType(GenesisMenuTypes.HOLYSTONE_FURNACE, "Holystone Furnace");

        this.addContainerType("host_treasure_chest", "Host Treasure Chest");
        this.addContainerType("guardian_treasure_chest", "Guardian Treasure Chest");
        this.addContainerType("cog_treasure_chest", "Cog Treasure Chest");

        this.addGuiText("slider.message.attack.invalid_item", "Hmm. It's a rock-solid block. My %1$s wouldn't work on this.");
        this.addGuiText("slider.message.attack.invalid_fist", "Hmm. It's a rock-solid block. My fist wouldn't work on this.");

        this.add("config." + this.id + ".startup.gameplay.altar_redesign", "Changes the Altar design and functionality to be like how it was in the Aether II. WARNING: Due to config limitations, this must be the same on both server and client to work properly");
        this.add("config." + this.id + ".startup.gameplay.altar_redesign.tooltip", "Changes the Altar design and functionality to be like how it was in the Aether II. WARNING: Due to config limitations, this must be the same on both server and client to work properly");

        this.addCommonConfig("gameplay", "gold_aercloud_ability", "Changes Gold Aercloud and respective parachute behavior to launch entities downwards");
        this.addCommonConfig("gameplay", "improved_slider_message", "Changes the message sent on attacking the Slider with an incorrect item to an alternate version which more subtly implies that you need a pickaxe");
        this.addClientConfig("gui", "genesis_menu_layout", "Replaces the menu toggle buttons with the Cumulus' menu switcher");
        this.addClientConfig("audio", "night_music_tracks", "Adds some nice night tracks to the Aether's music selection. Also disables the default music manager for the Aether, to prevent overlap");

        this.addPackTitle("classic", "Genesis of the Void Textures");

        this.addPackDescription("mod", "The Aether: Genesis Resources");
        this.addPackDescription("classic", "The classic look from Genesis of the Void");

        this.addGuiText("host.title", "the Slider Host Mimic");
        this.addGuiText("sentry_guardian.title", "the Sentry Guardian");
        this.addGuiText("labyrinth_eye.title", "the Labyrinth's Eye");

        this.addGuiText("boss.message.projectile", "Hmm... Ranged Weapons will not work here");

        this.addLore(GenesisItems.GOLDEN_SWET_BALL, "A gooey orb that is dropped from Golden Swets. It can be used to fertilize soil. Another use is to put it alongside string to make a lead.");
        this.addLore(GenesisItems.DARK_SWET_BALL, "A gooey orb that is dropped from Dark Swets. It can be used to fertilize soil. Another use is to put it alongside string to make a lead.");
        this.addLore(GenesisItems.CORNSTARCH_BOWL, "A large bowl of ground corn flour found among chests in Bronze and Silver Dungeons. It is a vital ingredient in making Candy Corn.");
        this.addLore(GenesisItems.CONTINUUM_ORB, "");
        this.addLore(GenesisItems.BLUE_SWET_JELLY, "A morsel of fluted gelatin made by sweetening Blue Swet Gel with Sugar.");
        this.addLore(GenesisItems.GOLDEN_SWET_JELLY, "A morsel of fluted gelatin made by sweetening Golden Swet Gel with Sugar.");
        this.addLore(GenesisItems.DARK_SWET_JELLY, "A morsel of fluted gelatin made by sweetening Dark Swet Gel with Sugar.");
        this.addLore(GenesisItems.DARK_GUMMY_SWET, "A gummy with a minty aftertaste, it can be found in random chests in Bronze and Silver Dungeons. It fully restores the player's hunger when eaten. Very useful for boss fights.");
        this.addLore(GenesisItems.ICESTONE_POPROCKS, "Powdery Icestone rock candies that pop in your mouth!");
        this.addLore(GenesisItems.COCOATRICE, "A large and solid chocolate, molded into a shape resembling a Cockatrice.");
        this.addLore(GenesisItems.WRAPPED_CHOCOLATES, "An assortment of delicious chocolates wrapped in bits of Aechor Petal.");
        this.addLore(GenesisItems.BLUEBERRY_LOLLIPOP, "A hard, round blue candy on a stick.");
        this.addLore(GenesisItems.ORANGE_LOLLIPOP, "A hard, round orange candy on a stick.");
        this.addLore(GenesisItems.STOMPER_POP, "A gooey, round candy made from a Baby Pink Swet. It will fill you up, but was it worth it?");
        this.addLore(GenesisItems.ORANGE, "A tangy Orange, grown from naturally occurring dwarf Orange Trees. These fill more hunger than Blue Berries, but aren't as common.");
        this.addLore(GenesisItems.WYNDBERRY, "A blue strawberry-like fruit, dropped from Carrion Sprouts when killed. Wyndberries are one of the most filling natural fruits in the Aether, and can be enchanted in an Altar to grant additional effects.");
        this.addLore(GenesisItems.JELLY_PUMPKIN, "A delicious Orange-flavored gelatin. Does not contain real Pumpkin!");
        this.addLore(GenesisItems.CANDY_CORN, "A piece of pointy, horn-shaped candy made from sugar and cornstarch.");
        this.addLore(GenesisItems.RAINBOW_STRAWBERRY, "A powerful enchanted fruit, good for hunger and saturation as well as a couple seconds of damage resistance and mining speed.");
        this.addLore(GenesisItems.BONE_RING, "");
        this.addLore(GenesisItems.CANDY_RING, "");
        this.addLore(GenesisItems.SKYROOT_RING, "A wooden ring that gives the rare chance of doubling drops.");
        this.addLore(GenesisItems.LUCKY_BELL, "");
        this.addLore(GenesisItems.SWETTY_PENDANT, "");
        this.addLore(GenesisItems.DAGGERFROST_LOCKET, "");
        this.addLore(GenesisItems.CAPE, "A Cape that is crafted using soft White Wool. It is able to be dyed any color!");
        this.addLore(GenesisItems.DEXTERITY_CAPE, "A slightly rare Cape found in Bronze Dungeons. It makes the wearer's legs stronger, allowing them to run at a faster speed.");
        this.addLore(GenesisItems.MOUSE_EAR_CAP, "A wearable cap that makes it look like you have big mouse ears! It can be dyed any color.");
        this.addLore(GenesisItems.FANGRIN_CAPSULE, "");
        this.addLore(GenesisItems.KRAISITH_CAPSULE, "");
        this.addLore(GenesisItems.FLEETING_STONE, "");
        this.addLore(GenesisItems.SOARING_STONE, "");
        this.addLore(GenesisItems.ETHEREAL_STONE, "");
        this.addLore(GenesisItems.ORB_OF_ARKENZUS, "");
        this.addLore(GenesisItems.FROSTPINE_TOTEM, "");
        this.addLore(GenesisItems.FROSTBOUND_STONE, "");
        this.addLore(GenesisItems.DEATH_SEAL, "");
        this.addLore(GenesisItems.BABY_PINK_SWET, "");
        this.addLore(GenesisItems.PHOENIX_DART_SHOOTER, "A Dart Shooter that can shoot any type of dart, and sets them on fire!");
        this.addLore(GenesisItems.CONTINUUM_BOMB, "");
        this.addLore(GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE, "");
        this.addLore(GenesisItems.MUSIC_DISC_AERWHALE, "A music disc that plays \"Aerwhale\" by Aether UK.");
        this.addLore(GenesisItems.MUSIC_DISC_APPROACHES, "A music disc that plays \"Approaches\" by Emile van Krieken.");
        this.addLore(GenesisItems.MUSIC_DISC_DEMISE, "A music disc that plays \"Demise\" by Moorziey.");
        this.addLore(GenesisItems.RECORDING_892, "A music disc that plays an eerie recording.");
        this.addLore(GenesisItems.BLUE_PARACHUTE, "A parachute that can boost you high into the air. It is crafted with Blue Aerclouds and can only be used once.");
        this.addLore(GenesisItems.GREEN_PARACHUTE, "A parachute that allows you more freedom of movement horizontally during descent. It is crafted with Green Aerclouds and can only be used once.");
        this.addLore(GenesisItems.PURPLE_PARACHUTE, "A parachute that propels you forward at a high speed. It is crafted with Purple Aerclouds and can only be used once.");
        this.addLore(GenesisItems.GUARDIAN_KEY, "A blueish, pewter key that is dropped from the Sentry Guardian after being defeated. You can use it to claim the treasure you earned!");
        this.addLore(GenesisItems.HOST_KEY, "A crimson-colored key that is dropped from the Slider Host Mimic after being defeated. You can use it to claim the treasure you earned!");
        this.addLore(GenesisItems.COG_KEY, "A dark key with a matte look. It is dropped from the Labyrinth Eye after being defeated. You can use it to claim the treasure you earned!");
        this.addLore(GenesisBlocks.ENCHANTED_GRASS_BLOCK, "A Grass Block that has been enchanted to allow for increasing harvest rates of Blue Berries.");
        this.addLore(GenesisBlocks.GREEN_AERCLOUD, "A pastel green cloud found across the skies of the Aether. These can bounce you horizontally in any random direction, so be careful to not fly into them with your Moa!");
        this.addLore(GenesisBlocks.PURPLE_AERCLOUD, "A light, purple cloud found at low elevations, emitting directional particles of air. Landing in a Purple Aercloud will launch you in the same direction as the particles, which can be used for momentum during flight.");
        this.addLore(GenesisBlocks.STORM_AERCLOUD, "A dark, dim cloud found near the bottom of the Aether. Some say this is where Tempests originate from at nightfall.");
        this.addLore(GenesisBlocks.CONTINUUM_ORE, "A rare ore found in the Aether. It will drop a Continuum Orb when mined with a Gravitite Pickaxe.");
        this.addLore(GenesisBlocks.BLUE_SKYROOT_LEAVES, "These leaves generate with Blue Skyroot Trees. They can drop Blue Skyroot Saplings and Skyroot Sticks when decaying.");
        this.addLore(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES, "These leaves generate with Dark Blue Skyroot Trees. They can drop Dark Blue Skyroot Saplings and Skyroot Sticks when decaying.");
        this.addLore(GenesisBlocks.PURPLE_CRYSTAL_LEAVES, "Purple Crystal Leaves that are home to White Apples.");
        this.addLore(GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES, "Leaves that come from Purple Crystal Trees, found amongst wooded areas. Sometimes they have fruit on them that can cure Inebriation.");
        this.addLore(GenesisBlocks.ORANGE_TREE, "A dwarf Orange Tree, found naturally across the islands of the Aether. It only grows to be as tall as a player!");
        this.addLore(GenesisBlocks.HOLYSTONE_HEADSTONE, "");
        this.addLore(GenesisBlocks.HOLYSTONE_KEYSTONE, "");
        this.addLore(GenesisBlocks.HOLYSTONE_HIGHLIGHT, "");
        this.addLore(GenesisBlocks.BLUE_SKYROOT_SAPLING, "These small blue saplings will grow into Skyroot Trees. They can be grown faster with Bone Meal.");
        this.addLore(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING, "These dark blue saplings will grow into a towering type of Skyroot Tree. They can be grown faster with Bone Meal.");
        this.addLore(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING, "These spindly saplings will grow into tall Crystal Trees with purple leaves. They can be grown faster with Bone Meal.");
        this.addLore(GenesisBlocks.SKYROOT_LOG_WALL, "Crafted from Skyroot Logs. Can be used for decorative enclosures and defenses. Great for keeping nasty intruders away!");
        this.addLore(GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL, "Crafted from Stripped Skyroot Logs. Can be used for decorative enclosures and defenses. Great for keeping nasty intruders away!");
        this.addLore(GenesisBlocks.SKYROOT_WOOD_WALL, "Crafted from Skyroot Wood. Can be used for decorative enclosures and defenses. Great for keeping nasty intruders away!");
        this.addLore(GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL, "Crafted from Stripped Skyroot Wood. Can be used for decorative enclosures and defenses. Great for keeping nasty intruders away!");
        this.addLore(GenesisBlocks.SKYROOT_CRAFTING_TABLE, "A workbench made of Skyroot, capable of crafting anything you might need.");
        this.addLore(GenesisBlocks.HOLYSTONE_FURNACE, "A furnace constructed from Holystone. This can be used to cook both ores and food while surviving in the Aether.");
        this.addLore(GenesisBlocks.SKYROOT_CHEST, "A Skyroot container that can be used to store all the goods you collect on your adventures.");
        this.addLore(GenesisBlocks.SKYROOT_LADDER, "A Skyroot ladder. Handy for vertical travel.");
        this.addLore(GenesisBlocks.CARVED_PILLAR, "");
        this.addLore(GenesisBlocks.CARVED_PILLAR_TOP, "");
        this.addLore(GenesisBlocks.SKYROOT_CHEST_MIMIC, "It may look like a normal Skyroot Chest, but it really isn't. As soon as you right-click on it, a Chest Mimic will pop out! These appear in Bronze and Silver Dungeons.");
        this.addLore(GenesisBlocks.DIVINE_CARVED_STONE, "Carved Stone that has blue grooves cut between the bricks. A fancy block to decorate with alongside its regular counterpart!");
        this.addLore(GenesisBlocks.DIVINE_SENTRY_STONE, "Sentry Stone that has blue grooves cut between the bricks. A fancy block to decorate with alongside its regular counterpart!");
        this.addLore(GenesisBlocks.DIVINE_CARVED_WALL, "Crafted from Mossy Divine Carved Stone. Can be used for decorative enclosures and defenses. Great for keeping nasty intruders away!");
        this.addLore(GenesisBlocks.DIVINE_CARVED_STAIRS, "Crafted from Divine Carved Stone. Stairs are useful for adding verticality to builds and are often used for decoration too!");
        this.addLore(GenesisBlocks.DIVINE_CARVED_SLAB, "Crafted from Divine Carved Stone. Slabs are half blocks, versatile for decoration and smooth slopes. Try adding some to a building's roofing!");
        this.addLore(GenesisBlocks.BLOOD_MOSS_HOLYSTONE, "An unbreakable stone found at the edges of labyrinths, covered in an off putting red moss.");
    }
}
