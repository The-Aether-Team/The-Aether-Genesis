package com.aetherteam.genesis.client;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.client.AetherClient;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.GenesisConfig;
import com.aetherteam.genesis.client.event.listeners.GenesisAudioListener;
import com.aetherteam.genesis.client.particle.GenesisParticleTypes;
import com.aetherteam.genesis.client.renderer.GenesisRenderers;
import com.aetherteam.genesis.item.GenesisItems;
import com.aetherteam.genesis.item.components.GenesisDataComponents;
import com.aetherteam.nitrogen.event.listeners.TooltipListeners;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = AetherGenesis.MODID, dist = Dist.CLIENT)
public class AetherGenesisClient {
    public AetherGenesisClient(ModContainer mod, IEventBus bus) {
        bus.addListener(AetherGenesisClient::clientSetup);

        bus.addListener(GenesisShaders::registerShaders);
        bus.addListener(GenesisParticleTypes::registerParticleFactories);
        bus.addListener(GenesisColorResolvers::registerItemColor);

        GenesisRenderers.listener(bus);
        GenesisAudioListener.listeners(NeoForge.EVENT_BUS);
    }

    public static void clientSetup(FMLClientSetupEvent event) {
        GenesisRenderers.registerCuriosRenderers();
        event.enqueueWork(() -> {
            if (GenesisConfig.CLIENT.night_music_tracks.get()) {
                AetherConfig.CLIENT.disable_music_manager.set(true);
            }
            registerItemModelProperties();
            registerTooltipOverrides();
        });
    }

    public static void registerItemModelProperties() {
        ItemProperties.register(GenesisItems.DEATH_SEAL.get(), ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "broken"),
                (stack, world, living, i) -> stack.has(GenesisDataComponents.NEX_SPIRIT_COOLDOWN) && stack.get(GenesisDataComponents.NEX_SPIRIT_COOLDOWN) > 0 ? 1.0F : 0.0F);

        AetherClient.CAPE_SECRETS.put((stack) -> stack.getHoverName().getString().equalsIgnoreCase("aercloud cape"), ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "textures/models/accessory/capes/aercloud_cape_accessory.png"));
        AetherClient.CAPE_SECRETS.put((stack) -> stack.getHoverName().getString().equalsIgnoreCase("kingbdogz' cape"), ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "textures/models/accessory/capes/kingbdogz_cape_accessory.png"));
        AetherClient.CAPE_SECRETS.put((stack) -> stack.getHoverName().getString().equalsIgnoreCase("lunerequinox's cape"), ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "textures/models/accessory/capes/lunerequinox_cape_accessory.png"));
        AetherClient.CAPE_SECRETS.put((stack) -> stack.getHoverName().getString().equalsIgnoreCase("slider cape"), ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "textures/models/accessory/capes/slider_cape_accessory.png"));
    }

    public static void registerTooltipOverrides() {
        TooltipListeners.PREDICATES.put(AetherItems.GOLDEN_PARACHUTE, (player, stack, components, ctx, component) -> {
            if (GenesisConfig.STARTUP.gold_aercloud_ability.get() && component.getContents() instanceof TranslatableContents contents && contents.getKey().endsWith(".1")) {
                return Component.translatable(contents.getKey() + ".genesis");
            } else {
                return component;
            }
        });
    }
}
