package com.aetherteam.aether_genesis;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class GenesisConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> gold_aercloud_ability;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("Gameplay");
            gold_aercloud_ability = builder
                    .comment("Changes Gold Aercloud and respective parachute behavior to launch entities downwards")
                    .translation("config.aether_genesis.common.gameplay.gold_aercloud_ability")
                    .define("Gold Aerclouds launch entities downwards", true);
            builder.pop();
        }
    }

    public static class Client {
        public Client(ForgeConfigSpec.Builder builder) {

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
