package com.dannbrown.discoverplanetzero.content.block

import com.dannbrown.discover.content.block.SimplePlantBlock
import com.dannbrown.discover.content.block.SimpleSproutBlock
import com.dannbrown.discover.init._DiscoverBlocks
import com.dannbrown.discoverplanetzero.init.AddonBlocks
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import org.jetbrains.annotations.NotNull


class GuayuleShrubBlock(plantBlock: GuayuleShrubTopBlock, properties: Properties) : SimpleSproutBlock(properties), BonemealableBlock {
    val plantBlock = plantBlock

  override fun randomTick(blockState: BlockState, serverLevel: ServerLevel, blockPos: BlockPos, @NotNull random: RandomSource) {
    val i = random.nextInt(150)
    if (i == 1) {
      if (serverLevel.getBlockState(blockPos.above()) === Blocks.AIR.defaultBlockState()) {
        growShrub(serverLevel, blockPos)
      }
    }
  }

  override fun canSurvive(state: BlockState, worldIn: LevelReader, pos: BlockPos): Boolean {
    val blockpos = pos.below()
    return mayPlaceOn(worldIn.getBlockState(blockpos))
  }

  protected fun mayPlaceOn(state: BlockState): Boolean {
    return state.`is`(BlockTags.SAND)
  }

  fun growShrub(@NotNull serverLevel: ServerLevel, @NotNull blockPos: BlockPos) {
    serverLevel.setBlock(
      blockPos,
      plantBlock.defaultBlockState().setValue(SimplePlantBlock.HALF, DoubleBlockHalf.LOWER),
      3
    )
    serverLevel.setBlock(
      blockPos.above(),
      plantBlock.defaultBlockState().setValue(SimplePlantBlock.HALF, DoubleBlockHalf.UPPER),
      3
    )
  }

  override fun getCloneItemStack(blockGetter: BlockGetter, blockPos: BlockPos, blockState: BlockState): ItemStack {
    return ItemStack(AddonBlocks.GUAYULE_SHRUB.get())
  }

  override fun performBonemeal(
    @NotNull serverLevel: ServerLevel,
    @NotNull random: RandomSource,
    @NotNull blockPos: BlockPos,
    @NotNull blockState: BlockState
  ) {
    if (serverLevel.getBlockState(blockPos.above()).isAir && canSurvive(blockState, serverLevel, blockPos)) {
      growShrub(serverLevel, blockPos)
    }
  }
}

