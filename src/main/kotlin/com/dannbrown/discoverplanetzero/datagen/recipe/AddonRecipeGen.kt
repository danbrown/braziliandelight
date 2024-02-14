package com.dannbrown.discoverplanetzero.datagen.recipe

import com.dannbrown.discover.datagen.recipes.DiscoverRecipeGen
import com.dannbrown.discover.init._DiscoverItems
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.init.AddonBlocks
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.Items

class AddonRecipeGen(generator: DataGenerator) : DiscoverRecipeGen(generator, AddonContent.MOD_ID) {
  override val recipeName: String = AddonContent.NAME

}