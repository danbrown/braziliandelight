package com.dannbrown.discoverplanetzero.datagen.lang

import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.init.AddonCreativeTabs

object AddonLangGen {
  fun addStaticLangs(doRun: Boolean) {
    if (!doRun) return // avoid running in the server-side
    //
    // Creative tabs
    AddonContent.REGISTRATE.addCreativeTabLang(AddonCreativeTabs.CREATIVE_TAB_KEY, AddonContent.NAME)

  }

}