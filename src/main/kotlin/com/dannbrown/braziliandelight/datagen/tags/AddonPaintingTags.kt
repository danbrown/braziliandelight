package com.dannbrown.braziliandelight.datagen.tags

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.compat.AddonModIntegrations
import com.dannbrown.braziliandelight.init.AddonPaintings
import com.dannbrown.braziliandelight.init.AddonTags
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.BiomeTagsProvider
import net.minecraft.data.tags.PaintingVariantTagsProvider
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.PaintingVariantTags
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class AddonPaintingTags (
  val output: PackOutput,
  val future: CompletableFuture<HolderLookup.Provider>,
  val existingFileHelper: ExistingFileHelper?
  ) : PaintingVariantTagsProvider(output, future, AddonContent.MOD_ID, existingFileHelper) {
  override fun getName(): String {
    return "${AddonContent.NAME} Painting Variant Tags"
  }

  override fun addTags(provider: HolderLookup.Provider) {
    tag(PaintingVariantTags.PLACEABLE)
  }
}
