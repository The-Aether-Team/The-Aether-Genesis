package com.aetherteam.aether_genesis.loot.modifiers;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.util.EquipmentUtil;
import com.aetherteam.aether_genesis.GenesisTags;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.aetherteam.aether_genesis.mixin.mixins.common.accessor.MobAccessor;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class PresentDropsModifier extends LootModifier {
    public static final Codec<PresentDropsModifier> CODEC = RecordCodecBuilder.create((instance) -> LootModifier.codecStart(instance).apply(instance, PresentDropsModifier::new));

    public PresentDropsModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> lootStacks, LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.DIRECT_KILLER_ENTITY);
        Entity target = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        ObjectArrayList<ItemStack> newStacks = new ObjectArrayList<>(lootStacks);
        if (entity instanceof LivingEntity livingEntity && target instanceof Mob mobTarget) {
            if (EquipmentUtil.isFullStrength(livingEntity) && EquipmentUtil.hasCurio(livingEntity, GenesisItems.LUCKY_BELL.get()) && ((MobAccessor) mobTarget).callShouldDespawnInPeaceful() && !mobTarget.getType().is(GenesisTags.Entities.NO_PRESENT_DROPS) && mobTarget.getRandom().nextInt(5) == 0) {
                newStacks.add(new ItemStack(AetherBlocks.PRESENT.get()));
            }
        }
        return newStacks;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return PresentDropsModifier.CODEC;
    }
}