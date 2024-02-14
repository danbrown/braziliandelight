package com.dannbrown.discoverplanetzero.datagen.tags

import com.dannbrown.discover.init.DiscoverTags
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.compat.AddonModIntegrations
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.LushRedSandArchesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.MossySlatesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.RedSandArchesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.RoseateDesertBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.ScrapWastelandsBiome
import com.dannbrown.discoverplanetzero.init.AddonTags
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.BiomeTagsProvider
import net.minecraft.tags.BiomeTags
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class AddonBiomeTags (
  val output: PackOutput,
  val future: CompletableFuture<HolderLookup.Provider>,
  val existingFileHelper: ExistingFileHelper?
  ) : BiomeTagsProvider(output, future, AddonContent.MOD_ID, existingFileHelper) {
  override fun getName(): String {
    return "${AddonContent.NAME} Biome Tags"
  }

  override fun addTags(provider: HolderLookup.Provider) {
    // discover dimensions
    tag(AddonTags.BIOME.IS_PLANET_ZERO)
      .add(RedSandArchesBiome.BIOME_KEY)
      .add(LushRedSandArchesBiome.BIOME_KEY)
      .add(ScrapWastelandsBiome.BIOME_KEY)
      .add(RoseateDesertBiome.BIOME_KEY)
      .add(MossySlatesBiome.BIOME_KEY)

    tag(DiscoverTags.BIOME.IS_DISCOVER)
      .addTag(AddonTags.BIOME.IS_PLANET_ZERO)

    // discover biomes
    tag(AddonTags.BIOME.IS_RED_SAND_ARCHES)
      .add(RedSandArchesBiome.BIOME_KEY)
      .add(LushRedSandArchesBiome.BIOME_KEY)

    tag(AddonTags.BIOME.IS_MOSSY_SLATES)
      .add(MossySlatesBiome.BIOME_KEY)

    tag(AddonTags.BIOME.IS_SCRAP_WASTELANDS)
      .add(ScrapWastelandsBiome.BIOME_KEY)

    tag(AddonTags.BIOME.IS_ROSEATE_DESERT)
      .add(RoseateDesertBiome.BIOME_KEY)

    // structures
    tag(AddonTags.BIOME.HAS_RED_ARCHES)
      .add(RedSandArchesBiome.BIOME_KEY)
      .add(LushRedSandArchesBiome.BIOME_KEY)

    tag(AddonTags.BIOME.HAS_GRANITE_ARCHES)
      .add(RedSandArchesBiome.BIOME_KEY)

    tag(AddonTags.BIOME.HAS_LUSH_RED_ARCHES)
      .add(LushRedSandArchesBiome.BIOME_KEY)

    // features
    tag(DiscoverTags.BIOME.HAS_BERRY_BUSH_PATCHES)
      .add(MossySlatesBiome.BIOME_KEY)

    tag(DiscoverTags.BIOME.HAS_MOSS_CARPET_PATCHES)
      .add(MossySlatesBiome.BIOME_KEY)

    tag(DiscoverTags.BIOME.HAS_STONE_BLOBS)
      .add(MossySlatesBiome.BIOME_KEY)

    tag(DiscoverTags.BIOME.HAS_BASALT_BLOBS)
      .add(MossySlatesBiome.BIOME_KEY)


    // add mod compatibility tags
    AddonModIntegrations.registerBiomeTags(this::tag)
    // vanilla
    tag(BiomeTags.HAS_MINESHAFT_MESA)
      .add(RedSandArchesBiome.BIOME_KEY)
      .add(LushRedSandArchesBiome.BIOME_KEY)

  }
}
