package com.dannbrown.braziliandelight.content.block

import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.ParticleUtils
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.LeavesBlock
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.common.IForgeShearable
import java.util.*
import kotlin.math.min

open class PalmLeavesBlock(pProperties: Properties) : LeavesBlock(pProperties), SimpleWaterloggedBlock, IForgeShearable {
  override fun getBlockSupportShape(pState: BlockState, pReader: BlockGetter, pPos: BlockPos): VoxelShape {
    return Shapes.empty()
  }

  override fun isRandomlyTicking(pState: BlockState): Boolean {
    return pState.getValue(DISTANCE_9) == DECAY_DISTANCE && !pState.getValue(PERSISTENT)
  }

  override fun randomTick(pState: BlockState, pLevel: ServerLevel, pPos: BlockPos, pRandom: RandomSource) {
    if (this.decaying(pState)) {
      dropResources(pState, pLevel, pPos)
      pLevel.removeBlock(pPos, false)
    }
  }

  override fun decaying(pState: BlockState): Boolean {
    return !pState.getValue(PERSISTENT) && pState.getValue(DISTANCE_9) == DECAY_DISTANCE
  }

  override fun tick(pState: BlockState, pLevel: ServerLevel, pPos: BlockPos, pRandom: RandomSource) {
    pLevel.setBlock(pPos, updateDistance(pState, pLevel, pPos), 3)
  }

  override fun getLightBlock(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos): Int {
    return 1
  }

  override fun updateShape(pState: BlockState, pFacing: Direction, pFacingState: BlockState, pLevel: LevelAccessor, pCurrentPos: BlockPos, pFacingPos: BlockPos): BlockState {
    if (pState.getValue(WATERLOGGED)) {
      pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel))
    }
    val i: Int = getDistanceAt(pFacingState) + 1
    if (i != 1 || pState.getValue(DISTANCE_9) != i) {
      pLevel.scheduleTick(pCurrentPos, this, 1)
    }
    return pState
  }

  override fun getFluidState(pState: BlockState): FluidState {
    return if (pState.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(pState)
  }

  override fun animateTick(pState: BlockState, pLevel: Level, pPos: BlockPos, pRandom: RandomSource) {
    if (pLevel.isRainingAt(pPos.above()) && pRandom.nextInt(15) == 1) {
      val blockpos = pPos.below()
      val blockstate = pLevel.getBlockState(blockpos)
      if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(pLevel, blockpos, Direction.UP)) {
        ParticleUtils.spawnParticleBelow(pLevel, pPos, pRandom, ParticleTypes.DRIPPING_WATER)
      }
    }
  }

  override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
    pBuilder.add(DISTANCE_9, PERSISTENT, WATERLOGGED, DISTANCE)
  }

  override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState {
    val fluidstate = pContext.level.getFluidState(pContext.clickedPos)
    val blockstate = (defaultBlockState().setValue(PERSISTENT, true)).setValue(WATERLOGGED, fluidstate.type === Fluids.WATER)
    return updateDistance(blockstate, pContext.level, pContext.clickedPos)
  }

  init {
    this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE_9, DECAY_DISTANCE).setValue(PERSISTENT, false).setValue(WATERLOGGED, false).setValue(DISTANCE, 7))
  }

  companion object {
    const val DECAY_DISTANCE: Int = 9
    val DISTANCE: IntegerProperty = BlockStateProperties.DISTANCE
    val PERSISTENT: BooleanProperty = BlockStateProperties.PERSISTENT
    val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
    val DISTANCE_9: IntegerProperty = IntegerProperty.create("distance_9", 1, DECAY_DISTANCE)

    private const val TICK_DELAY = 1
    private fun updateDistance(pState: BlockState, pLevel: LevelAccessor, pPos: BlockPos): BlockState {
      var i = DECAY_DISTANCE
      val mutablePos = MutableBlockPos()
      val var5 = Direction.values()
      val var6 = var5.size

      for (var7 in 0 until var6) {
        val direction = var5[var7]
        mutablePos.setWithOffset(pPos, direction)
        i = min(i.toDouble(), (getDistanceAt(pLevel.getBlockState(mutablePos)) + 1).toDouble()).toInt()
        if (i == 1) {
          break
        }
      }

      return pState.setValue(DISTANCE_9, i)
    }

    private fun getDistanceAt(pNeighbor: BlockState): Int {
      return getOptionalDistanceAt(pNeighbor).orElse(DECAY_DISTANCE)
    }

    fun getOptionalDistanceAt(pState: BlockState): OptionalInt {
      return if (pState.`is`(BlockTags.LOGS)) {
        OptionalInt.of(0)
      }
      else {
        if (pState.hasProperty(DISTANCE_9)) OptionalInt.of((pState.getValue(DISTANCE_9))) else OptionalInt.empty()
      }
    }


  }
}
