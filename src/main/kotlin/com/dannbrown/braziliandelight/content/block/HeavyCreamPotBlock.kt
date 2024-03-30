package com.dannbrown.braziliandelight.content.block

import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonItems
import com.dannbrown.braziliandelight.init.AddonTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult
import vectorwing.farmersdelight.common.block.CookingPotBlock
import vectorwing.farmersdelight.common.registry.ModBlocks
import java.util.Random

class HeavyCreamPotBlock(props: Properties): MilkPotBlock(props) {
  companion object {
    val HAS_COAGULANT: BooleanProperty = BooleanProperty.create("has_coagulant")
    val HAS_SALT: BooleanProperty = BooleanProperty.create("has_salt")
  }

  init {
    registerDefaultState(defaultBlockState()
      .setValue(FACING, Direction.NORTH)
      .setValue(MIXES, 0)
      .setValue(HAS_COAGULANT, false)
      .setValue(HAS_SALT, false))
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
    builder.add(FACING, MIXES, HAS_COAGULANT, HAS_SALT)
  }

  override fun use(pState: BlockState, pLevel: Level, pPos: BlockPos, pPlayer: Player, pHand: InteractionHand, pHit: BlockHitResult): InteractionResult {
    val itemInHand = pPlayer.getItemInHand(pHand)

    // if clicked with a bucket, replace the hand item with a heavy cream bucket and replace the block with an empty cooking pot, play a sound
    if (itemInHand.item === Items.BUCKET) {
      if (pLevel.isClientSide) pLevel.playSound(pPlayer, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f)
      if (!pLevel.isClientSide) {
        pLevel.setBlockAndUpdate(pPos, ModBlocks.COOKING_POT.get().defaultBlockState().setValue(CookingPotBlock.FACING, pState.getValue(FACING).opposite))
        if (!pPlayer.isCreative) {
          pPlayer.setItemInHand(pHand, ItemStack(AddonItems.HEAVY_CREAM_BUCKET.get()))
        }
      }
      return InteractionResult.SUCCESS
    }

    // if item in hand has a salt tag, set the block's salt property to true, consume 1 salt from hand
    if (itemInHand.`is`(AddonTags.ITEM.SALT)) {
      if(pState.getValue(HAS_SALT)) return InteractionResult.PASS
      if (pLevel.isClientSide) pLevel.playSound(pPlayer, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f)
      if (!pLevel.isClientSide) {

        // check if has coagulant in state, if so put a cheese pot, if not put a heavy cream pot update the block state
        if(pState.getValue(HAS_COAGULANT)) {
          pLevel.setBlockAndUpdate(pPos, AddonBlocks.MINAS_CHEESE_POT.get().defaultBlockState().setValue(CookingPotBlock.FACING, pState.getValue(FACING)))
        } else {
          pLevel.setBlockAndUpdate(pPos, pState.setValue(HAS_SALT, true))
        }

        if (!pPlayer.isCreative) {
          itemInHand.shrink(1)
        }
      }
      return InteractionResult.SUCCESS
    }

    // if item in hand has a coagulant tag, set the block's coagulant property to true
    if (itemInHand.`is`(AddonTags.ITEM.CHEESE_COAGULANT)) {
      if(pState.getValue(HAS_COAGULANT)) return InteractionResult.PASS
      if (pLevel.isClientSide) pLevel.playSound(pPlayer, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f)
      if (!pLevel.isClientSide) {

        // check if has salt in state, if so put a cheese pot, if not put a heavy cream pot update the block state
        if(pState.getValue(HAS_SALT)) {
          pLevel.setBlockAndUpdate(pPos, AddonBlocks.MINAS_CHEESE_POT.get().defaultBlockState().setValue(CookingPotBlock.FACING, pState.getValue(FACING)))
        } else {
          pLevel.setBlockAndUpdate(pPos, pState.setValue(HAS_COAGULANT, true))
        }

        if (!pPlayer.isCreative) {
          itemInHand.shrink(1)
        }
      }
      return InteractionResult.SUCCESS
    }

    return InteractionResult.PASS
  }



}