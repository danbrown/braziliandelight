package com.dannbrown.braziliandelight.datagen.worldgen

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.CropLeavesBlock
import com.dannbrown.braziliandelight.content.placerTypes.CrookedTrunkPlacer
import com.dannbrown.braziliandelight.content.placerTypes.CoconutPalmFoliagePlacer
import com.dannbrown.braziliandelight.content.placerTypes.PalmFoliagePlacer
import com.dannbrown.braziliandelight.content.tree.AcaiTreeDecorator
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.deltaboxlib.registry.worldgen.AbstractConfiguredFeaturesGen
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration

object AddonConfiguredFeatures: AbstractConfiguredFeaturesGen() {
  override val modId: String = AddonContent.MOD_ID

  val BLOCK_BELOW: BlockPos = BlockPos(0, -1, 0)
  val BLOCK_ABOVE: BlockPos = BlockPos(0, 1, 0)

  val LEMON_TREE_KEY = registerKey("lemon_tree")
  val COCONUT_PALM_TREE_KEY = registerKey("coconut_palm_tree")
  val ACAI_PALM_TREE_KEY = registerKey("acai_palm_tree")
  val PATCH_WILD_GARLIC_KEY = registerKey("patch_wild_garlic")
  val PATCH_WILD_COLLARD_GREENS_KEY = registerKey("patch_wild_collard_greens")
  val PATCH_WILD_COFFEE_BERRIES_KEY = registerKey("patch_wild_coffee_berries")
  val PATCH_WILD_CASSAVA_KEY = registerKey("patch_wild_cassava")
  val PATCH_WILD_CORN_KEY = registerKey("patch_wild_corn")
  val PATCH_WILD_GUARANA_KEY = registerKey("patch_wild_guarana")
  val PATCH_WILD_BEANS_KEY = registerKey("patch_wild_beans")
  val PATCH_YERBA_MATE_KEY = registerKey("patch_yerba_mate")


