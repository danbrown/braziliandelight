package com.dannbrown.braziliandelight.compat

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.CriterionTriggerInstance
import net.minecraft.advancements.RequirementsStrategy
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.ItemLike
import org.jetbrains.annotations.Nullable
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers
import java.util.function.Consumer

class CookingPotRecipeJsonBuilder private constructor(
  private val output: Item,
  private val outputCount: Int,
  private val container: Item?,
  private val inputs: MutableList<Ingredient>,
  private val experience: Float,
  private val cookingTime: Int
) : RecipeBuilder {

  private var tab: CookingPotRecipeBookTab? = null
  private val advancement = Advancement.Builder.recipeAdvancement()

  companion object {
    fun create(
      output: ItemLike,
      outputCount: Int,
      cookingTime: Int,
      experience: Float,
      container: Item?,
      inputs: List<Ingredient>
    ) = CookingPotRecipeJsonBuilder(
      output.asItem(),
      outputCount,
      container,
      inputs.toMutableList(),
      experience,
      cookingTime
    )

    fun create(
      output: ItemLike,
      outputCount: Int,
      cookingTime: Int,
      experience: Float,
      inputs: List<Ingredient>
    ) = CookingPotRecipeJsonBuilder(output.asItem(), outputCount, null, inputs.toMutableList(), experience, cookingTime)
  }

  fun input(tag: TagKey<Item>) = apply { input(Ingredient.of(tag)) }
  fun input(item: ItemLike) = apply { input(item, 1) }
  fun input(itemProvider: ItemLike, size: Int) = apply {
    repeat(size) { input(Ingredient.of(itemProvider)) }
  }

  fun input(ingredient: Ingredient) = apply { input(ingredient, 1) }
  fun input(ingredient: Ingredient, size: Int) = apply {
    repeat(size) { inputs.add(ingredient) }
  }

  override fun unlockedBy(criterionName: String, criterionTrigger: CriterionTriggerInstance) = apply {
    advancement.addCriterion(criterionName, criterionTrigger)
  }

  fun unlockedBy(criterionName: String, vararg items: ItemLike) =
    unlockedBy(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(*items))

  fun unlockedByAny(vararg items: ItemLike) = apply {
    advancement.addCriterion(
      "has_any_ingredient",
      InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(*items).build())
    )
  }

  fun setRecipeBookTab(tab: CookingPotRecipeBookTab) = apply { this.tab = tab }
  override fun group(@Nullable groupName: String?) = this
  override fun getResult(): Item = output

  override fun save(finishedRecipeConsumer: Consumer<FinishedRecipe>, recipeId: ResourceLocation) {
    ensureValid(recipeId)
    val fdCookingPrefix = "farmersdelight/cooking/"
    advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT)
      .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId.withPrefix(fdCookingPrefix)))
      .rewards(AdvancementRewards.Builder.recipe(recipeId.withPrefix(fdCookingPrefix)))
      .requirements(RequirementsStrategy.OR)

    finishedRecipeConsumer.accept(
      Result(
        recipeId.withPrefix(fdCookingPrefix),
        tab,
        output,
        outputCount,
        container,
        inputs,
        experience,
        cookingTime,
        advancement,
        recipeId.withPrefix("recipes/food/$fdCookingPrefix")
      )
    )
  }

  private fun ensureValid(id: ResourceLocation) {
    if (advancement.criteria.isEmpty()) throw IllegalStateException("No way of obtaining recipe $id")
  }

  class Result(
    private val id: ResourceLocation,
    private val tab: CookingPotRecipeBookTab?,
    private val output: Item,
    private val outputCount: Int,
    private val container: Item?,
    private val inputs: List<Ingredient>,
    private val experience: Float,
    private val cookingTime: Int,
    private val advancement: Advancement.Builder,
    private val advancementId: ResourceLocation
  ) : FinishedRecipe {
    override fun serializeRecipeData(json: JsonObject) {
      tab?.let { json.addProperty("recipe_book_tab", it.toString()) }
      json.addProperty("cookingtime", cookingTime)
      if (experience > 0) json.addProperty("experience", experience)

      val arrayIngredients = JsonArray()
      inputs.forEach { arrayIngredients.add(it.toJson()) }
      json.add("ingredients", arrayIngredients)

      val objectResult = JsonObject().apply {
        addProperty("item", BuiltInRegistries.ITEM.getKey(output).toString())
        if (outputCount > 1) addProperty("count", outputCount)
      }
      json.add("result", objectResult)

      container?.let {
        json.add("container", JsonObject().apply {
          addProperty("item", BuiltInRegistries.ITEM.getKey(it).toString())
        })
      }
    }

    override fun getId() = id
    override fun getType(): RecipeSerializer<*> = ModRecipeSerializers.COOKING.get()
    override fun serializeAdvancement(): JsonObject? = advancement.serializeToJson()
    override fun getAdvancementId() = advancementId
  }
}
