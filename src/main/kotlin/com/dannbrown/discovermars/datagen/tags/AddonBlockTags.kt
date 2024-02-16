package com.dannbrown.discovermars.datagen.tags

import com.dannbrown.discover.ProjectContent
import com.dannbrown.discovermars.AddonContent
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.IntrinsicHolderTagsProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Block
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture
import java.util.function.Function


class AddonBlockTags(
  output: PackOutput,
  future: CompletableFuture<HolderLookup.Provider>,
  existingFileHelper: ExistingFileHelper
) : IntrinsicHolderTagsProvider<Block>(
  output,
  Registries.BLOCK,
  future,
  Function<Block, ResourceKey<Block>> { block: Block ->
    block.builtInRegistryHolder()
      .key()
  }, AddonContent.MOD_ID,
  existingFileHelper
) {
  override fun getName(): String {
    return "${AddonContent.NAME} Block Tags"
  }

  override fun addTags(provider: HolderLookup.Provider) {
  }
}