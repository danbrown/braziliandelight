package com.dannbrown.discoverplanetzero.datagen.tags

import com.dannbrown.discoverplanetzero.AddonContent
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.EntityTypeTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture


class AddonEntityTypeTags(
  val output: PackOutput,
  val future: CompletableFuture<HolderLookup.Provider>,
  val existingFileHelper: ExistingFileHelper?
) : EntityTypeTagsProvider(output, future, AddonContent.MOD_ID, existingFileHelper) {
  override fun getName(): String {
    return "${AddonContent.NAME} Entity Type Tags"
  }

  override fun addTags(provider: HolderLookup.Provider) {
  }
}
