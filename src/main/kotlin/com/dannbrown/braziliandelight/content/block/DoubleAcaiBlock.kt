package com.dannbrown.braziliandelight.content.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.material.Fluids
import java.util.function.Supplier

open class DoubleAcaiBlock(
  props: Properties,
  private val isBush: Boolean = false,
  private val dropItem: Supplier<Item>,
  private val seedItem: Supplier<Item>? = null,
  private val chance: Float = 1f,
  private val multiplier: Int = 1
): DoubleCropBlock(props, isBush, dropItem, seedItem, chance, multiplier) {
  companion object{
    val FACING = BlockStateProperties.HORIZONTAL_FACING

    fun canBeGrown(serverLevel: ServerLevel, blockPos: BlockPos): Boolean {
      return (serverLevel.getBlockState(blockPos.below()).isAir || serverLevel.getBlockState(blockPos.below()).canBeReplaced())
    }
  }

  init {
    registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(AGE, 0).setValue(WATERLOGGED, false).setValue(HALF, DoubleBlockHalf.LOWER))
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
    builder.add(FACING, WATERLOGGED, AGE, HALF)
  }

  override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? {
    var stateForPlacement = super.getStateForPlacement(pContext) ?: return null
    stateForPlacement = stateForPlacement.setValue(FACING, pContext.horizontalDirection)
    return stateForPlacement
  }

  private fun canSurviveParts(pState: BlockState, pLevel: LevelReader, pPos: BlockPos): Boolean {
    return if (pState.getValue(HALF) == DoubleBlockHalf.LOWER) {
      // if I'm the lower half, check if the block above is the same block
      pLevel.getBlockState(pPos.above()).`is`(this)
    }
    else {
     return BuddingAcaiBlock.canSurviveAcai(pState, pLevel, pPos)
    }
  }

  override fun canSurviveDouble(pState: BlockState, pLevel: LevelReader, pPos: BlockPos): Boolean {
    if (pState.getValue(HALF) == DoubleBlockHalf.LOWER) {
      return canSurviveParts(pState, pLevel, pPos)
    }
    else {
      val blockState = pLevel.getBlockState(pPos.below())
      return if (pState.block !== this) {
        canSurviveParts(pState, pLevel, pPos)
      }
      else {
        blockState.`is`(this) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER
      }
    }
  }


}