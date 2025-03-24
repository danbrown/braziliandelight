package com.dannbrown.braziliandelight.compat

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.minecraft.advancements.CriterionTriggerInstance
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import org.jetbrains.annotations.Nullable
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers
import java.util.function.Consumer

class CuttingBoardRecipeBuilder private constructor(
  private val ingredient: Ingredient,
  private val tool: Ingredient,
  mainResult: ItemStack,
  count: Int,
  chance: Float
) : RecipeBuilder {
  private val results: MutableList<ChanceResult> =
    mutableListOf(ChanceResult(ItemStack(mainResult.item, count), chance))
  private var soundEventID: String = ""

  companion object {
    fun create(input: Item, tool: Ingredient, output: Item, count: Int, chance: Float): CuttingBoardRecipeBuilder {
      return CuttingBoardRecipeBuilder(Ingredient.of(input), tool, ItemStack(output), count, chance)
    }

    fun create(input: Item, tool: Ingredient, output: Item, count: Int): CuttingBoardRecipeBuilder {
      return CuttingBoardRecipeBuilder(Ingredient.of(input), tool, ItemStack(output), count, 1f)
    }

    fun create(input: Item, tool: Ingredient, output: Item): CuttingBoardRecipeBuilder {
      return CuttingBoardRecipeBuilder(Ingredient.of(input), tool, ItemStack(output), 1, 1f)
    }
  }

  fun output(output: Item, count: Int, chance: Float): CuttingBoardRecipeBuilder {
    results.add(ChanceResult(ItemStack(output, count), chance))
    return this
  }

  fun output(output: Item, chance: Float): CuttingBoardRecipeBuilder = output(output, 1, chance)
  fun output(output: Item): CuttingBoardRecipeBuilder = output(output, 1, 1.0f)
  fun addSound(soundEventID: String): CuttingBoardRecipeBuilder = apply { this.soundEventID = soundEventID }

  override fun unlockedBy(criterionName: String, criterionTrigger: CriterionTriggerInstance): RecipeBuilder = this
  override fun group(@Nullable groupName: String?): RecipeBuilder? = null
  override fun getResult(): Item? = results.firstOrNull()?.stack()?.item

  override fun save(finishedRecipeConsumer: Consumer<FinishedRecipe>, recipeId: ResourceLocation) {
    finishedRecipeConsumer.accept(
      Result(
        recipeId.withPrefix("farmersdelight/cutting/"),
        results,
        ingredient,
        tool,
        soundEventID
      )
    )
  }

  class Result(
    private val id: ResourceLocation,
    private val results: List<ChanceResult>,
    private val ingredient: Ingredient,
    private val tool: Ingredient,
    private val soundEventID: String
  ) : FinishedRecipe {
    override fun serializeRecipeData(json: JsonObject) {
      json.add("ingredients", JsonArray().apply { add(ingredient.toJson()) })
      json.add("result", JsonArray().apply { results.forEach { add(it.serialize()) } })
      json.add("tool", tool.toJson())
      if (soundEventID.isNotEmpty()) json.addProperty("sound", soundEventID)
    }

    override fun getId(): ResourceLocation = id
    override fun getType(): RecipeSerializer<*> = ModRecipeSerializers.CUTTING.get()
    override fun serializeAdvancement(): JsonObject? = null
    override fun getAdvancementId(): ResourceLocation? = null
  }
}