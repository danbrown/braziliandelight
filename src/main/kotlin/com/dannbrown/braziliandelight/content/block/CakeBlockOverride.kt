package com.dannbrown.braziliandelight.content.block

import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.tags.ItemTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CakeBlock
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.CandleCakeBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.BlockHitResult
import vectorwing.farmersdelight.common.tag.ModTags
import vectorwing.farmersdelight.common.utility.ItemUtils
import java.util.function.Supplier

class CakeBlockOverride(p: Properties, private val sliceItem: Supplier<Item>, private val candleCakes: List<Triple<String, CandleBlock, BlockEntry<CandleCakeBlockOverride>>>): CakeBlock(p) {
  override fun use(pState: BlockState, pLevel: Level, pPos: BlockPos, pPlayer: Player, pHand: InteractionHand, pHit: BlockHitResult): InteractionResult {
    val heldStack: ItemStack = pPlayer.getItemInHand(pHand)

    // Knives interact with the cake
    if (heldStack.`is`(ModTags.KNIVES))
      return cutSlice(pLevel, pPos, pState, pPlayer)

    // Candles interact with the cake
    if (heldStack.`is`(ItemTags.CANDLES) && pState.getValue(BITES) == 0 && byItem(heldStack.item) is CandleBlock)
      return addCandle(pLevel, pPos,  pPlayer, pHand, pHit)

    return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit)
  }



  private fun cutSlice(level: Level, pos: BlockPos, state: BlockState, player: Player): InteractionResult {
    val bites = state.getValue(BITES)
    if (bites < MAX_BITES) {
      level.setBlock(pos, state.setValue(BITES, bites + 1), 3)
    }
    else {
      level.removeBlock(pos, false)
    }
    val direction: Direction = player.direction.opposite
    ItemUtils.spawnItemEntity(level, this.getSliceItem(),
      pos.x + 0.5, pos.y + 0.3, pos.z + 0.5,
direction.stepX * 0.15, 0.05, direction.stepZ * 0.15
    )
    level.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8f, 0.8f)
    return InteractionResult.SUCCESS
  }


  private fun getSliceItem(): ItemStack {
    return ItemStack(this.sliceItem.get())
  }


  private fun addCandle(pLevel: Level, pPos: BlockPos, pPlayer: Player, pHand: InteractionHand, pHit: BlockHitResult): InteractionResult {
    val heldStack: ItemStack = pPlayer.getItemInHand(pHand)
    val block = byItem(heldStack.item)
    if (block is CandleBlock) {
      if (!pPlayer.isCreative) heldStack.shrink(1)

      val candleCakeData = getDataByCandle(block) ?: return InteractionResult.PASS

      pLevel.playSound(null, pPos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0f, 1.0f)
      pLevel.setBlockAndUpdate(pPos, candleCakeData.third.get().defaultBlockState())
      pLevel.gameEvent(pPlayer, GameEvent.BLOCK_CHANGE, pPos)
      pPlayer.awardStat(Stats.ITEM_USED[heldStack.item])
      return InteractionResult.SUCCESS
    }
    return InteractionResult.PASS
  }

  private fun getDataByCandle(candleItem: Block): Triple<String, CandleBlock, BlockEntry<CandleCakeBlockOverride>>? {
    var found: Triple<String, CandleBlock, BlockEntry<CandleCakeBlockOverride>>? = null
    for (entry in candleCakes){
      if (entry.second == candleItem){
        found = entry
      }
    }
    return found
  }
}