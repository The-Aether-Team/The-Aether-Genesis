package com.aetherteam.aether_genesis.capability;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.capability.player.GenesisPlayer;
import com.aetherteam.aether_genesis.capability.player.GenesisPlayerCapability;
import com.aetherteam.aether_genesis.capability.zephyr.ZephyrColor;
import com.aetherteam.aether_genesis.capability.zephyr.ZephyrColorCapability;
import com.aetherteam.nitrogen.capability.CapabilityProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = Aether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisCapabilities {
	public static final Capability<GenesisPlayer> GENESIS_PLAYER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() { });
	public static final Capability<ZephyrColor> ZEPHYR_COLOR_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() { });

	@SubscribeEvent
	public static void register(RegisterCapabilitiesEvent event) {
		event.register(GenesisPlayer.class);
	}

	@EventBusSubscriber(modid = Genesis.MODID)
	public static class Registration {
		@SubscribeEvent
		public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player player) {
				event.addCapability(new ResourceLocation(Genesis.MODID, "genesis_player"), new CapabilityProvider(GenesisCapabilities.GENESIS_PLAYER_CAPABILITY, new GenesisPlayerCapability(player)));
			}
			if (event.getObject() instanceof Zephyr zephyr) {
				event.addCapability(new ResourceLocation(Genesis.MODID, "zephyr_color"), new CapabilityProvider(GenesisCapabilities.ZEPHYR_COLOR_CAPABILITY, new ZephyrColorCapability(zephyr)));
			}
		}
	}
}
