package com.dannbrown.braziliandelight.content.block

import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import vectorwing.farmersdelight.common.block.TomatoVineBlock
import vectorwing.farmersdelight.common.registry.ModItems
import vectorwing.farmersdelight.common.registry.ModSounds
import java.util.function.Supplier

class VineCropBlock(props: Properties, private val dropItem: Supplier<ItemLike>, private val seedItem: Supplier<ItemLike>, private val buddingBlock: Supplier<BuddingVineCropBlock>): TomatoVineBlock(props) {
  override fun getBaseSeedId(): ItemLike {
    return seedItem.get()
  }
  override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
    val belowPos = pos.below()
    val belowState = level.getBlockState(belowPos)

    if (state.getValue(ROPELOGGED)) {
      return belowState.`is`(state.block) && hasGoodCropConditions(level, pos)
    }

    return super.canSurvive(state, level, pos)
  }
  override fun use(state: BlockState, level: Level, pos: BlockPos, player: Player, hand: InteractionHand, hit: BlockHitResult): InteractionResult {
    val age = state.getValue(ageProperty)
    val isMature = age == maxAge

    // If the player is holding bone meal, and the crop is not mature, pass the interaction to the super class
    if (!isMature && player.getItemInHand(hand).`is`(Items.BONE_MEAL)) {
      return InteractionResult.PASS
    }

    // crop is mature, drop the crop and reset the block
    else if (isMature) {
      val quantity: Int = 1 + level.random.nextInt(2)
      popResource(level, pos, ItemStack(dropItem.get(), quantity))
      level.playSound(null, pos, ModSounds.ITEM_TOMATO_PICK_FROM_BUSH.get(), SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4f)
      level.setBlock(pos, state.setValue(ageProperty, 0), 2)
      return InteractionResult.SUCCESS
    }
    else {
      return super.use(state, level, pos, player, hand, hit)
    }
  }
}