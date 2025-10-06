package com.aetherteam.genesis.loot.modifiers;

import com.aetherteam.aether.Aether;
import com.aetherteam.genesis.mixin.mixins.common.accessor.LootTableAccessor;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.Optional;

public class DungeonLootModifier extends LootModifier {
    public static final MapCodec<DungeonLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> codecStart(instance)
            .and(ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("overlapping_loot").forGetter(modifier -> modifier.overlappingLoot))
            .apply(instance, DungeonLootModifier::new));

    public final ResourceKey<LootTable> overlappingLoot;

    public DungeonLootModifier(LootItemCondition[] conditionsIn, ResourceKey<LootTable> overlappingLoot) {
        super(conditionsIn);
        this.overlappingLoot = overlappingLoot;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ServerLevel serverLevel = context.getLevel();
        Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        RandomSource randomSource = context.getRandom();
        LootTable overlappingTable = serverLevel.getServer().reloadableRegistries().getLootTable(this.overlappingLoot);

        generatedLoot = this.unshuffle(generatedLoot);
        ObjectArrayList<ItemStack> bottomList = this.reshuffle(overlappingTable, generatedLoot, randomSource);
        ObjectArrayList<ItemStack> topList = this.generateOverlappingLoot(serverLevel, origin, entity, randomSource, overlappingTable);
        NonNullList<ItemStack> newList = NonNullList.withSize(27, ItemStack.EMPTY);

        for (int i = 0; i < 27; i++) {
            ItemStack bottomStack = bottomList.get(i);
            ItemStack topStack = topList.get(i);
            if (bottomStack.isEmpty() && !topStack.isEmpty()) {
                newList.set(i, topStack);
            } else if (!bottomStack.isEmpty() && topStack.isEmpty()) {
                newList.set(i, bottomStack);
            } else if (!bottomStack.isEmpty() && !topStack.isEmpty()) {
                newList.set(i, randomSource.nextBoolean() ? bottomStack : topStack);
            }
        }
        return new ObjectArrayList<>(newList);
    }

    private ObjectArrayList<ItemStack> generateOverlappingLoot(ServerLevel serverLevel, Vec3 origin, Entity entity, RandomSource randomSource, LootTable overlappingTable) {
        if (origin != null) {
            LootParams.Builder paramsBuilder = new LootParams.Builder(serverLevel).withParameter(LootContextParams.ORIGIN, origin);
            if (entity instanceof Player player) {
                paramsBuilder.withLuck(player.getLuck()).withParameter(LootContextParams.THIS_ENTITY, player);
            }
            ObjectArrayList<ItemStack> list = overlappingTable.getRandomItems(paramsBuilder.create(LootContextParamSets.CHEST), randomSource);
            return this.reshuffle(overlappingTable, list, randomSource);
        }
        return new ObjectArrayList<>();
    }

    private ObjectArrayList<ItemStack> reshuffle(LootTable lootTable, ObjectArrayList<ItemStack> list, RandomSource random) {
        LootTableAccessor accessor = (LootTableAccessor) lootTable;
        accessor.callShuffleAndSplitItems(list, 27 - list.size(), random);
        NonNullList<ItemStack> nonNullList = NonNullList.withSize(27, ItemStack.EMPTY);
        for (int i = 0; i < list.size(); i++) {
            nonNullList.set(i, list.get(i));
        }
        Util.shuffle(nonNullList, random);
        return new ObjectArrayList<>(nonNullList);
    }

    private ObjectArrayList<ItemStack> unshuffle(ObjectArrayList<ItemStack> list) {
        ObjectArrayList<ItemStack> unshuffledList = new ObjectArrayList<>();
        for (ItemStack stack : list) {
            Optional<ItemStack> existingStack = unshuffledList.stream().filter(itemStack -> itemStack.is(stack.getItem())).findFirst();
            if (existingStack.isPresent()) {
                existingStack.get().setCount(existingStack.get().getCount() + stack.getCount());
            } else {
                unshuffledList.add(stack);
            }
        }
        return unshuffledList;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return DungeonLootModifier.CODEC;
    }
}
