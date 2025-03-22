package com.dannbrown.braziliandelight.content.items

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class MilkBottleItem(props: Properties) : CustomDrinkItem(props) {
  override fun affectConsumer(stack: ItemStack, world: Level, user: LivingEntity) {
    val activeStatusEffectList = user.activeEffectsMap.keys
    if (activeStatusEffectList.isNotEmpty()) {
      activeStatusEffectList
        .random()
        ?.let(user::removeEffect)
    }
  }

  override fun use(world: Level, user: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
    user.startUsingItem(hand)
    return InteractionResultHolder.consume(user.getItemInHand(hand))
  }
}