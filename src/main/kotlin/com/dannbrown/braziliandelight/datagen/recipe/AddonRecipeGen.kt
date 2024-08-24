package com.dannbrown.braziliandelight.datagen.recipe

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonItems
import com.dannbrown.braziliandelight.init.AddonTags
import com.dannbrown.deltaboxlib.registry.datagen.DeltaboxRecipeProvider
import com.tterrag.registrate.util.DataIngredient
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.DataGenerator
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.common.Tags
import net.minecraftforge.common.ToolActions
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab
import vectorwing.farmersdelight.common.crafting.ingredient.ToolActionIngredient
import vectorwing.farmersdelight.common.registry.ModBlocks
import vectorwing.farmersdelight.common.registry.ModItems
import vectorwing.farmersdelight.common.tag.ForgeTags
import vectorwing.farmersdelight.common.tag.ModTags
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder
import java.util.function.Consumer
import java.util.function.Supplier
import java.util.function.UnaryOperator
import java.util.stream.Stream

class AddonRecipeGen(generator: DataGenerator) : DeltaboxRecipeProvider(generator.packOutput, AddonContent.MOD_ID) {
  class CustomCuttingRecipeBuilder(private val modId: String, result: Supplier<ItemLike>, amount: Int) {
    private var result: Supplier<ItemLike>? = null
    private var amount: Int = 0
    private var soundId: String? = null
    private var prefix: String = ""
    private var suffix: String = "_cutting"
    private val allRecipes: MutableList<DeltaboxRecipeProvider.GeneratedRecipe> = ArrayList()
    private var toolIngredient: Ingredient = Ingredient.of(ForgeTags.TOOLS_KNIVES)
    private val extraResults: MutableList<Triple<Supplier<ItemLike>, Float, Int>> = ArrayList()

    init {
      this.result = result
      this.amount = amount
    }

    fun apply(builder: UnaryOperator<CustomCuttingRecipeBuilder>): CustomCuttingRecipeBuilder {
      return builder.apply(this)
    }

    fun prefix(prefix: String): CustomCuttingRecipeBuilder {
      this.prefix = prefix
      return this
    }

    fun suffix(suffix: String): CustomCuttingRecipeBuilder {
      this.suffix = suffix
      return this
    }

    fun amount(amount: Int): CustomCuttingRecipeBuilder {
      this.amount = amount
      return this
    }

    fun soundId(soundId: String): CustomCuttingRecipeBuilder {
      this.soundId = soundId
      return this
    }

    fun axeStripTool(): CustomCuttingRecipeBuilder {
      this.toolIngredient = ToolActionIngredient(ToolActions.AXE_STRIP)
      return this
    }

    fun axeDigTool(): CustomCuttingRecipeBuilder {
      this.toolIngredient = ToolActionIngredient(ToolActions.AXE_DIG)
      return this
    }

    fun shearsTool(): CustomCuttingRecipeBuilder {
      this.toolIngredient = Ingredient.of(Tags.Items.SHEARS)
      return this
    }

    fun shovelTool(): CustomCuttingRecipeBuilder {
      this.toolIngredient = ToolActionIngredient(ToolActions.SHOVEL_DIG)
      return this
    }

    fun pickaxeTool(): CustomCuttingRecipeBuilder {
      this.toolIngredient = ToolActionIngredient(ToolActions.PICKAXE_DIG)
      return this
    }

    fun hoeTool(): CustomCuttingRecipeBuilder {
      this.toolIngredient = ToolActionIngredient(ToolActions.HOE_DIG)
      return this
    }

    fun knifeTool(): CustomCuttingRecipeBuilder {
      this.toolIngredient = Ingredient.of(ForgeTags.TOOLS_KNIVES)
      return this
    }

    fun extraResult(result: Supplier<ItemLike>, chance: Float, amount: Int): CustomCuttingRecipeBuilder {
      extraResults.add(Triple(result, chance, amount))
      return this
    }


    fun build(
      ingredient: DataIngredient,
      chance: Int,
      builder: UnaryOperator<CuttingBoardRecipeBuilder> = UnaryOperator.identity(),
    ): CustomCuttingRecipeBuilder {
      return build(ingredient, chance, prefix, suffix, builder)
    }

