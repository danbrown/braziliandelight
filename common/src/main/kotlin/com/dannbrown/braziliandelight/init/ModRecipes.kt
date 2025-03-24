package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.compat.CookingPotRecipeJsonBuilder
import com.dannbrown.braziliandelight.compat.CuttingBoardRecipeBuilder
import com.dannbrown.braziliandelight.FarmersCompat
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.registrate.AbstractDeltaboxRegistrate
import com.dannbrown.deltaboxlib.registrate.util.DeltaboxUtil
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Blocks
import java.util.function.Consumer
import java.util.function.Supplier
import java.util.function.UnaryOperator
import java.util.stream.Stream

object ModRecipes {
  init {
    REGISTRATE
      // REPUGNANT_ARROW
      .recipe { r ->
        r.simpleShapedRecipe(
          { ModItems.REPUGNANT_ARROW.get() }, arrayOf("G", "S", "F"),
          mapOf(
            'G' to Supplier { Ingredient.of(ModItems.GARLIC_BULB.get()) },
            'S' to Supplier { Ingredient.of(Items.STICK) },
            'F' to Supplier { Ingredient.of(Items.FEATHER) },
          ),
          1
        )
      }
      // SALT_BUCKET_FROM_WATER_SMELTING
      .recipe { r ->
        r.comboFoodRecipe(
          { ModItems.SALT_BUCKET.get() },
          { Ingredient.of(Items.WATER_BUCKET) },
          RecipeCategory.MISC,
          0.1f,
          200
        )
      }
      // SALT_FROM_SALT_BUCKET
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.SALT.get() },
          listOf(Supplier { Ingredient.of(ModItems.SALT_BUCKET.get()) }),
          RecipeCategory.MISC,
          1
        )
      }
      // BUTTER_FROM_HEAVY_CREAM
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.BUTTER.get() },
          listOf(
            Supplier { Ingredient.of(ModTags.INGREDIENT.SALT_INGREDIENT) },
            Supplier { Ingredient.of(ModItems.HEAVY_CREAM_BUCKET.get()) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      // COOKED_SHRIMP
      .recipe { r ->
        r.comboFoodRecipe(
          { ModItems.COOKED_SHRIMP.get() },
          { Ingredient.of(ModItems.SHRIMP.get()) },
          RecipeCategory.FOOD,
          2f,
          200
        )
      }
      // SLICE_FROM_MINAS_CHEESE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.MINAS_CHEESE_SLICE.get() },
          listOf(Supplier { Ingredient.of(ModBlocks.MINAS_CHEESE.get()) }),
          RecipeCategory.FOOD,
          4
        )
      }
      // MINAS_CHEESE_TO_SLICE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModBlocks.MINAS_CHEESE.get() },
          List(4) { Supplier { Ingredient.of(ModItems.MINAS_CHEESE_SLICE.get()) } },
          RecipeCategory.FOOD,
          1, "_from_slices"
        )
      }
      // SLICE_FROM_CHICKEN_POT_PIE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.CHICKEN_POT_PIE_SLICE.get() },
          listOf(Supplier { Ingredient.of(ModBlocks.CHICKEN_POT_PIE.get()) }),
          RecipeCategory.FOOD,
          4
        )
      }
      // CHICKEN_POT_PIE_TO_SLICE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModBlocks.CHICKEN_POT_PIE.get() },
          List(4) { Supplier { Ingredient.of(ModItems.CHICKEN_POT_PIE_SLICE.get()) } },
          RecipeCategory.FOOD,
          1,
          "_from_slices"
        )
      }
      // SLICE_FROM_CARROT_CAKE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.CARROT_CAKE_SLICE.get() },
          listOf(Supplier { Ingredient.of(ModBlocks.CARROT_CAKE.get()) }),
          RecipeCategory.FOOD,
          7
        )
      }
      // CARROT_CAKE_TO_SLICE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModBlocks.CARROT_CAKE.get() },
          List(7) { Supplier { Ingredient.of(ModItems.CARROT_CAKE_SLICE.get()) } },
          RecipeCategory.FOOD,
          1, "_from_slices"
        )
      }
      // SLICE_FROM_CARROT_CAKE_WITH_CHOCOLATE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get() },
          listOf(Supplier { Ingredient.of(ModBlocks.CARROT_CAKE_WITH_CHOCOLATE.get()) }),
          RecipeCategory.FOOD,
          7
        )
      }
      // CARROT_CAKE_WITH_CHOCOLATE_TO_SLICE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModBlocks.CARROT_CAKE_WITH_CHOCOLATE.get() },
          List(7) { Supplier { Ingredient.of(ModItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()) } },
          RecipeCategory.FOOD,
          1, "_from_slices"
        )
      }
      // MINAS_CHEESE_ON_A_STICK
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.MINAS_CHEESE_ON_A_STICK.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.MINAS_CHEESE_SLICE.get()) },
            Supplier { Ingredient.of(ModItems.MINAS_CHEESE_SLICE.get()) },
            Supplier { Ingredient.of(Items.STICK) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }
      // GRILLED_CHEESE_ON_A_STICK
      .recipe { r ->
        r.comboFoodRecipe(
          { ModItems.GRILLED_CHEESE_ON_A_STICK.get() },
          { Ingredient.of(ModItems.MINAS_CHEESE_ON_A_STICK.get()) },
          RecipeCategory.FOOD,
          2f,
          200
        )
      }
      // SWEET_LOVE_APPLE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.SWEET_LOVE_APPLE.get() },
          listOf(
            Supplier { Ingredient.of(Items.SUGAR) },
            Supplier { Ingredient.of(ModItems.CONDENSED_MILK.get()) },
            Supplier { Ingredient.of(Items.STICK) },
            Supplier { Ingredient.of(Items.APPLE) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }
      // ROASTED_GARLIC
      .recipe { r ->
        r.comboFoodRecipe(
          { ModItems.ROASTED_GARLIC.get() },
          { Ingredient.of(ModItems.GARLIC_BULB.get()) },
          RecipeCategory.FOOD,
          2f,
          200
        )
      }
      // GARAPA
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.GARAPA.get() },
          listOf(
            Supplier { Ingredient.of(Items.GLASS_BOTTLE) },
            Supplier { Ingredient.of(Items.SUGAR_CANE) },
            Supplier { Ingredient.of(Items.SUGAR_CANE) },
            Supplier { Ingredient.of(Items.SUGAR_CANE) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      // GUARANA_POWDER_FROM_GUARANA
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.GUARANA_POWDER.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.GUARANA_FRUIT.get()) },
            Supplier { Ingredient.of(ModItems.GUARANA_FRUIT.get()) }
          ),
          RecipeCategory.MISC,
          2
        )
      }
      // CASSAVA_FLOUR_FROM_CASSAVA
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.CASSAVA_FLOUR.get() },
          listOf(
            Supplier { Ingredient.of(ModBlocks.BUDDING_CASSAVA.get()) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      // CORN_FLOUR_FROM_CORN
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.CORN_FLOUR.get() },
          listOf(
            Supplier { Ingredient.of(ModBlocks.BUDDING_CORN.get()) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      // GUARANA_SEEDS_FROM_GUARANA
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModBlocks.BUDDING_GUARANA.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.GUARANA_FRUIT.get()) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      // COLLARD_SEEDS_FROM_COLLARD_GREENS
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModBlocks.COLLARD_GREENS_CROP.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.COLLARD_GREENS.get()) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      // COFFEE_SEEDS_FROM_COFFEE_BERRIES
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModBlocks.BUDDING_COFFEE.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.COFFEE_BERRIES.get()) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      // KERNELS_FROM_CORN_SHAPELESS
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModBlocks.BUDDING_CORN.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.CORN.get()) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      // COOKED_CORN
      .recipe { r ->
        r.comboFoodRecipe(
          { ModItems.COOKED_CORN.get() },
          { Ingredient.of(ModItems.CORN.get()) },
          RecipeCategory.MISC,
          1f,
          200
        )
      }
      // COFFEE_BEANS_FROM_COFFEE_BERRIES
      .recipe { r ->
        r.comboFoodRecipe(
          { ModItems.COFFEE_BEANS.get() },
          { Ingredient.of(ModItems.COFFEE_BERRIES.get()) },
          RecipeCategory.MISC,
          1f,
          200
        )
      }
      // RAW_COXINHA
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.RAW_COXINHA.get() },
          listOf(
            Supplier { Ingredient.of(ModTags.INGREDIENT.WHEAT_DOUGH) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.CHEESE_INGREDIENT) },
            Supplier { Ingredient.of(ModTags.ITEM.COXINHA_FILLINGS) },
            Supplier { Ingredient.of(ModItems.CASSAVA_FLOUR.get()) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      // COXINHA
      .recipe { r ->
        r.comboFoodRecipe(
          { ModItems.COXINHA.get() },
          { Ingredient.of(ModItems.RAW_COXINHA.get()) },
          RecipeCategory.MISC,
          2f,
          200
        )
      }
      // RAW_CASSAVA_FRITTERS
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.RAW_CASSAVA_FRITTERS.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.CASSAVA_FLOUR.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.BUTTER_INGREDIENT) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.CHEESE_INGREDIENT) },
            Supplier { Ingredient.of(ModItems.COLLARD_GREENS.get()) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      // CASSAVA_FRITTERS
      .recipe { r ->
        r.comboFoodRecipe(
          { ModItems.CASSAVA_FRITTERS.get() },
          { Ingredient.of(ModItems.RAW_CASSAVA_FRITTERS.get()) },
          RecipeCategory.MISC,
          2f,
          200
        )
      }
      // GUARANA_SODA
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.GUARANA_SODA.get() },
          listOf(
            Supplier { Ingredient.of(Items.GLASS_BOTTLE) },
            Supplier { Ingredient.of(ModItems.GUARANA_POWDER.get()) },
            Supplier { Ingredient.of(Blocks.ICE) },
            Supplier { Ingredient.of(Items.SUGAR) },
            Supplier { Ingredient.of(Items.SUGAR) }
          ),
          RecipeCategory.MISC,
          1
        )
      }
      .recipe { r ->
        r.simpleShapedRecipe(
          { ModBlocks.CHICKEN_POT_PIE.get() },
          arrayOf(
            "ODG",
            "CCC",
            "TPH"
          ),
          mapOf(
            'D' to Supplier { Ingredient.of(ModTags.INGREDIENT.WHEAT_DOUGH) },
            'T' to Supplier { Ingredient.of(ModTags.INGREDIENT.TOMATO_SAUCE) },
            'O' to Supplier { Ingredient.of(ModTags.INGREDIENT.VEGETABLES_ONION) },
            'G' to Supplier { Ingredient.of(ModBlocks.GARLIC_CROP.get()) },
            'C' to Supplier { Ingredient.of(ModTags.INGREDIENT.COOKED_CHICKEN) },
            'P' to Supplier { Ingredient.of(ModTags.INGREDIENT.PIE_CRUST) },
            'H' to Supplier { Ingredient.of(ModItems.HEAVY_CREAM_BUCKET.get()) }
          ),
          1
        )
      }
      // CARROT_CAKE
      .recipe { r ->
        r.simpleShapedRecipe(
          { ModBlocks.CARROT_CAKE.get() },
          arrayOf(
            "MMM",
            "SEW",
            "CCC"
          ),
          mapOf(
            'C' to Supplier { Ingredient.of(ModTags.INGREDIENT.VEGETABLES_CARROT) },
            'E' to Supplier { Ingredient.of(ModTags.INGREDIENT.EGGS) },
            'M' to Supplier { Ingredient.of(ModTags.INGREDIENT.MILK) },
            'S' to Supplier { Ingredient.of(Items.SUGAR) },
            'W' to Supplier { Ingredient.of(Items.WHEAT) }
          ),
          1
        )
      }
      // CARROT_CAKE_WITH_CHOCOLATE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModBlocks.CARROT_CAKE_WITH_CHOCOLATE.get() },
          listOf(
            Supplier { Ingredient.of(ModBlocks.CARROT_CAKE.get()) },
            Supplier { Ingredient.of(ModItems.BRIGADEIRO_CREAM.get()) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }
      // SWEET_LOVE_APPLE_TRAY
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModBlocks.SWEET_LOVE_APPLE_TRAY.get() },
          listOf(
            Supplier { Ingredient.of(Items.BOWL) },
            Supplier { Ingredient.of(ModItems.SWEET_LOVE_APPLE.get()) },
            Supplier { Ingredient.of(ModItems.SWEET_LOVE_APPLE.get()) },
            Supplier { Ingredient.of(ModItems.SWEET_LOVE_APPLE.get()) },
            Supplier { Ingredient.of(ModItems.SWEET_LOVE_APPLE.get()) },
            Supplier { Ingredient.of(ModItems.SWEET_LOVE_APPLE.get()) },
            Supplier { Ingredient.of(ModItems.SWEET_LOVE_APPLE.get()) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }
      // YERBA_MATE_DRIED
      .recipe { r ->
        r.comboFoodRecipe(
          { ModItems.DRIED_YERBA_MATE.get() },
          { Ingredient.of(ModItems.YERBA_MATE_LEAVES.get()) },
          RecipeCategory.FOOD,
          1f,
          200
        )
      }
      // COCONUT_DRINK
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.COCONUT_DRINK.get() },
          listOf(
            Supplier { Ingredient.of(ModBlocks.GREEN_COCONUT.get()) },
            Supplier { Ingredient.of(Items.BAMBOO) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }

      // COCONUT_MILK
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.COCONUT_MILK.get() },
          listOf(
            Supplier { Ingredient.of(ModBlocks.GREEN_COCONUT.get()) },
            Supplier { Ingredient.of(Items.GLASS_BOTTLE) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }

      // CHEESE_BREAD_DOUGH
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.CHEESE_BREAD_DOUGH.get() },
          listOf(
            Supplier { Ingredient.of(ModTags.INGREDIENT.CHEESE_INGREDIENT) },
            Supplier { Ingredient.of(ModItems.CASSAVA_FLOUR.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.MILK) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.EGGS) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.SALT_INGREDIENT) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }
      // CHEESE_BREAD
      .recipe { r ->
        r.comboFoodRecipe(
          { ModItems.CHEESE_BREAD.get() },
          { Ingredient.of(ModItems.CHEESE_BREAD_DOUGH.get()) },
          RecipeCategory.FOOD,
          1f,
          200
        )
      }

      // COLLARD_GREENS_FAROFA
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.COLLARD_GREENS_FAROFA.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.COLLARD_GREENS.get()) },
            Supplier { Ingredient.of(ModItems.CASSAVA_FLOUR.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.BUTTER_INGREDIENT) },
            Supplier { Ingredient.of(ModItems.GARLIC_BULB.get()) },
            Supplier { Ingredient.of(Items.BOWL) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }

      // COLLARD_GREENS_SALAD
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.COLLARD_GREENS_SALAD.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.COLLARD_GREENS.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.VEGETABLES_ONION) },
            Supplier { Ingredient.of(Items.BOWL) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }
      // CHIMARRAO
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.CHIMARRAO.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.DRIED_YERBA_MATE.get()) },
            Supplier { Ingredient.of(Items.WATER_BUCKET) },
            Supplier { Ingredient.of(Items.BOWL) }
          ),
          RecipeCategory.MISC,
          1
        )
      }

      // LEMONADE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.LEMONADE.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.LEMON.get()) },
            Supplier { Ingredient.of(Items.SUGAR) },
            Supplier { Ingredient.of(Items.WATER_BUCKET) },
            Supplier { Ingredient.of(Items.GLASS_BOTTLE) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }

      // COLLARD_LEMONADE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.COLLARD_LEMONADE.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.COLLARD_GREENS.get()) },
            Supplier { Ingredient.of(ModItems.LEMON.get()) },
            Supplier { Ingredient.of(Items.SUGAR) },
            Supplier { Ingredient.of(Items.WATER_BUCKET) },
            Supplier { Ingredient.of(Items.GLASS_BOTTLE) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }

      // GUARANA_JUICE
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.GUARANA_JUICE.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.GUARANA_POWDER.get()) },
            Supplier { Ingredient.of(Items.SUGAR) },
            Supplier { Ingredient.of(Items.WATER_BUCKET) },
            Supplier { Ingredient.of(Items.GLASS_BOTTLE) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }

      // BROA
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.BROA.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.CORN_FLOUR.get()) },
            Supplier { Ingredient.of(ModItems.CASSAVA_FLOUR.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.BUTTER_INGREDIENT) }
          ),
          RecipeCategory.FOOD,
          4
        )
      }

      // BRAZILIAN_DINNER
      .recipe { r ->
        r.simpleShapelessRecipe(
          { ModItems.BRAZILIAN_DINNER.get() },
          listOf(
            Supplier { Ingredient.of(ModItems.COOKED_CARIOCA_BEANS.get()) },
            Supplier { Ingredient.of(ModItems.COOKED_BLACK_BEANS.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.COOKED_RICE) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.FRIED_EGG) }
          ),
          RecipeCategory.FOOD,
          1
        )
      }
      // GARLIC_CLOVE
      .recipe { r ->
        cutting(
          r.registrate, r.exporter,
          { ModItems.GARLIC_BULB.get() },
          listOf(
            Triple(Supplier { ModBlocks.GARLIC_CROP.getItem() }, 2, 1f)
          )
        )
      }
      // LEMON_SLICE
      .recipe { r ->
        cutting(
          r.registrate, r.exporter,
          { ModItems.LEMON.get() },
          listOf(
            Triple(Supplier { ModItems.LEMON_SLICE.get() }, 2, 1f)
          )
        )
      }
      // BEANS
      .recipe { r ->
        cutting(
          r.registrate, r.exporter,
          { ModItems.BEAN_POD.get() },
          listOf(
            Triple(Supplier { ModBlocks.CARIOCA_BEANS_CROP.getItem() }, 1, 0.5f),
            Triple(Supplier { ModBlocks.BLACK_BEANS_CROP.getItem() }, 1, 0.5f)
          )
        )
      }
      // KERNELS_FROM_CORN_CUTTING
      .recipe { r ->
        cutting(
          r.registrate, r.exporter,
          { ModItems.CORN.get() },
          listOf(
            Triple(Supplier { ModBlocks.BUDDING_CORN.getItem() }, 1, 0.5f),
            Triple(Supplier { ModBlocks.BUDDING_WHITE_CORN.getItem() }, 1, 0.5f)
          )
        )
      }
      // COCONUT_SLICE
      .recipe { r ->
        cutting(
          r.registrate, r.exporter,
          { ModBlocks.COCONUT.getItem() },
          listOf(
            Triple(Supplier { ModItems.COCONUT_SLICE.get() }, 1, 1f)
          ),
          { Ingredient.of(ItemTags.AXES) }
        )
      }
      // CONDENSED_MILK_FROM_MILK
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.CONDENSED_MILK.get() },
          1,
          { Items.GLASS_BOTTLE },
          listOf(
            Supplier { Ingredient.of(ModTags.INGREDIENT.MILK) },
            Supplier { Ingredient.of(Items.SUGAR) },
          )
        )
      }
      // PUDDING
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModBlocks.PUDDING.getItem() },
          1,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(ModItems.CONDENSED_MILK.get()) },
            Supplier { Ingredient.of(ModItems.CONDENSED_MILK.get()) },
            Supplier { Ingredient.of(ModItems.HEAVY_CREAM_BUCKET.get()) },
            Supplier { Ingredient.of(ModItems.HEAVY_CREAM_BUCKET.get()) },
            Supplier { Ingredient.of(Items.SUGAR) },
            Supplier { Ingredient.of(Items.SUGAR) },
          )
        )
      }
      // FEIJOADA
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModBlocks.FEIJOADA_POT.get() },
          1,
          { FarmersCompat.getCookingPot().asItem() },
          listOf(
            Supplier { Ingredient.of(ModBlocks.BLACK_BEANS_CROP.getItem()) },
            Supplier { Ingredient.of(ModBlocks.GARLIC_CROP.getItem()) },
            Supplier { Ingredient.of(ModItems.GARLIC_BULB.get()) },
            Supplier { Ingredient.of(ModItems.COLLARD_GREENS.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.BACON) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.BACON) }
          )
        )
      }
      // ACAI_CREAM
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.ACAI_CREAM.get() },
          1,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(ModBlocks.BUDDING_ACAI_BRANCH.get()) },
            Supplier { Ingredient.of(ModBlocks.BUDDING_ACAI_BRANCH.get()) },
            Supplier { Ingredient.of(ModItems.CONDENSED_MILK.get()) },
            Supplier { Ingredient.of(ModItems.GUARANA_POWDER.get()) }
          )
        )
      }
      // COCONUT_CREAM
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.COCONUT_CREAM.get() },
          1,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(ModItems.COCONUT_SLICE.get()) },
            Supplier { Ingredient.of(ModItems.COCONUT_SLICE.get()) },
            Supplier { Ingredient.of(ModItems.COCONUT_MILK.get()) },
            Supplier { Ingredient.of(ModItems.CONDENSED_MILK.get()) }
          )
        )
      }
      // BRIGADEIRO_CREAM
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.BRIGADEIRO_CREAM.get() },
          1,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(Items.COCOA_BEANS) },
            Supplier { Ingredient.of(Items.COCOA_BEANS) },
            Supplier { Ingredient.of(ModItems.CONDENSED_MILK.get()) },
            Supplier { Ingredient.of(ModItems.BUTTER.get()) }
          )
        )
      }
      // TUCUPI_BOIL
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.TUCUPI.get() },
          3,
          { Items.GLASS_BOTTLE },
          listOf(
            Supplier { Ingredient.of(ModBlocks.BUDDING_CASSAVA.getItem()) },
            Supplier { Ingredient.of(ModBlocks.BUDDING_CASSAVA.getItem()) },
            Supplier { Ingredient.of(Items.WATER_BUCKET) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.SALT_INGREDIENT) }
          )
        )
      }
      // FRIED_CASSAVA_WITH_BUTTER
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.FRIED_CASSAVA_WITH_BUTTER.get() },
          1,
          null,
          listOf(
            Supplier { Ingredient.of(ModBlocks.BUDDING_CASSAVA.get()) },
            Supplier { Ingredient.of(ModItems.BUTTER.get()) }
          )
        )
      }
      // COOKED_CARIOCA_BEANS
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.COOKED_CARIOCA_BEANS.get() },
          1,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(ModBlocks.CARIOCA_BEANS_CROP.getItem()) },
            Supplier { Ingredient.of(ModItems.GARLIC_BULB.get()) }
          )
        )
      }
      // COOKED_BLACK_BEANS
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.COOKED_BLACK_BEANS.get() },
          1,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(ModBlocks.BLACK_BEANS_CROP.getItem()) },
            Supplier { Ingredient.of(ModItems.GARLIC_BULB.get()) }
          )
        )
      }
      // TROPEIRO_BEANS
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.TROPEIRO_BEANS.get() },
          2,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(ModBlocks.CARIOCA_BEANS_CROP.getItem()) },
            Supplier { Ingredient.of(ModBlocks.CARIOCA_BEANS_CROP.getItem()) },
            Supplier { Ingredient.of(ModItems.CASSAVA_FLOUR.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.BACON) },
            Supplier { Ingredient.of(ModItems.GARLIC_BULB.get()) },
            Supplier { Ingredient.of(ModItems.COLLARD_GREENS.get()) },
          )
        )
      }
      // FRIED_FISH_WITH_ACAI
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.FRIED_FISH_WITH_ACAI.get() },
          1,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(ModTags.INGREDIENT.RAW_FISHES_COD) },
            Supplier { Ingredient.of(ModItems.CASSAVA_FLOUR.get()) },
            Supplier { Ingredient.of(ModItems.BUTTER.get()) },
            Supplier { Ingredient.of(ModBlocks.BUDDING_ACAI_BRANCH.get()) }
          )
        )
      }
      // ANGU
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.ANGU.get() },
          2,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(Items.WATER_BUCKET) },
            Supplier { Ingredient.of(ModItems.CORN_FLOUR.get()) },
            Supplier { Ingredient.of(ModItems.CORN_FLOUR.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.SALT_INGREDIENT) }
          )
        )
      }
      // BUTTERED_CORN
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.BUTTERED_CORN.get() },
          1,
          null,
          listOf(
            Supplier { Ingredient.of(ModItems.CORN.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.BUTTER_INGREDIENT) }
          )
        )
      }
      // SALPICAO
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.SALPICAO.get() },
          1,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(ModTags.INGREDIENT.COOKED_CHICKEN) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.VEGETABLES_CARROT) },
            Supplier { Ingredient.of(ModBlocks.BUDDING_CORN.getItem()) },
            Supplier { Ingredient.of(ModItems.BEAN_POD.get()) },
            Supplier { Ingredient.of(Items.APPLE) },
            Supplier { Ingredient.of(ModItems.HEAVY_CREAM_BUCKET.get()) }
          )
        )
      }
      // ACAI_TEA_WITH_GUARANA
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.ACAI_TEA_WITH_GUARANA.get() },
          2,
          { Items.GLASS_BOTTLE },
          listOf(
            Supplier { Ingredient.of(ModBlocks.BUDDING_ACAI_BRANCH.get()) },
            Supplier { Ingredient.of(ModItems.GUARANA_POWDER.get()) },
            Supplier { Ingredient.of(Items.WATER_BUCKET) },
            Supplier { Ingredient.of(Items.SUGAR) }
          )
        )
      }
      // COUSCOUS
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModItems.COUSCOUS.get() },
          1,
          { Items.BOWL },
          listOf(
            Supplier { Ingredient.of(ModItems.CORN_FLOUR.get()) },
            Supplier { Ingredient.of(ModItems.CORN_FLOUR.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.BUTTER_INGREDIENT) }
          )
        )
      }
      // GREEN_SOUP_POT
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModBlocks.GREEN_SOUP_POT.get() },
          1,
          { FarmersCompat.getCookingPot().asItem() },
          listOf(
            Supplier { Ingredient.of(ModItems.BEAN_POD.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.COOKED_PORK) },
            Supplier { Ingredient.of(ModItems.GARLIC_BULB.get()) }
          )
        )
      }

