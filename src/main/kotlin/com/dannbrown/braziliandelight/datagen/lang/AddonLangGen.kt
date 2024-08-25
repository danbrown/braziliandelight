package com.dannbrown.braziliandelight.datagen.lang

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.braziliandelight.datagen.advancements.AddonAdvancementsProvider
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

    // Advancements
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.ROOT_ADVANCEMENT_KEY, "Brazilian Delight", "Welcome to Brazilian Delight!")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.TROPICAL_SEEDS_KEY, "Tropical Seeds", "Obtain any tropical seed or sapling from Brazilian Delight")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.MILK_POT_ADVANCEMENT_KEY, "The Milky Way", "Place milk in a cooking pot, and now try to mix it!")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.HEAVY_CREAM_POT_ADVANCEMENT_KEY, "And it condenses", "Obtain a Heavy Cream Bucket from mixing milk")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.CHEESE_MAKING_KEY, "Cheese Making", "Mix Salt and Lemon with Heavy Cream in a cooking pot to make cheese!")

  }

}