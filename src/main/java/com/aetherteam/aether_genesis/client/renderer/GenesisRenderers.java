package com.aetherteam.aether_genesis.client.renderer;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.blockentity.AetherBlockEntityTypes;
import com.aetherteam.aether.client.renderer.blockentity.TreasureChestRenderer;
import com.aetherteam.aether.client.renderer.entity.IceCrystalRenderer;
import com.aetherteam.aether.client.renderer.entity.ParachuteRenderer;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.blockentity.GenesisBlockEntityTypes;
import com.aetherteam.aether_genesis.client.renderer.entity.CarrionSproutRenderer;
import com.aetherteam.aether_genesis.client.renderer.entity.DarkSwetRenderer;
import com.aetherteam.aether_genesis.client.renderer.entity.PhoenixDartRenderer;
import com.aetherteam.aether_genesis.client.renderer.entity.TempestRenderer;
import com.aetherteam.aether_genesis.client.renderer.model.CarrionSproutModel;
import com.aetherteam.aether_genesis.client.renderer.model.TempestModel;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Genesis.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(GenesisBlockEntityTypes.SKYROOT_CHEST.get(), SkyrootChestRenderer::new);

        event.registerEntityRenderer(GenesisEntityTypes.CARRION_SPROUT.get(), CarrionSproutRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.DARK_SWET.get(), DarkSwetRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.TEMPEST.get(), TempestRenderer::new);

        event.registerEntityRenderer(GenesisEntityTypes.BLUE_PARACHUTE.get(), (context) -> new ParachuteRenderer(context, AetherBlocks.BLUE_AERCLOUD));
        event.registerEntityRenderer(GenesisEntityTypes.GREEN_PARACHUTE.get(), (context) -> new ParachuteRenderer(context, GenesisBlocks.GREEN_AERCLOUD));
        event.registerEntityRenderer(GenesisEntityTypes.PURPLE_PARACHUTE.get(), (context) -> new ParachuteRenderer(context, GenesisBlocks.PURPLE_AERCLOUD));

        event.registerEntityRenderer(GenesisEntityTypes.TEMPEST_THUNDERBALL.get(), IceCrystalRenderer::new);
        event.registerEntityRenderer(GenesisEntityTypes.PHOENIX_DART.get(), PhoenixDartRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GenesisModelLayers.CARRION_SPROUT, CarrionSproutModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.TEMPEST, TempestModel::createBodyLayer);
        event.registerLayerDefinition(GenesisModelLayers.TEMPEST_TRANSPARENCY, TempestModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void addPlayerLayers(EntityRenderersEvent.AddLayers event) {
        EntityRenderDispatcher renderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        String[] types = new String[]{"default", "slim"};
        for (String type : types) {
            PlayerRenderer playerRenderer = event.getSkin(type);
            if (playerRenderer != null) {
                playerRenderer.addLayer(new PhoenixDartLayer(renderDispatcher, playerRenderer));
            }
        }
    }
}