    fun build(
      ingredient: DataIngredient,
      builder: UnaryOperator<CuttingBoardRecipeBuilder> = UnaryOperator.identity(),
    ): CustomCuttingRecipeBuilder {
      return build(ingredient, 100, prefix, suffix, builder)
    }

    fun build(
      ingredient: DataIngredient,
      prefix: String,
      suffix: String,
      builder: UnaryOperator<CuttingBoardRecipeBuilder> = UnaryOperator.identity(),
    ): CustomCuttingRecipeBuilder {
      return build(ingredient, 100, prefix, suffix, builder)
    }


    fun build(
      ingredient: DataIngredient,
      chance: Int,
      prefix: String,
      suffix: String,
      builder: UnaryOperator<CuttingBoardRecipeBuilder> = UnaryOperator.identity(),
    ): CustomCuttingRecipeBuilder {
      val generatedRecipe = DeltaboxRecipeProvider.GeneratedRecipe { consumer: Consumer<FinishedRecipe> ->
        val builder = builder.apply(
          CuttingBoardRecipeBuilder.cuttingRecipe(
            ingredient,
            toolIngredient,
            result?.get(),
            amount,
            chance
          )
        )

        extraResults.forEach { extra ->
          val extraResult = extra.first.get()
          val resultChance = extra.second
          val resultAmount = extra.third
          builder.addResultWithChance(extraResult, resultChance, resultAmount)
        }

        if (soundId != null) {
          builder.addSound(soundId)
        }


        builder.build(
          { result -> consumer.accept(result) },
          DeltaboxRecipeProvider.createSimpleLocation(modId, "cutting", result!!, prefix, suffix)
        )
      }

      allRecipes.add(generatedRecipe)

      return this
    }

    fun getRecipes(): List<DeltaboxRecipeProvider.GeneratedRecipe> {
      return allRecipes
    }
  }

  class CustomCookingPotRecipeBuilder(private val modId: String, result: Supplier<ItemLike>, amount: Int) {
    val FAST_COOKING: Int = 100 // 5 seconds
    val NORMAL_COOKING: Int = 200 // 10 seconds
    val SLOW_COOKING: Int = 400 // 20 seconds

    val SMALL_EXP: Float = 0.35f
    val MEDIUM_EXP: Float = 1.0f
    val LARGE_EXP: Float = 2.0f

    private var result: Supplier<ItemLike>? = null
    private var amount: Int = 0
    private var prefix: String = ""
    private var suffix: String = "_cooking_pot"
    private val allRecipes: MutableList<GeneratedRecipe> = ArrayList()
    private var unlockedBy: Supplier<ItemPredicate>? = null
    private val unlockedByIngredients: MutableList<Supplier<ItemLike>> = ArrayList()
    private var cookingTime: Int = NORMAL_COOKING
    private var expAmount: Float = MEDIUM_EXP
    private var recipeBookTab: CookingPotRecipeBookTab = CookingPotRecipeBookTab.MISC
    private var foodContainer: Supplier<ItemLike>? = null

    init {
      this.result = result
      this.amount = amount
    }

    fun apply(builder: UnaryOperator<CustomCookingPotRecipeBuilder>): CustomCookingPotRecipeBuilder {
      return builder.apply(this)
    }

    fun prefix(prefix: String): CustomCookingPotRecipeBuilder {
      this.prefix = prefix
      return this
    }

    fun suffix(suffix: String): CustomCookingPotRecipeBuilder {
      this.suffix = suffix
      return this
    }

    fun amount(amount: Int): CustomCookingPotRecipeBuilder {
      this.amount = amount
      return this
    }

    fun unlockedBy(unlockedBy: Supplier<ItemPredicate>): CustomCookingPotRecipeBuilder {
      this.unlockedBy = unlockedBy
      return this
    }

    fun unlockedByIngredients(vararg ingredients: Supplier<ItemLike>): CustomCookingPotRecipeBuilder {
      this.unlockedByIngredients.addAll(ingredients)
      return this
    }

    fun recipeBookTab(tab: CookingPotRecipeBookTab): CustomCookingPotRecipeBuilder {
      this.recipeBookTab = tab
      return this
    }

    fun foodContainer(container: Supplier<ItemLike>): CustomCookingPotRecipeBuilder {
      this.foodContainer = container
      return this
    }

    fun slowCooking(): CustomCookingPotRecipeBuilder {
      this.cookingTime = SLOW_COOKING
      return this
    }

