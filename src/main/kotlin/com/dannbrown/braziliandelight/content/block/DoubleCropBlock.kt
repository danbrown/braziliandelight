package com.dannbrown.braziliandelight.content.block

import com.dannbrown.databoxlib.content.block.GenericCropBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.common.ForgeHooks
import net.minecraftforge.common.IPlantable
import org.jetbrains.annotations.NotNull
import java.util.function.Supplier

open class DoubleCropBlock(
  props: Properties,
  private val isBush: Boolean = false,
  private val dropItem: Supplier<Item>,
  private val seedItem: Supplier<Item>? = null,
  private val chance: Float = 1f,
  private val multiplier: Int = 1
): GenericCropBlock(props, null), BonemealableBlock, IPlantable {
  companion object {
    const val MAX_AGE: Int = 3
    val AGE: IntegerProperty = BlockStateProperties.AGE_3
    val WATERLOGGED = BlockStateProperties.WATERLOGGED
    val HALF = BlockStateProperties.DOUBLE_BLOCK_HALF

    val SHAPE_BY_AGE = arrayOf(
      box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
      box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
      box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
      box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    )

    fun canBeGrown(serverLevel: ServerLevel, blockPos: BlockPos): Boolean {
      return blockPos.y < serverLevel.maxBuildHeight - 1 && (serverLevel.getBlockState(blockPos.above()).isAir || serverLevel.getBlockState(blockPos.above()).canBeReplaced())
    }
  }

  init {
    registerDefaultState(defaultBlockState().setValue(AGE, 0).setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, false))
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
    builder.add(WATERLOGGED, AGE, HALF)
  }

  override fun getMaxAge(): Int {
    return MAX_AGE
  }

  override fun getAgeProperty(): IntegerProperty {
    return AGE
  }
  override fun randomTick(pState: BlockState, pLevel: ServerLevel, pPos: BlockPos, pRandom: RandomSource) {
    if (pLevel.isAreaLoaded(pPos, 1)) {
      if (pLevel.getRawBrightness(pPos, 0) >= 9) {
        val i = this.getAge(pState)
        if (i < this.maxAge) {
          val f = getGrowthSpeed(this, pLevel, pPos)
          if (ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt((25.0f / f).toInt() + 1) == 0)) {
//            pLevel.setBlock(pPos, this.getStateForAge(i + 1), 2)
            handleGrowing(pState, pLevel, pPos)
            ForgeHooks.onCropsGrowPost(pLevel, pPos, pState)
          }
        }
      }
    }
  }

  fun handleGrowing(blockState: BlockState, serverLevel: ServerLevel, blockPos: BlockPos) {
    if(!canSurvive(blockState, serverLevel, blockPos)){
      serverLevel.destroyBlock(blockPos, true)
      return
    }

    // if the block is the upper half, check if the lower half is still there
    if (blockState.getValue(HALF) == DoubleBlockHalf.UPPER) {
      val lowerPos = blockPos.below()
      val lowerState = serverLevel.getBlockState(lowerPos)
      if (lowerState.block != this || lowerState.getValue(HALF) != DoubleBlockHalf.LOWER) {
        serverLevel.destroyBlock(blockPos, true)
        return
      }
    }

    // if the block is the lower half, check if the upper half is still there
    if (blockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
      val upperPos = blockPos.above()
      val upperState = serverLevel.getBlockState(upperPos)
      if (upperState.block != this || upperState.getValue(HALF) != DoubleBlockHalf.UPPER) {
        serverLevel.destroyBlock(blockPos, true)
        return
      }

      // all fine, if the age is less than the max age, increment the age in the lower half and the upper half, otherwise do nothing
      growAge(serverLevel, blockPos, upperPos, blockState, upperState)
    }
  }

  fun growAge(serverLevel: ServerLevel, lowerPos: BlockPos, upperPos: BlockPos, blockState: BlockState, upperState: BlockState, states: Int = 1) {
    val newStateQty: Int = Mth.clamp(blockState.getValue(ageProperty) + states, 0, maxAge)

    if (blockState.getValue(ageProperty) < maxAge) {
      serverLevel.setBlock(lowerPos, blockState.setValue(ageProperty, newStateQty), 3)
      serverLevel.setBlock(upperPos, upperState.setValue(ageProperty, newStateQty), 3)
    }
  }

  fun resetAge(serverLevel: ServerLevel, lowerPos: BlockPos, upperPos: BlockPos, blockState: BlockState, upperState: BlockState) {
    serverLevel.setBlock(lowerPos, blockState.setValue(ageProperty, 0), 3)
    serverLevel.setBlock(upperPos, upperState.setValue(ageProperty, 0), 3)
  }

  override fun isValidBonemealTarget(p0: LevelReader, p1: BlockPos, p2: BlockState, p3: Boolean): Boolean {
    return !isMature(p2)
  }

  override fun isBonemealSuccess(p0: Level, p1: RandomSource, p2: BlockPos, p3: BlockState): Boolean {
    return !isMature(p3)
  }

  fun isMature(blockState: BlockState): Boolean {
    return blockState.getValue(ageProperty) == maxAge
  }

  override fun performBonemeal(
    @NotNull serverLevel: ServerLevel,
    @NotNull random: RandomSource,
    @NotNull blockPos: BlockPos,
    @NotNull blockState: BlockState
  ) {
    // if the block is the upper half, check if the lower half is still there, if yes, grow both blocks
    if (blockState.getValue(HALF) == DoubleBlockHalf.UPPER) {
      val lowerPos = blockPos.below()
      val lowerState = serverLevel.getBlockState(lowerPos)
      if (lowerState.block == this && lowerState.getValue(HALF) == DoubleBlockHalf.LOWER) {
        growAge(serverLevel, lowerPos, blockPos, lowerState, blockState, Mth.nextInt(serverLevel.random, 1, maxAge))
      }
    }

    // if the block is the lower half, check if the upper half is still there, if yes, grow both blocks
    if (blockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
      val upperPos = blockPos.above()
      val upperState = serverLevel.getBlockState(upperPos)
      if (upperState.block == this && upperState.getValue(HALF) == DoubleBlockHalf.UPPER) {
        growAge(serverLevel, blockPos, upperPos, blockState, upperState, Mth.nextInt(serverLevel.random, 1, maxAge))
      }
    }
  }

  fun dropResources(pLevel: ServerLevel, pPos: BlockPos) {
    // if no seed and drop item is set, at least one is required
    if (!isBush) return

    // if have a seed item, do 'multiplier' rows of 'chance' to pop a seed
    if (seedItem != null) {
      var seedsToDrop = 0
      for (i in 0 until multiplier) {
        if (pLevel.random.nextFloat() < chance) {
          seedsToDrop++
        }
      }
      if (seedsToDrop > 0) Block.popResource(pLevel, pPos, ItemStack(seedItem.get(), seedsToDrop))
    }

    // if have a drop item, do 'multiplier' rows of 'chance' to pop a drop item
    var dropsToDrop = 1
    for (i in 0 until multiplier-1) {
      if (pLevel.random.nextFloat() < chance) {
        dropsToDrop++
      }
    }
    if (dropsToDrop > 0) Block.popResource(pLevel, pPos, ItemStack(dropItem.get(), dropsToDrop))

    pLevel.playSound(
      null,
      pPos,
      SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES,
      SoundSource.BLOCKS,
      1.0f,
      0.8f + pLevel.random.nextFloat() * 0.4f
    )
  }

  override fun use(pState: BlockState, pLevel: Level, pPos: BlockPos, pPlayer: Player, pHand: InteractionHand, pHit: BlockHitResult): InteractionResult {
    if (pLevel.isClientSide) return InteractionResult.PASS
    pLevel as ServerLevel

    // if has nothing do drop (no seed and no drop item), return
    if (!isBush) return InteractionResult.PASS

    // if the block is the upper half, check if the lower half is still there, if yes and its at max age, reset the age of both blocks
    if (pState.getValue(HALF) == DoubleBlockHalf.UPPER && checkOtherState(pLevel, pPos, DoubleBlockHalf.UPPER)) {
      dropResources(pLevel, pPos)
      resetAge(pLevel, pPos, pPos.below(), pState, pLevel.getBlockState(pPos.below()))
      return InteractionResult.SUCCESS
    }

    // if the block is the lower half, check if the upper half is still there, if yes and its at max age, reset the age of both blocks
    if (pState.getValue(HALF) == DoubleBlockHalf.LOWER && checkOtherState(pLevel, pPos, DoubleBlockHalf.LOWER)) {
      dropResources(pLevel, pPos)
      resetAge(pLevel, pPos.above(), pPos, pLevel.getBlockState(pPos.above()), pState)
      return InteractionResult.SUCCESS
    }

    return InteractionResult.PASS
  }

  private fun checkOtherState(pLevel: Level, pPos: BlockPos, half: DoubleBlockHalf): Boolean {
    val otherPos = if (half == DoubleBlockHalf.UPPER) pPos.below() else pPos.above()
    val otherState = pLevel.getBlockState(otherPos)
    return otherState.block == this && otherState.getValue(HALF) != half && isMature(otherState)
  }

  override fun canSurvive(state: BlockState, worldIn: LevelReader, pos: BlockPos): Boolean {
    return (worldIn.getRawBrightness(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && canSurviveDouble(state, worldIn, pos)
  }

  open fun canSurviveDouble(pState: BlockState, pLevel: LevelReader, pPos: BlockPos): Boolean {
    if (pState.getValue(HALF) != DoubleBlockHalf.UPPER) {
      return super.canSurvive(pState, pLevel, pPos)
    }
    else {
      val blockstate = pLevel.getBlockState(pPos.below())
      return if (pState.block !== this) {
        super.canSurvive(pState, pLevel, pPos)
      }
      else {
        blockstate.`is`(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER
      }
    }
  }

  override fun getCloneItemStack(state: BlockState?, target: HitResult?, level: BlockGetter?, pos: BlockPos?, player: Player?): ItemStack {
    return ItemStack(dropItem.get())
  }

  override fun getShape(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pContext: CollisionContext): VoxelShape {
    return SHAPE_BY_AGE[pState.getValue(AGE)]
  }

  override fun getSeed(pState: BlockState, pPos: BlockPos): Long {
    return Mth.getSeed(pPos.x, pPos.below(if (pState.getValue(HALF) == DoubleBlockHalf.LOWER) 0 else 1).y, pPos.z)
  }


  fun copyWaterloggedFrom(pLevel: LevelReader, pPos: BlockPos?, pState: BlockState): BlockState {
    return if (pState.hasProperty(BlockStateProperties.WATERLOGGED)) pState.setValue(BlockStateProperties.WATERLOGGED, pLevel.isWaterAt(pPos)) else pState
  }

  override fun playerWillDestroy(pLevel: Level, pPos: BlockPos, pState: BlockState, pPlayer: Player) {
    if (!pLevel.isClientSide) {
      if (pPlayer.isCreative) {
        preventCreativeDropFromBottomPart(pLevel, pPos, pState, pPlayer)
      }
      else {
        dropResources(pState, pLevel, pPos, null as BlockEntity?, pPlayer, pPlayer.mainHandItem)
      }
    }

    super.playerWillDestroy(pLevel, pPos, pState, pPlayer)
  }

  override fun playerDestroy(pLevel: Level, pPlayer: Player, pPos: BlockPos, pState: BlockState, pTe: BlockEntity?, pStack: ItemStack) {
    super.playerDestroy(pLevel, pPlayer, pPos, Blocks.AIR.defaultBlockState(), pTe, pStack)
  }

  open fun preventCreativeDropFromBottomPart(pLevel: Level, pPos: BlockPos, pState: BlockState, pPlayer: Player?) {
    val doubleBlockHalf = pState.getValue(HALF) as DoubleBlockHalf
    if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
      val blockPos = pPos.below()
      val blockState = pLevel.getBlockState(blockPos)
      if (blockState.`is`(pState.block) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
        val blockState1 = if (blockState.fluidState.`is`(Fluids.WATER)) Blocks.WATER.defaultBlockState() else Blocks.AIR.defaultBlockState()
        pLevel.setBlock(blockPos, blockState1, 35)
        pLevel.levelEvent(pPlayer, 2001, blockPos, getId(blockState))
      }
    }
  }

  override fun updateShape(pState: BlockState, pFacing: Direction, pFacingState: BlockState, pLevel: LevelAccessor, pCurrentPos: BlockPos, pFacingPos: BlockPos): BlockState {
    val doubleBlockHalf = pState.getValue(HALF) as DoubleBlockHalf
    return if ((pFacing.axis !== Direction.Axis.Y) || (doubleBlockHalf == DoubleBlockHalf.LOWER) != (pFacing == Direction.UP) || pFacingState.`is`(this) && pFacingState.getValue(HALF) != doubleBlockHalf) {
      if (doubleBlockHalf == DoubleBlockHalf.LOWER && pFacing == Direction.DOWN && !pState.canSurvive(pLevel, pCurrentPos)) Blocks.AIR.defaultBlockState() else super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos)
    }
    else {
      Blocks.AIR.defaultBlockState()
    }
  }

  override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? {
    val blockPos = pContext.clickedPos
    val level = pContext.level
    return if (blockPos.y < level.maxBuildHeight - 1 && level.getBlockState(blockPos.above()).canBeReplaced(pContext)) super.getStateForPlacement(pContext)
    else null
  }

  override fun setPlacedBy(pLevel: Level, pPos: BlockPos, pState: BlockState, pPlacer: LivingEntity?, pStack: ItemStack) {
    val blockPos = pPos.above()
    pLevel.setBlock(blockPos, copyWaterloggedFrom(pLevel, blockPos, defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)), 3)
  }
}