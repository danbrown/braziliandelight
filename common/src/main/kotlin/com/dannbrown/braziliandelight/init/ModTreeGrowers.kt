package com.dannbrown.braziliandelight.init

import com.dannbrown.deltaboxlib.content.worldgen.tree.DeltaboxTreeGrower
import java.util.*

object ModTreeGrowers {
  val LEMON_GROWER: DeltaboxTreeGrower = DeltaboxTreeGrower(
    "lemon_tree",
    Optional.empty(),
    Optional.of(ModConfiguredFeatures.LEMON_TREE),
    Optional.empty(),
  )
  val COCONUT_PALM_GROWER: DeltaboxTreeGrower = DeltaboxTreeGrower(
    "coconut_palm_tree",
    Optional.empty(),
    Optional.of(ModConfiguredFeatures.COCONUT_PALM_TREE),
    Optional.empty(),
  )
  val ACAI_PALM_GROWER: DeltaboxTreeGrower = DeltaboxTreeGrower(
    "acai_palm_tree",
    Optional.empty(),
    Optional.of(ModConfiguredFeatures.ACAI_PALM_TREE),
    Optional.empty(),
  )

  fun register() {
    // init
  }
}