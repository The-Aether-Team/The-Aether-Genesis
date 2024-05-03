package com.aetherteam.genesis.attachment;

import com.aetherteam.genesis.Genesis;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class GenesisDataAttachments {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Genesis.MODID);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<GenesisPlayerAttachment>> GENESIS_PLAYER = ATTACHMENTS.register("genesis_player", () -> AttachmentType.builder(GenesisPlayerAttachment::new).build());
	public static final DeferredHolder<AttachmentType<?>, AttachmentType<ZephyrColorAttachment>> ZEPHYR_COLOR = ATTACHMENTS.register("zephyr_color", () -> AttachmentType.builder(ZephyrColorAttachment::new).serialize(ZephyrColorAttachment.CODEC).copyOnDeath().build());
}
