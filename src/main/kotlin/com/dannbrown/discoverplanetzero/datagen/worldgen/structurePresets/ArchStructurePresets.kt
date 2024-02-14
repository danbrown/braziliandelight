package com.dannbrown.discoverplanetzero.datagen.worldgen.structurePresets

import com.dannbrown.discover.content.core.utils.BlendingFunction
import com.dannbrown.discover.content.worldgen.featureConfiguration.BoulderConfig
import com.dannbrown.discover.content.worldgen.structures.ArchConfiguration
import com.dannbrown.discover.content.worldgen.structures.ArchStructure
import com.dannbrown.discover.init._DiscoverBlocks
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonConfiguredFeatures
import com.dannbrown.discoverplanetzero.init.AddonBlocks
import com.dannbrown.discoverplanetzero.init.AddonTags
import net.minecraft.Util
import net.minecraft.core.HolderGetter
import net.minecraft.data.worldgen.features.CaveFeatures
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.util.valueproviders.ConstantFloat
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement
import net.minecraft.world.level.levelgen.placement.RarityFilter
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment
import java.util.Map

class ArchStructurePresets (biomes: HolderGetter<Biome>, lookup: HolderGetter<ConfiguredFeature<*, *>>) {
  val LUSH_RED_ROCK_ARCH_PRESET = ArchStructure(
    Structure.StructureSettings(
      biomes.getOrThrow(AddonTags.BIOME.HAS_LUSH_RED_ARCHES),
      Map.of(),
      GenerationStep.Decoration.RAW_GENERATION,
      TerrainAdjustment.NONE
    ),
    Util.make(ArchConfiguration.Builder()) { builder ->
      val blockProvider = WeightedStateProvider(
        SimpleWeightedRandomList.builder<BlockState>()
          .add(Blocks.TERRACOTTA.defaultBlockState(), 6)
          .add(Blocks.ORANGE_TERRACOTTA.defaultBlockState(), 4)
          .add(Blocks.SMOOTH_RED_SANDSTONE.defaultBlockState(), 3)
          .add(Blocks.RED_SANDSTONE.defaultBlockState(), 3)
          .add(Blocks.GRANITE.defaultBlockState(), 3)
          .add(AddonBlocks.RED_SANDSTONE_PEBBLES.get()
            .defaultBlockState(), 1)
      )

      builder.withSphereConfig(
        BoulderConfig.Builder()
          .setRadiusSettings(
            BoulderConfig.RadiusSettings(
              UniformInt.of(5, 10),
              UniformInt.of(5, 10), 0,
              UniformInt.of(5, 10)
            )
          )
          .setBlockProvider(blockProvider)
          .setMiddleBlockProvider(blockProvider)
          .setTopBlockProvider(blockProvider)
          .withNoiseFrequency(0.1f)
          .withSpawningFeatures(
            listOf(
              AddonConfiguredFeatures.directPlacedFeature(
                lookup.getOrThrow(CaveFeatures.GLOW_LICHEN),
                RarityFilter.onAverageOnceEvery(50)
              ),
              AddonConfiguredFeatures.directPlacedFeature(
                lookup.getOrThrow(CaveFeatures.MOSS_PATCH),
                RarityFilter.onAverageOnceEvery(10)
              ),
              AddonConfiguredFeatures.directPlacedFeature(
                lookup.getOrThrow(CaveFeatures.MOSS_PATCH_CEILING),
                RarityFilter.onAverageOnceEvery(30),
                RandomOffsetPlacement.vertical(UniformInt.of(-15, -10))
              ),
            )
          )
          .build()
      )

      builder.withMatchingBlendingFunctionChance(ConstantFloat.of(0.2f))
      builder.withPercentageDestroyed(ConstantFloat.of(0f))
      builder.withBlendingFunctionType(
        SimpleWeightedRandomList.builder<BlendingFunction>()
          .add(BlendingFunction.EASE_OUT_CUBIC, 16)
          .add(BlendingFunction.EAST_IN_OUT_CIRC, 8)
          .add(BlendingFunction.EASE_OUT_BOUNCE, 1)
          .build()
      )
    }
      .build()
  )
  val RED_ROCK_ARCH_PRESET = ArchStructure(
    Structure.StructureSettings(
      biomes.getOrThrow(AddonTags.BIOME.HAS_RED_ARCHES),
      Map.of(),
      GenerationStep.Decoration.RAW_GENERATION,
      TerrainAdjustment.NONE
    ),
    Util.make(ArchConfiguration.Builder()) { builder ->
      val blockProvider = WeightedStateProvider(
        SimpleWeightedRandomList.builder<BlockState>()
//              .add(Blocks.RED_SANDSTONE.defaultBlockState(), 4)
//              .add(Blocks.SMOOTH_RED_SANDSTONE.defaultBlockState(), 4)
          .add(_DiscoverBlocks.RED_HEMATITE.get()
            .defaultBlockState(), 3)
          .add(_DiscoverBlocks.COBBLED_RED_HEMATITE.get()
            .defaultBlockState(), 1)
//              .add(DiscoverBlocks.RED_SANDSTONE_PEBBLES.get().defaultBlockState(), 3)
      )

      builder.withSphereConfig(
        BoulderConfig.Builder()
          .setRadiusSettings(
            BoulderConfig.RadiusSettings(
              UniformInt.of(5, 12),
              UniformInt.of(5, 12), 0,
              UniformInt.of(5, 12)
            )
          )
          .setBlockProvider(blockProvider)
          .setMiddleBlockProvider(blockProvider)
          .setTopBlockProvider(blockProvider)
          .withNoiseFrequency(0.1f)
          .withSpawningFeatures(
            listOf(
              AddonConfiguredFeatures.directPlacedFeature(
                lookup.getOrThrow(AddonConfiguredFeatures.RED_HEMATITE_IRON_ORE_KEY),
                RarityFilter.onAverageOnceEvery(512)
              ),
            )
          )
          .build()
      )

      builder.withMatchingBlendingFunctionChance(ConstantFloat.of(0.2f))
      builder.withPercentageDestroyed(ConstantFloat.of(0f))
      builder.withBlendingFunctionType(
        SimpleWeightedRandomList.builder<BlendingFunction>()
          .add(BlendingFunction.EASE_OUT_CUBIC, 16)
          .add(BlendingFunction.EAST_IN_OUT_CIRC, 8)
          .add(BlendingFunction.EASE_OUT_BOUNCE, 1)
          .build()
      )
    }
      .build()
  )
  val GRANITE_RED_ROCK_ARCH_PRESET = ArchStructure(
    Structure.StructureSettings(
      biomes.getOrThrow(AddonTags.BIOME.HAS_GRANITE_ARCHES),
      Map.of(),
      GenerationStep.Decoration.RAW_GENERATION,
      TerrainAdjustment.NONE
    ),
    Util.make(ArchConfiguration.Builder()) { builder ->
      val blockProvider = WeightedStateProvider(
        SimpleWeightedRandomList.builder<BlockState>()
          .add(Blocks.TERRACOTTA.defaultBlockState(), 12)
          .add(Blocks.GRANITE.defaultBlockState(), 10)
      )

      builder.withSphereConfig(
        BoulderConfig.Builder()
          .setRadiusSettings(
            BoulderConfig.RadiusSettings(
              UniformInt.of(5, 15),
              UniformInt.of(5, 15), 0,
              UniformInt.of(5, 15)
            )
          )
          .setBlockProvider(blockProvider)
          .setMiddleBlockProvider(blockProvider)
          .setTopBlockProvider(blockProvider)
          .withNoiseFrequency(0.1f)
          .build()
      )

      builder.withMatchingBlendingFunctionChance(ConstantFloat.of(0.2f))
      builder.withPercentageDestroyed(ConstantFloat.of(0f))
      builder.withBlendingFunctionType(
        SimpleWeightedRandomList.builder<BlendingFunction>()
          .add(BlendingFunction.EASE_OUT_CUBIC, 16)
          .add(BlendingFunction.EAST_IN_OUT_CIRC, 8)
          .add(BlendingFunction.EASE_OUT_BOUNCE, 1)
          .build()
      )
    }
      .build()
  )
  // ----
}
