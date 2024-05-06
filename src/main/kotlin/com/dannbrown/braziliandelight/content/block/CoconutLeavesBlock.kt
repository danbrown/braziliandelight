package com.dannbrown.braziliandelight.content.block

import com.dannbrown.databoxlib.content.block.FlammableLeavesBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.Fluids
import kotlin.math.min

class CoconutLeavesBlock(props: Properties, flammability: Int = 60, fireSpread: Int = 30) : FlammableLeavesBlock(props, flammability, fireSpread) {
  init {
    this.registerDefaultState(stateDefinition.any()
      .setValue(DISTANCE_9, 9)
      .setValue(PERSISTENT, false)
      .setValue(DISTANCE, 7)
      .setValue(WATERLOGGED, false))
  }

  override fun isRandomlyTicking(state: BlockState): Boolean {
    return state.getValue(DISTANCE_9) == 9 && !state.getValue(PERSISTENT)
  }

  override fun randomTick(blockState: BlockState, serverLevel: ServerLevel, blockPos: BlockPos, randomSource: RandomSource) {
    if (this.decaying(blockState)) {
      dropResources(blockState, serverLevel, blockPos)
      serverLevel.removeBlock(blockPos, false)
    }
  }

  override fun decaying(pState: BlockState): Boolean {
    return !pState.getValue(PERSISTENT) && pState.getValue(DISTANCE_9) == 9
  }

  override fun tick(pState: BlockState, pLevel: ServerLevel, pPos: BlockPos, pRandom: RandomSource) {
    pLevel.setBlock(pPos, updateDistance(pState, pLevel, pPos), 3)
  }

  override fun updateShape(pState: BlockState, pFacing: Direction, pFacingState: BlockState, pLevel: LevelAccessor, pCurrentPos: BlockPos, pFacingPos: BlockPos): BlockState {
    if (pState.getValue<Boolean>(WATERLOGGED)) {
      pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel))
    }
    val i = getDistanceAt(pFacingState) + 1
    if (i != 1 || pState.getValue(DISTANCE_9) != i) {
      pLevel.scheduleTick(pCurrentPos, this, 1)
    }

    return pState
  }

  override fun createBlockStateDefinition(stateBuilder: StateDefinition.Builder<Block?, BlockState?>) {
    super.createBlockStateDefinition(stateBuilder)
    stateBuilder.add(DISTANCE_9)
  }

  override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState {
    val fluidstate = pContext.level.getFluidState(pContext.clickedPos)
    val blockstate = defaultBlockState().setValue<Boolean, Boolean>(PERSISTENT, true)
      .setValue<Boolean, Boolean>(WATERLOGGED, fluidstate.type === Fluids.WATER)
    return updateDistance(blockstate, pContext.level, pContext.clickedPos)
  }

  companion object {
    val DISTANCE_9: IntegerProperty = IntegerProperty.create("distance_9", 1, 9)
    private fun updateDistance(pState: BlockState, pLevel: LevelAccessor, pPos: BlockPos): BlockState {
      var i = 9
      val mutableBlockPos = MutableBlockPos()

      for (direction in Direction.values()) {
        mutableBlockPos.setWithOffset(pPos, direction)
        i = min(i.toDouble(), (getDistanceAt(pLevel.getBlockState(mutableBlockPos)) + 1).toDouble())
          .toInt()
        if (i == 1) {
          break
        }
      }

      return pState.setValue(DISTANCE_9, i)
    }

    private fun getDistanceAt(pNeighbor: BlockState): Int {
      return if (pNeighbor.`is`(BlockTags.LOGS)) {
        0
      }
      else {
        if (pNeighbor.block is CoconutLeavesBlock) pNeighbor.getValue(DISTANCE_9) else 9
      }
    }
  }
}