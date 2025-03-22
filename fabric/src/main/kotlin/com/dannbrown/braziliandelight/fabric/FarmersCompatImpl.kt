package com.dannbrown.braziliandelight.fabric

import com.nhoryzon.mc.farmersdelight.registry.BlocksRegistry
import com.nhoryzon.mc.farmersdelight.registry.EffectsRegistry
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.level.block.Block

object FarmersCompatImpl {
  @JvmStatic
  fun getCookingPot(): Block {
    return BlocksRegistry.COOKING_POT.get()
  }

  @JvmStatic
  fun getNourishment(): MobEffect {
    return EffectsRegistry.NOURISHMENT.get()
  }

  @JvmStatic
  fun getComfort(): MobEffect {
    return EffectsRegistry.COMFORT.get()
  }
}