package com.dannbrown.braziliandelight.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractPlacedFeaturesGen
import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonBlocks
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.util.valueproviders.ClampedInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.placement.RarityFilter
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures

object AddonPlacedFeatures: AbstractPlacedFeaturesGen() {
  override val modId: String = AddonContent.MOD_ID

  val LEMON_TREE_PLACED = registerKey("lemon_tree_placed")
  val PATCH_WILD_GARLIC_PLACED = registerKey("patch_wild_garlic_placed")
  val PATCH_WILD_COLLARD_GREENS_PLACED = registerKey("patch_wild_collard_greens_placed")
  val PATCH_WILD_COFFEE_BERRIES_PLACED = registerKey("patch_wild_coffee_berries_placed")
  val PATCH_WILD_CASSAVA_PLACED = registerKey("patch_wild_cassava_placed")
  val PATCH_WILD_CORN_PLACED = registerKey("patch_wild_corn_placed")
  val PATCH_WILD_GUARANA_PLACED = registerKey("patch_wild_guarana_placed")
  val PATCH_WILD_BEANS_PLACED = registerKey("patch_wild_beans_placed")

  override fun bootstrap(context: BootstapContext<PlacedFeature>) {
    val configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE)

    // LEMON TREE
    register(
      context, LEMON_TREE_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.LEMON_TREE_KEY),
      VegetationPlacements.treePlacement(
        RarityFilter.onAverageOnceEvery(32),
        AddonBlocks.LEMON_SAPLING.get(),
      )
    )

    // PATCH WILD GARLIC
    register(
      context, PATCH_WILD_GARLIC_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_WILD_GARLIC_KEY),
      wildCropPlaced(82)
    )

    // PATCH WILD COLLARD GREENS
    register(
      context, PATCH_WILD_COLLARD_GREENS_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_WILD_COLLARD_GREENS_KEY),
      wildCropPlaced(120)
    )

    // PATCH WILD COFFEE BERRIES
    register(
      context, PATCH_WILD_COFFEE_BERRIES_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_WILD_COFFEE_BERRIES_KEY),
      wildCropPlaced(90)
    )

    // PATCH WILD CASSAVA
    register(
      context, PATCH_WILD_CASSAVA_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_WILD_CASSAVA_KEY),
      wildCropPlaced(122)
    )

    // PATCH WILD CORN
    register(
      context, PATCH_WILD_CORN_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_WILD_CORN_KEY),
      wildCropPlaced(130)
    )

    // PATCH WILD GUARANA
    register(
      context, PATCH_WILD_GUARANA_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_WILD_GUARANA_KEY),
      wildCropPlaced(110)
    )

    // PATCH WILD BEANS
    register(
      context, PATCH_WILD_BEANS_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.PATCH_WILD_BEANS_KEY),
      wildCropPlaced(100)
    )
  }

  private fun wildCropPlaced(chance: Int,): List<PlacementModifier> {
    return listOf(RarityFilter.onAverageOnceEvery(chance), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())
  }

}