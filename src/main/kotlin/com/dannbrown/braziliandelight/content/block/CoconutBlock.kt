package com.dannbrown.braziliandelight.content.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.ChangeOverTimeBlock
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock
import net.minecraft.world.level.block.LadderBlock
import net.minecraft.world.level.block.SaplingBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*
import java.util.function.Supplier

class CoconutBlock(props: Properties, private val currentAge: CoconutState, private val nextBlock: Supplier< out Block>? = null)
  : FaceAttachedHorizontalDirectionalBlock(props), ChangeOverTimeBlock<CoconutBlock.CoconutState>, BonemealableBlock {
  init {
    registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false))
  }

  override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
    super.createBlockStateDefinition(pBuilder.add(FACE, FACING, BlockStateProperties.WATERLOGGED))
  }

  override fun onPlace(blockState: BlockState, level: Level, blockPos: BlockPos, blockState2: BlockState, p_60570_: Boolean) {
    super.onPlace(blockState, level, blockPos, blockState2, p_60570_)
  }

  override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? {
    var stateForPlacement = super.getStateForPlacement(pContext) ?: return null
    if (stateForPlacement.getValue(FACE) == AttachFace.FLOOR) stateForPlacement = stateForPlacement.setValue(FACING, stateForPlacement.getValue(FACING).opposite)
    return stateForPlacement
  }

  override fun getFluidState(pState: BlockState): FluidState {
    return if (pState.getValue(BlockStateProperties.WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(pState)
  }

  override fun getShape(blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos, collisionContext: CollisionContext): VoxelShape {
    val direction: Direction = blockState.getValue(FACING)

    return when (blockState.getValue(FACE) as AttachFace) {
      AttachFace.FLOOR -> {
        if (direction.axis === Direction.Axis.X) {
          return FLOOR_AABB_X
        }
        FLOOR_AABB_Z
      }

      AttachFace.WALL -> when (direction) {
        Direction.EAST -> EAST_AABB
        Direction.WEST -> WEST_AABB
        Direction.SOUTH -> SOUTH_AABB
        Direction.NORTH -> NORTH_AABB
        else -> NORTH_AABB
      }

      AttachFace.CEILING -> if (direction.axis === Direction.Axis.X) {
        CEILING_AABB_X
      }
      else {
        CEILING_AABB_Z
      }

      else -> if (direction.axis === Direction.Axis.X) {
        CEILING_AABB_X
      }
      else {
        CEILING_AABB_Z
      }
    }

  }

  override fun updateShape(blockState: BlockState, direction: Direction, blockState2: BlockState, levelAccessor: LevelAccessor, blockPos: BlockPos, blockPos2: BlockPos
  ): BlockState {
    return if (direction.opposite == blockState.getValue(LadderBlock.FACING) && !blockState.canSurvive(levelAccessor, blockPos)) {
      Blocks.AIR.defaultBlockState()
    }
    else {
      if (blockState.getValue(LadderBlock.WATERLOGGED)) {
        levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor))
      }
      super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2)
    }
  }

  override fun canSurvive(pState: BlockState, pLevel: LevelReader, pPos: BlockPos): Boolean {

    val direction = pState.getValue(FACING)
    // if the block is on the floor, allow it to be placed on any block
    if (pState.getValue(FACE) == AttachFace.FLOOR) {
      val blockState = pLevel.getBlockState(pPos.below())
      return blockState.isFaceSturdy(pLevel, pPos, Direction.UP) || blockState.tags.anyMatch { tag -> tag == BlockTags.LOGS || tag == BlockTags.LEAVES }
    }
    // if the block is on the ceiling, allow it to be placed only on logs and leaves
    else if (pState.getValue(FACE) == AttachFace.CEILING) {
      val blockState = pLevel.getBlockState(pPos.above())
      return blockState.tags.anyMatch { tag -> tag == BlockTags.LOGS || tag == BlockTags.LEAVES }
    }
    // if the block is on the wall, allow it to be placed only on logs and leaves
    else if (pState.getValue(FACE) == AttachFace.WALL) {
      val blockState = pLevel.getBlockState(pPos.relative(direction.opposite))
      return blockState.tags.anyMatch { tag -> tag == BlockTags.LOGS || tag == BlockTags.LEAVES }
    }
    return false
  }

  companion object {
    protected val EAST_AABB = box(0.0, 4.0, 4.0, 8.0, 12.0, 12.0)
    protected val WEST_AABB = box(8.0, 4.0, 4.0, 16.0, 12.0, 12.0)
    protected val SOUTH_AABB = box(4.0, 4.0, 0.0, 12.0, 12.0, 8.0)
    protected val NORTH_AABB = box(4.0, 4.0, 8.0, 12.0, 12.0, 16.0)
    protected val FLOOR_AABB_X = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
    protected val FLOOR_AABB_Z = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
    protected val CEILING_AABB_X = box(4.0, 8.0, 4.0, 12.0, 16.0, 12.0)
    protected val CEILING_AABB_Z = box(4.0, 8.0, 4.0, 12.0, 16.0, 12.0)
  }

  override fun randomTick(pState: BlockState, pLevel: ServerLevel, pPos: BlockPos, pRandom: RandomSource) {
    this.onRandomTick(pState, pLevel, pPos, pRandom)
  }

  override fun isRandomlyTicking(pState: BlockState): Boolean {
    return getNext(pState).isPresent
  }
  override fun getNext(p0: BlockState): Optional<BlockState> {
    // if next is a coconut block, return it with current states, if not, return the default block state, always check canSurvive
    if(nextBlock == null) {
      return Optional.ofNullable(null)
    }
    else if (nextBlock.get() is CoconutBlock) {
      return Optional.ofNullable(
        nextBlock.get().defaultBlockState()
          .setValue(FACING, p0.getValue(FACING))
          .setValue(FACE, p0.getValue(FACE))
          .setValue(BlockStateProperties.WATERLOGGED, p0.getValue(BlockStateProperties.WATERLOGGED))
      )
    }
    else if (p0.getValue(FACE) == AttachFace.FLOOR){
      return Optional.ofNullable(nextBlock.get().defaultBlockState())
    }
    return Optional.ofNullable(null)
  }
  override fun getChanceModifier(): Float {
    return 0.75f
  }
  override fun getAge(): CoconutState {
    return currentAge
  }

  enum class CoconutState {
    GREEN,
    BROWN,
    SPROUT,
  }

  override fun isValidBonemealTarget(levelReader: LevelReader, blockPos: BlockPos, blockState: BlockState, p3: Boolean): Boolean {
    return currentAge != CoconutState.SPROUT && currentAge != CoconutState.BROWN
  }
  override fun isBonemealSuccess(level: Level, randomSource: RandomSource, blockPos: BlockPos, blockState: BlockState): Boolean {
    return currentAge != CoconutState.SPROUT && currentAge != CoconutState.BROWN
  }
  override fun performBonemeal(serverLevel: ServerLevel, randomSource: RandomSource, blockPos: BlockPos, blockState: BlockState) {
    getNext(blockState).ifPresent { nextBlockState ->
      serverLevel.setBlock(blockPos, nextBlockState, 2)
    }
  }
}


