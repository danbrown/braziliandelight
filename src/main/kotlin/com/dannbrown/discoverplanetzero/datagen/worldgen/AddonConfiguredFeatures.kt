package com.dannbrown.discoverplanetzero.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractConfiguredFeaturesGen
import com.dannbrown.discover.content.worldgen.featureConfiguration.BoulderColumnConfig
import com.dannbrown.discover.content.worldgen.featureConfiguration.BoulderConfig
import com.dannbrown.discover.content.worldgen.featureConfiguration.SpikeConfig
import com.dannbrown.discover.datagen.worldgen.DiscoverFeatures
import com.dannbrown.discover.init._DiscoverBlocks
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.datagen.worldgen.trunkPlacer.ForkingStalkTrunkPlacer
import com.dannbrown.discoverplanetzero.init.AddonBlocks
import net.minecraft.core.Direction
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.data.worldgen.features.CaveFeatures
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.GeodeBlockSettings
import net.minecraft.world.level.levelgen.GeodeCrackSettings
import net.minecraft.world.level.levelgen.GeodeLayerSettings
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceSphereConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.placement.RarityFilter

object AddonConfiguredFeatures: AbstractConfiguredFeaturesGen() {
  override val modId: String = AddonContent.MOD_ID

  val RED_HEMATITE_IRON_ORE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("red_hematite_iron_ore")
  val PHOSPHORITE_ORE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("phosphorite_ore")
  val JOSHUA_TREE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("joshua_tree")
  val ROSEATE_BOULDER_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("roseate_boulder")
  val ROSEATE_SMALL_BOULDER_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("roseate_small_boulder")
  val ROSEATE_LARGE_BOULDER_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("roseate_large_boulder")
  val ROSEATE_HOLLOW_BOULDER_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("roseate_hollow_boulder")
  val RED_SAND_BOULDER_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("red_sand_boulder")
  val RED_SANDSTONE_SMALL_BOULDER_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("red_sand_small_boulder")
  val RED_SANDSTONE_LARGE_BOULDER_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("red_sand_large_boulder")
  val ROSEATE_GEODE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("roseate_geode")
  val BOULDER_COLUMNS_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("boulder_columns")
  val SMALL_BOULDER_COLUMN_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("small_boulder_column")
  val TALL_BOULDER_COLUMN_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("tall_boulder_column")
  val BROWN_SLATE_VEGETATION_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("brown_slate_vegetation")
  val ROSEATE_SPIKES_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("roseate_spikes")
  val ROSEATE_SMALL_SPIKE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("roseate_small_spike")
  val ROSEATE_TALL_SPIKE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("roseate_tall_spike")
  val ROSEATE_GIANT_SPIKE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("roseate_giant_spike")
  val PATCH_HIMALAYAN_SALT_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("patch_himalayan_salt")
  val SPARSE_DRY_GRASSES_SAND_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("sparse_dry_grasses_sand")
  val DRY_PATCHES_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("dry_patches")
  val PATCH_DRY_SHRUB_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("patch_dry_shrub")
  val PATCH_DEAD_GRASS_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("patch_dead_grass")
  val PATCH_GUAYULE_SHRUB_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("patch_guayule_shrub")
  val PATCH_OCOTILLO_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("patch_ocotillo")


