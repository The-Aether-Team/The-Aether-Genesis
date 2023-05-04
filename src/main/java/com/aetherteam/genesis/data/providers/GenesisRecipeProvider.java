package com.aetherteam.genesis.data.providers;

import com.aetherteam.aether.data.providers.AetherRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public abstract class GenesisRecipeProvider extends AetherRecipeProvider {
    public GenesisRecipeProvider(PackOutput output, String id) {
        super(output, id);
    }

    protected static ShapelessRecipeBuilder makeSwetJelly(Supplier<? extends Item> jelly, Supplier<? extends Item> ball) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, jelly.get(), 1)
                .requires(ball.get())
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(ball.get()), has(ball.get()));
    }
}
