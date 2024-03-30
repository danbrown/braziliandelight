package com.dannbrown.braziliandelight.content.block

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import vectorwing.farmersdelight.common.registry.ModBlocks
import java.util.function.Supplier

class NormalCropBlock(props: Properties, private val seedItem: Supplier<Item>): CropBlock(props) {
  private val SHAPE_BY_AGE = arrayOf<VoxelShape>(
    Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
    Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
    Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
    Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
    Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
    Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
    Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
    Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0)
  )

  override fun getPlant(level: BlockGetter, pos: BlockPos): BlockState {
    return this.defaultBlockState()
  }

  override fun getBaseSeedId(): ItemLike {
    return seedItem.get()
  }

  override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
    return SHAPE_BY_AGE[state.getValue(this.ageProperty)]
  }

  override fun getCloneItemStack(state: BlockState?, target: HitResult?, level: BlockGetter?, pos: BlockPos?, player: Player?): ItemStack {
    return ItemStack(seedItem.get())
  }
}