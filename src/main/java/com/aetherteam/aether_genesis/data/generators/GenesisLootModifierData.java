package com.aetherteam.aether_genesis.data.generators;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.loot.AetherLoot;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.aetherteam.aether_genesis.loot.modifiers.ChanceDoubleDropsModifier;
import com.aetherteam.aether_genesis.loot.modifiers.PresentDropsModifier;
import com.aetherteam.nitrogen.loot.modifiers.AddDungeonLootModifier;
import com.aetherteam.nitrogen.loot.modifiers.AddEntityDropsModifier;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.List;

public class GenesisLootModifierData extends GlobalLootModifierProvider {
    public GenesisLootModifierData(PackOutput output) {
        super(output, Genesis.MODID);
    }

    @Override
    protected void start() {
        this.add("golden_swet_ball", new AddEntityDropsModifier(
                new ItemStack(GenesisItems.GOLDEN_SWET_BALL.get()),
                new LootItemFunction[] {
                        SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)).build(),
                        LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)).build()
                },
                new LootItemCondition[] {
                        LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(AetherEntityTypes.GOLDEN_SWET.get())).build()
                })
        );
        this.add("swet_sugar", new AddEntityDropsModifier(
                new ItemStack(Items.SUGAR),
                new LootItemFunction[] {
                        SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)).build(),
                        LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)).build()
                },
                new LootItemCondition[] {
                        LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(AetherTags.Entities.SWETS)).build()
                })
        );

        this.add("chance_double_drops", new ChanceDoubleDropsModifier(new LootItemCondition[]{ }));
        this.add("present_drops", new PresentDropsModifier(new LootItemCondition[]{ }));

        this.add("bronze_dark_gummy_swet", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.DARK_GUMMY_SWET.get()), 3)),
                UniformInt.of(1, 1))
        );
        this.add("silver_dark_gummy_swet", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.DARK_GUMMY_SWET.get()), 4)),
                UniformInt.of(1, 4))
        );

        this.add("frostpine_totem", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.FROSTPINE_TOTEM.get()), 4)),
                UniformInt.of(1, 1))
        );
        this.add("dexterity_cape", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.DEXTERITY_CAPE.get()), 4)),
                UniformInt.of(1, 1))
        );
        this.add("fangrin_capsule", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.FANGRIN_CAPSULE.get()), 4)),
                UniformInt.of(1, 1))
        );
        this.add("kraisith_capsule", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.KRAISITH_CAPSULE.get()), 4)),
                UniformInt.of(1, 1))
        );
        this.add("soaring_stone", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.SOARING_STONE.get()), 4)),
                UniformInt.of(1, 1))
        );
        this.add("fleeting_stone", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.FLEETING_STONE.get()), 4)),
                UniformInt.of(1, 1))
        );
        this.add("baby_pink_swet", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.BABY_PINK_SWET.get()), 4)),
                UniformInt.of(1, 1))
        );
        this.add("mouse_ear_cap", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.MOUSE_EAR_CAP.get()), 1)),
                UniformInt.of(1, 1))
        );
        this.add("music_disc_demise", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.MUSIC_DISC_DEMISE.get()), 1)),
                UniformInt.of(1, 1))
        );
        this.add("bronze_candy_corn", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.CANDY_CORN.get()), 1)),
                UniformInt.of(1, 5))
        );
        this.add("bronze_cornstarch_bowl", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.CORNSTARCH_BOWL.get()), 1)),
                UniformInt.of(1, 2))
        );

        this.add("phoenix_dart_shooter", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.PHOENIX_DART_SHOOTER.get()), 2)),
                UniformInt.of(1, 1))
        );
        this.add("skyroot_ring", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.SKYROOT_RING.get()), 4)),
                UniformInt.of(1, 1))
        );
        this.add("swetty_pendant", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.SWETTY_PENDANT.get()), 2)),
                UniformInt.of(1, 1))
        );
        this.add("lucky_bell", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.LUCKY_BELL.get()), 2)),
                UniformInt.of(1, 1))
        );
        this.add("frostbound_stone", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.FROSTBOUND_STONE.get()), 1)),
                UniformInt.of(1, 1))
        );
        this.add("orb_of_arkenzus", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.FROSTBOUND_STONE.get()), 1)),
                UniformInt.of(1, 1))
        );
        this.add("ethereal_stone", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.ETHEREAL_STONE.get()), 1)),
                UniformInt.of(1, 1))
        );
        this.add("crystal_experience_bottle", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.CRYSTAL_EXPERIENCE_BOTTLE.get()), 4)),
                UniformInt.of(1, 1))
        );
        this.add("music_disc_approaches", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.MUSIC_DISC_APPROACHES.get()), 1)),
                UniformInt.of(1, 1))
        );
        this.add("silver_candy_corn", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.CANDY_CORN.get()), 1)),
                UniformInt.of(1, 7))
        );
        this.add("silver_cornstarch_bowl", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.CORNSTARCH_BOWL.get()), 1)),
                UniformInt.of(1, 4))
        );

        this.add("bone_ring", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.GOLD_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.BONE_RING.get()), 12)),
                UniformInt.of(1, 1))
        );
        this.add("candy_ring", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.GOLD_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.CANDY_RING.get()), 12)),
                UniformInt.of(1, 1))
        );
        this.add("daggerfrost_locket", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.GOLD_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.DAGGERFROST_LOCKET.get()), 12)),
                UniformInt.of(1, 1))
        );
        this.add("death_seal", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.GOLD_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.DAGGERFROST_LOCKET.get()), 2)),
                UniformInt.of(1, 1))
        );
        this.add("recording_892", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.GOLD_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.RECORDING_892.get()), 1)),
                UniformInt.of(1, 1))
        );
        this.add("continuum_bomb", new AddDungeonLootModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(AetherLoot.GOLD_DUNGEON_REWARD).build()
                },
                List.of(WeightedEntry.wrap(new ItemStack(GenesisItems.CONTINUUM_BOMB.get()), 8)),
                UniformInt.of(1, 1))
        );
    }
}
