package com.dannbrown.braziliandelight.content.block

import com.dannbrown.databoxlib.content.block.SimplePlantBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.common.ForgeHooks
import net.minecraftforge.common.IPlantable
import org.jetbrains.annotations.NotNull
import vectorwing.farmersdelight.common.registry.ModBlocks
import java.util.function.Supplier

class BuddingDoubleCropBlock(
  props: Properties,
  private val plantBlock: Supplier<out DoubleCropBlock2>,
  private val seedItem: Supplier<out Item>
): CropBlock(props), BonemealableBlock, IPlantable {
  companion object {
    const val MAX_AGE: Int = 4
    val AGE: IntegerProperty = BlockStateProperties.AGE_4
    val WATERLOGGED = BlockStateProperties.WATERLOGGED
    private val SHAPE_BY_AGE = arrayOf(
      box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
      box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
      box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
      box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
      box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    )
  }

  init {
    registerDefaultState(defaultBlockState().setValue(AGE, 0).setValue(WATERLOGGED, false))
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
    builder.add(WATERLOGGED, AGE)
  }


  override fun mayPlaceOn(blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos): Boolean {
    return blockState.`is`(ModBlocks.RICH_SOIL_FARMLAND.get()) || blockState.`is`(Blocks.FARMLAND)
  }


  override fun getMaxAge(): Int {
    return MAX_AGE
  }

  override fun getAgeProperty(): IntegerProperty {
    return AGE
  }



  override fun randomTick(blockState: BlockState, serverLevel: ServerLevel, blockPos: BlockPos, @NotNull random: RandomSource) {
    if (serverLevel.isAreaLoaded(blockPos, 1)) {
      if (serverLevel.getRawBrightness(blockPos, 0) >= 9) {
        val i: Int = blockState.getValue(ageProperty)
        if (i < this.maxAge) {
          val f: Float = getGrowthSpeed(this, serverLevel, blockPos)
          if (ForgeHooks.onCropsGrowPre(serverLevel, blockPos, blockState, random.nextInt((25.0f / f).toInt() + 1) == 0)) {
            handleGrowing(blockState, serverLevel, blockPos)
            ForgeHooks.onCropsGrowPost(serverLevel, blockPos, blockState)
          }
        }
      }
    }
  }


  override fun performBonemeal(
    @NotNull serverLevel: ServerLevel,
    @NotNull random: RandomSource,
    @NotNull blockPos: BlockPos,
    @NotNull blockState: BlockState
  ) {
    handleGrowing(blockState, serverLevel, blockPos, Mth.nextInt(serverLevel.random, 1, getMaxAge()))
  }

  private fun handleGrowing(blockState: BlockState, serverLevel: ServerLevel, blockPos: BlockPos, states: Int = 1) {
    if(!canSurvive(blockState, serverLevel, blockPos)) {
      serverLevel.destroyBlock(blockPos, true)
      return
    }

    val newStateQty: Int = Mth.clamp(blockState.getValue(getAgeProperty()) + states, 0, getMaxAge())

    // if the age is less than the max age, increment the age, otherwise grow the plant
    if (newStateQty < getMaxAge()) {
      serverLevel.setBlock(blockPos, blockState.setValue(getAgeProperty(), newStateQty), 3)
    }
    else {
      if (serverLevel.getBlockState(blockPos.above()) === Blocks.AIR.defaultBlockState()) {
        growTallGrass(serverLevel, blockPos)
      }
    }
  }

  private fun growTallGrass(@NotNull serverLevel: ServerLevel, @NotNull blockPos: BlockPos) {
    // if it cannot be grown as a double crop, return
    if(!DoubleCropBlock2.canBeGrown(serverLevel, blockPos)) return

    serverLevel.setBlock(
      blockPos,
      plantBlock.get().defaultBlockState().setValue(SimplePlantBlock.HALF, DoubleBlockHalf.LOWER),
      3
    )
    serverLevel.setBlock(
      blockPos.above(),
      plantBlock.get().defaultBlockState().setValue(SimplePlantBlock.HALF, DoubleBlockHalf.UPPER),
      3
    )
  }

  override fun canSurvive(state: BlockState, worldIn: LevelReader, pos: BlockPos): Boolean {
    return (worldIn.getRawBrightness(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && super.canSurvive(state, worldIn, pos)
  }

  override fun getCloneItemStack(state: BlockState?, target: HitResult?, level: BlockGetter?, pos: BlockPos?, player: Player?): ItemStack {
    return ItemStack(seedItem.get())
  }

  override fun getPlant(blockGetter: BlockGetter, blockPos: BlockPos): BlockState {
    return plantBlock.get().defaultBlockState()
  }

  override fun getShape(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pContext: CollisionContext): VoxelShape {
    return SHAPE_BY_AGE[pState.getValue(AGE)]
  }
}