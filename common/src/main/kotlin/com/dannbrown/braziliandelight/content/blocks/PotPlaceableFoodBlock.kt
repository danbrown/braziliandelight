package com.dannbrown.braziliandelight.content.blocks

import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Supplier

class PotPlaceableFoodBlock(
  props: Properties,
  private val sliceItem: Supplier<Item>,
  private val requireServing: Boolean = false,
  private val servingItem: Supplier<Item>? = Supplier { Items.BOWL }
) : PlaceableFoodBlock(props, sliceItem, requireServing, servingItem) {
  override fun getPlateSound(): SoundEvent {
    return SoundEvents.LANTERN_BREAK
  }

  override fun getFoodSound(): SoundEvent {
    return SoundEvents.GENERIC_DRINK
  }

  override fun getShape(
    state: BlockState,
    level: BlockGetter,
    pos: BlockPos,
    context: CollisionContext
  ): VoxelShape {
    return POT_SHAPE
  }
}