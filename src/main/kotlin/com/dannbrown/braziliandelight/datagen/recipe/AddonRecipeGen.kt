package com.dannbrown.braziliandelight.datagen.recipe

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonItems
import com.dannbrown.braziliandelight.init.AddonTags
import com.dannbrown.deltaboxlib.registry.datagen.recipe.DeltaboxRecipeSlice
import com.tterrag.registrate.util.DataIngredient
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Blocks
import vectorwing.farmersdelight.common.registry.ModBlocks
import vectorwing.farmersdelight.common.registry.ModItems
import vectorwing.farmersdelight.common.tag.ForgeTags
import java.util.function.Consumer
import java.util.function.Supplier
import java.util.function.UnaryOperator
import java.util.stream.Stream

class AddonRecipeGen : DeltaboxRecipeSlice(AddonContent.MOD_ID) {
  override fun name(): String {
    return "Brazilian Delight"
  }

  override fun addRecipes(recipeConsumer: Consumer<FinishedRecipe>) {
    val REPUGNANT_ARROW = crafting(recipeConsumer, { AddonItems.REPUGNANT_ARROW.get() }) { b ->
      b
        .shaped(1) { c ->
          c
            .pattern("G")
            .pattern("S")
            .pattern("F")
            .define('G', AddonItems.GARLIC_BULB.get())
            .define('S', Items.STICK)
            .define('F', Items.FEATHER)
        }
    }


    val SALT_BUCKET_FROM_WATER_SMELTING = cooking(recipeConsumer,
      {
        Ingredient.fromValues(
          Stream.of(
            Ingredient.ItemValue(ItemStack(Items.WATER_BUCKET)),
          )
        )
      },
      { AddonItems.SALT_BUCKET.get() }
    ) { b ->
      b
        .inFurnace(200, 1f)
        .inSmoker(100, 1f)
        .inBlastFurnace(100, 1f)
    }

    val SALT_FROM_SALT_BUCKET = crafting(recipeConsumer, { AddonItems.SALT.get() }) { b ->
      b.shapeless(1, "", "_bucket", listOf(DataIngredient.items(AddonItems.SALT_BUCKET.get())))
    }

    val BUTTER_FROM_HEAVY_CREAM = crafting(recipeConsumer, { AddonItems.BUTTER.get() }) { b ->
      b.shapeless(
        1,
        "",
        "",
        listOf(DataIngredient.tag(AddonTags.ITEM.SALT), DataIngredient.items(AddonItems.HEAVY_CREAM_BUCKET.get()))
      )
    }

    val COOKED_SHRIMP = cooking(recipeConsumer,
      { DataIngredient.items(AddonItems.SHRIMP.get()) },
      { AddonItems.COOKED_SHRIMP.get() }
    ) { b ->
      b
        .comboFoodCooking(200, 2f)
    }

    val SLICE_FROM_MINAS_CHEESE = crafting(recipeConsumer, { AddonItems.MINAS_CHEESE_SLICE.get() }) { b ->
      b.shapeless(4, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.MINAS_CHEESE.get())))
    }

    val MINAS_CHEESE_TO_SLICE = crafting(recipeConsumer, { AddonBlocks.MINAS_CHEESE.get() }) { b ->
      b.shapeless(
        1, "", "_from_slice", listOf(
          DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
          DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
          DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
          DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
        )
      )
    }

    val SLICE_FROM_CHICKEN_POT_PIE = crafting(recipeConsumer, { AddonItems.CHICKEN_POT_PIE_SLICE.get() }) { b ->
      b.shapeless(4, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CHICKEN_POT_PIE.get())))
    }

    val CHICKEN_POT_PIE_TO_SLICE = crafting(recipeConsumer, { AddonBlocks.CHICKEN_POT_PIE.get() }) { b ->
      b.shapeless(
        1, "", "_from_slice", listOf(
          DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
          DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
          DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
          DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
        )
      )
    }

    val SLICE_FROM_CARROT_CAKE = crafting(recipeConsumer, { AddonItems.CARROT_CAKE_SLICE.get() }) { b ->
      b.shapeless(7, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CARROT_CAKE.get())))
    }

    val CARROT_CAKE_TO_SLICE = crafting(recipeConsumer, { AddonBlocks.CARROT_CAKE.get() }) { b ->
      b.shapeless(
        1, "", "_from_slice", listOf(
          DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
        )
      )
    }

    val SLICE_FROM_CARROT_CAKE_WITH_CHOCOLATE = crafting(recipeConsumer, { AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get() }) { b ->
      b.shapeless(7, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CARROT_CAKE_WITH_CHOCOLATE.get())))
    }

    val CARROT_CAKE_WITH_CHOCOLATE_TO_SLICE = crafting(recipeConsumer, { AddonBlocks.CARROT_CAKE_WITH_CHOCOLATE.get() }) { b ->
      b.shapeless(
        1, "", "_from_slice", listOf(
          DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
          DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
        )
      )
    }

    val MINAS_CHEESE_ON_A_STICK = crafting(recipeConsumer, { AddonItems.MINAS_CHEESE_ON_A_STICK.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
          DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
          DataIngredient.items(Items.STICK),
        )
      )
    }


    val GRILLED_CHEESE_ON_A_STICK = cooking(recipeConsumer,
      { DataIngredient.items(AddonItems.MINAS_CHEESE_ON_A_STICK.get()) },
      { AddonItems.GRILLED_CHEESE_ON_A_STICK.get() }
    ) { b ->
      b
        .comboFoodCooking(200, 2f)
    }

    val SWEET_LOVE_APPLE = crafting(recipeConsumer, { AddonItems.SWEET_LOVE_APPLE.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.items(Items.SUGAR),
          DataIngredient.items(AddonItems.CONDENSED_MILK.get()),
          DataIngredient.items(Items.STICK),
          DataIngredient.items(Items.APPLE),
        )
      )
    }

    val ROASTED_GARLIC = cooking(recipeConsumer,
      { DataIngredient.items(AddonItems.GARLIC_BULB.get()) },
      { AddonItems.ROASTED_GARLIC.get() }
    ) { b ->
      b
        .comboFoodCooking(200, 2f)
    }

    val GARLIC_CLOVE = cutting(recipeConsumer, { AddonBlocks.GARLIC_CROP.get() }, 2) { b ->
      b
        .knifeTool()
        .build(DataIngredient.items(AddonItems.GARLIC_BULB.get()), "", "")
    }

    val LEMON_SLICE = cutting(recipeConsumer, { AddonItems.LEMON_SLICE.get() }, 2) { b ->
      b
        .knifeTool()
        .build(DataIngredient.items(AddonItems.LEMON.get()), "", "")
    }

    val BEANS = cutting(recipeConsumer, { Blocks.AIR }, 0) { b ->
      b
        .knifeTool()
        .extraResult({ AddonBlocks.BUDDING_BEANS_CROP }, 0.5f, 1)
        .extraResult({ AddonBlocks.CARIOCA_BEANS_CROP }, 0.5f, 1)
        .build(DataIngredient.items(AddonItems.BEAN_POD.get()), "bean_pod_", "_to_beans")
    }


    val GARAPA = crafting(recipeConsumer, { AddonItems.GARAPA.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.items(Items.GLASS_BOTTLE),
          DataIngredient.items(Items.SUGAR_CANE),
          DataIngredient.items(Items.SUGAR_CANE),
          DataIngredient.items(Items.SUGAR_CANE),
        )
      )
    }

    val GUARANA_POWDER_FROM_GUARANA = crafting(recipeConsumer, { AddonItems.GUARANA_POWDER.get() }) { b ->
      b.shapeless(
        2, "", "_from_guarana", listOf(
          DataIngredient.items(AddonItems.GUARANA_FRUIT.get()),
          DataIngredient.items(AddonItems.GUARANA_FRUIT.get()),
        )
      )
    }

    val CASSAVA_FLOUR_FROM_CASSAVA = crafting(recipeConsumer, { AddonItems.CASSAVA_FLOUR.get() }) { b ->
      b.shapeless(
        1, "", "_from_cassava", listOf(
          DataIngredient.items(AddonBlocks.BUDDING_CASSAVA.get()),
        )
      )
    }

    val GUARANA_SEEDS_FROM_GUARANA = crafting(recipeConsumer, { AddonBlocks.BUDDING_GUARANA.get() }) { b ->
      b.shapeless(
        1, "", "_from_fruit", listOf(
          DataIngredient.items(AddonItems.GUARANA_FRUIT.get()),
        )
      )
    }

    val COLLARD_SEEDS_FROM_COLLARD_GREENS = crafting(recipeConsumer, { AddonBlocks.COLLARD_GREENS_CROP.get() }) { b ->
      b.shapeless(
        1, "", "_from_greens", listOf(
          DataIngredient.items(AddonItems.COLLARD_GREENS.get()),
        )
      )
    }

    val COFFEE_SEEDS_FROM_COFFEE_BERRIES = crafting(recipeConsumer, { AddonBlocks.BUDDING_COFFEE.get() }) { b ->
      b.shapeless(
        1, "", "_from_berries", listOf(
          DataIngredient.items(AddonItems.COFFEE_BERRIES.get()),
        )
      )
    }

    val KERNELS_FROM_CORN_CUTTING = cutting(recipeConsumer, { AddonBlocks.BUDDING_CORN.get() }, 2) { b ->
      b
        .knifeTool()
        .extraResult({ AddonBlocks.WHITE_KERNELS_CROP }, 0.05f, 1)
        .build(DataIngredient.items(AddonItems.CORN.get()), "", "_to_kernels")
    }

    val KERNELS_FROM_CORN_SHAPELESS = crafting(recipeConsumer, { AddonBlocks.BUDDING_CORN.get() }) { b ->
      b.shapeless(
        1, "", "_from_corn", listOf(
          DataIngredient.items(AddonItems.CORN.get()),
        )
      )
    }

    val COOKED_CORN = cooking(recipeConsumer,
      { DataIngredient.items(AddonItems.CORN.get()) },
      { AddonItems.COOKED_CORN.get() }
    ) { b ->
      b
        .comboFoodCooking(200, 1f)
    }

    val COCONUT_SLICE = cutting(recipeConsumer, { AddonItems.COCONUT_SLICE.get() }, 2) { b ->
      b
        .axeDigTool()
        .build(DataIngredient.items(AddonBlocks.COCONUT.get()), "", "")
    }

    val COFFEE_BEANS_FROM_COFFEE_BERRIES = cooking(recipeConsumer,
      { DataIngredient.items(AddonItems.COFFEE_BERRIES.get()) },
      { AddonItems.COFFEE_BEANS.get() }
    ) { b ->
      b
        .comboFoodCooking(200, 1f)
    }

    val CONDENSED_MILK_FROM_MILK = cookingPot(recipeConsumer, { AddonItems.CONDENSED_MILK.get() }, { Items.GLASS_BOTTLE }, 1) { b ->
      b
        .unlockedByIngredients({ Items.MILK_BUCKET }, { Items.SUGAR }, { ModItems.MILK_BOTTLE.get() })
        .slowCooking()
        .build(
          listOf(
            DataIngredient.tag(ForgeTags.MILK),
            DataIngredient.items(Items.SUGAR),
          ),
          "",
          "_cooking_pot"
        )
    }

    val PUDDING = cookingPot(recipeConsumer, { AddonBlocks.PUDDING.get() }, { Items.BOWL }, 1) { b ->
      b
        .unlockedByIngredients(
          { AddonItems.CONDENSED_MILK.get() },
          { AddonItems.HEAVY_CREAM_BUCKET.get() },
          { Items.SUGAR },
          { ModItems.MILK_BOTTLE.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonItems.CONDENSED_MILK.get()),
            DataIngredient.items(AddonItems.CONDENSED_MILK.get()),
            DataIngredient.items(AddonItems.HEAVY_CREAM_BUCKET.get()),
            DataIngredient.items(AddonItems.HEAVY_CREAM_BUCKET.get()),
            DataIngredient.items(Items.SUGAR),
            DataIngredient.items(Items.SUGAR),
          ),
          "",
          "_cooking_pot"
        )
    }

    val FEIJOADA = cookingPot(recipeConsumer, { AddonBlocks.FEIJOADA_POT.get() }, { ModBlocks.COOKING_POT.get() }, 1) { b ->
      b
        .unlockedByIngredients(
          { AddonBlocks.BUDDING_BEANS_CROP.get() },
          { AddonBlocks.GARLIC_CROP.get() },
          { AddonItems.GARLIC_BULB.get() },
          { AddonItems.COLLARD_GREENS.get() },
          { ModItems.BACON.get() },
          { ModItems.BACON.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonBlocks.BUDDING_BEANS_CROP.get()),
            DataIngredient.items(AddonBlocks.BUDDING_BEANS_CROP.get()),
            DataIngredient.tag(AddonTags.ITEM.GARLIC),
            DataIngredient.items(ModItems.BACON.get()),
            DataIngredient.items(ModItems.ONION.get()),
            DataIngredient.items(AddonItems.COLLARD_GREENS.get()),
          ),
          "",
          "_cooking"
        )
    }

    val RAW_COXINHA = crafting(recipeConsumer, { AddonItems.RAW_COXINHA.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.items(ModItems.WHEAT_DOUGH.get()),
          DataIngredient.tag(AddonTags.ITEM.CHEESE),
          Ingredient.fromValues(
            Stream.of(
              Ingredient.TagValue(ForgeTags.RAW_CHICKEN),
              Ingredient.TagValue(ForgeTags.RAW_BEEF),
              Ingredient.TagValue(ForgeTags.RAW_PORK),
              Ingredient.TagValue(ForgeTags.RAW_MUTTON),
              Ingredient.ItemValue(ItemStack(Items.BROWN_MUSHROOM)),
              Ingredient.ItemValue(ItemStack(Items.RABBIT)),
            )
          ),
          DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()),
        )
      )
    }

    val COXINHA = cooking(recipeConsumer,
      { DataIngredient.items(AddonItems.RAW_COXINHA.get()) },
      { AddonItems.COXINHA.get() }
    ) { b ->
      b
        .comboFoodCooking(200, 2f)
    }

    val RAW_CASSAVA_FRITTERS = crafting(recipeConsumer, { AddonItems.RAW_CASSAVA_FRITTERS.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()),
          DataIngredient.tag(AddonTags.ITEM.BUTTER),
          DataIngredient.tag(AddonTags.ITEM.CHEESE),
          DataIngredient.items(AddonItems.COLLARD_GREENS.get()),
        )
      )
    }

    val CASSAVA_FRITTERS = cooking(recipeConsumer,
      { DataIngredient.items(AddonItems.RAW_CASSAVA_FRITTERS.get()) },
      { AddonItems.CASSAVA_FRITTERS.get() }
    ) { b ->
      b
        .comboFoodCooking(200, 2f)
    }

    val GUARANA_SODA = crafting(recipeConsumer, { AddonItems.GUARANA_SODA.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.items(Items.GLASS_BOTTLE),
          DataIngredient.items(AddonItems.GUARANA_POWDER.get()),
          DataIngredient.items(Blocks.ICE),
          DataIngredient.items(Items.SUGAR),
          DataIngredient.items(Items.SUGAR),
        )
      )
    }

    val ACAI_CREAM = cookingPot(recipeConsumer, { AddonItems.ACAI_CREAM.get() }, { Items.BOWL }, 1) { b ->
      b
        .unlockedByIngredients(
          { AddonBlocks.BUDDING_ACAI_BRANCH.get() },
          { AddonItems.CONDENSED_MILK.get() },
          { AddonItems.GUARANA_POWDER.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonBlocks.BUDDING_ACAI_BRANCH.get()),
            DataIngredient.items(AddonBlocks.BUDDING_ACAI_BRANCH.get()),
            DataIngredient.items(AddonItems.CONDENSED_MILK.get()),
            DataIngredient.items(AddonItems.GUARANA_POWDER.get()),
          ),
          "",
          "_cooking_pot"
        )
    }

    val COCONUT_CREAM = cookingPot(recipeConsumer, { AddonItems.COCONUT_CREAM.get() }, { Items.BOWL }, 1) { b ->
      b
        .unlockedByIngredients(
          { AddonItems.COCONUT_SLICE.get() },
          { AddonItems.CONDENSED_MILK.get() },
          { AddonItems.COCONUT_MILK.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonItems.COCONUT_SLICE.get()),
            DataIngredient.items(AddonItems.COCONUT_SLICE.get()),
            DataIngredient.items(AddonItems.COCONUT_MILK.get()),
            DataIngredient.items(AddonItems.CONDENSED_MILK.get()),
          ),
          "",
          "_cooking_pot"
        )
    }

    val BRIGADEIRO_CREAM = cookingPot(recipeConsumer, { AddonItems.BRIGADEIRO_CREAM.get() }, { Items.BOWL }, 1) { b ->
      b
        .unlockedByIngredients({ Items.COCOA_BEANS }, { AddonItems.CONDENSED_MILK.get() }, { AddonItems.BUTTER.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(Items.COCOA_BEANS),
            DataIngredient.items(Items.COCOA_BEANS),
            DataIngredient.items(AddonItems.CONDENSED_MILK.get()),
            DataIngredient.tag(AddonTags.ITEM.BUTTER),
          ),
          "",
          "_cooking_pot"
        )
    }

