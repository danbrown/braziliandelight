package com.dannbrown.braziliandelight.datagen.tags

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.compat.AddonModIntegrations
import com.dannbrown.braziliandelight.init.AddonTags
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

    // LEMON TREE
    tag(AddonTags.BIOME.HAS_LEMON_TREE)
      .addTags(BiomeTags.IS_FOREST)

    // COCONUT PALM TREE
    tag(AddonTags.BIOME.HAS_COCONUT_PALM_TREE)
      .addTags(BiomeTags.IS_BEACH)
      .addTags(BiomeTags.IS_JUNGLE)
      .addTags(BiomeTags.IS_BADLANDS)

    // WILD CROPS
    tag(AddonTags.BIOME.HAS_WILD_GARLIC)
      .addTags(BiomeTags.IS_HILL, BiomeTags.IS_FOREST, BiomeTags.IS_SAVANNA)

    tag(AddonTags.BIOME.HAS_WILD_COLLARD_GREENS)
      .addTags(BiomeTags.IS_TAIGA)

    tag(AddonTags.BIOME.HAS_WILD_COFFEE_BERRIES)
      .addTags(BiomeTags.IS_JUNGLE, BiomeTags.IS_TAIGA)

    tag(AddonTags.BIOME.HAS_WILD_CASSAVA)
      .addTags(BiomeTags.IS_JUNGLE, BiomeTags.IS_SAVANNA)

    tag(AddonTags.BIOME.HAS_WILD_CORN)
      .addTags(BiomeTags.IS_HILL, BiomeTags.IS_FOREST)

    tag(AddonTags.BIOME.HAS_WILD_GUARANA)
      .addTags(BiomeTags.IS_JUNGLE)

    tag(AddonTags.BIOME.HAS_WILD_BEANS)
      .addTags(BiomeTags.IS_FOREST, BiomeTags.IS_MOUNTAIN)


    // add mod compatibility tags
    AddonModIntegrations.registerBiomeTags(this::tag)
  }
}
