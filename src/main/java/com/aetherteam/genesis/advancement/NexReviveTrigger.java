package com.aetherteam.genesis.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class NexReviveTrigger extends SimpleCriterionTrigger<NexReviveTrigger.Instance> {
    @Override
    public Codec<NexReviveTrigger.Instance> codec() {
        return NexReviveTrigger.Instance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, (instance) -> true);
    }

    public record Instance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<NexReviveTrigger.Instance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(NexReviveTrigger.Instance::player)
                ).apply(instance, NexReviveTrigger.Instance::new));

        public static Criterion<NexReviveTrigger.Instance> create() {
            return GenesisAdvancementTriggers.NEX_REVIVE.get().createCriterion(new NexReviveTrigger.Instance(Optional.empty()));
        }
    }
}
