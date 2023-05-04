package com.aetherteam.genesis.data.generators;

import com.aetherteam.genesis.Genesis;
import com.aetherteam.genesis.item.GenesisItems;
import com.aetherteam.genesis.loot.modifiers.AddEntityDropsModifier;
import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.entity.AetherEntityTypes;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

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
    }
}
