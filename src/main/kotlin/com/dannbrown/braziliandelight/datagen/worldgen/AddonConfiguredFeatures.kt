package com.dannbrown.braziliandelight.datagen.worldgen

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.databoxlib.registry.worldgen.AbstractConfiguredFeaturesGen
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer

object AddonConfiguredFeatures: AbstractConfiguredFeaturesGen() {
  override val modId: String = AddonContent.MOD_ID

  val LEMON_TREE_KEY = registerKey("lemon_tree")



  override fun bootstrap(context: BootstapContext<ConfiguredFeature<*, *>>) {
    val lookup = context.lookup(Registries.CONFIGURED_FEATURE)
    // ----

    // LEMON TREE
    register(
      context, LEMON_TREE_KEY, Feature.TREE,
      createStraightFruitBlobTree(Blocks.OAK_LOG, AddonBlocks.LEMON_LEAVES.get(), AddonBlocks.BUDDING_LEMON_LEAVES.get(), 6, 2,5, 4, 0, 2)
        .ignoreVines()
        .build()
    )
  }
  private fun createStraightFruitBlobTree(logBlock: Block, leavesBlock: Block, fruitBlock: Block, leavesChance: Int, fruitChance: Int,  baseHeight: Int, heightRandA: Int, heightRandB: Int, radius: Int): TreeConfigurationBuilder {
    return TreeConfigurationBuilder(BlockStateProvider.simple(logBlock), StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), weightedStateProvider(mapOf(leavesBlock.defaultBlockState() to leavesChance, fruitBlock.defaultBlockState() to fruitChance)), BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3), TwoLayersFeatureSize(1, 0, 1))
  }
}