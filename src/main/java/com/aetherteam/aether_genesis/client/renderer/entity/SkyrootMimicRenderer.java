package com.aetherteam.aether_genesis.client.renderer.entity;

import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.entity.model.MimicModel;
import com.aetherteam.aether.entity.monster.dungeon.Mimic;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.client.renderer.GenesisModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SkyrootMimicRenderer extends MobRenderer<Mimic, MimicModel> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(Genesis.MODID, "textures/entity/mobs/mimic/normal.png");

	public SkyrootMimicRenderer(EntityRendererProvider.Context renderer) {
		super(renderer, new MimicModel(renderer.bakeLayer(GenesisModelLayers.SKYROOT_MIMIC)), 1.0F);
	}

	@Nonnull
	@Override
	public ResourceLocation getTextureLocation(@Nonnull Mimic mimic) {
		return TEXTURE;
	}
}