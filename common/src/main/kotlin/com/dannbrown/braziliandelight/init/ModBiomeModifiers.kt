package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import net.minecraft.world.level.levelgen.GenerationStep

object ModBiomeModifiers {
  val ADD_LEMON_TREE = REGISTRATE.biomeModifier(
    "add_lemon_tree",
    ModTags.BIOME.HAS_LEMON_TREE,
    ModPlacedFeatures.LEMON_TREE_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )
  val ADD_COCONUT_PALM_TREE = REGISTRATE.biomeModifier(
    "add_coconut_palm_tree",
    ModTags.BIOME.HAS_COCONUT_PALM_TREE,
    ModPlacedFeatures.COCONUT_PALM_TREE_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )
  val ADD_ACAI_PALM_TREE = REGISTRATE.biomeModifier(
    "add_acai_palm_tree",
    ModTags.BIOME.HAS_ACAI_PALM_TREE,
    ModPlacedFeatures.ACAI_PALM_TREE_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )
  val ADD_WILD_GARLIC = REGISTRATE.biomeModifier(
    "add_wild_garlic",
    ModTags.BIOME.HAS_WILD_GARLIC,
    ModPlacedFeatures.PATCH_WILD_GARLIC_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )
  val ADD_WILD_COLLARD_GREENS = REGISTRATE.biomeModifier(
    "add_wild_collard_greens",
    ModTags.BIOME.HAS_WILD_COLLARD_GREENS,
    ModPlacedFeatures.PATCH_WILD_COLLARD_GREENS_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )
  val ADD_WILD_COFFEE_BERRIES = REGISTRATE.biomeModifier(
    "add_wild_coffee_berries",
    ModTags.BIOME.HAS_WILD_COFFEE_BERRIES,
    ModPlacedFeatures.PATCH_WILD_COFFEE_BERRIES_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )
  val ADD_WILD_CASSAVA = REGISTRATE.biomeModifier(
    "add_wild_cassava",
    ModTags.BIOME.HAS_WILD_CASSAVA,
    ModPlacedFeatures.PATCH_WILD_CASSAVA_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )
  val ADD_WILD_CORN = REGISTRATE.biomeModifier(
    "add_wild_corn",
    ModTags.BIOME.HAS_WILD_CORN,
    ModPlacedFeatures.PATCH_WILD_CORN_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )
  val ADD_WILD_GUARANA = REGISTRATE.biomeModifier(
    "add_wild_guarana",
    ModTags.BIOME.HAS_WILD_GUARANA,
    ModPlacedFeatures.PATCH_WILD_GUARANA_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )
  val ADD_WILD_BEANS = REGISTRATE.biomeModifier(
    "add_wild_beans",
    ModTags.BIOME.HAS_WILD_BEANS,
    ModPlacedFeatures.PATCH_WILD_BEANS_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )
  val ADD_YERBA_MATE = REGISTRATE.biomeModifier(
    "add_yerba_mate",
    ModTags.BIOME.HAS_YERBA_MATE,
    ModPlacedFeatures.PATCH_YERBA_MATE_PLACED,
    GenerationStep.Decoration.VEGETAL_DECORATION
  )

  fun register() {
    // init
  }
}