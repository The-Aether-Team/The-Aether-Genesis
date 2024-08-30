package com.aetherteam.genesis.data.generators;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.GenesisTags;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.data.providers.GenesisRecipeProvider;
import com.aetherteam.genesis.item.GenesisItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class GenesisRecipeData extends GenesisRecipeProvider {
    public GenesisRecipeData(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, AetherGenesis.MODID);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        makeSwetJelly(GenesisItems.BLUE_SWET_JELLY, AetherItems.SWET_BALL).save(consumer);
        makeSwetJelly(GenesisItems.GOLDEN_SWET_JELLY, GenesisItems.GOLDEN_SWET_BALL).save(consumer);
        makeSwetJelly(GenesisItems.DARK_SWET_JELLY, GenesisItems.DARK_SWET_BALL).save(consumer);
        twoByTwoPacker(consumer, RecipeCategory.TOOLS, GenesisItems.BLUE_PARACHUTE.get(), AetherBlocks.BLUE_AERCLOUD.get());
        twoByTwoPacker(consumer, RecipeCategory.TOOLS, GenesisItems.GREEN_PARACHUTE.get(), GenesisBlocks.GREEN_AERCLOUD.get());
        twoByTwoPacker(consumer, RecipeCategory.TOOLS, GenesisItems.PURPLE_PARACHUTE.get(), GenesisBlocks.PURPLE_AERCLOUD.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, GenesisItems.CAPE.get())
                .define('#', ItemTags.WOOL)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_wool", has(ItemTags.WOOL)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenesisBlocks.HOLYSTONE_HEADSTONE.get(), 9).define('#', AetherBlocks.HOLYSTONE_BRICKS.get()).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenesisBlocks.HOLYSTONE_KEYSTONE.get(), 9).define('#', AetherBlocks.HOLYSTONE_BRICKS.get()).define('H', AetherBlocks.HOLYSTONE.get()).pattern("###").pattern("###").pattern("HHH").unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenesisBlocks.HOLYSTONE_HIGHLIGHT.get(), 9).define('#', AetherBlocks.HOLYSTONE_BRICKS.get()).define('H', AetherBlocks.HOLYSTONE.get()).pattern("###").pattern("H#H").pattern("H#H").unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GenesisItems.ICESTONE_POPROCKS.get(), 3).requires(AetherBlocks.ICESTONE.get()).requires(Items.SUGAR).unlockedBy(getHasName(AetherBlocks.ICESTONE.get()), has(AetherBlocks.ICESTONE.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GenesisItems.BLUEBERRY_LOLLIPOP.get()).requires(AetherItems.BLUE_BERRY.get()).requires(Items.SUGAR).requires(AetherItems.SKYROOT_STICK.get()).unlockedBy(getHasName(AetherItems.BLUE_BERRY.get()), has(AetherItems.BLUE_BERRY.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GenesisItems.ORANGE_LOLLIPOP.get()).requires(GenesisItems.ORANGE.get()).requires(Items.SUGAR).requires(AetherItems.SKYROOT_STICK.get()).unlockedBy(getHasName(GenesisItems.ORANGE.get()), has(GenesisItems.ORANGE.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GenesisItems.STOMPER_POP.get()).requires(GenesisItems.BABY_PINK_SWET.get()).requires(AetherItems.SKYROOT_STICK.get()).unlockedBy(getHasName(GenesisItems.BABY_PINK_SWET.get()), has(GenesisItems.BABY_PINK_SWET.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GenesisItems.COCOATRICE.get()).requires(Items.COCOA_BEANS).requires(AetherItems.SKYROOT_MILK_BUCKET.get()).requires(Items.SUGAR).unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GenesisItems.WRAPPED_CHOCOLATES.get()).requires(Items.COCOA_BEANS).requires(AetherItems.SKYROOT_MILK_BUCKET.get()).requires(AetherItems.AECHOR_PETAL.get()).requires(Items.SUGAR).unlockedBy(getHasName(AetherItems.AECHOR_PETAL.get()), has(AetherItems.AECHOR_PETAL.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GenesisItems.JELLY_PUMPKIN.get()).requires(GenesisItems.ORANGE.get()).requires(GenesisTags.Items.SWET_JELLY).requires(Items.SUGAR).unlockedBy(getHasName(GenesisItems.ORANGE.get()), has(GenesisItems.ORANGE.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GenesisItems.CANDY_CORN.get(), 5).requires(GenesisItems.CORNSTARCH_BOWL.get()).requires(AetherItems.SKYROOT_MILK_BUCKET.get()).requires(AetherItems.SKYROOT_WATER_BUCKET.get()).requires(Items.SUGAR).unlockedBy(getHasName(GenesisItems.CORNSTARCH_BOWL.get()), has(GenesisItems.CORNSTARCH_BOWL.get())).save(consumer);

        wall(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.SKYROOT_LOG_WALL.get(), AetherBlocks.SKYROOT_LOG.get());
        wall(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.SKYROOT_WOOD_WALL.get(), AetherBlocks.SKYROOT_WOOD.get());
        wall(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(), AetherBlocks.STRIPPED_SKYROOT_LOG.get());
        wall(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get(), AetherBlocks.STRIPPED_SKYROOT_WOOD.get());

        this.oreBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.MISC, GenesisItems.CONTINUUM_ORB.get(), RecipeCategory.MISC, GenesisItems.CONTINUUM_BOMB.get(), "continuum_orb_from_bomb", "continuum_bomb");

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenesisBlocks.SKYROOT_CRAFTING_TABLE.get())
                .define('#', AetherBlocks.SKYROOT_PLANKS.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(GenesisBlocks.SKYROOT_CRAFTING_TABLE.get()), has(AetherTags.Items.PLANKS_CRAFTING))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenesisBlocks.HOLYSTONE_FURNACE.get())
                .define('#',GenesisTags.Items.HOLYSTONE_FURNACE_CRAFTING)
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(GenesisBlocks.HOLYSTONE_FURNACE.get()), has(AetherBlocks.HOLYSTONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.BLAST_FURNACE)
                .group("minecraft:blast_furnace")
                .define('#', Blocks.SMOOTH_STONE)
                .define('X', GenesisBlocks.HOLYSTONE_FURNACE.get())
                .define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("IXI")
                .pattern("###")
                .unlockedBy("has_smooth_stone", has(Blocks.SMOOTH_STONE))
                .save(consumer, this.name("holystone_blast_furnace"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.SMOKER)
                .group("minecraft:smoker")
                .define('#', ItemTags.LOGS)
                .define('X', GenesisBlocks.HOLYSTONE_FURNACE.get())
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy(getHasName(GenesisBlocks.HOLYSTONE_FURNACE.get()), has(GenesisBlocks.HOLYSTONE_FURNACE.get()))
                .save(consumer, this.name("holystone_smoker"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenesisBlocks.SKYROOT_CHEST.get())
                .define('#', AetherTags.Items.PLANKS_CRAFTING)
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(GenesisBlocks.SKYROOT_CHEST.get()), has(AetherTags.Items.PLANKS_CRAFTING))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenesisBlocks.SKYROOT_LADDER.get(), 3)
                .define('#', GenesisTags.Items.SKYROOT_LADDER_CRAFTING)
                .pattern("# #")
                .pattern("###")
                .pattern("# #")
                .unlockedBy(getHasName(GenesisBlocks.SKYROOT_LADDER.get()), has(AetherTags.Items.SKYROOT_STICKS))
                .save(consumer);

        wall(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.DIVINE_CARVED_WALL.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.stairs(GenesisBlocks.DIVINE_CARVED_STAIRS, GenesisBlocks.DIVINE_CARVED_STONE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, GenesisBlocks.DIVINE_CARVED_SLAB.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());

        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.HOLYSTONE_HEADSTONE.get(), AetherBlocks.HOLYSTONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.HOLYSTONE_KEYSTONE.get(), AetherBlocks.HOLYSTONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.HOLYSTONE_HIGHLIGHT.get(), AetherBlocks.HOLYSTONE.get());

        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.HOLYSTONE_HEADSTONE.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.HOLYSTONE_KEYSTONE.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.HOLYSTONE_HIGHLIGHT.get(), AetherBlocks.HOLYSTONE_BRICKS.get());

        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.CARVED_PILLAR_TOP.get(), AetherBlocks.CARVED_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.CARVED_PILLAR_TOP.get(), GenesisBlocks.CARVED_PILLAR.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.CARVED_PILLAR.get(), AetherBlocks.CARVED_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.CARVED_PILLAR.get(), GenesisBlocks.CARVED_PILLAR_TOP.get());

        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.DIVINE_CARVED_STONE.get(), AetherBlocks.CARVED_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.DIVINE_SENTRY_STONE.get(), AetherBlocks.CARVED_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.DIVINE_SENTRY_STONE.get(), AetherBlocks.SENTRY_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, AetherBlocks.CARVED_STONE.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, AetherBlocks.SENTRY_STONE.get(), GenesisBlocks.DIVINE_SENTRY_STONE.get());

        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.DIVINE_CARVED_WALL.get(), AetherBlocks.CARVED_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.DIVINE_CARVED_STAIRS.get(), AetherBlocks.CARVED_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.DIVINE_CARVED_SLAB.get(), AetherBlocks.CARVED_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.DIVINE_CARVED_WALL.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.DIVINE_CARVED_STAIRS.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());
        this.stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, GenesisBlocks.DIVINE_CARVED_SLAB.get(), GenesisBlocks.DIVINE_CARVED_STONE.get());

        this.enchantingRecipe(RecipeCategory.FOOD, GenesisItems.RAINBOW_STRAWBERRY.get(), GenesisItems.WYNDBERRY.get(), 0.35F, 500).save(consumer, this.name("rainbow_strawberry_enchanting"));
        this.enchantingRecipe(RecipeCategory.BUILDING_BLOCKS, GenesisBlocks.GREEN_AERCLOUD.get(), GenesisBlocks.PURPLE_AERCLOUD.get(), 0.1F, 1000).save(consumer, this.name("green_aercloud_enchanting"));

    }
}
