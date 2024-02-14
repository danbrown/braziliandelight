package com.dannbrown.discoverplanetzero.init

import com.dannbrown.databoxlib.lib.LibTags
import com.dannbrown.discoverplanetzero.AddonContent

object AddonTags {
  object BLOCK {
  }
  object ITEM {
  }
  object BIOME {
    val IS_PLANET_ZERO = LibTags.modBiomeTag(AddonContent.MOD_ID, "is_planet_zero")
    val IS_RED_SAND_ARCHES = LibTags.modBiomeTag(AddonContent.MOD_ID, "is_red_sand_arches")
    val IS_MOSSY_SLATES = LibTags.modBiomeTag(AddonContent.MOD_ID, "is_mossy_slates")
    val IS_SCRAP_WASTELANDS = LibTags.modBiomeTag(AddonContent.MOD_ID, "is_scrap_wastelands")
    val IS_ROSEATE_DESERT = LibTags.modBiomeTag(AddonContent.MOD_ID, "is_roseate_desert")
    val HAS_RED_ARCHES = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_structure/red_arches")
    val HAS_LUSH_RED_ARCHES = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_structure/lush_red_arches")
    val HAS_GRANITE_ARCHES = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_structure/granite_arches")
  }
}