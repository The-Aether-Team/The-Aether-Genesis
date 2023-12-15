package com.aetherteam.aether_genesis.client.renderer;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.capability.player.AetherPlayer;
import com.aetherteam.aether.client.renderer.accessory.PendantRenderer;
import com.aetherteam.aether.client.renderer.entity.IceCrystalRenderer;
import com.aetherteam.aether.client.renderer.entity.ParachuteRenderer;
import com.aetherteam.aether.client.renderer.entity.model.MimicModel;
import com.aetherteam.aether.client.renderer.player.layer.DartLayer;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.blockentity.GenesisBlockEntityTypes;
import com.aetherteam.aether_genesis.client.renderer.accessory.MouseEarCapRenderer;
import com.aetherteam.aether_genesis.client.renderer.accessory.model.MouseEarCapModel;
import com.aetherteam.aether_genesis.client.renderer.blockentity.SkyrootChestMimicRenderer;
import com.aetherteam.aether_genesis.client.renderer.blockentity.SkyrootChestRenderer;
import com.aetherteam.aether_genesis.client.renderer.entity.*;
import com.aetherteam.aether_genesis.client.renderer.entity.model.*;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.entity.PhoenixDart;
import com.aetherteam.aether_genesis.item.GenesisItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = Genesis.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(GenesisBlockEntityTypes.SKYROOT_CHEST.get(), SkyrootChestRenderer::new);
        event.registerBlockEntityRenderer(GenesisBlockEntityTypes.SKYROOT_CHEST_MIMIC.get(), SkyrootChestMimicRenderer::new);

        event.registerEntityRenderer(GenesisEntityTypes.CARRION_SPROUT.get(), CarrionSproutRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.DARK_SWET.get(), DarkSwetRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.TEMPEST.get(), TempestRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.BATTLE_SENTRY.get(), BattleSentryRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.TRACKING_GOLEM.get(), TrackingGolemRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.SKYROOT_MIMIC.get(), SkyrootMimicRenderer::new);

        event.registerEntityRenderer(GenesisEntityTypes.SENTRY_GUARDIAN.get(), SentryGuardianRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.SLIDER_HOST_MIMIC.get(), HostMimicRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.LABYRINTH_EYE.get(), LabyrinthEyeRenderer::new);

        event.registerEntityRenderer(GenesisEntityTypes.BLUE_PARACHUTE.get(), (context) -> new ParachuteRenderer(context, AetherBlocks.BLUE_AERCLOUD));
        event.registerEntityRenderer(GenesisEntityTypes.GREEN_PARACHUTE.get(), (context) -> new ParachuteRenderer(context, GenesisBlocks.GREEN_AERCLOUD));
        event.registerEntityRenderer(GenesisEntityTypes.PURPLE_PARACHUTE.get(), (context) -> new ParachuteRenderer(context, GenesisBlocks.PURPLE_AERCLOUD));

        event.registerEntityRenderer(GenesisEntityTypes.FLEETING_WISP.get(), (context) -> new WispRenderer(context, GenesisModelLayers.FLEETING_WISP, new ResourceLocation(Genesis.MODID, "textures/entity/companions/fleeting_wisp.png")));
        event.registerEntityRenderer(GenesisEntityTypes.SOARING_WISP.get(), (context) -> new WispRenderer(context, GenesisModelLayers.SOARING_WISP, new ResourceLocation(Genesis.MODID, "textures/entity/companions/soaring_wisp.png")));
        event.registerEntityRenderer(GenesisEntityTypes.ETHEREAL_WISP.get(), EtherealWispRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.SHADE_OF_ARKENZUS.get(), ShadeOfArkenzusRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.FROSTPINE_TOTEM.get(), FrostpineTotemRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.BABY_PINK_SWET.get(), BabyPinkSwetRenderer::new);

        event.registerEntityRenderer(GenesisEntityTypes.DAGGERFROST_SNOWBALL.get(), DaggerfrostSnowballRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.TEMPEST_THUNDERBALL.get(), IceCrystalRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.PHOENIX_DART.get(), PhoenixDartRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.COG_ARROW.get(), CogArrowRenderer::new);

        event.registerEntityRenderer(GenesisEntityTypes.CONTINUUM_BOMB.get(), (context) -> new ThrownItemRenderer<>(context, 1.0F, true));
        event.registerEntityRenderer(GenesisEntityTypes.REWARD_ITEM.get(), ItemEntityRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.HOST_EYE.get(), HostEyeRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GenesisModelLayers.SKYROOT_CHEST_MIMIC, ChestRenderer::createSingleBodyLayer);

        event.registerLayerDefinition(GenesisModelLayers.CARRION_SPROUT, CarrionSproutModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.SKYROOT_MIMIC, MimicModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.TEMPEST, TempestModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.TEMPEST_TRANSPARENCY, TempestModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.BATTLE_SENTRY, SlimeModel::createOuterBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.TRACKING_GOLEM, TrackingGolemModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.SENTRY_GUARDIAN, SentryGuardianModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.SLIDER_HOST_MIMIC, SliderHostMimicModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.LABYRINTH_EYE, LabyrinthEyeModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.HOST_EYE, HostEyeModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.COG_ARROW, CogArrowModel::createBodyLayer);

        event.registerLayerDefinition(GenesisModelLayers.FLEETING_WISP, WispModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.SOARING_WISP, WispModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.ETHEREAL_WISP, WispModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.SHADE_OF_ARKENZUS, ShadeOfArkenzusModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.FROSTPINE_TOTEM, FrostpineTotemModel::createBodyLayer);

        event.registerLayerDefinition(GenesisModelLayers.MOUSE_EAR_CAP, MouseEarCapModel::createLayer);
    }

    public static void registerCuriosRenderers() {
        CuriosRendererRegistry.register(GenesisItems.LUCKY_BELL.get(), PendantRenderer::new);
        CuriosRendererRegistry.register(GenesisItems.SWETTY_PENDANT.get(), PendantRenderer::new);
        CuriosRendererRegistry.register(GenesisItems.DAGGERFROST_LOCKET.get(), PendantRenderer::new);
        CuriosRendererRegistry.register(GenesisItems.MOUSE_EAR_CAP.get(), MouseEarCapRenderer::new);
    }

    @SubscribeEvent
    public static void addPlayerLayers(EntityRenderersEvent.AddLayers event) {
        EntityRenderDispatcher renderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        String[] types = new String[]{"default", "slim"};
        for (String type : types) {
            PlayerRenderer playerRenderer = event.getSkin(type);
            if (playerRenderer != null) {
                playerRenderer.addLayer(new DartLayer<>(renderDispatcher, playerRenderer, (entity) -> new PhoenixDart(GenesisEntityTypes.PHOENIX_DART.get(), entity.level()), AetherPlayer::getGoldenDartCount, 1.0F));
            }
        }
    }
}
