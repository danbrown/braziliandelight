package com.dannbrown.braziliandelight.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractBiome
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.world.level.biome.Biome

object AddonBiomes {
  val BIOMES: MutableList<AbstractBiome> = ArrayList()

  init {
  }

  fun bootstrap(context: BootstapContext<Biome>) {
    for (biome in BIOMES) {
      biome.bootstrapBiome(context)
    }
  }
}