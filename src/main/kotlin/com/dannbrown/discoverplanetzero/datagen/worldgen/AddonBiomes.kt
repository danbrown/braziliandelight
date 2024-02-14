package com.dannbrown.discoverplanetzero.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.LushRedSandArchesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.MossySlatesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.RedSandArchesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.RoseateDesertBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.ScrapWastelandsBiome
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.world.level.biome.Biome

object AddonBiomes {
  val BIOMES: MutableList<AbstractBiome> = ArrayList()

  init {
    // Planet Zero
    BIOMES.add(RedSandArchesBiome)
    BIOMES.add(LushRedSandArchesBiome)
    BIOMES.add(ScrapWastelandsBiome)
    BIOMES.add(RoseateDesertBiome)
    BIOMES.add(MossySlatesBiome)
  }

  fun bootstrap(context: BootstapContext<Biome>) {
    for (biome in BIOMES) {
      biome.bootstrapBiome(context)
    }
  }
}