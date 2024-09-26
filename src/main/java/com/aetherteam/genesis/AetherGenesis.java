package com.aetherteam.genesis;

import com.aetherteam.aether.network.packet.clientbound.SetVehiclePacket;
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
import com.aetherteam.genesis.inventory.menu.GenesisMenuTypes;
import com.aetherteam.genesis.item.GenesisItems;
import com.aetherteam.genesis.loot.entries.GenesisLootPoolEntries;
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
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.util.InclusiveRange;
import net.minecraft.world.flag.FeatureFlagSet;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
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

    public AetherGenesis(IEventBus bus, Dist dist) {
        bus.addListener(this::commonSetup);
        bus.addListener(this::registerPackets);
        bus.addListener(this::dataSetup);
        bus.addListener(this::packSetup);

        DeferredRegister<?>[] registers = {
                GenesisBlocks.BLOCKS,
                GenesisItems.ITEMS,
                GenesisEntityTypes.ENTITY_TYPES,
                GenesisMenuTypes.MENU_TYPES,
                GenesisBlockEntityTypes.BLOCK_ENTITY_TYPES,
                GenesisLootPoolEntries.LOOT_POOL_ENTRY_TYPES,
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

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GenesisConfig.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, GenesisConfig.CLIENT_SPEC);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            GenesisBlocks.registerPots();
            GenesisBlocks.registerFlammability();

            Regions.register(new GenesisRegion(new ResourceLocation(MODID, MODID), GenesisConfig.COMMON.biome_weight.get()));

            //todo balance distributions
            BronzeDungeonBuilder.ROOM_OPTIONS_BUILDER.get("chest_room").add((manager, pos, rot, processors) -> new GenesisBronzeDungeonRoom(manager, "spawner_room", pos, rot, processors), 2);
            BronzeDungeonBuilder.ROOM_OPTIONS_BUILDER.get("chest_room").add((manager, pos, rot, processors) -> new GenesisBronzeDungeonRoom(manager, "spawner_room_pillars", pos, rot, processors), 2);
//            BronzeDungeonBuilder.ROOM_OPTIONS_BUILDER.get("boss_room").add((manager, pos, rot, processors) -> new GenesisBronzeBossRoom(manager, "host_mimic_boss_room", pos, rot, processors), 5);
        });
    }

    public void registerPackets(RegisterPayloadHandlerEvent event) {
        IPayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0").optional();

        // CLIENTBOUND
        registrar.play(TrackingGolemWarningPacket.ID, TrackingGolemWarningPacket::decode, payload -> payload.client(TrackingGolemWarningPacket::handle));

        // BOTH
        registrar.play(GenesisPlayerSyncPacket.ID, GenesisPlayerSyncPacket::decode, GenesisPlayerSyncPacket::handle);
        registrar.play(ZephyrColorSyncPacket.ID, ZephyrColorSyncPacket::decode, ZephyrColorSyncPacket::handle);
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
        generator.addProvider(event.includeServer(), new GenesisRegistrySets(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new GenesisRecipeData(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), GenesisLootTableData.create(packOutput));
        generator.addProvider(event.includeServer(), new GenesisLootModifierData(packOutput));
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
                    source.accept(Pack.create(
                            "builtin/genesis_classic",
                            Component.translatable("pack.aether_genesis.classic.title"),
                            false,
                            new PathPackResources.PathResourcesSupplier(resourcePath, true),
                            new Pack.Info(metadata.description(), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of(), false),
                            Pack.Position.TOP,
                            false,
                            PackSource.BUILT_IN)
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
                    source.accept(Pack.create(
                            "builtin/genesis_data_override",
                            Component.literal(""),
                            true,
                            new PathPackResources.PathResourcesSupplier(resourcePath, true),
                            new Pack.Info(metadata.description(), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of(), true),
                            Pack.Position.TOP,
                            false,
                            PackSource.BUILT_IN)
                    )
            );
        }
    }
}