    fun fastCooking(): CustomCookingPotRecipeBuilder {
      this.cookingTime = FAST_COOKING
      return this
    }

    fun normalCooking(): CustomCookingPotRecipeBuilder {
      this.cookingTime = NORMAL_COOKING
      return this
    }

    fun cookingTime(time: Int): CustomCookingPotRecipeBuilder {
      this.cookingTime = time
      return this
    }

    fun smallExp(): CustomCookingPotRecipeBuilder {
      this.expAmount = SMALL_EXP
      return this
    }

    fun mediumExp(): CustomCookingPotRecipeBuilder {
      this.expAmount = MEDIUM_EXP
      return this
    }

    fun largeExp(): CustomCookingPotRecipeBuilder {
      this.expAmount = LARGE_EXP
      return this
    }

    fun expAmount(amount: Float): CustomCookingPotRecipeBuilder {
      this.expAmount = amount
      return this
    }

    fun build(
      ingredients: List<Ingredient>,
      prefix: String,
      suffix: String,
      builder: UnaryOperator<CookingPotRecipeBuilder> = UnaryOperator.identity(),
    ): CustomCookingPotRecipeBuilder {
      val generatedRecipe = GeneratedRecipe { consumer: Consumer<FinishedRecipe> ->
        val builder = builder.apply(
          CookingPotRecipeBuilder.cookingPotRecipe(
            result?.get(),
            amount,
            cookingTime,
            expAmount,
            foodContainer?.get()
          )
        )

        ingredients.forEach { ingredient ->
          builder.addIngredient(ingredient)
        }

        if (unlockedByIngredients.isNotEmpty()) {
          builder.unlockedByAnyIngredient(*unlockedByIngredients.map { it.get() }.toTypedArray())
        } else if (unlockedBy != null) {
          builder.unlockedBy("has_item", inventoryTrigger(unlockedBy?.get()))
        } else {
          builder.unlockedBy(
            "has_item",
            inventoryTrigger(
              ItemPredicate.Builder.item()
                .of(Items.AIR)
                .build()
            )
          )
        }

        builder.setRecipeBookTab(recipeBookTab)

        builder.build(
          { result -> consumer.accept(result) },
          createSimpleLocation(modId, "cooking_pot", result!!, prefix, suffix)
        )
      }

      allRecipes.add(generatedRecipe)

      return this
    }

    fun getRecipes(): List<DeltaboxRecipeProvider.GeneratedRecipe> {
      return allRecipes
    }
  }

  fun cutting(
    result: Supplier<ItemLike>,
    amount: Int = 1,
    builder: UnaryOperator<CustomCuttingRecipeBuilder> = UnaryOperator.identity()
  ): List<GeneratedRecipe> {
    val allCrafting = CustomCuttingRecipeBuilder(AddonContent.MOD_ID, result, amount).apply(builder)
      .getRecipes()

    all.addAll(allCrafting)
    return allCrafting
  }

  fun cookingPot(
    result: Supplier<ItemLike>,
    amount: Int = 1,
    builder: UnaryOperator<CustomCookingPotRecipeBuilder> = UnaryOperator.identity()
  ): List<GeneratedRecipe> {
    val allCrafting = CustomCookingPotRecipeBuilder(AddonContent.MOD_ID, result, amount).apply(builder)
      .getRecipes()

    all.addAll(allCrafting)
    return allCrafting
  }


  val REPUGNANT_ARROW = crafting({ AddonItems.REPUGNANT_ARROW.get() }) { b ->
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


  val SALT_BUCKET_FROM_WATER_SMELTING = cooking(
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

  val SALT_FROM_SALT_BUCKET = crafting({ AddonItems.SALT.get() }) { b ->
    b.shapeless(1, "", "_bucket", listOf(DataIngredient.items(AddonItems.SALT_BUCKET.get())))
  }

  val BUTTER_FROM_HEAVY_CREAM = crafting({ AddonItems.BUTTER.get() }) { b ->
    b.shapeless(
      1,
      "",
      "",
      listOf(DataIngredient.tag(AddonTags.ITEM.SALT), DataIngredient.items(AddonItems.HEAVY_CREAM_BUCKET.get()))
    )
  }

  val COOKED_SHRIMP = cooking(
    { DataIngredient.items(AddonItems.SHRIMP.get()) },
    { AddonItems.COOKED_SHRIMP.get() }
  ) { b ->
    b
      .comboFoodCooking(200, 2f)
  }

  val SLICE_FROM_MINAS_CHEESE = crafting({ AddonItems.MINAS_CHEESE_SLICE.get() }) { b ->
    b.shapeless(4, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.MINAS_CHEESE.get())))
  }

