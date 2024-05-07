package com.dannbrown.braziliandelight.content.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.phys.HitResult
import java.util.function.Supplier

class BuddingLeavesBlock(props: Properties, private val fruitBlock: Supplier<FaceAttachedHorizontalDirectionalBlock>): CoconutLeavesBlock(props), BonemealableBlock {
  override fun isFlammable(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Boolean {
    return true
  }

  override fun getFlammability(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Int {
    return 60
  }

  override fun getFireSpreadSpeed(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Int {
    return 30
  }

  override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
    val blockBelow = level.getBlockState(pos.below())
    // check if block below is air
    if (random.nextInt(100) % 20 == 0) { // 5% chance
      if (blockBelow.isAir) {
      // place fruit block below
      level.setBlock(pos.below(), fruitBlock.get().defaultBlockState().setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.CEILING), Block.UPDATE_CLIENTS)
      }
    } else{
      super.randomTick(state, level, pos, random)
    }
  }

  override fun isRandomlyTicking(pState: BlockState): Boolean {
    return true
  }

  override fun getCloneItemStack(state: BlockState?, target: HitResult?, level: BlockGetter?, pos: BlockPos?, player: Player?): ItemStack {
    return fruitBlock.get().getCloneItemStack(state, target, level, pos, player)
  }
  override fun isValidBonemealTarget(p0: LevelReader, p1: BlockPos, p2: BlockState, p3: Boolean): Boolean {
    return p0.getBlockState(p1.below()).isAir
  }
  override fun isBonemealSuccess(p0: Level, p1: RandomSource, p2: BlockPos, p3: BlockState): Boolean {
    return p0.getBlockState(p2.below()).isAir
  }
  override fun performBonemeal(p0: ServerLevel, p1: RandomSource, p2: BlockPos, p3: BlockState) {
    if (p0.getBlockState(p2.below()).isAir) {
      p0.setBlock(p2.below(), fruitBlock.get().defaultBlockState().setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.CEILING), Block.UPDATE_CLIENTS)
    }
  }
}