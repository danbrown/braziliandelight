package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.content.effect.RepugnantEffect
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE

object ModEffects {
  val REPUGNANT = REGISTRATE.mobEffect("repugnant") { RepugnantEffect() }
  fun register() {
    REGISTRATE.buildEffects()
  }
}