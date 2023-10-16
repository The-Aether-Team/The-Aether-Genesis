package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether.capability.player.AetherPlayer;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.entity.companion.Wisp;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class EtherealWispRenderer extends WispRenderer {
    public EtherealWispRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, GenesisModelLayers.ETHEREAL_WISP, new ResourceLocation(Genesis.MODID, "textures/entity/companions/ethereal_wisp.png"));
    }

    @Override
    public void render(Wisp wisp, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        Player player = wisp.getLevel().getPlayerByUUID(wisp.getOwner());
        if (player != null) {
            AetherPlayer.get(player).ifPresent((aetherPlayer) -> {
                if (!aetherPlayer.isWearingInvisibilityCloak()) {
                    super.render(wisp, entityYaw, partialTicks, matrixStack, buffer, packedLight);
                    if (this.shadowRadius < 0.25F) {
                        this.shadowRadius = 0.25F;
                    }
                } else {
                    if (this.shadowRadius > 0.0F) {
                        this.shadowRadius = 0.0F;
                    }
                }
            });
        }
    }
}
