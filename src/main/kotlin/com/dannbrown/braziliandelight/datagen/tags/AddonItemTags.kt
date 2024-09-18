package com.dannbrown.braziliandelight.datagen.tags

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonTags
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.TagEntry
import net.minecraft.world.level.block.Block
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class AddonItemTags(
  output: PackOutput,
  future: CompletableFuture<HolderLookup.Provider>,
  provider: CompletableFuture<TagLookup<Block>>,
  fileHelper: ExistingFileHelper
) :
  ItemTagsProvider(output, future, provider, AddonContent.MOD_ID, fileHelper) {
  override fun getName(): String {
    return "${AddonContent.NAME} Item Tags"
  }
  override fun addTags(provider: HolderLookup.Provider) {
    tag(AddonTags.ITEM.CHEESE_COAGULANT)
      .add(TagEntry.tag(AddonTags.ITEM.LEMON.location))
  }
}