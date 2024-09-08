package com.dannbrown.braziliandelight.datagen.recipe

import com.dannbrown.deltaboxlib.registry.datagen.recipe.DeltaboxRecipeProvider
import com.tterrag.registrate.util.DataIngredient
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraftforge.common.Tags
import net.minecraftforge.common.ToolActions
import vectorwing.farmersdelight.common.crafting.ingredient.ToolActionIngredient
import vectorwing.farmersdelight.common.tag.ForgeTags
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder
import java.util.function.Consumer
import java.util.function.Supplier
import java.util.function.UnaryOperator

class CustomCuttingRecipeBuilder(private val modId: String, private val result: Supplier<ItemLike>, private val amount: Int): DeltaboxRecipeProvider.DeltaBoxRecipeBuilder()
{
  private var soundId: String? = null
  private var prefix: String = ""
  private var suffix: String = "_cutting"
  private val allRecipes: MutableMap<ResourceLocation, CuttingBoardRecipeBuilder> = HashMap()
  private var toolIngredient: Ingredient = Ingredient.of(ForgeTags.TOOLS_KNIVES)
  private val extraResults: MutableList<Triple<Supplier<ItemLike>, Float, Int>> = ArrayList()

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
    val _builder = builder.apply(
      CuttingBoardRecipeBuilder.cuttingRecipe(
        ingredient,
        toolIngredient,
        result.get(),
        amount,
        chance
      )
    )

    extraResults.forEach { extra ->
      val extraResult = extra.first.get()
      val resultChance = extra.second
      val resultAmount = extra.third
      _builder.addResultWithChance(extraResult, resultChance, resultAmount)
    }

    if (soundId != null) {
      _builder.addSound(soundId!!)
    }

    allRecipes[DeltaboxRecipeProvider.createSimpleLocation(modId, "cutting", result, prefix, suffix)] = _builder

    return this
  }

  override fun getRecipes(consumer: Consumer<FinishedRecipe>): Set<ResourceLocation> {
    for ((key, value) in allRecipes) {
      value.build(consumer, key)
    }
    return allRecipes.keys
  }
}
