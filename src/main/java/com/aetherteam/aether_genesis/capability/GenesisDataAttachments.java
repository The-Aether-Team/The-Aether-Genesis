package com.aetherteam.aether_genesis.capability;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether_genesis.Genesis;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@Mod.EventBusSubscriber(modid = Aether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisDataAttachments {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Genesis.MODID);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<GenesisPlayerAttachment>> GENESIS_PLAYER = ATTACHMENTS.register("genesis_player", () -> AttachmentType.builder(GenesisPlayerAttachment::new).copyOnDeath().build());
	public static final DeferredHolder<AttachmentType<?>, AttachmentType<ZephyrColorAttachment>> ZEPHYR_COLOR = ATTACHMENTS.register("zephyr_color", () -> AttachmentType.builder(ZephyrColorAttachment::new).serialize(ZephyrColorAttachment.CODEC).copyOnDeath().build());
}
