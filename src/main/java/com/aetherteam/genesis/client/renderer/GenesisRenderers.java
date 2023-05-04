package com.aetherteam.genesis.client.renderer;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.client.renderer.entity.DarkSwetRenderer;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.client.renderer.entity.ParachuteRenderer;
import com.aetherteam.aether.entity.AetherEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Genesis.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GenesisEntityTypes.DARK_SWET.get(), DarkSwetRenderer::new);

        event.registerEntityRenderer(GenesisEntityTypes.BLUE_PARACHUTE.get(), (context) -> new ParachuteRenderer(context, AetherBlocks.BLUE_AERCLOUD));
        event.registerEntityRenderer(GenesisEntityTypes.GREEN_PARACHUTE.get(), (context) -> new ParachuteRenderer(context, GenesisBlocks.GREEN_AERCLOUD));
        event.registerEntityRenderer(GenesisEntityTypes.PURPLE_PARACHUTE.get(), (context) -> new ParachuteRenderer(context, GenesisBlocks.PURPLE_AERCLOUD));
    }
}
