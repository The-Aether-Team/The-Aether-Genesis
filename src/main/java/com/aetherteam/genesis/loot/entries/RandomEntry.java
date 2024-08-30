package com.aetherteam.genesis.loot.entries;

import com.google.common.collect.Lists;
import net.minecraft.world.level.storage.loot.entries.ComposableEntryContainer;
import net.minecraft.world.level.storage.loot.entries.CompositeEntryBase;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * [CODE COPY] - {@link net.minecraft.world.level.storage.loot.entries.AlternativesEntry}.
 * Shuffles the entry list for randomization instead of alternatives.
 */
public class RandomEntry extends CompositeEntryBase {
    RandomEntry(List<LootPoolEntryContainer> children, List<LootItemCondition> conditions) {
        super(children, conditions);
    }

    @Override
    public LootPoolEntryType getType() {
        return GenesisLootPoolEntries.RANDOM.get();
    }

    /**
     * Compose the given children into one container.
     */
    @Override
    protected ComposableEntryContainer compose(List<? extends ComposableEntryContainer> entries) {
        return switch (entries.size()) {
            case 0 -> ALWAYS_FALSE;
            case 1 -> entries.get(0);
            default -> (context, entry) -> {
                List<ComposableEntryContainer> entriesList = Lists.newArrayList(entries);
                Collections.shuffle(entriesList);
                for (ComposableEntryContainer container : entriesList) {
                    if (container.expand(context, entry)) {
                        return true;
                    }
                }
                return false;
            };
        };
    }

    public static RandomEntry.Builder random(LootPoolEntryContainer.Builder<?>... children) {
        return new RandomEntry.Builder(children);
    }

    public static <E> RandomEntry.Builder random(Collection<E> childrenSources, Function<E, LootPoolEntryContainer.Builder<?>> toChildrenFunction) {
        return new RandomEntry.Builder(childrenSources.stream().map(toChildrenFunction::apply).toArray(LootPoolEntryContainer.Builder[]::new));
    }

    public static class Builder extends LootPoolEntryContainer.Builder<RandomEntry.Builder> {
        private final List<LootPoolEntryContainer> entries = Lists.newArrayList();

        public Builder(LootPoolEntryContainer.Builder<?>... children) {
            for(LootPoolEntryContainer.Builder<?> builder : children) {
                this.entries.add(builder.build());
            }
        }

        @Override
        protected RandomEntry.Builder getThis() {
            return this;
        }

        public RandomEntry.Builder another(LootPoolEntryContainer.Builder<?> childBuilder) {
            this.entries.add(childBuilder.build());
            return this;
        }

        @Override
        public LootPoolEntryContainer build() {
            return new RandomEntry(this.entries, this.getConditions());
        }
    }
}
