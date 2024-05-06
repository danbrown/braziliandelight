package com.dannbrown.braziliandelight.content.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class CoconutBlock(props: Properties) : Block(props) {
  private val SHAPE: VoxelShape = Shapes.box(0.2, 0.0, 0.2, 0.8, 0.9, 0.8)

  init {
    registerDefaultState(this.defaultBlockState().setValue(STACK, 1))
  }

  override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
    return SHAPE
  }

  override fun use(state: BlockState, world: Level, pos: BlockPos, player: Player, hand: InteractionHand, hit: BlockHitResult): InteractionResult {
    val stack: ItemStack = player.getItemInHand(hand)
    if (stack.item === this.asItem()) {
      if (state.block is CoconutBlock && state.getValue(STACK) < 3) {
        world.setBlock(pos, state.setValue(STACK, state.getValue(STACK) + 1), UPDATE_ALL)
        if (!player.isCreative) {
          stack.shrink(1)
        }
        return InteractionResult.SUCCESS
      }
    }
    else if (stack.isEmpty && !player.offhandItem.isEmpty) {
      if (!player.isShiftKeyDown) {
        return InteractionResult.PASS
      }
      if (state.getValue(STACK) >= 1) {
        return InteractionResult.PASS
      }
      if (!world.isClientSide) {
        world.setBlockAndUpdate(pos, this.defaultBlockState().setValue(STACK, 1))
        player.offhandItem.shrink(1)
      }
      return InteractionResult.SUCCESS
    }
    else if (stack.isEmpty) {
      if (state.getValue(STACK) > 1) {
        world.setBlock(pos, state.setValue(STACK, state.getValue(STACK) - 1), UPDATE_ALL)
      }
      else if (state.getValue(STACK) == 1) {
        world.destroyBlock(pos, false)
      }
      player.addItem(this.asItem()
        .getDefaultInstance())
      return InteractionResult.SUCCESS
    }
    return super.use(state, world, pos, player, hand, hit)
  }

  override fun skipRendering(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
    return stateFrom.`is`(this) || super.skipRendering(state, stateFrom, direction)
  }

  override fun tick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
    if (!state.canSurvive(world, pos)) {
      world.destroyBlock(pos, true)
    }
  }

  override fun updateShape(state: BlockState, direction: Direction, neighborState: BlockState, world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos): BlockState {
    if (!state.canSurvive(world, pos)) {
      world.scheduleTick(pos, this, 1)
    }
    return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
  }

  override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
    val blockPos = pos.below()
    return world.getBlockState(blockPos).isFaceSturdy(world, blockPos, Direction.UP)
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
    builder.add(STACK)
  }

  companion object {
    val STACK: IntegerProperty = IntegerProperty.create("stack", 1, 3)
  }
}