package com.aetherteam.aether_genesis.loot.functions;

import com.aetherteam.aether.loot.functions.AetherLootFunctions;
import com.aetherteam.aether_genesis.entity.passive.CarrionSprout;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

public class CarrionSproutSize extends LootItemConditionalFunction {
    private final NumberProvider randomBound;
    private final NumberProvider minimum;
    private final NumberProvider maximum;

    protected CarrionSproutSize(LootItemCondition[] conditions, NumberProvider randomBound, NumberProvider minimum, NumberProvider maximum) {
        super(conditions);
        this.randomBound = randomBound;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity instanceof CarrionSprout carrionSprout && stack.is(GenesisItems.WYNDBERRY.get())) {
            stack.setCount(Math.round(carrionSprout.getSize()) * Mth.clamp(carrionSprout.getRandom().nextInt(this.randomBound.getInt(context)), this.minimum.getInt(context), this.randomBound.getInt(context)));
        }
        return stack;
    }

    public static CarrionSproutSize.Builder setAmount(NumberProvider randomBound, NumberProvider minimum, NumberProvider maximum) {
        return new CarrionSproutSize.Builder(randomBound, minimum, maximum);
    }

    @Override
    public LootItemFunctionType getType() {
        return GenesisLootFunctions.CARRION_SPROUT_SIZE.get();
    }

    public static class Builder extends LootItemConditionalFunction.Builder<CarrionSproutSize.Builder> {
        private NumberProvider randomBound;
        private NumberProvider minimum;
        private NumberProvider maximum;

        public Builder(NumberProvider randomBound, NumberProvider minimum, NumberProvider maximum) {
            this.randomBound = randomBound;
            this.minimum = minimum;
            this.maximum = maximum;
        }

        @Override
        protected CarrionSproutSize.Builder getThis() {
            return this;
        }

        public void setRandomBound(NumberProvider randomBound) {
            this.randomBound = randomBound;
        }

        public void setMinimum(NumberProvider minimum) {
            this.minimum = minimum;
        }

        public void setMaximum(NumberProvider maximum) {
            this.maximum = maximum;
        }

        @Override
        public LootItemFunction build() {
            return new CarrionSproutSize(this.getConditions(), this.randomBound, this.minimum, this.maximum);
        }
    }

    public static class Serializer extends LootItemConditionalFunction.Serializer<CarrionSproutSize> {
        @Override
        public void serialize(JsonObject json, CarrionSproutSize instance, JsonSerializationContext context) {
            super.serialize(json, instance, context);
            json.add("randomBound", context.serialize(instance.randomBound));
            json.add("minimum", context.serialize(instance.minimum));
            json.add("maximum", context.serialize(instance.maximum));
        }

        @Override
        public CarrionSproutSize deserialize(JsonObject json, JsonDeserializationContext context, LootItemCondition[] conditions) {
            NumberProvider randomBound = GsonHelper.getAsObject(json, "randomBound", context, NumberProvider.class);
            NumberProvider minimum = GsonHelper.getAsObject(json, "minimum", context, NumberProvider.class);
            NumberProvider maximum = GsonHelper.getAsObject(json, "maximum", context, NumberProvider.class);
            return new CarrionSproutSize(conditions, randomBound, minimum, maximum);
        }
    }
}
