package com.aetherteam.genesis.client.event.listeners;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.passive.Sheepuff;
import com.aetherteam.genesis.Genesis;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderLivingEvent;

@Mod.EventBusSubscriber(modid = Genesis.MODID, value = Dist.CLIENT)
public class GenesisRenderListener {
    @SubscribeEvent
    public static void onClientTick(RenderLivingEvent.Pre<?, ?> event) { //todo doesnt work doesnt render in right spot
        Entity entity = event.getEntity();
        PoseStack poseStack = event.getPoseStack();
        if (entity.getType() == AetherEntityTypes.AERBUNNY.get()) {
            float f = 1.0F;
            float f1 = 1.0F;
            float f2 = 1.0F;
            if (entity.hasCustomName() && entity.getName().getString().equals("jeb_")) {
                int i1 = 25;
                int i = entity.tickCount / i1 + entity.getId();
                int j = DyeColor.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f3 = ((float) (entity.tickCount % i1) + event.getPartialTick()) / (float) i1;
                float[] afloat1 = Sheepuff.getColorArray(DyeColor.byId(k));
                float[] afloat2 = Sheepuff.getColorArray(DyeColor.byId(l));
                f = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
                f1 = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
                f2 = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
            }
            poseStack.pushPose();
            RenderSystem.setShaderColor(f, f1, f2, 1.0F);
            poseStack.popPose();
        }
    }
}
