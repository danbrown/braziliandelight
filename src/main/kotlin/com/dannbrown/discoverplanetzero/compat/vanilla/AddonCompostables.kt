package com.dannbrown.discoverplanetzero.compat.vanilla

import com.dannbrown.databoxlib.registry.datagen.DataboxCompostables
import com.dannbrown.discoverplanetzero.init.AddonBlocks

object AddonCompostables: DataboxCompostables(mutableMapOf(
   AddonBlocks.JOSHUA_FAMILY.LEAVES!!.get().asItem() to 0.3f,
   AddonBlocks.JOSHUA_FAMILY.SAPLING!!.get().asItem() to 0.3f,
   AddonBlocks.GUAYULE_SHRUB.get().asItem() to 0.3f,
   AddonBlocks.AGAVE.get().asItem() to 0.3f,
   AddonBlocks.DEAD_GRASS.get().asItem() to 0.3f,
   AddonBlocks.SPARSE_DRY_GRASS.get().asItem() to 0.3f,
   AddonBlocks.TALL_SPARSE_DRY_GRASS.get().asItem() to 0.3f,
   AddonBlocks.OCOTILLO.get().asItem() to 0.3f,
   AddonBlocks.DRY_PATCHES.get().asItem() to 0.3f,
   AddonBlocks.DRY_SHRUB.get().asItem() to 0.3f
))


