package com.dannbrown.braziliandelight.content.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.Item
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Supplier

class LoveAppleTrayBlock(
  props: Properties,
  private val sliceItem: Supplier<Item>,
  private val requireServing: Boolean = true,
  private val servingItem: Supplier<Item>? = null
): PlaceableFoodBlock(props, sliceItem, requireServing, servingItem) {
  companion object {
    const val MAX_PARTS = 6
    val PARTS: IntegerProperty = IntegerProperty.create("parts", 0, MAX_PARTS)
    val LOVE_APPLE_SHAPE_Z: VoxelShape = box(3.0, 0.0, 1.5, 13.0, 11.0, 14.5);
    val LOVE_APPLE_SHAPE_X: VoxelShape = box(1.5, 0.0, 3.0, 14.5, 11.0, 13.0);
    val SHAPE_Z: VoxelShape = Shapes.joinUnoptimized(PLATE_SHAPE, LOVE_APPLE_SHAPE_Z, BooleanOp.OR)
    val SHAPE_X: VoxelShape = Shapes.joinUnoptimized(PLATE_SHAPE, LOVE_APPLE_SHAPE_X, BooleanOp.OR)
  }

  init {
    registerDefaultState(defaultBlockState().setValue(USES, 0).setValue(PARTS, 0).setValue(FACING, Direction.NORTH))
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
    builder.add(USES, PARTS, FACING)
  }

  override fun getMaxUses(): Int {
    return MAX_PARTS
  }

  override fun getUses(state: BlockState): Int {
    return state.getValue(PARTS)
  }

  override fun setUses(state: BlockState, uses: Int): BlockState {
    return state.setValue(PARTS, uses)
  }

  override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
    return if (getUses(state) == getMaxUses()) PLATE_SHAPE else (if (state.getValue(FACING).axis == Direction.Axis.X) SHAPE_X else SHAPE_Z)
  }
}