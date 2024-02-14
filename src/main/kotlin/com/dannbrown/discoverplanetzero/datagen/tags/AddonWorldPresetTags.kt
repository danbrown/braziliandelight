package com.dannbrown.discoverplanetzero.datagen.tags

import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonWorldPresets
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.WorldPresetTagsProvider
import net.minecraft.tags.TagKey
import net.minecraft.tags.WorldPresetTags
import net.minecraft.world.level.levelgen.presets.WorldPreset
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture


class AddonWorldPresetTags(
  output: PackOutput,
  future: CompletableFuture<HolderLookup.Provider>,
  fileHelper: ExistingFileHelper
) :
  WorldPresetTagsProvider(output, future, AddonContent.MOD_ID, fileHelper) {
  override fun getName(): String {
    return "${AddonContent.NAME} World Presets Tags"
  }

  override public fun tag(tagKey: TagKey<WorldPreset>): TagAppender<WorldPreset> {
    return super.tag(tagKey)
  }

  override fun addTags(provider: HolderLookup.Provider) {
    // BEWARE: It can replace tags created by the REGISTRATE system
    tag(WorldPresetTags.NORMAL).add(AddonWorldPresets.DISCOVER_WORLD_PRESET)
  }
}