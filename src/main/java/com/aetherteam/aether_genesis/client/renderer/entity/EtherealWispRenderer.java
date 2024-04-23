package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether.attachment.AetherDataAttachments;
import com.aetherteam.aether.attachment.AetherPlayerAttachment;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.entity.companion.Wisp;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class EtherealWispRenderer extends WispRenderer {
    public EtherealWispRenderer(EntityRendererProvider.Context context) {
        super(context, GenesisModelLayers.ETHEREAL_WISP, new ResourceLocation(Genesis.MODID, "textures/entity/companions/ethereal_wisp.png"));
    }

    @Override
    public void render(Wisp wisp, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        UUID owner = wisp.getOwner();
        if (owner != null) {
            Player player = wisp.level().getPlayerByUUID(owner);
            if (player != null) {
                AetherPlayerAttachment attachment = player.getData(AetherDataAttachments.AETHER_PLAYER);
                if (!attachment.isWearingInvisibilityCloak()) {
                    super.render(wisp, entityYaw, partialTicks, poseStack, buffer, packedLight);
                    if (this.shadowRadius < 0.25F) {
                        this.shadowRadius = 0.25F;
                    }
                } else {
                    if (this.shadowRadius > 0.0F) {
                        this.shadowRadius = 0.0F;
                    }
                }
            }
        }
    }
}
