package com.dannbrown.braziliandelight.content.blocks

import com.dannbrown.deltaboxlib.content.block.GenericCropBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Supplier

class AcaiCropBlock(
  props: Properties,
  isBudding: Boolean = false,
  grownBlock: Supplier<out Block>? = null,
  isDouble: Boolean = false,
  isBush: Boolean = false,
  includeSeedOnDrop: Boolean = false,
  fruitItem: Supplier<ItemLike>?,
  chance: Float = 1f,
  multiplier: Int = 1
) : GenericCropBlock(
  props,
  isBudding,
  grownBlock,
  isDouble,
  isBush,
  includeSeedOnDrop,
  fruitItem,
  chance,
  multiplier,
) {
  companion object {
    val FACING = BlockStateProperties.HORIZONTAL_FACING
    protected val WEST_AABB = box(0.0, 8.0, 4.0, 8.0, 16.0, 12.0)
    protected val EAST_AABB = box(8.0, 8.0, 4.0, 16.0, 16.0, 12.0)
    protected val NORTH_AABB = box(4.0, 8.0, 0.0, 12.0, 16.0, 8.0)
    protected val SOUTH_AABB = box(4.0, 8.0, 8.0, 12.0, 16.0, 16.0)

    protected val WEST_AABB_3 = box(0.0, 1.0, 2.0, 12.0, 16.0, 14.0)
    protected val EAST_AABB_3 = box(4.0, 1.0, 2.0, 16.0, 16.0, 14.0)
    protected val NORTH_AABB_3 = box(2.0, 1.0, 0.0, 14.0, 16.0, 12.0)
    protected val SOUTH_AABB_3 = box(2.0, 1.0, 4.0, 14.0, 16.0, 16.0)

    protected val WEST_AABB_DOUBLE = box(0.0, 0.0, 1.0, 14.0, 16.0, 15.0)
    protected val EAST_AABB_DOUBLE = box(2.0, 0.0, 1.0, 16.0, 16.0, 15.0)
    protected val NORTH_AABB_DOUBLE = box(1.0, 0.0, 0.0, 15.0, 16.0, 14.0)
    protected val SOUTH_AABB_DOUBLE = box(1.0, 0.0, 2.0, 15.0, 16.0, 16.0)

  }

  init {
    registerDefaultState(defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(FACING, Direction.NORTH))
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
    super.createBlockStateDefinition(builder)
    builder.add(FACING)
  }

  // Handles placement of the AcaiCropBlock on a log block
  override fun getStateForPlacement(blockPlaceContext: BlockPlaceContext): BlockState? {
    val blockPos = blockPlaceContext.clickedPos
    val level = blockPlaceContext.level
    val clickedBlock = level.getBlockState(blockPos.relative(blockPlaceContext.clickedFace.opposite))

    if (canGrowAttached(clickedBlock)) {
      // Attach the crop to the side of the log and face it correctly
      val facing = blockPlaceContext.clickedFace.opposite
      return this.defaultBlockState().setValue(FACING, facing)
    }

    return null
  }

  protected fun canGrowAttached(state: BlockState): Boolean {
    return state.`is`(BlockTags.LOGS) || state.`is`(BlockTags.LEAVES)
  }

  // Overrides the grow logic to make it grow downwards
  override fun growCrops(level: Level, blockPos: BlockPos, blockState: BlockState) {
    var newState = this.getAge(blockState) + this.getBonemealAgeIncrease(level)
    if (newState > this.maxAge) newState = this.maxAge

    if (blockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
      // Only update the upper half's age when bonemeal is used on the lower half
      val otherPos = blockPos.above()
      val otherState = level.getBlockState(otherPos)
      if (otherState.`is`(this) && otherState.getValue(HALF) == DoubleBlockHalf.UPPER) {
        updateBlockState(level, otherPos, otherState.setValue(this.ageProperty, newState), 2)
      }
    } else {
      if (newState == this.maxAge && isBudding && !isDouble) growTallAcai(
        level,
        blockPos,
        blockState
      )
      else updateBlockState(level, blockPos, blockState.setValue(this.ageProperty, newState), 2)
    }
  }

  fun growTallAcai(level: Level, blockPos: BlockPos, blockState: BlockState) {
    val otherPos = blockPos.below()
    val otherState = level.getBlockState(otherPos)
    if (!isBudding || grownBlock == null || !canGrowBelow(otherState)) return
    level.setBlock(
      blockPos,
      grownBlock!!.get().defaultBlockState().setValue(FACING, blockState.getValue(FACING))
        .setValue(HALF, DoubleBlockHalf.UPPER),
      3
    )
    level.setBlock(
      otherPos,
      grownBlock!!.get().defaultBlockState().setValue(FACING, blockState.getValue(FACING))
        .setValue(HALF, DoubleBlockHalf.LOWER),
      3
    )
  }

  // Checks if the block below is a viable position for growth
  private fun canGrowBelow(state: BlockState): Boolean {
    // Ensure the block below is a valid surface for growth
    return state.`is`(Blocks.AIR) || state.canBeReplaced()
  }

  // Overrides to update both the upper and lower part of the crop if it is double-blocked
  override fun updateBlockState(level: Level, blockPos: BlockPos, blockstate: BlockState, i: Int) {
    level.setBlock(blockPos, blockstate, i)
    if (isDouble) level.setBlock(getDoubleOtherPos(blockPos, blockstate), getOtherBlockstate(blockstate), i)
  }

  // Makes sure the crop survives attached to the log block
  override fun canSurvive(state: BlockState, worldIn: LevelReader, pos: BlockPos): Boolean {
    val blockBelow = worldIn.getBlockState(pos.below())
    val blockAbove = worldIn.getBlockState(pos.above())

    // If it's the lower part of the crop, check if the block above is the upper part of the crop
    if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
      return blockAbove.`is`(this) // Ensure the block above is the upper part
    }

    // If it's the upper part of the crop, check if the block below is a log and if it's connected on the correct side
    if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
      val sideBlockPos = pos.relative(state.getValue(FACING)) // Get the position on the opposite side (facing side)
      val sideBlockState = worldIn.getBlockState(sideBlockPos)

      val doubleCheck = isDouble && blockBelow.`is`(this) || !isDouble // Baduntss
      return canGrowAttached(sideBlockState) && doubleCheck
    }

    return false
  }


  override fun randomTick(
    blockState: BlockState,
    serverLevel: ServerLevel,
    blockPos: BlockPos,
    randomSource: RandomSource
  ) {
    // if is the lower part, doesn't grow with random tick, depends on the upper part
    if (blockState.getValue(HALF) == DoubleBlockHalf.LOWER) return
    // grow normaly if its the lower part, should work for the normal and double variants

    if (serverLevel.getRawBrightness(blockPos, 0) >= 9) {
      val i = this.getAge(blockState)
      if (i == this.maxAge && isBudding) {
        growTallAcai(serverLevel, blockPos, blockState)
        return
      }
      if (i < this.maxAge) {
        val f = getGrowthSpeed(this, serverLevel, blockPos);
        if (randomSource.nextInt((25.0F / f).toInt() + 1) == 0) {
          updateBlockState(serverLevel, blockPos, blockState.setValue(this.ageProperty, (i + 1)), 2)
        }
      }
    }
  }

  override fun getShape(
    state: BlockState,
    level: BlockGetter,
    pos: BlockPos,
    context: CollisionContext
  ): VoxelShape {
    val direction: Direction = state.getValue(FACING)
    val age = state.getValue(AGE)

    if (isDouble) {
      return when (state.getValue(FACING)) {
        Direction.NORTH -> NORTH_AABB_DOUBLE
        Direction.SOUTH -> SOUTH_AABB_DOUBLE
        Direction.EAST -> EAST_AABB_DOUBLE
        Direction.WEST -> WEST_AABB_DOUBLE
        else -> super.getShape(state, level, pos, context)
      }
    }

    return when (age) {
      MAX_AGE - 1 -> {
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
}