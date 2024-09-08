package com.dannbrown.braziliandelight.datagen.recipe

import com.dannbrown.deltaboxlib.registry.datagen.recipe.DeltaboxRecipeProvider
import com.tterrag.registrate.providers.RegistrateRecipeProvider
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder
import java.util.function.Consumer
import java.util.function.Supplier
import java.util.function.UnaryOperator

class CustomCookingPotRecipeBuilder(private val modId: String, private val result: Supplier<ItemLike>, private val foodContainer: Supplier<ItemLike>? = null, private val amount: Int): DeltaboxRecipeProvider.DeltaBoxRecipeBuilder()
{
  val FAST_COOKING: Int = 100 // 5 seconds
  val NORMAL_COOKING: Int = 200 // 10 seconds
  val SLOW_COOKING: Int = 400 // 20 seconds

  val SMALL_EXP: Float = 0.35f
  val MEDIUM_EXP: Float = 1.0f
  val LARGE_EXP: Float = 2.0f

  private var prefix: String = ""
  private var suffix: String = "_cooking_pot"
  private val allRecipes: MutableMap<ResourceLocation, CookingPotRecipeBuilder> = HashMap()
  private var unlockedBy: Supplier<ItemPredicate>? = null
  private val unlockedByIngredients: MutableList<Supplier<ItemLike>> = ArrayList()
  private var cookingTime: Int = NORMAL_COOKING
  private var expAmount: Float = MEDIUM_EXP
  private var recipeBookTab: CookingPotRecipeBookTab = CookingPotRecipeBookTab.MISC

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
    val _builder = builder.apply(
      CookingPotRecipeBuilder.cookingPotRecipe(
        result.get(),
        amount,
        cookingTime,
        expAmount,
        foodContainer?.get()
      )
    )

    ingredients.forEach { ingredient ->
      _builder.addIngredient(ingredient)
    }

    if (unlockedByIngredients.isNotEmpty()) {
      _builder.unlockedByAnyIngredient(*unlockedByIngredients.map { it.get() }.toTypedArray())
    } else if (unlockedBy != null) {
      _builder.unlockedBy("has_item", RegistrateRecipeProvider.inventoryTrigger(unlockedBy?.get()))
    } else {
      _builder.unlockedBy(
        "has_item",
        RegistrateRecipeProvider.inventoryTrigger(
          ItemPredicate.Builder.item()
            .of(Items.AIR)
            .build()
        )
      )
    }

    _builder.setRecipeBookTab(recipeBookTab)

    allRecipes[DeltaboxRecipeProvider.createSimpleLocation(modId, "cooking_pot", result, prefix, suffix)] = _builder

    return this
  }

  override fun getRecipes(consumer: Consumer<FinishedRecipe>): Set<ResourceLocation> {
    for ((key, value) in allRecipes) {
      value.build(consumer, key)
    }
    return allRecipes.keys
  }
}
