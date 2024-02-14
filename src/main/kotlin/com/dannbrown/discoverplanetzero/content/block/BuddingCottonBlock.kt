package com.dannbrown.discoverplanetzero.content.block

import com.dannbrown.databoxlib.content.block.BuddingBushBlock
import com.dannbrown.discover.init._DiscoverBlocks
import com.dannbrown.discoverplanetzero.init.AddonBlocks
import com.dannbrown.discoverplanetzero.init.AddonItems
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.state.BlockState
import kotlin.math.min

class BuddingCottonBlock(properties: Properties?) : BuddingBushBlock(properties, { AddonItems.COTTON_SEEDS.get() }), BonemealableBlock {
  override fun getPlant(world: BlockGetter, pos: BlockPos): BlockState {
    return AddonBlocks.BUDDING_COTTON_CROP.get().defaultBlockState()
  }

  override fun mayPlaceOn(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos): Boolean {
    return pState.`is`((Blocks.FARMLAND))
  }

  override fun updateShape(
    state: BlockState,
    facing: Direction,
    facingState: BlockState,
    level: LevelAccessor,
    currentPos: BlockPos,
    facingPos: BlockPos
  ): BlockState {
    if (state.getValue<Int>(AGE) == 4) {
      level.setBlock(currentPos, AddonBlocks.COTTON_CROP.get().defaultBlockState(), 3)
    }
    return super.updateShape(state, facing, facingState, level, currentPos, facingPos)
  }

  override fun canGrowPastMaxAge(): Boolean {
    return true
  }

  override fun growPastMaxAge(state: BlockState?, level: ServerLevel?, pos: BlockPos?, random: RandomSource?) {
    level!!.setBlockAndUpdate(pos, AddonBlocks.COTTON_CROP.get().defaultBlockState())
  }

  override fun isValidBonemealTarget(levelReader: LevelReader, pos: BlockPos, state: BlockState, isClient: Boolean): Boolean {
    return true
  }

  override fun isBonemealSuccess(level: Level, random: RandomSource, pos: BlockPos, state: BlockState): Boolean {
    return true
  }

  protected fun getBonemealAgeIncrease(level: Level): Int {
    return Mth.nextInt(level.random, 1, 4)
  }

  override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
    val maxAge: Int = getMaxAge()
    val ageGrowth = min((getAge(state) + getBonemealAgeIncrease(level)).toDouble(), 7.0).toInt()
    if (ageGrowth <= maxAge) {
      level.setBlockAndUpdate(pos, state.setValue(AGE, ageGrowth))
    } else {
      val remainingGrowth = ageGrowth - maxAge - 1
      level.setBlockAndUpdate(
        pos,
        AddonBlocks.COTTON_CROP.get().defaultBlockState().setValue(CottonCropBlock.CROP_AGE, remainingGrowth)
      )
    }
  }
}