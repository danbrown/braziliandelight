package com.dannbrown.braziliandelight.content.block

import com.dannbrown.braziliandelight.init.AddonBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
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
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import vectorwing.farmersdelight.common.registry.ModBlocks

open class MilkPotBlock(props: Properties): Block(props) {
  companion object {
    val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
    val MIXES: IntegerProperty = IntegerProperty.create("mixes", 0, 3)
  }

  init {
    registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(MIXES, 0))
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
    builder.add(FACING, MIXES)
  }

  override fun getStateForPlacement(context: BlockPlaceContext): BlockState {
    return defaultBlockState().setValue(FACING, context.horizontalDirection) as BlockState
  }


  override fun getShape(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pContext: CollisionContext): VoxelShape {
    return PlaceableFoodBlock.POT_SHAPE
  }

  override fun canSurvive(pState: BlockState, pLevel: LevelReader, pPos: BlockPos): Boolean {
    return pLevel.getBlockState(pPos.below()).isSolid()
  }

  override fun updateShape(stateIn: BlockState, facing: Direction, facingState: BlockState, level: LevelAccessor, currentPos: BlockPos, facingPos: BlockPos): BlockState {
    return if (facing == Direction.DOWN && !stateIn.canSurvive(level, currentPos)) Blocks.AIR.defaultBlockState() else super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos)
  }


  override fun getCloneItemStack(state: BlockState?, target: HitResult?, level: BlockGetter?, pos: BlockPos?, player: Player?): ItemStack {
    return ItemStack(ModBlocks.COOKING_POT.get().asItem())
  }

  override fun use(pState: BlockState, pLevel: Level, pPos: BlockPos, pPlayer: Player, pHand: InteractionHand, pHit: BlockHitResult): InteractionResult {
    val itemInHand = pPlayer.getItemInHand(pHand)

    // if clicked with a bucket, replace the hand item with a milk bucket and replace the block with an empty cooking pot, play a sound
    if (itemInHand.item === Items.BUCKET) {
      if (pLevel.isClientSide) pLevel.playSound(pPlayer, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f)
      if (!pLevel.isClientSide) {
        pLevel.setBlockAndUpdate(pPos, ModBlocks.COOKING_POT.get().defaultBlockState())
        if (!pPlayer.isCreative) {
          pPlayer.setItemInHand(pHand, ItemStack(Items.MILK_BUCKET))
        }
      }
      return InteractionResult.SUCCESS
    }

    // if clicked with empty hand, replace the block with its mix level increased by 1 and play a sound, if it reaches 3, replace the block with a heavy cream pot
    if (itemInHand.isEmpty) {
      if (pLevel.isClientSide)  pLevel.playSound(pPlayer, pPos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0f, 1.0f)
      if (!pLevel.isClientSide) {
        if(pState.getValue(MIXES) < 3) {
          pLevel.setBlockAndUpdate(pPos, pState.setValue(MIXES, pState.getValue(MIXES) + 1))
        } else {
          pLevel.setBlockAndUpdate(pPos, AddonBlocks.HEAVY_CREAM_POT.get().defaultBlockState().setValue(FACING, pState.getValue(FACING)))
        }
      }
      return InteractionResult.SUCCESS
    }

    return InteractionResult.PASS
  }
}