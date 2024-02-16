package com.dannbrown.discovermars.datagen.tags

import com.dannbrown.discovermars.AddonContent
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.FluidTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture


class AddonFluidTags(
  output: PackOutput,
  future: CompletableFuture<HolderLookup.Provider>,
  fileHelper: ExistingFileHelper
) :
  FluidTagsProvider(output, future, AddonContent.MOD_ID, fileHelper) {
  override fun getName(): String {
    return "${AddonContent.NAME} Fluid Tags"
  }

  override fun addTags(provider: HolderLookup.Provider) {
  }
}