// FISH_MOQUECA_POT

      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModBlocks.FISH_MOQUECA_POT.get() },
          1,
          { FarmersCompat.getCookingPot().asItem() },
          listOf(
            Supplier { Ingredient.of(ModTags.INGREDIENT.RAW_FISHES_COD) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.VEGETABLES_ONION) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.VEGETABLES_TOMATO) },
            Supplier { Ingredient.of(ModItems.HEAVY_CREAM_BUCKET.get()) },
            Supplier { Ingredient.of(ModItems.LEMON.get()) },
            Supplier { Ingredient.of(ModItems.COCONUT_MILK.get()) }
          )
        )
      }

// STROGANOFF_POT
      .recipe { r ->
        cookingPot(
          r.registrate, r.exporter,
          { ModBlocks.STROGANOFF_POT.get() },
          1,
          { FarmersCompat.getCookingPot().asItem() },
          listOf(
            Supplier { Ingredient.of(ModTags.INGREDIENT.RAW_CHICKEN) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.RAW_BEEF) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.RAW_MUTTON) },
            Supplier { Ingredient.of(Items.RABBIT) },
            Supplier { Ingredient.of(ModItems.GARLIC_BULB.get()) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.VEGETABLES_ONION) },
            Supplier { Ingredient.of(ModTags.INGREDIENT.VEGETABLES_TOMATO) },
            Supplier { Ingredient.of(ModItems.HEAVY_CREAM_BUCKET.get()) },
            Supplier { Ingredient.of(Items.BROWN_MUSHROOM) }
          )
        )
      }
  }

  fun cutting(
    registrate: AbstractDeltaboxRegistrate,
    exporter: Consumer<FinishedRecipe>,
    input: Supplier<Item>,
    outputs: List<Triple<Supplier<Item>, Int, Float>>,
    tool: Supplier<Ingredient> = Supplier {
      Ingredient.of(
        FarmersCompat.TAGS.KNIVES
      )
    },
    name: String? = null,
    suffix: String = "_cutting"
  ) {
    val output = outputs.first()
    val asName = name
      ?: DeltaboxUtil.getItemId(output.first.get())
    val builder =
      CuttingBoardRecipeBuilder.create(
        input.get(),
        tool.get(),
        output.first.get(),
        output.second,
        output.third
      )

    val _outputs = outputs.drop(1)
    for (ingredient in _outputs) builder.output(
      ingredient.first.get(),
      ingredient.second,
      ingredient.third
    )

    val _outputs_items =
      _outputs.map { it.first.get() }

    builder.unlockedBy(
      "has_ingredients",
      InventoryChangeTrigger.TriggerInstance.hasItems(
        *_outputs_items.toTypedArray()
      )
    )
    builder.save(
      exporter,
      DeltaboxUtil.resourceLocation(
        registrate.modId,
        asName + suffix
      )
    )
  }

  fun cookingPot(
    registrate: AbstractDeltaboxRegistrate,
    exporter: Consumer<FinishedRecipe>,
    output: Supplier<ItemLike>,
    amount: Int,
    foodContainer: Supplier<Item>? = null,
    ingredients: List<Supplier<Ingredient>>,
    cookingTime: Int = 200,
    experience: Float = 1.0f,
    name: String? = null,
    suffix: String = "_cooking_pot"
  ) {
    val asName =
      name ?: DeltaboxUtil.getItemId(output.get())

    val builder =
      CookingPotRecipeJsonBuilder.create(
        output.get(),
        amount,
        cookingTime,
        experience,
        foodContainer?.get(),
        ingredients.map { it.get() })

    val _ingredients_items =
      ingredients.map { it.get().items.toList() }
        .flatten().map { it.item }

    builder.unlockedBy(
      "has_ingredients",
      InventoryChangeTrigger.TriggerInstance.hasItems(
        *_ingredients_items.toTypedArray()
      )
    )
    builder.save(
      exporter,
      DeltaboxUtil.resourceLocation(
        registrate.modId,
        asName + suffix
      )
    )
  }

  fun register() {
    // init
  }
}