  override fun bootstrap(context: BootstapContext<ConfiguredFeature<*, *>>) {
    val lookup = context.lookup(Registries.CONFIGURED_FEATURE)

    // RED HEMATITE IRON
    register(
      context, RED_HEMATITE_IRON_ORE_KEY, Feature.REPLACE_BLOBS, ReplaceSphereConfiguration(
        _DiscoverBlocks.RED_HEMATITE.get()
          .defaultBlockState(),
        _DiscoverBlocks.RED_HEMATITE_IRON_ORE.get()
          .defaultBlockState(),
        UniformInt.of(1, 2),
      )
    )
    // PHOSPHORITE
    register(
      context, PHOSPHORITE_ORE_KEY, Feature.ORE, OreConfiguration(
        listOf(
          OreConfiguration.target(stoneReplaceable,
            AddonBlocks.PHOSPHORITE_FAMILY.MAIN!!.get()
              .defaultBlockState()),
          OreConfiguration.target(
            deepslateReplaceables,
            AddonBlocks.PHOSPHORITE_FAMILY.MAIN!!.get()
              .defaultBlockState()
          )
        ), 64
      )
    )
    // JOSHUA TREE
    register(
      context, JOSHUA_TREE_KEY, Feature.TREE, TreeConfiguration.TreeConfigurationBuilder(
        BlockStateProvider.simple(AddonBlocks.JOSHUA_FAMILY.STALK!!.get()),
        ForkingStalkTrunkPlacer(5, 6, 3),
        BlockStateProvider.simple(AddonBlocks.JOSHUA_FAMILY.LEAVES!!.get()),
        AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
        TwoLayersFeatureSize(1, 0, 2)
      )
        .build()
    )

    // ROSEATE BOULDER
    register(
      context, ROSEATE_HOLLOW_BOULDER_KEY, DiscoverFeatures.BOULDER.get(), BoulderConfig.Builder()
        .setRadiusSettings(
          BoulderConfig.RadiusSettings(
            UniformInt.of(16, 24), // x radius
            UniformInt.of(10, 16), 0, // y radius
            UniformInt.of(16, 24)
          ) // z radius
        )
        .setBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.AIR.defaultBlockState() to 1,
            )
          )
        )
        .setMiddleBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.RAW_HIMALAYAN_SALT.get()
                .defaultBlockState() to 3,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 1,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
            )
          )
        )
        .setTopBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
                .defaultBlockState() to 4,
              AddonBlocks.ROSEATE_FAMILY.SMOOTH!!.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
            )
          )
        )
        .setPlacementPredicates(
          listOf(
            BlockPredicate.matchesTag(BlockTags.SAND),
          )
        )
        .build()
    )


    register(
      context, ROSEATE_LARGE_BOULDER_KEY, DiscoverFeatures.BOULDER.get(), BoulderConfig.Builder()
        .setRadiusSettings(
          BoulderConfig.RadiusSettings(
            UniformInt.of(16, 24), // x radius
            UniformInt.of(10, 16), 0, // y radius
            UniformInt.of(16, 24)
          ) // z radius
        )
        .setBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
                .defaultBlockState() to 4,
              AddonBlocks.ROSEATE_FAMILY.SMOOTH!!.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
            )
          )
        )
        .setMiddleBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
                .defaultBlockState() to 4,
              AddonBlocks.ROSEATE_FAMILY.SMOOTH!!.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
            )
          )
        )
        .setTopBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
                .defaultBlockState() to 4,
              AddonBlocks.ROSEATE_FAMILY.SMOOTH!!.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
            )
          )
        )
        .setPlacementPredicates(
          listOf(
            BlockPredicate.matchesTag(BlockTags.SAND),
          )
        )
        .build()
    )

    register(
      context, ROSEATE_SMALL_BOULDER_KEY, DiscoverFeatures.BOULDER.get(), BoulderConfig.Builder()
        .setRadiusSettings(
          BoulderConfig.RadiusSettings(
            UniformInt.of(8, 16), // x radius
            UniformInt.of(5, 8), 0, // y radius
            UniformInt.of(8, 16)
          ) // z radius
        )
        .setBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
                .defaultBlockState() to 4,
              AddonBlocks.ROSEATE_FAMILY.SMOOTH!!.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
            )
          )
        )
        .setMiddleBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
                .defaultBlockState() to 4,
              AddonBlocks.ROSEATE_FAMILY.SMOOTH!!.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
            )
          )
        )
        .setTopBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
                .defaultBlockState() to 4,
              AddonBlocks.ROSEATE_FAMILY.SMOOTH!!.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
            )
          )
        )
        .setPlacementPredicates(
          listOf(
            BlockPredicate.matchesTag(BlockTags.SAND),
          )
        )
        .build()
    )
    // ROSEATE BOULDER
    register(
      context, ROSEATE_BOULDER_KEY, Feature.RANDOM_SELECTOR, RandomFeatureConfiguration(
        listOf(
          WeightedPlacedFeature(directPlacedFeature(lookup.getOrThrow(ROSEATE_LARGE_BOULDER_KEY)), 0.75f),
          WeightedPlacedFeature(directPlacedFeature(lookup.getOrThrow(ROSEATE_HOLLOW_BOULDER_KEY)), 0.25f)
        ),
        directPlacedFeature(lookup.getOrThrow(ROSEATE_SMALL_BOULDER_KEY))
      )
    )
    // ROSEATE GEODE
    register(
      context, ROSEATE_GEODE_KEY, Feature.GEODE, GeodeConfiguration(
        GeodeBlockSettings(
          BlockStateProvider.simple(Blocks.AIR),
          BlockStateProvider.simple(AddonBlocks.RAW_HIMALAYAN_SALT.get()),
          BlockStateProvider.simple(AddonBlocks.RAW_HIMALAYAN_SALT.get()),
          BlockStateProvider.simple(Blocks.CALCITE),
          BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
          listOf(
            AddonBlocks.HIMALAYAN_SALT_CLUSTER.get()
              .defaultBlockState(),
//            Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(),
          ),
          BlockTags.FEATURES_CANNOT_REPLACE,
          BlockTags.GEODE_INVALID_BLOCKS
        ),
        GeodeLayerSettings(1.7, 2.2, 3.2, 4.2),
        GeodeCrackSettings(0.95, 2.0, 2),
        0.35,
        0.083,
        true,
        UniformInt.of(4, 6),
        UniformInt.of(3, 4),
        UniformInt.of(1, 2),
        -16,
        16,
        0.05,
        1
      )
    )
    // RED SAND BOULDER
    register(
      context, RED_SANDSTONE_LARGE_BOULDER_KEY, DiscoverFeatures.BOULDER.get(), BoulderConfig.Builder()
        .setRadiusSettings(
          BoulderConfig.RadiusSettings(
            UniformInt.of(16, 24), // x radius
            UniformInt.of(10, 16), 0, // y radius
            UniformInt.of(16, 24)
          ) // z radius
        )
        .setBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.RED_SANDSTONE.defaultBlockState() to 4,
              Blocks.SMOOTH_RED_SANDSTONE.defaultBlockState() to 2,
              AddonBlocks.RED_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
            )
          )
        )
        .setMiddleBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.RED_SANDSTONE.defaultBlockState() to 4,
              Blocks.SMOOTH_RED_SANDSTONE.defaultBlockState() to 2,
              AddonBlocks.RED_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
            )
          )
        )
        .setTopBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.RED_SANDSTONE.defaultBlockState() to 4,
              Blocks.ORANGE_TERRACOTTA.defaultBlockState() to 3,
              Blocks.SMOOTH_RED_SANDSTONE.defaultBlockState() to 2,
              AddonBlocks.RED_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
            )
          )
        )
        .setPlacementPredicates(
          listOf(
            BlockPredicate.matchesTag(BlockTags.SAND),
          )
        )
        .build()
    )

    register(
      context, RED_SANDSTONE_SMALL_BOULDER_KEY, DiscoverFeatures.BOULDER.get(), BoulderConfig.Builder()
        .setRadiusSettings(
          BoulderConfig.RadiusSettings(
            UniformInt.of(8, 16), // x radius
            UniformInt.of(5, 8), 0, // y radius
            UniformInt.of(8, 16)
          ) // z radius
        )
        .setBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.RED_SANDSTONE.defaultBlockState() to 4,
              Blocks.ORANGE_TERRACOTTA.defaultBlockState() to 3,
              Blocks.SMOOTH_RED_SANDSTONE.defaultBlockState() to 2,
              AddonBlocks.RED_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
            )
          )
        )
        .setMiddleBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.RED_SANDSTONE.defaultBlockState() to 4,
              Blocks.SMOOTH_RED_SANDSTONE.defaultBlockState() to 2,
              AddonBlocks.RED_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
            )
          )
        )
        .setTopBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.RED_SANDSTONE.defaultBlockState() to 4,
              Blocks.SMOOTH_RED_SANDSTONE.defaultBlockState() to 2,
              AddonBlocks.RED_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
            )
          )
        )
        .setPlacementPredicates(
          listOf(
            BlockPredicate.matchesTag(BlockTags.SAND),
          )
        )
        .build()
    )
    // RED SAND BOULDER
    register(
      context, RED_SAND_BOULDER_KEY, Feature.RANDOM_SELECTOR, RandomFeatureConfiguration(
        listOf(
          WeightedPlacedFeature(directPlacedFeature(lookup.getOrThrow(RED_SANDSTONE_LARGE_BOULDER_KEY)), 0.75f)
        ),
        directPlacedFeature(lookup.getOrThrow(RED_SANDSTONE_SMALL_BOULDER_KEY))
      )
    )
    // BASIC BOULDER COLUMN
    register(
      context, SMALL_BOULDER_COLUMN_KEY, DiscoverFeatures.BOULDER_COLUMN.get(), BoulderColumnConfig.Builder()
        .setStackHeight(UniformInt.of(1, 3))
        .setPointed(false)
        .setRadiusSettings(
          BoulderColumnConfig.RadiusSettings(
            UniformInt.of(32, 40), // x radius
            UniformInt.of(10, 16), 0, // y radius
            UniformInt.of(32, 40)
          ) // z radius
        )
        .setBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.DEEPSLATE.defaultBlockState() to 6,
              Blocks.COBBLED_DEEPSLATE.defaultBlockState() to 3,
              Blocks.AIR.defaultBlockState() to 1
            )
          )
        )
        .setMiddleBlockProvider(
          weightedStateProvider(
            mapOf(
              _DiscoverBlocks.BROWN_SLATE.get()
                .defaultBlockState() to 6,
              _DiscoverBlocks.COBBLED_BROWN_SLATE.get()
                .defaultBlockState() to 2,
              Blocks.AIR.defaultBlockState() to 1
            )
          )
        )
        .setTopBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.MOSS_CARPET.defaultBlockState() to 5,
              Blocks.AIR.defaultBlockState() to 1
            )
          )
        )
        .setPlacementPredicates(
          listOf(
            BlockPredicate.matchesBlocks(Blocks.MUD),
            BlockPredicate.matchesBlocks(_DiscoverBlocks.COBBLED_BROWN_SLATE.get()),
            BlockPredicate.matchesBlocks(Blocks.BROWN_TERRACOTTA),
          )
        )
        .withSpawningFeatures(
          listOf(
            directPlacedFeature(lookup.getOrThrow(CaveFeatures.GLOW_LICHEN), RarityFilter.onAverageOnceEvery(20)),
            directPlacedFeature(lookup.getOrThrow(CaveFeatures.MOSS_PATCH), RarityFilter.onAverageOnceEvery(10)),
          )
        )
        .build()
    )
    // TALL BOULDER COLUMN
    register(
      context, TALL_BOULDER_COLUMN_KEY, DiscoverFeatures.BOULDER_COLUMN.get(), BoulderColumnConfig.Builder()
        .setStackHeight(UniformInt.of(3, 7))
        .setPointed(true)
        .setRadiusSettings(
          BoulderColumnConfig.RadiusSettings(
            UniformInt.of(16, 24), // x radius
            UniformInt.of(10, 16), 0, // y radius
            UniformInt.of(16, 24)
          ) // z radius
        )
        .setBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.DEEPSLATE.defaultBlockState() to 6,
              Blocks.COBBLED_DEEPSLATE.defaultBlockState() to 3,
              Blocks.AIR.defaultBlockState() to 1
            )
          )
        )
        .setMiddleBlockProvider(
          weightedStateProvider(
            mapOf(
              _DiscoverBlocks.BROWN_SLATE.get()
                .defaultBlockState() to 6,
              _DiscoverBlocks.COBBLED_BROWN_SLATE.get()
                .defaultBlockState() to 2,
              Blocks.AIR.defaultBlockState() to 1
            )
          )
        )
        .setTopBlockProvider(
          weightedStateProvider(
            mapOf(
              Blocks.MOSS_CARPET.defaultBlockState() to 5,
              Blocks.AIR.defaultBlockState() to 1
            )
          )
        )
        .setPlacementPredicates(
          listOf(
            BlockPredicate.matchesBlocks(Blocks.MUD),
            BlockPredicate.matchesBlocks(_DiscoverBlocks.COBBLED_BROWN_SLATE.get()),
            BlockPredicate.matchesBlocks(Blocks.BROWN_TERRACOTTA),
          )
        )
        .withSpawningFeatures(
          listOf(
            directPlacedFeature(lookup.getOrThrow(CaveFeatures.GLOW_LICHEN), RarityFilter.onAverageOnceEvery(20)),
            directPlacedFeature(lookup.getOrThrow(CaveFeatures.MOSS_PATCH), RarityFilter.onAverageOnceEvery(10)),
          )
        )
        .build()
    )
    // BOULDER COLUMNS
    register(
      context, BOULDER_COLUMNS_KEY, Feature.RANDOM_SELECTOR, RandomFeatureConfiguration(
        listOf(
          WeightedPlacedFeature(directPlacedFeature(lookup.getOrThrow(TALL_BOULDER_COLUMN_KEY)), 0.75f)
        ),
        directPlacedFeature(lookup.getOrThrow(SMALL_BOULDER_COLUMN_KEY))
      )
    )
    // ROSEATE SPIKES
    register(
      context, ROSEATE_SMALL_SPIKE_KEY, DiscoverFeatures.SPIKE.get(), SpikeConfig.Builder()
        .setBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
                .defaultBlockState() to 4,
              AddonBlocks.ROSEATE_FAMILY.SMOOTH!!.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
            )
          )
        )
        .setHeightVariance(UniformInt.of(15, 40))
        .build()
    )

    register(
      context, ROSEATE_TALL_SPIKE_KEY, DiscoverFeatures.SPIKE.get(), SpikeConfig.Builder()
        .setBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
                .defaultBlockState() to 6,
              AddonBlocks.ROSEATE_FAMILY.SMOOTH!!.get()
                .defaultBlockState() to 3,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
              Blocks.SMOOTH_SANDSTONE.defaultBlockState() to 1
            )
          )
        )
        .setHeightVariance(UniformInt.of(40, 75))
        .build()
    )

    register(
      context, ROSEATE_GIANT_SPIKE_KEY, DiscoverFeatures.SPIKE.get(), SpikeConfig.Builder()
        .setBlockProvider(
          weightedStateProvider(
            mapOf(
              AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
                .defaultBlockState() to 6,
              AddonBlocks.ROSEATE_FAMILY.SMOOTH!!.get()
                .defaultBlockState() to 3,
              AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                .defaultBlockState() to 2,
              AddonBlocks.ROSEATE_GRAINS.get()
                .defaultBlockState() to 1,
              Blocks.SMOOTH_SANDSTONE.defaultBlockState() to 1
            )
          )
        )
        .setHeightVariance(UniformInt.of(75, 90))
        .build()
    )

    register(
      context, ROSEATE_SPIKES_KEY, Feature.RANDOM_SELECTOR, RandomFeatureConfiguration(
        listOf(
          WeightedPlacedFeature(directPlacedFeature(lookup.getOrThrow(ROSEATE_GIANT_SPIKE_KEY)), 0.25f),
          WeightedPlacedFeature(directPlacedFeature(lookup.getOrThrow(ROSEATE_TALL_SPIKE_KEY)), 0.75f)
        ),
        directPlacedFeature(lookup.getOrThrow(ROSEATE_SMALL_SPIKE_KEY))
      )
    )
    // HIMALAYAN SALT CLUSTER
    register(
      context, PATCH_HIMALAYAN_SALT_KEY, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(
        8,
        PlacementUtils.filtered(
          Feature.SIMPLE_BLOCK,
          SimpleBlockConfiguration(
            BlockStateProvider.simple(AddonBlocks.HIMALAYAN_SALT_CLUSTER.get())
          ),
          BlockPredicate.allOf(
            BlockPredicate.ONLY_IN_AIR_PREDICATE,
            BlockPredicate.anyOf(
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.SAND),
            ),
          )
        )
      )
    )


    // SPARSE GRASSES
    register(
      context, SPARSE_DRY_GRASSES_SAND_KEY, Feature.RANDOM_SELECTOR, RandomFeatureConfiguration(
        listOf(
          WeightedPlacedFeature(
            PlacementUtils.inlinePlaced(
              Feature.RANDOM_PATCH,
              FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                SimpleBlockConfiguration(
                  BlockStateProvider.simple(AddonBlocks.TALL_SPARSE_DRY_GRASS.get())
                ),
                listOf(), 32,
              ), ON_SAND_FILTER
            ),
            0.33f
          )
        ),
        PlacementUtils.inlinePlaced(
          Feature.RANDOM_PATCH,
          FeatureUtils.simplePatchConfiguration(
            Feature.SIMPLE_BLOCK,
            SimpleBlockConfiguration(
              BlockStateProvider.simple(AddonBlocks.SPARSE_DRY_GRASS.get())
            ), listOf(), 32
          ), ON_SAND_FILTER
        ),
      )
    )
    // DRY PATCHES
    register(
      context, DRY_PATCHES_KEY, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(
        96,
        PlacementUtils.filtered(
          Feature.SIMPLE_BLOCK,
          SimpleBlockConfiguration(
            BlockStateProvider.simple(AddonBlocks.DRY_PATCHES.get())
          ),
          BlockPredicate.allOf(
            BlockPredicate.ONLY_IN_AIR_PREDICATE,
            BlockPredicate.anyOf(
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.SAND),
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.DIRT),
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.TERRACOTTA),
              BlockPredicate.matchesBlocks(Direction.DOWN.normal, _DiscoverBlocks.COBBLED_BROWN_SLATE.get()),
            ),
          )
        )
      )
    )

    // DRYS SHRUBS
    register(
      context, PATCH_DRY_SHRUB_KEY, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(
        6,
        PlacementUtils.filtered(
          Feature.SIMPLE_BLOCK,
          SimpleBlockConfiguration(
            BlockStateProvider.simple(AddonBlocks.DRY_SHRUB.get())
          ),
          BlockPredicate.allOf(
            BlockPredicate.ONLY_IN_AIR_PREDICATE,
            BlockPredicate.anyOf(
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.SAND),
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.DIRT),
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.TERRACOTTA),
              BlockPredicate.matchesBlocks(Direction.DOWN.normal, _DiscoverBlocks.COBBLED_BROWN_SLATE.get()),
            ),
          )
        )
      )
    )
    // DEAD GRASS
    register(
      context, PATCH_DEAD_GRASS_KEY, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(
        6,
        PlacementUtils.filtered(
          Feature.SIMPLE_BLOCK,
          SimpleBlockConfiguration(
            BlockStateProvider.simple(AddonBlocks.DEAD_GRASS.get())
          ),
          BlockPredicate.allOf(
            BlockPredicate.ONLY_IN_AIR_PREDICATE,
            BlockPredicate.anyOf(
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.SAND),
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.DIRT),
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.TERRACOTTA),
            ),
          )
        )
      )
    )
    // GUAYULE SHRUBS
    register(
      context, PATCH_GUAYULE_SHRUB_KEY, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(
        6,
        PlacementUtils.filtered(
          Feature.SIMPLE_BLOCK,
          SimpleBlockConfiguration(
            BlockStateProvider.simple(AddonBlocks.GUAYULE_SHRUB.get())
          ),
          BlockPredicate.allOf(
            BlockPredicate.ONLY_IN_AIR_PREDICATE,
            BlockPredicate.anyOf(
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.SAND),
            ),
          )
        )
      )
    )
    // OCOTILLO
    register(
      context, PATCH_OCOTILLO_KEY, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(
        5,
        PlacementUtils.filtered(
          Feature.SIMPLE_BLOCK,
          SimpleBlockConfiguration(
            BlockStateProvider.simple(AddonBlocks.OCOTILLO.get())
          ),
          BlockPredicate.allOf(
            BlockPredicate.ONLY_IN_AIR_PREDICATE,
            BlockPredicate.anyOf(
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.SAND),
              BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.DIRT),
            ),
          )
        )
      )
    )
    // BROWN SLATE VEGETATION
    register(
      context, BROWN_SLATE_VEGETATION_KEY, Feature.SIMPLE_RANDOM_SELECTOR,
      SimpleRandomFeatureConfiguration(
        HolderSet.direct(
          PlacementUtils.inlinePlaced(
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
              Feature.SIMPLE_BLOCK,
              SimpleBlockConfiguration(
                BlockStateProvider.simple(AddonBlocks.OCOTILLO.get())
              )
            )
          ),
          PlacementUtils.inlinePlaced(
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
              Feature.SIMPLE_BLOCK,
              SimpleBlockConfiguration(
                BlockStateProvider.simple(AddonBlocks.DRY_PATCHES.get())
              )
            )
          ),
          PlacementUtils.inlinePlaced(
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
              Feature.SIMPLE_BLOCK,
              SimpleBlockConfiguration(
                BlockStateProvider.simple(Blocks.MOSS_CARPET)
              )
            )
          ),
          PlacementUtils.inlinePlaced(
            Feature.NO_BONEMEAL_FLOWER,
            FeatureUtils.simplePatchConfiguration(
              Feature.SIMPLE_BLOCK,
              SimpleBlockConfiguration(
                BlockStateProvider.simple(AddonBlocks.DRY_SHRUB.get())
              )
            )
          ),
          PlacementUtils.inlinePlaced(
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
              32, PlacementUtils.onlyWhenEmpty<SimpleBlockConfiguration, Feature<SimpleBlockConfiguration>>(
                Feature.SIMPLE_BLOCK,
                SimpleBlockConfiguration(BlockStateProvider.simple(AddonBlocks.SPARSE_DRY_GRASS.get()))
              )
            )
          )
        )
      )
    )
    // ----
  }
}