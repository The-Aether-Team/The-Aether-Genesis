package com.aetherteam.aether_genesis.loot.functions;

import com.aetherteam.aether_genesis.entity.passive.CarrionSprout;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;

public class CarrionSproutSize extends LootItemConditionalFunction {
    public static final Codec<CarrionSproutSize> CODEC = RecordCodecBuilder.create(instance -> commonFields(instance)
            .and(ConstantValue.CODEC.fieldOf("random_bound").forGetter(carrionSproutSize -> carrionSproutSize.randomBound))
            .and(ConstantValue.CODEC.fieldOf("minimum").forGetter(carrionSproutSize -> carrionSproutSize.minimum))
            .and(ConstantValue.CODEC.fieldOf("maximum").forGetter(carrionSproutSize -> carrionSproutSize.maximum))
            .apply(instance, CarrionSproutSize::new)
    );

    private final ConstantValue randomBound;
    private final ConstantValue minimum;
    private final ConstantValue maximum;

    protected CarrionSproutSize(List<LootItemCondition> conditions, ConstantValue randomBound, ConstantValue minimum, ConstantValue maximum) {
        super(conditions);
        this.randomBound = randomBound;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    /**
     * Determines the drop count of Wyndberries based on a Carrion Sprout's size.
     *
     * @param stack   The {@link ItemStack} for the loot pool.
     * @param context The {@link LootContext}.
     * @return The {@link ItemStack} for the loot pool.
     */
    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity instanceof CarrionSprout carrionSprout && stack.is(GenesisItems.WYNDBERRY.get())) {
            stack.setCount(Math.round(carrionSprout.getSize()) * Mth.clamp(carrionSprout.getRandom().nextInt(this.randomBound.getInt(context)), this.minimum.getInt(context), this.randomBound.getInt(context)));
        }
        return stack;
    }

    public static LootItemConditionalFunction.Builder<?> builder(ConstantValue randomBound, ConstantValue minimum, ConstantValue maximum) {
        return LootItemConditionalFunction.simpleBuilder((lootItemConditions) -> new CarrionSproutSize(lootItemConditions, randomBound, minimum, maximum));
    }

    @Override
    public LootItemFunctionType getType() {
        return GenesisLootFunctions.CARRION_SPROUT_SIZE.get();
    }
}
