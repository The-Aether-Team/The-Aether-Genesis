package com.aetherteam.genesis.data.generators;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.GenesisTags;
import com.aetherteam.genesis.advancement.ContinuumOrbLootTrigger;
import com.aetherteam.genesis.advancement.NexReviveTrigger;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
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
            AdvancementHolder killZephyroo = Advancement.Builder.advancement()
                    .parent(Advancement.Builder.advancement().build(ResourceLocation.fromNamespaceAndPath(Aether.MODID, "enchanted_gravitite")))
                    .display(AetherItems.GRAVITITE_SWORD,
                            Component.translatable("advancement.aether_genesis.kill_zephyroo"),
                            Component.translatable("advancement.aether_genesis.kill_zephyroo.desc"),
                            null,
                            AdvancementType.CHALLENGE, true, true, true)
                    .addCriterion("kill_zephyroo", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(GenesisEntityTypes.ZEPHYROO.get()), DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(DamageTypeTags.IS_EXPLOSION))))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "kill_zephyroo"), existingFileHelper);
            AdvancementHolder killTempest = Advancement.Builder.advancement()
                    .parent(Advancement.Builder.advancement().build(ResourceLocation.fromNamespaceAndPath(Aether.MODID, "enchanted_gravitite")))
                    .display(GenesisBlocks.STORM_AERCLOUD.get(),
                            Component.translatable("advancement.aether_genesis.kill_tempest"),
                            Component.translatable("advancement.aether_genesis.kill_tempest.desc"),
                            null,
                            AdvancementType.CHALLENGE, true, true, true)
                    .addCriterion("kill_tempest", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(GenesisEntityTypes.TEMPEST.get()), DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(DamageTypeTags.IS_PROJECTILE)).direct(EntityPredicate.Builder.entity().of(GenesisEntityTypes.TEMPEST_THUNDERBALL.get()))))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "kill_tempest"), existingFileHelper);

            AdvancementHolder continuumOrb = Advancement.Builder.advancement()
                    .parent(Advancement.Builder.advancement().build(ResourceLocation.fromNamespaceAndPath(Aether.MODID, "enter_aether")))
                    .display(GenesisItems.CONTINUUM_ORB.get(),
                            Component.translatable("advancement.aether_genesis.continuum_orb"),
                            Component.translatable("advancement.aether_genesis.continuum_orb.desc"),
                            null,
                            AdvancementType.TASK, true, true, true)
                    .addCriterion("continuum_orb", ContinuumOrbLootTrigger.Instance.forAny())
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "continuum_orb"), existingFileHelper);
            AdvancementHolder continuumBomb = Advancement.Builder.advancement()
                    .parent(continuumOrb)
                    .display(GenesisItems.CONTINUUM_BOMB.get(),
                            Component.translatable("advancement.aether_genesis.continuum_bomb"),
                            Component.translatable("advancement.aether_genesis.continuum_bomb.desc"),
                            null,
                            AdvancementType.GOAL, true, true, false)
                    .addCriterion("continuum_bomb", ConsumeItemTrigger.TriggerInstance.usedItem(GenesisItems.CONTINUUM_BOMB.get()))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "continuum_bomb"), existingFileHelper);

            AdvancementHolder sentryGuardian = Advancement.Builder.advancement()
                    .parent(Advancement.Builder.advancement().build(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "slider_host_mimic")))
                    .display(GenesisItems.GUARDIAN_KEY.get(),
                            Component.translatable("advancement.aether_genesis.sentry_guardian"),
                            Component.translatable("advancement.aether_genesis.sentry_guardian.desc"),
                            null,
                            AdvancementType.GOAL, true, true, false)
                    .addCriterion("kill_sentry_guardian", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(GenesisEntityTypes.SENTRY_GUARDIAN.get())))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "sentry_guardian"), existingFileHelper);
            AdvancementHolder sliderHostMimic = Advancement.Builder.advancement()
                    .parent(Advancement.Builder.advancement().build(ResourceLocation.fromNamespaceAndPath(Aether.MODID, "bronze_dungeon")))
                    .display(GenesisItems.HOST_KEY.get(),
                            Component.translatable("advancement.aether_genesis.slider_host_mimic"),
                            Component.translatable("advancement.aether_genesis.slider_host_mimic.desc"),
                            null,
                            AdvancementType.GOAL, true, true, false)
                    .addCriterion("kill_slider_host_mimic", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(GenesisEntityTypes.SLIDER_HOST_MIMIC.get())))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "slider_host_mimic"), existingFileHelper);
            AdvancementHolder labyrinthEye = Advancement.Builder.advancement()
                    .parent(Advancement.Builder.advancement().build(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "sentry_guardian")))
                    .display(GenesisItems.COG_KEY.get(),
                            Component.translatable("advancement.aether_genesis.labyrinth_eye"),
                            Component.translatable("advancement.aether_genesis.labyrinth_eye.desc"),
                            null,
                            AdvancementType.GOAL, true, true, false)
                    .addCriterion("kill_labyrinth_eye", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(GenesisEntityTypes.LABYRINTH_EYE.get())))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "labyrinth_eye"), existingFileHelper);

            AdvancementHolder mouseEars = Advancement.Builder.advancement()
                    .parent(Advancement.Builder.advancement().build(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "labyrinth_eye")))
                    .display(GenesisItems.MOUSE_EAR_CAP.get(),
                            Component.translatable("advancement.aether_genesis.mouse_ears"),
                            Component.translatable("advancement.aether_genesis.mouse_ears.desc"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("mouse_ears", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.MOUSE_EAR_CAP))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "mouse_ears"), existingFileHelper);

            AdvancementHolder candies = Advancement.Builder.advancement()
                    .parent(Advancement.Builder.advancement().build(ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "labyrinth_eye")))
                    .display(GenesisItems.CANDY_CORN.get(),
                            Component.translatable("advancement.aether_genesis.candies"),
                            Component.translatable("advancement.aether_genesis.candies.desc"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("blue_swet_jelly", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.BLUE_SWET_JELLY))
                    .addCriterion("golden_swet_jelly", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.GOLDEN_SWET_JELLY))
                    .addCriterion("dark_swet_jelly", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.DARK_SWET_JELLY))
                    .addCriterion("blue_gummy_swet", InventoryChangeTrigger.TriggerInstance.hasItems(AetherItems.BLUE_GUMMY_SWET))
                    .addCriterion("golden_gummy_swet", InventoryChangeTrigger.TriggerInstance.hasItems(AetherItems.GOLDEN_GUMMY_SWET))
                    .addCriterion("dark_gummy_swet", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.DARK_GUMMY_SWET))
                    .addCriterion("icestone_poprocks", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.ICESTONE_POPROCKS))
                    .addCriterion("blueberry_lollipop", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.BLUEBERRY_LOLLIPOP))
                    .addCriterion("orange_lollipop", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.ORANGE_LOLLIPOP))
                    .addCriterion("stomper_pop", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.STOMPER_POP))
                    .addCriterion("jelly_pumpkin", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.JELLY_PUMPKIN))
                    .addCriterion("cocoatrice", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.COCOATRICE))
                    .addCriterion("wrapped_chocolates", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.WRAPPED_CHOCOLATES))
                    .addCriterion("candy_corn", InventoryChangeTrigger.TriggerInstance.hasItems(GenesisItems.CANDY_CORN))
                    .addCriterion("ginger_bread_man", InventoryChangeTrigger.TriggerInstance.hasItems(AetherItems.GINGERBREAD_MAN))
                    .addCriterion("candy_cane", InventoryChangeTrigger.TriggerInstance.hasItems(AetherItems.CANDY_CANE))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "candies"), existingFileHelper);

            AdvancementHolder companion = Advancement.Builder.advancement()
                    .parent(Advancement.Builder.advancement().build(ResourceLocation.fromNamespaceAndPath(Aether.MODID, "obtain_petal")))
                    .display(GenesisItems.BABY_PINK_SWET.get(),
                            Component.translatable("advancement.aether_genesis.companion"),
                            Component.translatable("advancement.aether_genesis.companion.desc"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("companion", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(GenesisTags.Items.COMPANIONS)))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "companion"), existingFileHelper);
            AdvancementHolder nexSpirit = Advancement.Builder.advancement()
                    .parent(companion)
                    .display(GenesisItems.DEATH_SEAL.get(),
                            Component.translatable("advancement.aether_genesis.nex_spirit"),
                            Component.translatable("advancement.aether_genesis.nex_spirit.desc"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("resurrect", NexReviveTrigger.Instance.create())
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "nex_spirit"), existingFileHelper);
        }
    }
}
