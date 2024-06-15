package com.dannbrown.braziliandelight.compat.vanilla

import com.dannbrown.deltaboxlib.registry.datagen.DeltaboxFlowerPots
import com.dannbrown.braziliandelight.init.AddonBlocks

object AddonFlowerPots: DeltaboxFlowerPots(mutableMapOf(
  AddonBlocks.LEMON_SAPLING to AddonBlocks.POTTED_LEMON_SAPLING,
  AddonBlocks.COCONUT_PALM_SAPLING to AddonBlocks.POTTED_COCONUT_PALM_SAPLING,
  AddonBlocks.ACAI_PALM_SAPLING to AddonBlocks.POTTED_ACAI_PALM_SAPLING
))
