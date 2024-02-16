package com.dannbrown.discovermars.datagen.tags

import com.dannbrown.discover.init.DiscoverTags
import com.dannbrown.discovermars.AddonContent
import com.dannbrown.discovermars.compat.AddonModIntegrations
import com.dannbrown.discovermars.init.AddonTags
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

    // add mod compatibility tags
    AddonModIntegrations.registerBiomeTags(this::tag)
  }
}
