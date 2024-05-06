package com.dannbrown.braziliandelight.content.block

import com.dannbrown.databoxlib.content.block.GenericDoublePlantBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.common.ForgeHooks
import net.minecraftforge.common.IPlantable
import org.jetbrains.annotations.NotNull
import java.util.function.Supplier

class DoubleCropBlock(
  props: Properties,
  private val isBush: Boolean = false,
  private val dropItem: Supplier<Item>,
  private val seedItem: Supplier<Item>? = null,
  private val chance: Float = 1f,
  private val multiplier: Int = 1
): GenericDoublePlantBlock(props, null), BonemealableBlock, IPlantable {
  companion object {
    const val MAX_AGE: Int = 3
    val AGE: IntegerProperty = IntegerProperty.create("age", 0, MAX_AGE)
    val WATERLOGGED = BlockStateProperties.WATERLOGGED


    private val SHAPE_BY_AGE = arrayOf(
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

  fun getMaxAge(): Int {
    return MAX_AGE
  }

  fun getAgeProperty(): IntegerProperty {
    return AGE
  }

  fun isMature(blockState: BlockState): Boolean {
    return blockState.getValue(getAgeProperty()) == getMaxAge()
  }

  override fun randomTick(blockState: BlockState, serverLevel: ServerLevel, blockPos: BlockPos, @NotNull random: RandomSource) {
    if (serverLevel.isAreaLoaded(blockPos, 1)) {
      if (serverLevel.getRawBrightness(blockPos, 0) >= 9) {
        val i: Int = blockState.getValue(getAgeProperty())
        if (i < this.getMaxAge()) {
          val f: Float = getGrowthSpeed(this, serverLevel, blockPos)
          if (ForgeHooks.onCropsGrowPre(serverLevel, blockPos, blockState, random.nextInt((25.0f / f).toInt() + 1) == 0)) {
            handleGrowing(blockState, serverLevel, blockPos)
            ForgeHooks.onCropsGrowPost(serverLevel, blockPos, blockState)
          }
        }
      }
    }
  }

  // this is copied from the vanilla CropBlock class
  protected fun getGrowthSpeed(pBlock: Block, pLevel: BlockGetter, pPos: BlockPos): Float {
    var f = 1.0f
    val blockPos = pPos.below()

    for (i in -1..1) {
      for (j in -1..1) {
        var f1 = 0.0f
        val blockState = pLevel.getBlockState(blockPos.offset(i, 0, j))
        if (blockState.canSustainPlant(pLevel, blockPos.offset(i, 0, j), Direction.UP, pBlock as IPlantable?)) {
          f1 = 1.0f
          if (blockState.isFertile(pLevel, pPos.offset(i, 0, j))) {
            f1 = 3.0f
          }
        }

        if (i != 0 || j != 0) {
          f1 /= 4.0f
        }

        f += f1
      }
    }
    val blockPos1 = pPos.north()
    val blockPos2 = pPos.south()
    val blockPos3 = pPos.west()
    val blockPos4 = pPos.east()
    val flag = pLevel.getBlockState(blockPos3).`is`(pBlock) || pLevel.getBlockState(blockPos4).`is`(pBlock)
    val flag1 = pLevel.getBlockState(blockPos1).`is`(pBlock) || pLevel.getBlockState(blockPos2).`is`(pBlock)
    if (flag && flag1) { f /= 2.0f }
    else {
      val flag2 = pLevel.getBlockState(blockPos3.north())
        .`is`(pBlock) || pLevel.getBlockState(blockPos4.north())
        .`is`(pBlock) || pLevel.getBlockState(blockPos4.south())
        .`is`(pBlock) || pLevel.getBlockState(blockPos3.south())
        .`is`(pBlock)
      if (flag2) { f /= 2.0f }
    }

    return f
  }

  fun handleGrowing(blockState: BlockState, serverLevel: ServerLevel, blockPos: BlockPos, states: Int = 1) {
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
    val newStateQty: Int = Mth.clamp(blockState.getValue(getAgeProperty()) + states, 0, getMaxAge())

    if (blockState.getValue(getAgeProperty()) < getMaxAge()) {
      serverLevel.setBlock(lowerPos, blockState.setValue(getAgeProperty(), newStateQty), 3)
      serverLevel.setBlock(upperPos, upperState.setValue(getAgeProperty(), newStateQty), 3)
    }
  }

  fun resetAge(serverLevel: ServerLevel, lowerPos: BlockPos, upperPos: BlockPos, blockState: BlockState, upperState: BlockState) {
    serverLevel.setBlock(lowerPos, blockState.setValue(getAgeProperty(), 0), 3)
    serverLevel.setBlock(upperPos, upperState.setValue(getAgeProperty(), 0), 3)
  }

  override fun isValidBonemealTarget(p0: LevelReader, p1: BlockPos, p2: BlockState, p3: Boolean): Boolean {
    return !isMature(p2)
  }

  override fun isBonemealSuccess(p0: Level, p1: RandomSource, p2: BlockPos, p3: BlockState): Boolean {
    return !isMature(p3)
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
        growAge(serverLevel, lowerPos, blockPos, lowerState, blockState, Mth.nextInt(serverLevel.random, 1, getMaxAge()))
      }
    }

    // if the block is the lower half, check if the upper half is still there, if yes, grow both blocks
    if (blockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
      val upperPos = blockPos.above()
      val upperState = serverLevel.getBlockState(upperPos)
      if (upperState.block == this && upperState.getValue(HALF) == DoubleBlockHalf.UPPER) {
        growAge(serverLevel, blockPos, upperPos, blockState, upperState, Mth.nextInt(serverLevel.random, 1, getMaxAge()))
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
    return (worldIn.getRawBrightness(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && super.canSurvive(state, worldIn, pos)
  }

  override fun getCloneItemStack(state: BlockState?, target: HitResult?, level: BlockGetter?, pos: BlockPos?, player: Player?): ItemStack {
    return ItemStack(dropItem.get())
  }

  override fun getShape(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pContext: CollisionContext): VoxelShape {
    return SHAPE_BY_AGE[pState.getValue(AGE)]
  }
}