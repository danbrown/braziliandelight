package com.dannbrown.braziliandelight.content.block

import com.dannbrown.deltaboxlib.content.block.SimplePlantBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.Mth
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Supplier

class BuddingAcaiBlock(
  props: Properties,
  private val plantBlock: Supplier<out DoubleCropBlock>,
): BuddingDoubleCropBlock(props, plantBlock) {

  companion object{
    val FACING = BlockStateProperties.HORIZONTAL_FACING

    protected val WEST_AABB = box(0.0, 8.0, 4.0, 8.0, 16.0, 12.0)
    protected val EAST_AABB = box(8.0, 8.0, 4.0, 16.0, 16.0, 12.0)
    protected val NORTH_AABB = box(4.0, 8.0, 0.0, 12.0, 16.0, 8.0)
    protected val SOUTH_AABB = box(4.0, 8.0, 8.0, 12.0, 16.0, 16.0)

    protected val WEST_AABB_3 = box(0.0, 1.0, 2.0, 12.0, 16.0, 14.0)
    protected val EAST_AABB_3 = box(4.0, 1.0, 2.0, 16.0, 16.0, 14.0)
    protected val NORTH_AABB_3 = box(2.0, 1.0, 0.0, 14.0, 16.0, 12.0)
    protected val SOUTH_AABB_3 = box(2.0, 1.0, 4.0, 14.0, 16.0, 16.0)

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

  override fun getShape(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pContext: CollisionContext): VoxelShape {
    val direction: Direction = pState.getValue(FACING)
    val age = pState.getValue(AGE)
    return when(age) {
      MAX_AGE-1 -> {
        when (direction) {
          Direction.EAST -> EAST_AABB_3
          Direction.WEST -> WEST_AABB_3
          Direction.SOUTH -> SOUTH_AABB_3
          Direction.NORTH -> NORTH_AABB_3
          else -> NORTH_AABB_3
        }
      }
      else -> {
        when (direction) {
          Direction.EAST -> EAST_AABB
          Direction.WEST -> WEST_AABB
          Direction.SOUTH -> SOUTH_AABB
          Direction.NORTH -> NORTH_AABB
          else -> NORTH_AABB
        }
      }
    }
  }



  override fun canSurvive(state: BlockState, worldIn: LevelReader, pos: BlockPos): Boolean {
    return canSurviveAcai(state, worldIn, pos)
  }
}