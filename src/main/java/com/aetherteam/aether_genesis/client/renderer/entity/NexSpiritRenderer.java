package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.model.NexSpiritModel;
import com.aetherteam.aether_genesis.entity.companion.NexSpirit;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.Locale;

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
        poseStack.translate(0.0, -0.4, 0.0);
        float sin = Mth.sin((nexSpirit.tickCount + partialTickTime) / 6);
        poseStack.translate(0.0, sin / 15, 0.0);
    }

    @Override
    protected float getBob(NexSpirit nexSpirit, float partialTick) {
        return (nexSpirit.tickCount + partialTick) / 6;
    }

    @Override
    public ResourceLocation getTextureLocation(NexSpirit nexSpirit) {
        if (nexSpirit.isBroken()) {
            return NEX_SPIRIT_BROKEN_TEXTURE;
        } else {
            String name = nexSpirit.getName().getString();
            if (name.toLowerCase(Locale.ROOT).contains("wot") || name.toLowerCase(Locale.ROOT).contains("anger") || name.toLowerCase(Locale.ROOT).contains("angr")) {
                return NEX_SPIRIT_ANGERED_TEXTURE;
            } else if (name.toLowerCase(Locale.ROOT).contains("huh") || name.toLowerCase(Locale.ROOT).contains("confus")) {
                return NEX_SPIRIT_CONFUSED_TEXTURE;
            } else if (name.toLowerCase(Locale.ROOT).contains("sin") || name.toLowerCase(Locale.ROOT).contains("flush") || name.toLowerCase(Locale.ROOT).contains("floosh")) {
                return NEX_SPIRIT_FLUSHED_TEXTURE;
            } else if (name.toLowerCase(Locale.ROOT).contains("sick") || name.toLowerCase(Locale.ROOT).contains("nause") || name.toLowerCase(Locale.ROOT).contains("atleastitsnotanemoji")) {
                return NEX_SPIRIT_NAUSEATED_TEXTURE;
            } else if (name.toLowerCase(Locale.ROOT).contains("sad") || name.toLowerCase(Locale.ROOT).contains("pensive")) {
                return NEX_SPIRIT_SADDENED_TEXTURE;
            } else if (name.toLowerCase(Locale.ROOT).contains("sus")) {
                return NEX_SPIRIT_SUSPICIOUS_TEXTURE;
            }
            return NEX_SPIRIT_TEXTURE;
        }
    }
}
