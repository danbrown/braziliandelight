package com.dannbrown.braziliandelight.content.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.HitResult
import vectorwing.farmersdelight.common.block.BuddingBushBlock
import vectorwing.farmersdelight.common.block.BuddingTomatoBlock
import vectorwing.farmersdelight.common.block.TomatoVineBlock
import java.util.function.Supplier
import kotlin.math.min

class BuddingVineCropBlock(props: Properties, private val cropBlock: Supplier<VineCropBlock>, private val seedItem: Supplier<Item>): BuddingTomatoBlock(props) {
  override fun getPlant(world: BlockGetter, pos: BlockPos): BlockState {
    return cropBlock.get().defaultBlockState()
  }

  override fun getCloneItemStack(state: BlockState?, target: HitResult?, level: BlockGetter?, pos: BlockPos?, player: Player?): ItemStack {
    return ItemStack(seedItem.get())
  }

  override fun updateShape(state: BlockState, facing: Direction, facingState: BlockState, level: LevelAccessor, currentPos: BlockPos, facingPos: BlockPos): BlockState {
    if (state.getValue(BuddingBushBlock.AGE) == 4) {
      level.setBlock(currentPos, cropBlock.get().defaultBlockState(), 3)
    }
    return super.updateShape(state, facing, facingState, level, currentPos, facingPos)
  }

  override fun growPastMaxAge(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
    level.setBlockAndUpdate(pos, cropBlock.get().defaultBlockState())
  }

  override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
    val maxAge = maxAge
    val ageGrowth = min((getAge(state) + getBonemealAgeIncrease(level)).toDouble(), 7.0).toInt()
    if (ageGrowth <= maxAge) {
      level.setBlockAndUpdate(pos, state.setValue(AGE, ageGrowth))
    }
    else {
      val remainingGrowth = ageGrowth - maxAge - 1
      level.setBlockAndUpdate(pos, cropBlock.get().defaultBlockState().setValue(TomatoVineBlock.VINE_AGE, remainingGrowth))
    }
  }

}