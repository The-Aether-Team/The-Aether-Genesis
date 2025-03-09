package com.aetherteam.genesis.data.generators;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.data.generators.AetherAdvancementData;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.GenesisTags;
import com.aetherteam.genesis.advancement.ContinuumOrbLootTrigger;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GenesisAdvancementData extends AdvancementProvider {
    public GenesisAdvancementData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper helper) {
        super(output, registries, helper, List.of(new GenesisAdvancements()));
    }

    public static class GenesisAdvancements implements AdvancementGenerator {
        @SuppressWarnings("unused")
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
            AdvancementHolder continuumOrb = Advancement.Builder.advancement()
                    .parent(ResourceLocation.fromNamespaceAndPath(Aether.MODID, "enchanted_gravitite"))
                    .display(GenesisItems.CONTINUUM_ORB.get(),
                            Component.translatable("advancement.aether_genesis.continuum_orb"),
                            Component.translatable("advancement.aether_genesis.continuum_orb.desc"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("continuum_orb", ContinuumOrbLootTrigger.Instance.forAny())
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "continuum_orb"), existingFileHelper);

            AdvancementHolder companion = Advancement.Builder.advancement()
                    .parent(ResourceLocation.fromNamespaceAndPath(Aether.MODID, "bronze_dungeon"))
                    .display(GenesisItems.BABY_PINK_SWET.get(),
                            Component.translatable("advancement.aether_genesis.companion"),
                            Component.translatable("advancement.aether_genesis.companion.desc"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("companion", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(GenesisTags.Items.COMPANIONS)))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "companion"), existingFileHelper);

            AdvancementHolder sentryGuardian = Advancement.Builder.advancement()
                    .parent(ResourceLocation.fromNamespaceAndPath(Aether.MODID, "bronze_dungeon"))
                    .display(GenesisItems.GUARDIAN_KEY.get(),
                            Component.translatable("advancement.aether_genesis.sentry_guardian"),
                            Component.translatable("advancement.aether_genesis.sentry_guardian.desc"),
                            null,
                            AdvancementType.GOAL, true, true, false)
                    .addCriterion("kill_sentry_guardian", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(GenesisEntityTypes.SENTRY_GUARDIAN.get())))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "sentry_guardian"), existingFileHelper);
            AdvancementHolder sliderHostMimic = Advancement.Builder.advancement()
                    .parent(ResourceLocation.fromNamespaceAndPath(Aether.MODID, "bronze_dungeon"))
                    .display(GenesisItems.HOST_KEY.get(),
                            Component.translatable("advancement.aether_genesis.slider_host_mimic"),
                            Component.translatable("advancement.aether_genesis.slider_host_mimic.desc"),
                            null,
                            AdvancementType.GOAL, true, true, false)
                    .addCriterion("kill_slider_host_mimic", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(GenesisEntityTypes.SLIDER_HOST_MIMIC.get())))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "slider_host_mimic"), existingFileHelper);
            AdvancementHolder labyrinthEye = Advancement.Builder.advancement()
                    .parent(ResourceLocation.fromNamespaceAndPath(Aether.MODID, "bronze_dungeon"))
                    .display(GenesisItems.COG_KEY.get(),
                            Component.translatable("advancement.aether_genesis.labyrinth_eye"),
                            Component.translatable("advancement.aether_genesis.labyrinth_eye.desc"),
                            null,
                            AdvancementType.GOAL, true, true, false)
                    .addCriterion("kill_labyrinth_eye", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(GenesisEntityTypes.LABYRINTH_EYE.get())))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "labyrinth_eye"), existingFileHelper);
        }
    }
}
