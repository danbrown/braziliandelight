package com.dannbrown.braziliandelight.content.block

import com.mojang.datafixers.util.Pair
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
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
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import vectorwing.farmersdelight.common.tag.ModTags
import vectorwing.farmersdelight.common.utility.ItemUtils
import java.util.function.Supplier

class PuddingBlock(props: Properties, private val sliceItem: Supplier<Item>, private val plateItem: Supplier<Item> = Supplier { Items.BOWL }): Block(props) {

  companion object {
    val MAX_BITES = 4
    val BITES: IntegerProperty = IntegerProperty.create("bites", 0, MAX_BITES)
    val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING;
    val FOOD_SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 6.0, 14.0);
    val PLATE_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0)
    val SHAPE: VoxelShape = Shapes.joinUnoptimized(PLATE_SHAPE, FOOD_SHAPE, BooleanOp.OR)
  }

  init {
    registerDefaultState(defaultBlockState().setValue(BITES, 0).setValue(FACING, Direction.NORTH))
  }

  override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
    builder.add(BITES, FACING)
  }

  fun getMaxBites(): Int {
    return MAX_BITES
  }

  override fun use(state: BlockState, level: Level, pos: BlockPos, player: Player, hand: InteractionHand, hit: BlockHitResult): InteractionResult {
    val heldStack = player.getItemInHand(hand)
    if (level.isClientSide) {
      if (heldStack.`is`(ModTags.KNIVES)) {
        return this.cutSlice(level, pos, state, player)
      }

      if (this.consumeBite(level, pos, state, player) == InteractionResult.SUCCESS) {
        return InteractionResult.SUCCESS
      }

      if (heldStack.isEmpty) {
        return InteractionResult.CONSUME
      }
    }

    return if (heldStack.`is`(ModTags.KNIVES)) this.cutSlice(level, pos, state, player) else this.consumeBite(level, pos, state, player)
  }


  fun consumeBite(level: Level, pos: BlockPos, state: BlockState, playerIn: Player): InteractionResult {
    if (!playerIn.canEat(false)) {
      return InteractionResult.PASS
    }
    else {
      val sliceStack = ItemStack(this.sliceItem.get())
      val sliceFood = sliceStack.item.foodProperties
      playerIn.foodData.eat(sliceStack.item, sliceStack)
      if (sliceStack.item.isEdible && sliceFood != null) {
        val var7 = sliceFood.effects.iterator()

        while (var7.hasNext()) {
          val pair: Pair<MobEffectInstance, Float> = var7.next()
          if (!level.isClientSide && pair.first != null && level.random.nextFloat() < (pair.second as Float)) {
            playerIn.addEffect(MobEffectInstance(pair.first))
          }
        }
      }
      val bites = state.getValue(BITES) as Int
      if (bites < getMaxBites()) {
        level.setBlock(pos, state.setValue(BITES, bites + 1) as BlockState, 3)
      }
      else {
        ItemUtils.spawnItemEntity(level, ItemStack(this.plateItem.get()), pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.3, pos.z.toDouble() + 0.5, 0.0, 0.05, 0.0)
        level.removeBlock(pos, false)
        level.playSound(null as Player?, pos, SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 0.8f, 0.8f)
        return InteractionResult.SUCCESS
      }

      level.playSound(null as Player?, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8f, 0.8f)
      return InteractionResult.SUCCESS
    }
  }

  fun cutSlice(level: Level, pos: BlockPos, state: BlockState, player: Player): InteractionResult {
    val bites = state.getValue(BITES) as Int

    val itemX = pos.x.toDouble() + 0.5
    val itemY = pos.y.toDouble() + 0.3
    val itemZ = pos.z.toDouble() + 0.5
    val direction = player.direction.opposite
    val motionX = direction.stepX.toDouble() * 0.15
    val motionY = 0.05
    val motionZ = direction.stepZ.toDouble() * 0.15

    if (bites < getMaxBites()) {
      level.setBlock(pos, state.setValue(BITES, bites + 1) as BlockState, 3)
    } else {
      ItemUtils.spawnItemEntity(level, ItemStack(this.plateItem.get()), itemX, itemY, itemZ, motionX, motionY, motionZ)
      level.removeBlock(pos, false)
      level.playSound(null as Player?, pos, SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 0.8f, 0.8f)
      return InteractionResult.SUCCESS
    }
    ItemUtils.spawnItemEntity(level, ItemStack(this.sliceItem.get()), itemX, itemY, itemZ, motionX, motionY, motionZ)
    level.playSound(null as Player?, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8f, 0.8f)
    return InteractionResult.SUCCESS
  }

  override fun isPathfindable(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pType: PathComputationType): Boolean {
    return false
  }

  override fun hasAnalogOutputSignal(pState: BlockState): Boolean {
    return true
  }

  override fun getAnalogOutputSignal(pState: BlockState, pLevel: Level, pPos: BlockPos): Int {
    return this.getMaxBites() - pState.getValue(BITES)
  }

  override fun canSurvive(pState: BlockState, pLevel: LevelReader, pPos: BlockPos): Boolean {
    return pLevel.getBlockState(pPos.below()).isSolid()
  }

  override fun updateShape(stateIn: BlockState, facing: Direction, facingState: BlockState, level: LevelAccessor, currentPos: BlockPos, facingPos: BlockPos): BlockState {
    return if (facing == Direction.DOWN && !stateIn.canSurvive(level, currentPos)) Blocks.AIR.defaultBlockState() else super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos)
  }

  override fun getStateForPlacement(context: BlockPlaceContext): BlockState {
    return defaultBlockState().setValue(FACING, context.horizontalDirection) as BlockState
  }

  override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
    return if (state.getValue(BITES) == MAX_BITES) PLATE_SHAPE else SHAPE
  }
}