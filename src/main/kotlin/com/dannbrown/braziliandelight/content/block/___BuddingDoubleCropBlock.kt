package com.dannbrown.braziliandelight.content.block

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
import vectorwing.farmersdelight.common.block.BuddingBushBlock
import vectorwing.farmersdelight.common.block.TomatoVineBlock
import vectorwing.farmersdelight.common.registry.ModBlocks
import java.util.function.Supplier
import kotlin.math.min


open class ___BuddingDoubleCropBlock(props: Properties, private val bottomBlock: Supplier<___DoubleCropBlock>) : BuddingBushBlock(props), BonemealableBlock {
  override fun getPlant(world: BlockGetter, pos: BlockPos): BlockState {
    return this.defaultBlockState()
  }

  override fun mayPlaceOn(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos): Boolean {
    return pState.`is`(ModBlocks.RICH_SOIL_FARMLAND.get()) || pState.`is`(Blocks.FARMLAND)
  }

  override fun updateShape(state: BlockState, facing: Direction, facingState: BlockState, level: LevelAccessor, currentPos: BlockPos, facingPos: BlockPos): BlockState {
    if (state.getValue(AGE) as Int == 4) {
      level.setBlock(currentPos, bottomBlock.get().defaultBlockState(), 3)
    }

    return super.updateShape(state, facing, facingState, level, currentPos, facingPos)
  }

  override fun canGrowPastMaxAge(): Boolean {
    return true
  }

  override fun growPastMaxAge(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
    level.setBlockAndUpdate(pos, ModBlocks.TOMATO_CROP.get().defaultBlockState())
  }

  override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState, isClient: Boolean): Boolean {
    return true
  }

  override fun isBonemealSuccess(level: Level, random: RandomSource, pos: BlockPos, state: BlockState): Boolean {
    return true
  }

  protected fun getBonemealAgeIncrease(level: Level): Int {
    return Mth.nextInt(level.random, 1, 4)
  }

  override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
    val maxAge = this.maxAge
    val ageGrowth = min((this.getAge(state) + this.getBonemealAgeIncrease(level)).toDouble(), 7.0)
      .toInt()
    if (ageGrowth <= maxAge) {
      level.setBlockAndUpdate(pos, state.setValue(AGE, ageGrowth) as BlockState)
    }
    else {
      val remainingGrowth = ageGrowth - maxAge - 1
      level.setBlockAndUpdate(pos,
        ModBlocks.TOMATO_CROP.get()
          .defaultBlockState()
          .setValue(TomatoVineBlock.VINE_AGE, remainingGrowth) as BlockState)
    }
  }
}
