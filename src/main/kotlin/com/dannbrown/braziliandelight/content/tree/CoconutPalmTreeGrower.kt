package com.dannbrown.braziliandelight.content.tree

import com.dannbrown.braziliandelight.datagen.worldgen.AddonConfiguredFeatures
import net.minecraft.data.worldgen.features.TreeFeatures
import net.minecraft.resources.ResourceKey
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.grower.AbstractTreeGrower
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature

class CoconutPalmTreeGrower : AbstractTreeGrower() {
  override fun getConfiguredFeature(randomSource: RandomSource, b: Boolean): ResourceKey<ConfiguredFeature<*, *>> {
    return AddonConfiguredFeatures.COCONUT_PALM_TREE_KEY
  }
}
