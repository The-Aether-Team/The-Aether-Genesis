package com.aetherteam.aether_genesis.item.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class GenesisFoods {
    public static final FoodProperties SWET_JELLY = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final FoodProperties ICESTONE_POPROCKS = new FoodProperties.Builder().fast().nutrition(1).saturationMod(0.2F).build();
    public static final FoodProperties COCOATRICE = new FoodProperties.Builder().nutrition(6).saturationMod(0.2F).build();
    public static final FoodProperties WRAPPED_CHOCOLATES = new FoodProperties.Builder().fast().nutrition(7).saturationMod(0.2F).build();
    public static final FoodProperties BLUEBERRY_LOLLIPOP = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build();
    public static final FoodProperties ORANGE_LOLLIPOP = new FoodProperties.Builder().nutrition(5).saturationMod(0.2F).build();
    public static final FoodProperties STOMPER_POP = new FoodProperties.Builder().nutrition(20).saturationMod(0.2F).build();
    public static final FoodProperties ORANGE = new FoodProperties.Builder().nutrition(3).saturationMod(0.5F).build();
    public static final FoodProperties WYNDBERRY = new FoodProperties.Builder().fast().nutrition(5).saturationMod(0.4F).build();
    public static final FoodProperties JELLY_PUMPKIN = new FoodProperties.Builder().nutrition(5).saturationMod(0.2F).build();
    public static final FoodProperties CANDY_CORN = new FoodProperties.Builder().fast().nutrition(2).saturationMod(0.2F).build();
    public static final FoodProperties RAINBOW_STRAWBERRY = new FoodProperties.Builder().fast().alwaysEat().nutrition(10).saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1), 100)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 200, 1), 100).build();
}
