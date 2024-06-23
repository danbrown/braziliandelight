package com.dannbrown.braziliandelight.datagen.lang

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.braziliandelight.init.AddonCreativeTabs
import com.dannbrown.braziliandelight.init.AddonEffects
import com.dannbrown.braziliandelight.lib.AddonNames

object AddonLangGen {
  fun addStaticLangs(doRun: Boolean) {
    if (!doRun) return // avoid running in the server-side
    //
    // Creative tabs
    AddonContent.REGISTRATE.addCreativeTabLang(AddonCreativeTabs.CREATIVE_TAB_KEY, AddonContent.NAME)

    // Items
    AddonContent.REGISTRATE.addItemTooltipLang("carrot_cake", "§6§oFixed");
    AddonContent.REGISTRATE.addRawLang(PlaceableFoodBlock.WRONG_ITEM_KEY, "You need a %s to eat this.")

    // Potions
    AddonContent.REGISTRATE.addEffectLang(AddonNames.REPULSIVE, "Repulsive")
    AddonContent.REGISTRATE.addPotionLang(AddonNames.REPULSIVE, "Repulsive")
    AddonContent.REGISTRATE.addPotionLang(AddonNames.STRONG_REPULSIVE, "Repulsive II")
    AddonContent.REGISTRATE.addPotionLang(AddonNames.STRONGER_REPULSIVE, "Repulsive III")
  }

}