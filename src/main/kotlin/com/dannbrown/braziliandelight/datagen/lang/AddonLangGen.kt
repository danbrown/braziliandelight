package com.dannbrown.braziliandelight.datagen.lang

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.braziliandelight.init.AddonCreativeTabs
import com.dannbrown.braziliandelight.lib.AddonNames
import vectorwing.farmersdelight.FarmersDelight

object AddonLangGen {
  fun addStaticLangs(doRun: Boolean) {
    if (!doRun) return // avoid running in the server-side
    //
    // Creative tabs
    AddonContent.REGISTRATE.addCreativeTabLang(AddonCreativeTabs.CREATIVE_TAB_KEY, AddonContent.NAME)

    // Items
    AddonContent.REGISTRATE.addItemTooltipLang("carrot_cake", "§6§oFixed");
    AddonContent.REGISTRATE.addItemTooltipLang("repugnant_arrow", "Nobody wants to be near this")
    AddonContent.REGISTRATE.addRawLang(PlaceableFoodBlock.WRONG_ITEM_KEY, "You need a %s to eat this.")
    AddonContent.REGISTRATE.addRawLang(FarmersDelight.MODID + ".tooltip." + AddonNames.COCONUT_MILK, "Clears 1 Effect")

    // Potions
    AddonContent.REGISTRATE.addEffectLang(AddonNames.REPUGNANT, "Repugnant")
    AddonContent.REGISTRATE.addPotionLang(AddonNames.REPUGNANT, "Repugnant")
    AddonContent.REGISTRATE.addPotionLang(AddonNames.STRONG_REPUGNANT, "Repugnant II")
    AddonContent.REGISTRATE.addPotionLang(AddonNames.STRONGER_REPUGNANT, "Repugnant III")
  }

}