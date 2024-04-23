package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether.client.renderer.entity.model.MimicModel;
import com.aetherteam.aether.entity.monster.dungeon.Mimic;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SkyrootMimicRenderer extends MobRenderer<Mimic, MimicModel> {
	private static final ResourceLocation SKYROOT_MIMIC_TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/mimic/normal.png");

	public SkyrootMimicRenderer(EntityRendererProvider.Context context) {
		super(context, new MimicModel(context.bakeLayer(GenesisModelLayers.SKYROOT_MIMIC)), 1.0F);
	}
	
	@Override
	public ResourceLocation getTextureLocation(Mimic mimic) {
		return SKYROOT_MIMIC_TEXTURE;
	}
}