package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import net.minecraft.world.level.levelgen.placement.RarityFilter
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.PlacementModifier

object ModPlacedFeatures {
  val LEMON_TREE_PLACED = REGISTRATE.placedFeature("lemon_tree_placed") { k, c, u ->
    u.register(
      c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.LEMON_TREE), VegetationPlacements.treePlacement(
        RarityFilter.onAverageOnceEvery(32),
        ModBlocks.LEMON_SAPLING.get(),
      )
    )
  }
  val COCONUT_PALM_TREE_PLACED = REGISTRATE.placedFeature("coconut_palm_tree_placed") { k, c, u ->
    u.register(
      c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.COCONUT_PALM_TREE), VegetationPlacements.treePlacement(
        RarityFilter.onAverageOnceEvery(12),
        ModBlocks.COCONUT_PALM_SAPLING.get(),
      )
    )
  }
  val ACAI_PALM_TREE_PLACED = REGISTRATE.placedFeature("acai_palm_tree_placed") { k, c, u ->
    u.register(
      c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.ACAI_PALM_TREE), VegetationPlacements.treePlacement(
        RarityFilter.onAverageOnceEvery(40),
        ModBlocks.ACAI_PALM_SAPLING.get(),
      )
    )
  }
  val PATCH_WILD_GARLIC_PLACED = REGISTRATE.placedFeature("patch_wild_garlic_placed") { k, c, u ->
    u.register(c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.PATCH_WILD_GARLIC), wildCropPlaced(82))
  }
  val PATCH_WILD_COLLARD_GREENS_PLACED = REGISTRATE.placedFeature("patch_wild_collard_greens_placed") { k, c, u ->
    u.register(c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.PATCH_WILD_COLLARD_GREENS), wildCropPlaced(120))
  }
  val PATCH_WILD_COFFEE_BERRIES_PLACED = REGISTRATE.placedFeature("patch_wild_coffee_berries_placed") { k, c, u ->
    u.register(c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.PATCH_WILD_COFFEE_BUSH), wildCropPlaced(90))
  }
  val PATCH_WILD_CASSAVA_PLACED = REGISTRATE.placedFeature("patch_wild_cassava_placed") { k, c, u ->
    u.register(c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.PATCH_WILD_CASSAVA), wildCropPlaced(122))
  }
  val PATCH_WILD_CORN_PLACED = REGISTRATE.placedFeature("patch_wild_corn_placed") { k, c, u ->
    u.register(c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.PATCH_WILD_CORN), wildCropPlaced(130))
  }
  val PATCH_WILD_GUARANA_PLACED = REGISTRATE.placedFeature("patch_wild_guarana_placed") { k, c, u ->
    u.register(c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.PATCH_WILD_GUARANA), wildCropPlaced(110))
  }
  val PATCH_WILD_BEANS_PLACED = REGISTRATE.placedFeature("patch_wild_beans_placed") { k, c, u ->
    u.register(c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.PATCH_WILD_BEANS), wildCropPlaced(100))
  }
  val PATCH_YERBA_MATE_PLACED = REGISTRATE.placedFeature("patch_yerba_mate_placed") { k, c, u ->
    u.register(c, k, u.lookupConfiguredFeature(c, ModConfiguredFeatures.PATCH_YERBA_MATE), wildCropPlaced(86))
  }

  private fun wildCropPlaced(chance: Int): List<PlacementModifier> {
    return listOf(
      RarityFilter.onAverageOnceEvery(chance),
      InSquarePlacement.spread(),
      PlacementUtils.HEIGHTMAP,
      BiomeFilter.biome()
    )
  }

  fun register() {
    // init
  }
}