  override fun bootstrap(context: BootstapContext<ConfiguredFeature<*, *>>) {
    val lookup = context.lookup(Registries.CONFIGURED_FEATURE)
    // ----

    // LEMON TREE
    register(
      context, LEMON_TREE_KEY, Feature.TREE,
      createStraightFruitBlobTree(Blocks.OAK_LOG, AddonBlocks.LEMON_LEAVES.get(), AddonBlocks.BUDDING_LEMON_LEAVES.get(), 12, 4, 1,5, 4, 0, 2)
        .ignoreVines()
        .build()
    )

    // COCONUT PALM TREE
    register(
      context, COCONUT_PALM_TREE_KEY, Feature.TREE,
      createPalmTree(Blocks.JUNGLE_LOG, AddonBlocks.COCONUT_PALM_LEAVES.get(), 9, 4, 0, 0)
        .ignoreVines()
        .build()
    )

    // ACAI PALM TREE
    register(
      context, ACAI_PALM_TREE_KEY, Feature.TREE,
      createAcaiTree(Blocks.JUNGLE_LOG, AddonBlocks.ACAI_PALM_LEAVES.get(), 9, 4, 0, 0)
        .ignoreVines()
        .decorators(listOf(AcaiTreeDecorator(0.25f)))
        .build()
    )

    // PATCH WILD GARLIC
    register(
      context, PATCH_WILD_GARLIC_KEY, ModBiomeFeatures.WILD_CROP.get(),
      wildCropWithFloorConfig(AddonBlocks.WILD_GARLIC.get(), Blocks.GRASS, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT), Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT))
    )

    // PATCH WILD COLLARD GREENS
    register(
      context, PATCH_WILD_COLLARD_GREENS_KEY, ModBiomeFeatures.WILD_CROP.get(),
      wildCropWithFloorConfig(AddonBlocks.WILD_COLLARD_GREENS.get(), Blocks.GRASS, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT), Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT))
    )

    // PATCH WILD COFFEE BERRIES
    register(
      context, PATCH_WILD_COFFEE_BERRIES_KEY, ModBiomeFeatures.WILD_CROP.get(),
      wildCropWithFloorConfig(AddonBlocks.WILD_COFFEE_BERRIES.get(), Blocks.GRASS, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT), Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT))
    )

    // PATCH WILD CASSAVA
    register(
      context, PATCH_WILD_CASSAVA_KEY, ModBiomeFeatures.WILD_CROP.get(),
      wildCropWithFloorConfig(AddonBlocks.WILD_CASSAVA.get(), Blocks.GRASS, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT), Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT))
    )

    // PATCH WILD CORN
    register(
      context, PATCH_WILD_CORN_KEY, ModBiomeFeatures.WILD_CROP.get(),
      wildCropWithFloorConfig(AddonBlocks.WILD_CORN.get(), Blocks.GRASS, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT), Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT))
    )

    // PATCH WILD GUARANA
    register(
      context, PATCH_WILD_GUARANA_KEY, ModBiomeFeatures.WILD_CROP.get(),
      wildCropWithFloorConfig(AddonBlocks.WILD_GUARANA.get(), Blocks.GRASS, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT), Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT))
    )

    // PATCH WILD BEANS
    register(
      context, PATCH_WILD_BEANS_KEY, ModBiomeFeatures.WILD_CROP.get(),
      wildCropWithFloorConfig(AddonBlocks.WILD_BEANS.get(), Blocks.GRASS, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT), Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT))
    )

    // PATCH YERBA MATE
    register(
      context, PATCH_YERBA_MATE_KEY, ModBiomeFeatures.WILD_CROP.get(),
      wildCropWithFloorConfig(AddonBlocks.YERBA_MATE_BUSH.get(), Blocks.GRASS, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT), Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT))
    )

  }
  private fun createStraightFruitBlobTree(logBlock: Block, leavesBlock: Block, fruitBlock: Block, leavesChance: Int, fruitChance: Int, fruitReadyChance: Int,  baseHeight: Int, heightRandA: Int, heightRandB: Int, radius: Int): TreeConfigurationBuilder {
    return TreeConfigurationBuilder(
      BlockStateProvider.simple(logBlock),
      StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
      weightedStateProvider(mapOf(
        leavesBlock.defaultBlockState() to leavesChance,
        fruitBlock.defaultBlockState() to fruitChance,
        fruitBlock.defaultBlockState().setValue(CropLeavesBlock.AGE, 3) to fruitReadyChance,
        fruitBlock.defaultBlockState().setValue(CropLeavesBlock.AGE, 2) to fruitReadyChance,
        fruitBlock.defaultBlockState().setValue(CropLeavesBlock.AGE, 1) to fruitReadyChance,
      )),
      BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3),
      TwoLayersFeatureSize(1, 0, 1)
    )
  }

  private fun createPalmTree(logBlock: Block, leavesBlock: Block, baseHeight: Int, heightRandA: Int, heightRandB: Int, radius: Int): TreeConfigurationBuilder {
    return TreeConfigurationBuilder(
      BlockStateProvider.simple(logBlock),
      CrookedTrunkPlacer(baseHeight, heightRandA, heightRandB),
      BlockStateProvider.simple(leavesBlock),
      CoconutPalmFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0)),
      TwoLayersFeatureSize(1, 0, 1)
    )
  }

  private fun createAcaiTree(logBlock: Block, leavesBlock: Block, baseHeight: Int, heightRandA: Int, heightRandB: Int, radius: Int): TreeConfigurationBuilder {
    return TreeConfigurationBuilder(
      BlockStateProvider.simple(logBlock),
      StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
      BlockStateProvider.simple(leavesBlock),
      PalmFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0)),
      TwoLayersFeatureSize(1, 0, 1)
    )
  }

  private fun wildCropWithFloorConfig(primaryBlock: Block, secondaryBlock: Block, plantedOn: BlockPredicate, floorBlock: Block, replaces: BlockPredicate): WildCropConfiguration {
    return WildCropConfiguration(64, 6, 3, plantBlockConfig(primaryBlock, plantedOn), plantBlockConfig(secondaryBlock, plantedOn), floorBlockConfig(floorBlock, replaces))
  }
  private fun plantBlockConfig(block: Block, plantedOn: BlockPredicate): Holder<PlacedFeature> {
    return PlacementUtils.filtered(
      Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(block)),
      BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, plantedOn))
  }
  private fun floorBlockConfig(block: Block, replaces: BlockPredicate): Holder<PlacedFeature> {
    return PlacementUtils.filtered<SimpleBlockConfiguration, Feature<SimpleBlockConfiguration>>(
      Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(block)),
      BlockPredicate.allOf(BlockPredicate.replaceable(BLOCK_ABOVE), replaces))
  }
}