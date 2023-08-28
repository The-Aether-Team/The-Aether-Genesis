package com.aetherteam.aether_genesis;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.block.advancement.GenesisAdvancementTriggers;
import com.aetherteam.aether_genesis.blockentity.GenesisBlockEntityTypes;
import com.aetherteam.aether_genesis.blockentity.GenesisMenuTypes;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.client.particle.GenesisParticleTypes;
import com.aetherteam.aether_genesis.data.generators.*;
import com.aetherteam.aether_genesis.data.generators.tags.GenesisBiomeTagData;
import com.aetherteam.aether_genesis.data.generators.tags.GenesisBlockTagData;
import com.aetherteam.aether_genesis.data.generators.tags.GenesisEntityTagData;
import com.aetherteam.aether_genesis.data.generators.tags.GenesisItemTagData;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.aetherteam.aether_genesis.loot.functions.GenesisLootFunctions;
import com.aetherteam.aether_genesis.loot.modifiers.GenesisLootModifiers;
import com.aetherteam.aether_genesis.network.GenesisPacketHandler;
import com.aetherteam.aether_genesis.world.biomemodifier.GenesisBiomeModifierSerializers;
import com.aetherteam.aether_genesis.world.feature.GenesisFeatures;
import com.aetherteam.aether_genesis.world.foliageplacer.GenesisFoliagePlacerTypes;
import com.aetherteam.aether_genesis.world.treedecorator.GenesisTreeDecoratorTypes;
import com.aetherteam.aether_genesis.world.trunkplacer.GenesisTrunkPlacerTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.SharedConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.resource.PathPackResources;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Mod(Genesis.MODID)
public class Genesis {
    public static final String MODID = "aether_genesis";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Genesis() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::dataSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::packSetup);

        DeferredRegister<?>[] registers = {
                GenesisBlocks.BLOCKS,
                GenesisItems.ITEMS,
                GenesisEntityTypes.ENTITY_TYPES,
                GenesisMenuTypes.MENU_TYPES,
                GenesisBlockEntityTypes.BLOCK_ENTITY_TYPES,
                GenesisLootFunctions.LOOT_FUNCTION_TYPES,
                GenesisLootModifiers.GLOBAL_LOOT_MODIFIERS,
                GenesisFeatures.FEATURES,
                GenesisFoliagePlacerTypes.FOLIAGE_PLACERS,
                GenesisTrunkPlacerTypes.TRUNK_PLACERS,
                GenesisTreeDecoratorTypes.TREE_DECORATORS,
                GenesisParticleTypes.PARTICLES,
                GenesisSoundEvents.SOUNDS,
                GenesisBiomeModifierSerializers.BIOME_MODIFIER_SERIALIZERS
        };

        for (DeferredRegister<?> register : registers) {
            register.register(modEventBus);
        }

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GenesisConfig.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, GenesisConfig.CLIENT_SPEC);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        GenesisPacketHandler.register();

        GenesisAdvancementTriggers.init();

        event.enqueueWork(() -> {
            GenesisBlocks.registerPots();
            GenesisBlocks.registerFlammability();

            this.registerComposting();
        });
    }

    public void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            if (GenesisConfig.CLIENT.night_music_tracks.get()) {
                AetherConfig.CLIENT.disable_music_manager.set(true);
            }
        });
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
        generator.addProvider(event.includeServer(), new GenesisRecipeData(packOutput));
        generator.addProvider(event.includeServer(), GenesisLootTableData.create(packOutput));
        generator.addProvider(event.includeServer(), new GenesisLootModifierData(packOutput));
        GenesisBlockTagData blockTags = new GenesisBlockTagData(packOutput, lookupProvider, fileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new GenesisItemTagData(packOutput, lookupProvider, blockTags.contentsGetter(), fileHelper));
        generator.addProvider(event.includeServer(), new GenesisEntityTagData(packOutput, lookupProvider, fileHelper));
        generator.addProvider(event.includeServer(), new GenesisBiomeTagData(packOutput, lookupProvider, fileHelper));

        // pack.mcmeta
        PackMetadataGenerator packMeta = new PackMetadataGenerator(packOutput);
        Map<PackType, Integer> packTypes = Map.of(PackType.SERVER_DATA, SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
        packMeta.add(PackMetadataSection.TYPE, new PackMetadataSection(Component.translatable("pack.aether_genesis.mod.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES), packTypes));
        generator.addProvider(true, packMeta);
    }

    public void packSetup(AddPackFindersEvent event) {
        // Resource Packs
        this.setupClassicPack(event);

        // Data Packs
        this.setupDataOverridePack(event);
    }

    private void setupClassicPack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(Genesis.MODID).getFile().findResource("packs/classic");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(Genesis.MODID).getFile().getFileName() + ":" + resourcePath, true, resourcePath);
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("pack.aether_genesis.classic.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create(
                            "builtin/genesis_classic",
                            Component.translatable("pack.aether_genesis.classic.title"),
                            false,
                            (string) -> pack,
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), pack.isHidden()),
                            PackType.CLIENT_RESOURCES,
                            Pack.Position.TOP,
                            false,
                            PackSource.BUILT_IN)
                    ));
        }
    }

    private void setupDataOverridePack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            Path resourcePath = ModList.get().getModFileById(Genesis.MODID).getFile().findResource("packs/data_override");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(Genesis.MODID).getFile().getFileName() + ":" + resourcePath, true, resourcePath);
            PackMetadataSection metadata = new PackMetadataSection(Component.literal(""), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create(
                            "builtin/genesis_data_override",
                            Component.literal(""),
                            true,
                            (string) -> pack,
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), true),
                            PackType.SERVER_DATA,
                            Pack.Position.TOP,
                            false,
                            PackSource.BUILT_IN)
                    )
            );
        }
    }

    private void registerComposting() {
        this.addCompost(0.3F, GenesisBlocks.BLUE_SKYROOT_LEAVES.get().asItem());
        this.addCompost(0.3F, GenesisBlocks.BLUE_SKYROOT_SAPLING.get());
        this.addCompost(0.3F, GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get().asItem());
        this.addCompost(0.3F, GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get());
        this.addCompost(0.3F, GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get().asItem());
        this.addCompost(0.3F, GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get());
        this.addCompost(0.3F, GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get());
    }

    private void addCompost(float chance, ItemLike item) {
        ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
    }
}
