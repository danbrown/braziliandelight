package com.dannbrown.discoverplanetzero.datagen.worldgen.tree

import com.dannbrown.discover.datagen.worldgen.DiscoverConfiguredFeatures
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonConfiguredFeatures
import net.minecraft.resources.ResourceKey
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.grower.AbstractTreeGrower
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature

class JoshuaTreeGrower : AbstractTreeGrower() {
  override fun getConfiguredFeature(
    pRandom: RandomSource?,
    pHasFlowers: Boolean
  ): ResourceKey<ConfiguredFeature<*, *>> {
    return AddonConfiguredFeatures.JOSHUA_TREE_KEY
  }
}