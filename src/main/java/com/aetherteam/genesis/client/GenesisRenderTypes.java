package com.aetherteam.genesis.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiFunction;

public class GenesisRenderTypes extends RenderStateShard {
    private static final RenderStateShard.ShaderStateShard RENDERTYPE_TRACKING_GOLEM_EYES_SHADER = new RenderStateShard.ShaderStateShard(GenesisShaders::getRendertypeTrackingGolemEyes);

    private static final BiFunction<ResourceLocation, TransparencyStateShard, RenderType> TRACKING_GOLEM_EYES = Util.memoize(
            (location, shard) -> {
                RenderStateShard.TextureStateShard renderstateshard$texturestateshard = new RenderStateShard.TextureStateShard(location, false, false);
                return RenderType.create(
                        "aether_genesis:tracking_golem_eyes",
                        DefaultVertexFormat.NEW_ENTITY,
                        VertexFormat.Mode.QUADS,
                        1536,
                        false,
                        true,
                        RenderType.CompositeState.builder()
                                .setShaderState(RENDERTYPE_TRACKING_GOLEM_EYES_SHADER)
                                .setTextureState(renderstateshard$texturestateshard)
                                .setTransparencyState(shard)
                                .setWriteMaskState(COLOR_WRITE)
                                .createCompositeState(false)
                );
            }
    );

    public GenesisRenderTypes(String name, Runnable setup, Runnable clear) {
        super(name, setup, clear);
    }

    public static RenderType eyes(ResourceLocation location) {
        return TRACKING_GOLEM_EYES.apply(location, ADDITIVE_TRANSPARENCY);
    }
}
