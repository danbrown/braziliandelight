package com.dannbrown.discovermars.datagen.recipe

import com.dannbrown.discover.content.core.ProjectHeat
import com.dannbrown.discover.datagen.recipes.DiscoverRecipeGen
import com.dannbrown.discover.init.DiscoverFluids
import com.dannbrown.discover.init._DiscoverBlocks
import com.dannbrown.discover.init._DiscoverItems
import com.dannbrown.discovermars.AddonContent
import com.dannbrown.discovermars.init.AddonBlocks
import com.dannbrown.discovermars.init.AddonItems
import com.simibubi.create.AllItems
import com.tterrag.registrate.util.DataIngredient
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks

class AddonRecipeGen(generator: DataGenerator) : DiscoverRecipeGen(generator, AddonContent.MOD_ID) {
  override val recipeName: String = AddonContent.NAME

}