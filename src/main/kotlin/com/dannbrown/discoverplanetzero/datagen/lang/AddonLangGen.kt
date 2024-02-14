package com.dannbrown.discoverplanetzero.datagen.lang

import com.dannbrown.discover.ProjectContent
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.LushRedSandArchesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.MossySlatesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.RedSandArchesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.RoseateDesertBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.ScrapWastelandsBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.dimension.PlanetZeroDimension
import com.dannbrown.discoverplanetzero.init.AddonCreativeTabs

object AddonLangGen {
  fun addStaticLangs(doRun: Boolean) {
    if (!doRun) return // avoid running in the server-side
    //
    // Creative tabs
    AddonContent.REGISTRATE.addCreativeTabLang(AddonCreativeTabs.CREATIVE_TAB_KEY, AddonContent.NAME)
    // Biomes
    AddonContent.REGISTRATE.addBiomeLang(RoseateDesertBiome.biomeId, "Roseate Desert")
    AddonContent.REGISTRATE.addBiomeLang(ScrapWastelandsBiome.biomeId, "Scrap Wastelands")
    AddonContent.REGISTRATE.addBiomeLang(RedSandArchesBiome.biomeId, "Red Sand Arches")
    AddonContent.REGISTRATE.addBiomeLang(LushRedSandArchesBiome.biomeId, "Lush Red Sand Arches")
    AddonContent.REGISTRATE.addBiomeLang(MossySlatesBiome.biomeId, "Mossy Slates")
    // Dimension
    AddonContent.REGISTRATE.addDimensionLang(PlanetZeroDimension.dimensionId, "Planet Zero")
    // World Presets
    AddonContent.REGISTRATE.addWorldPresetLang(PlanetZeroDimension.dimensionId, "Planet Zero")
  }

}