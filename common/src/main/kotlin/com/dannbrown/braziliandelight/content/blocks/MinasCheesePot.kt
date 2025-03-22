package com.dannbrown.braziliandelight.content.blocks

import com.dannbrown.braziliandelight.FarmersCompat
import com.dannbrown.braziliandelight.init.ModBlocks
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.CakeBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.phys.BlockHitResult

class MinasCheesePot(props: Properties) : MilkPotBlock(props) {
  override fun use(
    pState: BlockState,
    pLevel: Level,
    pPos: BlockPos,
    pPlayer: Player,
    pHand: InteractionHand,
    pHit: BlockHitResult
  ): InteractionResult {
    // if clicked with an empty hand, drop a cheese item, and put an empty pot in place
    if (pPlayer.getItemInHand(pHand).isEmpty) {
      if (pLevel.isClientSide) pLevel.playSound(
        pPlayer,
        pPos,
        SoundEvents.SLIME_BLOCK_BREAK,
        SoundSource.BLOCKS,
        1.0f,
        1.0f
      )
      if (!pLevel.isClientSide) {
        pLevel.setBlockAndUpdate(
          pPos,
          FarmersCompat.getCookingPot().defaultBlockState()
            .setValue(BlockStateProperties.HORIZONTAL_FACING, pState.getValue(FACING).opposite)
        )
        popResource(pLevel, pPos, ItemStack(ModBlocks.MINAS_CHEESE.get()))
      }
      return InteractionResult.SUCCESS
    }

    return InteractionResult.PASS
  }
}