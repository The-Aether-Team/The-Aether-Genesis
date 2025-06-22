package com.aetherteam.genesis;

import com.aetherteam.aether.data.generators.AetherRegistrySets;
import com.aetherteam.aether.world.structurepiece.bronzedungeon.BronzeDungeonBuilder;
import com.aetherteam.genesis.advancement.GenesisAdvancementTriggers;
import com.aetherteam.genesis.attachment.GenesisDataAttachments;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.blockentity.GenesisBlockEntityTypes;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.client.particle.GenesisParticleTypes;
import com.aetherteam.genesis.data.generators.*;
import com.aetherteam.genesis.data.generators.tags.GenesisBiomeTagData;
import com.aetherteam.genesis.data.generators.tags.GenesisBlockTagData;
import com.aetherteam.genesis.data.generators.tags.GenesisEntityTagData;
import com.aetherteam.genesis.data.generators.tags.GenesisItemTagData;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.event.listeners.EntityListener;
import com.aetherteam.genesis.event.listeners.LevelListener;
import com.aetherteam.genesis.event.listeners.WeaponAbilityListener;
import com.aetherteam.genesis.event.listeners.abilities.AccessoryAbilityListener;
import com.aetherteam.genesis.event.listeners.abilities.ToolAbilityListener;
import com.aetherteam.genesis.event.listeners.capability.GenesisPlayerListener;
import com.aetherteam.genesis.inventory.menu.GenesisMenuTypes;
import com.aetherteam.genesis.item.GenesisCreativeTabs;
import com.aetherteam.genesis.item.GenesisDataComponents;
import com.aetherteam.genesis.item.GenesisItems;
import com.aetherteam.genesis.loot.functions.GenesisLootFunctions;
import com.aetherteam.genesis.loot.modifiers.GenesisLootModifiers;
import com.aetherteam.genesis.network.packet.GenesisPlayerSyncPacket;
import com.aetherteam.genesis.network.packet.ZephyrColorSyncPacket;
import com.aetherteam.genesis.network.packet.clientbound.TrackingGolemWarningPacket;
import com.aetherteam.genesis.world.GenesisRegion;
import com.aetherteam.genesis.world.feature.GenesisFeatures;
import com.aetherteam.genesis.world.structurepiece.GenesisStructurePieceTypes;
import com.aetherteam.genesis.world.structurepiece.bronzedungeon.GenesisBronzeBossRoom;
import com.aetherteam.genesis.world.structurepiece.bronzedungeon.GenesisBronzeDungeonRoom;
import com.aetherteam.genesis.world.treedecorator.GenesisTreeDecoratorTypes;
import com.aetherteam.genesis.world.trunkplacer.GenesisTrunkPlacerTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.DetectedVersion;
import net.minecraft.SharedConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.util.InclusiveRange;
import net.minecraft.world.flag.FeatureFlagSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import terrablender.api.Regions;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Mod(AetherGenesis.MODID)
public class AetherGenesis {
    public static final String MODID = "aether_genesis";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AetherGenesis(ModContainer mod, IEventBus bus) {
        bus.addListener(this::commonSetup);
        bus.addListener(this::registerPackets);
        bus.addListener(this::dataSetup);
        bus.addListener(this::packSetup);

        bus.addListener(GenesisCreativeTabs::buildCreativeModeTabs);

        GenesisEntityTypes.listen(bus);

//        bus.addListener((ModifyDefaultComponentsEvent event) -> {
//            if (GenesisConfig.COMMON.gold_aercloud_ability.get()) {
//                event.modify(AetherItems.GOLDEN_PARACHUTE, builder -> builder.set(DataComponents.MAX_DAMAGE, 1));
//            }
//        });

        eventSetup(NeoForge.EVENT_BUS);

        DeferredRegister<?>[] registers = {
                GenesisDataComponents.DATA_COMPONENTS,
                GenesisBlocks.BLOCKS,
                GenesisItems.ITEMS,
                GenesisEntityTypes.ENTITY_TYPES,
                GenesisMenuTypes.MENU_TYPES,
                GenesisBlockEntityTypes.BLOCK_ENTITY_TYPES,
                GenesisLootFunctions.LOOT_FUNCTION_TYPES,
                GenesisLootModifiers.GLOBAL_LOOT_MODIFIERS,
                GenesisFeatures.FEATURES,
                GenesisTreeDecoratorTypes.TREE_DECORATORS,
                GenesisTrunkPlacerTypes.TRUNK_PLACERS,
                GenesisParticleTypes.PARTICLES,
                GenesisSoundEvents.SOUNDS,
                GenesisAdvancementTriggers.TRIGGERS,
                GenesisDataAttachments.ATTACHMENTS,
                GenesisStructurePieceTypes.STRUCTURE_PIECE_TYPES
        };

        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }

