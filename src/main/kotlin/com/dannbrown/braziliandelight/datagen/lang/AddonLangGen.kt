package com.dannbrown.braziliandelight.datagen.lang

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.braziliandelight.content.item.CustomFoodItem
import com.dannbrown.braziliandelight.content.item.PlaceableBlockItem
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonCreativeTabs

object AddonLangGen {
  fun addStaticLangs(doRun: Boolean) {
    if (!doRun) return // avoid running in the server-side
    //
    // Creative tabs
    AddonContent.REGISTRATE.addCreativeTabLang(AddonCreativeTabs.CREATIVE_TAB_KEY, AddonContent.NAME)

    // Items
    AddonContent.REGISTRATE.addRawLang(PlaceableBlockItem.PLACEABLE_KEY, "Placeable")
    AddonContent.REGISTRATE.addRawLang(PlaceableFoodBlock.WRONG_ITEM_KEY, "You need a %s to eat this.")
  }

}