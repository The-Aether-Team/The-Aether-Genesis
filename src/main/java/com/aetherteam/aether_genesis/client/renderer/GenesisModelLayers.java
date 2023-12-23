package com.aetherteam.aether_genesis.client.renderer;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class GenesisModelLayers {
    public static final ModelLayerLocation CARRION_SPROUT = register("carrion_sprout");
    public static final ModelLayerLocation ZEPHYROO = register("zephyroo");
    public static final ModelLayerLocation TEMPEST = register("tempest");
    public static final ModelLayerLocation TEMPEST_TRANSPARENCY = register("tempest", "transparency");
    public static final ModelLayerLocation BATTLE_SENTRY = register("battle_sentry");
    public static final ModelLayerLocation TRACKING_GOLEM = register("tracking_golem");
    public static final ModelLayerLocation SKYROOT_MIMIC = register("skyroot_mimic");
    public static final ModelLayerLocation SKYROOT_CHEST_MIMIC = register("skyroot_chest_mimic");
    public static final ModelLayerLocation SENTRY_GUARDIAN = register("sentry_guardian");
    public static final ModelLayerLocation SLIDER_HOST_MIMIC = register("slider_host_mimic");
    public static final ModelLayerLocation LABYRINTH_EYE = register("labyrinth_eye");
    public static final ModelLayerLocation HOST_EYE_PROJECTILE = register("host_eye");
    public static final ModelLayerLocation COG_PROJECTILE = register("cog_arrow");

    public static final ModelLayerLocation FLEETING_WISP = register("fleeting_wisp");
    public static final ModelLayerLocation SOARING_WISP = register("soaring_wisp");
    public static final ModelLayerLocation ETHEREAL_WISP = register("ethereal_wisp");
    public static final ModelLayerLocation SHADE_OF_ARKENZUS = register("shade_of_arkenzus");
    public static final ModelLayerLocation FROSTPINE_TOTEM = register("frostpine_totem");

    public static final ModelLayerLocation MOUSE_EAR_CAP = register("mouse_ear_cap");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String type) {
        return register(new ResourceLocation(Genesis.MODID, name), type);
    }

    private static ModelLayerLocation register(ResourceLocation identifier, String type) {
        return new ModelLayerLocation(identifier, type);
    }
}
