package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.entity.companion.NexSpirit;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class NexSpiritRenderer extends CompanionRenderer<NexSpirit, NexSpiritModel> {
    private static final ResourceLocation NEX_SPIRIT_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/nex_spirit/nex_spirit.png");
    private static final ResourceLocation NEX_SPIRIT_BROKEN_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/nex_spirit/nex_spirit_broken.png");
    private static final ResourceLocation NEX_SPIRIT_ANGERED_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/nex_spirit/nex_spirit_angered.png");
    private static final ResourceLocation NEX_SPIRIT_CONFUSED_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/nex_spirit/nex_spirit_confused.png");
    private static final ResourceLocation NEX_SPIRIT_FLUSHED_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/nex_spirit/nex_spirit_flushed.png");
    private static final ResourceLocation NEX_SPIRIT_NAUSEATED_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/nex_spirit/nex_spirit_nauseated.png");
    private static final ResourceLocation NEX_SPIRIT_SADDENED_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/nex_spirit/nex_spirit_saddened.png");
    private static final ResourceLocation NEX_SPIRIT_SUSPICIOUS_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/nex_spirit/nex_spirit_suspicious.png");

    public NexSpiritRenderer(EntityRendererProvider.Context context) {
        super(context, new NexSpiritModel(context.bakeLayer(GenesisModelLayers.NEX_SPIRIT)), 0.45F);
    }

    @Override
    protected void scale(NexSpirit nexSpirit, PoseStack poseStack, float partialTickTime) {
        poseStack.translate(0.0, -0.45, 0.0);
        float sin = Mth.sin((nexSpirit.tickCount + partialTickTime) / 6);
        poseStack.translate(0.0, sin / 15, 0.0);
    }

    @Override
    protected float getBob(NexSpirit nexSpirit, float partialTick) {
        return partialTick;
    }

    @Override
    public ResourceLocation getTextureLocation(NexSpirit nexSpirit) {
        if (nexSpirit.isBroken()) {
            return NEX_SPIRIT_BROKEN_TEXTURE;
        } else {
            String name = nexSpirit.getName().getString();
            if (name.equalsIgnoreCase("Angered")) { //todo expand naming possibilities
                return NEX_SPIRIT_ANGERED_TEXTURE;
            } else if (name.equalsIgnoreCase("Confused")) {
                return NEX_SPIRIT_CONFUSED_TEXTURE;
            } else if (name.equalsIgnoreCase("Flushed")) {
                return NEX_SPIRIT_FLUSHED_TEXTURE;
            } else if (name.equalsIgnoreCase("Nauseated")) {
                return NEX_SPIRIT_NAUSEATED_TEXTURE;
            } else if (name.equalsIgnoreCase("Saddened")) {
                return NEX_SPIRIT_SADDENED_TEXTURE;
            } else if (name.equalsIgnoreCase("Suspicious")) {
                return NEX_SPIRIT_SUSPICIOUS_TEXTURE;
            }
            return NEX_SPIRIT_TEXTURE;
        }
    }
}
