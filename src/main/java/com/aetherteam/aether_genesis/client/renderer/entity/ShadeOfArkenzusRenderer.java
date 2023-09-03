package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.aether_genesis.client.renderer.entity.layers.ShadeOfArkenzusEyesLayer;
import com.aetherteam.aether_genesis.client.renderer.entity.model.ShadeOfArkenzusModel;
import com.aetherteam.aether_genesis.entity.companion.ShadeOfArkenzus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ShadeOfArkenzusRenderer extends CompanionRenderer<ShadeOfArkenzus, ShadeOfArkenzusModel> {
    private static final ResourceLocation SHADE_OF_ARKENZUS_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/companions/shade_of_arkenzus/shade_of_arkenzus.png");

    public ShadeOfArkenzusRenderer(EntityRendererProvider.Context context) {
        super(context, new ShadeOfArkenzusModel(context.bakeLayer(GenesisModelLayers.SHADE_OF_ARKENZUS)), 0.25F);
        this.addLayer(new ShadeOfArkenzusEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ShadeOfArkenzus p_entity) {
        return SHADE_OF_ARKENZUS_TEXTURE;
    }
}
