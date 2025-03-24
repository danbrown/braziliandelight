package com.dannbrown.braziliandelight.fabric

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.level.block.Block
import vectorwing.farmersdelight.common.registry.ModBlocks
import vectorwing.farmersdelight.common.registry.ModEffects

object FarmersCompatImpl {
  @JvmStatic
  fun getCookingPot(): Block {
    return ModBlocks.COOKING_POT.get()
  }

  @JvmStatic
  fun getNourishment(): MobEffect {
    return ModEffects.NOURISHMENT.get()
  }

  @JvmStatic
  fun getComfort(): MobEffect {
    return ModEffects.COMFORT.get()
  }
}