        mod.registerConfig(ModConfig.Type.STARTUP, GenesisConfig.STARTUP_SPEC);
        mod.registerConfig(ModConfig.Type.COMMON, GenesisConfig.COMMON_SPEC);
        mod.registerConfig(ModConfig.Type.CLIENT, GenesisConfig.CLIENT_SPEC);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            GenesisBlocks.registerPots();
            GenesisBlocks.registerFlammability();

            GenesisItems.registerAccessories();

            Regions.register(new GenesisRegion(ResourceLocation.fromNamespaceAndPath(MODID, MODID), GenesisConfig.COMMON.biome_weight.get()));

            BronzeDungeonBuilder.ROOM_OPTIONS_BUILDER.get("chest_room").add((manager, pos, rot, processors) -> new GenesisBronzeDungeonRoom(manager, "spawner_room", pos, rot, processors), 3);
            BronzeDungeonBuilder.ROOM_OPTIONS_BUILDER.get("chest_room").add((manager, pos, rot, processors) -> new GenesisBronzeDungeonRoom(manager, "spawner_room_pillars", pos, rot, processors), 2);

            BronzeDungeonBuilder.ROOM_OPTIONS_BUILDER.get("boss_room").add((manager, pos, rot, processors) -> new GenesisBronzeBossRoom(manager, "sentry_guardian_boss_room", pos, rot, processors), 6);
            BronzeDungeonBuilder.ROOM_OPTIONS_BUILDER.get("boss_room").add((manager, pos, rot, processors) -> new GenesisBronzeBossRoom(manager, "host_mimic_boss_room", pos, rot, processors), 6);
            BronzeDungeonBuilder.ROOM_OPTIONS_BUILDER.get("boss_room").add((manager, pos, rot, processors) -> new GenesisBronzeBossRoom(manager, "labyrinth_eye_boss_room", pos, rot, processors), 6);
        });
    }

    public void registerPackets(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar(MODID).versioned("1.0.0").optional();

        // CLIENTBOUND
        registrar.playToClient(TrackingGolemWarningPacket.TYPE, TrackingGolemWarningPacket.STREAM_CODEC, TrackingGolemWarningPacket::execute);

        // BOTH
        registrar.playBidirectional(GenesisPlayerSyncPacket.TYPE, GenesisPlayerSyncPacket.STREAM_CODEC, GenesisPlayerSyncPacket::execute);
        registrar.playBidirectional(ZephyrColorSyncPacket.TYPE, ZephyrColorSyncPacket.STREAM_CODEC, ZephyrColorSyncPacket::execute);
    }

    public void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput packOutput = generator.getPackOutput();

        // Client Data
        generator.addProvider(event.includeClient(), new GenesisBlockStateData(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new GenesisItemModelData(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new GenesisLanguageData(packOutput));
        generator.addProvider(event.includeClient(), new GenesisSoundData(packOutput, fileHelper));

        // Server Data
        generator.addProvider(event.includeServer(), new GenesisRegistrySets(packOutput, new AetherRegistrySets(packOutput, lookupProvider).getRegistryProvider()));
        generator.addProvider(event.includeServer(), new GenesisRecipeData(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), GenesisLootTableData.create(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new GenesisLootModifierData(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new GenesisAdvancementData(packOutput, lookupProvider, fileHelper));
        generator.addProvider(event.includeServer(), new GenesisDataMapData(packOutput, lookupProvider));
        GenesisBlockTagData blockTags = new GenesisBlockTagData(packOutput, lookupProvider, fileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new GenesisItemTagData(packOutput, lookupProvider, blockTags.contentsGetter(), fileHelper));
        generator.addProvider(event.includeServer(), new GenesisEntityTagData(packOutput, lookupProvider, fileHelper));
        generator.addProvider(event.includeServer(), new GenesisBiomeTagData(packOutput, lookupProvider, fileHelper));

        // pack.mcmeta
        generator.addProvider(true, new PackMetadataGenerator(packOutput).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.translatable("pack.aether_genesis.mod.description"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
                Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));
    }

    public void packSetup(AddPackFindersEvent event) {
        // Resource Packs
        this.setupClassicPack(event);
        this.setupAltarOverridePack(event);

        // Data Packs
        this.setupDataOverridePack(event);
    }

    /**
     * A built-in resource pack for programmer art.
     */
    private void setupClassicPack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(AetherGenesis.MODID).getFile().findResource("packs/classic");
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("pack.aether_genesis.classic.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
            event.addRepositorySource((source) ->
                    source.accept(new Pack(
                            new PackLocationInfo("builtin/genesis_classic", Component.translatable("pack.aether_genesis.classic.title"), PackSource.BUILT_IN, Optional.empty()),
                            new PathPackResources.PathResourcesSupplier(resourcePath),
                            new Pack.Metadata(metadata.description(), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of(), false),
                            new PackSelectionConfig(false, Pack.Position.TOP, false)
                        )
                    ));
        }
    }

    /**
     * A built-in resource pack for overriding Altar design.
     */
    private void setupAltarOverridePack(AddPackFindersEvent event) {
        if (GenesisConfig.STARTUP.altar_redesign.get() && event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(AetherGenesis.MODID).getFile().findResource("packs/altar_override");
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("pack.aether_genesis.altar_override.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
            event.addRepositorySource((source) ->
                    source.accept(new Pack(
                                    new PackLocationInfo("builtin/genesis_altar_override", Component.translatable("pack.aether_genesis.altar_override.title"), PackSource.BUILT_IN, Optional.empty()),
                                    new PathPackResources.PathResourcesSupplier(resourcePath),
                                    new Pack.Metadata(metadata.description(), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of(), false),
                                    new PackSelectionConfig(true, Pack.Position.TOP, false)
                            )
                    ));
        }
    }

    /**
     * A built-in data pack for overriding some datapack files in the Aether mod.
     */
    private void setupDataOverridePack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            Path resourcePath = ModList.get().getModFileById(AetherGenesis.MODID).getFile().findResource("packs/data_override");
            PackMetadataSection metadata = new PackMetadataSection(Component.literal(""), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
            event.addRepositorySource((source) ->
                    source.accept(new Pack(
                            new PackLocationInfo("builtin/genesis_data_override", Component.literal(""),  PackSource.BUILT_IN,Optional.empty()),
                            new PathPackResources.PathResourcesSupplier(resourcePath),
                            new Pack.Metadata(metadata.description(), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of(), true),
                            new PackSelectionConfig(true, Pack.Position.TOP, false)
                        )
                    ));
        }
    }

    public void eventSetup(IEventBus neoBus) {
        AccessoryAbilityListener.listen(neoBus);
        ToolAbilityListener.listen(neoBus);

        GenesisPlayerListener.listen(neoBus);

        EntityListener.listen(neoBus);
        LevelListener.listen(neoBus);
        WeaponAbilityListener.listen(neoBus);
    }
}