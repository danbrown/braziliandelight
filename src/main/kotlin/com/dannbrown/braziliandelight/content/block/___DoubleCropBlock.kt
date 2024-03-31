package com.dannbrown.braziliandelight.content.block

import net.minecraft.core.BlockPos
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
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import vectorwing.farmersdelight.common.registry.ModBlocks
import java.util.function.Supplier

class ___DoubleCropBlock(props: Properties, private val seedItem: Supplier<Item>): CropBlock(props), BonemealableBlock {
  companion object {
    val MAX_AGE: Int = 4
    val HALF: EnumProperty<DoubleBlockHalf> = BlockStateProperties.DOUBLE_BLOCK_HALF
    val UPPER_AGE: IntegerProperty = IntegerProperty.create("upper_age", 0, MAX_AGE)
    val SHAPE_BY_AGE = arrayOf<VoxelShape>(
      Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0),
      Block.box(3.0, 0.0, 3.0, 13.0, 10.0, 13.0),
      Block.box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0),
      Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0),
      Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
    )
  }

  init {
    registerDefaultState(defaultBlockState().setValue(ageProperty, 0).setValue(HALF, DoubleBlockHalf.LOWER))
  }

  override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
    pBuilder.add(ageProperty, HALF)
  }

  override fun getMaxAge(): Int {
    return MAX_AGE
  }

  override fun getAgeProperty(): IntegerProperty {
    return UPPER_AGE
  }

  override fun getShape(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pContext: CollisionContext): VoxelShape {
    return SHAPE_BY_AGE[pState.getValue(ageProperty)]
  }

  override fun getCloneItemStack(state: BlockState?, target: HitResult?, level: BlockGetter?, pos: BlockPos?, player: Player?): ItemStack {
    return ItemStack(seedItem.get())
  }

  override fun canSurvive(pState: BlockState, worldIn: LevelReader, pos: BlockPos): Boolean {
    val isLight = (worldIn.getRawBrightness(pos, 0) >= 8 || worldIn.canSeeSky(pos))
    if (pState.getValue(HALF) != DoubleBlockHalf.UPPER) {
      return super.canSurvive(pState, worldIn, pos)
    }
    else {
      val blockstate = worldIn.getBlockState(pos.below())
      return if (pState.block !== this) {
        super.canSurvive(pState, worldIn, pos)
      }
      else {
        (blockstate.`is`(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) && isLight
      }
    }
  }

  override fun mayPlaceOn(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos): Boolean {
    return pState.`is`(ModBlocks.RICH_SOIL_FARMLAND.get()) || pState.`is`(Blocks.FARMLAND)
  }

  override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? {
    val blockPos = pContext.clickedPos
    val level = pContext.level
    return if (blockPos.y < level.maxBuildHeight - 1 && level.getBlockState(blockPos.above()).canBeReplaced(pContext)) super.getStateForPlacement(pContext)
    else null
  }

  override fun setPlacedBy(pLevel: Level, pPos: BlockPos, pState: BlockState, pPlacer: LivingEntity?, pStack: ItemStack) {
    val blockPos = pPos.above()
    pLevel.setBlock(blockPos, copyWaterloggedFrom(pLevel, blockPos, defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER) as BlockState), 3)
  }

  fun placeAt(pLevel: LevelAccessor, pState: BlockState, pPos: BlockPos, pFlags: Int) {
    val blockPos = pPos.above()
    pLevel.setBlock(pPos, copyWaterloggedFrom(pLevel, pPos, pState.setValue(HALF, DoubleBlockHalf.LOWER)), pFlags)
    pLevel.setBlock(blockPos, copyWaterloggedFrom(pLevel, blockPos, pState.setValue(HALF, DoubleBlockHalf.UPPER)), pFlags)
  }

  private fun copyWaterloggedFrom(pLevel: LevelReader, pPos: BlockPos, pState: BlockState): BlockState {
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

  private fun preventCreativeDropFromBottomPart(pLevel: Level, pPos: BlockPos, pState: BlockState, pPlayer: Player?) {
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

}