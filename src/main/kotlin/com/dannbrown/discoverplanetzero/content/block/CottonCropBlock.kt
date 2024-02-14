package com.dannbrown.discoverplanetzero.content.block

import com.dannbrown.discoverplanetzero.init.AddonItems
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
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.PushReaction
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.common.ForgeHooks

class CottonCropBlock(properties: Properties?) : CropBlock(properties) {
  init {
    registerDefaultState(stateDefinition.any().setValue(ageProperty, 0))
  }

  override fun use(
    state: BlockState,
    level: Level,
    pos: BlockPos,
    player: Player,
    hand: InteractionHand,
    hit: BlockHitResult
  ): InteractionResult {
    val age = state.getValue(ageProperty)
    val isMature = age == maxAge
    return if (!isMature && player.getItemInHand(hand).`is`(Items.BONE_MEAL)) {
      InteractionResult.PASS
    } else if (isMature) {
      val quantity = 1 + level.random.nextInt(2)
      popResource(level, pos, ItemStack(AddonItems.COTTON_PULP.get(), quantity))
      if (level.random.nextFloat() < 0.1) {
        popResource(level, pos, ItemStack(AddonItems.COTTON_SEEDS.get()))
      }
      level.playSound(
        null,
        pos,
        SoundEvents.ITEM_PICKUP,
        SoundSource.BLOCKS,
        1.0f,
        0.8f + level.random.nextFloat() * 0.4f
      )
      level.setBlock(pos, state.setValue(ageProperty, 0), 2)
      InteractionResult.SUCCESS
    } else {
      super.use(state, level, pos, player, hand, hit)
    }
  }

  override fun isRandomlyTicking(state: BlockState): Boolean {
    return true
  }

  override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
    if (!level.isAreaLoaded(pos, 1)) return
    if (level.getRawBrightness(pos, 0) >= 9) {
      val age = getAge(state)
      if (age < this.maxAge) {
        val speed = getGrowthSpeed(this, level, pos)
        if (ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt((25.0f / speed).toInt() + 1) == 0)) {
          level.setBlock(pos, state.setValue(ageProperty, age + 1), 2)
          ForgeHooks.onCropsGrowPost(level, pos, state)
        }
      }
    }
  }



  override fun getStateForAge(age: Int): BlockState {
    return defaultBlockState().setValue(this.ageProperty, age)
  }

  override fun getAgeProperty(): IntegerProperty {
    return CROP_AGE
  }

  override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
    return SHAPE
  }

  override fun getMaxAge(): Int {
    return 3
  }

  override fun getBaseSeedId(): ItemLike {
    return AddonItems.COTTON_SEEDS.get()
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
    builder.add(CROP_AGE)
  }

  override fun getBonemealAgeIncrease(level: Level): Int {
    return super.getBonemealAgeIncrease(level) / 2
  }

  override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
    var newAge = getAge(state) + getBonemealAgeIncrease(level)
    val maxAge = this.maxAge
    if (newAge > maxAge) {
      newAge = maxAge
    }
    level.setBlockAndUpdate(pos, state.setValue(ageProperty, newAge))
  }


  override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
    return super.canSurvive(state, level, pos)
  }

  fun hasGoodCropConditions(level: LevelReader, pos: BlockPos?): Boolean {
    return level.getRawBrightness(pos, 0) >= 8 || level.canSeeSky(pos)
  }

  override fun playerDestroy(
    level: Level,
    player: Player,
    pos: BlockPos,
    state: BlockState,
    blockEntity: BlockEntity?,
    stack: ItemStack
  ) {
    super.playerDestroy(level, player, pos, state, blockEntity, stack)
  }

  override fun updateShape(
    state: BlockState,
    facing: Direction,
    facingState: BlockState,
    level: LevelAccessor,
    currentPos: BlockPos,
    facingPos: BlockPos
  ): BlockState {
    if (!state.canSurvive(level, currentPos)) {
      level.scheduleTick(currentPos, this, 1)
    }
    return state
  }

  override fun getPistonPushReaction(state: BlockState): PushReaction {
    return PushReaction.NORMAL
  }

  override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
    if (!state.canSurvive(level, pos)) {
      level.destroyBlock(pos, true)
    }
  }

  companion object {
    val CROP_AGE = BlockStateProperties.AGE_3
    private val SHAPE = box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
  }
}