//  val PASTEL_DOUGH = cutting(recipeConsumer, { AddonItems.PASTEL_DOUGH.get() }, 2) { b -> b
//    .shovelTool()
//    .build(DataIngredient.items(ModItems.WHEAT_DOUGH.get()), "", "_from_wheat_dough")
//  }

    val CHICKEN_POT_PIE = crafting(recipeConsumer, { AddonBlocks.CHICKEN_POT_PIE.get() }) { b ->
      b
        .shaped(1) { c ->
          c
            .pattern("ODG")
            .pattern("CCC")
            .pattern("TPH")
            .define('D', ModItems.WHEAT_DOUGH.get())
            .define('T', ModItems.TOMATO_SAUCE.get())
            .define('O', ForgeTags.VEGETABLES_ONION)
            .define('G', AddonBlocks.GARLIC_CROP.get())
            .define('C', ForgeTags.COOKED_CHICKEN)
            .define('P', ModItems.PIE_CRUST.get())
            .define('H', AddonItems.HEAVY_CREAM_BUCKET.get())
        }
    }

    val CARROT_CAKE = crafting(recipeConsumer, { AddonBlocks.CARROT_CAKE.get() }) { b ->
      b
        .shaped(1) { c ->
          c
            .pattern("MMM")
            .pattern("SEW")
            .pattern("CCC")
            .define('C', ForgeTags.VEGETABLES_CARROT)
            .define('E', ForgeTags.EGGS)
            .define('M', ForgeTags.MILK)
            .define('S', Items.SUGAR)
            .define('W', Items.WHEAT)
        }
    }

    val CARROT_CAKE_WITH_CHOCOLATE = crafting(recipeConsumer, { AddonBlocks.CARROT_CAKE_WITH_CHOCOLATE.get() }) { b ->
      b
        .shapeless(
          1, "", "", listOf(
            DataIngredient.items(AddonBlocks.CARROT_CAKE.get()),
            DataIngredient.items(AddonItems.BRIGADEIRO_CREAM.get()),
          )
        )
    }

    val SWEET_LOVE_APPLE_TRAY = crafting(recipeConsumer, { AddonBlocks.SWEET_LOVE_APPLE_TRAY.get() }) { b ->
      b
        .unlockedBy {
          ItemPredicate.Builder.item()
            .of(AddonItems.SWEET_LOVE_APPLE.get())
            .build()
        }
        .shapeless(
          1, "", "", listOf(
            DataIngredient.items(Items.BOWL),
            DataIngredient.items(AddonItems.SWEET_LOVE_APPLE.get()),
            DataIngredient.items(AddonItems.SWEET_LOVE_APPLE.get()),
            DataIngredient.items(AddonItems.SWEET_LOVE_APPLE.get()),
            DataIngredient.items(AddonItems.SWEET_LOVE_APPLE.get()),
            DataIngredient.items(AddonItems.SWEET_LOVE_APPLE.get()),
            DataIngredient.items(AddonItems.SWEET_LOVE_APPLE.get()),
          )
        )
    }

    val YERBA_MATE_DRIED = cooking(recipeConsumer,
      {
        Ingredient.fromValues(
          Stream.of(
            Ingredient.ItemValue(ItemStack(AddonItems.YERBA_MATE_LEAVES.get())),
          )
        )
      },
      { AddonItems.DRIED_YERBA_MATE.get() }
    ) { b ->
      b
        .comboFoodCooking(200, 1f)
    }

    val YERBA_MATE_LEAVES = cutting(recipeConsumer, { AddonItems.YERBA_MATE_LEAVES.get() }, 1) { b ->
      b
        .knifeTool()
        .extraResult({ AddonItems.YERBA_MATE_LEAVES.get() }, 0.25f, 1)
        .build(DataIngredient.items(AddonBlocks.YERBA_MATE_BUSH.get()), "", "")
    }

    val TUCUPI_BOIL = cookingPot(recipeConsumer, { AddonItems.TUCUPI.get() }, { Items.GLASS_BOTTLE }, 3) { b ->
      b
        .unlockedByIngredients({ AddonBlocks.BUDDING_CASSAVA.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonBlocks.BUDDING_CASSAVA.get()),
            DataIngredient.items(AddonBlocks.BUDDING_CASSAVA.get()),
            DataIngredient.items(Items.WATER_BUCKET),
            DataIngredient.tag(AddonTags.ITEM.SALT),
          ),
          "",
          "_cooking"
        )
    }

    val FRIED_CASSAVA_WITH_BUTTER = cookingPot(recipeConsumer, { AddonItems.FRIED_CASSAVA_WITH_BUTTER.get() }, null, 1) { b ->
      b
        .unlockedByIngredients({ AddonBlocks.BUDDING_CASSAVA.get() }, { AddonItems.BUTTER.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonBlocks.BUDDING_CASSAVA.get()),
            DataIngredient.tag(AddonTags.ITEM.BUTTER),
          ),
          "",
          "_cooking"
        )
    }

    val COCONUT_DRINK = crafting(recipeConsumer, { AddonItems.COCONUT_DRINK.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.items(AddonBlocks.GREEN_COCONUT.get()),
          DataIngredient.items(Items.BAMBOO),
        )
      )
    }

    val COCONUT_MILK = crafting(recipeConsumer, { AddonItems.COCONUT_MILK.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.items(AddonBlocks.GREEN_COCONUT.get()),
          DataIngredient.items(Items.GLASS_BOTTLE),
        )
      )
    }

    val CHEESE_BREAD_DOUGH = crafting(recipeConsumer, { AddonItems.CHEESE_BREAD_DOUGH.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.tag(AddonTags.ITEM.CHEESE),
          DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()),
          DataIngredient.tag(AddonTags.ITEM.MILK),
          DataIngredient.tag(ForgeTags.EGGS),
          DataIngredient.tag(AddonTags.ITEM.SALT),
        )
      )
    }

    val CHEESE_BREAD = cooking(recipeConsumer,
      {
        Ingredient.fromValues(
          Stream.of(
            Ingredient.ItemValue(ItemStack(AddonItems.CHEESE_BREAD_DOUGH.get())),
          )
        )
      },
      { AddonItems.CHEESE_BREAD.get() }
    ) { b ->
      b
        .comboFoodCooking(200, 1f)
    }

    val COLLARD_GREENS_FAROFA = crafting(recipeConsumer, { AddonItems.COLLARD_GREENS_FAROFA.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.tag(AddonTags.ITEM.COLLARD_GREENS),
          DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()),
          DataIngredient.tag(AddonTags.ITEM.BUTTER),
          DataIngredient.tag(AddonTags.ITEM.GARLIC),
          DataIngredient.items(Items.BOWL),
        )
      )
    }

    val COLLARD_GREENS_SALAD = crafting(recipeConsumer, { AddonItems.COLLARD_GREENS_SALAD.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.tag(AddonTags.ITEM.COLLARD_GREENS),
          DataIngredient.tag(ForgeTags.VEGETABLES_ONION),
          DataIngredient.items(Items.BOWL),
        )
      )
    }

    val COOKED_CARIOCA_BEANS = cookingPot(recipeConsumer, { AddonItems.COOKED_CARIOCA_BEANS.get() }, { Items.BOWL }, 1) { b ->
      b
        .unlockedByIngredients(
          { AddonBlocks.CARIOCA_BEANS_CROP.get() },
          { AddonBlocks.BUDDING_BEANS_CROP.get() },
          { AddonItems.GARLIC_BULB.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonBlocks.CARIOCA_BEANS_CROP.get()),
            DataIngredient.tag(AddonTags.ITEM.GARLIC),
          ),
          "",
          "_cooking"
        )
    }

    val COOKED_BLACK_BEANS = cookingPot(recipeConsumer, { AddonItems.COOKED_BLACK_BEANS.get() }, { Items.BOWL }, 1) { b ->
      b
        .unlockedByIngredients(
          { AddonBlocks.CARIOCA_BEANS_CROP.get() },
          { AddonBlocks.BUDDING_BEANS_CROP.get() },
          { AddonItems.GARLIC_BULB.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonBlocks.BUDDING_BEANS_CROP.get()),
            DataIngredient.tag(AddonTags.ITEM.GARLIC),
          ),
          "",
          "_cooking"
        )
    }


    val TROPEIRO_BEANS = cookingPot(recipeConsumer, { AddonItems.TROPEIRO_BEANS.get() }, { Items.BOWL }, 2) { b ->
      b
        .unlockedByIngredients(
          { AddonItems.CASSAVA_FLOUR.get() },
          { AddonBlocks.CARIOCA_BEANS_CROP.get() },
          { ModItems.BACON.get() },
          { AddonItems.GARLIC_BULB.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonBlocks.CARIOCA_BEANS_CROP.get()),
            DataIngredient.items(AddonBlocks.CARIOCA_BEANS_CROP.get()),
            DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()),
            DataIngredient.tag(ForgeTags.RAW_PORK),
            DataIngredient.tag(AddonTags.ITEM.COLLARD_GREENS),
            DataIngredient.tag(AddonTags.ITEM.GARLIC),
          ),
          "",
          "_cooking"
        )
    }

    val FRIED_FISH_WITH_ACAI = cookingPot(recipeConsumer, { AddonItems.FRIED_FISH_WITH_ACAI.get() }, { Items.BOWL }, 1) { b ->
      b
        .unlockedByIngredients(
          { Items.COD },
          { AddonBlocks.BUDDING_ACAI_BRANCH.get() },
          { AddonItems.CASSAVA_FLOUR.get() },
          { AddonItems.BUTTER.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.tag(ForgeTags.RAW_FISHES_COD),
            DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()),
            DataIngredient.items(AddonBlocks.BUDDING_ACAI_BRANCH.get()),
            DataIngredient.tag(AddonTags.ITEM.BUTTER),
          ),
          "",
          "_cooking"
        )
    }

    val ANGU = cookingPot(recipeConsumer, { AddonItems.ANGU.get() }, { Items.BOWL }, 2) { b ->
      b
        .unlockedByIngredients({ Items.WATER_BUCKET }, { AddonItems.CORN_FLOUR.get() }, { AddonItems.SALT.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(Items.WATER_BUCKET),
            DataIngredient.items(AddonItems.CORN_FLOUR.get()),
            DataIngredient.items(AddonItems.CORN_FLOUR.get()),
            DataIngredient.tag(AddonTags.ITEM.SALT),
          ),
          "",
          "_cooking"
        )
    }

    val CHIMARRAO = crafting(recipeConsumer, { AddonItems.CHIMARRAO.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.items(AddonItems.DRIED_YERBA_MATE.get()),
          DataIngredient.items(Items.WATER_BUCKET),
          DataIngredient.items(Items.BOWL),
        )
      )
    }

    val BUTTERED_CORN = cookingPot(recipeConsumer, { AddonItems.BUTTERED_CORN.get() }, null, 1) { b ->
      b
        .unlockedByIngredients({ AddonBlocks.BUDDING_CORN.get() }, { AddonItems.BUTTER.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.tag(AddonTags.ITEM.CORN),
            DataIngredient.tag(AddonTags.ITEM.BUTTER),
          ),
          "",
          "_cooking"
        )
    }

    val SALPICAO = cookingPot(recipeConsumer, { AddonItems.SALPICAO.get() }, { Items.BOWL }, 1) { b ->
      b
        .unlockedByIngredients(
          { Items.COOKED_CHICKEN },
          { Items.CARROT },
          { Items.APPLE },
          { AddonItems.BEAN_POD.get() },
          { AddonItems.HEAVY_CREAM_BUCKET.get() },
          { AddonBlocks.BUDDING_CORN.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.tag(ForgeTags.COOKED_CHICKEN),
            DataIngredient.tag(ForgeTags.VEGETABLES_CARROT),
            DataIngredient.tag(AddonTags.ITEM.KERNELS),
            DataIngredient.items(AddonItems.BEAN_POD.get()),
            DataIngredient.items(Items.APPLE),
            DataIngredient.items(AddonItems.HEAVY_CREAM_BUCKET.get()),
          ),
          "",
          "_cooking"
        )
    }

    val LEMONADE = crafting(recipeConsumer, { AddonItems.LEMONADE.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.tag(AddonTags.ITEM.LEMON),
          DataIngredient.items(Items.SUGAR),
          DataIngredient.items(Items.WATER_BUCKET),
          DataIngredient.items(Items.GLASS_BOTTLE),
        )
      )
    }

    val COLLARD_LEMONADE = crafting(recipeConsumer, { AddonItems.COLLARD_LEMONADE.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.tag(AddonTags.ITEM.COLLARD_GREENS),
          DataIngredient.tag(AddonTags.ITEM.LEMON),
          DataIngredient.items(Items.SUGAR),
          DataIngredient.items(Items.WATER_BUCKET),
          DataIngredient.items(Items.GLASS_BOTTLE),
        )
      )
    }

    val GUARANA_JUICE = crafting(recipeConsumer, { AddonItems.GUARANA_JUICE.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          DataIngredient.items(AddonItems.GUARANA_POWDER.get()),
          DataIngredient.items(Items.SUGAR),
          DataIngredient.items(Items.WATER_BUCKET),
          DataIngredient.items(Items.GLASS_BOTTLE),
        )
      )
    }

    val ACAI_TEA_WITH_GUARANA = cookingPot(recipeConsumer, { AddonItems.ACAI_TEA_WITH_GUARANA.get() }, { Items.GLASS_BOTTLE }, 2) { b ->
      b
        .unlockedByIngredients(
          { AddonBlocks.BUDDING_ACAI_BRANCH.get() },
          { AddonItems.GUARANA_POWDER.get() },
          { Items.WATER_BUCKET },
          { Items.SUGAR },
          { Items.GLASS_BOTTLE })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonBlocks.BUDDING_ACAI_BRANCH.get()),
            DataIngredient.items(AddonBlocks.BUDDING_ACAI_BRANCH.get()),
            DataIngredient.items(AddonItems.GUARANA_POWDER.get()),
            DataIngredient.items(Items.WATER_BUCKET),
            DataIngredient.items(Items.SUGAR),
          ),
          "",
          "_cooking"
        )
    }

    val COUSCOUS = cookingPot(recipeConsumer, { AddonItems.COUSCOUS.get() },  { Items.BOWL }, 1) { b ->
      b
        .unlockedByIngredients({ AddonItems.CORN_FLOUR.get() }, { AddonItems.BUTTER.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonItems.CORN_FLOUR.get()),
            DataIngredient.items(AddonItems.CORN_FLOUR.get()),
            DataIngredient.tag(AddonTags.ITEM.BUTTER),
          ),
          "",
          "_cooking"
        )
    }

    val BROA = crafting(recipeConsumer, { AddonItems.BROA.get() }) { b ->
      b.shapeless(
        4, "", "", listOf(
          DataIngredient.items(AddonItems.CORN_FLOUR.get()),
          DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()),
          DataIngredient.tag(AddonTags.ITEM.BUTTER),
        )
      )
    }

    val BRAZILIAN_DINNER = crafting(recipeConsumer, { AddonItems.BRAZILIAN_DINNER.get() }) { b ->
      b.shapeless(
        1, "", "", listOf(
          Ingredient.fromValues(
            Stream.of(
              Ingredient.ItemValue(ItemStack(AddonItems.COOKED_CARIOCA_BEANS.get())),
              Ingredient.ItemValue(ItemStack(AddonItems.COOKED_BLACK_BEANS.get())),
            )
          ),
          DataIngredient.items(ModItems.COOKED_RICE.get()),
          DataIngredient.items(ModItems.FRIED_EGG.get()),
        )
      )
    }

    val GREEN_SOUP_POT = cookingPot(recipeConsumer, { AddonBlocks.GREEN_SOUP_POT.get() }, { ModBlocks.COOKING_POT.get() }, 1) { b ->
      b
        .unlockedByIngredients({ AddonItems.BEAN_POD.get() }, { Items.COOKED_PORKCHOP }, { AddonItems.GARLIC_BULB.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.items(AddonItems.BEAN_POD.get()),
            DataIngredient.items(AddonItems.BEAN_POD.get()),
            DataIngredient.tag(ForgeTags.COOKED_PORK),
            DataIngredient.tag(AddonTags.ITEM.GARLIC),
          ),
          "",
          "_cooking"
        )
    }

    val FISH_MOQUECA_POT = cookingPot(recipeConsumer, { AddonBlocks.FISH_MOQUECA_POT.get() }, { ModBlocks.COOKING_POT.get() },  1) { b ->
      b
        .unlockedByIngredients(
          { Items.COD },
          { ModItems.ONION.get() },
          { ModItems.TOMATO.get() },
          { AddonItems.HEAVY_CREAM_BUCKET.get() },
          { AddonItems.LEMON.get() },
          { AddonItems.COCONUT_MILK.get() })
        .normalCooking()
        .build(
          listOf(
            DataIngredient.tag(ForgeTags.RAW_FISHES_COD),
            DataIngredient.tag(ForgeTags.VEGETABLES_ONION),
            DataIngredient.tag(ForgeTags.VEGETABLES_TOMATO),
            DataIngredient.items(AddonItems.HEAVY_CREAM_BUCKET.get()),
            DataIngredient.tag(AddonTags.ITEM.LEMON),
            DataIngredient.items(AddonItems.COCONUT_MILK.get()),
          ),
          "",
          "_cooking"
        )
    }

    val STROGANOFF_POT = cookingPot(recipeConsumer, { AddonBlocks.STROGANOFF_POT.get() }, { ModBlocks.COOKING_POT.get() }, 1) { b ->
      b
        .unlockedByIngredients(
          { Items.COOKED_BEEF },
          { ModItems.ONION.get() },
          { ModItems.TOMATO.get() },
          { AddonItems.HEAVY_CREAM_BUCKET.get() },
          { AddonItems.GARLIC_BULB.get() })
        .normalCooking()
        .build(
          listOf(
            Ingredient.fromValues(
              Stream.of(
                Ingredient.TagValue(ForgeTags.RAW_CHICKEN),
                Ingredient.TagValue(ForgeTags.RAW_BEEF),
                Ingredient.TagValue(ForgeTags.RAW_MUTTON),
                Ingredient.ItemValue(ItemStack(Items.RABBIT)),
              )
            ),
            DataIngredient.tag(AddonTags.ITEM.GARLIC),
            DataIngredient.tag(ForgeTags.VEGETABLES_ONION),
            DataIngredient.tag(ForgeTags.VEGETABLES_TOMATO),
            DataIngredient.items(AddonItems.HEAVY_CREAM_BUCKET.get()),
            DataIngredient.items(Items.BROWN_MUSHROOM),
          ),
          "",
          "_cooking"
        )
    }
  }


  fun cutting(
    recipeConsumer: Consumer<FinishedRecipe>,
    result: Supplier<ItemLike>,
    amount: Int = 1,
    builder: UnaryOperator<CustomCuttingRecipeBuilder> = UnaryOperator.identity()
  ): Set<ResourceLocation> {
    val allRecipes = CustomCuttingRecipeBuilder(AddonContent.MOD_ID, result, amount).apply(builder).getRecipes(recipeConsumer)
    all.addAll(allRecipes)
    return allRecipes
  }

  fun cookingPot(
    recipeConsumer: Consumer<FinishedRecipe>,
    result: Supplier<ItemLike>,
    foodContainer: Supplier<ItemLike>? = null,
    amount: Int = 1,
    builder: UnaryOperator<CustomCookingPotRecipeBuilder> = UnaryOperator.identity()
  ): Set<ResourceLocation> {
    val allRecipes = CustomCookingPotRecipeBuilder(AddonContent.MOD_ID, result, foodContainer, amount).apply(builder).getRecipes(recipeConsumer)
    all.addAll(allRecipes)
    return allRecipes
  }
}