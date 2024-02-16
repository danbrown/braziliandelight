package com.dannbrown.discovermars.datagen.lang

import com.dannbrown.discovermars.AddonContent
import com.dannbrown.discovermars.init.AddonCreativeTabs

object AddonLangGen {
  fun addStaticLangs(doRun: Boolean) {
    if (!doRun) return // avoid running in the server-side
    //
    // Creative tabs
    AddonContent.REGISTRATE.addCreativeTabLang(AddonCreativeTabs.CREATIVE_TAB_KEY, AddonContent.NAME)

  }

}