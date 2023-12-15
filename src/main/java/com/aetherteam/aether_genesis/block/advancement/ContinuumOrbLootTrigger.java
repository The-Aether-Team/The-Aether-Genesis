package com.aetherteam.aether_genesis.block.advancement;

import com.aetherteam.aether.Aether;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class ContinuumOrbLootTrigger extends SimpleCriterionTrigger<ContinuumOrbLootTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(Aether.MODID, "continuum_orb_loot");
    public static final ContinuumOrbLootTrigger INSTANCE = new ContinuumOrbLootTrigger();

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public ContinuumOrbLootTrigger.Instance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext context) {
        ItemPredicate itemPredicate = ItemPredicate.fromJson(json.get("item"));
        return new ContinuumOrbLootTrigger.Instance(entity, itemPredicate);
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

    public static class Instance extends AbstractCriterionTriggerInstance {
        private final ItemPredicate item;

        public Instance(ContextAwarePredicate predicate, ItemPredicate item) {
            super(ContinuumOrbLootTrigger.ID, predicate);
            this.item = item;
        }

        public static ContinuumOrbLootTrigger.Instance forItem(ItemPredicate item) {
            return new ContinuumOrbLootTrigger.Instance(ContextAwarePredicate.ANY, item);
        }

        public static ContinuumOrbLootTrigger.Instance forItem(ItemLike item) {
            return forItem(ItemPredicate.Builder.item().of(item).build());
        }

        public static ContinuumOrbLootTrigger.Instance forAny() {
            return forItem(ItemPredicate.ANY);
        }

        public boolean test(ItemStack stack) {
            return this.item.matches(stack);
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("item", this.item.serializeToJson());
            return jsonObject;
        }
    }
}

