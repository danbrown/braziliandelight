package com.dannbrown.braziliandelight.content.block

import com.dannbrown.databoxlib.content.block.SimplePlantBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.Mth
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import java.util.function.Supplier

class BuddingAcaiBlock(
  props: Properties,
  private val plantBlock: Supplier<out DoubleCropBlock>,
  private val seedItem: Supplier<out Item>
): BuddingDoubleCropBlock(props, plantBlock, seedItem) {

  companion object{
    val FACING = BlockStateProperties.HORIZONTAL_FACING

    fun canSurviveAcai(pState: BlockState, pLevel: LevelReader, pPos: BlockPos): Boolean {
      // or, if I'm the upper half, check if facing side are logs or leaves
      val facingBlockState = pLevel.getBlockState(pPos.relative(pState.getValue(FACING)))
      val checkState = { blockState: BlockState -> blockState.tags.anyMatch { it == BlockTags.LOGS || it == BlockTags.LEAVES } }
      return checkState.invoke(facingBlockState)
    }
  }

  init {
    registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(AGE, 0).setValue(WATERLOGGED, false))
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
    builder.add(FACING, WATERLOGGED, AGE)
  }

  override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? {
    var stateForPlacement = super.getStateForPlacement(pContext) ?: return null
    stateForPlacement = stateForPlacement.setValue(FACING, pContext.horizontalDirection)
    return stateForPlacement
  }




  override fun growTallPlant(serverLevel: ServerLevel, blockPos: BlockPos) {
    // if it cannot be grown as a double crop, return
    if(!DoubleAcaiBlock.canBeGrown(serverLevel, blockPos)) return

    serverLevel.setBlock(
      blockPos,
      plantBlock.get().defaultBlockState().setValue(SimplePlantBlock.HALF, DoubleBlockHalf.UPPER).setValue(FACING, serverLevel.getBlockState(blockPos).getValue(FACING)),
      3
    )
    serverLevel.setBlock(
      blockPos.below(),
      plantBlock.get().defaultBlockState().setValue(SimplePlantBlock.HALF, DoubleBlockHalf.LOWER).setValue(FACING, serverLevel.getBlockState(blockPos).getValue(FACING)),
      3
    )
  }

  override fun handleGrowing(blockState: BlockState, serverLevel: ServerLevel, blockPos: BlockPos, states: Int) {
    if(!canSurvive(blockState, serverLevel, blockPos)) {
      serverLevel.destroyBlock(blockPos, true)
      return
    }
    val newStateQty: Int = Mth.clamp(blockState.getValue(ageProperty) + states, 0, maxAge)
    // if the age is less than the max age, increment the age, otherwise grow the plant
    if (newStateQty < maxAge) {
      serverLevel.setBlock(blockPos, blockState.setValue(ageProperty, newStateQty), 3)
    }
    else {
      if (DoubleAcaiBlock.canBeGrown(serverLevel, blockPos)) {
        growTallPlant(serverLevel, blockPos)
      }
    }
  }



  override fun canSurvive(state: BlockState, worldIn: LevelReader, pos: BlockPos): Boolean {
    return canSurviveAcai(state, worldIn, pos)
  }
}