package com.dannbrown.braziliandelight.content.block

import com.dannbrown.databoxlib.content.block.GenericTallGrassBlock
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
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
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
  private val plantBlock: Supplier<out DoubleCropBlock>,
  private val seedItem: Supplier<out Item>
): GenericTallGrassBlock(plantBlock, props, null), BonemealableBlock, IPlantable {
  companion object {
    const val MAX_AGE: Int = 4
    val AGE: IntegerProperty = IntegerProperty.create("age", 0, MAX_AGE)
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

  fun getMaxAge(): Int {
    return MAX_AGE
  }

  fun getAgeProperty(): IntegerProperty {
    return AGE
  }

  override fun mayPlaceOn(blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos): Boolean {
    return blockState.`is`(ModBlocks.RICH_SOIL_FARMLAND.get()) || blockState.`is`(Blocks.FARMLAND)
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

  override fun performBonemeal(
    @NotNull serverLevel: ServerLevel,
    @NotNull random: RandomSource,
    @NotNull blockPos: BlockPos,
    @NotNull blockState: BlockState
  ) {
    handleGrowing(blockState, serverLevel, blockPos, Mth.nextInt(serverLevel.random, 1, getMaxAge()))
  }

  fun handleGrowing(blockState: BlockState, serverLevel: ServerLevel, blockPos: BlockPos, states: Int = 1) {
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

  override fun growTallGrass(@NotNull serverLevel: ServerLevel, @NotNull blockPos: BlockPos) {
    // if it cannot be grown as a double crop, return
    if(!DoubleCropBlock.canBeGrown(serverLevel, blockPos)) return

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