  val MINAS_CHEESE_TO_SLICE = crafting({ AddonBlocks.MINAS_CHEESE.get() }) { b ->
    b.shapeless(
      1, "", "_from_slice", listOf(
        DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
        DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
        DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
        DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
      )
    )
  }

  val SLICE_FROM_CHICKEN_POT_PIE = crafting({ AddonItems.CHICKEN_POT_PIE_SLICE.get() }) { b ->
    b.shapeless(4, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CHICKEN_POT_PIE.get())))
  }

  val CHICKEN_POT_PIE_TO_SLICE = crafting({ AddonBlocks.CHICKEN_POT_PIE.get() }) { b ->
    b.shapeless(
      1, "", "_from_slice", listOf(
        DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
        DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
        DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
        DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
      )
    )
  }

  val SLICE_FROM_CARROT_CAKE = crafting({ AddonItems.CARROT_CAKE_SLICE.get() }) { b ->
    b.shapeless(7, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CARROT_CAKE.get())))
  }

  val CARROT_CAKE_TO_SLICE = crafting({ AddonBlocks.CARROT_CAKE.get() }) { b ->
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

  val SLICE_FROM_CARROT_CAKE_WITH_CHOCOLATE = crafting({ AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get() }) { b ->
    b.shapeless(7, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CARROT_CAKE_WITH_CHOCOLATE.get())))
  }

  val CARROT_CAKE_WITH_CHOCOLATE_TO_SLICE = crafting({ AddonBlocks.CARROT_CAKE_WITH_CHOCOLATE.get() }) { b ->
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

  val MINAS_CHEESE_ON_A_STICK = crafting({ AddonItems.MINAS_CHEESE_ON_A_STICK.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
        DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
        DataIngredient.items(Items.STICK),
      )
    )
  }


  val GRILLED_CHEESE_ON_A_STICK = cooking(
    { DataIngredient.items(AddonItems.MINAS_CHEESE_ON_A_STICK.get()) },
    { AddonItems.GRILLED_CHEESE_ON_A_STICK.get() }
  ) { b ->
    b
      .comboFoodCooking(200, 2f)
  }

  val SWEET_LOVE_APPLE = crafting({ AddonItems.SWEET_LOVE_APPLE.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.items(Items.SUGAR),
        DataIngredient.items(AddonItems.CONDENSED_MILK.get()),
        DataIngredient.items(Items.STICK),
        DataIngredient.items(Items.APPLE),
      )
    )
  }

  val ROASTED_GARLIC = cooking(
    { DataIngredient.items(AddonItems.GARLIC_BULB.get()) },
    { AddonItems.ROASTED_GARLIC.get() }
  ) { b ->
    b
      .comboFoodCooking(200, 2f)
  }

  val GARLIC_CLOVE = cutting({ AddonBlocks.GARLIC_CROP.get() }, 2) { b ->
    b
      .knifeTool()
      .build(DataIngredient.items(AddonItems.GARLIC_BULB.get()), "", "")
  }

  val LEMON_SLICE = cutting({ AddonItems.LEMON_SLICE.get() }, 2) { b ->
    b
      .knifeTool()
      .build(DataIngredient.items(AddonItems.LEMON.get()), "", "")
  }

  val BEANS = cutting({ Blocks.AIR }, 0) { b ->
    b
      .knifeTool()
      .extraResult({ AddonBlocks.BUDDING_BEANS_CROP }, 0.5f, 1)
      .extraResult({ AddonBlocks.CARIOCA_BEANS_CROP }, 0.5f, 1)
      .build(DataIngredient.items(AddonItems.BEAN_POD.get()), "bean_pod_", "_to_beans")
  }


  val GARAPA = crafting({ AddonItems.GARAPA.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.items(Items.GLASS_BOTTLE),
        DataIngredient.items(Items.SUGAR_CANE),
        DataIngredient.items(Items.SUGAR_CANE),
        DataIngredient.items(Items.SUGAR_CANE),
      )
    )
  }

  val GUARANA_POWDER_FROM_GUARANA = crafting({ AddonItems.GUARANA_POWDER.get() }) { b ->
    b.shapeless(
      2, "", "_from_guarana", listOf(
        DataIngredient.items(AddonItems.GUARANA_FRUIT.get()),
        DataIngredient.items(AddonItems.GUARANA_FRUIT.get()),
      )
    )
  }

  val CASSAVA_FLOUR_FROM_CASSAVA = crafting({ AddonItems.CASSAVA_FLOUR.get() }) { b ->
    b.shapeless(
      1, "", "_from_cassava", listOf(
        DataIngredient.items(AddonBlocks.BUDDING_CASSAVA.get()),
      )
    )
  }

  val GUARANA_SEEDS_FROM_GUARANA = crafting({ AddonBlocks.BUDDING_GUARANA.get() }) { b ->
    b.shapeless(
      1, "", "_from_fruit", listOf(
        DataIngredient.items(AddonItems.GUARANA_FRUIT.get()),
      )
    )
  }

  val COLLARD_SEEDS_FROM_COLLARD_GREENS = crafting({ AddonBlocks.COLLARD_GREENS_CROP.get() }) { b ->
    b.shapeless(
      1, "", "_from_greens", listOf(
        DataIngredient.items(AddonItems.COLLARD_GREENS.get()),
      )
    )
  }

  val COFFEE_SEEDS_FROM_COFFEE_BERRIES = crafting({ AddonBlocks.BUDDING_COFFEE.get() }) { b ->
    b.shapeless(
      1, "", "_from_berries", listOf(
        DataIngredient.items(AddonItems.COFFEE_BERRIES.get()),
      )
    )
  }

  val KERNELS_FROM_CORN_CUTTING = cutting({ AddonBlocks.BUDDING_CORN.get() }, 2) { b ->
    b
      .knifeTool()
      .extraResult({ AddonBlocks.WHITE_KERNELS_CROP }, 0.05f, 1)
      .build(DataIngredient.items(AddonItems.CORN.get()), "", "_to_kernels")
  }

  val KERNELS_FROM_CORN_SHAPELESS = crafting({ AddonBlocks.BUDDING_CORN.get() }) { b ->
    b.shapeless(
      1, "", "_from_corn", listOf(
        DataIngredient.items(AddonItems.CORN.get()),
      )
    )
  }

  val COOKED_CORN = cooking(
    { DataIngredient.items(AddonItems.CORN.get()) },
    { AddonItems.COOKED_CORN.get() }
  ) { b ->
    b
      .comboFoodCooking(200, 1f)
  }

  val COCONUT_SLICE = cutting({ AddonItems.COCONUT_SLICE.get() }, 2) { b ->
    b
      .axeDigTool()
      .build(DataIngredient.items(AddonBlocks.COCONUT.get()), "", "")
  }

  val COFFEE_BEANS_FROM_COFFEE_BERRIES = cooking(
    { DataIngredient.items(AddonItems.COFFEE_BERRIES.get()) },
    { AddonItems.COFFEE_BEANS.get() }
  ) { b ->
    b
      .comboFoodCooking(200, 1f)
  }

  val CONDENSED_MILK_FROM_MILK = cookingPot({ AddonItems.CONDENSED_MILK.get() }, 1) { b ->
    b
      .unlockedByIngredients({ Items.MILK_BUCKET }, { Items.SUGAR }, { ModItems.MILK_BOTTLE.get() })
      .slowCooking()
      .foodContainer { Items.GLASS_BOTTLE }
      .build(
        listOf(
          DataIngredient.tag(ForgeTags.MILK),
          DataIngredient.items(Items.SUGAR),
        ),
        "",
        "_cooking_pot"
      )
  }

  val PUDDING = cookingPot({ AddonBlocks.PUDDING.get() }, 1) { b ->
    b
      .unlockedByIngredients(
        { AddonItems.CONDENSED_MILK.get() },
        { AddonItems.HEAVY_CREAM_BUCKET.get() },
        { Items.SUGAR },
        { ModItems.MILK_BOTTLE.get() })
      .normalCooking()
      .foodContainer { Items.BOWL }
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

  val FEIJOADA = cookingPot({ AddonBlocks.FEIJOADA_POT.get() }, 1) { b ->
    b
      .unlockedByIngredients(
        { AddonBlocks.BUDDING_BEANS_CROP.get() },
        { AddonBlocks.GARLIC_CROP.get() },
        { AddonItems.GARLIC_BULB.get() },
        { AddonItems.COLLARD_GREENS.get() },
        { ModItems.BACON.get() },
        { ModItems.BACON.get() })
      .normalCooking()
      .foodContainer { ModBlocks.COOKING_POT.get() }
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

  val RAW_COXINHA = crafting({ AddonItems.RAW_COXINHA.get() }) { b ->
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

  val COXINHA = cooking(
    { DataIngredient.items(AddonItems.RAW_COXINHA.get()) },
    { AddonItems.COXINHA.get() }
  ) { b ->
    b
      .comboFoodCooking(200, 2f)
  }

  val RAW_CASSAVA_FRITTERS = crafting({ AddonItems.RAW_CASSAVA_FRITTERS.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()),
        DataIngredient.tag(AddonTags.ITEM.BUTTER),
        DataIngredient.tag(AddonTags.ITEM.CHEESE),
        DataIngredient.items(AddonItems.COLLARD_GREENS.get()),
      )
    )
  }

  val CASSAVA_FRITTERS = cooking(
    { DataIngredient.items(AddonItems.RAW_CASSAVA_FRITTERS.get()) },
    { AddonItems.CASSAVA_FRITTERS.get() }
  ) { b ->
    b
      .comboFoodCooking(200, 2f)
  }

  val GUARANA_SODA = crafting({ AddonItems.GUARANA_SODA.get() }) { b ->
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

  val ACAI_CREAM = crafting({ AddonItems.ACAI_CREAM.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.items(AddonBlocks.BUDDING_ACAI_BRANCH.get()),
        DataIngredient.items(AddonBlocks.BUDDING_ACAI_BRANCH.get()),
        DataIngredient.items(AddonItems.GUARANA_POWDER.get()),
        DataIngredient.items(Items.BOWL),
      )
    )
  }

  val BRIGADEIRO_CREAM = cookingPot({ AddonItems.BRIGADEIRO_CREAM.get() }, 1) { b ->
    b
      .unlockedByIngredients({ Items.COCOA_BEANS }, { AddonItems.CONDENSED_MILK.get() }, { AddonItems.BUTTER.get() })
      .normalCooking()
      .foodContainer { Items.BOWL }
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

//  val PASTEL_DOUGH = cutting({ AddonItems.PASTEL_DOUGH.get() }, 2) { b -> b
//    .shovelTool()
//    .build(DataIngredient.items(ModItems.WHEAT_DOUGH.get()), "", "_from_wheat_dough")
//  }

  val CHICKEN_POT_PIE = crafting({ AddonBlocks.CHICKEN_POT_PIE.get() }) { b ->
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

  val CARROT_CAKE = crafting({ AddonBlocks.CARROT_CAKE.get() }) { b ->
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

  val CARROT_CAKE_WITH_CHOCOLATE = crafting({ AddonBlocks.CARROT_CAKE_WITH_CHOCOLATE.get() }) { b ->
    b
      .shapeless(
        1, "", "", listOf(
          DataIngredient.items(AddonBlocks.CARROT_CAKE.get()),
          DataIngredient.items(AddonItems.BRIGADEIRO_CREAM.get()),
        )
      )
  }

  val SWEET_LOVE_APPLE_TRAY = crafting({ AddonBlocks.SWEET_LOVE_APPLE_TRAY.get() }) { b ->
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

  val YERBA_MATE_DRIED = cooking(
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

  val YERBA_MATE_LEAVES = cutting({ AddonItems.YERBA_MATE_LEAVES.get() }, 1) { b ->
    b
      .knifeTool()
      .extraResult({ AddonItems.YERBA_MATE_LEAVES.get() }, 0.25f, 1)
      .build(DataIngredient.items(AddonBlocks.YERBA_MATE_BUSH.get()), "", "")
  }

  val TUCUPI_BOIL = cookingPot({ AddonItems.TUCUPI.get() }, 3) { b ->
    b
      .unlockedByIngredients({ AddonBlocks.BUDDING_CASSAVA.get() })
      .normalCooking()
      .foodContainer { Items.GLASS_BOTTLE }
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

  val FRIED_CASSAVA_WITH_BUTTER = cookingPot({ AddonItems.FRIED_CASSAVA_WITH_BUTTER.get() }, 1) { b ->
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

  val COCONUT_DRINK = crafting({ AddonItems.COCONUT_DRINK.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.items(AddonBlocks.GREEN_COCONUT.get()),
        DataIngredient.items(Items.BAMBOO),
      )
    )
  }

  val COCONUT_MILK = crafting({ AddonItems.COCONUT_MILK.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.items(AddonBlocks.GREEN_COCONUT.get()),
        DataIngredient.items(Items.GLASS_BOTTLE),
      )
    )
  }

  val CHEESE_BREAD_DOUGH = crafting({ AddonItems.CHEESE_BREAD_DOUGH.get() }) { b ->
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

  val CHEESE_BREAD = cooking(
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

  val COLLARD_GREENS_FAROFA = crafting({ AddonItems.COLLARD_GREENS_FAROFA.get() }) { b ->
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

  val COLLARD_GREENS_SALAD = crafting({ AddonItems.COLLARD_GREENS_SALAD.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.tag(AddonTags.ITEM.COLLARD_GREENS),
        DataIngredient.tag(ForgeTags.VEGETABLES_ONION),
        DataIngredient.items(Items.BOWL),
      )
    )
  }

  val COOKED_CARIOCA_BEANS = cookingPot({ AddonItems.COOKED_CARIOCA_BEANS.get() }, 1) { b ->
    b
      .unlockedByIngredients(
        { AddonBlocks.CARIOCA_BEANS_CROP.get() },
        { AddonBlocks.BUDDING_BEANS_CROP.get() },
        { AddonItems.GARLIC_BULB.get() })
      .normalCooking()
      .foodContainer { Items.BOWL }
      .build(
        listOf(
          DataIngredient.items(AddonBlocks.CARIOCA_BEANS_CROP.get()),
          DataIngredient.tag(AddonTags.ITEM.GARLIC),
        ),
        "",
        "_cooking"
      )
  }

  val COOKED_BLACK_BEANS = cookingPot({ AddonItems.COOKED_BLACK_BEANS.get() }, 1) { b ->
    b
      .unlockedByIngredients(
        { AddonBlocks.CARIOCA_BEANS_CROP.get() },
        { AddonBlocks.BUDDING_BEANS_CROP.get() },
        { AddonItems.GARLIC_BULB.get() })
      .normalCooking()
      .foodContainer { Items.BOWL }
      .build(
        listOf(
          DataIngredient.items(AddonBlocks.BUDDING_BEANS_CROP.get()),
          DataIngredient.tag(AddonTags.ITEM.GARLIC),
        ),
        "",
        "_cooking"
      )
  }


  val TROPEIRO_BEANS = cookingPot({ AddonItems.TROPEIRO_BEANS.get() }, 2) { b ->
    b
      .unlockedByIngredients(
        { AddonItems.CASSAVA_FLOUR.get() },
        { AddonBlocks.CARIOCA_BEANS_CROP.get() },
        { ModItems.BACON.get() },
        { AddonItems.GARLIC_BULB.get() })
      .normalCooking()
      .foodContainer { Items.BOWL }
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

  val FRIED_FISH_WITH_ACAI = cookingPot({ AddonItems.FRIED_FISH_WITH_ACAI.get() }, 1) { b ->
    b
      .unlockedByIngredients(
        { Items.COD },
        { AddonBlocks.BUDDING_ACAI_BRANCH.get() },
        { AddonItems.CASSAVA_FLOUR.get() },
        { AddonItems.BUTTER.get() })
      .normalCooking()
      .foodContainer { Items.BOWL }
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

  val ANGU = cookingPot({ AddonItems.ANGU.get() }, 2) { b ->
    b
      .unlockedByIngredients({ Items.WATER_BUCKET }, { AddonItems.CORN_FLOUR.get() }, { AddonItems.SALT.get() })
      .normalCooking()
      .foodContainer { Items.BOWL }
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

  val CHIMARRAO = crafting({ AddonItems.CHIMARRAO.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.items(AddonItems.DRIED_YERBA_MATE.get()),
        DataIngredient.items(Items.WATER_BUCKET),
        DataIngredient.items(Items.BOWL),
      )
    )
  }

  val BUTTERED_CORN = cookingPot({ AddonItems.BUTTERED_CORN.get() }, 1) { b ->
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

  val SALPICAO = cookingPot({ AddonItems.SALPICAO.get() }, 1) { b ->
    b
      .unlockedByIngredients(
        { Items.COOKED_CHICKEN },
        { Items.CARROT },
        { Items.APPLE },
        { AddonItems.BEAN_POD.get() },
        { AddonItems.HEAVY_CREAM_BUCKET.get() },
        { AddonBlocks.BUDDING_CORN.get() })
      .normalCooking()
      .foodContainer { Items.BOWL }
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

  val LEMONADE = crafting({ AddonItems.LEMONADE.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.tag(AddonTags.ITEM.LEMON),
        DataIngredient.items(Items.SUGAR),
        DataIngredient.items(Items.WATER_BUCKET),
        DataIngredient.items(Items.GLASS_BOTTLE),
      )
    )
  }

  val COLLARD_LEMONADE = crafting({ AddonItems.COLLARD_LEMONADE.get() }) { b ->
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

  val GUARANA_JUICE = crafting({ AddonItems.GUARANA_JUICE.get() }) { b ->
    b.shapeless(
      1, "", "", listOf(
        DataIngredient.items(AddonItems.GUARANA_POWDER.get()),
        DataIngredient.items(Items.SUGAR),
        DataIngredient.items(Items.WATER_BUCKET),
        DataIngredient.items(Items.GLASS_BOTTLE),
      )
    )
  }

  val ACAI_TEA_WITH_GUARANA = cookingPot({ AddonItems.ACAI_TEA_WITH_GUARANA.get() }, 2) { b ->
    b
      .unlockedByIngredients(
        { AddonBlocks.BUDDING_ACAI_BRANCH.get() },
        { AddonItems.GUARANA_POWDER.get() },
        { Items.WATER_BUCKET },
        { Items.SUGAR },
        { Items.GLASS_BOTTLE })
      .normalCooking()
      .foodContainer { Items.GLASS_BOTTLE }
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

  val COUSCOUS = cookingPot({ AddonItems.COUSCOUS.get() }, 1) { b ->
    b
      .unlockedByIngredients({ AddonItems.CORN_FLOUR.get() }, { AddonItems.BUTTER.get() })
      .normalCooking()
      .foodContainer { Items.BOWL }
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

  val BROA = crafting({ AddonItems.BROA.get() }) { b ->
    b.shapeless(
      4, "", "", listOf(
        DataIngredient.items(AddonItems.CORN_FLOUR.get()),
        DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()),
        DataIngredient.tag(AddonTags.ITEM.BUTTER),
      )
    )
  }

  val BRAZILIAN_DINNER = crafting({ AddonItems.BRAZILIAN_DINNER.get() }) { b ->
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

  val GREEN_SOUP_POT = cookingPot({ AddonBlocks.GREEN_SOUP_POT.get() }, 1) { b ->
    b
      .unlockedByIngredients({ AddonItems.BEAN_POD.get() }, { Items.COOKED_PORKCHOP }, { AddonItems.GARLIC_BULB.get() })
      .normalCooking()
      .foodContainer { ModBlocks.COOKING_POT.get() }
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

  val FISH_MOQUECA_POT = cookingPot({ AddonBlocks.FISH_MOQUECA_POT.get() }, 1) { b ->
    b
      .unlockedByIngredients(
        { Items.COD },
        { ModItems.ONION.get() },
        { ModItems.TOMATO.get() },
        { AddonItems.HEAVY_CREAM_BUCKET.get() },
        { AddonItems.LEMON.get() },
        { AddonItems.COCONUT_MILK.get() })
      .normalCooking()
      .foodContainer { ModBlocks.COOKING_POT.get() }
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

  val STROGANOFF_POT = cookingPot({ AddonBlocks.STROGANOFF_POT.get() }, 1) { b ->
    b
      .unlockedByIngredients(
        { Items.COOKED_BEEF },
        { ModItems.ONION.get() },
        { ModItems.TOMATO.get() },
        { AddonItems.HEAVY_CREAM_BUCKET.get() },
        { AddonItems.GARLIC_BULB.get() })
      .normalCooking()
      .foodContainer { ModBlocks.COOKING_POT.get() }
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