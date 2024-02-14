package com.dannbrown.discoverplanetzero.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractPlacedFeaturesGen
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.init.AddonBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.util.valueproviders.ClampedInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.RarityFilter

object AddonPlacedFeatures: AbstractPlacedFeaturesGen() {
  override val modId: String = AddonContent.MOD_ID

  // @ KEYS
  val PHOSPHORITE_ORE_UPPER_PLACED: ResourceKey<PlacedFeature> = registerKey("phosphorite_ore_upper_placed")
  val PHOSPHORITE_ORE_LOWER_PLACED: ResourceKey<PlacedFeature> = registerKey("phosphorite_ore_lower_placed")
  val JOSHUA_PLACED: ResourceKey<PlacedFeature> = registerKey("joshua_placed")
  val RED_SAND_BOULDER_PLACED: ResourceKey<PlacedFeature> = registerKey("red_sand_boulder_placed")
  val BOULDER_COLUMNS_PLACED: ResourceKey<PlacedFeature> = registerKey("boulder_columns_placed")
  val BROWN_SLATE_VEGETATION_PLACED: ResourceKey<PlacedFeature> = registerKey("brown_slate_vegetation_placed")
  val SPARSE_DRY_GRASSES_PLACED: ResourceKey<PlacedFeature> = registerKey("sparse_dry_grasses_placed")
  val DRY_PATCHES_NOISE_PLACED: ResourceKey<PlacedFeature> = registerKey("dry_patches_noise_placed")
  val PATCH_DRY_SHRUBS_PLACED: ResourceKey<PlacedFeature> = registerKey("patch_dry_shrubs_placed")
  val PATCH_DEAD_GRASSES_PLACED: ResourceKey<PlacedFeature> = registerKey("patch_dead_grasses_placed")
  val PATCH_GUAYULE_SHRUBS_PLACED: ResourceKey<PlacedFeature> = registerKey("patch_guayule_shrubs_placed")
  val PATCH_OCOTILLO_PLACED: ResourceKey<PlacedFeature> = registerKey("patch_ocotillo_placed")
  val ROSEATE_SPIKES_PLACED: ResourceKey<PlacedFeature> = registerKey("roseate_spikes_placed")
  val ROSEATE_BOULDER_PLACED: ResourceKey<PlacedFeature> = registerKey("roseate_boulder_placed")
  val ROSEATE_GEODE_PLACED: ResourceKey<PlacedFeature> = registerKey("roseate_geode_placed")
  val PATCH_HIMALAYAN_SALT_PLACED: ResourceKey<PlacedFeature> = registerKey("patch_himalayan_salt_placed")

  override fun bootstrap(context: BootstapContext<PlacedFeature>) {
    val configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE)


    // PHOSPHORITE
    register(
      context,
      PHOSPHORITE_ORE_UPPER_PLACED,
      configuredFeatures.getOrThrow(AddonConfiguredFeatures.PHOSPHORITE_ORE_KEY),
      rareOrePlacement(
        2,  // VeinsPerChunk
        HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))
      )
    )
    register(
      context,
      PHOSPHORITE_ORE_LOWER_PLACED,
      configuredFeatures.getOrThrow(AddonConfiguredFeatures.PHOSPHORITE_ORE_KEY),
      commonOrePlacement(
        2,  // VeinsPerChunk
        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))
      )
    )


    // JOSHUA TREE
    register(
      context, JOSHUA_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.JOSHUA_TREE_KEY),
      VegetationPlacements.treePlacement(
        RarityFilter.onAverageOnceEvery(8),
        AddonBlocks.JOSHUA_FAMILY.SAPLING!!.get()
      )
    )

    // ROSEATE BOULDER
    register(
      context, ROSEATE_BOULDER_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.ROSEATE_BOULDER_KEY),
      listOf(
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(40),
      )
    )
    // RED SAND BOULDER
    register(
      context, RED_SAND_BOULDER_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.RED_SAND_BOULDER_KEY),
      listOf(
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(50),
      )
    )
    // BOULDER COLUMNS
    register(
      context, BOULDER_COLUMNS_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.BOULDER_COLUMNS_KEY),
      listOf(
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(30),
      )
    )
    // ROSEATE SPIKES
    register(
      context, ROSEATE_SPIKES_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.ROSEATE_SPIKES_KEY),
      listOf(
        CountPlacement.of(1),
        InSquarePlacement.spread(),
        HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(15),
      )
    )
    // ROSEATE GEODE
    register(
      context, ROSEATE_GEODE_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.ROSEATE_GEODE_KEY),
      listOf(
        RarityFilter.onAverageOnceEvery(20),
        InSquarePlacement.spread(),
        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
        BiomeFilter.biome()
      )
    )
    // PATCH HIMALAYAN SALT CLUSTERS
    register(
      context,
      PATCH_HIMALAYAN_SALT_PLACED,
      configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_HIMALAYAN_SALT_KEY),
      listOf(
        InSquarePlacement.spread(),
        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(2),
      )
    )
    // SPARSE DRY GRASSES
    register(
      context,
      SPARSE_DRY_GRASSES_PLACED,
      configuredFeatures.getOrThrow(AddonConfiguredFeatures.SPARSE_DRY_GRASSES_SAND_KEY),
      listOf(
        InSquarePlacement.spread(),
        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(2),
      )
    )
    // DRY PATCHES
    register(
      context, DRY_PATCHES_NOISE_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.DRY_PATCHES_KEY),
      listOf(
        NoiseThresholdCountPlacement.of(-0.25, 7, 0),
        InSquarePlacement.spread(),
        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(6),
      )
    )

    // PATCH DRY SHRUBS
    register(
      context, PATCH_DRY_SHRUBS_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_DRY_SHRUB_KEY),
      listOf(
        InSquarePlacement.spread(),
        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(3),
      )
    )
    // PATCH DEAD GRASSES
    register(
      context, PATCH_DEAD_GRASSES_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_DEAD_GRASS_KEY),
      listOf(
        InSquarePlacement.spread(),
        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(3),
      )
    )
    // PATCH GUAYULE SHRUBS
    register(
      context,
      PATCH_GUAYULE_SHRUBS_PLACED,
      configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_GUAYULE_SHRUB_KEY),
      listOf(
        InSquarePlacement.spread(),
        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(3),
      )
    )
    // PATCH OCOTILLO
    register(
      context, PATCH_OCOTILLO_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_OCOTILLO_KEY),
      listOf(
        InSquarePlacement.spread(),
        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
        BiomeFilter.biome(),
        RarityFilter.onAverageOnceEvery(2),
      )
    )
    // BROWN SLATE VEGETATION
    register(
      context,
      BROWN_SLATE_VEGETATION_PLACED,
      configuredFeatures.getOrThrow(AddonConfiguredFeatures.BROWN_SLATE_VEGETATION_KEY),
      listOf(
        RarityFilter.onAverageOnceEvery(2),
        InSquarePlacement.spread(),
        PlacementUtils.HEIGHTMAP,
        CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1), 0, 1)),
        BiomeFilter.biome()
      )
    )
  }

}