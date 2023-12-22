package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.layers.TempestMarkingsLayer;
import com.aetherteam.aether_genesis.client.renderer.entity.layers.TempestTransparencyGlowLayer;
import com.aetherteam.aether_genesis.client.renderer.entity.layers.TempestTransparencyLayer;
import com.aetherteam.aether_genesis.client.renderer.entity.model.TempestModel;
import com.aetherteam.aether_genesis.entity.monster.Tempest;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class TempestRenderer extends MobRenderer<Tempest, TempestModel> {
    private static final ResourceLocation TEMPEST_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/tempest/tempest.png");

    public TempestRenderer(Context context) {
        super(context, new TempestModel(context.bakeLayer(GenesisModelLayers.TEMPEST)), 0.5F);
        this.addLayer(new TempestMarkingsLayer(this));
        this.addLayer(new TempestTransparencyLayer(this, new TempestModel(context.getModelSet().bakeLayer(GenesisModelLayers.TEMPEST_TRANSPARENCY))));
        this.addLayer(new TempestTransparencyGlowLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Tempest pEntity) {
        return TEMPEST_TEXTURE;
    }

    protected void scale(Tempest tempest,  PoseStack poseStack, float partialTickTime) {
        float f = Mth.lerp(partialTickTime, (float)tempest.getCloudScale(), (float)(tempest.getCloudScale() + tempest.getCloudScaleAdd()));
        float f1 = f / 40.0F;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        f1 = 1.0F / ((float)Math.pow((double)f1, 5.0D) * 2.0F + 1.0F);
        float f2 = (8.0F + f1) / 2.0F;
        float f3 = (8.0F + 1.0F / f1) / 2.0F;
        poseStack.scale(f3, f2, f3);
        poseStack.translate(0.0D, 0.5D, 0.0D);
        if (this.getModel() instanceof TempestModel) {
            poseStack.scale(0.8F, 0.8F, 0.8F);
            poseStack.translate(0.0D, -0.1D, 0.0D);
        }
        float sin = Mth.sin((tempest.tickCount + partialTickTime) / 6);
        poseStack.translate(0.0D, sin / 15, 0.0D);
    }

    protected float getBob( Tempest tempest, float partialTicks) {
        return Mth.lerp(partialTicks, tempest.getTailRot(), tempest.getTailRot() + tempest.getTailRotAdd());
    }
}
