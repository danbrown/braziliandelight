package com.dannbrown.braziliandelight.datagen.recipe

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonItems
import com.dannbrown.databoxlib.registry.datagen.DataboxRecipeProvider
import com.tterrag.registrate.util.DataIngredient
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.DataGenerator
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.common.Tags
import net.minecraftforge.common.ToolActions
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil.Test
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab
import vectorwing.farmersdelight.common.crafting.ingredient.ToolActionIngredient
import vectorwing.farmersdelight.common.registry.ModItems
import vectorwing.farmersdelight.common.tag.ForgeTags
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder
import java.util.function.Consumer
import java.util.function.Supplier
import java.util.function.UnaryOperator
import java.util.stream.Stream

class AddonRecipeGen(generator: DataGenerator) : DataboxRecipeProvider(generator.packOutput, AddonContent.MOD_ID) {


  val SLICE_FROM_MINAS_CHEESE = crafting({ AddonItems.MINAS_CHEESE_SLICE.get() }) { b ->
    b.shapeless(4, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.MINAS_CHEESE.get())))
  }

  val MINAS_CHEESE_TO_SLICE = crafting({ AddonBlocks.MINAS_CHEESE.get() }) { b ->
    b.shapeless(1, "", "_from_slice", listOf(
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
    ))
  }

  val SLICE_FROM_CHICKEN_POT_PIE = crafting({ AddonItems.CHICKEN_POT_PIE_SLICE.get() }) { b ->
    b.shapeless(4, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CHICKEN_POT_PIE.get())))
  }

  val CHICKEN_POT_PIE_TO_SLICE = crafting({ AddonBlocks.CHICKEN_POT_PIE.get() }) { b ->
    b.shapeless(1, "", "_from_slice", listOf(
      DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
      DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
      DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
      DataIngredient.items(AddonItems.CHICKEN_POT_PIE_SLICE.get()),
    ))
  }

  val SLICE_FROM_CARROT_CAKE = crafting({ AddonItems.CARROT_CAKE_SLICE.get() }) { b ->
    b.shapeless(7, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CARROT_CAKE.get())))
  }

  val CARROT_CAKE_TO_SLICE = crafting({ AddonBlocks.CARROT_CAKE.get() }) { b ->
    b.shapeless(1, "", "_from_slice", listOf(
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
    ))
  }

  val SLICE_FROM_CARROT_CAKE_WITH_CHOCOLATE = crafting({ AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get() }) { b ->
    b.shapeless(7, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CARROT_CAKE_WITH_CHOCOLATE.get())))
  }

  val CARROT_CAKE_WITH_CHOCOLATE_TO_SLICE = crafting({ AddonBlocks.CARROT_CAKE_WITH_CHOCOLATE.get() }) { b ->
    b.shapeless(1, "", "_from_slice", listOf(
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
    ))
  }

  val MINAS_CHEESE_ON_A_STICK = crafting({ AddonItems.MINAS_CHEESE_ON_A_STICK.get() }) { b ->
    b.shapeless(1, "", "", listOf(
      DataIngredient.items(Items.STICK),
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
    ))
  }


  val GRILLED_CHEESE_ON_A_STICK = cooking(
    { DataIngredient.items(AddonItems.MINAS_CHEESE_ON_A_STICK.get()) },
    { AddonItems.GRILLED_CHEESE_ON_A_STICK.get() }
  ) { b -> b
    .comboFoodCooking(300, 4f)
  }

  val SWEET_LOVE_APPLE = crafting({ AddonItems.SWEET_LOVE_APPLE.get() }) { b ->
    b.shapeless(1, "", "", listOf(
      DataIngredient.items(Items.STICK),
      DataIngredient.items(Items.APPLE),
      DataIngredient.items(Items.SUGAR),
      DataIngredient.items(AddonItems.CONDENSED_MILK.get()),
    ))
  }

  val ROASTED_GARLIC = cooking(
    { DataIngredient.items(AddonItems.GARLIC_BULB.get()) },
    { AddonItems.ROASTED_GARLIC.get() }
  ) { b -> b
    .comboFoodCooking(200, 2f)
  }

  val GARLIC_CLOVE = cutting({ AddonItems.GARLIC_CLOVE.get() }, 2) { b -> b
    .knifeTool()
    .build(DataIngredient.items(AddonItems.GARLIC_BULB.get()), "", "")
  }

  val BEANS = cutting({ Blocks.AIR }, 0) { b -> b
    .knifeTool()
    .extraResult({ AddonItems.BLACK_BEANS }, 0.5f, 1)
    .extraResult({ AddonItems.CARIOCA_BEANS }, 0.5f, 1)
    .build(DataIngredient.items(AddonItems.BEAN_POD.get()), "bean_pod_", "_to_beans")
  }

  val COXINHA = cooking(
    { DataIngredient.items(AddonItems.RAW_COXINHA.get()) },
    { AddonItems.COXINHA.get() }
  ) { b -> b
    .comboFoodCooking(300, 4f)
  }

  val GARAPA = crafting({ AddonItems.GARAPA.get() }) { b ->
    b.shapeless(1, "", "", listOf(
      DataIngredient.items(Items.GLASS_BOTTLE),
      DataIngredient.items(Items.SUGAR_CANE),
      DataIngredient.items(Items.SUGAR_CANE),
      DataIngredient.items(Items.SUGAR_CANE),
    ))
  }

  val GUARANA_POWDER_FROM_GUARANA = crafting({ AddonItems.GUARANA_POWDER.get() }) { b ->
    b.shapeless(1, "", "_from_guarana", listOf(
      DataIngredient.items(AddonItems.GUARANA_FRUIT.get()),
    ))
  }

  val CASSAVA_FLOUR_FROM_CASSAVA = crafting({ AddonItems.CASSAVA_FLOUR.get() }) { b ->
    b.shapeless(1, "", "_from_cassava", listOf(
      DataIngredient.items(AddonItems.CASSAVA_ROOT.get()),
    ))
  }

  val COFFEE_BEANS_FROM_COFFEE_BERRIES = cooking(
    { DataIngredient.items(AddonItems.COFFEE_BERRIES.get()) },
    { AddonItems.COFFEE_BEANS.get() }
  ) { b -> b
    .comboFoodCooking(200, 1f)
  }

  val CONDENSED_MILK_FROM_MILK = cookingPot({ AddonItems.CONDENSED_MILK.get() }, 1) { b -> b
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

  val PUDDING = cookingPot({ AddonBlocks.PUDDING.get() }, 1) { b -> b
    .unlockedByIngredients({ AddonItems.CONDENSED_MILK.get() }, {AddonItems.HEAVY_CREAM.get()}, { Items.SUGAR }, { ModItems.MILK_BOTTLE.get() })
    .normalCooking()
    .foodContainer { Items.BOWL }
    .build(
      listOf(
        DataIngredient.items(AddonItems.CONDENSED_MILK.get()),
        DataIngredient.items(AddonItems.CONDENSED_MILK.get()),
        DataIngredient.items(AddonItems.HEAVY_CREAM.get()),
        DataIngredient.items(AddonItems.HEAVY_CREAM.get()),
        DataIngredient.items(Items.SUGAR),
        DataIngredient.items(Items.SUGAR),
      ),
      "",
      "_cooking_pot"
    )
  }

  val RAW_COXINHA = crafting({ AddonItems.RAW_COXINHA.get() }) { b ->
    b.shapeless(1, "", "", listOf(
      DataIngredient.items(ModItems.WHEAT_DOUGH.get()),
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
      Ingredient.fromValues(Stream.of(
        Ingredient.TagValue(ForgeTags.RAW_CHICKEN),
        Ingredient.TagValue(ForgeTags.RAW_BEEF),
        Ingredient.TagValue(ForgeTags.RAW_PORK),
        Ingredient.TagValue(ForgeTags.RAW_MUTTON),
        Ingredient.ItemValue(ItemStack(Items.BROWN_MUSHROOM)),
        Ingredient.ItemValue(ItemStack(Items.RABBIT)),
      ))
    ))
  }

  fun cutting(result: Supplier<ItemLike>, amount: Int = 1, builder: UnaryOperator<CustomCuttingRecipeBuilder> = UnaryOperator.identity()): List<GeneratedRecipe> {
    val allCrafting = CustomCuttingRecipeBuilder(AddonContent.MOD_ID, result, amount).apply(builder)
      .getRecipes()

    all.addAll(allCrafting)
    return allCrafting
  }

  fun cookingPot(result: Supplier<ItemLike>, amount: Int = 1, builder: UnaryOperator<CustomCookingPotRecipeBuilder> = UnaryOperator.identity()): List<GeneratedRecipe> {
    val allCrafting = CustomCookingPotRecipeBuilder(AddonContent.MOD_ID, result, amount).apply(builder)
      .getRecipes()

    all.addAll(allCrafting)
    return allCrafting
  }

  class CustomCuttingRecipeBuilder(private val modId: String, result: Supplier<ItemLike>, amount: Int) {
    private var result: Supplier<ItemLike>? = null
    private var amount: Int = 0
    private var soundId: String? = null
    private var prefix: String = ""
    private var suffix: String = "_cutting"
    private val allRecipes: MutableList<DataboxRecipeProvider.GeneratedRecipe> = ArrayList()
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
      return build(ingredient, 100, prefix, suffix,  builder)
    }

    fun build(
      ingredient: DataIngredient,
      prefix: String,
      suffix: String,
      builder: UnaryOperator<CuttingBoardRecipeBuilder> = UnaryOperator.identity(),
    ): CustomCuttingRecipeBuilder {
      return build(ingredient, 100, prefix, suffix,  builder)
    }


    fun build(
      ingredient: DataIngredient,
      chance: Int,
      prefix: String,
      suffix: String,
      builder: UnaryOperator<CuttingBoardRecipeBuilder> = UnaryOperator.identity(),
    ): CustomCuttingRecipeBuilder {
      val generatedRecipe = DataboxRecipeProvider.GeneratedRecipe { consumer: Consumer<FinishedRecipe> ->
        val builder = builder.apply(CuttingBoardRecipeBuilder.cuttingRecipe(ingredient, toolIngredient, result?.get(), amount, chance))

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
          DataboxRecipeProvider.createSimpleLocation(modId, "cutting", result!!, prefix, suffix)
        )
      }

      allRecipes.add(generatedRecipe)

      return this
    }

    fun getRecipes(): List<DataboxRecipeProvider.GeneratedRecipe> {
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

    fun foodContainer(container: Supplier<ItemLike>): CustomCookingPotRecipeBuilder{
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
        val builder = builder.apply(CookingPotRecipeBuilder.cookingPotRecipe(result?.get(), amount, cookingTime, expAmount, foodContainer?.get()))

        ingredients.forEach { ingredient ->
          builder.addIngredient(ingredient)
        }

        if(unlockedByIngredients.isNotEmpty()) {
          builder.unlockedByAnyIngredient(*unlockedByIngredients.map { it.get() }.toTypedArray())
        }
        else if (unlockedBy != null) {
          builder.unlockedBy("has_item", inventoryTrigger(unlockedBy?.get()))
        }
        else {
          builder.unlockedBy("has_item",
            inventoryTrigger(ItemPredicate.Builder.item()
              .of(Items.AIR)
              .build()))
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

    fun getRecipes(): List<DataboxRecipeProvider.GeneratedRecipe> {
      return allRecipes
    }
  }
}