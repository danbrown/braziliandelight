package com.dannbrown.braziliandelight.compat.vanilla

import com.dannbrown.databoxlib.registry.datagen.DataboxFlowerPots
import com.dannbrown.braziliandelight.init.AddonBlocks

object AddonFlowerPots: DataboxFlowerPots(mutableMapOf(
  AddonBlocks.LEMON_SAPLING to AddonBlocks.POTTED_LEMON_SAPLING,
  AddonBlocks.COCONUT_PALM_SAPLING to AddonBlocks.POTTED_COCONUT_PALM_SAPLING,
  AddonBlocks.ACAI_PALM_SAPLING to AddonBlocks.POTTED_ACAI_PALM_SAPLING
))
