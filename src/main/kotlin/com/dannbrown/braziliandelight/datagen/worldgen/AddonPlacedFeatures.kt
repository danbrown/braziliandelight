package com.dannbrown.braziliandelight.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractPlacedFeaturesGen
import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonBlocks
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

  val LEMON_TREE_PLACED = registerKey("lemon_tree_placed")

  override fun bootstrap(context: BootstapContext<PlacedFeature>) {
    val configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE)

    // LEMON TREE
    register(
      context, LEMON_TREE_PLACED, configuredFeatures.getOrThrow(AddonConfiguredFeatures.LEMON_TREE_KEY),
      VegetationPlacements.treePlacement(
        RarityFilter.onAverageOnceEvery(25),
        AddonBlocks.LEMON_SAPLING.get(),
      )
    )

  }

}