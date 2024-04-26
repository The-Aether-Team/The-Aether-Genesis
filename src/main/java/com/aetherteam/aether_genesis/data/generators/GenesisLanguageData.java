package com.aetherteam.aether_genesis.data.generators;

import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.data.providers.GenesisLanguageProvider;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.inventory.menu.GenesisMenuTypes;
import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.data.PackOutput;

public class GenesisLanguageData extends GenesisLanguageProvider {
    public GenesisLanguageData(PackOutput output) {
        super(output, Genesis.MODID);
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
        this.addItem(GenesisItems.DARK_SWET_SPAWN_EGG, "Dark Swet Spawn Egg");
        this.addItem(GenesisItems.TEMPEST_SPAWN_EGG, "Tempest Spawn Egg");
        this.addItem(GenesisItems.SKYROOT_CHEST_MIMIC_SPAWN_EGG, "Skyroot Chest Mimic Spawn Egg");
        this.addItem(GenesisItems.BATTLE_SENTRY_SPAWN_EGG, "Battle Sentry Spawn Egg");
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

        this.addAbilityTooltip(AetherItems.SKYROOT_PICKAXE.get(), "\u00A79Ability:\u00A7r Double Drops");
        this.addAbilityTooltip(AetherItems.SKYROOT_AXE.get(), "\u00A79Ability:\u00A7r Double Drops");
        this.addAbilityTooltip(AetherItems.SKYROOT_SHOVEL.get(), "\u00A79Ability:\u00A7r Double Drops");
        this.addAbilityTooltip(AetherItems.SKYROOT_HOE.get(), "\u00A79Ability:\u00A7r Double Drops");
        this.addAbilityTooltip(AetherItems.SKYROOT_SWORD.get(), "\u00A79Ability:\u00A7r Double Drops");
        this.addAbilityTooltip(AetherItems.HOLYSTONE_PICKAXE.get(), "\u00A79Ability:\u00A7r Drops Ambrosium");
        this.addAbilityTooltip(AetherItems.HOLYSTONE_AXE.get(), "\u00A79Ability:\u00A7r Drops Ambrosium");
        this.addAbilityTooltip(AetherItems.HOLYSTONE_SHOVEL.get(), "\u00A79Ability:\u00A7r Drops Ambrosium");
        this.addAbilityTooltip(AetherItems.HOLYSTONE_HOE.get(), "\u00A79Ability:\u00A7r Drops Ambrosium");
        this.addAbilityTooltip(AetherItems.HOLYSTONE_SWORD.get(), "\u00A79Ability:\u00A7r Drops Ambrosium");
        this.addAbilityTooltip(AetherItems.ZANITE_PICKAXE.get(), "\u00A79Ability:\u00A7r Grows Stronger");
        this.addAbilityTooltip(AetherItems.ZANITE_AXE.get(), "\u00A79Ability:\u00A7r Grows Stronger");
        this.addAbilityTooltip(AetherItems.ZANITE_SHOVEL.get(), "\u00A79Ability:\u00A7r Grows Stronger");
        this.addAbilityTooltip(AetherItems.ZANITE_HOE.get(), "\u00A79Ability:\u00A7r Grows Stronger");
        this.addAbilityTooltip(AetherItems.ZANITE_SWORD.get(), "\u00A79Ability:\u00A7r Grows Stronger");
        this.addAbilityTooltip(AetherItems.GRAVITITE_PICKAXE.get(), "\u00A79Ability:\u00A7r Shift Gravity\r\n\u00A73Use:\u00A7r Right-Click Block");
        this.addAbilityTooltip(AetherItems.GRAVITITE_AXE.get(), "\u00A79Ability:\u00A7r Shift Gravity\r\n\u00A73Use:\u00A7r Right-Click Block");
        this.addAbilityTooltip(AetherItems.GRAVITITE_SHOVEL.get(), "\u00A79Ability:\u00A7r Shift Gravity\r\n\u00A73Use:\u00A7r Right-Click Block");
        this.addAbilityTooltip(AetherItems.GRAVITITE_HOE.get(), "\u00A79Ability:\u00A7r Shift Gravity\r\n\u00A73Use:\u00A7r Right-Click Block");
        this.addAbilityTooltip(AetherItems.GRAVITITE_SWORD.get(), "\u00A79Ability:\u00A7r Shift Gravity");
        this.addAbilityTooltip(AetherItems.VALKYRIE_PICKAXE.get(), "\u00A79Ability:\u00A7r Extended Reach");
        this.addAbilityTooltip(AetherItems.VALKYRIE_AXE.get(), "\u00A79Ability:\u00A7r Extended Reach");
        this.addAbilityTooltip(AetherItems.VALKYRIE_SHOVEL.get(), "\u00A79Ability:\u00A7r Extended Reach");
        this.addAbilityTooltip(AetherItems.VALKYRIE_HOE.get(), "\u00A79Ability:\u00A7r Extended Reach");
        this.addAbilityTooltip(AetherItems.VALKYRIE_LANCE.get(), "\u00A79Ability:\u00A7r Extended Reach");
        this.addAbilityTooltip(AetherItems.POISON_DART.get(), "\u00A79Ability:\u00A7r Inflict Inebriation");
        this.addAbilityTooltip(AetherItems.ENCHANTED_DART.get(), "\u00A79Ability:\u00A7r Extra Damage");
        this.addAbilityTooltip(AetherItems.GOLDEN_DART_SHOOTER.get(), "\u00A77Shoots:\u00A7r Golden Darts");
        this.addAbilityTooltip(AetherItems.POISON_DART_SHOOTER.get(), "\u00A77Shoots:\u00A7r Poison Darts");
        this.addAbilityTooltip(AetherItems.ENCHANTED_DART_SHOOTER.get(), "\u00A77Shoots:\u00A7r Enchanted Darts");
        this.addAbilityTooltip(GenesisItems.PHOENIX_DART_SHOOTER.get(), "\u00A77Shoots:\u00A7r All Dart Types");
        this.addAbilityTooltip(AetherItems.PIG_SLAYER.get(), "\u00A79Ability:\u00A7r Slaughter Pigs");
        this.addAbilityTooltip(AetherItems.VAMPIRE_BLADE.get(), "\u00A79Ability:\u00A7r Lifesteal");
        this.addAbilityTooltip(AetherItems.FLAMING_SWORD.get(), "\u00A79Ability:\u00A7r Create Flames");
        this.addAbilityTooltip(AetherItems.HOLY_SWORD.get(), "\u00A79Ability:\u00A7r Obliterate Undead");
        this.addAbilityTooltip(AetherItems.LIGHTNING_SWORD.get(), "\u00A79Ability:\u00A7r Spawn Lightning");
        this.addAbilityTooltip(AetherItems.LIGHTNING_KNIFE.get(), "\u00A79Ability:\u00A7r Spawn Lightning\r\n\u00A73Use:\u00A7r Right-Click");
        this.addAbilityTooltip(AetherItems.HAMMER_OF_KINGBDOGZ.get(), "\u00A79Ability:\u00A7r Area of Effect Wave\r\n\u00A73Use:\u00A7r Right-Click");
        this.addAbilityTooltip(AetherItems.CANDY_CANE_SWORD.get(), "\u00A79Ability:\u00A7r Drops Candy Canes");

        this.addAbilityTooltip(AetherItems.GRAVITITE_HELMET.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Super Jump\r\n\u00A73Use:\u00A7r Shift + Space Bar\r\n"); //todo line breaks show as annoying code blocks
        this.addAbilityTooltip(AetherItems.GRAVITITE_CHESTPLATE.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Super Jump\r\n\u00A73Use:\u00A7r Shift + Space Bar\r\n");
        this.addAbilityTooltip(AetherItems.GRAVITITE_LEGGINGS.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Super Jump\r\n\u00A73Use:\u00A7r Shift + Space Bar\r\n");
        this.addAbilityTooltip(AetherItems.GRAVITITE_BOOTS.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Super Jump\r\n\u00A73Use:\u00A7r Shift + Space Bar\r\n");
        this.addAbilityTooltip(AetherItems.GRAVITITE_GLOVES.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Super Jump\r\n\u00A73Use:\u00A7r Shift + Space Bar\r\n");
        this.addAbilityTooltip(AetherItems.PHOENIX_HELMET.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Fire Resistant\r\n");
        this.addAbilityTooltip(AetherItems.PHOENIX_CHESTPLATE.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Fire Resistant\r\n");
        this.addAbilityTooltip(AetherItems.PHOENIX_LEGGINGS.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Fire Resistant\r\n");
        this.addAbilityTooltip(AetherItems.PHOENIX_BOOTS.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Fire Resistant\r\n");
        this.addAbilityTooltip(AetherItems.PHOENIX_GLOVES.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Fire Resistant\r\n");
        this.addAbilityTooltip(AetherItems.NEPTUNE_HELMET.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Walk in Water\r\n");
        this.addAbilityTooltip(AetherItems.NEPTUNE_CHESTPLATE.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Walk in Water\r\n");
        this.addAbilityTooltip(AetherItems.NEPTUNE_LEGGINGS.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Walk in Water\r\n");
        this.addAbilityTooltip(AetherItems.NEPTUNE_BOOTS.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Walk in Water\r\n");
        this.addAbilityTooltip(AetherItems.NEPTUNE_GLOVES.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Walk in Water\r\n");
        this.addAbilityTooltip(AetherItems.VALKYRIE_HELMET.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Grows Wings\r\n");
        this.addAbilityTooltip(AetherItems.VALKYRIE_CHESTPLATE.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Grows Wings\r\n");
        this.addAbilityTooltip(AetherItems.VALKYRIE_LEGGINGS.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Grows Wings\r\n");
        this.addAbilityTooltip(AetherItems.VALKYRIE_BOOTS.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Grows Wings\r\n");
        this.addAbilityTooltip(AetherItems.VALKYRIE_GLOVES.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Grows Wings\r\n");
        this.addAbilityTooltip(AetherItems.SENTRY_BOOTS.get(), "\u00A77When Wearing Full Set:\u00A7r\r\n\u00A79Ability:\u00A7r Feather Falling\r\n");

        this.addAbilityTooltip(AetherItems.IRON_BUBBLE.get(), "\u00A79Ability:\u00A7r Water Breathing\r\n");
        this.addAbilityTooltip(AetherItems.IRON_RING.get(), "\u00A7eCosmetic\r\n");
        this.addAbilityTooltip(AetherItems.GOLDEN_RING.get(), "\u00A7eCosmetic\r\n");
        this.addAbilityTooltip(AetherItems.ZANITE_RING.get(), "\u00A79Ability:\u00A7r Increases Mining Speed\r\n");
        this.addAbilityTooltip(AetherItems.IRON_PENDANT.get(), "\u00A7eCosmetic\r\n");
        this.addAbilityTooltip(AetherItems.GOLDEN_PENDANT.get(), "\u00A7eCosmetic\r\n");
        this.addAbilityTooltip(AetherItems.ZANITE_PENDANT.get(), "\u00A79Ability:\u00A7r Increases Mining Speed\r\n");
        this.addAbilityTooltip(AetherItems.REGENERATION_STONE.get(), "\u00A79Ability:\u00A7r Regenerates Health\r\n");
        this.addAbilityTooltip(AetherItems.ICE_RING.get(), "\u00A79Ability:\u00A7r Solidifies Lava and Water\r\n");
        this.addAbilityTooltip(AetherItems.ICE_PENDANT.get(), "\u00A79Ability:\u00A7r Solidifies Lava and Water\r\n");
        this.addAbilityTooltip(GenesisItems.MOUSE_EAR_CAP.get(), "\u00A7eCosmetic\r\n\u00A76Dyable\r\n");
        this.addAbilityTooltip(GenesisItems.CAPE.get(), "\u00A7eCosmetic\r\n\u00A76Dyable\r\n");
        this.addAbilityTooltip(GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE.get(), "\u00A79Ability:\u00A7r Stores Experience\r\n");
        this.addAbilityTooltip(GenesisItems.DAGGERFROST_LOCKET.get(), "\u00A79Ability:\u00A7r Snowballs Cause Damage\r\n");
        this.addAbilityTooltip(GenesisItems.LUCKY_BELL.get(), "\u00A79Ability:\u00A7r Mobs Drop Presents\r\n");
        this.addAbilityTooltip(GenesisItems.CANDY_RING.get(), "\u00A79Ability:\u00A7r Removes All Hunger\r\n");
        this.addAbilityTooltip(GenesisItems.SKYROOT_RING.get(), "\u00A79Ability:\u00A7r 15% Chance Double Drops\r\n");
        this.addAbilityTooltip(GenesisItems.SWETTY_PENDANT.get(), "\u00A79Ability:\u00A7rGives Wall Sliding\r\n");
        this.addAbilityTooltip(AetherItems.GOLDEN_FEATHER.get(), "\u00A79Ability:\u00A7r Gives Slowfall\r\n");
        this.addAbilityTooltip(AetherItems.VALKYRIE_CAPE.get(), "\u00A79Ability:\u00A7r Gives Slowfall\r\n");
        this.addAbilityTooltip(AetherItems.SWET_CAPE.get(), "\u00A79Ability:\u00A7r Allows Swet Riding\r\n");
        this.addAbilityTooltip(AetherItems.INVISIBILITY_CLOAK.get(), "\u00A79Ability:\u00A7r Gives Invisibility\r\n");
        this.addAbilityTooltip(AetherItems.AGILITY_CAPE.get(), "\u00A79Ability:\u00A7r Gives High Steps\r\n");
        this.addAbilityTooltip(GenesisItems.DEXTERITY_CAPE.get(), "\u00A79Ability:\u00A7r Gives Move Speed\r\n");
        this.addAbilityTooltip(GenesisItems.ORB_OF_ARKENZUS.get(), "\u00A79Ability:\u00A7r Gives Slowfall\r\n");
        this.addAbilityTooltip(GenesisItems.BABY_PINK_SWET.get(), "\u00A79Ability:\u00A7r Allows Swet Riding\r\n");
        this.addAbilityTooltip(GenesisItems.ETHEREAL_STONE.get(), "\u00A79Ability:\u00A7r Gives Invisibility\r\n");
        this.addAbilityTooltip(GenesisItems.FLEETING_STONE.get(), "\u00A79Ability:\u00A7r Gives High Steps\r\n");
        this.addAbilityTooltip(GenesisItems.FANGRIN_CAPSULE.get(), "\u00A79Ability:\u00A7r Melee Combat\r\n");
        this.addAbilityTooltip(GenesisItems.DEATH_SEAL.get(), "\u00A79Ability:\u00A7r Gives Resurrection\r\n");
        this.addAbilityTooltip(GenesisItems.KRAISITH_CAPSULE.get(), "\u00A79Ability:\u00A7r Ranged Combat\r\n");
        this.addAbilityTooltip(GenesisItems.SOARING_STONE.get(), "\u00A79Ability:\u00A7r Gives Move Speed\r\n");
        this.addAbilityTooltip(GenesisItems.FROSTPINE_TOTEM.get(), "\u00A79Ability:\u00A7r Gives Night Vision\r\n");
        this.addAbilityTooltip(GenesisItems.FROSTBOUND_STONE.get(), "\u00A79Ability:\u00A7r Generates Snowballs\r\n");

        this.addAbilityTooltip(AetherItems.AMBROSIUM_SHARD.get(), "\u00A79Ability:\u00A7r Enchants Grass\r\n\u00A73Use:\u00A7r Right-Click Block");
        this.addAbilityTooltip(AetherItems.SWET_BALL.get(), "\u00A79Ability:\u00A7r Grows Grass\r\n\u00A73Use:\u00A7r Right-Click Block");
        this.addAbilityTooltip(GenesisItems.GOLDEN_SWET_BALL.get(), "\u00A79Ability:\u00A7r Grows Grass\r\n\u00A73Use:\u00A7r Right-Click Block");
        this.addAbilityTooltip(GenesisItems.DARK_SWET_BALL.get(), "\u00A79Ability:\u00A7r Grows Grass\r\n\u00A73Use:\u00A7r Right-Click Block");
        this.addAbilityTooltip(GenesisItems.CONTINUUM_ORB.get(), "\u00A79Ability:\u00A7r Grants Random Item\r\n\u00A73Use:\u00A7r Right-Click");
        this.addAbilityTooltip(AetherItems.BLUE_GUMMY_SWET.get(), "\u00A7aBuff:\u00A7r Fills Hunger");
        this.addAbilityTooltip(AetherItems.GOLDEN_GUMMY_SWET.get(), "\u00A7aBuff:\u00A7r Fills Hunger");
        this.addAbilityTooltip(GenesisItems.DARK_GUMMY_SWET.get(), "\u00A7aBuff:\u00A7r Fills Hunger");
        this.addAbilityTooltip(AetherItems.HEALING_STONE.get(), "\u00A7aBuff:\u00A7r Regenerates Health");
        this.addAbilityTooltip(AetherItems.WHITE_APPLE.get(), "\u00A7aBuff:\u00A7r Cures Inebriation");
        this.addAbilityTooltip(GenesisItems.RAINBOW_STRAWBERRY.get(), "\u00A7aBuff:\u00A7r Increases Mining Speed & Resistance");

        this.addAbilityTooltip(AetherItems.BLUE_MOA_EGG.get(), "\u00A773 Mid-Air Jumps\u00A7r");
        this.addAbilityTooltip(AetherItems.WHITE_MOA_EGG.get(), "\u00A774 Mid-Air Jumps\u00A7r");
        this.addAbilityTooltip(AetherItems.BLACK_MOA_EGG.get(), "\u00A778 Mid-Air Jumps\u00A7r");
        this.addAbilityTooltip(AetherItems.COLD_PARACHUTE.get(), "\u00A79Ability:\u00A7r Slow Descent\r\n\u00A73Use:\u00A7r Right-Click");
        this.addAbilityTooltip(AetherItems.GOLDEN_PARACHUTE.get(), "\u00A79Ability:\u00A7r Fast Descent\r\n\u00A73Use:\u00A7r Right-Click");
        this.addAbilityTooltip(GenesisItems.PURPLE_PARACHUTE.get(), "\u00A79Ability:\u00A7r Directional Descent\r\n\u00A73Use:\u00A7r Right-Click");
        this.addAbilityTooltip(GenesisItems.GREEN_PARACHUTE.get(), "\u00A79Ability:\u00A7r Forward Descent\r\n\u00A73Use:\u00A7r Right-Click");
        this.addAbilityTooltip(GenesisItems.BLUE_PARACHUTE.get(), "\u00A79Ability:\u00A7r Fast Elevation\r\n\u00A73Use:\u00A7r Right-Click");
        this.addAbilityTooltip(AetherItems.LIFE_SHARD.get(), "\u00A79Ability:\u00A7r Increase Max Health Up To 10\r\n\u00A73Use:\u00A7r Right-Click");
        this.addAbilityTooltip(AetherItems.SKYROOT_POISON_BUCKET.get(), "\u00A7cDebuff:\u00A7r Inflicts Inebriation");
        this.addAbilityTooltip(AetherItems.SKYROOT_REMEDY_BUCKET.get(), "\u00A7aBuff:\u00A7r Cures Inebriation");
        this.addAbilityTooltip(GenesisItems.CONTINUUM_BOMB.get(), "\u00A79Ability:\u00A7r Creates Festive Party!\r\n\u00A73Use:\u00A7r Right-Click");

        this.addEntityType(GenesisEntityTypes.CARRION_SPROUT, "Carrion Sprout");
        this.addEntityType(GenesisEntityTypes.DARK_SWET, "Dark Swet");
        this.addEntityType(GenesisEntityTypes.TEMPEST, "Tempest");
        this.addEntityType(GenesisEntityTypes.BATTLE_SENTRY, "Battle Sentry");
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
