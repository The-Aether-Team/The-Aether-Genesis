package com.aetherteam.genesis.client;

import com.aetherteam.genesis.AetherGenesis;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = AetherGenesis.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisShaders {
    private static ShaderInstance rendertypeTrackingGolemEyes;

    @SubscribeEvent
    public static void registerShaders(RegisterShadersEvent event) {
        ResourceProvider resourceProvider = event.getResourceProvider();
        try {
            event.registerShader(new ShaderInstance(resourceProvider, new ResourceLocation(AetherGenesis.MODID, "rendertype_tracking_golem_eyes"), DefaultVertexFormat.NEW_ENTITY), instance -> rendertypeTrackingGolemEyes = instance);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ShaderInstance getRendertypeTrackingGolemEyes() {
        return rendertypeTrackingGolemEyes;
    }
}
