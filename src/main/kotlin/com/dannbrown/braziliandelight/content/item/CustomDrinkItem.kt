package com.dannbrown.braziliandelight.content.item

import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.UseAnim

class CustomDrinkItem(props: Properties, private var hasFoodEffectTooltip: Boolean = false, private var hasCustomTooltip: Boolean = false): CustomFoodItem(props, hasFoodEffectTooltip, hasCustomTooltip) {
  override fun getUseAnimation(pStack: ItemStack?): UseAnim {
    return UseAnim.DRINK
  }

  override fun getDrinkingSound(): SoundEvent {
    return SoundEvents.GENERIC_DRINK
  }

  override fun getEatingSound(): SoundEvent {
    return SoundEvents.GENERIC_DRINK
  }
}