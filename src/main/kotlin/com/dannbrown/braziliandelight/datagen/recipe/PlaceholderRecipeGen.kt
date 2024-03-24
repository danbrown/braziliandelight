package com.dannbrown.braziliandelight.datagen.recipe

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonItems
import com.dannbrown.databoxlib.registry.datagen.DataboxRecipeProvider
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Blocks
import java.util.stream.Stream

class PlaceholderRecipeGen(generator: DataGenerator) : DataboxRecipeProvider(generator.packOutput, AddonContent.MOD_ID) {
  val STONE_FROM_COBBLESTONE = cooking(
  { Ingredient.of(Items.COBBLESTONE) },
  { Blocks.STONE }
  ) { b -> b
      .inBlastFurnace(200, 1f)
  }
}