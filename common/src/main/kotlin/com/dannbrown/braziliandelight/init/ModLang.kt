package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.content.blocks.PlaceableFoodBlock
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE

object ModLang {
  val LANGS = REGISTRATE.langs()
    .tooltip(ModNames.COFFEE_BEANS, "Work in progress")
    .tooltip(ModNames.COFFEE_BERRIES, "Work in progress")
    .tooltip(ModNames.WHITE_KERNELS, "Work in progress")
    .tooltip(ModNames.REPUGNANT_ARROW, "Nobody wants to be near this")
    .addRawLang(PlaceableFoodBlock.WRONG_ITEM_KEY, "You need a %s to eat this.")
    .addRawLang(ModContent.MOD_ID + ".tooltip." + ModNames.COCONUT_MILK, "Clears 1 Effect")


  fun register() {
    // init
  }
}