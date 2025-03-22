package com.dannbrown.braziliandelight.content.blocks

import com.dannbrown.braziliandelight.FarmersCompat
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.effect.MobEffectInstance
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
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Supplier

class PieBlock(props: Properties, private val pieSlice: Supplier<Item>) : Block(props) {
  companion object {
    val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
    val BITES: IntegerProperty = IntegerProperty.create("bites", 0, 3)
    val SHAPE: VoxelShape = Block.box(2.0, 0.0, 2.0, 14.0, 4.0, 14.0)
  }

  init {
    registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BITES, 0))
  }

  fun getPieSliceItem(): ItemStack = ItemStack(pieSlice.get())

  fun getMaxBites(): Int = 4

  override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
    return SHAPE
  }

  override fun getStateForPlacement(context: BlockPlaceContext): BlockState {
    return defaultBlockState().setValue(FACING, context.horizontalDirection)
  }

  override fun use(
    state: BlockState,
    level: Level,
    pos: BlockPos,
    player: Player,
    hand: InteractionHand,
    hit: BlockHitResult
  ): InteractionResult {
    val heldStack = player.getItemInHand(hand)
    if (level.isClientSide) {
      if (heldStack.`is`(FarmersCompat.TAGS.KNIVES)) {
        return cutSlice(level, pos, state, player)
      }
      if (consumeBite(level, pos, state, player) == InteractionResult.SUCCESS) {
        return InteractionResult.SUCCESS
      }
      if (heldStack.isEmpty) {
        return InteractionResult.CONSUME
      }
    }
    return if (heldStack.`is`(FarmersCompat.TAGS.KNIVES)) cutSlice(level, pos, state, player) else consumeBite(
      level,
      pos,
      state,
      player
    )
  }

  private fun consumeBite(level: Level, pos: BlockPos, state: BlockState, player: Player): InteractionResult {
    if (!player.canEat(false)) return InteractionResult.PASS

    val sliceStack = getPieSliceItem()
    val sliceFood = sliceStack.item.foodProperties

    player.foodData.eat(sliceStack.item, sliceStack)

    sliceFood?.effects?.forEach { data ->
      val effect = data.first
      val probability = data.second
      if (!level.isClientSide && effect != null && level.random.nextFloat() < probability) {
        player.addEffect(MobEffectInstance(effect))
      }
    }

    val bites = state.getValue(BITES)
    if (bites < getMaxBites() - 1) {
      level.setBlock(pos, state.setValue(BITES, bites + 1), 3)
    } else {
      level.removeBlock(pos, false)
    }
    level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8f, 0.8f)
    return InteractionResult.SUCCESS
  }

  private fun cutSlice(level: Level, pos: BlockPos, state: BlockState, player: Player): InteractionResult {
    val bites = state.getValue(BITES)
    if (bites < getMaxBites() - 1) {
      level.setBlock(pos, state.setValue(BITES, bites + 1), 3)
    } else {
      level.removeBlock(pos, false)
    }

    val direction = player.direction.opposite
    FarmersCompat.spawnItemEntity(
      level, getPieSliceItem(), pos.x + 0.5, pos.y + 0.3, pos.z + 0.5,
      direction.stepX * 0.15, 0.05, direction.stepZ * 0.15
    )
    level.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8f, 0.8f)
    return InteractionResult.SUCCESS
  }

  override fun updateShape(
    stateIn: BlockState,
    facing: Direction,
    facingState: BlockState,
    level: LevelAccessor,
    currentPos: BlockPos,
    facingPos: BlockPos
  ): BlockState {
    return if (facing == Direction.DOWN && !stateIn.canSurvive(
        level,
        currentPos
      )
    ) Blocks.AIR.defaultBlockState() else super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos)
  }

  override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
    return level.getBlockState(pos.below()).isSolid
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
    builder.add(FACING, BITES)
  }

  override fun getAnalogOutputSignal(blockState: BlockState, level: Level, pos: BlockPos): Int {
    return getMaxBites() - blockState.getValue(BITES)
  }

  override fun hasAnalogOutputSignal(state: BlockState): Boolean {
    return true
  }

  override fun isPathfindable(
    state: BlockState,
    level: BlockGetter,
    pos: BlockPos,
    type: PathComputationType
  ): Boolean {
    return false
  }
}
