package com.aetherteam.genesis;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class GenesisConfig {
    public static class Startup {
        public final ModConfigSpec.ConfigValue<Boolean> altar_redesign;
        public final ModConfigSpec.ConfigValue<Boolean> gold_aercloud_ability;

        public Startup(ModConfigSpec.Builder builder) {
            builder.push("Gameplay");
            altar_redesign = builder
                    .comment("Changes the Altar design and functionality to be like how it was in the Aether II. WARNING: Due to config limitations, this must be the same on both server and client to work properly")
                    .translation("config.aether_genesis.common.gameplay.altar_redesign")
                    .define("Changes Altar design", true);
            gold_aercloud_ability = builder
                    .comment("Changes Gold Aercloud and respective parachute behavior to launch entities downwards. WARNING: Due to config limitations, this must be the same on both server and client to work properly")
                    .translation("config.aether_genesis.common.gameplay.gold_aercloud_ability")
                    .define("Gold Aerclouds launch entities downwards", true);
            builder.pop();
        }
    }

    public static class Server {
        public final ModConfigSpec.ConfigValue<Boolean> tan_zephyr_variation;
        public final ModConfigSpec.ConfigValue<Boolean> improved_slider_message;

        public Server(ModConfigSpec.Builder builder) {
            builder.push("Gameplay");
            improved_slider_message = builder
                    .comment("Changes the message sent on attacking the Slider with an incorrect item to an alternate version which more subtly implies that you need a pickaxe")
                    .translation("config.aether_genesis.server.gameplay.improved_slider_message")
                    .define("Improved Slider Message", true);
            tan_zephyr_variation = builder
                    .comment("Allows a smaller, tan variation of Zephyrs to spawn")
                    .translation("config.aether_genesis.server.gameplay.tan_zephyr_variation")
                    .define("Tan Zephyr variant", true);
            builder.pop();
        }
    }

    public static class Common {
        public final ModConfigSpec.ConfigValue<Integer> biome_weight;

        public Common(ModConfigSpec.Builder builder) {
            builder.push("World Generation");
            biome_weight = builder
                    .comment("Determines the biome weight for biome regions")
                    .translation("config.aether_genesis.common.world_generation.biome_weight")
                    .define("Biome Weight", 15);
            builder.pop();
        }
    }

    public static class Client {
        public final ModConfigSpec.ConfigValue<Boolean> night_music_tracks;

        public Client(ModConfigSpec.Builder builder) {
            builder.push("Audio");
            night_music_tracks = builder
                    .comment("Adds some nice night tracks to the Aether's music selection. Also disables the default music manager for the Aether, to prevent overlap")
                    .translation("config.aether_genesis.client.audio.night_music_tracks")
                    .define("Nighttime music tracks", true);
        }
    }

    public static final ModConfigSpec STARTUP_SPEC;
    public static final Startup STARTUP;

    public static final ModConfigSpec SERVER_SPEC;
    public static final Server SERVER;

    public static final ModConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    public static final ModConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        final Pair<Startup, ModConfigSpec> startupSpecPair = new ModConfigSpec.Builder().configure(Startup::new);
        STARTUP_SPEC = startupSpecPair.getRight();
        STARTUP = startupSpecPair.getLeft();

        final Pair<Server, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = serverSpecPair.getRight();
        SERVER = serverSpecPair.getLeft();

        final Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();

        final Pair<Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
