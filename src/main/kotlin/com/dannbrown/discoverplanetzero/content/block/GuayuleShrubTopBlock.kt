package com.dannbrown.discoverplanetzero.content.block

import com.dannbrown.discoverplanetzero.init.AddonBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult



class GuayuleShrubTopBlock(p: Properties) : DoublePlantBlock(p) {
  override fun mayPlaceOn(state: BlockState, blockGetter: BlockGetter, blockPos: BlockPos): Boolean {
    return state.`is`(BlockTags.SAND)
  }

  override fun use(
    blockState: BlockState?,
    level: Level,
    blockPos: BlockPos,
    player: Player,
    interactionHand: InteractionHand,
    blockHitResult: BlockHitResult
  ): InteractionResult {
    val random: RandomSource = level.random
    val blockPosBelow = blockPos.below()
    val blockPosAbove = blockPos.above()

    val blockBelowIsGuayuleShrub = level.getBlockState(blockPosBelow).`is`(AddonBlocks.GUAYULE_SHRUB_TALL.get())
    val blockAboveIsGuayuleShrub = level.getBlockState(blockPosAbove).`is`(AddonBlocks.GUAYULE_SHRUB_TALL.get())


    if (!level.isClientSide) {
      if (blockBelowIsGuayuleShrub || blockAboveIsGuayuleShrub) {
        // play sound
        level.playSound(
          null,
          blockPos,
          SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES,
          SoundSource.BLOCKS,
          1.0f,
          0.8f + level.random.nextFloat() * 0.4f
        )
      }

      val d = blockPos.x.toDouble() + random.nextDouble()
      val e = blockPos.y.toDouble() + 1.0
      val f = blockPos.z.toDouble() + random.nextDouble()
      level.addParticle(ParticleTypes.HAPPY_VILLAGER, d, e, f, 0.0, 0.0, 0.0)

      // in case block above is guayule shrub, drop item and set block back to simple guayule shrub
      if (blockAboveIsGuayuleShrub) {
        // drop item
//        popResource(level, blockPos, ItemStack(DiscoverBlocks.GUAYULE_SHRUB.get(), 1))

        // set block back to simple guayule shrub, if is above or below
        level.setBlockAndUpdate(blockPos, AddonBlocks.GUAYULE_SHRUB.get().defaultBlockState())
        level.neighborChanged(blockPos, AddonBlocks.GUAYULE_SHRUB.get(), blockPosAbove)
        level.neighborChanged(blockPos, Blocks.AIR, blockPosBelow)

        return InteractionResult.SUCCESS
      }

      // in case block below is guayule shrub, drop item and set block back to simple guayule shrub
      if (blockBelowIsGuayuleShrub) {
        // drop item
//        popResource(level, blockPos, ItemStack(DiscoverBlocks.GUAYULE_SHRUB.get(), 1))

        // set block back to simple guayule shrub, if is above or below
        level.setBlockAndUpdate(blockPosBelow, AddonBlocks.GUAYULE_SHRUB.get().defaultBlockState())
        level.neighborChanged(blockPosBelow, AddonBlocks.GUAYULE_SHRUB.get(), blockPos)
        level.neighborChanged(blockPosBelow, Blocks.AIR, blockPos)

        return InteractionResult.SUCCESS
      }
    }

    return InteractionResult.PASS
  }

  override fun getCloneItemStack(blockGetter: BlockGetter, blockPos: BlockPos, blockState: BlockState): ItemStack {
    return ItemStack(AddonBlocks.GUAYULE_SHRUB.get())
  }
}

