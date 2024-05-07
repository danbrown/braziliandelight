package com.dannbrown.braziliandelight.content.item

import com.dannbrown.braziliandelight.content.entity.CoconutProjectileEntity
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block

class CoconutItem(block: Block, props: Properties) : BlockItem(block, props) {
  override fun use(pLevel: Level, pPlayer: Player, pHand: InteractionHand): InteractionResultHolder<ItemStack> {
    val itemStack: ItemStack = pPlayer.getItemInHand(pHand)
    pLevel.playSound(pPlayer, pPlayer.x, pPlayer.y, pPlayer.z,
      SoundEvents.EGG_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (pLevel.getRandom().nextFloat() * 0.4f + 0.8f)
    )
    if (!pLevel.isClientSide) {
      val coconutEntity = CoconutProjectileEntity(pLevel, pPlayer)
      coconutEntity.item = itemStack
      coconutEntity.shootFromRotation(pPlayer, pPlayer.xRot, pPlayer.yRot, 0.0f, 1.5f, 1.0f)
      pLevel.addFreshEntity(coconutEntity)
    }
    pPlayer.awardStat(Stats.ITEM_USED.get(this))
    if (!pPlayer.abilities.instabuild) {
      itemStack.shrink(1)
    }
    return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide())
  }
}