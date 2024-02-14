package com.dannbrown.discoverplanetzero.compat.vanilla

import com.dannbrown.databoxlib.registry.datagen.DataboxFlowerPots
import com.dannbrown.discoverplanetzero.init.AddonBlocks

object AddonFlowerPots: DataboxFlowerPots(mutableMapOf(
  AddonBlocks.SIMPLE_FLOWER to AddonBlocks.POTTED_SIMPLE_FLOWER,
  AddonBlocks.GUAYULE_SHRUB to AddonBlocks.POTTED_GUAYULE_SHRUB,
  AddonBlocks.JOSHUA_FAMILY.SAPLING!! to AddonBlocks.JOSHUA_FAMILY.POTTED_SAPLING!!
))
