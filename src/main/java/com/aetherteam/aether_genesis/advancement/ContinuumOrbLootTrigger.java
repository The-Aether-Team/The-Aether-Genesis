package com.aetherteam.aether_genesis.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public class ContinuumOrbLootTrigger extends SimpleCriterionTrigger<ContinuumOrbLootTrigger.Instance> {
    @Override
    public Codec<ContinuumOrbLootTrigger.Instance> codec() {
        return ContinuumOrbLootTrigger.Instance.CODEC;
    }

    public void trigger(ServerPlayer player, ItemStack stack) {
        this.trigger(player, (instance) -> instance.test(stack));
    }

    public void trigger(ServerPlayer player, List<ItemStack> stacks) {
        this.trigger(player, (instance) -> {
            for (ItemStack itemStack : stacks) {
                if (instance.test(itemStack)) {
                    return true;
                }
            }
            return false;
        });
    }

    public record Instance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> item) implements SimpleInstance {
        public static final Codec<ContinuumOrbLootTrigger.Instance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(ContinuumOrbLootTrigger.Instance::player),
                        ExtraCodecs.strictOptionalField(ItemPredicate.CODEC, "item").forGetter(ContinuumOrbLootTrigger.Instance::item))
                .apply(instance, ContinuumOrbLootTrigger.Instance::new));

        public static Criterion<ContinuumOrbLootTrigger.Instance> forItem(ItemPredicate item) {
            return GenesisAdvancementTriggers.CONTINUUM_ORB.get().createCriterion(new ContinuumOrbLootTrigger.Instance(Optional.empty(), Optional.of(item)));
        }

        public boolean test(ItemStack stack) {
            return this.item.isEmpty() || this.item.get().matches(stack);
        }
    }
}

