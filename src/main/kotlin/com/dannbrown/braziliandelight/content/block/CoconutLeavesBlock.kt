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
      .setValue(DISTANCE_9, MAX_DISTANCE)
      .setValue(PERSISTENT, true)
      .setValue(DISTANCE, 7)
      .setValue(WATERLOGGED, false))
  }

  override fun isRandomlyTicking(state: BlockState): Boolean {
    return state.getValue(DISTANCE_9) == MAX_DISTANCE && !state.getValue(PERSISTENT)
  }

  override fun randomTick(blockState: BlockState, serverLevel: ServerLevel, blockPos: BlockPos, randomSource: RandomSource) {
    if (this.decaying(blockState)) {
      dropResources(blockState, serverLevel, blockPos)
      serverLevel.removeBlock(blockPos, false)
    }
  }

  override fun decaying(pState: BlockState): Boolean {
    return !pState.getValue(PERSISTENT) && pState.getValue(DISTANCE_9) == MAX_DISTANCE
  }

  override fun tick(pState: BlockState, pLevel: ServerLevel, pPos: BlockPos, pRandom: RandomSource) {
    pLevel.setBlock(pPos, updateDistanceFromLogs(pState, pLevel, pPos), 3)
  }


  override fun updateShape(state: BlockState, direction: Direction, neighborState: BlockState, world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos): BlockState {
    if (state.getValue(WATERLOGGED)) {
      world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
    }

    val distance = getDistanceFromLog(neighborState) + 1
    if (distance != 1 || state.getValue(DISTANCE_9) != distance) {
      world.scheduleTick(pos, this, 1)
    }

    return state
  }

  override fun createBlockStateDefinition(stateBuilder: StateDefinition.Builder<Block?, BlockState?>) {
    super.createBlockStateDefinition(stateBuilder)
    stateBuilder.add(DISTANCE_9)
  }

  private fun updateDistanceFromLogs(state: BlockState, world: LevelAccessor, pos: BlockPos): BlockState {
    var distance = MAX_DISTANCE
    val mutablePos = MutableBlockPos()
    val directions: Array<Direction> = Direction.values()
    for (direction in directions) {
      mutablePos.setWithOffset(pos, direction)
      distance = min(distance.toDouble(), (getDistanceFromLog(world.getBlockState(mutablePos)) + 1).toDouble()).toInt()
      if (distance == 1) {
        break
      }
    }
    return state.setValue(DISTANCE_9, distance)
  }

  private fun getDistanceFromLog(state: BlockState): Int {
    return if (state.`is`(BlockTags.LOGS)) {
      0
    }
    else {
      if (state.block is CoconutLeavesBlock) state.getValue(DISTANCE_9) else MAX_DISTANCE
    }
  }

  override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState {
    val fluidState = pContext.level.getFluidState(pContext.clickedPos)
    val blockState = defaultBlockState().setValue(PERSISTENT, true).setValue(WATERLOGGED, fluidState.type === Fluids.WATER)
    return updateDistanceFromLogs(blockState, pContext.level, pContext.clickedPos)
  }

  companion object {
    val MAX_DISTANCE = 9
    val DISTANCE_9: IntegerProperty = IntegerProperty.create("distance_9", 1, MAX_DISTANCE)

  }

}