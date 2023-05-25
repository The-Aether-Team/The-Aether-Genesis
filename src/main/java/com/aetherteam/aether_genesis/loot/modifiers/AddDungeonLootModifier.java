package com.aetherteam.aether_genesis.loot.modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import java.util.*;

public class AddDungeonLootModifier extends LootModifier {
    public static final Codec<AddDungeonLootModifier> CODEC = RecordCodecBuilder.create(instance -> codecStart(instance)
            .and(WeightedEntry.Wrapper.codec(ItemStack.CODEC).listOf().fieldOf("entries").forGetter(modifier -> modifier.entries))
            .and(IntProvider.CODEC.fieldOf("rolls").forGetter(modifier -> modifier.rolls))
            .apply(instance, AddDungeonLootModifier::new));

    public final List<WeightedEntry.Wrapper<ItemStack>> entries;
    public final IntProvider rolls;

    public AddDungeonLootModifier(LootItemCondition[] conditionsIn, List<WeightedEntry.Wrapper<ItemStack>> entries, IntProvider rolls) {
        super(conditionsIn);
        this.entries = entries;
        this.rolls = rolls;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        RandomSource randomSource = context.getRandom();
        Vec3 vec3 = context.getParamOrNull(LootContextParams.ORIGIN);
        if (vec3 != null) {
            BlockPos pos = BlockPos.containing(vec3);
            BlockEntity blockEntity = context.getLevel().getBlockEntity(pos);
            if (blockEntity instanceof BaseContainerBlockEntity containerBlockEntity) {
                int rollCount = this.rolls.sample(randomSource);
                for (int i = 0; i < rollCount; i++) {
                    boolean isFull = generatedLoot.size() == containerBlockEntity.getContainerSize();
                    if (!isFull) {
                        int weight = this.entries.stream().map(entry -> entry.getWeight().asInt()).reduce(0, Integer::sum);
                        WeightedRandom.getRandomItem(randomSource, this.entries, weight).ifPresent(e -> generatedLoot.add(e.getData()));
                    }
                }
            }
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return AddDungeonLootModifier.CODEC;
    }
}
