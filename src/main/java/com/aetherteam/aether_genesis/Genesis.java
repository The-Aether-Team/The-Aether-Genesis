package com.aetherteam.aether_genesis;

import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.client.particle.GenesisParticleTypes;
import com.aetherteam.aether_genesis.data.generators.*;
import com.aetherteam.aether_genesis.data.generators.tags.GenesisBiomeTagData;
import com.aetherteam.aether_genesis.data.generators.tags.GenesisBlockTagData;
import com.aetherteam.aether_genesis.data.generators.tags.GenesisEntityTagData;
import com.aetherteam.aether_genesis.data.generators.tags.GenesisItemTagData;
import com.aetherteam.aether_genesis.entity.GenesisEntityTypes;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.aetherteam.aether_genesis.loot.modifiers.GenesisLootModifiers;
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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.slf4j.Logger;

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

        DeferredRegister<?>[] registers = {
                GenesisBlocks.BLOCKS,
                GenesisItems.ITEMS,
                GenesisEntityTypes.ENTITY_TYPES,
                GenesisLootModifiers.GLOBAL_LOOT_MODIFIERS,
                GenesisFoliagePlacerTypes.FOLIAGE_PLACERS,
                GenesisTrunkPlacerTypes.TRUNK_PLACERS,
                GenesisTreeDecoratorTypes.TREE_DECORATORS,
                GenesisParticleTypes.PARTICLES,
                GenesisSoundEvents.SOUNDS
        };

        for (DeferredRegister<?> register : registers) {
            register.register(modEventBus);
        }

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GenesisConfig.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, GenesisConfig.CLIENT_SPEC);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            GenesisBlocks.registerPots();
            GenesisBlocks.registerFlammability();

            this.registerComposting();
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

    private void registerComposting() {
        this.addCompost(0.3F, GenesisBlocks.BLUE_SKYROOT_LEAVES.get().asItem());
        this.addCompost(0.3F, GenesisBlocks.BLUE_SKYROOT_SAPLING.get());
        this.addCompost(0.3F, GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get().asItem());
        this.addCompost(0.3F, GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.get());
        this.addCompost(0.3F, GenesisBlocks.PURPLE_CRYSTAL_LEAVES.get().asItem());
        this.addCompost(0.3F, GenesisBlocks.PURPLE_CRYSTAL_FRUIT_LEAVES.get());
        this.addCompost(0.3F, GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get());
    }

    /**
     * Copy of {@link ComposterBlock#add(float, ItemLike)}.
     * @param chance Chance (as a {@link Float}) to fill a compost layer.
     * @param item The {@link ItemLike} that can be composted.
     */
    private void addCompost(float chance, ItemLike item) {
        ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
    }
}
