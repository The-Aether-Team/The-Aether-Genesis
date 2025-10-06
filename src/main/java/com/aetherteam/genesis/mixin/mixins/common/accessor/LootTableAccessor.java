package com.aetherteam.genesis.mixin.mixins.common.accessor;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LootTable.class)
public interface LootTableAccessor {
    @Invoker
    void callShuffleAndSplitItems(ObjectArrayList<ItemStack> stacks, int emptySlotsCount, RandomSource random);
}
