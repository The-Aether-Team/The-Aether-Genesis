package com.aetherteam.genesis;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class GenesisConfig {
    public static class Common {
        public final ModConfigSpec.ConfigValue<Boolean> gold_aercloud_ability;
        public final ModConfigSpec.ConfigValue<Boolean> tan_zephyr_variation;
        public final ModConfigSpec.ConfigValue<Boolean> improved_slider_message;
        public final ModConfigSpec.ConfigValue<Integer> biome_weight;
        public final ModConfigSpec.ConfigValue<Boolean> aether_ii_portal_sounds;

        public Common(ModConfigSpec.Builder builder) { //todo server config
            builder.push("Gameplay");
            gold_aercloud_ability = builder
                    .comment("Changes Gold Aercloud and respective parachute behavior to launch entities downwards")
                    .translation("config.aether_genesis.common.gameplay.gold_aercloud_ability")
                    .define("Gold Aerclouds launch entities downwards", true);
            tan_zephyr_variation = builder
                    .comment("Allows a smaller, tan variation of Zephyrs to spawn")
                    .translation("config.aether_genesis.common.gameplay.tan_zephyr_variation")
                    .define("Tan Zephyr variant", true);
            improved_slider_message = builder
                    .comment("Changes the message sent on attacking the Slider with an incorrect item to an alternate version which more subtly implies that you need a pickaxe")
                    .translation("config.aether_genesis.common.gameplay.improved_slider_message")
                    .define("Improved Slider Message", true);
            builder.pop();
            builder.push("World Generation");
            biome_weight = builder
                    .comment("Determines the biome weight for biome regions.")
                    .translation("config.aether_genesis.common.world_generation.biome_weight")
                    .define("Biome Weight", 15);
            builder.pop();
            builder.push("Audio");
            aether_ii_portal_sounds = builder
                    .comment("Gives Aether Portals their sounds from the Aether II")
                    .translation("config.aether_genesis.client.audio.aether_ii_portal_sounds")
                    .define("Aether II Portal Sounds", true);
            builder.pop();
        }
    }

    public static class Client {
        public final ModConfigSpec.ConfigValue<Boolean> genesis_menu_layout;
        public final ModConfigSpec.ConfigValue<Boolean> night_music_tracks;

        public Client(ModConfigSpec.Builder builder) {
            builder.push("Gui");
            genesis_menu_layout = builder
                    .comment("Replaces the menu toggle buttons with the Cumulus' menu switcher")
                    .translation("config.aether.client.gui.genesis_menu_layout")
                    .define("Genesis menu button layout", true);
            builder.pop();

            builder.push("Audio");
            night_music_tracks = builder
                    .comment("Adds some nice night tracks to the Aether's music selection. Also disables the default music manager for the Aether, to prevent overlap")
                    .translation("config.aether_genesis.client.audio.night_music_tracks")
                    .define("Nighttime music tracks", true);
        }
    }

    public static final ModConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    public static final ModConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        final Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();

        final Pair<Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
