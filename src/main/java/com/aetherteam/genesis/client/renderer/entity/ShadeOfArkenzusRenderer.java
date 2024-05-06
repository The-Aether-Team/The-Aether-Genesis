package com.aetherteam.genesis.client.renderer.entity;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.entity.layers.ShadeOfArkenzusEyesLayer;
import com.aetherteam.genesis.client.renderer.entity.model.ShadeOfArkenzusModel;
import com.aetherteam.genesis.entity.companion.ShadeOfArkenzus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ShadeOfArkenzusRenderer extends CompanionRenderer<ShadeOfArkenzus, ShadeOfArkenzusModel> {
    private static final ResourceLocation SHADE_OF_ARKENZUS_TEXTURE = new ResourceLocation(AetherGenesis.MODID, "textures/entity/companions/shade_of_arkenzus/shade_of_arkenzus.png");

    public ShadeOfArkenzusRenderer(EntityRendererProvider.Context context) {
        super(context, new ShadeOfArkenzusModel(context.bakeLayer(GenesisModelLayers.SHADE_OF_ARKENZUS)), 0.35F);
        this.addLayer(new ShadeOfArkenzusEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ShadeOfArkenzus shadeOfArkenzus) {
        return SHADE_OF_ARKENZUS_TEXTURE;
    }
}
