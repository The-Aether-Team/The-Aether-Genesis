package com.aetherteam.aether_genesis;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class GenesisConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> gold_aercloud_ability;

        public Common(ForgeConfigSpec.Builder builder) { //todo server config
            builder.push("Gameplay");
            gold_aercloud_ability = builder
                    .comment("Changes Gold Aercloud and respective parachute behavior to launch entities downwards")
                    .translation("config.aether_genesis.common.gameplay.gold_aercloud_ability")
                    .define("Gold Aerclouds launch entities downwards", true);
            builder.pop();
        }
    }

    public static class Client {
        public final ForgeConfigSpec.ConfigValue<Boolean> genesis_menu_layout;
        public final ForgeConfigSpec.ConfigValue<Boolean> night_music_tracks;
        public final ForgeConfigSpec.ConfigValue<Boolean> blue_aercloud_bounce_sfx;

        public Client(ForgeConfigSpec.Builder builder) {
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
            blue_aercloud_bounce_sfx = builder
                    .comment("Makes Blue Aerclouds have their wobbly sounds that play when bouncing on them")
                    .translation("config.aether_genesis.client.audio.blue_aercloud_bounce_sfx")
                    .define("Blue Aercloud bouncing sounds", true);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();

        final Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
