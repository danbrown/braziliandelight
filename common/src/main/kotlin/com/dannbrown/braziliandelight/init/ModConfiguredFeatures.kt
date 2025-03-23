package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.content.placerTypes.AcaiTreeDecorator
import com.dannbrown.braziliandelight.content.placerTypes.CoconutPalmFoliagePlacer
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.content.worldgen.placerType.CrookedTrunkPlacer
import com.dannbrown.deltaboxlib.content.worldgen.placerType.PalmFoliagePlacer
import com.dannbrown.deltaboxlib.init.DeltaboxFeatures
import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.tags.BlockTags
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer
import net.minecraft.world.level.levelgen.placement.CaveSurface
import java.util.OptionalInt

object ModConfiguredFeatures {

  val LEMON_TREE = REGISTRATE.configuredFeature("lemon_tree") { k, c, u ->
    u.register(
      c, k, Feature.TREE,
      u.createStraightFruitBlobTree(
        Blocks.OAK_LOG,
        ModBlocks.LEMON_LEAVES.get(),
        ModBlocks.BUDDING_LEMON_LEAVES.get(),
        12,
        4,
        1,
        5,
        4,
        0,
        2
      )
        .ignoreVines()
        .build()
    )
  }

  val COCONUT_PALM_TREE = REGISTRATE.configuredFeature("coconut_palm_tree") { k, c, u ->
    u.register(
      c, k, Feature.TREE,
      createPalmTree(Blocks.JUNGLE_LOG, ModBlocks.COCONUT_PALM_LEAVES.get(), 9, 4, 0, 0)
        .ignoreVines()
        .build()
    )
  }

  val ACAI_PALM_TREE = REGISTRATE.configuredFeature("acai_palm_tree") { k, c, u ->
    u.register(
      c, k, Feature.TREE,
      createAcaiTree(Blocks.JUNGLE_LOG, ModBlocks.ACAI_PALM_LEAVES.get(), 9, 4, 0, 0)
        .ignoreVines()
        .decorators(listOf(AcaiTreeDecorator(0.25f)))
        .build()
    )
  }

  val PATCH_WILD_GARLIC = REGISTRATE.configuredFeature("patch_wild_garlic") { k, c, u ->
    u.register(
      c, k, DeltaboxFeatures.WILD_CROP.get(),
      u.wildCropWithFloorConfig(
        ModBlocks.WILD_GARLIC.get(),
        Blocks.GRASS,
        BlockPredicate.matchesTag(u.BLOCK_BELOW, BlockTags.DIRT),
        Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT)
      )
    )
  }
  val PATCH_WILD_COLLARD_GREENS = REGISTRATE.configuredFeature("patch_wild_collard_greens") { k, c, u ->
    u.register(
      c, k, DeltaboxFeatures.WILD_CROP.get(),
      u.wildCropWithFloorConfig(
        ModBlocks.WILD_COLLARD_GREENS.get(),
        Blocks.GRASS,
        BlockPredicate.matchesTag(u.BLOCK_BELOW, BlockTags.DIRT),
        Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT)
      )
    )
  }
  val PATCH_WILD_COFFEE_BUSH = REGISTRATE.configuredFeature("patch_wild_coffee_berries") { k, c, u ->
    u.register(
      c, k, DeltaboxFeatures.WILD_CROP.get(),
      u.wildCropWithFloorConfig(
        ModBlocks.WILD_COFFEE_BUSH.get(),
        Blocks.GRASS,
        BlockPredicate.matchesTag(u.BLOCK_BELOW, BlockTags.DIRT),
        Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT)
      )
    )
  }
  val PATCH_WILD_CASSAVA = REGISTRATE.configuredFeature("patch_wild_cassava") { k, c, u ->
    u.register(
      c, k, DeltaboxFeatures.WILD_CROP.get(),
      u.wildCropWithFloorConfig(
        ModBlocks.WILD_CASSAVA.get(),
        Blocks.GRASS,
        BlockPredicate.matchesTag(u.BLOCK_BELOW, BlockTags.DIRT),
        Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT)
      )
    )
  }
  val PATCH_WILD_CORN = REGISTRATE.configuredFeature("patch_wild_corn") { k, c, u ->
    u.register(
      c, k, DeltaboxFeatures.WILD_CROP.get(),
      u.wildCropWithFloorConfig(
        ModBlocks.WILD_CORN.get(),
        Blocks.GRASS,
        BlockPredicate.matchesTag(u.BLOCK_BELOW, BlockTags.DIRT),
        Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT)
      )
    )
  }
  val PATCH_WILD_GUARANA = REGISTRATE.configuredFeature("patch_wild_guarana") { k, c, u ->
    u.register(
      c, k, DeltaboxFeatures.WILD_CROP.get(),
      u.wildCropWithFloorConfig(
        ModBlocks.WILD_GUARANA.get(),
        Blocks.GRASS,
        BlockPredicate.matchesTag(u.BLOCK_BELOW, BlockTags.DIRT),
        Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT)
      )
    )
  }
  val PATCH_WILD_BEANS = REGISTRATE.configuredFeature("patch_wild_beans") { k, c, u ->
    u.register(
      c, k, DeltaboxFeatures.WILD_CROP.get(),
      u.wildCropWithFloorConfig(
        ModBlocks.WILD_BEANS.get(),
        Blocks.GRASS,
        BlockPredicate.matchesTag(u.BLOCK_BELOW, BlockTags.DIRT),
        Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT)
      )
    )
  }
  val PATCH_YERBA_MATE = REGISTRATE.configuredFeature("patch_yerba_mate") { k, c, u ->
    u.register(
      c, k, DeltaboxFeatures.WILD_CROP.get(),
      u.wildCropWithFloorConfig(
        ModBlocks.YERBA_MATE_BUSH.get(),
        Blocks.GRASS,
        BlockPredicate.matchesTag(u.BLOCK_BELOW, BlockTags.DIRT),
        Blocks.COARSE_DIRT, BlockPredicate.matchesTag(BlockTags.DIRT)
      )
    )
  }

  private fun createPalmTree(
    logBlock: Block,
    leavesBlock: Block,
    baseHeight: Int,
    heightRandA: Int,
    heightRandB: Int,
    radius: Int
  ): TreeConfigurationBuilder {
    return TreeConfigurationBuilder(
      BlockStateProvider.simple(logBlock),
      CrookedTrunkPlacer(baseHeight, heightRandA, heightRandB),
      BlockStateProvider.simple(leavesBlock),
      CoconutPalmFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0)),
      TwoLayersFeatureSize(1, 0, 1)
    )
  }

  private fun createAcaiTree(
    logBlock: Block,
    leavesBlock: Block,
    baseHeight: Int,
    heightRandA: Int,
    heightRandB: Int,
    radius: Int
  ): TreeConfigurationBuilder {
    return TreeConfigurationBuilder(
      BlockStateProvider.simple(logBlock),
      StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
      BlockStateProvider.simple(leavesBlock),
      PalmFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0)),
      TwoLayersFeatureSize(1, 0, 1)
    )
  }

  fun register() {
    // init
  }
}