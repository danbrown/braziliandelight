package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.content.placerTypes.AcaiTreeDecorator
import com.dannbrown.braziliandelight.content.placerTypes.CoconutPalmFoliagePlacer
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE

object ModPlacerTypes {

  val COCONUT_PALM_FOLIAGE_PLACER =
    REGISTRATE.foliagePlacer("coconut_palm_foliage_placer") { CoconutPalmFoliagePlacer.CODEC }
  val ACAI_DECORATOR =
    REGISTRATE.treeDecorator("acaipalm_decorator") { AcaiTreeDecorator.CODEC }

  fun register() {
    // init
  }
}