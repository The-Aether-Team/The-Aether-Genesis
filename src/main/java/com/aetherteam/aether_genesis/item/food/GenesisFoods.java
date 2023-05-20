package com.aetherteam.aether_genesis.item.food;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class GenesisFoods {
    public static final FoodProperties SWET_JELLY = new FoodProperties.Builder().fast().nutrition(5).saturationMod(0.2F).build();
    public static final FoodProperties ICESTONE_POPROCKS = new FoodProperties.Builder().fast().nutrition(5).saturationMod(0.2F).build();
    public static final FoodProperties COCOATRICE = new FoodProperties.Builder().fast().nutrition(12).saturationMod(0.48F).build();
    public static final FoodProperties WARPPED_CHOCOLATES = new FoodProperties.Builder().fast().nutrition(12).saturationMod(0.48F).build();
    public static final FoodProperties BLUEBERRY_LOLLIPOP = new FoodProperties.Builder().fast().nutrition(10).saturationMod(0.4F).build();
    public static final FoodProperties ORANGE_LOLLIPOP = new FoodProperties.Builder().fast().nutrition(8).saturationMod(0.32F).build();
    public static final FoodProperties STOMPER_POP = new FoodProperties.Builder().fast().nutrition(20).saturationMod(0.8F).build();
    public static final FoodProperties ORANGE = new FoodProperties.Builder().fast().nutrition(4).saturationMod(0.16F).build();
    public static final FoodProperties WYNDBERRY = new FoodProperties.Builder().fast().nutrition(4).saturationMod(0.16F).build();
    public static final FoodProperties JELLY_PUMPKIN = new FoodProperties.Builder().fast().nutrition(12).saturationMod(0.48F).build();
    public static final FoodProperties CANDY_CORN = new FoodProperties.Builder().fast().nutrition(10).saturationMod(0.4F).build();
    public static final FoodProperties RAINBOW_STRAWBERRY = new FoodProperties.Builder().fast().nutrition(8).saturationMod(0.32F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1), 100).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 200, 1), 100).build();
}
