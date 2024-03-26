package com.dannbrown.braziliandelight.datagen.lang

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.PuddingBlock
import com.dannbrown.braziliandelight.init.AddonCreativeTabs

object AddonLangGen {
  fun addStaticLangs(doRun: Boolean) {
    if (!doRun) return // avoid running in the server-side
    //
    // Creative tabs
    AddonContent.REGISTRATE.addCreativeTabLang(AddonCreativeTabs.CREATIVE_TAB_KEY, AddonContent.NAME)

    // Items
    AddonContent.REGISTRATE.addRawLang(PuddingBlock.WRONG_ITEM_KEY, "You need a %s to eat this.")
  }

}